<template>
  <Modal
    v-model="visiable"
    :maskClosable=false
    title="添加系统代码"
    width="60%"
    @on-ok="ok"
    @on-cancel="cancel">
    <Tree></Tree>
  </Modal>
</template>
<script>

export default {
  name: 'RoleRelated',
  data () {
    return {
      visiable: false,
      prpDcode: {}
    }
  },
  methods: {
    show () {
      console.log('show ')
      this.prpDcode = {}
      this.visiable = true
    },
    ok () {
      console.log(this.prpDcode)
    },
    cancel () {
      this.visiable = false
    },
    init () {
      // 初始化树状结构信息
    }
  },
  mounted () {
    this.init()
  }
}
</script>
