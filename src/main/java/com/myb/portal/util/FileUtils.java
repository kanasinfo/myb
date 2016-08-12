package com.myb.portal.util;

import java.io.File;
import java.io.FileFilter;

/**
 * File utilities used to generate, save files
 * @author J.Mars
 *
 */
public class FileUtils {

	public static final FileFilter DIRECTORY_FILTER = new FileFilter(){
		@Override
		public boolean accept(File pathname) {
			if(pathname.isDirectory())return true;
			else return false;
		}
	};
	
	private FileUtils(){ }
	
	public static String generateUUIDFilename(String fileName) {
		String extention = "";
		int i = fileName.lastIndexOf(".");
		if (i >-1 && i < fileName.length()){
			extention = fileName.substring(fileName.lastIndexOf("."));
		}
		return StringUtils.generateUUID()+extention;
	}
	
	public static File[] listSubDirectories(File file){
		if(file.exists()&&file.isDirectory()){
			return file.listFiles(DIRECTORY_FILTER);
		}else{
			return null;
		}
	}
	public static File[] listSubDirectories(String filepath){
		File file = new File(filepath);
		return listSubDirectories(file);
	}
}
