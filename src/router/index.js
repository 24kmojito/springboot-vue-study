import Vue from 'vue'
import VueRouter from 'vue-router'
import Login1 from '../components/Login1.vue'
import Login2 from '../components/Login2.vue'
import Login3 from '../components/Login3.vue'
import Home1 from "../components/Home1";
import Home2 from "../components/Home2";
import MakeDangan from "../components/MakeDangan";
import MakeDangan1 from "../components/MakeDangan1";
import Dangan from "../components/Dangan";
import ViewDangan from "@/components/ViewDangan";
import ViewDangan1 from "@/components/ViewDangan1";
import EditDangan from "@/components/EditDangan";
import Loanapproval from "@/components/Loanapproval";
import Loanapproval1 from "@/components/Loanapproval1";
import ViewProject from "@/components/ViewProject";
import ViewProject1 from "@/components/ViewProject1";
import Register1 from "@/components/Register1";
import Register2 from "@/components/Register2";
import Home3 from "@/components/Home3";
import checkeconomic from "@/components/checkeconomic";
import economic from "@/components/economic";
import Leconomic from "@/components/Leconomic";
import shenheshensu from "@/components/shenheshensu";
import AddProject from "@/components/AddProject";
import addProject1 from "@/components/addproject1";
import addshensu from "@/components/addshensu";
import Denglu from "@/components/Denglu";
import Login4 from "@/components/Login4";
import main from "@/components/main";
import main1 from "@/components/main1";
import Home4 from "@/components/Home4";
import Dangan1 from "@/components/Dangan1";
Vue.use(VueRouter)



const router = new VueRouter({
  routes:[
    { path: '/',redirect:'/login1'},
    { path: '/login1', component:Login1 },
    { path: '/main', component:main },
    { path: '/main1', component:main1 },
    { path: '/login4', component:Login4 },
    { path: '/Denglu', component:Denglu },
    { path: '/leconomic', component:Leconomic },
    { path: '/register1', component:Register1 },
    { path: '/register2', component:Register2 },
    { path: '/loanapproval', component:Loanapproval },
    { path: '/loanapproval1', component:Loanapproval1 },
    { path: '/viewDangan', component:ViewDangan },
    { path: '/viewDangan1', component:ViewDangan1 },
    { path: '/viewproject', component:ViewProject },
    { path: '/viewproject1', component:ViewProject1 },
    { path: '/checkeconomic', component:checkeconomic },
    { path: '/editDangan', component:EditDangan },
    { path: '/economic', component:economic },
    { path: '/addproject', component:AddProject },
    { path: '/addproject1', component:addProject1 },
    { path: '/login2', component:Login2 },
    { path: '/login3', component:Login3 },
    { path: '/home1', component:Home1 },
    { path: '/home2', component:Home2 },
    { path: '/home3', component:Home3 },
    { path: '/home4', component:Home4 },
    { path: '/makeDangan', component:MakeDangan },
    { path: '/makeDangan1', component:MakeDangan1 },
    { path: '/Dangan', component:Dangan },
    { path: '/Dangan1', component:Dangan1 },
    { path: '/shenheshensu', component:shenheshensu},
    { path: '/addshensu', component:addshensu},
  ]
})

//挂载路由导航守卫
 router.beforeEach((to,from,next) => {
   //to将要访问的路径
   //from 代表从哪个路径跳转而来
   //next 是一个函数，表示放行
   //   next（） 放行   next（’‘）
   if(to.path ==='/login1') return next();
   if(to.path ==='/login2') return next();
   if(to.path ==='/main') return next();
   if(to.path ==='/main1') return next();
   if(to.path ==='/login3') return next();
   if(to.path ==='/login4') return next();
   if(to.path ==='/leconomic') return next();
   if(to.path ==='/register1') return next();
   if(to.path ==='/register2') return next();
   if(to.path ==='/economic') return next();
   if(to.path ==='/home1') return next();
   if(to.path ==='/home2') return next();
   if(to.path ==='/home3') return next();
   if(to.path ==='/home4') return next();
   if(to.path ==='/Dangan') return next();
   if(to.path ==='/Dangan1') return next();
   if(to.path ==='/makeDangan') return next();
   if(to.path ==='/makeDangan1') return next();
   if(to.path ==='/shenheshensu') return next();
   if(to.path ==='/addproject') return next();
   if(to.path ==='/addshensu') return next();
   if(to.path ==='/Denglu') return next();

      //获取token
   const tokenStr = window.sessionStorage.getItem('token')
   if(!tokenStr) return next('/login1')
   next()
 })


export default router
