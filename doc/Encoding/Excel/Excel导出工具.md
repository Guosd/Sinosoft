# Excel 导出工具使用说明

## 0.写在前面的话

Excel 文件生成，下载只需如下一行代码（有没有很爽！）

```java
new ExportExcel("标题", Test.class).setDataList(testList).write(response, fileName).dispose();
```
文件名称：类名所在文件夹

## 1. 使用说明
- 编写实体类
```java
@Data
public class Test implements Serializable {
  // title 列名  
  // align 0：自动；1：靠左；2：居中；3：靠右
  // sort 导出字段字段排序
  @ExcelField(title="Test1", align=2, sort=10)
  private Date test1;
	...
}
```

- 调用生成工具

```java
// "标题" 居中展示的标题
// Test.class 指映射类
// testList	Excle 中的数据 List类型
// response HttpServletResponse 处理文件下载使用
// fileName 下载的文件名字
new ExportExcel("标题", Test.class).setDataList(testList).write(response, fileName).dispose();
```

 ## 2.源码分析

1. 实例化导出工具
`public ExportExcel(String title, Class<?> cls, int type, int... groups)`

    - 获取cls中具有`@ExcelField`注解的字段

    ```java
    Field[] fs = cls.getDeclaredFields();
    for (Field f : fs){
      ExcelField ef = f.getAnnotation(ExcelField.class);
      ....
    }
    ```

    - 获取cls中具有`@ExcelField`注解的方法

    ```java
    Method[] ms = cls.getDeclaredMethods();
    for (Method m : ms){
      ExcelField ef = m.getAnnotation(ExcelField.class);
      ...
    }
    ```

    - 根据 `@ExcelField`中的`sort` 对cls 中的字段进行排序

    ```java
    Collections.sort(annotationList, new Comparator<Object[]>(){....})
    ```

    - 初始化 Excel 头信息

    ```java
    initialize(title, headerList)
    ```

2. 放入数据
    `public <E> ExportExcel setDataList(List<E> list)`
    
    > 由第一步获取的属性，生成excel

3. 输出文件

   `public ExportExcel write(HttpServletResponse response, String fileName)`

   > 使用流进行输出

***

```
# ExportExcel 类中方法
ExportExcel 
│─ ExportExcel(String title, Class<?> cls)
│─ ExportExcel(String title, Class<?> cls, int type, int... groups)
│─ ExportExcel(String title, String[] headers)
│─ ExportExcel(String title, List<String> headerList)
│─ private initialize(String title, List<String> headerList)
│─ private Map<String, CellStyle> createStyles(Workbook wb)
│─ public Row addRow()
│─ public Cell addCell(Row row, int column, Object val)
│─ public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType)
│─ public <E> ExportExcel setDataList(List<E> list)
│─ public ExportExcel write(OutputStream os)
│─ public ExportExcel write(HttpServletResponse response, String fileName)
│─ public ExportExcel writeFile(String name)
│─ public ExportExcel dispose()
└─ ......
```

***

```xml
<!-- 引入jar包 -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.9</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.9</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml-schemas</artifactId>
    <version>3.9</version>
</dependency>
```

