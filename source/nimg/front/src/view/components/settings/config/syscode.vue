<template>
  <div>
    <Card>
      <div class="search-row">
        <div class="search-cell">
          <span class="search-text">类型</span>
          <Input v-model="search.codeType" clearable placeholder="输入关键字搜索" class="search-input"/>
        </div>
        <div class="search-cell">
          <span class="search-text">代码</span>
          <Input v-model="search.codeCode" clearable placeholder="输入关键字搜索" class="search-input"/>
        </div>
      </div>
      <div class="search-btn-row">
        <div class="search-btn">
          <Button type="primary" icon="ios-search" @click="handleSearch" ghost>查询</Button>
        </div>
        <div class="search-btn">
          <Button type="primary" icon="ios-add-circle-outline" @click="$refs.codeAdd.show()" ghost>添加</Button>
        </div>
      </div>
      <Divider />
      <tables
        :columns="columns"
        :loading="loading"
        :footer=true
        :searchable = false
        ref="tables"
        editable
        search-place="top"

        v-model="tableData"

        @on-save-edit="handleRowEditSave"
        @on-delete="handleDelete"
        @on-search="handleSearch"
      />
      <code-add ref="codeAdd" @handleAdd="handleAdd"></code-add>
    </Card>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { querySysCode, updateSysCode, deleteSysCode } from '@/api/data'
import CodeAdd from './syscodeadd'

export default {
  name: 'tables_page',
  components: {
    Tables,
    CodeAdd
  },
  data () {
    return {
      loading: false,
      search: {},
      columns: [
        { title: '类型', key: 'codeType', editable: true },
        { title: '代码', key: 'codeCode', editable: true },
        { title: '中文', key: 'codeCName', editable: true },
        { title: '英文', key: 'codeEName', editable: true },
        { title: '繁體', key: 'codeTName', editable: true },
        { title: '序号', key: 'displayNo' },
        {
          title: '操作',
          key: 'handle',
          options: ['delete']
        }
      ],
      tableData: {}
    }
  },
  methods: {
    handleDelete (params) {
      console.log('删除', params)
      deleteSysCode(params).then(res => {
        this.$Message.success('删除成功！')
      })
    },
    handleRowEditSave (params) {
      updateSysCode(params).then(res => {
        this.$Message.success('修改成功！')
      })
    },
    handleSearch (params) {
      this.loading = true
      Object.assign(params, this.search)
      querySysCode(params).then(res => {
        this.tableData = res.data.data
        this.loading = false
      })
    },
    handleAdd () {
      this.$refs.codeAdd.show()
    }
  },
  mounted () {
    /*
    getTableData().then(res => {
      console.log('表格数据数据：', res.data)
      this.tableData = {
        pageSize: 10,
        dataCount: 800,
        data: res.data
      }
      this.loading = false
    })
    */
  }
}
</script>

<style>

</style>
