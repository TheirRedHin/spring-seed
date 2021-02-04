package com.redhin;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

  private final static String DataSourceUrl = "jdbc:mysql://192.168.1.36:3308/test?useUnicode=true&useSSL=false&characterEncoding=utf8";
  private final static String DataSourceDriver = "com.mysql.cj.jdbc.Driver";
  private final static String DataSourceUser = "root";
  private final static String DataSourcePwd = "root";
  private final static String Author = "";
  private final static String Parent = "com.redhin.springseed";
  private final static String ModuleName = "test";


  public static void main(String[] args) throws Exception {
    generator();
  }

  private static void generator() throws IOException, SQLException {
    AutoGenerator gen = new AutoGenerator();
    // 1.设置数据源
    gen.setDataSource(getDataSource());
    // 2.设置全局配置
    gen.setGlobalConfig(getGlobalConfig());
    // 3.设置策略配置
    gen.setStrategy(getStrategyConfig("mc_card"));
    // 4.设置包配置
    gen.setPackageInfo(getPackageConfig());
    // 5.设置自定义属性
    gen.setCfg(getInjectionConfig());
    // 6.设置模板引擎
    gen.setTemplateEngine(new FreemarkerTemplateEngine());
    // 7.设置模板
    gen.setTemplate(getTemplateConfig());
    // 8.执行代码生成
    gen.execute();
  }

  /**
   * 模板配置
   *
   * @return 模板配置
   */
  private static TemplateConfig getTemplateConfig() {
    return new TemplateConfig()
        // 默认不输出Mapper.xml文件，通过自定义输出配置生成Mapper.xml文件
        .setXml(null)
        .setController("templates/controller.java")
        .setEntity("templates/entity.java")
        .setMapper("templates/mapper.java")
        .setService("templates/service.java")
        .setServiceImpl("templates/serviceImpl.java");
  }

  /**
   * 数据源配置
   *
   * @return 数据源
   * @throws SQLException
   */
  private static DataSourceConfig getDataSource() throws SQLException {
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(DataSourceUrl);
    dsc.setDriverName(DataSourceDriver);
    dsc.setUsername(DataSourceUser);
    dsc.setPassword(DataSourcePwd);
    return dsc;
  }

  /**
   * 全局配置
   *
   * @return 全局配置
   */
  private static GlobalConfig getGlobalConfig() {
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir(projectPath + "\\src\\main\\java");
    gc.setAuthor(Author);
    gc.setOpen(false);
    return gc;
  }

  /**
   * 包配置
   *
   * @return 包配置
   */
  private static PackageConfig getPackageConfig() {
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(ModuleName);
    pc.setParent(Parent);
    return pc;
  }

  private static StrategyConfig getStrategyConfig(String... tableNames) {
    return new StrategyConfig()
        // .setCapitalMode(true)// 全局大写命名
        //.setDbColumnUnderline(true)//全局下划线命名
        //.setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
        .setInclude(tableNames) // 需要生成的表
        .setRestControllerStyle(true);
//        // 自定义实体父类
//        .setSuperEntityClass("com.hotent.base.entity.BaseModel")
//        // 自定义 service 父类 默认IService
//        .setSuperServiceClass("com.hotent.base.manager.BaseManager")
//        // 自定义 service 实现类父类 默认ServiceImpl
//        .setSuperServiceImplClass("com.hotent.base.manager.impl.BaseManagerImpl")
//        // 自定义 controller 父类
//        .setSuperControllerClass("com.hotent.base.controller.BaseController");
  }

  private static InjectionConfig getInjectionConfig() {
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };

    // 如果模板引擎是 freemarker
    String templatePath = "/templates/mapper.xml.ftl";
    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return System.getProperty("user.dir") + "/src/main/resources/mapper/" + getPackageConfig()
            .getModuleName()
            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });
    cfg.setFileOutConfigList(focList);
    return cfg;
  }

}
