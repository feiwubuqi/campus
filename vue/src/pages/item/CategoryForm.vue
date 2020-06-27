<template>
  <v-card
    class="mx-auto"
    max-width="400"

  >

  <i class="el-icon-close icon_i" @click="handleDel" ></i>
    <router-link :to="{path: '/item/newsInfo',query:{id:id}}" exact title="公告详情">
      <div>
    <v-img
      class="white--text align-end"
      height="200px"
      :src="items"
    >
    </v-img>
</div>
      <v-card-title style="color: #1c2438;font-weight: bold;height: 70px" v-text="title"></v-card-title>
    </router-link>
    <hr>


    <v-card-text style="height: 120px" class="text--primary">
<div v-text="content" ></div>
    </v-card-text>
  </v-card>

</template>

<script>
    export default {
        name: "CategoryForm",
        props:['items','title','content','id'],
        data(){
            return{

            }
        },
        methods:{
            handleDel(){
                console.log(this.$props.id)
                this.$emit('removeImg',this.$props.id)

            }
        }
    }
</script>

<style scoped>
.icon_i{
  position: absolute;
  margin-left: 280px;
  z-index:999;color: #1c2438;
  background-color:rgba(225,225,225,1)
}
</style>
