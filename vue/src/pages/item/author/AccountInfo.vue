<template>
  <el-row type="flex" justify="center" style="margin-top: 50px">

    <el-form ref="account" :model="account"  label-width="80px" @keyup.enter.native="updateAccount()">
      <el-form-item prop="accountSno" label="账号"><el-input disabled v-model="account.accountSno" placeholder="请输入账号" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item prop="userName" label="姓名"><el-input  v-model="account.accountName" placeholder="请输入姓名" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item prop="mobile" label="学院"><el-input v-model="account.academy" placeholder="请输入手机号码" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item prop="mobile" label="联系方式"><el-input v-model="account.mobile" placeholder="请输入手机号码" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item prop="mobile" label="班级"><el-input v-model="account.grade" placeholder="请输入手机号码" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item prop="mobile" label="籍贯"><el-input v-model="account.nativePlace" placeholder="请输入手机号码" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateAccount" icon="el-icon-upload">修改</el-button>
<!--       // <el-button @click="resetForm('account')">重置</el-button>-->
</el-form-item>
      <router-link to="/login">切换账号重新登录</router-link>

    </el-form>
  </el-row>
</template>

<script>
    export default {
        inject:['reload'],
        name: "AccountInfo",
        data(){
            return{
            account:{
                accountSno:'',
                accountName:'',
                accountPwd:'',
                academy:'',
                mobile:'',
                grade:'',
                imgAddress:'',
                nativePlace:'',
            }
        }
    },
        created(){
            let accountinfo = JSON.parse(sessionStorage.getItem('account'))
            this.account = accountinfo
        },
        methods: {
            resetForm(account) {
                this.$refs[account].resetFields();
            },
            updateAccount() {
                this.$http.post("/campus_life_api/api/campus/account/edit", this.account).then(res => {
                    this.$message({
                        type: 'success',
                        message: '正在修改',
                        duration: 2000
                    });
                    let  accountinfo=JSON.stringify(res.data)
                    sessionStorage.setItem('account',[accountinfo])
                    this.reload()
                })

            },
        }
    }
</script>

<style scoped>

</style>
