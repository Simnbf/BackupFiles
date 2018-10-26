package com.sbf.RegularBackup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ListFiles {
    public static void main(String[] args) {
      File folder = new File("G:\\Test");
      ListFiles listFiles = new ListFiles();
      System.out.println("reading files before Java8 - Using listFiles() method");
      listFiles.listAllFiles(folder);
      System.out.println("-------------------------------------------------");
      System.out.println("reading files Java8 - Using Files.walk() method");
      listFiles.listAllFiles("G:\\Test");

     }

	private String[] filesInPath;
     // Uses listFiles method  
     public void listAllFiles(File folder){
         System.out.println("In listAllfiles(File) method");
         File[] fileNames = folder.listFiles();
         for(File file : fileNames){
             // if directory call the same method again
             if(file.isDirectory()){
                 listAllFiles(file);
             }else{
                 try {
                     readContent(file);
                 } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
        
             }
         }
     }
     // Uses Files.walk method   
     public void listAllFiles(String path){
         System.out.println("In listAllfiles(String path) method");
         System.out.println("Working with " + path);
         int i = 1;
         try(Stream<Path> paths = Files.walk(Paths.get(path))) {
             paths.forEach(filePath -> {
                 if (Files.isRegularFile(filePath)) {
                     try {
                    	 System.out.println(filePath);
                    	 //this.filesInPath[i] = filePath.toString();
//                    	 i=i+1;
//                         readContent(filePath);
                     } catch (Exception e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                 }
             });
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } 
     }
     public String getFileNames(int i) {
    	 return this.filesInPath[i];
     }
     
     public void readContent(File file) throws IOException{
         System.out.println("read file " + file.getCanonicalPath() );
         try(BufferedReader br  = new BufferedReader(new FileReader(file))){
               String strLine;
               // Read lines from the file, returns null when end of stream 
               // is reached
               while((strLine = br.readLine()) != null){
                System.out.println("Line is - " + strLine);
               }
         }
     }
     
     public void readContent(Path filePath) throws IOException{
         System.out.println("read file " + filePath);
         List<String> fileList = Files.readAllLines(filePath);
         System.out.println("" + fileList);
     }
     
}
