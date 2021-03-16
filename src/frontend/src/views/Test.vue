<template>
  <div>
    <h1>For Test</h1>
    <button v-on:click="reverseMsg">Reverse Message</button>
    <p>{{ message }}</p>
    <b>count: {{ this.$store.state.count }}</b><br>
    <b>count^2: {{ this.$store.getters.countSquare }}</b><br>
    <b>count^weight: {{ this.$store.getters.countWeightSquare }}</b><br>
    <b>randomNum: {{ this.$store.getters.getRandom }}</b><br>
    <input type="button" @click="increment()" value="increment" /><br>
    <input type="button" @click="decrement()" value="decrement" /><br>
    <input type="button" @click="randomNumber()" value="random"><br>

<!--    Component를 만든다는 것은 결국 나만의 커스텀 HTML 태그를 만든다는 것이다.-->
<!--    LocalComponent(components)가 쓰는 num은
        현재 Component(views)에서 사용하는 value와 같다.-->
    <local-component :num="value"></local-component>
    <button v-on:click="plus">click</button><br>

<!--    GlobalComponent-->
    <global-Component :initial-counter="counter"></global-Component>

  </div>
</template>

<script>
import Vue from 'vue'
import cookies from 'vue-cookies'
import LocalComponent from "@/components/LocalComponent";
import GlobalComponent from "@/components/GlobalComponent";

Vue.component(GlobalComponent.name, GlobalComponent)
Vue.use(cookies)

export default {
  name: "Test",
  data () {
    return {
      message: "안녕 난 뷰야~",
      counter: 7000,
      value: 3000
    }
  },
  components: {
    'local-component': LocalComponent
  },
  methods: {
    reverseMsg() {
      this.message = this.message.split('').reverse().join('')
    },
    increment() {
      this.$store.commit('increment')
      this.$cookies.set('value',
          this.$store.state.count, '5s')
    },
    decrement() {
      this.$store.commit('decrement')
      this.$cookies.set('value',
          this.$store.state.count, '10s')
    },
    randomNumber() {
      // 디스패치하면 action으로 넘어간다.
      this.$store.dispatch('generateRandomNumber')
    },
    plus() {
      this.value++
    }
  },
  created() {
    this.$store.state.count = this.$cookies.get('value')
  }
}
</script>

<style scoped>

</style>