<template>
  <v-container fluid >
<v-layout v-if="resp.newsInfoList!=null">
  <v-flex xs3>
  <CategoryForm @removeImg="remove" :content="resp.newsInfoList[0].content" :items="resp.newsInfoList[0].imgAddress"  :title="resp.newsInfoList[0].title" :id="resp.newsInfoList[0].sequenceId" ></CategoryForm>
  </v-flex>
  <v-spacer/>
  <v-flex xs3 v-if="resp.newsInfoList.length>=2">
  <CategoryForm @removeImg="remove" :content="resp.newsInfoList[1].content" :items="resp.newsInfoList[1].imgAddress" :title="resp.newsInfoList[1].title" :id="resp.newsInfoList[1].sequenceId"></CategoryForm>
  </v-flex>
  <v-spacer/>
  <v-flex xs3 v-if="resp.newsInfoList.length>=3">
    <CategoryForm @removeImg="remove" :content="resp.newsInfoList[2].content" :items="resp.newsInfoList[2].imgAddress" :title="resp.newsInfoList[2].title" :id="resp.newsInfoList[2].sequenceId"  ></CategoryForm>
  </v-flex>
</v-layout>
    <v-layout style="margin-top: 20px ">
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
  import CategoryForm from "./CategoryForm";
  export default {
      inject:['reload'],
      name: "category",
    data() {
      return {
          page:1,
          totalNum:3,
          resp: {
              page: "",
              pageSize:"",
              totalNum:0 ,
              newsInfoList: [
                  {
                      sequenceId: "",
                      title: "",
                      author: "",
                      fromAddress: "",
                      time: "",
                      countNum: "",
                      imgAddress: "",
                      content: ""
                  }
              ]
          }
      }
    },
      created(){

        this.$http.post("/campus_life_api/api/campus/news/info",{
                page:"1",
            pageSize:"3",

        })
            .then(res=>{
                 this.resp=res.data;
                 this.totalNum=this.resp.totalNum
                console.log(this.resp.totalNum)
                 this.loading=false;
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
            if (level==1){
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
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
        del(id){ this.$http.post("/campus_life_api/api/campus/news/delete",{
      sequenceId:id})
            .then(res=>{
      console.log(res.data)
      // this.$router.go(0)
      this.reload()
    })
  },
        query(currentPage,pageSize1){
            this.$http.post("/campus_life_api/api/campus/news/info",{
                page:currentPage,
                pageSize:pageSize1,

            })
                .then(res=>{
                    this.resp=res.data;
                    this.loading=false;
                })

        }
      },
      computed:{
      },
      components:{
        CategoryForm
      }
  }

</script>

<style scoped>



</style>
