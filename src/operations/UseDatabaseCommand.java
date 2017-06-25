package operations;

import java.io.File;

import runtimeDatabase.DataBase;
import runtimeDatabase.Table;
import storage.RegistryFileHandler;
import storage.TableFileHandler;

public class UseDatabaseCommand {
	
	//01_Attributes**********************************************
	private DataBase database;
	private String databaseDirectory;
	private String repositoryDirectory;
	
	//02_Constructor*********************************************
	public UseDatabaseCommand(String databaseName, String repositoryDirectory){
		database=new DataBase(databaseName, repositoryDirectory);
		databaseDirectory=repositoryDirectory+File.separator+databaseName;
		this.repositoryDirectory=repositoryDirectory;
	}
	
	//03_Methods*************************************************
	public DataBase execute(){
		boolean dbRegistered=RegistryFileHandler.databaseRegistered(database.getName(), repositoryDirectory);
		if(dbRegistered==true){
			File selectedDatabase=new File(databaseDirectory);
			if(selectedDatabase.isDirectory()){
				String tableList[]=RegistryFileHandler.loadTableNamesRegistery(databaseDirectory);
				if(tableList!=null){
					for(String tableName:tableList){
						TableFileHandler tableFileHandler=TableFileHandler.getInstance();
						Table currentTable=tableFileHandler.loadTable(tableName, database, databaseDirectory, "xml");
						database.addTable(currentTable);
					}
				}
				return database;
		    }
			return null;
		}
		return null;
		
	}
	

}
