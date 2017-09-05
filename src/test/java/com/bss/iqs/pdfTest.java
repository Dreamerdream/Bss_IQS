package com.bss.iqs;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;

public class pdfTest {

    public static void main(String[] args) throws Exception {

//        OutputStream outputStream = new FileOutputStream(new File(""));
//        String html = null;
//
//        Document document = new Document(PageSize.A4, 50, 50, 60, 60);
//        try {
//            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
//            document.open();
//            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            document.close();
//        }
//        Document document = new Document();
//        try {
//
//            try {
//                PdfWriter.getInstance(document, new FileOutputStream("D:\\Metadados.pdf"));
//            } catch (DocumentException e) {
//                e.printStackTrace();
//            }
//
//            document.open();
//
//            document.addSubject("Gerando PDF em Java");
//            document.addKeywords("www.cialne.com.br");
//            document.addCreator("CIALNE");
//            document.addAuthor("Clairton");
//
//            // adicionando um parágrafo no documento
//            document.add(new Paragraph("Gerando PDF - Java"));
//        } catch (DocumentException | IOException ex) {
//           // Logger.getLogger(Metadados.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        document.close();

//        Document document = new Document();
//        try {
//
//            PdfWriter.getInstance(document, new FileOutputStream( "D:\\Imagem.pdf"));
//
//            document.open();
//
//            // adicionando um parágrafo no documento
//            document.add(new Paragraph("Gerando PDF - Java"));
//            document.add(new Paragraph("Imagem"));
//
//            Image figura = Image.getInstance("D:\\123.jpg");
//            document.add(figura);
//            figura = Image.getInstance("D:\\456.jpg");
//            document.add(figura);
//
//        } catch (DocumentException | IOException ex) {
//        }
//        document.close();

//        Document document = new Document();
//        try {
//            String home = System.getProperty("user.home");
//            PdfWriter.getInstance(document, new FileOutputStream( "D:\\Table.pdf"));
//
//            document.open();
//
//            // adicionando um parágrafo no documento
//            document.add(new Paragraph("Gerando PDF - Java\n\n"));
//
//            Font fontBold = new Font();
//            fontBold.setStyle(Font.BOLD);
//
//            PdfPTable table = new PdfPTable(new float[]{5f,17f,17f});
//
//            PdfPCell cell = new PdfPCell(new Paragraph("id", fontBold));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("nome", fontBold));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("telefone", fontBold));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("1"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("Clairton"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("87696845"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("2"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("Hélio"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("96844844"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("3"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("Emanuel"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("91234567"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("4"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("Gessyca"));
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Paragraph("81823456"));
//            table.addCell(cell);
//
//            document.add(table);
//
//        } catch (DocumentException | IOException ex) {
//        }
//        document.close();
        //String path = pdfTest.class.getResource("/").getPath();



        File file = new File("D:\\success.html");
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


        Document document = new Document(PageSize.A4, 50, 50, 60, 60);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( "D:\\haha.pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(sb.toString()));
        bufReader.close();
        document.close();
//
//
//
//        //页面大小
//        Rectangle rect = new Rectangle(PageSize.B5.rotate());
//        //页面背景色
//        rect.setBackgroundColor(BaseColor.ORANGE);
//
//        Document doc = new Document();
//
//        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\123.pdf"));
//
//        //PDF版本(默认1.4)
//        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
//        writer.setEncryption("Hello".getBytes(), "World".getBytes(),
//                PdfWriter.ALLOW_SCREENREADERS,
//                PdfWriter.STANDARD_ENCRYPTION_128);
//
//        //文档属性
//        doc.addTitle("Title@sample");
//        doc.addAuthor("Author@rensanning");
//        doc.addSubject("Subject@iText sample");
//        doc.addKeywords("Keywords@iText");
//        doc.addCreator("Creator@iText");
//
//        //页边空白
//        doc.setMargins(10, 20, 30, 40);
//
//        doc.open();
//        doc.add(new Paragraph("Hello World"));
//        doc.close();
    }




}
