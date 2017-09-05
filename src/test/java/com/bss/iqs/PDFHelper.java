package com.bss.iqs;


import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

public class PDFHelper {

    public static void main(String[] args) throws Exception{
        String templatePath="D:\\success.html";
        String targetPath="D:\\购票证明3.pdf";
        String watermarkPath="D:\\hhh.png";
        createPdfWithMark(templatePath,targetPath,watermarkPath,400,400);
    }

    public static void createPdfWithMark(String htmlFile,String targetUrl,String markPicUrl,int xCoord,int yCoord) throws Exception {
        String tempPdf="D:\\"+Thread.currentThread().getId()+"temp.pdf";
        createPdfByHtmlFile(htmlFile,tempPdf);
        addPdfMark(tempPdf, targetUrl, markPicUrl, xCoord, yCoord, 1);
    }

    public static PdfWriter createPdfByHtmlFile(String htmlFile, String targetUrl) throws Exception {
        String html = FileHelper.readFile(htmlFile);
        return createPdfByHtml(html,targetUrl);
    }


    public static PdfWriter createPdfByHtml(String html,String targetUrl) throws Exception {

        List<Element> parseToList = HTMLWorker.parseToList(new StringReader(html), null, new HashMap<String, Object>());
        Document d = new Document(PageSize.A4);

        PdfWriter writer=PdfWriter.getInstance(d, new FileOutputStream(new File(targetUrl)));
        d.open();

        for (Element e : parseToList) {
            fixChineseCoding(e);
            d.add(e);
        }
        System.out.println("已完成");
        d.close();

        return writer;
    }


    public static void addPdfMark(String InPdfFile, String outPdfFile, String markImagePath,int xCoord,int yCoord, int pageSize) throws Exception {

        PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());

        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));

        Image img = Image.getInstance(markImagePath);// 插入水印

        img.setAbsolutePosition(xCoord, yCoord);


        for(int i = 1; i <= pageSize; i++) {

            PdfContentByte under = stamp.getUnderContent(i);

            under.addImage(img);

        }

        stamp.close();// 关闭

        File tempfile = new File(InPdfFile);

        if(tempfile.exists()) {

            tempfile.delete();
        }

    }

    private static BaseFont bfChinese;
    private static Font cfont ;

    /**
     * 修正中文乱码问题
     * 现只支持table与p,div,span等
     * @param e
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private static void  fixChineseCoding(Element e) throws IOException, DocumentException {
        if(bfChinese==null){
            bfChinese = BaseFont.createFont("C:/Windows/Fonts/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
        if(e instanceof Paragraph){
            for(Chunk c:e.getChunks()){//解决中文乱码
                cfont = new Font(bfChinese, 20, Font.NORMAL);
                cfont.setColor(c.getFont().getColor());
                cfont.setSize(c.getFont().getSize());
                c.setFont(cfont);
            }
            return;
        }
        if(e instanceof PdfPTable){
            PdfPTable table=(PdfPTable)e;
            for(PdfPRow row:table.getRows()){
                for(PdfPCell cell:row.getCells()){
                    if(cell!=null&&cell.getCompositeElements()!=null){
                        for(Element comp:cell.getCompositeElements()){
                            fixChineseCoding(comp);
                        }
                    }
                }
            }
        }
    }


}
