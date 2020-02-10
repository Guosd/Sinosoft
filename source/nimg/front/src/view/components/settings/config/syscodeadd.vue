<template>
  <Modal
    v-model="visiable"
    title="添加系统代码"
    width="80%"
    @on-ok="ok"
    @on-cancel="cancel">
    <div class="form-box">
      <div class="form-row">
        <div class="form-cell">
          <span class="form-text">类型</span>
          <Input v-model="prpDcode.codeType" clearable placeholder="输入类型" class="form-input"/>
        </div>
        <div class="form-cell">
          <span class="form-text">代码</span>
          <Input v-model="prpDcode.codeCode" clearable placeholder="输入代码" class="form-input"/>
        </div>
      </div>
      <div class="form-row">
        <div class="form-cell">
          <span class="form-text">中文</span>
          <Input v-model="prpDcode.codeCName" clearable placeholder="输入中文" class="form-input"/>
        </div>
        <div class="form-cell">
          <span class="form-text">英文</span>
          <Input v-model="prpDcode.codeEName" clearable placeholder="输入英文" class="form-input"/>
        </div>
      </div>
      <div class="form-row">
        <div class="form-cell">
          <span class="form-text">繁體</span>
          <Input v-model="prpDcode.codeTName" clearable placeholder="输入繁體" class="form-input"/>
        </div>
        <div class="form-cell">
          <span class="form-text">序号</span>
          <Input v-model="prpDcode.displayNo" clearable placeholder="输入序号" class="form-input"/>
        </div>
      </div>
    </div>
  </Modal>
</template>
<script>
import { saveSysCode } from '@/api/data'

export default {
  name: 'CodeAdd',
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
      saveSysCode(this.prpDcode).then(res => {
        console.log(res)
        this.visiable = false
        this.$Message.info('保存成功')
      })
    },
    cancel () {
      this.visiable = false
    }
  }
}
</script>
