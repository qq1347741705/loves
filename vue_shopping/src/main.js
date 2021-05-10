import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
import './assets/css/global.css'
import './assets/fonts/iconfont.css'
import BlogList from './components/BlogList.vue'
import Editor from './components/Editor.vue'
import BlogInfo from './components/BlogInfo.vue'

Vue.config.productionTip = false
//axios.defaults.baseURL = "http://10.22.75.63:9000/"
//axios.defaults.baseURL = "http://39.108.158.47:9000/"
axios.defaults.baseURL = "http://10.22.77.37:9000/"
Vue.prototype.$http = axios

Vue.prototype.$checkLogin = (res,vue) => {
  if(res.data.meta.status != 200){
    vue.$message.error(res.data.meta.msg);
    window.sessionStorage.removeItem("token");
    vue.$router.push('/login');
    return false;
  }
  return true;
}

axios.interceptors.request.use(config => {
  // console.log(config)
  // 为请求头对象，添加token验证的Authorization字段
  config.headers.token = window.sessionStorage.getItem('token')
  // 在最后必须 return config
  return config
})
//全局注册组件
Vue.component('blog-list',BlogList)
Vue.component('wang-edt',Editor)
Vue.component('blog-info',BlogInfo)



new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
