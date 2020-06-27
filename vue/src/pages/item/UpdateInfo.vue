<template>
  <div>
    <span style="color: #44a3f3;position: absolute;margin-left: 110px;margin-top: -38px">公告信息修改</span>
    <el-upload
      class="upload-demo"
      action="/campus_life_api/api/campus/upload/img"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :file-list="fileList"
      :limit=1
      list-type="picture">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title" style="width: 500px" ></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="ruleForm.author" style="width: 500px"></el-input>
      </el-form-item>
      <el-form-item label="引用地址" prop="fromAddress" >
        <el-select v-model="ruleForm.fromAddress" placeholder="请选择引用地址" style="width: 500px">
          <el-option label="校园新闻" value="校园新闻"></el-option>
          <el-option label="微博" value="微博"></el-option>
          <el-option label="腾讯新闻" value="腾讯新闻"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" required>
        <el-col :span="12">
          <el-form-item prop="time">
            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.time" style="width: 89%;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="信息内容" prop="content">
        <el-input type="textarea" v-model="ruleForm.content" style="width: 500px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"   :plain="true" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "UpdateInfo",

        data(){
            return{
                fileList: [{
                        name: 'gateway网关配置.jpg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    }],

                ruleForm: {
                    sequenceId:'',
                    author: '',
                    title: '',
                    fromAddress:'',
                    time: '',
                    delivery: false,
                    content: '',
                    imgAddress:''
                },
                rules: {
                    author: [
                        { required: true, message: '请输入作者名称', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    fromAddress:[
                        {required: true, message: '请选择引用地址', trigger: 'change'}
                    ],
                    title: [
                        { required: true, message: '请输入标题名称', trigger: 'blur' }
                    ],
                    // time: [
                    //     { type: 'date', required: true, message: '请选择日期', trigger: 'blur' }
                    // ],
                    content: [
                        { required: true, message: '请填写信息内容', trigger: 'blur' }
                    ]
                }
            }
        },
        components:{
        },
        methods:{
            handleSuccess(response, file, fileList){
                console.log("...")
                console.log(file)
                this.ruleForm.imgAddress=file.name
            },
            handleRemove(file, fileList) {
                this.$http.post("/campus_life_api/api/campus/upload/removeImg",{
                    name:file.name
                })
                    .then(res=>{
                        this.ruleForm.imgAddress=''
                        console.log(res.data)
                    })
                console.log(file, fileList);
            },
            handlePreview(file) {
                this.ruleForm.imgAddress=file.name;
                console.log(file);
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (this.ruleForm.imgAddress.length>0){
                        if (valid) {
                            // alert('submit!');

                            this.$message({
                                message:"正在提交",
                                duration:1500
                            });
                            this.$http.post("/campus_life_api/api/campus/news/update",this.ruleForm)
                                .then(res=>{
                                    this.$message({
                                        message:res.data,
                                        duration:2000
                                    });
                                })
                            let _this=this
                            setTimeout(function () {
                                _this.$router.push("category")
                            },2000)

                        }
                    }else {
                        // console.log('error submit!!');
                        alert("请上传图片")
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            //this.ruleForm=this.$route.query.newsInfo,
            this.$http.post("/campus_life_api/api/campus/news/detail",{
                sequenceId:this.$route.query.id,
                title:"",
                author:"",
                fromAddress:"",
                time:"",
                countNum:"",
                imgAddress:"",
                content: ""

            })
                .then(res=>{
                    this.ruleForm=res.data;
                    this.loading=false;
                    let reg=new RegExp('../../static/','g')
                    let newMsg=this.ruleForm.imgAddress.replace(reg,'');
                    this.ruleForm.imgAddress=newMsg
                    let baseUrl="http://localhost:9001/static/"
                    this.fileList[0].url=baseUrl+newMsg
                    this.fileList[0].name=newMsg
                })

            //console.log(newMsg)
        }


    }
</script>

<style scoped>

</style>
