<template>
  <v-carousel
    cycle
    height="481"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      reverse-transition="fade-transition"
      transition="fade-transition"
    >
    </v-carousel-item>

  </v-carousel>


</template>

<script>

    export default {
        name:"Dynamic",
        data () {
            return {

                accountSno:this.$route.query.accountSno,
                items: [
                    {
                        src: require('../assets/01.jpg'),
                    },
                    {
                        src:  require('../assets/02.jpg'),
                    },
                    {
                        src: require('../assets/03.jpg'),
                    },
                    {
                        src: require('../assets/04.jpg'),
                    },
                ],
            }
        },
        created() {

            // this.$http.post("/campus_life_api/api/campus/account",{
            //     accountSno:this.accountSno
            // })
            //     .then(res=>{
            //     // this.$emit('getAccount',res.data.accountName)
            // })
        }




    }
</script>

<style scoped>

</style>
