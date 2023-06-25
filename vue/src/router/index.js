import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/Manage.vue'),
    redirect: "/charts",
    children: [
      {path: 'charts', name: '主页', component: () => import('../views/Charts.vue')},
      {path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
      {path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
      {path: 'maintain', name: '药品信息维护', component: () => import('../views/Drug.vue')},
      {path: 'supplier', name: '供应商信息', component: () => import('../views/Supplier.vue')},
      {path: 'stock', name: '库存查询', component: () => import('../views/Stock.vue')},
      {path: 'out', name: '药品出库', component: () => import('../views/Out.vue')},
      {path: 'out_info', name: '出库统计', component: () => import('../views/Out_Info.vue')},
      {path: 'in', name: '药品入库', component: () => import('../views/In.vue')},
      {path: 'in_info', name: '入库统计', component: () => import('../views/In_Info.vue')},
      {path: 'check', name: '药品盘点', component: () => import('../views/Check.vue')},
    ]
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  //设置当前的路由名称，为了在Header组件中使用
  store.commit("setPath")  //触发store的数据更新
  next()  //放行路由
})

export default router
