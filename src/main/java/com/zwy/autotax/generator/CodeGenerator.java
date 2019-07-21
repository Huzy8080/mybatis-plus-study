package com.zwy.autotax.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName CodeGenerator
 * @Description 代码生成器
 * @Author HUZHAOYANG
 * @Date 2019/6/27 19:23
 * @Version 1.0
 */
public class CodeGenerator {
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";
    public static final String URL = "url";
    public static final String DRIVER_NAME = "driverName";
    public static final String OUTPUT_DIR = "OutputDir";
    public static final String AUTHOR = "author";
    public static final String PARENT_PACKAGE = "parentPackage";
    public static final String TABLE_PREFIX = "tablePrefix";
    public static final String TABLE_NAME = "tableName";
    public static final String DAO = "dao";

    private static ResourceBundle properties;

    static {
        //代码生成器配置文件
        try {
            properties = ResourceBundle.getBundle("generator");
        } catch (Exception e) {
            System.out.println("无法加载代码生成器配置文件，请检查配置文件路径！");
        }
    }

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        //设置全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(properties.getString(OUTPUT_DIR));
        gc.setAuthor(properties.getString(AUTHOR));
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(properties.getString(URL));
        dsc.setDriverName(properties.getString(DRIVER_NAME));
        dsc.setUsername(properties.getString(USER_NAME));
        dsc.setPassword(properties.getString(PASSWORD));
        generator.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(properties.getString(PARENT_PACKAGE));
        pc.setMapper(DAO);
        generator.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
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
                return properties.getString("outputDirXml") + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        //关闭Service生成
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        //关闭Controller生成
        templateConfig.setController(null);
        //关闭XML，采用自定义配置
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(properties.getString(TABLE_NAME).split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(properties.getString(TABLE_PREFIX));
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        generator.execute();
    }

}
