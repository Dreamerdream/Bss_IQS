package com.bss.iqs.util;

import com.bss.iqs.value.FilePath;
import com.lowagie.text.Annotation;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    // 保存group logo文件
    public static String uploadFile(MultipartFile file){

        String filePath = null;

        OutputStream os = null;
        try {
            //文件名
            String fileName = file.getOriginalFilename();
            // 文件上传后的路径
            filePath = FilePath.LOGO_PATH + File.separator + fileName;
            InputStream inputStream = file.getInputStream();
            os = new FileOutputStream( filePath);
            byte[] bs = new byte[1024];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            filePath = null;
        }finally {
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    filePath = null;
                }
            }

        }
       return filePath;
    }

    //将模板的内容保存为.fdl文件
    public static void templat2ftl(String content,Integer id){
        byte[] b=content.getBytes();
        BufferedOutputStream stream = null;
        File file = null;
        String fileName = "template_" + id + ".ftl"; //.fdl文件
        try {
            file = new File(FilePath.FREEMARK_PATH + File.separator + fileName );
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }



    //将freemarker生成html
    public static void ftl2html(Map<String,Object> model,Integer id) throws Exception{
        //读取freemark的目录
        File file = new File(FilePath.FREEMARK_PATH);
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setDirectoryForTemplateLoading(file);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);//.RETHROW
        configuration.setClassicCompatible(true);
        String freemarkName = "template_" + id + ".ftl";
        Template template = configuration.getTemplate(freemarkName); // freeMarker template
        String fileName =  "html_" + id + ".html";
        Writer out = new FileWriter(new File(FilePath.HTML_PATH + File.separator + fileName));
        template.process(model,out);
        out.close();
    }


    //读取html文件成字符串
    public static String  readFile(Integer id) throws Exception{
        String path = FilePath.HTML_PATH + File.separator + "html_" + id + ".html";
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufReader=new BufferedReader(fileReader);
        String content = "";
        StringBuilder sb = new StringBuilder();

        while(content != null){
            content = bufReader.readLine();

            if(content == null){
                break;
            }

            sb.append(content.trim());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception{

        String content = "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <title>Getting Started: Serving Web Content</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h4 th:text=\"${msg}\"></h4>\n" +
                "<form th:action=\"@{/user/login}\" method =\"post\">\n" +
                "    <div><input name=\"username\"  type=\"text\"/></div>\n" +
                "    <div><input name=\"password\"  type=\"password\"/></div>\n" +
                "    <input type=\"submit\" value=\"提交\"/>\n" +
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
//        String path ="D:\\";
//        uploadFile(content,path);
     //   templat2ftl(content,66);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","hgh");
        ftl2html(map,66);
    }
}
