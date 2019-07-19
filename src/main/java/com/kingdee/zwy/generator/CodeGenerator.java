package com.kingdee.zwy.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

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

    private static  ResourceBundle properties ;
    static {
        //代码生成器配置文件
        try {
            properties= ResourceBundle.getBundle("generator");
        }catch (Exception e){
            System.out.println("无法加载代码生成器配置文件，请检查配置文件路径！");
        }
    }

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        //设置全局配置
        generator.setGlobalConfig(initGlobalConfig());

        // 数据源配置
        generator.setDataSource(initDataSourceConfig());

        // 包配置
        PackageConfig packageInfo = initPackageConfig();
        generator.setPackageInfo(packageInfo);

        // 自定义配置
        generator.setCfg(initInjectionConfig( packageInfo));

        // 配置模板
        generator.setTemplate(initTemplateConfig());

        // 策略配置
        generator.setStrategy(initStrategyConfig());

        //generator.setTemplateEngine(new FreemarkerTemplateEngine());
        
        generator.execute();
    }

    private static StrategyConfig initStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_bm_");
        return strategy;
    }

    private static TemplateConfig initTemplateConfig() {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        //关闭Service生成
        // templateConfig.setService();
        //关闭Controller生成
        // templateConfig.setController();
        //关闭XML，采用自定义配置
        templateConfig.setXml(null);
        return templateConfig;
    }

    private static InjectionConfig initInjectionConfig(PackageConfig packageInfo) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return "E:/deGeneratorTest/src/main/resources/mapper/" + packageInfo.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static PackageConfig initPackageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.zwy.autotax");
        return pc;
    }

    private static DataSourceConfig initDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(properties.getString(URL));
        dsc.setDriverName(properties.getString(DRIVER_NAME));
        dsc.setUsername(properties.getString(USER_NAME));
        dsc.setPassword(properties.getString(PASSWORD));
        return dsc;
    }

    private static GlobalConfig initGlobalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("E:/deGeneratorTest/src/main/java");
//        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("HUZHAOYANG");
        gc.setOpen(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        return gc;
    }


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
