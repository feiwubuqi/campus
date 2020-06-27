<template>
  <div style="height: 500px">
    <el-form style="margin-left: 30%;margin-top: 20px" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="当前用户:" prop="title">
        <el-input  v-model="accountName" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="权限角色"  >
        <el-select v-model="accountlevel" placeholder="请选择权限角色" style="width: 200px">
          <el-option label="普通用户" value="普通用户"></el-option>
          <el-option label="管理员" value="管理员"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-button style="margin-left: 57%" type="text" @click="open">修改</el-button>

    <div :class="flag?'box':'istrue'">
      <el-form style="margin-left: 10px">
        <v-toolbar-title style="color: #00b0ff">提示</v-toolbar-title>
      <el-form-item style="margin-top: 5px;font-size: 16px;font-weight: bold" label="授权账号:" prop="title">
        <el-input type="password" style="width: 200px" v-model="account"></el-input>
      </el-form-item>
      <el-form-item style="margin-top: 15px;font-size: 16px;font-weight: bold" label="授权密码:" prop="title">
        <el-input style="width: 200px" type="password" v-model="password" ></el-input>
      </el-form-item>
        <el-button style="margin-left: 200px" type="text" @click="close">取消</el-button>
        <el-button   type="text" @click="updateAccount">确认</el-button>
      </el-form>
    </div>



  </div>


</template>

<script>
    export default {
        inject:['reload'],
        name: "Role",
        data(){
            return{
                account:'',
                password:'',
                flag:false,
                accountName:'',
                accountlevel:''
            }
        },created() {
            let account=JSON.parse(sessionStorage.getItem('account'))
            this.accountName=account.accountName
            if (account.level==0){
                this.accountlevel='普通用户'
            }else if (account.level==1){
                this.accountlevel='管理员'
            }

        },methods:{

            open() {
                  this.flag=true;
            },
            close(){
                this.flag=false
            },
            updateAccount(){
            this.$http.post("/campus_life_api/api/campus/account/level",{
                author:this.account,
                password:this.password,
                accountSno:JSON.parse(sessionStorage.getItem('account')).accountSno,
                level:this.accountlevel
            }).then(res=>{
                if (res.data!=null){
                  let account=JSON.stringify(res.data)
                    sessionStorage.setItem('account',[account])
                    this.flag=false
                    this.reload()
                }

            })
            }
        }
    }
</script>

<style scoped>
  .istrue{
    display: none;
  }
.box{
  margin-left: 30%;
  width: 300px;
  height: 200px;
  position:absolute;
  padding: 0px;
  border: 1px solid #00b0ff;
  background-color:#FFF;
  z-index:10001;
}


</style>
