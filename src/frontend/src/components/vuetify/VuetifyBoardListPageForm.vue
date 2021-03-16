<template>
  <div>
    <table>
      <tr>
        <th>번호</th>
        <th>이름</th>
        <th>연락처</th>
        <th>주소</th>
      </tr>
      <tr v-for="page in paginatedData" :key="page.no">
        <td>{{ page.no }}</td>
        <td>{{ page.name }}</td>
        <td>{{ page.tel }}</td>
        <td>{{ page.address }}</td>
      </tr>
    </table>
    <div>
      <button :disabled="pageNum === 0" @click="prevPage">
        이전
      </button>
      <span>{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage">
        다음
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "VuetifyBoardListPageForm",
  data() {
    return {
      pageNum: 0,
    }
  },
  props: {
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: true,
      default: 10
    }
  },
  methods: {
    nextPage () {
      this.pageNum += 1
    },
    prevPage () {
      this.pageNum -= 1
    }
  },
  computed: {
    pageCount() {
      const listLen = this.listArray.length
      const listSize = this.pageSize

      let page = Math.floor(listLen / listSize)
      if(listLen % listSize > 0) {
        page += 1
      }
      return page
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize
      const end = start + this.pageSize

      return this.listArray.slice(start, end)
    }
  }
}
</script>

<style scoped>
template {
  font-size: 62.5%;
}

table {
  width: 70%;
  border-collapse: collapse;
}

table tr {
  border-bottom: 1px solid #77aadd;
  text-align: center;

}

div button {
  margin: 10px 15px 0px;
  letter-spacing: 2px;
  outline: none;
  background: #77aadd;
  border-radius: 5px;
  color: white;
  padding: 10px 15px;
  font-size: 0.8rem;
}

div button:hover {
  background: #5a91c7;
}
</style>