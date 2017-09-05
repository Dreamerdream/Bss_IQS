package com.bss.iqs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileHelper {

   public static String  readFile(String path) throws Exception{
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
}
