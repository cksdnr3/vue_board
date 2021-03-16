<template>
  <div align="center">
    <h2>뷰티파이 기반 게시판</h2>

    <vuetify-board-list-page-form :list-array="pageArray" :page-size="pageSize"/>
  </div>
</template>

<script>
import axios from "axios";
import VuetifyBoardListPageForm from "@/components/vuetify/VuetifyBoardListPageForm";


export default {
  name: "VuetifyBoardListPage",
  components: {
    VuetifyBoardListPageForm

  },
  data () {
    return {
      pageArray: [],
      pageSize: 10,
    }
  },
  created () {
    axios.get('http://sample.bmaster.kro.kr/contacts')
    .then(res => {
      this.pageArray = res.data.contacts
    })
    .catch(err => {
      console.log(err.data.message)
    })
  }
}
</script>

<style scoped>

</style>