<template>

<div>
  <el-container>
    <el-header>
      <Myheader></Myheader>
    </el-header>
    <div class="messages">
       <el-main class="messages">
        <el-table
            :data="messages"
            height="250"
            border
            style="width: 100%">
            <el-table-column
              prop="message"
              label="消息"
              width="360">
            </el-table-column>
            <el-table-column
              prop="sendtime"
              label="时间"
              width="360">
            </el-table-column>
          </el-table>
      </el-main>
    </div>

    <el-footer>Footer</el-footer>
  </el-container>

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
        messages: null,
      }
    },
    created() {
      this.getMessages()
      this.start()
    },
    beforeDestroy(){
      clearInterval(this.timer)
    },
    methods:{
      getMessages(){
        let username = this.$cookies.get("user").username
        console.log(username)
        this.$axios({
          url:`/vote/getMessage/${username}`,
        }).then(res => {
          this.messages=res.data
        })
      },
      start(){
        this.timer = setInterval(this.getMessages, 500)
      }
    }

  }
</script>
<style>
    .el-header, .el-footer {
      background-color: #B3C0D1;
      color: #333;
      text-align: center;
      line-height: 60px;
    }
    .el-main {
      background-color: #E9EEF3;
      color: #333;
      text-align: center;
      line-height: 60px;
    }

    body > .el-container {
      margin-bottom: 40px;
    }
  .image{
    width: 120px;
    height: 120px;
  }
  .el-row {
      margin-bottom: 20px;
      &:last-child {
        margin-bottom: 0;
      }
    }
    .el-col {
      border-radius: 4px;
    }
    .bg-purple-dark {
      background: #99a9bf;
    }
    .bg-purple {
      background: #d3dce6;
    }
    .bg-purple-light {
      background: #e5e9f2;
    }
    .grid-content {
      border-radius: 4px;
      min-height: 36px;
    }
    .row-bg {
      padding: 10px 0;
      background-color: #f9fafc;
    }
    .messages{
      width: 800px;
      margin: auto;
    }

</style>
