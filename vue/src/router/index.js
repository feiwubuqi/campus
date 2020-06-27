import Vue from 'vue'
import Router from 'vue-router'
import store from "../store";

// if (window.sessionStorage.getItem('token')){
//   store.commit('set_token',seesionStorg.getItem('token'))
// }

Vue.use(Router)

function route (path, file, name, children) {
  return {
    exact: true,
    path,
    name,
    children,
    component: () => import('../pages' + file)
  }
}

export default new Router({
  mode:'history',
  base:'/campus/',
  routes: [
    route("/login",'/Login',"Login"),// /login路径，路由到登录组件
    route("/register",'/Register',"Register"),
    {
      path:"/", // 根路径，路由到 Layout组件
      component: () => import('../pages/Layout'),
      redirect:"/login",
      children:[ // 其它所有组件都是 Layout的子组件
        route("/index/dynamic",'/Dynamic',"Dynamic"),
        route("/item/category",'/item/Category',"Category"),
        route("/item/brand",'/item/Brand',"Brand"),
        route("/item/newsInfo",'/item/NewsInfo',"NewsInfo"),
        route("/item/updateInfo",'/item/UpdateInfo',"UpdateInfo"),
        route("/item/list",'/item/Goods',"Goods"),
        route("/item/repair",'/item/repair/RepairInfo',"RepairInfo"),
        route("/item/addRepair",'/item/repair/NewRepair',"NewRepair"),
        route("/authority/info",'/item/author/AccountInfo',"AccountInfo"),
        route("/authority/role",'/item/author/Role',"Role"),

        route("/item/dormitory",'/item/dormitory/DormitoryInfo',"DormitoryInfo"),
        route("/trade/promotion",'/trade/Promotion',"Promotion")
      ]
    }
  ]
})
