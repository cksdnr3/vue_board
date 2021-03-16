import {
    FETCH_BOARD_LIST,
    FETCH_BOARD,
    CRAWL_START,
    FIND_ONE,
} from './mutation-types'

import axios from 'axios'
import router from "@/router";


// view ---dispatch--> actions --- commit ---> mutation ---mutate---> state --- render ---> view --- dispatch ---> actions .... .... ...
//               (backend처리는 actions에서)
//            비동기 처리는 액션을 거쳐 뮤테이션으로 처리한다.
// 액션은 비동기처리를 한 후에 반드시 뮤테이션으로 정보를 넘기고 뮤테이션에서 state를 관리하는 식으로 사용해야한다.(state관리는 뮤테이션에서 반드시!)
export default {
    // actions는 비동기를 처리한다.!
    async crawlFind ({ commit }, category) {
        axios.get(`http://localhost:7777/${category}`)
            .then(({ data }) => {
                commit(CRAWL_START, data)
                if(window.location.pathname !== '/crawlCategory') {
                    router.push('crawlCategory')
                }
            }
        )
    },
    async crawlFindOne ({ commit }, newsNo) {
        axios.get(`http://localhost:7777/${newsNo}`)
            .then(({ data }) => {
                commit(FIND_ONE, data)
                if(window.location.pathname !== '/crawlCategory') {
                    router.push('crawlCategory/news')
                }
            }
        )
    },
    fetchBoardList ({ commit }) {
        return axios.get('http://localhost:7777/boards')
            .then(res => {
                commit(FETCH_BOARD_LIST, res.data)
            }
        )
    },

    fetchBoard ({ commit }, boardNo) {
        console.log('fetchBoard ' + commit + ', boardNo = ' + boardNo)
        return axios.get(`http://localhost:7777/boards/${boardNo}`)
            .then(res => {
            console.log('fetchBoard - res: ' + res.data)
            commit(FETCH_BOARD, res.data)
            }
        )
    },
    generateRandomNumber ({ commit }) {
        axios.get('http://localhost:7777/random')
            .then(res => {
                console.log('res: ' + parseInt(res.data.randNumber))
                commit('successGenerateRandomNumber', parseInt(res.data.randNumber))
            }
        )
    }
}
