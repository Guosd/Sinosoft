<template>
  <div>
    <Card>
      <img class="index" :src="src" :alt="code">
    </Card>
  </div>
</template>

<script>
import indexPicture from '@/assets/images/index.png'
import errorContent from '@/view/error-page/error-content.vue'
export default {
  name: 'home',
  components: {
    errorContent
  },
  data () {
    return {
      src: indexPicture
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
