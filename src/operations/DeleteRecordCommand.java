package operations;

import runtimeDatabase.DataBase;
import runtimeDatabase.Table;
import storage.TableFileHandler;

public class DeleteRecordCommand {
	
	//01_Attributes**********************************************
	private DataBase database;
	private String tableName;
	private CriteriaSet condition;
	private Table table;
	private SearchTableCommand searchCommand;
	
	//02_Constructor*********************************************
	public DeleteRecordCommand(DataBase database, String tableName, CriteriaSet condition){
		this.database=database;
		this.tableName=tableName;
		this.condition=condition;
		this.searchCommand=null;
		this.table=null;
	}
	
	//03_Methods*************************************************
	public boolean execute(){
		//01_validate table name
		boolean validTableName=CommonMethods.validateTableName(database, tableName);
		if(validTableName==true){
			//02_find matching indices
			table=database.getTableByName(tableName);
			searchCommand=new SearchTableCommand(table, condition);
			int[] matchingIndices=searchCommand.execute();
			//03_delete matching records (efficient way to reduce ArrayList shift cost)
			for(int i=matchingIndices.length; i>=1; i--){
				int matchingIndex=matchingIndices[i-1];
				table.removeRecord(matchingIndex);
			}
			//04_update storage table
			TableFileHandler tableFileHandler=TableFileHandler.getInstance();
			tableFileHandler.saveTable(table, "xml");
			return true;
		}
		return false;
	}
	

}
