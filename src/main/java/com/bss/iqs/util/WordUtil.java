package com.bss.iqs.util;

import com.bss.iqs.value.FilePath;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class WordUtil {

    public static void htmlToWord(String fileName,Integer id) throws Exception {
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;

        try {
            if (!"".equals(fileName)) {
                File fileDir = new File(fileName);
                if (!fileDir.exists()) {
                    String content = readFile(id);
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

    public static String readFile(Integer id) throws Exception {
        String filename = FilePath.HTML_PATH + File.separator + "html_" + id + ".html";
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

    public static void main(String[] args) throws Exception{

        htmlToWord("D:\\hghghghg.doc",66);

    }
}
