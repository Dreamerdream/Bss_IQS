package com.bss.iqs.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7.
 */
public class MpGenerator {


    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E://");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor("hgh");
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://localhost:3306/data_query_system?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setInclude(new String[]{"template"});
//        sc.setSuperControllerClass("com.baomidou.demo.TestController");
//        sc.setSuperMapperClass("com.baomidou.demo.TestMapper");
//        sc.setSuperServiceClass("com.baomidou.demo.TestService");
//        sc.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
//        sc.setSuperEntityClass("com.baomidou.demo.TestEntity");
//        sc.setEntityColumnConstant(true);
//        sc.setEntityBuilderModel(true);
        mpg.setStrategy(sc);

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.baomidou");
        pc.setModuleName("test");
        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };


        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        /*focList.add(new FileOutConfig("/template/list.jsp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return "D://my_" + tableInfo.getEntityName() + ".jsp";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);*/


        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);

        mpg.execute();
    }
}
