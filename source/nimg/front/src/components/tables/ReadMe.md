# Tables 使用说明

## 1.表格参数说明
  1. v-model, 对应模块化变量为 value
      > type: Object
      > eg:
      > ```json
      > {
      > 	dataCount: 800,
      > 	pageSize: 10,
      > 	data: [] // 此处传入数组即可
      > }
      > ```
      
  2. :columns="columns" 表格列
     
      > type: Arrays
      
  3. :loading 是否展示加载中
     
      > type: Boolean
      
  4. :footer 是否展示底部分页组件
     
      > type: Boolean
      
  5. :size 控制表格大小
      > type: String
      > ```javascript
      > 只能是数组中的一个
      > ['small', 'large', 'default']
      > ```
      
  4. :footer 是否展示底部分页组件
     
      > type: Boolean
      
  5. :width 宽度
     
      > type: [Number, String]
      
  6. :height 高度
     
      > type: [Number, String]
      
  7. :stripe 是否显示条纹
     
      > type: Boolean
      
  8. :border 是否展示表格内部边框
     
      > type: Boolean
      
  9. :showHeader 是否展示表格标题
     
      > type: Boolean

  10. :highlightRow 

      > type: Boolean
     
  11. :rowClassName 

      > type: Function

  12. :context

      > type: Object
     
  13. :noDataText 当无数据展示的时候显示的内容

       > type: String

  14. :noFilteredDataText 无过滤数据显示内容

      > type: String

  15. :disabledHover 鼠标悬停是否变色

      > type: Boolean

  16. :loading 是否展示正在加载

      > type: Boolean

  17. :footer 是否展示底部分页组件

      > type: Boolean

  18. :editable 全局设置是否可编辑

      > type: Boolean

  19. :searchable 是否可搜索

      > type: Boolean

  20. :searchPlace 全局设置是否可编辑

      > type: String
      > ```javascript
      > 只能是数组中的一个
      > ['top', 'bottom']
      > ```

## 2.表格回调方法说明
  > @on-start-edit="functionName"

  1. @on-current-change 
  2. @on-select
  3. @on-select-cancel
  4. @on-select-all
  5. @on-selection-change
  6. @on-sort-change
  7. @on-filter-change 
  8. @on-row-click
  9. @on-row-dblclick
  10. @on-expand
  11. @on-search 表格改变调用的查询方法

