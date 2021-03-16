<template>
  <div>
    <h3>게시판</h3>
    <table border="1">
      <tr>
        <th align="center" width="80">번호</th>
        <th align="center" width="320">제목</th>
        <th align="center" width="100">작성자</th>
        <th align="center" width="180">등록일자(버그수정중)</th>
      </tr>
      <!--      boards(views)를 사용하기 위해선 props(components) 객체에 지정을 해줘야 한다.-->
      <tr v-if="!boards || (Array.isArray(boards) && boards.length === 0)">
        <td colspan="4">
          List is empty
        </td>
      </tr>
      <!--      게시판 리스트 안에 있는 모든 정보를 순회하며 table을 만든다.-->
      <tr v-else v-for="board in boards" :key="board.boardNo">
        <td align="center">{{ board.boardNo }}</td>
        <td align="left">
          <!--          제목을 클릭하면 router를 통해 name에 지정한 경로로 들어간다.-->
          <!--          이 때, params 객체에 담긴 프로퍼티들을 매개변수로 전달할 수 있다.-->
          <router-link :to="{
            name: 'BoardReadPage',
            params: { boardNo: board.boardNo.toString() }
          }">
            {{ board.title }}
          </router-link>
        </td>
        <td align="right">{{ board.writer }}</td>
        <td align="center">{{ board.regDate }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'BoardList',
  props: {
    boards: {
      type: Array
    }
  }
}
</script>
