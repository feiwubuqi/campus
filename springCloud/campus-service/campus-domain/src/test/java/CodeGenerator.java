import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 *
 * @author xianglj@belink.com 2019年7月5日
 */
public class CodeGenerator {

	public static void main(String[] args) {

		// 数据库表名
		String tableName = "account_info";

		// 作者
		String author = "zhengmh@belink.com";
		
		// 数据库地址
		String url = "jdbc:mysql://192.168.75.81:3306/dormitory_manage?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
		
		// 数据库用户名
		String username = "root";
		
		// 数据库密码
		String password = "root123456";

		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor(author);
		gc.setOpen(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(url);
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername(username);
		dsc.setPassword(password);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName("domain");
		pc.setEntity("model");
		pc.setMapper("repository");
		pc.setParent("com.campus");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
			}
		};

		// 如果模板引擎是 freemarker
		String templatePath = "/template/mapper.xml.ftl";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		
		// 自定义mapper
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mybatis/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		
		// 自定义service
		templatePath = "/template/service.java.ftl";
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/java/com/campus/domain/service" + "/"
						+ tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
			}
		});
		
		// 自定义repository
		templatePath = "/template/repository.xml.ftl";
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/java/com/campus/domain/repository" + "/"
						+ "I" + tableInfo.getEntityName() + "Repository" + StringPool.DOT_JAVA;
			}
		});
		
		
		// cfg.setFileCreate(new IFileCreate() {
		// @Override
		// public boolean isCreate(ConfigBuilder configBuilder, FileType fileType,
		// String filePath) {
		// // 判断自定义文件夹是否需要创建
		// checkDir("调用默认方法创建的目录");
		// return false;
		// }
		// });
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setService(null);
		templateConfig.setController(null);
		templateConfig.setServiceImpl(null);
		templateConfig.setMapper(null);
		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// strategy.setSuperEntityClass("com.ynet.user.domain.model");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
		// strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
		// 写于父类中的公共字段
		// strategy.setSuperEntityColumns("id");
	//strategy.setInclude(tableName);
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
