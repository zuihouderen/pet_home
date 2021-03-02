<template >
    <div>
        <a-layout id="components-layout-demo-fixed">
            <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%',display:'flex' }">
                <div style="margin-right: 20px">
                    <b>宠物家</b>
                </div>
                <a-menu
                        theme="dark"
                        mode="horizontal"
                        :default-selected-keys="['1']"
                        :style="{ lineHeight: '64px' }"

                >
                    <a-menu-item key="1"  >
                        <router-link to="/knowledge"> 宠物知识</router-link>
                    </a-menu-item>
                    <a-menu-item key="2">
                        <router-link to="/dynamic"> 用户动态</router-link>
                    </a-menu-item>
                    <a-menu-item key="3" v-if="uid">
                        <router-link to="/usercenter">个人中心</router-link>
                    </a-menu-item>
<!--                    <a-menu-item key="4" v-if="isAdmin">-->
<!--                        疑问中心-->
<!--                    </a-menu-item>-->
                </a-menu>
                <div style="position: absolute;right: 25px;cursor: pointer">
                    <div style="padding-top: 5px">
                    <a-badge
                            :count="messageCount"
                            show-zero
                            :number-style="{
                            backgroundColor:'#108ee9',
                           }"
                            @click="showMessage"
                    >
                    <a-dropdown >
                        <div style="display: flex;" >
                            <div>
                        <p v-if="uid" style="color: #42b983;font-size: 20px;padding-right: 5px">{{uname}}</p></div>
                            <div>
                        <a-avatar style="cursor: pointer" :src="uimg" ></a-avatar></div>
                        </div>
                        <a-menu slot="overlay">
                            <a-menu-item v-if="uid">
                                <a @click="startUpdate">编辑个人信息</a>
                            </a-menu-item>
                            <a-menu-item>
                                <a  @click="logout" v-if="uid">登出</a>
                            </a-menu-item>
                            <a-menu-item v-if="!uid">
                                <a @click="toLogin">登录</a>
                            </a-menu-item>
                        </a-menu>
                    </a-dropdown>
                    </a-badge>
                    </div>
                </div>
            </a-layout-header>
            <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
                <a-breadcrumb :style="{ margin: '16px 0' }">
                    <a-breadcrumb-item>首页</a-breadcrumb-item>
                    <a-breadcrumb-item>{{this.$route.name}}</a-breadcrumb-item>
                </a-breadcrumb>
                <div :style="{ background: '#fff', padding: '24px', minHeight: '480px' }">
                  <router-view/>
                </div>
            </a-layout-content>

        </a-layout>
        <a-drawer
                title="编辑个人信息"
                placement="right"
                :closable="false"
                width="480px"
                :visible="visible"
                @close="onclose"
        >
           <a-form  style="text-align: center">
               <a-upload style="display: flex;
  justify-content: center;
  align-items: center; "
                         list-type="picture-card"
                         action="http://upload-z2.qiniup.com"
                         :show-file-list="false"
                         :show-upload-list="false"
                         :data="postData"
                         :onautocomplete="true"
                         @change="handleAvatarSuccess"
                         :before-upload="beforeAvatarUpload">

                   <img v-if="uimg" :src="uimg" class="avatar" />
                   <div v-else >
                       <a-icon  type="plus" ></a-icon>
                   </div>

               </a-upload>
               <a-form   @submit="handleUpdate" :form="uform">
                   <a-form-item >
                       <a-row>
                           <a-col span="6">用户名</a-col>
                           <a-col span="18"><a-input :disabled="true" v-decorator="[
          'uname',
          { initialValue:uname,rules: [{ required: true, message: '请选择用户名' }] },
        ]"></a-input></a-col>
                       </a-row>
                   </a-form-item>
                   <a-form-item >
                       <a-row>
                           <a-col span="6">年龄</a-col>
                           <a-col span="18"><a-input type="number" v-decorator="[ 'uage',
          {initialValue:uage,rules: [{validator:checkNum}] },]"></a-input></a-col>
                       </a-row>
                   </a-form-item>
                   <a-form-item >
                       <a-row>
                           <a-col span="6">性别</a-col>
                           <a-col span="18">
                               <a-select   v-decorator="[ 'ugender',
          {rules: [{ required: true, message: '请选择性别' }] },]">
                                   <a-select-option value="1">男</a-select-option>
                                   <a-select-option value="0">女</a-select-option>
                               </a-select>
                           </a-col>
                       </a-row>
                   </a-form-item>
                   <a-form-item >
                       <a-row>
                           <a-col span="6">密码</a-col>
                           <a-col span="18"><a-input-password type="password" v-decorator="[ 'password',
          { initialValue:password,rules: [{ required: true, message: '请输入密码' }] },]"></a-input-password></a-col>
                       </a-row>
                   </a-form-item>
                   <a-button type="primary" html-type="submit" class="login-form-button" >
                       更新个人信息
                   </a-button>

               </a-form>
           </a-form>
        </a-drawer>
        <a-drawer
                title="系统信息"
                placement="right"
                :closable="false"
                width="560px"
                :visible="messageVisible"
                @close="onclose"
        >
                <a-list
                        item-layout="horizontal"
                        :data-source="messages"
                        :pagination="pagination"
                >
                    <a-list-item slot="renderItem" slot-scope="item">
                        <a slot="actions"  @click="viewDetail(item)">查看</a>
                        <a slot="actions" @click="deleteMessage(item.mid)">删除</a>
                        <a-list-item-meta
                        >
                            <a-avatar
                                    :src="uimg"
                                    slot="avatar"
                            />
                            <a slot="title" >{{ item.msgtitle }}</a>
                            <div v-if="!item.msgstate" slot="description">未查看</div>
                        </a-list-item-meta>

                        <div>{{item.mdate}}</div>
                    </a-list-item>
                </a-list>
            <hr/>
            <div align="center"><a-button type="danger" @click="cleanMessage">清空消息</a-button></div>

        </a-drawer>
        <a-modal v-model="detailVisible" @ok="detailVisible=false" ok-text="确认" cancel-text="取消">
            <div style="text-align: center">
                <b>{{msg.msgtitle}}</b>
            </div>
            <div style="margin-top: 10px">
                <p>{{msg.msg}}</p>
            </div>
            <div>
                <span>{{msg.mdate}}</span>
            </div>
        </a-modal>

    </div>
</template>
<script>

    import axios from "@/utils/axios";
    import {token} from "@/utils/qiniuToken";
    import {domain} from "@/utils/domain";

    export  default {
        name:'Home',
        data(){
            return{
                uid:0,
                uname:'',
                uimg:'',
                isAdmin:0,
                uage:0,
                password:'',
                ugender:0,
                visible:false,
                options:[
                    {value:0,label:"女"},
                    {value:1,label:"男"},
                ],
                postData:{
                    token:""
                },
                uform:this.$form.createForm(this),
                messageCount:0,
                messageVisible:false,
                messages:[],
                detailVisible:false,
                msg:{
                    mid:0, msgtitle:'', mdate:'', msg:'', msgstate:0, reciptant:this.uname
                },
                pagination: {
                    pageSize: 5,
                },

            };
        },
        methods:{
            //1.登出,Vuex信息清空
          logout(){
              let data={
                  uid:0,
                  uname:'',
                  uimg:'',
                  isAdmin:0,

              }
              this.$store.commit("setuserinfo",data)
              this.uid=this.$store.state.uid;
              this.uname=this.$store.state.uname;
              this.uimg=this.$store.state.uimg;
              this.isAdmin=this.$store.state.isAdmin;
              if (sessionStorage!=null){
                  sessionStorage.clear()
              }
              console.log("清空用户信息")
              if(this.$route.path==="/dynamicdetail") {
                  this.$router.replace("/dynamic")
              }else {
                  if(this.$route.path==="/dynamic"){
                      return;
                  }
                  if(this.$route.path==="/knowledge"){
                      return;
                  }
                  this.$router.replace("/knowledge")
              }
          },
            //2.去到登录界面
           toLogin(){
              this.$router.push("/login")
           },
            //3.更新个人信息
            startUpdate(){
                let data={
                    uid:this.uid
                }
                axios.getInfo(data).then(res=>{
                    let result=res.data.user
                    this.ugender=result.ugender
                    this.uage=result.uage
                    this.password=result.password
                    this.visible=true
                })
            },
            handleUpdate(e){
                e.preventDefault()
                this.uform.validateFields((errors, values) => {
                    if(!errors){
                        let data={
                            uid:this.uid,
                            uimg:this.uimg,
                            ugender:values.ugender,
                            uname:values.uname,
                            uage:values.uage,
                            password:values.password
                        }
                        console.log(data)
                        axios.updateInfo(data).then(res=>{
                            var result=res.data
                            let success=result.success
                            if(success===1){
                                this.$store.commit("setuserinfo",result.user)
                                sessionStorage.setItem("store", JSON.stringify(this.$store.state));
                                this.$message.success("修改个人信息成功")
                                this.visible=false
                            }
                            else {
                                alert(result.message)
                            }
                        })
                    }
                })
            },
            onclose(){
                this.visible=false
                this.messageVisible=false
            },
            //4.图片上传
            handleAvatarSuccess(res) {
                if(res!=null){
                    this.uimg = domain + res.file.response.key;}
                console.log(this.uimg);
                // this.EncodedEntryURI = res.key;
            },
            beforeAvatarUpload(file) {
                const isIMG = ["image/jpeg", "image/png", "image/gif"].includes(
                    file.type
                );
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isIMG) {
                    this.$message.error("上传的头像必须是图片格式!");
                }
                if (!isLt2M) {
                    this.$message.error("上传头像图片大小不能超过 2MB!");
                }
                return isIMG && isLt2M;
            },

            //5.检测年龄
            checkNum(rule, value, callback) {
                if (value> 0) {
                    callback();
                    return;
                }
                callback("年龄必须大于0");
            },
            //6.显示消息
            showMessage(){
                let data={
                    uname:this.uname
                }

                axios.getMessages(data).then(res=>{
                    this.messages=res.data.messages
                    this.messageVisible=true
                    var i=0;
                    this.messageCount=0;
                    for(;i<this.messages.length;i++){
                        if(!this.messages[i].msgstate){
                            this.messageCount++
                        }
                    }
                })

            },
            //7.删除消息
            deleteMessage(mid){
              let data={
                  mid:mid
              }
              axios.deleteMessage(data).then(res=>{
                  if(!res.data.success){
                      alert(res.data.message)
                  }else {
                      this.$message.success("删除系统消息成功")
                  }

              }).finally(()=>{
                  this.doGetMessageCount()
              })
            },
            //8.获取未读消息的数量
            doGetMessageCount(){
                let data={
                    uname:this.uname
                }

                axios.getMessages(data).then(res=>{
                    this.messages=res.data.messages
                    var i=0;
                    this.messageCount=0;
                    for(;i<this.messages.length;i++){
                        if(!this.messages[i].msgstate){
                            this.messageCount++
                        }
                    }
                })
            },
            //9.查看消息详情
            viewDetail(item){
                this.detailVisible=true;
                this.msg=item
                this.msg.msgstate=1;
                axios.updateMessage(this.msg).then(res=>{
                    if(!res.data.success){
                        alert(res.data.message)
                    }
                    this.doGetMessageCount()

                })

            },
            //10清空消息
            cleanMessage(){
              let data={
                  reciptant: this.uname
              }
              axios.cleanMessage(data).then(res=>{
                  if(res.data.success){
                      this.doGetMessageCount()
                      this.messageVisible=false
                      this.$message.success("清空系统系统消息成功")
                  }
                  else {
                      alert(res.data.message)
                  }
              })
            }
        },

        mounted(){
            this.uid=this.$store.state.uid;
            this.uname=this.$store.state.uname;
            this.uimg=this.$store.state.uimg;
            this.isAdmin=this.$store.state.isAdmin;
            this.postData.token=token()
            this.doGetMessageCount()
            setInterval(this.doGetMessageCount,3000)
            console.log(this.uid)
        },

    }
</script>
<style scoped>
    #components-layout-demo-fixed .logo {
        width: 120px;
        height: 31px;
        background: rgba(255, 255, 255, 0.2);
        /*background-image: url("../assets/bg.jpg");*/
        margin: 16px 24px 16px 0;
        float: left;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
        margin: 0 auto;
    }
    b{
        background-image: linear-gradient(135deg,red,blue);
        -webkit-background-clip:text;
        color: transparent;
        font-size: 25px;
        margin-bottom: 20px;
    }
    a{
        background-image: linear-gradient(135deg,red,blue);
        -webkit-background-clip:text;
        color: transparent;
    }
    p{
        background-image: linear-gradient(135deg,blue,gray);
        -webkit-background-clip:text;
        color: transparent;
    }

</style>
