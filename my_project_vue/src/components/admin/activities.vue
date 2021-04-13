<template>

<div>
  <el-container>
    <el-header>
      <Myheader></Myheader>
    </el-header>
    <el-main class="activities">
      <el-table
          :data="all_activities"
          border
          style="width: 100%">
          <el-table-column
            type="index"
            fixed
            prop="title"
            label="序号"
            width="50">
          </el-table-column>
          <el-table-column
            fixed
            prop="title"
            label="标题"
            width="200">
          </el-table-column>
          <el-table-column
            fixed
            prop="publictime"
            label="发布时间"
            width="200">
          </el-table-column>
          <el-table-column
            prop="deadLine"
            label="截止时间"
            width="180">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button @click="getActivityDetail(scope.row.Id)" type="text" size="small">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="pagination">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination.current"
            :page-sizes="[6, 10, 20, 40]"
            :page-size="pagination.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total">
          </el-pagination>
        </div>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>

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
        all_activities: null,
        username: null,
        pagination:{
          current:1,
          total:0,
          size:6
        },
      }
    },
    created() {
      this.getAllActivities()
    },
    methods:{
      getAllActivities(){
        this.$axios({
          url:`/vote/getAllActivities/${this.pagination.current}/${this.pagination.size}`,
        }).then(res => {
          this.all_activities=res.data.list
          this.pagination.total=res.data.total
          console.log("获取所有活动信息")
          console.log(toString(res))
        })
      },
      getActivityDetail(id){
          this.$router.push({path:"/test",query:{id:id}})
        },
      handleSizeChange(val) {
        this.pagination.size = val
        this.getAllActivities()
      },
      //改变当前页码，重新发送请求
      handleCurrentChange(val) {
        this.pagination.current = val
        this.getAllActivities()()
      },
     }
 }

</script>

<style>
  .activities{
    width: 800px;
    margin: auto;
  }
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

</style>
