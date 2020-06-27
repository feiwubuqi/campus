<template>
  <v-app>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>校园生活服务平台</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field prepend-icon="person" v-model="accountSno" label="账号" type="text"/>
    <v-text-field
    prepend-icon="lock"
    v-model="password"
    label="密码"
    id="password"
:append-icon="e1 ? 'visibility' : 'visibility_off'"
:append-icon-cb="() => (e1 = !e1)"
:type="e1 ? 'text' : 'password'"
    ></v-text-field>
    </v-form>
    </v-card-text>
    <v-card-actions>

    <v-btn color="primary" @click="toRegister">注册</v-btn>
    <v-spacer></v-spacer>
    <v-btn color="primary" @click="doLogin">登录</v-btn>
    </v-card-actions>
    </v-card>
    </v-flex>
    </v-layout>
    </v-container>
    </v-content>
    <v-dialog v-model="dialog" width="300px">
    <v-alert icon="warning" color="error" :value="true">
    用户名和密码不能为空
    </v-alert>
    </v-dialog>
    </v-app>
    </template>

    <script>

export default {

    name:'login',
  data: () => ({
      token:'',
    accountSno:'',
    password: "",
      account:{
      accountSno:'',
     accountName:'',
      accountPwd:'',
      academy:'',
      mobile:'',
      grade:'',
      imgAddress:'',
      nativePlace:'',
          level:''
      },

    dialog: false,
    e1:false
  }),
  methods: {
      toRegister(){

         this.$router.push("/register")
      },

      doLogin(){
          this.$http.post("/campus_life_api/api/campus/login",{
              accountSno:this.accountSno,
              accountPwd:this.password
          }).then(res=>{
            if (null!=res.data){
                //console.log(res.data)
                this.account=res.data
                let accountInfo=JSON.stringify(this.account)
                console.log(accountInfo)
                let token=res.headers.authenticationtoken
               // console.log(token)
                this.$store.commit("set_token",token)
                sessionStorage.setItem('account',accountInfo)
               // console.log(this.$store.state)
                this.$router.push({path:"/index/dynamic"})
            }else {
                alert("用户名或密码错误！")
            }

          })



      }
  }
};
</script>
