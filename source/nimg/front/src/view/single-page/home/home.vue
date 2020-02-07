<template>
  <div>
    <Card>
      <img class="index" :src="leftSrc" >
      <img class="index" :src="rightSrc">
    </Card>
  </div>
</template>

<script>
import leftPicture from '@/assets/images/leftIndex.png'
import rightPicture from '@/assets/images/rightIndex.png'
import errorContent from '@/view/error-page/error-content.vue'
export default {
  name: 'home',
  components: {
    errorContent
  },
  data () {
    return {
      leftSrc: leftPicture,
      rightSrc: rightPicture
    }
  }
}
</script>

<style lang="less">
.index{
  width: 50%;
  height: 50%;
  position: relative;
  margin-left: auto;
  margin-right: auto;
}
</style>
