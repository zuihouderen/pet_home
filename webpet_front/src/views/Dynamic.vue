<template>
    <div >
        <div align="center" style="margin-bottom: 20px">
            <a-input-search placeholder="请输入标题名字" enter-button @search="onSearch" style="width: 440px" />
        </div>
        <div align="center" style="margin-bottom: 10px">
            <a-row >
                <a-col span="6"/>
                <a-col span="3">
                    <a-button v-if="this.uid!==0"  @click="doGetAllDynamics">所有动态</a-button>
                </a-col>
                <a-col span="3">
                    <a-button v-if="this.uid!==0" @click="showModal" >发布动态</a-button>
                </a-col>
                <a-col span="3">
                    <a-button v-if="this.uid!==0" @click="doGetMyDynamic" >我的动态</a-button>
                </a-col>
                <a-col span="3">
                    <a-button v-if="this.uid!==0" @click="doGetMyKeep" >我的关注</a-button>
                </a-col>
                <a-col span="6"/>
            </a-row>
        </div>
        <a-modal v-model="dialogVisible"  title="发布动态" :footer="null"
                 style="text-align: center;">
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

                <img v-if="pdimg" :src="pdimg" class="avatar" />
                <div v-else >
                    <a-icon  type="plus" ></a-icon>
                </div>

            </a-upload>
            <a-form :form="pform" @submit="handlePubulish">
                <a-form-item >
                    <a-row>
                        <a-col span="6">动态标题</a-col>
                        <a-col span="18"><a-input  v-decorator="[
          'pdtitle',
          { rules: [{ required: true, message: '请输入动态标题' }] },
        ]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6">内容</a-col>
                        <a-col span="18"><a-textarea  v-decorator="[ 'bewrite',
          { rules: [{ required: true, message: '请输入动态内容!' }] },]"></a-textarea></a-col>
                    </a-row>
                </a-form-item>


                <a-button type="primary" html-type="submit" >
                    发布
                </a-button>

            </a-form>
        </a-modal>
        <div class="demo-infinite-container"
             :infinite-scroll-disabled="false"
             :infinite-scroll-distance="4">
        <a-list item-layout="vertical" size="large"  :data-source="listData">
            <a-list-item slot="renderItem" key="item.pkid" slot-scope="item" >
                <template slot="actions">

                    <span @click="toDetail(item.pdtitle)">
                       <a-icon type="message" style="margin-right: 8px;"/>参与评论
                    </span>

                </template>
                <img
                        slot="extra"
                        width="272"
                        height="272"
                        alt="logo"
                        :src="item.pdimg"
                />
                <div style="display: flex;">
                    <a-avatar :src="item.uimg" size="large"></a-avatar>
                    <p style="font-size: xx-large">{{item.pdowner}}</p>
                    <div  v-if="item.pdowner===uname">
                        <a-button type="primary" style="margin-left: 25px;margin-top: 10px" @click="doUpdateDynamic(item.pdtitle)">更新动态</a-button>
                        <a-button type="danger" style="margin-left: 25px;margin-top: 10px" @click="doDeleteDynamic(item.pdtitle)">删除动态</a-button>
                    </div>
                </div>
                <a-list-item-meta  >
                    <b slot="title"  style="font-size: x-large">{{ item.pdtitle}}</b>
                </a-list-item-meta>
                <a-list-item>
                    <b>内容：{{item.bewrite}}</b>
                </a-list-item>
                <a-list-item>
                    <b>{{item.pdtime}}</b>
                </a-list-item>

            </a-list-item>
        </a-list>
        </div>
        <a-modal v-model="modalVisible"  title="更新动态" :footer="null"
                 style="text-align: center;">
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

                <img v-if="pdimg" :src="pdimg" class="avatar" />
                <div v-else >
                    <a-icon  type="plus" ></a-icon>
                </div>

            </a-upload>
            <a-form :form="uform" @submit="handleUpdate">
                <a-form-item >
                    <a-row>
                        <a-col span="6">动态标题</a-col>
                        <a-col span="18"><a-input :disabled="true" :value="pdtitle"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6">内容</a-col>
                        <a-col span="18"><a-textarea  v-decorator="[ 'bewrite',
          { rules: [{ required: true, message: '请输入动态内容!' }] },]"></a-textarea></a-col>
                    </a-row>
                </a-form-item>


                <a-button type="primary" html-type="submit" >
                    确认更新
                </a-button>

            </a-form>
        </a-modal>
        <a-modal v-model="visible" :title=null  ok-text="确认" cancelText="取消" @ok="handleDelete">
            <b>你确认删除动态吗?</b>
        </a-modal>
    </div>
</template>

<script>
    import axios from "@/utils/axios";
    import {token} from "@/utils/qiniuToken";
    import {getTime} from "@/utils/api";
    import {domain} from "@/utils/domain";

    export default {
        data(){
            return{
                uid:0,
                uname:'',
                uimg:'',
                isAdmin:0,
                listData:[],
                pagination: {
                    pageSize: 2,
                },
                actions: [
                    // { type: 'star-o', text: '156' },
                    // { type: 'like-o', text: '156' },
                    // { type: 'message', text: '0' },
                ],
                postData:{
                    token:""
                },
                dialogVisible:false,
                pdimg:'',
                pdtitle:'',
                pform:this.$form.createForm(this),
                uform:this.$form.createForm(this),
                modalVisible:false,
                visible:false,
            }
        },
        methods:{
            //1.获取所有动态
            doGetAllDynamics(){
                axios.getAllDynamics(null).then(res=>{
                    let result=res.data
                    if(result.success===1){
                        this.listData=result.pdynamics
                        console.log(this.listData)
                        console.log(result.message)
                    }else{
                        alert(result.message)
                    }
                }).catch(err=>{
                    console.log(err)
                })
            },
            // //2.获取所有评论数目
            // doGetAllCommentCount(){
            //     let data=
            //     axios.getAllComCount()
            // },
            //3.图片上传
            handleAvatarSuccess(res) {
                if(res!=null){
                    this.pdimg = domain+ res.file.response.key;}
                console.log(this.pdimg);
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
            //4.发布动态
            showModal() {
                this.dialogVisible=true;
            },
            handlePubulish(e){
                e.preventDefault()
                this.pform.validateFields((errors, values) => {
                    if(!errors){
                        let data={
                             pdtitle:values.pdtitle, pdimg:this.pdimg, uimg:this.uimg, bewrite:values.bewrite, pdowner:this.uname, pdtime:getTime()
                        }
                        axios.addPdynamic(data).then(res=>{
                            let result=res.data
                            if(result.success===1){
                                this.doGetAllDynamics()
                                this.dialogVisible=false
                                this.$message.success("成功发布动态")
                            }else {
                                alert(result.message)
                            }
                        })
                    }
                })

            },
            //5.删除动态
            doDeleteDynamic(pdtitle){
                this.pdtitle=pdtitle
                this.visible=true

            },
            handleDelete(){
                let data={
                    pdtitle:this.pdtitle,
                    pdtime:getTime()
                }
                axios.deletePdynamic(data).then(res=>{
                    if(res.data.success===1){
                        this.visible=false
                        this.doGetAllDynamics()
                    }
                    else {
                        alert(res.data.message)
                    }
                })
            },
            //6.更新动态
            doUpdateDynamic(pdtitle){

                let data={
                    pdtitle:pdtitle
                }
                axios.getDynamic(data).then(res=>{
                    console.log(res.data)
                    let data=res.data.pdynamics[0]
                    console.log(data)
                    this.pdimg=data.pdimg
                    this.pdtitle=data.pdtitle
                    this.modalVisible=true;
                })
            },
            handleUpdate(e){
                e.preventDefault()
                this.uform.validateFields((errors, values) => {
                    if(!errors){
                        let data={
                            pdtitle:this.pdtitle, pdimg:this.pdimg, uimg:this.uimg, bewrite:values.bewrite, pdowner:this.uname, pdtime:getTime()
                        }
                        axios.updatePdynamic(data).then(res=>{
                            let result=res.data
                            if(result.success===1){
                                this.doGetAllDynamics()
                                this.modalVisible=false
                                this.$message.success("成功更新动态")
                            }else {
                                alert(result.message)
                            }
                        })
                    }
                })
            },
            //7.跳转详情页
            toDetail(pdtitle){
                this.$router.push({path:'/dynamicdetail',query:{pdtitle:pdtitle}})
            },
            //8.获取我的动态
            doGetMyDynamic(){
                let data={
                    uname:this.uname
                }
                axios.getUserDynamics(data).then(res=>{
                    this.listData=res.data.pdynamics

                })
            },
            //8.查询动态
            onSearch(value){
                value=   value.replace(/\s+/g,"")
                let data={
                    pdtitle:value
                }
                axios.getDynamicByName(data).then(res=>{
                    this.listData=res.data.pdynamics
                })

            },
            //9.获取我的关注
            doGetMyKeep(){
                let data={
                    uname:this.uname
                }
                var dataList=[]
                axios.getMykeeps(data).then(res=>{
                    //console.log(res.data)
                    dataList=res.data.pdkeeps
                    console.log(dataList)

                }).finally(f=>{
                    console.log(dataList)
                    this.listData=[]
                    var i=0
                    for( ;i<dataList.length;i++){
                        let  varData={
                            pdtitle:dataList[i].pdtitle
                        }
                        axios.getDynamic(varData).then(res=>{
                            console.log(res.data.pdynamics)
                            this.listData.push(res.data.pdynamics[0])
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

            this.doGetAllDynamics()
            this.postData.token=token()
        }
    }
</script>

<style scoped>
    b{
        background-image: linear-gradient(135deg,blue,gray);
        -webkit-background-clip:text;
        color: transparent;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
        margin: 0 auto;
    }
    .demo-infinite-container {
        border: 1px solid #e8e8e8;
        border-radius: 4px;
        overflow: auto;
        padding: 8px 24px;
        height: 480px;
    }
</style>