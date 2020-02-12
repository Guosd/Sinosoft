<template>
  <Modal
    v-model="visiable"
    :maskClosable=false
    title="添加系统代码"
    width="60%"
    @on-ok="ok"
    @on-cancel="cancel">
    <Tree ref="tree" :data="relatedInfo" show-checkbox></Tree>
  </Modal>
</template>
<script>
import { queryTaskRoleRelated, saveTaskRoleRelated } from '@/api/user'
export default {
  name: 'RoleRelated',
  data () {
    return {
      roleCode: '',
      visiable: false,
      relatedInfo: []
    }
  },
  methods: {
    show (roleCode) {
      if (roleCode === undefined || roleCode === null || roleCode === '') {
        this.$Message.info('角色代码为空，不允许配置')
        return
      }
      this.visiable = true
      this.roleCode = roleCode
      this.init()
    },
    ok () {
      const checkedInput = this.$refs.tree.getCheckedAndIndeterminateNodes()
      const taskIds = []
      checkedInput.forEach(item => taskIds.push(item.id))
      console.log(taskIds)
      const param = {
        roleCode: this.roleCode,
        taskIds: taskIds
      }
      saveTaskRoleRelated(param).then(res => {
        this.$Message.success('保存成功！')
      })
    },
    cancel () {
      this.visiable = false
    },
    init () {
      // 初始化树状结构信息
      queryTaskRoleRelated(this.roleCode).then(res => {
        console.log(res)
        this.relatedInfo = res.data.data
      })
    },
    getCheckedNodes () {
      console.log(this.$refs.tree.getCheckedAndIndeterminateNodes())
    }
  }
}
</script>
