package com.sbf.RegularBackup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListFiles {

	// Uses Files.walk method
	public static List<String> listAllFiles(String path) throws IOException {
		System.out.println("In listAllfiles(String path) method");
		System.out.println("Working with " + path);
		Stream<Path> paths = Files.walk(Paths.get(path));
		List<String> filesFound = new ArrayList<String>();
		paths.forEach(filePath -> {
			// if (Files.isRegularFile(filePath)){
			String temp = filePath.toString();
			filesFound.add(temp);
			// }
		});
		return filesFound;
	}

}
