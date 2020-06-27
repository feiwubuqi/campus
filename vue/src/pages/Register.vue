<template>
  <el-row type="flex" justify="center" style="margin-top: 50px">

    <el-form ref="formData" :model="formData" :rules="rules" label-width="80px" @keyup.enter.native="register()">
      <v-toolbar-title style="margin-left: 80px;margin-bottom: 20px">校园生活服务平台</v-toolbar-title>
      <el-form-item prop="accountSno" label="账号"><el-input v-model="formData.accountSno" placeholder="请输入账号" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item prop="password" label="密码"><el-input v-model="formData.password" placeholder="请输入密码" type="password" prefix-icon="icon-login_pwd" clearable></el-input></el-form-item>
      <el-form-item prop="cheackPassword" label="确认密码"><el-input v-model="formData.cheackPassword" placeholder="再次输入密码" type="password" prefix-icon="icon-login_pwd" clearable></el-input></el-form-item>

      <el-form-item prop="userName" label="姓名"><el-input v-model="formData.userName" placeholder="请输入姓名" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
      <el-form-item prop="mobile" label="手机号码"><el-input v-model="formData.mobile" placeholder="请输入手机号码" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
<!--      <el-form-item prop="userName" label="手机号码"><el-input v-model="formData.userName" placeholder="请输入账号" prefix-icon="icon-login_user" clearable></el-input></el-form-item>-->
<!--      <el-form-item prop="userName" label="班级"><el-input v-model="formData.userName" placeholder="请输入账号" prefix-icon="icon-login_user" clearable></el-input></el-form-item>-->
<!--      <el-form-item prop="userName" label="头像"><el-input v-model="formData.userName" placeholder="请输入账号" prefix-icon="icon-login_user" clearable></el-input></el-form-item>-->
<!--      <el-form-item prop="userName" label="籍贯"><el-input v-model="formData.userName" placeholder="请输入账号" prefix-icon="icon-login_user" clearable></el-input></el-form-item>-->



      <el-form-item>
        <el-button type="primary" @click="register('formData')" icon="el-icon-upload">注册</el-button>
        <el-button @click="resetForm('formData')">重置</el-button></el-form-item>
      <router-link to="/login">已有密码？登录</router-link>

    </el-form>
  </el-row>
</template>
<script>
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.formData.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

            return {
                formData: {
                    accountSno: '',
                    password: '',
                    cheackPassword:'',
                    username:'',
                    mobile:""
                },
                rules: {
                    accountSno:[{required: true,message: '账号不能为空', trigger: 'blur'}],
                    userName: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
                    password: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
                    mobile:[{required: true,max:11,min:11,message:"请输入11位手机号码",trigger:'blur'}],
                    cheackPassword: [{ required: true, validator: validatePass, trigger: 'blur' }]

                }
            };
        },
        methods: {
            register(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.$http.post("/campus_life_api/api/campus/register",{
                            accountSno:this.formData.accountSno,
                            accountPwd:this.formData.password,
                            accountName:this.formData.userName,
                            mobile:this.formData.mobile

                        }).then(res=>{
                            this.$message({
                                type: 'success',
                                message: '注册成功',
                                duration:2000
                            });
                            let _this=this
                            setTimeout(function () {
                                _this.$router.push('/login');
                            },1000)

                        })

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }

        }
    };
</script>
