<template>
  <div>
    <Myheader></Myheader>
    <div class="userTable">
      <el-table
          :data="users"
          border
          style="width: 100%">
          <el-table-column
            fixed
            type="index"
            label="序号"
            width="150">
          </el-table-column>
          <el-table-column
            fixed
            prop="username"
            label="用户名"
            width="150">
          </el-table-column>
          <el-table-column
            prop="mail"
            label="邮箱"
            width="120">
          </el-table-column>
          <el-table-column
            prop="gender"
            label="性别"
            width="120">
          </el-table-column>
          <el-table-column
            prop="phoneNumber"
            label="手机号码"
            width="120">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="不可投票/可投票"
            width="150">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.authority"
                active-color="#13ce66"
                inactive-color="#7F7F7F"
                active-value="1"
                inactive-value="0"
                @change="changeUserAuthority(scope.row.Id, scope.row.authority)">
              </el-switch>
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
    </div>
    <h3 style="color: red;">{{message}}</h3>
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
        message:'',
        users:null,
        pagination:{
          current:1,
          total:0,
          size:6
        },
      }
    },
    created() {
      this.getUsers();
    },
    methods:{
      getUsers(){
        this.$axios({
          url: `/vote/admin/getUsers/${this.pagination.current}/${this.pagination.size}`,
          method: "GET"
        }).then(res => {
          this.users = res.data.list
          this.pagination.total = res.data.total
        })
      },
      changeUserAuthority(Id, authority){
        if(Id==1)
          this.$message("无效操作，不能修改管理员权限")
        else{
          let postDate = this.$qs.stringify({
            id:Id,
            authority:authority
          })
          // console.log(postDate)
          this.$axios({
            url:"/vote/admin/changeAuthority",
            method:"POST",
            data:postDate
          }).then(res =>{
            this.$message("修改权限成功")
            console.log("修改权限成功")
          })
        }
      },
      handleSizeChange(val) {
        this.pagination.size = val
        this.getUsers()
      },
      //改变当前页码，重新发送请求
      handleCurrentChange(val) {
        this.pagination.current = val
        this.getUsers()
      },
    }
  }
</script>

<style>
  .userTable{
    width: 850px;
    height: auto;
    margin: auto;
  }
</style>
