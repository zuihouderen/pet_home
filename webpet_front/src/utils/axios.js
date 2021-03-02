import Vue from 'vue'
import axios from 'axios'
import VueAxios from "vue-axios";

Vue.use(VueAxios,axios)

function post(url,data) {
    return axios({
        method: "POST",
        url: "http://localhost:8080/" + url,
        data: data,
    })
}

/**
 * 用户模块
 * @param data
 * @returns {AxiosPromise}
 */
//1.登录
function login(data) {
    return post("user/login", data);
}
//2.注册
function register(data) {
    return post("user/register",data);
}
//3.登出
// function logout(data) {
//     return post("user/logout",data);
// }
//4.更新个人信息
function updateInfo(data) {
    return post("user/updateInfo",data);
}
//5.添加宠物
function addPet(data) {
    return post("user/addPet",data);
}
//6.删除通知
function deleteMessage(data) {
    return post("user/deleteMessage",data);
}
//7.清空通知
function cleanMessage(data) {
    return post("user/cleanMessage",data);
}
//8.改变消息状态
function updateMessage(data) {
    return post("user/updateMessage",data);
}
//9.获取消息通知
function getMessages(data) {
    return post("user/getMessages",data);
}
//10.更新宠物信息
function updatePet(data) {
    return post("pet/updatePet",data);
}
//11.删除宠物
function deletePet(data) {
    return post("pet/deletePet",data)
}
//12.获取宠物列表
function getPets(data) {
    return post("pet/getPets",data);
}
//13.获取宠物信息
function getPet(data) {
    return post("pet/getPet",data);
}
//14.获取宠物日志
function getPlogs(data) {
    return post("pet/getPlogs",data);
}
//15.添加宠物日志
function addPetLog(data) {
    return post("pet/addPetLog",data);
}
//16.更新宠物日志
function updatePetLog(data) {
    return post("pet/updatePetLog",data);
}
//17.删除宠物日志
function deletePlog(data) {
    return post("pet/deletePlog",data);
}

/**
 * 个人动态模块
 * @param data
 * @returns {AxiosPromise}
 */
//18.是否是动态的发布者
function isOwner(data) {
    return post("dynamic/isOwner",data);
}
//19.获取所有动态列表
function getAllDynamics(data) {
    return post("dynamic/getAllDynamics",data);
}
//20.获取用户发布的动态
function getUserDynamics(data) {
    return post("dynamic/getUserDynamics",data);
}
//21.获取动态详情
function getDynamic(data) {
    return post("dynamic/getDynamic",data);
}
//22.发布动态
function addPdynamic(data) {
    return post("dynamic/addPdynamic",data);
}
//23.更新动态
function updatePdynamic(data) {
    return post("dynamic/updatePdynamic",data);
}
//24.删除动态
function deletePdynamic(data) {
    return post("dynamic/deletePdynamic",data);
}
//25.获取评论的数量
function getAllComCount(data) {
    return post("dynamic/getAllComCount",data);
}
//26.获取评论列表
function getAllPdcomments(data) {
    return post("dynamic/getAllPdcomments",data);
}
//27.增加评论
function addPdcomment(data) {
    return post("dynamic/addPdcomment",data);
}
//28.删除评论
function deletePdcomment(data) {
    return post("dynamic/deletePdcomment",data);
}
//29.是否点赞
function isLoved(data) {
    return post("dynamic/isLoved",data);
}
//30.获取所有点赞数
function getAllPdloves(data) {
    return post("dynamic/getAllPdloves",data);
}
//31.点赞
function addPdlove(data) {
    return post("dynamic/addPdlove",data);
}
//32.取消点赞
function deletePdlove(data) {
    return post("dynamic/deletePdlove",data);
}
//33.动态是否收藏
function isKeeped(data) {
    return post("dynamic/isKeeped",data);
}
//34.获取收藏数
function getAllKeepCount(data) {
    return post("dynamic/getAllKeepCount",data);
}
//35.获取收藏?？？？
function getAllPdkeeps(data) {
    return post("dynamic/getAllPdkeeps",data);
}
//36.获取个人收藏
function getMykeeps(data) {
    return post("dynamic/getMykeeps",data);
}
//37.添加收藏
function addPdkeep(data) {
    return post("dynamic/addPdkeep",data);
}
//38.删除收藏
function deletePdkeep(data) {
    return post("dynamic/deletePdkeep",data);
}

/**
 *宠物知识模块
 * @param data
 */
//39.添加宠物知识
function add(data) {
    return post("petknowledge/add",data);
}
//40.获取宠物知识
function getAll(data) {
    return post("petknowledge/getAll",data);
}
//41.根据类型获取宠物知识
function getAllByType(data) {
    return post("petknowledge/getAllByType",data);
}
//42.搜索名字获取宠物知识
function getAllByName(data) {
    return post("petknowledge/getAllByName",data);
}
//43.获取疑问
function getQues(data) {
    return post("petknowledge/getQues",data);
}
//44.提出疑问或者解答疑问
function addques(data) {
    return post("petknowledge/addques",data);
}
//45.判断是否是问题的提出者
function isowner(data) {
    return post("petknowledge/isowner",data);
}
//46.删除问题
function deleteques(data) {
    return post("petknowledge/deleteques",data);
}

//补充：47.获取子评论
function getSubPdcomments(data) {
    return post("dynamic/getSubPdcomments",data);
}
//48.获取知识详情
function getById(data) {
    return post("petknowledge/getById",data)
}
//49.查询动态
function getDynamicByName(data) {
    return post("dynamic/getDynamicByName",data)
}
//50.获取日志详情
function getPlog(data) {
    return post("pet/getPlog",data)
}
//51.获取用户信息
function getInfo(data) {
    return post("user/getInfo",data)
}
export default {
//个人模块:1~9
    login,
    register,
    // logout,
    updateInfo,
    addPet,
    deleteMessage,
    cleanMessage,
    updateMessage,
    getMessages,
//宠物模块:9~17
    updatePet,
    deletePet,
    getPets,
    getPet,
    getPlogs,
    addPetLog,
    updatePetLog,
    deletePlog,
//个人动态模块18~38
    //动态信息模块18~24
    isOwner,
    getAllDynamics,
    getUserDynamics,
    getDynamic,
    addPdynamic,
    updatePdynamic,
    deletePdynamic,
    //评论模块25~28
    getAllComCount,
    getAllPdcomments,
    addPdcomment,
    deletePdcomment,
    //点赞模块29~32
    isLoved,
    getAllPdloves,
    addPdlove,
    deletePdlove,
    //收藏模块33~38
    isKeeped,
    getAllKeepCount,
    getAllPdkeeps,
    getMykeeps,
    addPdkeep,
    deletePdkeep,
//宠物知识模块39~46
    add,
    getAll,
    getAllByType,
    getAllByName,
    getQues,
    addques,
    isowner,
    deleteques,
//补充
    getSubPdcomments,
    getById,
    getDynamicByName,
    getPlog,
    getInfo
}