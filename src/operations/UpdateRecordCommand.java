package operations;

import java.util.ArrayList;

import runtimeDatabase.Cell;
import runtimeDatabase.DataBase;
import runtimeDatabase.Record;
import runtimeDatabase.Table;
import storage.TableFileHandler;

public class UpdateRecordCommand implements Command{

	//01_Attributes**********************************************
	private DataBase database;
	private Record newRecord;
	private String tableName;
	private Table table;
	private SearchTableCommand searchCommand;
	
	//02_Constructor*********************************************
	public UpdateRecordCommand(DataBase database, String tableName, Record newRecord, CriteriaSet condition){
		this.database=database;
		this.newRecord=newRecord;
		this.tableName=tableName;
		this.table=database.getTableByName(tableName);
		this.searchCommand=new SearchTableCommand(table, condition);
	}
	
	//03_Methods*************************************************
	public boolean execute(){
		//01_validate update parameters
		boolean validTableName=CommonMethods.validateTableName(database, tableName);
		boolean validColNamesAndValues=CommonMethods.validateCellNamesAndValues(table, newRecord);
		if(validTableName&&validColNamesAndValues){
			//02_search for matching criteria
			int[] matchingIndices=searchCommand.execute();
			ArrayList<Record> tableRecords=table.getRecordsReference();
			//03_update matching records
			for(int i=1; i<=matchingIndices.length; i++){
				Record recordToUpdate=tableRecords.get(matchingIndices[i-1]);
				Cell valueCells[]=newRecord.getCells();
				for(Cell valueCell:valueCells){
					String cellName=valueCell.getCellName();
					Cell cellToUpdate=recordToUpdate.getCellByName(cellName);
					cellToUpdate.setValue(valueCell.getValue());
				}
			}
			//04_update storage table
			TableFileHandler tableFileHandler=TableFileHandler.getInstance();
			tableFileHandler.saveTable(table, "xml");
			return true;
		}
		return false;
	}

	
	
	
	
	

	public void ObligatoryExecute() {
		// TODO Auto-generated method stub
		
	}
	
	
}
