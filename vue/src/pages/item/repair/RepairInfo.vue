<template>
  <v-container fluid >
    <v-flex xs12>
      <repair-info-from v-if="resp.repairInfoList!=null"  :repair="resp.repairInfoList[0]" @removeRepair="remove"></repair-info-from>
    </v-flex>
    <v-flex xs12>
      <repair-info-from :repair="resp.repairInfoList[1]" @removeRepair="remove"></repair-info-from>
    </v-flex>
    <v-flex xs12>
      <repair-info-from :repair="resp.repairInfoList[2]" @removeRepair="remove"></repair-info-from>
    </v-flex>
    <v-layout >
      <v-spacer/>
      <v-flex xs4>
        <div class="text-center" >
          <v-pagination
            v-model="page"
            :length="resp.totalNum"
            circle
          ></v-pagination>
        </div>
      </v-flex>
      <v-spacer/>
    </v-layout>
  </v-container>
</template>

<script>

  import RepairInfoFrom from "./RepairInfoFrom";
    export default {
        name: "RepairInfo",
        inject:['reload'],
        data(){
            return {
                page: 1,
                totalNum: 2,
                resp: {
                    page: "1",
                    pageSize: "3",
                    totalNum: 2,
                    repairInfoList: [
                        {
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
                        }
                    ]

                }
            }
        },
        components:{
            RepairInfoFrom
        },
        created() {
            this.$http.post("/campus_life_api/api/campus/repair/info",this.resp)
                .then(res=>{
                    this.resp=res.data
                    this.loading=false
                })
        },
        watch:{
            page() {
                this.query(this.page,3);

            }
        },
        methods:{
            remove(id){
                let level=JSON.parse(sessionStorage.getItem('account')).level
                if (level==1) {
                    this.$confirm('此操作将永久删除该维修任务, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.del(id)
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
                }else{
                    alert("您的权限不足！")
                }
            },
            del(id)
                {
                    this.$http.post("/campus_life_api/api/campus/repair/delete", {
                        taskId: id
                    })
                        .then(res => {
                            console.log(res.data)
                            // this.$router.go(0)
                            this.reload()
                        })
                },

            query(currentPage,pageSize1){
                this.$http.post("/campus_life_api/api/campus/repair/info",{
                    page:currentPage,
                    pageSize:pageSize1,

                })
                    .then(res=>{
                        this.resp=res.data;
                        this.loading=false;
                    })

            }
    }



    }
</script>

<style scoped>

</style>
