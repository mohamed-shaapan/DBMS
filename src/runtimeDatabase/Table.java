package runtimeDatabase;

import java.util.ArrayList;

public class Table {
	
	//01_Attributes*************************************
		private DataBase parentDatabase;
		private ArrayList<Record> records;
		private String tableName;
		private String columnNames[];
		private String columnDataTypes[];
		private int recordSize;
		private int colSize;
		
	//02_Constructor************************************
		public Table(String tableName, DataBase parentDatabase, int colSize){
			this.parentDatabase=parentDatabase;
			records=new ArrayList<>();
			this.tableName=tableName;
			columnNames=null;
			columnDataTypes=null;
			this.colSize=colSize;
			this.recordSize=0;
		}
		
		
	//03_Methods****************************************
		public void setColumnNames(String columnNames[]){
			this.columnNames=columnNames;
		}
		public void setColumnDataTypes(String columnDataTypes[]){
			this.columnDataTypes=columnDataTypes;
		}
		public void addNewRecord(Record record){
			//make sure record matches table
			this.records.add(record);
			recordSize++;
		}
	
	//04_Getters****************************************
		public String[] getColNames(){
			return this.columnNames;
		}
		public String[] getColTypes(){
			return this.columnDataTypes;
		}
		public String getTableName(){
			return this.tableName;
		}
		public String getDirectory(){
			if(parentDatabase!=null){
				return parentDatabase.getDirectory();
			}
			return null;
		}
		public int getRecordSize(){
			return this.recordSize;
		}
		public Record[] getRecords(){
			Record[] tmp=records.toArray(new Record[records.size()]);
			return tmp;
		}
		public void removeRecord(int index){
			records.remove(index);
		}
		public ArrayList<Record> getRecordsReference(){
			return this.records;
		}
		public int getColSize(){
			return this.colSize;
		}
		
		
}







