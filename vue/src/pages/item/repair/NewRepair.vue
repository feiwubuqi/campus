<template>
  <div style="height: 481px">
<!--    <span style="color: #44a3f3;position: absolute;margin-left: 110px;margin-top: -38px">公告信息修改</span>-->
    <el-upload
      class="upload-demo"
      action="/campus_life_api/api/campus/upload/img"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"

      :limit=1
      list-type="picture">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="描述" prop="problemDesc">
        <el-input v-model="ruleForm.problemDesc" style="width: 500px" ></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="mobile">
        <el-input v-model="ruleForm.mobile" style="width: 500px"></el-input>
      </el-form-item>
      <span style="color: red;position: absolute;margin-left: 35px;margin-top: 12px">*</span><el-form-item label="门牌号" prop="address" >
        <el-select v-model="address1" placeholder="请选择公寓地址" style="width: 150px">
          <el-option label="1公寓" value="1-"></el-option>
          <el-option label="2公寓" value="2-"></el-option>
          <el-option label="3公寓" value="3-"></el-option>
          <el-option label="4公寓" value="4-"></el-option>
          <el-option label="5公寓" value="5-"></el-option>
          <el-option label="6公寓" value="6-"></el-option>
          <el-option label="7公寓" value="7-"></el-option>
        </el-select>
        <span style="display: inline-block;width: 18px"></span>
        <el-select v-model="address2" placeholder="请选择宿舍楼层" style="width: 150px">
        <el-option label="1楼" value="1"></el-option>
        <el-option label="2楼" value="2"></el-option>
        <el-option label="3楼" value="3"></el-option>
        <el-option label="4楼" value="4"></el-option>
        <el-option label="5楼" value="5"></el-option>
        <el-option label="6楼" value="6"></el-option>
      </el-select>
        <span style="display: inline-block;width: 18px"></span>
        <el-select v-model="address3" placeholder="请选择宿舍门牌" style="width: 150px">
          <el-option label="1室" value="01"></el-option>
          <el-option label="2室" value="02"></el-option>
          <el-option label="3室" value="03"></el-option>
          <el-option label="4室" value="04"></el-option>
          <el-option label="5室" value="05"></el-option>
          <el-option label="6室" value="06"></el-option>
          <el-option label="7室" value="07"></el-option>
          <el-option label="8室" value="08"></el-option>
          <el-option label="9室" value="09"></el-option>
          <el-option label="10室" value="10"></el-option>
          <el-option label="11室" value="11"></el-option>
          <el-option label="12室" value="12"></el-option>
          <el-option label="13室" value="13"></el-option>
          <el-option label="14室" value="14"></el-option>
          <el-option label="15室" value="15"></el-option>
          <el-option label="16室" value="16"></el-option>
          <el-option label="17室" value="17"></el-option>
          <el-option label="18室" value="18"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="维修时间" required>
        <el-col :span="12">
          <el-form-item prop="repairTime">
            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.repairTime" style="width: 89%;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input type="textarea" v-model="ruleForm.remark" style="width: 500px"></el-input>
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
        name: "NewRepair",
        data(){
            return{
                // fileList: [{
                //     name: 'gateway网关配置.jpg',
                //     url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                // }],
                address1:'',
                address2:'',
                address3:'',
                ruleForm: {
                    problemDesc:'',
                    houseNo:'',
                    reportTime:'',
                    mobile:'',
                    repairTime:'',
                    remark:'',
                    imgAddress:'',
                    processStatus:'',
                    accountSno:'',
                    taskId:''
                },
                rules: {
                    problemDesc: [
                        { required: true, message: '请输入描述内容', trigger: 'blur' },
                        // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    address1:[
                        {required: true, message: '请选择引用地址', trigger: 'change'}
                    ],
                    mobile: [
                        { required: true, message: '请输入手机号', trigger: 'blur' },
                        { min: 11, max:11, message: '手机号码为11位', trigger: 'blur' }
                    ],
                    // time: [
                    //     { type: 'date', required: true, message: '请选择日期', trigger: 'blur' }
                    // ],
                    remark: [
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
                            this.ruleForm.houseNo=this.address1+this.address2+this.address3
                            this.$http.post("/campus_life_api/api/campus/repair/incr",this.ruleForm)
                                .then(res=>{
                                    this.$message({
                                        message:res.data,
                                        duration:2000
                                    });
                                })
                            let _this=this
                            setTimeout(function () {
                                _this.$router.push("repair")
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

        }



    }
</script>

<style scoped>

</style>
