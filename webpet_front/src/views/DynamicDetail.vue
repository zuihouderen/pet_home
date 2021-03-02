<template>
    <div>
        <a-list item-layout="vertical" size="large"  :data-source="listData">
        <a-list-item slot="renderItem" key="item.pkid" slot-scope="item" >

            <template slot="actions">
                    <span v-if="iskeep" @click="handleKeep">
                       <a-icon type="star-o" style="margin-right: 8px;color: yellow" theme="filled"/>{{keepnum}}
                    </span>
                    <span v-else @click="handleKeep">
                        <a-icon type="star-o" style="margin-right: 8px;" />{{keepnum}}
                    </span>
                    <span v-if="islove" @click="handLove">
                       <a-icon type="like-o" style="margin-right: 8px;color: blue" theme="filled"/>{{lovenum}}
                    </span>
                    <span v-else  @click="handLove">
                        <a-icon type="like-o" style="margin-right: 8px;" />{{lovenum}}
                    </span>
                <span >
                       <a-icon type="message" style="margin-right: 8px" />{{comnum}}
                    </span>
            </template>
            <img
                    slot="extra"
                    width="272"
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
        <hr/>
        <div class="demo-infinite-container"
             :infinite-scroll-disabled="false"
             :infinite-scroll-distance="4">
        <a-list :data-source="commentList" item-layout="vertical">
            <a-list-item slot="renderItem" slot-scope="item"  >

                <a-list-item-meta :description="item.content"    @click="doGetSubComments(item.comid)">
                    <a slot="title" >{{item.comowner}}</a>
                    <a-avatar
                            slot="avatar"
                            :src="item.uimg"
                    />

                </a-list-item-meta>

                <div v-if="visible&&parent===item.comid" style="padding-left: 40px" slot="actions">
                    <a-list :data-source="subcomlist" item-layout="vertical" >
                        <a-list-item slot="renderItem" slot-scope="item"  @click="doGetSubComments(item.comid)">
                            <a-list-item-meta :description="item.content" style="font-size: large">
                                <a slot="title" >{{item.comowner}}</a>
                                <a-avatar
                                        slot="avatar"
                                        :src="item.uimg"
                                />
                            </a-list-item-meta>
                            <div slot="extra">
                                <div style="padding-right: 5px">{{item.comdate}}</div>
                                <div>
                                    <a-button type="danger" v-if="item.comowner===uname"  @click="doDeletCom(item.comid)">删除</a-button>
                                </div>
                            </div>
                        </a-list-item>
                    </a-list>
                </div>
                <div slot="extra">
                <div style="padding-right: 5px">{{item.comdate}}</div>
                    <div>
                <a-button type="danger" v-if="item.comowner===uname"  @click="doDeletCom(item.comid)">删除</a-button>
                    </div>
                </div>
            </a-list-item>
        </a-list>
        </div>
        <hr/>
        <a-input-search
                placeholder="input comment content"
                enter-button="COMMENT"
                size="large"
                @search="doComment"
        />
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
        <a-modal v-model="confirmVisble" :title=null  ok-text="确认" cancelText="取消" @ok="handleDelete">
            <b>你确认删除动态吗?</b>
        </a-modal>
    </div>
</template>

<script>
    import axios from "@/utils/axios";
    import {getTime} from "@/utils/api";
    import {token} from "@/utils/qiniuToken";
    import {domain} from "@/utils/domain";

    export default {
        data(){
            return{
                uid:0,
                uname:'',
                uimg:'',
                isAdmin:0,
                listData:[],
                iskeep:0,
                islove:0,
                lovenum:0,
                keepnum:0,
                comnum:0,
                parent:0,
                commentList:[],
                visible:false,
                subcomlist:[],
                postData:{
                    token:""
                },
                dialogVisible:false,
                pdimg:'',
                pdtitle:'',
                uform:this.$form.createForm(this),
                modalVisible:false,
                confirmVisble:false,
            }
        },
        methods:{
            //1.获取动态详情
            doGetDynamic(){
                let data={
                    pdtitle:this.pdtitle
                }
                axios.getDynamic(data).then(res=>{
                    this.listData=res.data.pdynamics
                    this.pdimg=this.listData[0].pdimg
                    console.log(this.listData)
                })
            },
            //2.获取关注数
            doGetKeepCount(){
                let data={
                    pdtitle:this.pdtitle
                }
                axios.getAllKeepCount(data).then(res=>{
                    this.keepnum=res.data.message
                })
            },
            //3.获取所有点赞数
            doGetLoveCount(){
                let data={
                    pdtitle:this.pdtitle
                }
                axios.getAllPdloves(data).then(res=>{
                    this.lovenum=res.data.message
                })
            },
            //4.是否点赞
            isLove(){
                let data={
                    pdtitle:this.pdtitle,
                    pdloveowner:this.uname
                }
                axios.isLoved(data).then(res=>{
                    this.islove=res.data
                    console.log(res.data)
                })
            },
            //5.是否收藏
            isKeep(){
                let data={
                    pdtitle:this.pdtitle,
                    pdkeeper:this.uname
                }
                axios.isKeeped(data).then(res=>{
                    this.iskeep=res.data
                    console.log(res.data)
                })
            },
            //6.获取所有评论数
            doGetAllComCount(){
                let data={
                    pdtitle:this.pdtitle
                }
                axios.getAllComCount(data).then(res=>{
                    this.comnum=res.data.message
                })
            },
            //7.收藏/取消收藏
            handleKeep(){
                //收藏了，取消收藏
                var keep=this.iskeep

                if (keep){
                    let data={
                        pdtitle:this.pdtitle,
                        pdkeeper:this.uname
                    }
                    axios.deletePdkeep(data).then(res=>{
                        if(res.data.success){
                            this.doGetKeepCount()
                            this.iskeep=0;
                            this.$message.info("你已经取消关注")
                        }
                    })
                }
                else {
                    let data={
                        pdtitle:this.pdtitle,
                        pdkeeper:this.uname,
                        pdkeepdata:getTime()
                    }
                    axios.addPdkeep(data).then(res=>{
                        if(res.data.success){
                            this.doGetKeepCount()
                            this.iskeep=1;
                            this.$message.info("你已经关注该动态")
                        }
                    })
                }
            },
            //8.点赞，取消点赞
            handLove(){
                //点赞了，取消点赞
                var love=this.islove
                let data={
                    pdtitle:this.pdtitle,
                    pdloveowner:this.uname
                }
                if (love){

                    axios.deletePdlove(data).then(res=>{
                        if(res.data.success){
                            this.doGetLoveCount()
                            this.islove=0;
                            this.$message.info("你已经取消点赞")
                        }
                    })
                }
                else {
                    axios.addPdlove(data).then(res=>{
                        if(res.data.success){
                            this.doGetLoveCount()
                            this.islove=1;
                            this.$message.info("你已经点赞该动态")
                        }
                    })
                }
            },
            //9.添加评论
            doComment(value){
                value=   value.replace(/\s+/g,"");
                var parent=this.parent
                let data={
                   pdtitle:this.pdtitle, comowner:this.uname, content:value, comdate:getTime(), uimg:this.uimg, parent:parent
                }
                if(value!==""){
                axios.addPdcomment(data).then(res=>{
                    if(res.data.success){
                            this.doGetAllComCount()
                            this.doGetSubComments(this.parent)
                            this.doGetAllComment()
                        this.$message.success("成功添加评论")
                            // this.parent=parent
                    }
                })}else {
                    alert("请输入有效内容")
                }
            },
            //10.获取所有评论
            doGetAllComment(){
                let data={
                    pdtitle:this.pdtitle
                }
                axios.getAllPdcomments(data).then(res=>{
                    this.commentList=res.data.pdcomments
                })
            },
            //11.删除所有评论
            doDeletCom(comid){
                let data={
                    comid:comid
                }
                axios.deletePdcomment(data).then(res=>{
                    if (res.data.success){
                        this.doGetSubComments(this.parent)
                        this.doGetAllComCount()
                        this.doGetAllComment()
                        this.$message.success("成功删除评论")
                    }
                })
            },
            //12.获取子评论
            doGetSubComments(comid){
                this.visible=!this.visible
                if(this.visible){
                this.parent=comid}
                else {
                    this.parent=0
                }
                console.log(this.visible+" "+this.parent)
                let data={
                    comid:comid
                }
                axios.getSubPdcomments(data).then(res=>{
                    if (res.data.success){
                        this.subcomlist=res.data.pdcomments
                        this.doGetAllComment()
                        this.doGetAllComCount()
                    }
                })

            },
            //13.删除动态
            doDeleteDynamic(pdtitle){
                this.pdtitle=pdtitle
                this.confirmVisble=true

            },
            handleDelete(){
                let data={
                    pdtitle:this.pdtitle,
                    pdtime:getTime()
                }
                axios.deletePdynamic(data).then(res=>{
                    if(res.data.success===1){
                        this.$router.replace("/dynamic")
                        this.$message.success("成功删除动态")
                    }
                    else {
                        alert(res.data.message)
                    }
                })
            },
            //14.更新动态
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
                                this.doGetDynamic()
                                this.modalVisible=false
                                this.$message.success("成功更新动态")
                            }else {
                                alert(result.message)
                            }
                        })
                    }
                })
            },
            //15.图片处理
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
        },
        mounted(){
           this.uid=this.$store.state.uid;
           this.uname=this.$store.state.uname;
           this.uimg=this.$store.state.uimg;
           this.isAdmin=this.$store.state.isAdmin;
           this.pdtitle=this.$route.query.pdtitle;
           this.doGetDynamic();
           this.doGetKeepCount();
           this.doGetLoveCount();
           this.isLove();
           this.isKeep();
           this.doGetAllComCount();
           this.doGetAllComment();
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
    .demo-infinite-container {
        border: 1px solid #e8e8e8;
        border-radius: 4px;
        overflow: auto;
        padding: 8px 24px;
        height: 160px;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
        margin: 0 auto;
    }
</style>