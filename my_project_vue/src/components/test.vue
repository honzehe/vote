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
                      <el-button type="text" size="small" @click="DeleteOption(scope.row.id)">删除选项</el-button>
                    </template>
              </el-table-column>
            </el-table>
            <el-button type="primary" @click="dialogFormVisible = true">通知用户投票结果</el-button>
            <el-dialog title="通知" :visible.sync="dialogFormVisible">
              <el-form :model="message">
                <el-form-item label="消息内容" :label-width="formLabelWidth">
                  <el-input v-model="message.message" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发送时间">
                    <el-date-picker
                      v-model="message.sendtime"
                      type="datetime"
                      placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="sendMessage()">确 定</el-button>
              </div>
            </el-dialog>
            <el-button type="primary" @click="dialogFormVisible1 = true">添加选项</el-button>
            <el-dialog title="添加选项" :visible.sync="dialogFormVisible1">
              <el-form :model="option">
                <el-form-item label="选项描述" :label-width="formLabelWidth">
                  <el-input v-model="option.description" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible1 = false">取 消</el-button>
                <el-button type="primary" @click="AddOption()">确 定</el-button>
              </div>
            </el-dialog>
        </div>
  </div>

</template>

<script>
import header from "@/components/admin/header"
export default{
  components:{
    "Myheader":header
  },
	data(){
		return{
		  username:null,
		  voted:false,
		  activity_detail: null,
		  options:null,
      message:{
        Id:null,
        message:"该活动投票已结束，感谢您的参与",
        sendtime: null,
        username:""
      },
      option:{
        id:'',
        description:"",
        poll:'0',
        aid:''
      },
      formLabelWidth: '120px',
      dialogFormVisible: false,
      dialogFormVisible1: false,
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
      init(){
        console.log("发送请求了")
        let id = this.$route.query.id;
        this.$axios({
          url: `/vote/getActivityDetail/${id}`,
          method: 'GET',
        }).then(res => {
          this.activity_detail = res.data;
          console.log(this.activity_detail)
        })
        this.getOptions()
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
      sendMessage(){
        this.dialogFormVisible=false
        let id = this.$route.query.id;
        this.message.sendtime = this.formatDate(this.message.sendtime);
        this.$axios({
          url: `/vote/admin/sendMessage/${id}`,
          method: 'POST',
          data:{
            ...this.message
          }
        }).then(res => {
          this.$message("通知成功")
        })
      },
      AddOption(){
        this.dialogFormVisible1=false
        // let postData = this.$qs.stringify({
        //   Id:'',
        //   description:this.option.description,
        //   poll:0,
        //   aId:this.$route.query.id
        // })
        this.option.aid=this.$route.query.id;
        console.log(this.option)
        this.$axios({
          url: `/vote/admin/AddOption`,
          method: `POST`,
          data:this.option
        }).then(res =>{
          console.log("添加成功")
        })
      },
      DeleteOption(id){
        let aId = this.$route.query.id;
        this.$axios({
          url:`/vote/admin/deleteOption/${aId}/${id}`,
          method:`post`,
        }).then(res =>{
          console.log("删除成功")
        })
      }
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
