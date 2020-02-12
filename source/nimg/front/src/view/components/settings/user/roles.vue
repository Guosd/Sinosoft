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
          <Button type="primary" icon="ios-add-circle-outline" @click="$refs.roleRelated.show()" ghost>添加</Button>
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
        @config="config"
      >
        <template slot-scope="{ row, index }" slot="action">
          <Button type="primary" size="small" style="margin-right: 5px" >View</Button>
        </template>
      </tables>
      <role-related ref="roleRelated"></role-related>
    </Card>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { querySysRole, deleteSysRole, updateSysRole } from '@/api/user'
import RoleRelated from './roleRelatedAdd'

export default {
  name: 'tables_page',
  components: {
    RoleRelated,
    Tables
  },
  data () {
    return {
      loading: false,
      search: {},
      columns: [
        { title: '角色代码', key: 'roleCode', editable: true },
        { title: '角色名称', key: 'roleName', editable: true },
        { title: '系统代码', key: 'system', editable: true },
        {
          title: '操作',
          key: 'handle',
          button: [
            (h, params, vm) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.config(params)
                    }
                  }
                }, '配置')
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
      console.log('删除', params)
      deleteSysRole(params).then(res => {
        this.$Message.success('删除成功！')
      })
    },
    handleRowEditSave (params) {
      updateSysRole(params).then(res => {
        this.$Message.success('修改成功！')
      })
    },
    handleSearch (params) {
      this.loading = true
      Object.assign(params, this.search)
      querySysRole(params).then(res => {
        this.tableData = res.data.data
        this.loading = false
      })
    },
    config (params) {
      this.$refs.roleRelated.show(params.row.id)
    }
  },
  mounted () {
  }
}
</script>

<style>

</style>
