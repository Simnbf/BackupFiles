package com.sbf.RegularBackup;

public class BackupFiles {
    public static void main( String[] args )
    {
        String fromDir = args [0], toDir = args[1];
        System.out.println("Going to backup files from " + fromDir + " to " + toDir);
        ListFiles listFiles = new ListFiles();
        listFiles.listAllFiles(fromDir);
//        System.out.println(listFiles.getFileNames(1));
        return;
    }
}