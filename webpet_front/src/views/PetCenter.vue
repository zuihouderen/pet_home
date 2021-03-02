<template>
    <div>
        <a-row>
            <a-col span="8"><a-card hoverable  style="width: 300px">
                <img
                        slot="cover"
                        :src="petinfo.pimg"
                />
                <a-card-meta   style="font-size: large">
                    <a-avatar
                            slot="avatar"
                            size="large"
                            :src="uimg"
                    />
                    <b slot="title" style="font-size: x-large"></b>
                    <b slot="description" style="padding-top: 20px">拥有者:{{uname}}</b>
                </a-card-meta>
                <hr/>
                <div style="font-size:x-large ;text-align: center">
                    <b>{{petinfo.pname}}</b>
                </div>
                <div style="font-size: large">
                    <b >特点:{{petinfo.pdescription}}</b>
                </div>
                <div style="padding-top: 5px;font-size: large">
                    <b>类型:{{petinfo.ptype}}</b>

                </div>
                <div style="padding-top: 5px;font-size: large">
                    <b>月龄:{{petinfo.petage}}月</b>
                </div>
                <div style="padding-top: 5px;font-size: large">
                    <b v-if="petinfo.pgender">性别:直男</b>
                    <b v-else>性别:女士</b>
                </div>
            </a-card></a-col>
            <a-col span="16">
                <template>
                    <div style="padding-bottom: 10px">
                        <a-button @click="startAdd">添加日志</a-button>
                    </div>
                    <div
                            class="demo-infinite-container"
                            :infinite-scroll-disabled="false"
                            :infinite-scroll-distance="4"
                    >
                        <a-timeline mode="alternate" v-for="item in listData">
                            <a-timeline-item >
                                <a-icon type="tags" slot="dot"  style="font-size: 16px;" />
                                <b>日志标题：{{item.pltitle}}</b></a-timeline-item>
                            <a-timeline-item >
                                <a-icon slot="dot" type="clock-circle-o" style="font-size: 16px;" />
                                <b>日志时间：{{item.pldate}}</b></a-timeline-item>
                            <a-timeline-item >
                                <a-icon slot="dot" type="crown" style="font-size: 16px;" />
                                <b>日志拥有者：{{item.plowner}}</b></a-timeline-item>
                            <a-timeline-item >
                                <a-icon type="file-image" slot="dot"  style="font-size: 16px;"/>
                                <img
                                        :src="item.plimg"
                                        class="img"
                                />
                            </a-timeline-item>
                            <a-timeline-item>
                                <a-icon slot="dot" type="folder-open"  style="font-size: 16px;"/>
                                <b>{{item.pldescription}}</b>
                            </a-timeline-item>
                            <a-timeline-item color="green" style="cursor: pointer"  @click="startUpdata(item.pltitle)">
                                <a-icon slot="dot" type="edit"  style="font-size: 16px; cursor: pointer"/>
                                <b>编辑</b>
                            </a-timeline-item>
                            <a-timeline-item color="red" style="cursor: pointer" @click="startDelete(item.pltitle)">
                                <a-icon slot="dot" type="delete"  style="font-size: 16px; cursor: pointer"/>
                                <b style="color: red">删除</b>
                            </a-timeline-item>
                        </a-timeline>
                    </div>


            </template>
            </a-col>

        </a-row>
        <a-modal v-model="deleteVisible" :title=null  ok-text="确认" cancelText="取消" @ok="handleDelete()">
            <b>你确认删除日志吗?</b>
        </a-modal>
        <a-modal v-model="addVisible"  :footer="null" title="添加宠物日志" style="text-align: center;top:0">
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

                <img v-if="petlog.plimg" :src="petlog.plimg" class="avatar" />
                <div v-else >
                    <a-icon  type="plus" ></a-icon>
                </div>

            </a-upload>
            <a-form :form="aform"  @submit="handleAdd" >
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>日志名称</b></a-col>
                        <a-col span="18"><a-input
                                v-decorator="[ 'pltitle',
          { rules: [{ required: true, message: '请输入日志名称!' }] },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>日志描述</b></a-col>
                        <a-col span="18"><a-textarea  v-decorator="[ 'pldescription',
          { rules: [{ required: true, message: '请输入宠物特点!' }] },]"></a-textarea></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>日志时间</b></a-col>
                        <a-col span="18">
                        <a-date-picker
                                style="width: 354px"
                                v-decorator="['pldate', {rules: [{ type: 'object', required: true, message: '请选择时间' }]}]"
                                show-time
                                format="YYYY/MM/DD HH:mm:ss"
                        ></a-date-picker>
                        </a-col>
                    </a-row>
                </a-form-item>

                <a-button type="primary" html-type="submit" class="login-form-button" >
                    确认添加
                </a-button>

            </a-form>

        </a-modal>
        <a-modal v-model="updateVisible"  :footer="null" title="更新宠物日志" style="text-align: center;top:0">
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

                <img v-if="petlog.plimg" :src="petlog.plimg" class="avatar" />
                <div v-else >
                    <a-icon  type="plus" ></a-icon>
                </div>

            </a-upload>
            <a-form :form="uform"  @submit="handleUpdate" >
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>日志名称</b></a-col>
                        <a-col span="18"><a-input
                                :disabled="true"
                                v-decorator="[ 'pltitle',
          {initialValue:petlog.pltitle, rules: [{ required: true, message: '请输入日志名称!' }] },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>日志描述</b></a-col>
                        <a-col span="18"><a-textarea  v-decorator="[ 'pldescription',
          { initialValue:petlog.pldescription, rules: [{ required: true, message: '请输入宠物特点!' }] },]"></a-textarea></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>日志时间</b></a-col>
                        <a-col span="18">
                            <a-date-picker
                                    style="width: 354px"
                                    v-decorator="['pldate', {initialValue:petlog.pldate,rules: [{ type: 'object', required: true, message: '请选择时间' }]}]"
                                    show-time
                                    format="YYYY/MM/DD HH:mm:ss"
                            ></a-date-picker>
                        </a-col>
                    </a-row>
                </a-form-item>

                <a-button type="primary" html-type="submit" class="login-form-button" >
                    确认更新
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
            return{
                uid:0,
                uname:'',
                uimg:'',
                isAdmin:0,
                petlog:{
                    pltitle:'', plimg:'', pldate:'', pldescription:'', plowner:''
                },
                petinfo:{},
                postData:{
                    token:""
                },
                listData:[],
                addVisible:false,
                aform:this.$form.createForm(this),
                deleteVisible:false,
                updateVisible:false,
                uform:this.$form.createForm(this),

            }
        },
        methods:{
            //1.获取宠物信息
            doGetPet(){
                let data={
                    pname:this.petlog.plowner
                }
                axios.getPet(data).then(res=>{
                    if(res.data.success){
                        this.petinfo=res.data.pets[0]
                        console.log(this.petinfo)
                    }
                })
            },
            //2.获取所有日志
            doGetPetLogs(){
                let data={
                    pname:this.petlog.plowner
                }
                axios.getPlogs(data).then(res=>{
                    this.listData=res.data.plogs
                    console.log(this.listData)
                })
            },
            //3.添加宠物日志
            startAdd(){
                this.addVisible=true
            },
            handleAdd(e){
                e.preventDefault()
                this.aform.validateFields((errors, values) => {
                    if(!errors){
                        var date=values['pldate'].format('YYYY/MM/DD HH:mm:ss')
                        let data={
                            pltitle:values.pltitle, plimg:this.petlog.plimg, pldate:date, pldescription:values.pldescription, plowner:this.petinfo.pname
                        }
                        axios.addPetLog(data).then(res=>{
                            if(res.data.success){
                            this.doGetPetLogs()
                            this.addVisible=false
                                this.$message.success("成功添加宠物日志")
                            }
                            else {
                                alert(res.data.message)
                            }

                        })

                    }

                })

            },
            //4.图片上传
            handleAvatarSuccess(res) {
                if(res!=null){
                    this.petlog.plimg= domain + res.file.response.key;
                }
                console.log(this.pimg);
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
            //5.删除日志
            startDelete(pltitle){
                this.deleteVisible=true
                this.petlog.pltitle=pltitle
            },
            handleDelete(){
                let  data={
                    pltitle:  this.petlog.pltitle
                }
                axios.deletePlog(data).then(res=>{
                    if(res.data.success){
                        this.$message.success("成功删除宠物日志")
                        this.deleteVisible=false
                        this.doGetPetLogs()
                    }
                    else {
                        alert(res.data.message)
                    }


                })
            },
            //6.更新日志
            startUpdata(pltitle){
                this.updateVisible=true
                this.petlog.pltitle=pltitle
                let data={
                    pltitle:pltitle
                }
                axios.getPlog(data).then(res=>{
                    this.petlog=res.data.plogs[0]
                    console.log(this.petlog)

                })

            },
            handleUpdate(e){
                e.preventDefault()
                this.uform.validateFields((errors, values) => {
                    if(!errors){
                        var date=values['pldate'].format('YYYY/MM/DD HH:mm:ss')
                        let data={
                            pltitle:values.pltitle, plimg:this.petlog.plimg, pldate:date, pldescription:values.pldescription, plowner:this.petinfo.pname
                        }
                        axios.updatePetLog(data).then(res=>{
                            if(res.data.success){
                                this.doGetPetLogs()
                                this.updateVisible=false
                                this.$message.success("成功更新宠物日志")
                            }
                            else {
                                alert(res.data.message)
                            }

                        })

                    }

                })
            }



        },
        mounted(){
            this.uid=this.$store.state.uid;
            this.uname=this.$store.state.uname;
            this.uimg=this.$store.state.uimg;
            this.isAdmin=this.$store.state.isAdmin;
            this.petlog.plowner=this.$route.query.pname
            console.log( this.petlog.plowner)
            this.doGetPet();
            this.postData.token=token();
            this.doGetPetLogs();
        }
    }
</script>

<style scoped>
    b{
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
    .img {
        width: 178px;
        height: 178px;
        display: block;
        border-radius: 25px;
        margin: 0 auto;
    }
    .demo-infinite-container {
        border: 1px solid #e8e8e8;
        border-radius: 4px;
        overflow: auto;
        padding: 8px 24px;
        height: 500px;
    }
</style>