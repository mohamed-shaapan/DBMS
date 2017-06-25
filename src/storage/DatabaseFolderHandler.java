package storage;

import java.io.File;

public class DatabaseFolderHandler {

	//**************************
	//**************************************************************
	public static boolean createDatabaseFolder(String databaseName, String repositoryDirectory){
		String databaseDirectory=repositoryDirectory+File.separator+databaseName;
		File targetDatabase=new File(databaseDirectory);
		if(!targetDatabase.exists()){
			if(targetDatabase.mkdir()){
				return true;
			}else{
			return false;     
			}        
		}
		return false;
	}
	
	//***************************
	//**************************************************************
	public static boolean deleteDatabaseFolder(String databaseName, String repositoryDirectory){
		String databaseDirectory=repositoryDirectory+File.separator+databaseName;
		File databaseToDelete=new File(databaseDirectory);
		if(databaseToDelete.exists()){
			CommonFileMethods.deleteFile(databaseDirectory);
			return true;
		}
		return false;
	}
	//**************************************************************
	//**************************************************************
	
	
	
}
