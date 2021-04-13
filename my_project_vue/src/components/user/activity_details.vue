<template>
  <div>
      <Myheader></Myheader>
      <h3>{{activity_detail.title}}</h3>
      <div class="introduction">
        <p>{{activity_detail.introduction}}</p>
      </div>
	  <h3>投票截止时间：{{activity_detail.deadLine}}</h3>
      <div class="option">
          <el-table
            :data="options"
            border
            style="width: 100%">
            <el-table-column type="index"
              label="序号"
              width="100"
              align="center">
              </el-table-column>
            <el-table-column
              prop="description"
              label="选项描述"
              width="240">
            </el-table-column>
            <el-table-column
              prop="poll"
              label="票数"
              width="180">
            </el-table-column>
            <el-table-column
                  fixed="right"
                  prop="id"
                  label="操作"
                  width="100">
                  <template slot-scope="scope">
                    <el-button type="text" size="small" @click="vote(scope.row.id)">投票</el-button>
                  </template>
                </el-table-column>
          </el-table>
      </div>

      <h3 style="color: red;">{{message}}</h3>

  </div>

</template>

<script>
import header from "@/components/user/header"
export default{
  components:{
    "Myheader":header
  },
	data(){
		return{
		  username:null,
		  message:null,
		  voted:false,
		  activity_detail: null,
		  options:null,
		}
	},
    created(){
      this.init()
      this.start()
    },
    beforeDestroy() {
      clearInterval(this.timer)
    },
    methods:{
      init(){
        console.log("发送请求了")
        let id = this.$route.query.id;
        console.log(id)
        this.$axios({
          url: `/vote/getActivityDetail/${id}`,
          method: 'GET',
        }).then(res => {
          this.activity_detail = res.data;
          console.log(this.activity_detail)
        })
        this.getOptions()
      },
      vote(id){
        if(this.$cookies.get("user").authority == 1){
          let date = new Date(this.activity_detail.deadLine)
          let nowdate = new Date()
          console.log(nowdate)
          console.log(date)
          if(nowdate > date){
              this.$message("已过投票截止时间") 
          }else{
            let votePostData = this.$qs.stringify({
              aId: this.activity_detail.Id,
              id: id
            })
            let postData = this.$qs.stringify(
            {
              uId: this.$cookies.get("user").Id,
              aId: this.activity_detail.Id
            })
            if(this.voted != true){
              this.voted = true
              this.$axios({
              url: `/vote/voteActivity`,
              method: 'PUT',
              data:postData
              }).then(res => {
                if(res.data != -1){
                  this.$axios({
                  url: `/vote/updatePoll`,
                  method: "PUT",
                  data:votePostData
                  })
                }else{
                  this.$message("一个活动只能投一次票")
                }
              })}else{
              this.$message("请勿反复投票")
              }
          }
        }else{
          this.$message("没有权限投票")
        }
      },
      start(){
        this.timer = setInterval(this.getOptions, 500);
      },
      getOptions(){
        let id = this.$route.query.id;
        this.$axios({
          url: `/vote/getOptions/${id}`,
          method: 'GET',
        }).then(res => {
          this.options = res.data
        })
      },
    },
}
</script>

<style>
  .option{
    width: 600px;
    height: auto;
    margin: auto;
  }
  .options{
    width: 200px;
    height: auto;
    float: left;
    margin: auto;
  }
  .username{
    font-size: 10;
  }
  .introduction{
    font-family: "微软雅黑";
    color: #330033;
    margin: auto;
    width: 600px;
  }
</style>
