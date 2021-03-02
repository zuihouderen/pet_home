<template>
    <div>
        <div>
            <a-list item-layout="vertical" size="large" :data-source="listData">
                <a-list-item slot="renderItem" key="item.pkid" slot-scope="item">
                    <img
                            slot="extra"
                            width="272"
                            alt="logo"
                            :src="item.coverurl"
                    />

                    <a-list-item-meta  >
                        <a slot="title" :herf="item.url">{{ item.petname}}</a>
                    </a-list-item-meta>
                    <div >
                        <a-row class="row">
                            <a-col :span="6"> <b>寿命：{{item.life}}</b></a-col>
                            <a-col :span="6"> <b>来自：{{item.nation}}</b></a-col>
                            <a-col :span="12"> <b>标签：{{item.characters}}</b></a-col>
                        </a-row>
                        <a-row class="row">
                            <a-col :span="12"> <b>易得病：{{item.easyofdisease}}</b></a-col>
                        </a-row>
                    </div>
                    <div>
                        <b>简介：{{item.feature}}</b>
                    </div>
                    <div><b>特征：{{item.characterfeature}}</b></div>
                    <div>  <b>喂养须知：{{item.careknowledge}}</b></div>
                    <b>喂养要点：{{item.feedpoints}}</b>
                </a-list-item>
            </a-list>
            <hr/>
            <div class="demo-infinite-container"
            :infinite-scroll-disabled="false"
            :infinite-scroll-distance="4">
            <a-list :data-source="quesList">
                <a-list-item slot="renderItem" slot-scope="item">
                    <a-list-item-meta :description="item.des">
                        <a slot="title" >用户{{item.uid}}</a>
                        <a-avatar
                                slot="avatar"
                                :src="item.uimg"
                        />
                    </a-list-item-meta>
                    <div style="padding-right: 5px">{{item.qtime}}</div>
                    <a-button type="danger" v-if="item.uid===uid" @click="deleteQues(item.qid)" >删除</a-button>
                </a-list-item>
            </a-list>
            </div>
            <hr/>
            <a-input-search
                    placeholder="请输入内容"
                    enter-button="Doubt"
                    size="large"
                    @search="doDoubt"
            />
        </div>
    </div>
</template>

<script>
    import axios from "@/utils/axios";
    import {getTime} from "@/utils/api"
    export default {
        data(){
            return{
                uid:0,
                uname:'',
                uimg:'',
                isAdmin:0,
                quesList:[],
                listData:[],
                pkid:""

            }
        },
        methods:{
            //1.提出疑问
            doDoubt(value){
                let time=getTime()
                value=   value.replace(/\s+/g,"");
                console.log(time)
                let data={
                     pkid:this.pkid, uid:this.uid, uimg:this.uimg, des:value, qtime:time
                }
                if(data.des===""){
                    alert("请输入有效信息")
                    return
                }
                axios.addques(data).then(res=>{
                    var result=res.data
                    let success=result.success
                    if(success===1){
                        console.log(result.question)
                        this.getques()
                    }else {
                        alert(result.message)
                    }
                }).catch(err=>{
                    console.log(err)
                })
            },
            //2.获取问题
            getques(){
                let data={"pkid":this.pkid}
                axios.getQues(data).then(res=>{
                    let result=res.data
                    this.quesList=result.questions
                    console.log(result)
                }).catch(err=>{
                    console.log(err)
                })
            },
            //3.删除疑问
            deleteQues(qid){
                let data={
                    qid:qid
                }
                axios.deleteques(data).then(res=>{
                    this.getques()
                    if(res.data.success===0){
                        alert(res.data.message)
                    }else {
                        this.$message.success("成功删除疑问")
                    }
                })
            }

        },
        mounted(){
            this.uid=this.$store.state.uid;
            this.uname=this.$store.state.uname;
            this.uimg=this.$store.state.uimg;
            this.isAdmin=this.$store.state.isAdmin;
            this.pkid=this.$route.query.pkid;
            let pkid= this.$route.query.pkid;
            console.log("pkid"+pkid)
            let data={"pkid":pkid}
            axios.getById(data).then(res=>{
                this.listData=res.data.petknowledges
            }).catch(err=>{
                console.log(err)
            })
            axios.getQues(data).then(res=>{
                let result=res.data
                this.quesList=result.questions
                console.log(result)
            }).catch(err=>{
                console.log(err)
            })


        }
    }
</script>

<style scoped>
    b{
        background-image: linear-gradient(135deg,green,gray);
        -webkit-background-clip:text;
        color: transparent;
    }
    .demo-infinite-container {
        border: 1px solid #e8e8e8;
        border-radius: 4px;
        overflow: auto;
        padding: 8px 24px;
        height: 130px;
    }
</style>