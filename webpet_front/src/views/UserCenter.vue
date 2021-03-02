<template>
    <div>
        <div>
            <a-row>
                <a-col span="22"></a-col>
                <a-col span="2">
                    <a-button @click="startAdd" type="primary">添加宠物</a-button>
                </a-col>
            </a-row>
        </div>
        <template>
            <a-list :grid="{ gutter: 48, xs: 1, sm: 2, md: 4, lg: 4, xl: 4, xxl: 3 }" :data-source="petList" >
                <a-list-item slot="renderItem" slot-scope="item">
                    <a-card hoverable>
                        <img
                                slot="cover"
                                :src="item.pimg"
                        />
                        <template slot="actions" class="ant-card-actions">
                            <a-icon key="delete" type="delete"  @click="startDelete(item.pname)"/>
                            <a-icon key="edit" type="edit" @click="startUpdate(item.pname)"/>
                            <a-icon key="ellipsis" type="ellipsis" @click="toDetail(item.pname)"/>
                        </template>
                        <a-card-meta   style="font-size: large">
                            <a-avatar
                                    slot="avatar"
                                    size="large"
                                    :src="uimg"
                            />
                            <b slot="description" style="font-size: x-large">{{item.pname}}</b>
                            <b slot="title"></b>
                        </a-card-meta>
                        <hr/>
                        <div style="padding-top: 5px;font-size: large">
                            <b>特点：{{item.pdescription}}</b>
                        </div>
                        <div style="padding-top: 5px;font-size: large">
                            <b>类型：{{item.ptype}}</b>
                        </div>
                        <div style="padding-top: 5px;font-size: large">
                            <b>月龄:{{item.petage}}月</b>
                        </div>
                        <div style="padding-top: 5px;font-size: large">
                            <b v-if="item.pgender">性别:直男</b>
                            <b v-else>性别:女士</b>
                        </div>
                    </a-card>
                </a-list-item>
            </a-list>
        </template>
        <a-modal v-model="visible" :title=null  ok-text="确认" cancelText="取消" @ok="handleDelete">
            <b>你确认删除宠物信息吗?</b>
        </a-modal>
        <a-modal v-model="modalVisible"  :footer="null" title="更新宠物信息" style="text-align: center;top:0">
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

                <img v-if="form.pimg" :src="form.pimg" class="avatar" />
                <div v-else >
                    <a-icon  type="plus" ></a-icon>
                </div>

            </a-upload>
            <a-form :form="uform"  @submit="handleUpdate" >
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>宠物名字</b></a-col>
                        <a-col span="18"><a-input :disabled="true"
                                                  v-decorator="[ 'pname',
          { initialValue:form.pname,rules: [{ required: true, message: '请输入宠物标题!' }] },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>特点</b></a-col>
                        <a-col span="18"><a-input  v-decorator="[ 'pdescription',
          { initialValue:form.pdescription,rules: [{ required: true, message: '请输入宠物特点!' }] },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>宠物类型</b></a-col>
                        <a-col span="18">
                            <a-select  v-decorator="[ 'ptype',
          { initialValue:form.ptype,rules: [{ required: true, message: '请输入宠物类型!' }] },]">
                                <a-select-option value="哈士奇">哈士奇</a-select-option>
                                <a-select-option value="阿拉斯加犬">阿拉斯加犬</a-select-option>
                                <a-select-option value="泰迪">泰迪</a-select-option>
                                <a-select-option value="指猴">指猴</a-select-option>
                                <a-select-option value="比熊犬">比熊犬</a-select-option>
                                <a-select-option value="宝莲灯鱼">宝莲灯鱼</a-select-option>
                                <a-select-option value="泰卡尔特猫">卡尔特猫</a-select-option>
                                <a-select-option value="埃及猫">埃及猫</a-select-option>
                                <a-select-option value="伯曼猫">伯曼猫</a-select-option>
                                <a-select-option value="安哥拉兔">安哥拉兔</a-select-option>
                                <a-select-option value="其他">其他</a-select-option>
                            </a-select>
                        </a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>性别</b></a-col>
                        <a-col span="18">
                            <a-select  v-decorator="[ 'pgender',
          { rules: [{ required: true, message: '请输入宠物性别!' }] },]">
                                <a-select-option value="1">直男</a-select-option>
                                <a-select-option value="0">女士</a-select-option>
                            </a-select>
                        </a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>月龄</b></a-col>
                        <a-col span="18"><a-input type="number"  v-decorator="[ 'petage',
          { initialValue:form.petage, rules: [{ validator: checkNum }], },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-button type="primary" html-type="submit" class="login-form-button" >
                    确认更新
                </a-button>

            </a-form>

        </a-modal>
        <a-modal v-model="addVisible"  :footer="null" title="添加宠物" style="text-align: center;top:0">
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

                <img v-if="form.pimg" :src="form.pimg" class="avatar" />
                <div v-else >
                    <a-icon  type="plus" ></a-icon>
                </div>

            </a-upload>
            <a-form :form="aform"  @submit="handleAdd" >
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>宠物名字</b></a-col>
                        <a-col span="18"><a-input
                                                  v-decorator="[ 'pname',
          { initialValue:form.pname,rules: [{ required: true, message: '请输入宠物标题!' }] },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>特点</b></a-col>
                        <a-col span="18"><a-input  v-decorator="[ 'pdescription',
          { initialValue:form.pdescription,rules: [{ required: true, message: '请输入宠物特点!' }] },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>宠物类型</b></a-col>
                        <a-col span="18">
                            <a-select  v-decorator="[ 'ptype',
          { initialValue:form.ptype,rules: [{ required: true, message: '请输入宠物类型!' }] },]">
                                <a-select-option value="哈士奇">哈士奇</a-select-option>
                                <a-select-option value="阿拉斯加犬">阿拉斯加犬</a-select-option>
                                <a-select-option value="泰迪">泰迪</a-select-option>
                                <a-select-option value="指猴">指猴</a-select-option>
                                <a-select-option value="比熊犬">比熊犬</a-select-option>
                                <a-select-option value="宝莲灯鱼">宝莲灯鱼</a-select-option>
                                <a-select-option value="泰卡尔特猫">卡尔特猫</a-select-option>
                                <a-select-option value="埃及猫">埃及猫</a-select-option>
                                <a-select-option value="伯曼猫">伯曼猫</a-select-option>
                                <a-select-option value="安哥拉兔">安哥拉兔</a-select-option>
                                <a-select-option value="其他">其他</a-select-option>
                            </a-select>
                        </a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>性别</b></a-col>
                        <a-col span="18">
                            <a-select  v-decorator="[ 'pgender',
          { rules: [{ required: true, message: '请输入宠物性别!' }] },]">
                                <a-select-option value="1">直男</a-select-option>
                                <a-select-option value="0">女士</a-select-option>
                            </a-select>
                        </a-col>
                    </a-row>
                </a-form-item>
                <a-form-item >
                    <a-row>
                        <a-col span="6"><b>月龄</b></a-col>
                        <a-col span="18"><a-input type="number"  v-decorator="[ 'petage',
          { initialValue:form.petage, rules: [{ validator: checkNum }], },]"></a-input></a-col>
                    </a-row>
                </a-form-item>
                <a-button type="primary" html-type="submit" class="login-form-button" >
                    确认添加
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
                petList:[],
                visible:false,
                pname:'',
                pimg:'',
                addVisible:false,
                modalVisible:false,
                postData:{
                    token:""
                },
                form:{
                    pid:0,  phost:'',
                    pname:'',  petage:0, pdescription:'', pgender:'', pimg:'', ptype:''
                },
                uform:this.$form.createForm(this),
                aform:this.$form.createForm(this),
            }
        },
        methods:{
            //1.获取所有宠物
            doGetAllPets(){
                let data={
                    uname:this.uname
                }
                axios.getPets(data).then(res=>{
                    this.petList=res.data.pets
                    this.form.pimg=this.petList[0].pimg
                    console.log(this.petList)
                })
            },
            //2.删除宠物
            startDelete(pname){
                this.visible=true
                this.pname=pname

            },
            handleDelete(){
                let data={
                    pname:this.pname
                }
                axios.deletePet(data).then(res=>{
                    if(res.data.success){
                        this.doGetAllPets()
                        this.visible=false
                        this.$message.success("成功删除宠物信息")
                    }
                    else {
                        alert(res.data.message)
                    }
                })
            },
            //3.更新宠物信息
            startUpdate(pname){
                this.modalVisible=true
                this.pname=pname
                let  data={
                    pname:pname
                }
                axios.getPet(data).then(res=>{
                    this.form=res.data.pets[0]
                    console.log(this.form)
                })
            },
            handleUpdate(e){
                e.preventDefault();
                this.uform.validateFields((errors, values) => {
                    if(!errors){
                        console.log(values)
                        let data={
                            pname:values.pname, phost:this.uname, petage:values.petage, pdescription:values.pdescription,
                            pgender:values.pgender, pimg:this.pimg, ptype:values.ptype
                        }
                        axios.updatePet(data).then(res=>{
                            this.doGetAllPets()
                            this.modalVisible=false
                            this.$message.success("成功修改宠物信息")
                        })
                    }
                })

            },
            //4.图片上传
            handleAvatarSuccess(res) {
                if(res!=null){
                    this.pimg = domain + res.file.response.key;
                    this.form.pimg= domain + res.file.response.key;
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
            //5.检测数字大于0
            checkNum(rule, value, callback) {
                if (value> 0) {
                    callback();
                    return;
                }
                callback("月龄必须大于0");
            },
            //6.跳转宠物详情页
            toDetail(pname){
                this.$router.push({path:'/petcenter',query:{pname:pname}})
            },
            //7.添加宠物
            startAdd(){
                this.addVisible=true
            },
            handleAdd(e){
                e.preventDefault();
                this.aform.validateFields((errors, values) => {
                    if(!errors){
                        console.log(values)
                        let data={
                             pname:values.pname, phost:this.uname, petage:values.petage, pdescription:values.pdescription,
                            pgender:values.pgender, pimg:this.pimg, ptype:values.ptype
                        }
                        axios.addPet(data).then(res=>{
                            this.doGetAllPets()
                            this.addVisible=false
                            this.$message.success("成功添加宠物")
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
            this.doGetAllPets()
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
</style>