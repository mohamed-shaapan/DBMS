package operations;

import runtimeDatabase.DataBase;
import storage.RegistryFileHandler;
import storage.TableFileHandler;

public class DropTableCommand {

	private DataBase database;
	private String tableName;
	
	public DropTableCommand(DataBase database, String tableName){
		this.database=database;
		this.tableName=tableName;
	}
	
	public boolean execute(){
		boolean validTableName=CommonMethods.validateTableName(database, tableName);
		if(validTableName==true){
			//01_remove table from memory database
			int tableIndex=database.getTableIndex(tableName);
			database.removeTable(tableIndex);
			//02_remove table from storage
			TableFileHandler tableFileHandler=TableFileHandler.getInstance();
			tableFileHandler.removeTable(tableName, database.getDirectory());
			//03_update tables names registry file
			RegistryFileHandler.updateTableNamesRegistery(database.getDirectory(), database.getTableNames());
			return true;
		}
		return false;
	}
	
}
