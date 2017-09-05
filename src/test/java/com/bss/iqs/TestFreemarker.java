package com.bss.iqs;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestFreemarker {


    public static void main(String[] args) throws  Exception{


        TestFreemarker testFreemarker = new TestFreemarker();
        testFreemarker.testFreemarker();

    }

    public void testFreemarker() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("msg", "胡广辉");
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setDirectoryForTemplateLoading(new File("D:\\"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);//.RETHROW
        configuration.setClassicCompatible(true);
        Template template = configuration.getTemplate("abcde.ftl"); // freeMarker template

        Writer out = new FileWriter(new File("D:\\abcde.html"));


        template.process(model,out);

        out.close();
    }
}
