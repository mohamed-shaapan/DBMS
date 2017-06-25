package operations;

import java.util.ArrayList;

import application.DBMS;
import storage.DatabaseFolderHandler;
import storage.RegistryFileHandler;

public class DropDatabaseCommand {

	//01_Attributes**********************************************
	private String databaseName;
	private String repositoryDirectory;
	private DBMS databaseManager;
	
	//02_Constructor*********************************************
	public DropDatabaseCommand(String databaseName, String repositoryDirectory, DBMS databaseManager){
		this.databaseName=databaseName;
		this.repositoryDirectory=repositoryDirectory;
		this.databaseManager=databaseManager;
	}
	
	//03_Methods*************************************************
	public boolean execute(){
		//01_delete DB folder from storage
		DatabaseFolderHandler.deleteDatabaseFolder(databaseName, repositoryDirectory);
		//02_update repository DB registry file
		ArrayList<String> dbNamesListRegistry=databaseManager.getDatabaseNamesList();
		dbNamesListRegistry.remove(databaseName);
		String databaseNames[]=dbNamesListRegistry.toArray(new String[dbNamesListRegistry.size()]);
		RegistryFileHandler.updateDatabaseNamesRegistery(repositoryDirectory, databaseNames);
		return true;
		
	}
	
}
