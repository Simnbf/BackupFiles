package com.sbf.RegularBackup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class BackupFiles {
	public static void main(String[] args) {
		String fromDir = args[0], toDir = args[1];

		File iFile = new File(fromDir);
		File trgBDir = new File(toDir);
		if (!iFile.isDirectory()) {
			System.out.println("Input directory " + fromDir + " is not a directory");
			return;
		}
		if (!trgBDir.isDirectory()) {
			System.out.println("Output directory " + toDir + " is not a directory");
			return;
		}
		System.out.println("Going to backup files from " + fromDir + " to " + toDir);
		String lowDir = iFile.getName();
		String actualTarget = toDir + "/" + lowDir;
		File trgDir = new File(actualTarget);
		trgDir.mkdir();
		System.out.println("Created directory: " + actualTarget);

		// Get list of files from source directory
		List<String> filesFound = new ArrayList();
		try {
			filesFound = ListFiles.listAllFiles(fromDir);
			int x = 0;
			while (x < filesFound.size()) {
				String copyFile = filesFound.get(x);
				// Copy from source to target dir
				copyFilesToTarget(fromDir, copyFile, actualTarget);
				x++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		return;
	}

	public static int copyFilesToTarget(String srcbase, String file2Copy, String trgDir) {

		File inFile = new File(file2Copy);
		// Get either the file name or directory, depending on what this is
		String lowQual = inFile.getName();

		String z = file2Copy.replace(srcbase, trgDir);

		File target = new File(z);
		if (inFile.isDirectory()) {
			target.mkdirs();
		}

		// If this "file" is actually a directory, we'll create it in target so that the
		// files are distributed into directories
		if (inFile.isDirectory()) {
			if (target.mkdir()) {
				System.out.println("Directory " + lowQual + " created in target " + z);
				return 0;
			} else {
				System.out.println("Directory " + lowQual + " already exists in target " + z);
				return 1;
			}
		}

		// Just a regular file, let's do the copy
		System.out.println("Copying " + file2Copy + " to " + z);
		try {
			FileUtils.copyFile(inFile, target);
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 12;
		}
	}

}