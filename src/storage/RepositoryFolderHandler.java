package storage;

import java.io.File;

public class RepositoryFolderHandler {

	public static void initializeRepositoryFolder(String repositoryDirectory){
		File file=new File(repositoryDirectory);
        if(!file.exists()){
            if(file.mkdir()){
            	System.out.println("Repository is created!");
            }
            else{
            	System.out.println("Failed to create Repository!");
            }
        }else{
        	System.out.println("Repository Already Exists!");
        }
	}
	
}
