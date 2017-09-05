package com.bss.iqs;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;



public class html2word {

    public static void main(String[] args) throws Exception{


        htmlToWord("D:\\success.html","D:\\111.docx");
    }
    public static void htmlToWord(String srcPath,String fileName) throws Exception {
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;
        try {
            if (!"".equals(fileName)) {
                File fileDir = new File(fileName);
                if (!fileDir.exists()) {
                    String content = readFile(srcPath);
                    byte b[] = content.getBytes();
                    bais = new ByteArrayInputStream(b);
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                    fos = new FileOutputStream(fileName);
                    poifs.writeFilesystem(fos);
                    bais.close();
                    fos.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) fos.close();
            if(bais != null) bais.close();
        }
    }

    public static String readFile(String filename) throws Exception {
        StringBuffer buffer = new StringBuffer("");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            buffer = new StringBuffer();
            while (br.ready())
                buffer.append((char) br.read());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(br!=null) br.close();
        }
        return buffer.toString();
    }
}
