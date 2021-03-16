<template>
  <button @click="addCounter">{{ counter }}</button>
</template>

<script>
export default {
  name: "GlobalComponent",
  // GolobalComonent 연결시 initial-counter
  // 명명 규칙에 의해 initial-counter -> initialCounter로 표기
  props: ['initialCounter'],
  data() {
    return {
      counter: this.initialCounter
    }
  },
  methods: {
    addCounter() {
      this.counter++
    }
  }
}
</script>

<style scoped>

</style>