<template>
    <div class="bg" align="center" style="padding-top: 200px">

        <a-form
                id="components-form-demo-normal-login"
                :form="form"
                class="login-form"
                @submit="handleSubmit"
                style="width: 480px;background-color:whitesmoke;border-radius: 10px"

        ><div >
            <p style="font-size: 36px;margin-top: 10px;">
            系统登陆
            </p>
        </div>
            <a-form-item>
                <a-input
                        v-decorator="[
          'uname',
          { rules: [{ required: true, message: 'Please input your username!' }] },
        ]"
                        placeholder="Username"
                >
                    <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)" />
                </a-input>
            </a-form-item>
            <a-form-item>
                <a-input-password
                        v-decorator="[
          'password',
          { rules: [{ required: true, message: 'Please input your Password!' }] },
        ]"
                        type="password"
                        placeholder="Password"
                >
                    <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)" />
                </a-input-password>
            </a-form-item>
            <a-form-item>
                <a-button type="primary" html-type="submit" class="login-form-button">
                    登录
                </a-button>
            </a-form-item>
                <div align="left" style="padding-left: 20px;font-size: 20px" @click="doRegister"><a >注册</a></div>
        </a-form>
        <a-modal v-model="dialogVisible"  title="注册" :footer="null"
                 style="text-align: center"
        >
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
                <a-form class="register-form"  @submit="handleRegister" :form="registerForm">
                    <a-form-item >
                        <a-row>
                            <a-col span="6">用户名</a-col>
                            <a-col span="18"><a-input  v-decorator="[
          'uname',
          { rules: [{ required: true, message: 'Please input your username!' }] },
        ]"></a-input></a-col>
                        </a-row>
                    </a-form-item>
                    <a-form-item >
                        <a-row>
                            <a-col span="6">年龄</a-col>
                            <a-col span="18"><a-input type="number" v-decorator="[ 'uage',
          { rules: [{validator:checkNum}] },]"></a-input></a-col>
                        </a-row>
                    </a-form-item>
                    <a-form-item >
                        <a-row>
                            <a-col span="6">性别</a-col>
                            <a-col span="18">
                                <a-select @change="handleSelect"  default-value="男">
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
          { rules: [{ required: true, message: 'Please input your password!' }] },]"></a-input-password></a-col>
                        </a-row>
                    </a-form-item>
                    <a-button type="primary" html-type="submit" class="login-form-button">
                        注册
                    </a-button>

                </a-form>
        </a-modal>

    </div>
</template>

<script>
    import axios from "@/utils/axios";
    import {token} from "@/utils/qiniuToken";
    import {domain} from "@/utils/domain";

    export default {
        data(){
            return {
                form: this.$form.createForm(this),
                registerForm:this.$form.createForm(this),
                postData:{
                    token:""
                },
                dialogVisible:false,
                uimg:"",
                ugender:1,
                rules:[],
                options:[
                    {value:0,label:"女"},
                    {value:1,label:"男"},
                ]
            }
        },
        methods: {
            //1.登陆
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFields((err, values) => {
                    if (!err) {
                        console.log('Received values of form: ', values);
                        axios.login(values).then(res=>{
                            console.log(res.data)
                            var success=res.data.success
                            if (success==1){
                            var user=res.data.user
                            this.$store.commit("setuserinfo",user)

                            sessionStorage.setItem("store", JSON.stringify(this.$store.state));
                            this.$router.replace("/home")
                            }else {
                                alert(res.data.message)
                            }

                        }).catch(err=>{
                            console.log(err)
                        })
                    }
                });
            },
            //2.注册
            doRegister(){
                this.dialogVisible=true;
            },
            handleRegister(e) {
                e.preventDefault();
                this.registerForm.validateFields((err, values) => {
                    if (!err) {
                        console.log('Received values of form: ', values);
                        console.log(this.uimg)
                        console.log(this.ugender)
                        let data={
                            uimg:this.uimg,
                            ugender:this.ugender,
                            uname:values.uname,
                            uage:values.uage,
                            password:values.password
                        }
                        axios.register(data).then(res=>{
                            var result=res.data
                            let success=result.success
                            if(success===1){
                                this.$store.commit("setuserinfo",result.user)
                                sessionStorage.setItem("store", JSON.stringify(this.$store.state));
                                this.$router.replace("/home")
                            }
                            else {
                                alert(result.message)
                            }
                        })
                    }
                });
            },
            //3.图片上传
            handleAvatarSuccess(res) {
                if(res!=null){
                this.uimg = domain+ res.file.response.key;}
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
            //4.选择性别
            handleSelect(value){
               this.ugender=value;
            },
            //5.检测年龄
            checkNum(rule, value, callback) {
                if (value> 0) {
                    callback();
                    return;
                }
                callback("年龄必须大于0");
            },
        },
        mounted(){
            this.postData.token=token()
        }
    };


</script>

<style scoped>
    .bg {
        /* background: url(url); */
        height: 100%;
        background-image: url("../assets/bg.jpg");
        background-position: center center;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
        align-items: center;
        opacity: 0.9;
    }
    p{
        background-image: linear-gradient(135deg,red,blue);
        -webkit-background-clip:text;
        color: transparent;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
        margin: 0 auto;
    }
</style>