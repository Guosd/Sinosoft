# Excel 导入工具使用说明

## 0.写在前面的话

   解析过程：

- 上传文件

- 解析
> 解析也只需要一句话
```
ImportExcel ei = new ImportExcel(file, 1, 0);
List<User> list = ei.getDataList(User.class);
```

## 1. 使用说明
- 编写实体类
```java
@Data
public class Test implements Serializable {
  // title 列名  
  // type 0：导出导入；1：仅导出；2：仅导入
  // align 0：自动；1：靠左；2：居中；3：靠右
  // sort 导出字段字段排序
  @ExcelField(title="Test1", type=0, align=2, sort=10)
  private Date test1;
	...
}
```

- 调用生成工具

```java
// "标题" 居中展示的标题
ImportExcel ei = new ImportExcel(file, 1, 0);
List<User> list = ei.getDataList(User.class);
```

 ## 2.源码分析
1. 实例化时，会将Excel 数据进行解析
    ```java
    /**
     * 构造函数
     * @param file 导入文件对象
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     */
    // 上传文件解析
    public ImportExcel(MultipartFile multipartFile, int headerNum, int sheetIndex)
    // 直接File
    public ImportExcel(File file, int headerNum, int sheetIndex) 
    // 最后都调用这一个
    public ImportExcel(String fileName, InputStream is, int headerNum, int sheetIndex) 
    ```
2. getDataList 会将数据反射到实体类型
  `public <E> List<E> getDataList(Class<E> cls, int... groups)`

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

   - 开始反射放值

   ```java
   List<E> dataList = Lists.newArrayList();
  		for (int i = this.getDataRowNum(); i < this.getLastDataRowNum(); i++)
   ```




