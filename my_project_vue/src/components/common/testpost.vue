<template>
  <h1>Test</h1>
</template>

<script>
  export default{
    created() {
      this.login()
    },
    methods:{
      login(){
        var url = "/vote/login"
        let postData = this.$qs.stringify({
          username:"admin",
          password:"123456"
        })
        this.$axios.post(url,postData)
      }
    }
  }
</script>

<style>
</style>
