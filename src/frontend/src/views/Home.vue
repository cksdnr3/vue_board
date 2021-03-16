<template>
  <div class="home">
    <div id="header">
      <router-link :to="{ name: 'Home' }"
                   class="nav-link"
                   active-class="active">
        Home
      </router-link>
      <router-link :to="{ name: 'About' }"
                   class="nav-link"
                   active-class="active">
        About Us
      </router-link>
      <router-link :to="{ name: 'BoardListPage' }"
                   class="nav-link"
                   active-class="active">
        Board
      </router-link>
      <router-link :to="{ name: 'Test' }">
        Test
      </router-link>
      <router-link :to="{ name: 'VuetifyBoardListPage' }">
        VuetifyBoardListPage
      </router-link>
      <router-link :to="{ name: 'VuetifyBoardListPaginationPage' }">
        VuetifyBoardListPaginationPage
      </router-link>
      <router-link :to="{ name: 'CrawlCategoryPage' }">
        crawling
      </router-link>
      <h2>This is an Home Page</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data: function () {
    return {
      message: 'Vue Test Message'
    }
  },
  components: {
  }
}
</script>

<!--스타일 scoped 특성은 현제 template에만 스타일을 적용다는 뜻-->
<style scoped>
div {
  border: 1px solid #ccc;
}

#header {
  padding: 15px;
  margin-bottom: 15px;
}

img {
  width: auto;
  height: auto;
  max-width: 1000px;
  max-height: 350px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

#login {
  background-color: #77aadd;
  color: #ffffff;
  font-weight: bold;
  float: right;
}

</style>
