import {
    FETCH_BOARD_LIST,
    FETCH_BOARD,
    CRAWL_START,
    FIND_ONE
} from './mutation-types'

export default {
  // mutation 에서 정의된 것들은 크리티컬 섹션을 보호해줌 - 동기를 보장해준다.

  // For Board
  [FETCH_BOARD_LIST](state, boards) {
    state.boards = boards
  },
  [FETCH_BOARD] (state, board) {
    state.board = board
  },

  // For Test
  increment(state) {
    state.count++
  },
  decrement(state) {
    state.count--
  },
  successGenerateRandomNumber (state, payload) {
    state.random = payload
  },
  [CRAWL_START] (state, payload) {
    state.lists = payload
  },
  [FIND_ONE] (state, payload) {
    state.news = payload
  }
}
