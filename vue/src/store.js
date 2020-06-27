import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store=new Vuex.Store({
  state:{
    token:''
  },
  mutations:{
    set_token:(state,token)=>{
      state.token=token
      window.sessionStorage.token=token
    },
    del_token:(state)=>{
      state.token=''
      window.sessionStorage.removeItem('token')
    }
  }

})
Vue.prototype.$store=store
export default store
