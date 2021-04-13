<template>
<div>
  <Myheader></Myheader>
  <div class="activity">
    <el-form ref="form" :model="activity" label-width="80px">
      <el-form-item label="活动名称">
        <el-input v-model="activity.title"></el-input>
      </el-form-item>
      <el-form-item label="活动简介">
        <el-input v-model="activity.subTitle"></el-input>
      </el-form-item>
      <el-form-item label="截止时间">
          <el-date-picker
            v-model="activity.deadLine"
            type="datetime"
            placeholder="选择日期时间">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="活动内容">
        <el-input type="textarea" v-model="activity.introduction"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="AddActivity()">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
    <el-form ref="form" :model="option" label-width="80px">
      <el-form-item label="选项描述">
        <el-input v-model="option.description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="AddOption()">立即添加</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>

</div>

</template>

<script>
import header from "@/components/admin/header"
  export default{
    components:{
      "Myheader":header
    },
    data() {
      return{
        submit:false,
        message: null,
        activity: {
          Id: null,
          title: "",
          publictime: "",
          introduction: "",
          deadLine:"",
          image: "./././",
          timeLimit: ""
        },
        option: {
          id: null,
          description: "",
          aid: null,
          poll: 0,
        },
      }
    },
    created(){
      this.getActivityId();
    },
    methods:{
      formatDate(value) {
        let date = new Date(value);
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? ('0' + MM) : MM;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let m = date.getMinutes();
        m = m < 10 ? ('0' + m) : m;
        let s = date.getSeconds();
        s = s < 10 ? ('0' + s) : s;
        return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
      },
      AddActivity(){
        this.activity.deadLine = this.formatDate(this.activity.deadLine)
        this.activity.publictime = this.formatDate(new Date())
        console.log(this.activity.deadLine)
        if(this.submit==false){
          this.$axios({
            url: "/vote/admin/AddActivity",
            method: "POST",
            data:{
              ...this.activity
            }
          }).then(res =>{
            let resData = res.data
            if(resData != '0'){
              this.submit = true
              this.$message("添加活动成功")
              console.log("添加成功")
            }else{
              console.log("添加失败")
            }
          })
        }else{
          this.$message("请勿反复提交")
        }
      },
      AddOption(){
        console.log(this.option.aid)
        this.$axios({
          url: "/vote/admin/AddOption",
          method: "POST",
          data:{
            ...this.option
          }
        }).then(res => {
          let resData = res.data
          if(resData != '0'){
            this.$message("添加选项成功")
            console.log("添加选项成功")
          }else{
            this.$message("添加选项失败")
            console.log("添加选项失败")
          }
        })
      },
      getActivityId(){
        this.$axios({
          url: "/vote/getActivityId",
          method: "GET"
        }).then(res => {
          let resData = res.data
          this.option.aid = resData+1
          console.log(this.option.aId)
        })
      },
      toActivities(){
        this.$router.push("/admin/activities")
      },
      userManager(){
        this.$router.push("/admin/user_manager")
      },
      logout(){
        this.$cookies.remove("user")
        this.$router.push("/")
      }
    },
}
</script>

<style>
  .main{
    width: auto;
    height: 1000px;
    margin: auto;
  }
  .activity{
    width: 500px;
    height: auto;
    margin: auto;
    border-color: #999999;
    border-style: solid;
  }
</style>
