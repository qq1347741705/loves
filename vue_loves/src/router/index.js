import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import BlogList from '../components/BlogList.vue'
import Editor from '../components/Editor.vue'
import Welcome from '../components/Welcome.vue'
import Photo from '../components/Photo.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/home', component: Home, redirect: '/welcome',
    children: [
      { path: '/release', component: Editor },
      { path: '/blogList', component: BlogList },
      { path: '/welcome', component: Welcome },
      { path: '/photo', component: Photo },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
