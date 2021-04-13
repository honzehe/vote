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
      <el-menu-item index="1" @click="toUserManager()()">用户管理</el-menu-item>
      <el-menu-item index="3" @click="AddActivity()">添加活动</el-menu-item>
      <el-menu-item index="4" @click="logout()">注销</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  export default{
    name:"header",
    methods:{
      toActivities(){
        this.$router.push("/admin/activities")
      },
      toUserManager(){
        this.$router.push("/admin/user_manager")
      },
      AddActivity(){
        this.$router.push("/admin/add_activity")
      },
      logout(){
        this.$cookies.remove("user")
        this.$router.push("/")
      }
    }
  }
</script>

<style scoped>
  .header{
    background-color: #000000;
  }
</style>
