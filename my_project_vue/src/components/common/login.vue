<template>

<div class="bg">
 <div class="login">
  <el-container>
    <el-header>
      <h1>投票系统</h1>
    </el-header>
    <el-main>
      <h3 class="title">账号登录</h3>
      <h4 style="color: red;">{{warn}}</h4>
      <el-input v-model="user.username" placeholder="请输入内容"></el-input>
      <el-input placeholder="请输入密码" v-model="user.password" show-password></el-input>
      <el-button @click="login()" type="primary">登录</el-button>
      <el-button type="primary" @click="gotoRegist()">注册账号</el-button>
      <Vcode :show="isShow" @success="success" @close="close" />
    </el-main>
    </el-container>
  </div>
</div>

</template>

<script>
import Vcode from "vue-puzzle-vcode";
export default{
  components:{
    Vcode
  },
  data(){
    return{
      isShow: true, // 验证码模态框是否出现
      user:{
        Id: null,
        username: "admin",
        password: null,
        mail: null,
        gender: null,
        image: null,
        phoneNumber: null,
        authority: null,
        aId: null
      },
      warn:""
    }
  },
  methods:{
    login(){
      let postData = this.$qs.stringify({
         username:this.user.username,
         password:this.user.password
       })
       var url = "/vote/login"
       this.$axios.post(url,postData)
       .then(res =>{
        let resData = res.data;
        if(resData == "success"){
          console.log(resData)
          console.log("登录成功")
          this.$axios({
            url:`/vote/getUserByName/${this.user.username}`,
            method:'GET',
          }).then(res =>{
            console.log(res.data)
            this.$cookies.set("user",res.data)
          })
          if(this.user.username=="admin"){
            this.$router.push("/admin/activities")
          }else{
            this.$router.push("/user/activities")
          }
        }else{
          console.log("登录失败")
          this.warn="用户名或密码错误"
        }
      })
    },
    gotoRegist(){
      this.$router.push({path: '/user/regist'})
    },
    // 用户通过了验证
    success(msg) {
      this.isShow = false; // 通过验证后，需要手动隐藏模态框
    },
    // 用户点击遮罩层，应该关闭模态框
    close() {
      this.isShow = false;
    }
  },
}
</script>

<style>
  .el-header, .el-footer {
      background-color: #B3C0D1;
      color: #333;
      text-align: center;
      line-height: 40px;
    }

    .el-aside {
      background-color: #D3DCE6;
      color: #333;
      text-align: center;
      line-height: 300px;
    }

    .el-main {
      background-color: #E9EEF3;
      color: #333;
      text-align: center;
      line-height: 50px;
    }
    body > .el-container {
      margin-bottom: 40px;
    }
    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
      line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
      line-height: 320px;
    }
  .login{
    height: 200px;
    width: 400px;
    position: inherit;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    margin:auto;

  }
  .bg{
     background-image: url(../../assets/img/loginbg.png);
     background-repeat: repeat;
  }
  .title{
    font-size: 15;
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  }
</style>
