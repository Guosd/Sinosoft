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
          <Button type="primary" @click="handleSearch" ghost>查询</Button>
        </div>
        <div class="search-btn">
          <Button type="primary" ghost>撤销</Button>
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

        @on-start-edit="handleRowEdit"
        @on-delete="handleDelete"
        @on-search="handleSearch"
      />
    </Card>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { querySysCode } from '@/api/data'

export default {
  name: 'tables_page',
  components: {
    Tables
  },
  data () {
    return {
      loading: false,
      search: {},
      columns: [
        { title: '类型', key: 'codeType' },
        { title: '代码', key: 'codeCode' },
        { title: '中文', key: 'codeCName' },
        { title: '英文', key: 'codeEName' },
        { title: '繁體', key: 'codeTName' },
        { title: '序号', key: 'displayNo' }
        /* {
          title: 'Handle',
          key: 'handle',
          options: ['delete'],
          button: [
            (h, params, vm) => {
              return h('Poptip', {
                props: {
                  confirm: true,
                  title: '你确定要删除吗?'
                },
                on: {
                  'on-ok': () => {
                    vm.$emit('on-delete', params)
                    vm.$emit('input', params.tableData.filter((item, index) => index !== params.row.initRowIndex))
                  }
                }
              }, [
                h('Button', '自定义删除')
              ])
            }
          ]
        } */
      ],
      tableData: {}
    }
  },
  methods: {
    handleDelete (params) {
      console.log(params)
    },
    handleRowEdit (params) {
      console.log(params)
    },
    handleSearch (params) {
      this.loading = true
      Object.assign(params, this.search)
      console.log('handleSearch', params, this.search)
      querySysCode(params).then(res => {
        console.log('表格数据数据：', res.data)
        this.tableData = res.data.data
        this.loading = false
      })
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
