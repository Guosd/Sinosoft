const btns = {
  delete: (h, params, vm) => {
    return h('Poptip', {
      props: {
        confirm: true,
        title: '你确定要删除吗?'
      },
      on: {
        'on-ok': () => {
          console.log('删除数据', params)
          vm.$emit('on-delete', params.tableData.data.filter((item, index) => index === params.row.initRowIndex)[0])
          params.tableData.data = params.tableData.data.filter((item, index) => index !== params.row.initRowIndex).map((item, index) => {
            let res = item
            res.initRowIndex = index
            return res
          })
          // 因为直接操作无法更新，因此，在此处使用Object.assign
          let tmp = {}
          Object.assign(tmp, params.tableData)
          vm.$emit('input', tmp)
        }
      }
    }, [
      h('Button', {
        props: {
          type: 'text',
          ghost: true
        }
      }, [
        h('Icon', {
          props: {
            type: 'md-trash',
            size: 18,
            color: '#000000'
          }
        })
      ])
    ])
  }
}

export default btns
