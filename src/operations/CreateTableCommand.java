package operations;

import runtimeDatabase.DataBase;
import runtimeDatabase.Table;
import storage.RegistryFileHandler;
import storage.TableFileHandler;

public class CreateTableCommand {
	
	private DataBase database;
	private String tableName;
	private String[] colNames;
	private String[] colTypes;
	
	public CreateTableCommand(DataBase database, String tableName, String colNames[], String colTypes[]){
		this.database=database;
		this.tableName=tableName;
		this.colNames=colNames;
		this.colTypes=colTypes;
	}
	
	public boolean execute(){
		//01_create table in memory
		Table table=new Table(tableName, database, colNames.length);
		table.setColumnNames(colNames);
		table.setColumnDataTypes(colTypes);
		database.addTable(table);
		//02_create table in database repository
		TableFileHandler tableFileHandler=TableFileHandler.getInstance();
		tableFileHandler.saveTable(table, "xml");
		//03_update tables names registry file
		RegistryFileHandler.updateTableNamesRegistery(database.getDirectory(), database.getTableNames());
		return true;
	}

}
