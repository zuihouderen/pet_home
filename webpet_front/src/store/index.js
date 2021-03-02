//导入自定义Vuex
import Vue from 'vue'
import Vuex  from 'vuex'
Vue.use(Vuex)

const store=new Vuex.Store({
    state:{
        uid:0,
        uname:"",
        uimg:"",
        isAdmin:0
    },
    mutations:{
        setuserinfo(state,userinfo){
            state.uid=userinfo.uid;
            state.uname=userinfo.uname;
            state.uimg=userinfo.uimg;
            state.isAdmin=userinfo.isAdmin;
        }
    }
})


export default store
