package storage;

import java.io.File;

import runtimeDatabase.DataBase;
import runtimeDatabase.Table;

public final class TableFileHandler {
	
	//01_Singleton Class*****************************
	//**************************************************************
		private static final TableFileHandler INSTANCE=new TableFileHandler();
		
		private TableFileHandler(){}
		
		public static TableFileHandler getInstance(){
			return INSTANCE;
		}
	//**************************************************************
	//**************************************************************
	
	
	//02_Methods*************************************
	//**************************************************************
	public boolean saveTable(Table table, String format){
		if(format.equalsIgnoreCase("xml")){
			return XMLFormatHandler.saveTable(table);
		}
		else if(format.equalsIgnoreCase("json")){
			
		}
		return false;
	}
	//**************************************************************
	public Table loadTable(String tableName, DataBase parentDatabase, String databaseDirectory, String format){
		if(format.equalsIgnoreCase("xml")){
			return XMLFormatHandler.loadTable(tableName, parentDatabase, databaseDirectory);
		}
		else if(format.equalsIgnoreCase("json")){
			
		}
		return null;
	}
	//**************************************************************
	public boolean removeTable(String tableName, String databaseDirectory){
		String tableXMLFileDirectory=databaseDirectory+File.separator+tableName+".xml";
		String tableDTDFileDirectory=databaseDirectory+File.separator+tableName+".dtd";
		CommonFileMethods.deleteFile(tableXMLFileDirectory);
		CommonFileMethods.deleteFile(tableDTDFileDirectory);
		return false;
	}
	
	
	
	
	
}
