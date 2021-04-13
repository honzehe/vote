<template>
<div class="bg">
  <div class="regist">
   <!-- <div class="image">
      <img src="../../assets/logo.png" />
    </div> -->
    <el-container>
      <el-header>
        <h1>投票系统</h1>
      </el-header>
      <el-main>
        <el-input v-model="userInfo.username" placeholder="请输入账号"></el-input>
        <el-input placeholder="请输入密码" v-model="userInfo.password" show-password></el-input>
        <el-input v-model="userInfo.mail" placeholder="请输入邮箱地址"></el-input>
        <el-input v-model="userInfo.phoneNumber" placeholder="请输入手机号码"></el-input>
        <el-checkbox v-model="male" label="男" border></el-checkbox>
        <el-checkbox v-model="female" label="女" border></el-checkbox>
        <el-button type="primary" @click="regist()" style="display: block; margin: auto;">开始注册</el-button>
      </el-main>
    </el-container>
  </div>
</div>

</template>

<script>
  export default{
    data(){
      return{
        male:true,
        female:false,
        userInfo:{
          Id:null,
          username: '',
          password: '',
          mail: '',
          gender: 1,
          image: '',
          phoneNumber: '',
          authority: '1',
        }
      }
    },
    methods:{
      regist(){
        if(this.male==true)
          this.userInfo.gender=1
        else
          this.userInfo.gender=0
        this.$axios({
          url: '/vote/regist',
          method: 'post',
          data:{
            ...this.userInfo
          }
        }).then(res =>{
          let resData = res.data;
          if(resData=='0'){
            console.log("注册失败！！！")
          }else{
            this.$cookies.set("user", this.userInfo)
            console.log("注册成功！！！")
            this.$router.push("/")
          }
        })
      }
    }
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
  .regist{
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
</style>
