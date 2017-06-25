package operations;

import runtimeDatabase.Cell;
import runtimeDatabase.DataBase;
import runtimeDatabase.Record;
import runtimeDatabase.Table;
import runtimeDatabase.ValidDataTypes;

public class CommonMethods {

	//Final Tested Method*********************************************************
	public static boolean validateTableName(DataBase databaseToTest, String tableName){
		Table tablesInDatabase[]=databaseToTest.getTables();
		for(Table currentTable:tablesInDatabase){
			String currentName=currentTable.getTableName();
			boolean test=currentName.equalsIgnoreCase(tableName);
			if(test==true){
				return true;
			}
		}
		return false;
	}
	
	//Final Tested Method*********************************************************
	public static boolean validateCompleteOrderedColValues(Table tableToTest, Record recordToTest) {
		//INSERT statement : column names are NOT given 
		//i.e. ordered cells, complete values
		String validCellTypes[]=tableToTest.getColTypes();
		Cell cellsToTest[]=recordToTest.getCells();
		for(int i=1; i<=validCellTypes.length; i++){
			String validCellType=validCellTypes[i-1];
			String valueToTest=cellsToTest[i-1].getValue();
			boolean test=ValidDataTypes.testDataType(validCellType, valueToTest);
			if(test==false){
				return false;
			}
		}
		return true;
	}
	
	//Final Tested Method*********************************************************
	public static boolean validateCellNamesAndValues(Table tableToTest, Record recordToTest){
		//INSERT statement : column names are given 
		//i.e. assuming missing & unordered columns and values
		Cell recordCells[]=recordToTest.getCells();
		String validColNames[]=tableToTest.getColNames();
		String validColTypes[]=tableToTest.getColTypes();
		for(Cell cellToTest:recordCells){
			String cellName=cellToTest.getCellName();
			String cellValue=cellToTest.getValue();
			boolean validCell=false;
			for(int i=1; i<=validColNames.length; i++){
				boolean matchingCellName=cellName.equalsIgnoreCase(validColNames[i-1]);
				if(matchingCellName==true){
					boolean dataTypeTest=ValidDataTypes.testDataType(validColTypes[i-1], cellValue);
					if(dataTypeTest==true){
						validCell=true;
					}
					continue;
				}
			}
			if(validCell==false){
				return false;
			}
		}
		return true;
	}
	
	//Final Tested Method*********************************************************
	public static Record[] shrinkRecordSet(Record matchingRecords[], String colNames[]){
		Record[] resultSet=new Record[matchingRecords.length];
		for(int i=1; i<=resultSet.length; i++){
			resultSet[i-1]=shrinkRecord(matchingRecords[i-1], colNames);
		}
		return resultSet;
	}

	//Final Tested Method*********************************************************
	private static Record shrinkRecord(Record inputRecord, String colNames[]){
		Record outputRecord=new Record(colNames.length);
		for(int i=1; i<=colNames.length; i++){
			String colName=colNames[i-1];
			Cell cellToObtain=inputRecord.getCellByName(colName);
			outputRecord.getCells()[i-1]=cellToObtain;
		}
		return outputRecord;

	}
	
	//Final Tested Method*********************************************************
	public static boolean validateColNames(Table tableToTest, Record recordToTest){
		String recordColNames[]=recordToTest.getColumnNames();
		String tableColNames[]=tableToTest.getColNames();
		//01_SELECT ALL columns
		if(recordColNames[0].equalsIgnoreCase("*")){
			return true;
		}
		//02_check size
		if(recordColNames.length>tableColNames.length){return false;}
		//02_check col names
		for(String colToTest:recordColNames){
			boolean matchFound=false;
			for(String validColName:tableColNames){
				if(colToTest.equalsIgnoreCase(validColName)){
					matchFound=true;
					continue;
				}
			}
			if(matchFound==false){return false;}
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}
