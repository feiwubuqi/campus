<template>
  <v-card style="height: 605px">
    <v-toolbar class="elevation-0">
<!--      <v-btn color="primary" @click="toDormitory">新增</v-btn>-->
<!--      <v-spacer/>-->
      <v-flex xs3>
        状态：
        <v-btn-toggle mandatory v-model.lazy="filter.saleable">
          <v-btn flat @click="all">
            全部
          </v-btn>
          <v-btn flat :value="true" @click="access">
            未使用
          </v-btn>
          <v-btn flat :value="false" @click="success">
            已使用
          </v-btn>
        </v-btn-toggle>
      </v-flex>
      <v-spacer/>
      <v-flex xs3>
        <v-text-field
          append-icon="search"
          label="搜索"
          single-line
          hide-details
          v-model="filter.search"
        />
      </v-flex>
    </v-toolbar>
    <el-table
      :data="resp.dormitoryFlowList"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="id"
        width="180"
      align="center">
      </el-table-column>
      <el-table-column
        prop="accountSno"
        label="学号"
        width="180"
        align="center">
      </el-table-column>
      <el-table-column
        prop="houseNo"
        label="门牌号"
        width="180"
        align="center">
      </el-table-column>
      <el-table-column
        prop="bedNo"
        label="床位"
        align="center">
      </el-table-column>
      <el-table-column
        prop="intoTime"
        label="入住时间"
        align="center">
      </el-table-column>
      <el-table-column
        prop="outTime"
        label="迁出时间"
        align="center">
      </el-table-column>
      <el-table-column
        prop="status"
        label="使用状态"
        align="center"
      >
      </el-table-column>
    </el-table>
    <v-layout  style="margin-top: 30px">
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
  </v-card>
</template>

<script>
  import GoodsForm from './GoodsForm'

  export default {
      name: "goods",
      inject:['reload'],
    data() {
      return {
            page:1,
          filter:{
              search:'',
              saleable:'',
          },
          resp: {
              page: "1",
              pageSize: "3",
              totalNum: 2,
              dormitoryFlowList: [{
              id:'1',
              intoTime: '2016-05-02',
              accountSno: '163010103',
              houseNo: '7公寓508室',
              bedNo: '01' ,
              outTime: '2020-06-30',
              status:'',

          }]
          }
      }
    },
      created(){

          this.$http.post("/campus_life_api/api/campus/dormitory/info",{
              page:1,
              pageSize:8,

          }).then(res=>{
                  this.resp=res.data;
                  this.loading=false;

              })
      },
      watch:{
          page() {
              this.query(this.page,8,this.resp.dormitoryFlowList[0].status);
          },
          filter:{
              deep:true,
              handler(newVal,oldVal){
                  console.log(newVal.search)
                  console.log(oldVal.search)
                  if (newVal.search!=null&&newVal.search!=''){
                  this.$http.post("/campus_life_api/api/campus/dormitory/info",{
                      page:1,
                      pageSize:8,
                      dormitoryFlow:{
                          houseNo:newVal.search
                      }
                  })
                      .then(res=>{
                          this.resp=res.data;
                          this.loading=false;

                      })
                  }
              }
          }
      },
      methods:{
          all(){
              this.reload()
          },
          access(){
              this.resp.dormitoryFlowList[0].status='未使用'
              this.query(1,8,'00')
          },
          success(){
              this.resp.dormitoryFlowList[0].status='已使用'
              this.query(1,8,'01')
          },
        toDormitory(){

            this.$router.push("")
        },


          query(currentPage,pageSize1,status){
              this.$http.post("/campus_life_api/api/campus/dormitory/info",{
                  page:currentPage,
                  pageSize:pageSize1,
                  dormitoryFlow:{
                      status:status
                  }
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
