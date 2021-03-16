<template>
  <div align="center">
    <h2>게시글 읽기</h2>
    <board-read v-if="board" :board="board"/>
    <p v-else>로딩중 ...</p>
    <router-link :to="{ name: 'BoardModifyPage', params: { boardNo } }">
      수정
    </router-link>
    <button @click="onDelete">Delete</button>
    <router-link :to="{ name: 'BoardListPage' }">
      목록
    </router-link>
  </div>
</template>

<script>
import BoardRead from '@/components/BoardRead'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'BoardReadPage',
  props: {
    boardNo: {
      type: String,
      // required 가 true 이면 외부에서 접근 시 반드시 필요함
      required: true
    }
  },
  // computed는 현재 template에 선언된 변수를 바꾸는 등 간단한 로직에 사용
  // 해당 함수와 연결된 값이 바뀔 때 만 해당 함수만을 실행한다.
  // computed와 methods의 차이점:
  // 1. template에서 호출시 ()를 적지 않아야 한다.
  // 2. return 값이 반드시 존재해야한다.
  // 3. 매개변수를 받을 수 없다.
  computed: {
    // state 는 반드시 computed에
    // getter 도 반드시 computed에
    ...mapState([
      'board'
    ])
  },
  created () {
    console.log('BoardReadPage created(): ' + this.boardNo)
    this.fetchBoard(this.boardNo)
      .catch(err => {
        alert(err.response.data.message)
        this.$router.push()
      })
  },
  // methods는 computed와 다르게 어떤 변수든 변경되면(update가 동작하면) 함수를 모두 실행한다.
  methods: {
    // action은 반드시 methods에
    // mutation은 반드시 mthods에
    ...mapActions([
      'fetchBoard'
    ]),
    onDelete () {
      // board는 객체이고 boardNo는 board의 프로퍼티이다.
      // board의 여러가지 프로퍼티 중 boardNo만 정하겠다는 말
      const { boardNo } = this.board
      // 삭제는 deleteMapping
      axios.delete(`http://localhost:7777/boards/${boardNo}`)
        .then(() => {
          alert('Delete Success')
          this.$router.push({ name: 'BoardListPage' })
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    }
  },
  components: {
    BoardRead
  }
}
</script>
