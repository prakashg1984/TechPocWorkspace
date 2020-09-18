package com.test.sampledrools;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileReaderUtil {

	private static final Logger log = LoggerFactory.getLogger(FileReaderUtil.class);

	/**
	 * List all files from a directory and its subdirectories
	 * @param directoryName to be listed
	 * @return 
	 */
	/*public static List<File> listFilesAndFilesSubDirectories(String directoryName, List<File> fileList){
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		log.info("checking for file in  directory- "+directory.getAbsolutePath());
		for (File file : fList){
			if (file.isFile()){
				fileList.add(file);
				log.info("adding file to list - "+file.getAbsolutePath());
			} else if (file.isDirectory()){
				listFilesAndFilesSubDirectories(file.getAbsolutePath(),fileList);
			}
		}
		return fileList;
	}*/

	public static List<File> listFilesAndFilesSubDirectories(String directoryName, List<File> fileList){

		List<File> returnFileList = fileList;
		try {
			File directory = new File(directoryName);
			File[] fList = directory.listFiles();
			log.info("checking for file in  directory- {}", directory.getAbsolutePath());
			for (File file : fList) {
				if (file.isFile() && !"README.txt".equals(file.getName())) {
					returnFileList.add(file);
					log.info("adding file to list - {}", file.getAbsolutePath());
				} else if (file.isDirectory()) {
					listFilesAndFilesSubDirectories(file.getAbsolutePath(), returnFileList);
				}
			}
		} catch(Exception ex){
			log.warn("Error while reading input files. Check if the input folder is created", ex);
		}
		return returnFileList;
	}

	public static String readFile(File filePath) throws Exception {
		return FileUtils.readFileToString(filePath);
	}


	public static boolean writeToFile(String filePath,String fileName,String orderNumber, String status, boolean appendToFile) {
		String fullFileName = filePath+ "/" +fileName;
		log.info("Status for customerOrderNumber {} will be written to file: {}",orderNumber,fullFileName);

		File filePathDir = new File(filePath);
		if (! filePathDir.exists()){
			filePathDir.mkdir();
		}

		boolean isSuccess = false;
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String timeNow = dateFormat.format(date); 

			//String dataToWrite = timeNow + "|" + orderNumber + "|" + status;
			String dataToWrite = status;

			File file = new File(fullFileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), appendToFile);
			bw = new BufferedWriter(fw);
			//bw.newLine();
			bw.write(dataToWrite);
			log.info("Status for customerOrderNumber {} written to file: {} successfully.",orderNumber,fullFileName);
			isSuccess=true;
		} catch (IOException e) {
			log.error("Failed to write status for customerOrderNumber {} to file: {}. Error is:",orderNumber,fullFileName,e);

		} finally {

			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				log.warn("Closing objects failed. Error is:",ex);
			}
		}
		return isSuccess;
	}	

	public static void backUpFile(String fileName, String backupPath, String dataPath) {
		System.out.println("fileName: "+fileName +"backupPath: "+backupPath +"dataPath: "+dataPath );
		File fileEntry = new File(fileName);
		String backUpFileName = fileName.replaceAll(dataPath, backupPath);
		File backUpFileNameFile = new File(backUpFileName);
		String backupFilePath = backUpFileNameFile.getParent();

		File bkpDirectory = new File(backupFilePath);
		if (! bkpDirectory.exists()){
			bkpDirectory.mkdirs();
		}

		if (!fileEntry.isDirectory()) {
			if (fileEntry.renameTo(new File(backUpFileName))) {
				log.info("File, {} has been moved to back up folder",fileName);
			}
		}


	}

}
