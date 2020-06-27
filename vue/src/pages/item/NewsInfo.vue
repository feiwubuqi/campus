<template>

<div v-if="newsInfo.title">
  <span style="color: #44a3f3;position: absolute;margin-left: 110px;margin-top: -38px"> 公告信息详情</span>
  <h1 v-text="newsInfo.title" style="text-align: center;line-height: 80px;height: 80px"></h1>
  <el-button @click="updateInfo" native-type="button" type="primary" icon="el-icon-edit" size="medium" style="position: absolute;margin-left:90%;width:100px;margin-top:-5%">修改</el-button>
  <hr/>
  <div style="height:50px;text-align: center;line-height: 50px;">发布时间: <span v-text="newsInfo.time"></span> 浏览次数: <span v-text="newsInfo.countNum"></span></div>
<div v-text="newsInfo.content" style="width: 80%;font-size: 17px;margin: auto;white-space: pre-line"></div>
  <div style="margin: auto;text-align: center">
<img :src="newsInfo.imgAddress">
  </div>
</div>
</template>

<script>
    export default {
        name: "NewsInfo",
        data() {
            return {
                newsInfo:
                    {
                        sequenceId:"",
                        title:"",
                        author:"",
                        fromAddress:"",
                        time:"",
                        countNum:"",
                        imgAddress:"",
                        content: ""
                    },
                seqId:this.$route.query.id

            }
        },

        created(){
            this.$http.post("/campus_life_api/api/campus/news/detail",{

                        sequenceId:this.seqId,
                        title:"",
                        author:"",
                        fromAddress:"",
                        time:"",
                        countNum:"",
                        imgAddress:"",
                        content: ""

            })
                .then(res=>{
                    this.newsInfo=res.data;
                    this.loading=false;
                })
        },
methods:{
            updateInfo(){
               this.$router.push({path:"updateInfo",query:{id:this.newsInfo.sequenceId}})
            }
}

    }
</script>

<style scoped>

</style>
