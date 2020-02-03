package com.sinosoft.nimg;

import com.github.framework.evo.code.generator.GenConfig;
import com.github.framework.evo.code.generator.GenParam;
import com.github.framework.evo.code.generator.GenType;
import com.github.framework.evo.code.generator.Generator;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

	public static void main(String[] args) {
        doGenerate("test11","test11");
	}

	private static void doGenerate(String module, String... tabName) {
		GenConfig gc = new GenConfig();
		gc.setBasePackage("com.sinosoft.nimg.persistence");

		// 设置要忽略的表名前缀，默认空
		//gc.setIgnoreTablePrefixs(new String[] { "prpl", "prpd" });

		// 设置PO是否保留前缀(设置忽略表名前缀时)，默认true
		gc.setKeepPrefixForPO(true);

		// 设置是否默认开启二级缓存，默认false
		gc.setDefaultCache(true);

		// 设置取操作时间函数，默认数据库自适应
		// gc.setOperateTimeForHisFunc("sysdate");

		// 是否启用删除标志模式（不真删除，只是设置标志deleted_flag字段为1），默认false
		gc.setDeletedFlagMode(true);

		/** 设置忽略下划线，默认为false */
		gc.setDbColumnUnderline(false);

		// 设置基本保存目录（Java源代码根目录）
		gc.setSaveDir("src/main/java");

		// 设置不开启缓存处理
		gc.setDefaultCache(false);

		// mysql 数据库相关配置
		//gc.setDbDriverName("com.mysql.cj.jdbc.Driver");
		gc.setDbDriverName("oracle.jdbc.driver.OracleDriver");
		//gc.setDbCatalog("table"); // MySQL的Catalog通常和数据库名一致，采用8.0+版驱动后需要设置
		//gc.setDbSchema("payment");  // oracle 不能使用这个属性，否则生成失败
		gc.setDbUser("nimg");
		gc.setDbPassword("nimg");
		gc.setDbUrl("jdbc:oracle:thin:@192.168.1.9:1521:prod");

		// 支持生成的文件类型:生成PO、BASE_MAPPER_XML（自动覆盖）、Dao、VO、MapperXML（不覆盖）
		gc.setGenTypes(
				new GenType[] { GenType.VO, GenType.PO, GenType.DAO, GenType.BASE_MAPPER_XML, GenType.MAPPER_XML });

		List<GenParam> paramList = new ArrayList<GenParam>();
		/**
		 * 请保持生成参数的完整，不要注释
		 */
		paramList.add(new GenParam(module, tabName));

		Generator generator = new Generator();
		generator.setGenConfig(gc);
		generator.setParamList(paramList);
		try {
			generator.generate();
			System.out.println(tabName + " 代码生成成功!");
		}catch (Exception ex){
			System.out.println(tabName + " 代码生成失败!\n" + ex);
		}
	}
}
