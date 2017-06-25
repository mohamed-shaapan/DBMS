package operations;

import runtimeDatabase.DataBase;
import runtimeDatabase.Record;
import runtimeDatabase.Table;

public class RetrieveRecordCommand {
	
	//01_Attributes**********************************************
	private DataBase database;
	private String tableName;
	private String colNames[];
	private CriteriaSet condition;
	
	private SearchTableCommand searchCommand;
	private Table table;
	
	//02_Constructor*********************************************
	public RetrieveRecordCommand(DataBase database, String tableName, String colNames[], CriteriaSet condition){
		this.database=database;
		this.tableName=tableName;
		this.colNames=colNames;
		this.condition=condition;
		this.searchCommand=null;
		this.table=null;
	}
	
	//03_Methods*************************************************
	public Record[] execute(){
		//01_Validate table name
		boolean validTableName=CommonMethods.validateTableName(database, tableName);
		if(validTableName==true){
			//02_Validate column names
			this.table=database.getTableByName(tableName);
			Record tmp=new Record(colNames.length);
			tmp.setCellNames(colNames);
			boolean validColNames=CommonMethods.validateColNames(table, tmp);
			if(validColNames==true){
				//03_obtain records matching this criteria
				searchCommand=new SearchTableCommand(table, condition);
				Record[] tableRecords=table.getRecords();
				int[] matchingIndices=searchCommand.execute();
				Record[] matchingRecords=new Record[matchingIndices.length];
				for(int i=1; i<=matchingIndices.length; i++){
					int matchingIndex=matchingIndices[i-1];
					matchingRecords[i-1]=tableRecords[matchingIndex];
				}
				//04_select which columns to return
				if(colNames[0].equalsIgnoreCase("*")){
					//return all columns
					return matchingRecords;
				}else{
					//return select columns
					return CommonMethods.shrinkRecordSet(matchingRecords, colNames);
				}
			}
			return null;
		}
		return null;

	}
	



}
