package operations;

import java.io.File;
import java.util.ArrayList;

import application.DBMS;
import storage.DatabaseFolderHandler;
import storage.RegistryFileHandler;

public class CreateDatabaseCommand {

	//01_Attributes**********************************************
	private String databaseName;
	private String repositoryDirectory;
	private String databaseDirectory;
	private DBMS databaseManager;
	
	//02_Constructor*********************************************
	public CreateDatabaseCommand(String databaseName, String repositoryDirectory, DBMS databaseManager){
		this.databaseName=databaseName;
		this.repositoryDirectory=repositoryDirectory;
		this.databaseDirectory=repositoryDirectory+File.separator+databaseName;
		this.databaseManager=databaseManager;
	}
	
	//03_Methods*************************************************
	public boolean execute(){
		//01_try to create database folder
		boolean createDatabaseFolder=DatabaseFolderHandler.createDatabaseFolder(databaseName, repositoryDirectory);
		if(createDatabaseFolder==true){
			//02_create tables names registry file
			RegistryFileHandler.updateTableNamesRegistery(databaseDirectory, null);
			//03_update repository DB registry file 
			ArrayList<String> dbNamesListRegistry=databaseManager.getDatabaseNamesList();
			dbNamesListRegistry.add(databaseName);
			String databaseNames[]=dbNamesListRegistry.toArray(new String[dbNamesListRegistry.size()]);
			RegistryFileHandler.updateDatabaseNamesRegistery(repositoryDirectory, databaseNames);
			return true;
		}
		return false;
	}
	
	
	
}
