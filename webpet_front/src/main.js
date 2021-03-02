import Vue from 'vue'
import App from './App.vue'
//1.导入ant-design-vue
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
//导入已经自定义的vuex
import store from './store'
//导入自定义的router
import router from './router'
Vue.config.productionTip = false
import infiniteScroll from 'vue-infinite-scroll'
Vue.use(infiniteScroll)
//2.挂载Antd
Vue.use(Antd)
//启动路由保护
router.beforeEach((to, from, next) => {
  if (to.path == '/' || to.path == '/login'||to.path=='/knowledge'||to.path=='/dynamic') {
    console.log("去到宠物知识界面")
    next();
  }else {
    let state = JSON.parse(sessionStorage.getItem("store"))
    console.log(state)
    if (state!=null&&state.uid != 0) {
      console.log("已经登陆，跳转成功")
      next();
    }else{
      alert("未登录，跳转失败,请先登陆")
      if(from.path==='/dynamic'){
        next('/dynamic')
      }
      else {
      next('/knowledge');}
    }
  }
})
new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
