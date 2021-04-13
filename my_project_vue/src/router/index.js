import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import("@/components/common/login")
    },
    {
      path: '/testpost',
      name: 'testpost',
      component: () => import("@/components/common/testpost")
    },
    {path: '/test', name:'test', component: () => import("@/components/test.vue")},
    {path: '/user/activities', name:'activities', component: () => import("@/components/user/activities")},
    {path: '/user/message', name:'message', component: () => import("@/components/user/message")},
    {path: '/user/regist', name:'regist', component: () => import("@/components/user/regist")},
    {path: '/user/activity_detail', name:'activity_detail', component: () => import("@/components/user/activity_details")},
    {path: '/user/userinfo', name:'userinfo', component: () => import("@/components/user/userinfo")},
    {path: '/user/vote_activity', name:'vote_activity', component: () => import("@/components/user/vote_activity")},
    {path: '/admin/activities',name:'activites', component: () => import("@/components/admin/activities")},
    {path: '/admin/add_activity',name:'add_activity', component: () => import("@/components/admin/add_activity")},
    {path: '/admin/user_manager',name:'user_manager', component: () => import("@/components/admin/user_manager")},
  ]
})
