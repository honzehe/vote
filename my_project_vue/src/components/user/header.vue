<template>
  <div>
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1" @click="toActivities()">活动主页</el-menu-item>
      <el-menu-item index="2" @click="toMyActivities()">投票的活动</el-menu-item>
      <el-submenu index="3">
          <template slot="title">
            <div>
              <el-avatar icon="el-icon-user-solid"></el-avatar>
            </div>
          </template>
          <el-menu-item index="3-1" @click="toMyMessage()">我的消息</el-menu-item>
          <el-menu-item index="3-2">个人信息</el-menu-item>
          <el-menu-item index="3-3" @click="logout()">注销</el-menu-item>
      </el-submenu>


    </el-menu>
  </div>
</template>

<script>
  export default{
    name:"header",
    methods:{
      toActivities(){
        this.$router.push("/user/activities")
      },
      toMyActivities(){
        this.$router.push("/user/vote_activity")
      },
      logout(){
        this.$cookies.remove("user")
        this.$router.push("/")
      },
      toMyMessage(){
        this.$router.push("/user/message")
      }
    }
  }
</script>

<style scoped>
  .header{
    background-color: #000000;
  }
</style>
