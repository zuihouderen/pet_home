import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/views/Login";
import Home from "@/views/Home";
import KnowledgeCenter from "@/views/KnowledgeCenter";
import Dynamic from "@/views/Dynamic";
import UserCenter from "@/views/UserCenter";
import PetCenter from "@/views/PetCenter";
import KnowledgeDetail from "@/views/KnowledgeDetail";
import DynamicDetail from "@/views/DynamicDetail";
Vue.use(Router)
export default new Router({
    routes:[

        {
            path:'/login',
            name:'登录',
            component:Login,
        },
        {
            path:'/home',
            alias:'/',
            // name:'首页',
            component:Home,
            children:[
                {
                    path:'',
                    redirect:'/knowledge'
                },
                {
                    path:'/knowledge',
                    name:'宠物知识',
                    component:KnowledgeCenter
                },
                {
                    path:'/knowledgedetail',
                    name:'宠物知识详情',
                    component:KnowledgeDetail
                },
                {
                    path:'/dynamic',
                    name:'用户动态',
                    component:Dynamic
                },
                {
                    path:'/dynamicdetail',
                    name:'动态详情',
                    component:DynamicDetail
                },
                {
                    path:'/usercenter',
                    name:'用户中心',
                    component:UserCenter,
                    // children:[
                    //     {
                    //         path:'/pet',alias:'',
                    //         name:'我的宠物',
                    //         component:PetCenter,
                    //     },
                    //     {
                    //         path:'/mykeep',
                    //         name:'我的关注',
                    //         component:MyKeep,
                    //     },
                    //     {
                    //         path:'/petcenter',
                    //         name:'我的关注',
                    //         component:MyKeep,
                    //     },
                    //
                    // ]
                },
                {
                    component:PetCenter,
                    name:'宠物中心',
                    path:'/petcenter'
                }

            ]
        },
    ]
})