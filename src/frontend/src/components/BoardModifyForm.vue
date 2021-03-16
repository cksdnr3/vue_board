<template>
  <div>
    <h3>수정 형식</h3>
    <!--    prevent는 해당 이벤트의 동작을 막는다?-->
    <form @submit.prevent="onSubmit">
      <table>
        <tr>
          <td>번호</td>
          <td><input type="text" :value="board.boardNo" disabled></td>
        </tr>
        <tr>
          <td>등록일자</td>
          <td><input type="text" :value="board.regDate" disabled></td>
        </tr>
        <tr>
          <td>제목</td>
          <td><input type="text" v-model="title"></td>
        </tr>
        <tr>
          <td>작성자</td>
<!--          v-bind와 같은 일반 바인딩의 경우 readonly 특성을 가지고 있다.-->
<!--          실제로 값을 입력하면 input태그의 value가 바뀌는 것이지 BoardModifyForm board의 writer가 바뀌는 것이 아니다.-->
          <td><input type="text" :value="board.writer" disabled></td>
        </tr>
        <tr>
          <td>본문</td>
<!--          v-model 은 양방향 바인딩이다.-->
<!--          실제로 BoardModifyForm의 content가 바뀐다.-->
          <td><textarea v-model="content" rows="5"></textarea></td>
        </tr>
      </table>

      <div>
        <button type="submit">수정</button>
        <router-link :to="{ name: 'BoardReadPage', params: { boardNo: board.boardNo.toString() } }">
          취소
        </router-link>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'BoardModifyForm',
  props: {
    board: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      // board : { title: '', content : ''} 와 같은 형태로 객체를 통해 멀티바인딩을 할 수 도 있다.
      title: '',
      content: ''
    }
  },
  methods: {
    onSubmit () {
      const { title, content } = this
      //  $emit은 첫 번째 인자로 전달하는 이벤트를 발생시킨다.
      // 사실 @ 로 지정한 이벤트는 submit이라는 내장 이벤트가 아닌
      // 커스텀 이벤트이다. 그 것을 작동 시키는 것이다.
      this.$emit('submit', { title, content })
    }
  },
  created () {
    this.title = this.board.title
    this.content = this.board.content
  }
}
</script>
