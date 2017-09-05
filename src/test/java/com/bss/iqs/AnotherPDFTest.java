package com.bss.iqs;

import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class AnotherPDFTest {


    public static void main(String[] args) throws Exception{

        OutputStream os = new FileOutputStream("D:\\963.pdf");
        ITextRenderer renderer = new ITextRenderer();
        String url = new File("D:\\error.html").toURI().toURL().toString();

        renderer.setDocument(url);

        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/Fonts/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //解决图片的相对路径问题
     //   renderer.getSharedContext().setBaseURL("file:/D:/");
        renderer.layout();
        renderer.createPDF(os);

        os.flush();
        os.close();
    }

}
