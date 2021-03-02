
<template>
    <div>
        <div align="center">
            <a-input-search placeholder="input search text" enter-button @search="onSearch" style="width: 400px"/>
        </div>
        <div align="center" style="padding-top: 15px">
            <a-list :grid="{ gutter: 20, column: 4}" :data-source="typeList" >
                <a-list-item slot="renderItem" slot-scope="item" :key="item.key" @click="onFindByType(item.key)">
                    <a-avatar  :src="item.url"></a-avatar>
                    <div><b>{{item.typename}}</b></div>
                </a-list-item>
            </a-list>
        </div>
        <div  class="demo-infinite-container"
              :infinite-scroll-disabled="false"
              :infinite-scroll-distance="4">

            <a-list item-layout="vertical" size="large"  :data-source="listData">
                <a-list-item slot="renderItem" key="item.pkid" slot-scope="item">
                    <template v-for="{ type, text } in actions" slot="actions" >
                        <span :key="type" @click="toDetail(item.pkid)">
                          <a-icon :type="type" style="margin-right: 8px" />
                          {{ text }}
                        </span>
                    </template>
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
        </div>
    </div>
</template>
<script>

    import axios from "@/utils/axios";

    export default {
        data() {
            return {
                listData:[],
                typeList:[
                    {typename:'猫科',key:0,url:"http://qjisaamgy.hn-bkt.clouddn.com/Fk8AF4OzlZUVyZAxvZvTxuoOVA7t"},
                    {typename:'犬类',key:1,url:"http://qjisaamgy.hn-bkt.clouddn.com/FprF0pvQVf6M4ybJwoGG_5m91jZe"},
                    {typename:'水族类',key:4,url:"http://qjisaamgy.hn-bkt.clouddn.com/FuWU9ugqQKYEvX1aulYt-HS8_mOh"},
                    {typename:'小宠物类',key:3,url:"http://qjisaamgy.hn-bkt.clouddn.com/Fv9oDo_yc38LYpam7VbjQEqUHl7k"},
                ],
                pagination: {
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 3,
                },
                actions: [
                    // { type: 'star-o', text: '156' },
                    // { type: 'like-o', text: '156' },
                    { type: 'question', text: '提出疑问' },
                ],
            };
        },
        methods:{
            //1.按关键字查找
            onSearch(value){
                value=   value.replace(/\s+/g,"")
                let data={
                    "petname":value
                }

                axios.getAllByName(data).then(res=>{
                    var result=res.data.petknowledges
                    this.listData=result
                    console.log(data)
                    console.log(result)
                }).catch(err=>{
                    console.log(err)
                })
            },
            //2.按类型查找
            onFindByType(key){
                let data={
                    "pettype":key
                }
                axios.getAllByType(data).then(res=>{
                    var result=res.data.petknowledges
                    this.listData=result
                }).catch(err=>{
                    console.log(err)
                })
            },
            toDetail(pkid){
                this.$router.push({path:"/knowledgedetail",query:{pkid:pkid}})
                 // this.$router.replace("/knowledgedetail")
                console.log(pkid+"pkid")
            }

        },
        mounted(){
            //1.获取宠物知识列表
            let data=null
            axios.getAll(data).then(res=>{
                var result=res.data
                this.listData=result.petknowledges
            }).catch(err=>{
                console.log(err)
            })
        }

    };
</script>
<style scoped>
.row{
    padding-bottom: 10px;
}
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
    height: 460px;
}
</style>