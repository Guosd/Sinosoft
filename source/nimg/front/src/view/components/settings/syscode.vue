<template>
  <div>
    <Card>
      <div class="search-row">
        <div class="search-cell">
          <span class="search-text">查询条件</span>
          <Input clearable placeholder="输入关键字搜索" class="search-input"/>
        </div>
        <div class="search-cell">
          <span class="search-text">查询条件</span>
          <Input clearable placeholder="输入关键字搜索" class="search-input"/>
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
import { getTableData } from '@/api/data'

export default {
  name: 'tables_page',
  components: {
    Tables
  },
  data () {
    return {
      loading: false,
      columns: [
        { title: 'Name', key: 'name', sortable: true },
        { title: 'Email', key: 'email', editable: true },
        { title: 'Create-Time', key: 'createTime' },
        {
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
        }
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
      console.log('handleSearch', params)
      getTableData().then(res => {
        console.log('表格数据数据：', res.data)
        this.tableData = {
          pageSize: 10,
          dataCount: 5,
          data: res.data
        }
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
