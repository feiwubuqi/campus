<template>
  <div  class="demo-image" v-if="repair!=null">
    <span v-if="repair.processStatus==='00'" class="span " style="text-align: center;line-height: 100px;color: red;font-weight: bold">new</span>
    <span v-if="repair.processStatus==='01'" class="span " style="text-align: center;line-height: 100px;color: #a1a1a1;font-weight: bold">已完成</span>
    <el-image
      style="width: 120px; height: 120px;margin-left: 70px;"
      :src="repair.imgAddress"
      fit="cover">
    </el-image>
    <span  class="span " style="z-index: 999"  >描述:</span><span class="span " style="z-index: 999;margin-left: 50px" v-text="repair.problemDesc"></span>
    <span class="span " style="z-index: 888; margin-top: 60px">联系方式:</span><span class="span " style="margin-top: 60px;z-index: 999;margin-left: 80px" v-text="repair.mobile"></span>
    <span class="span" style="margin-left: 350px">门牌号:</span><span class="span " style="z-index: 999;margin-left: 405px" v-text="repair.houseNo"></span>
    <span class="span" style="margin-left: 350px;margin-top: 60px">维修时间:</span><span class="span " style="margin-top: 60px;z-index: 999;margin-left: 420px" v-text="repair.repairTime"></span>
    <el-switch
      v-model="value"
      active-text="未完成"
      inactive-text="已完成"
      style="position: absolute;margin-left: 750px;margin-top: 55px" >
    </el-switch>
    <el-button type="primary" icon="el-icon-delete" style="position: absolute;margin-left: 900px;margin-top: 45px" @click="handleDel"></el-button>
    <el-collapse accordion>

    <el-collapse-item  class="span" title="备注" style="font-size:16px;margin-left: 800px;margin-top: -125px;position:absolute ">
      <div v-text="repair.remark"></div>
<!--      <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>-->
    </el-collapse-item>
  </el-collapse>
  </div>

</template>

<script>
    export default {
        inject:['reload'],
        name: "RepairInfoFrom",
        props:['repair'],
        data() {
            return {
                value:false,
                fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
            }
        },
        methods:{
            handleDel(){

                this.$emit('removeRepair',this.$props.repair.taskId)

            }
        },
        created() {


        },
        watch:{
            repair(){
                if (this.$props.repair.processStatus==='00'){
                    this.value=true
                }else {
                    this.value=false
                }
            },
            value(){
                if (this.value){
                    this.$props.repair.processStatus='00'
                }else {
                    this.$props.repair.processStatus='01'
                }
                this.$http.post("/campus_life_api/api/campus/repair/update",this.$props.repair)
                    .then(res=>{
                        console.log(res.data)
                        // this.reload()
                    })


            }
        }
    }
</script>

<style scoped>

  .span{
    font-family: 微软雅黑;
    height:50px;
    line-height: 50px;
    display: inline-block;
    /*background-color: #00e5ff;*/
    margin-left:10px;
    position: absolute;
    font-size: 16px
  }


</style>
