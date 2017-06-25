package operations;

import runtimeDatabase.Cell;
import runtimeDatabase.DataBase;
import runtimeDatabase.Record;
import runtimeDatabase.Table;
import storage.TableFileHandler;

public class InsertNewRecordCommand implements Command{
	
	//01_Attributes**********************************************
	private DataBase database;
	private Record newRecord;
	private String tableName;
	private Table table;
	
	//02_Constructor*********************************************
	public InsertNewRecordCommand(DataBase database, String tableName, Record newRecord){
		this.database=database;
		this.newRecord=newRecord;
		this.tableName=tableName;
		this.table=database.getTableByName(tableName);
	}
	
	//03_Methods*************************************************
	public boolean execute() {
		Cell tmp=newRecord.getCells()[0];
		String cellName=tmp.getCellName();
		if(cellName==null){
			return executeCaseB();
		}else{
			return executeCaseA();
		}
	}
	
	private boolean executeCaseA(){
		//column names are given
		//i.e. assuming missing & unordered columns and values
		boolean validTableName=CommonMethods.validateTableName(database, tableName);
		boolean validColNamesAndValues=CommonMethods.validateCellNamesAndValues(table, newRecord);
		if(validTableName&&validColNamesAndValues){
			Record recordToInsert=new Record(table);
			Cell valueCells[]=newRecord.getCells();
			for(Cell valueCell:valueCells){
				String cellName=valueCell.getCellName();
				Cell cellToUpdate=recordToInsert.getCellByName(cellName);
				cellToUpdate.setValue(valueCell.getValue());
			}
			table.addNewRecord(recordToInsert);
			//02_update storage table
			TableFileHandler tableFileHandler=TableFileHandler.getInstance();
			tableFileHandler.saveTable(table, "xml");
			return true;
		}
		return false;
	}
	
	private boolean executeCaseB(){
		//column names aren't given
		//i.e. given complete & ordered record values
		boolean validTableName=CommonMethods.validateTableName(database, tableName);
		boolean validColValues=CommonMethods.validateCompleteOrderedColValues(table, newRecord);
		if(validTableName&&validColValues){
			Record recordToInsert=new Record(table);
			Cell cellsToInsert[]=recordToInsert.getCells();
			Cell valuesCells[]=newRecord.getCells();
			for(int i=1; i<=cellsToInsert.length; i++){
				cellsToInsert[i-1].setValue(valuesCells[i-1].getValue());
			}
			table.addNewRecord(recordToInsert);
			//02_update storage table
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
