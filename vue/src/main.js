// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import config from './config'
import MyComponent from './components/MyComponent'
import './http';
import 'vuetify/dist/vuetify.min.css'
import qs from 'qs'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/material.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import '@mdi/font/css/materialdesignicons.css'
import ElementUI from 'element-ui';
import store from "./store";
import axios from 'axios'

axios.interceptors.request.use(config=> {
  console.log('请求前')
  //console.log(sessionStorage.getItem('token'))
  if (sessionStorage.getItem('token')) {
    console.log('带上token')
    config.headers.common['Authentication-Token'] = sessionStorage.getItem('token')
  }
  return config
},error => {
  return Promise.reject(error)
})
axios.interceptors.response.use(response=>{
  return response
},error => {
  if (error.response){
    switch (error.response.status) {
      case 401:
        store.commit('del_token',);
        router.replace({
          path:'/login',
          query:{
            redirect:router.currentRoute.fullPath
          }
        })

    }
  }
  return Promise.reject(error.response.date)
})


Vue.use(Vuetify, { theme: config.theme})
Vue.use(MyComponent)
Vue.use(ElementUI)
Vue.prototype.$qs = qs;

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store:store,
  // vuetify: new Vuetify(),
  components: { App },
  template: '<App/>'
})
