package storage;

import java.io.File;


public class CommonFileMethods {

	//**************************************************************
	//**************************************************************
	public static String getFileNameExtention(String fileName){
		String extension="";
		int i=fileName.lastIndexOf('.');
		if(i>0){
		    extension=fileName.substring(i+1);
		}
		return extension;
	}

	//**************************************************************
	//**************************************************************
	public static void deleteFile(String fileDirectory){
		File fileToDelete=new File(fileDirectory);
		deleteFileImplementation(fileToDelete);
	}
	
	private static void deleteFileImplementation(File selectedFile){
	    if(selectedFile.isDirectory()){
	    	for(File subFile:selectedFile.listFiles()){
	    		deleteFileImplementation(subFile);   
	    	}
	    }
	    selectedFile.delete();
	}
	
	//**************************************************************
	//**************************************************************
	
	
	
	
	
	
}
