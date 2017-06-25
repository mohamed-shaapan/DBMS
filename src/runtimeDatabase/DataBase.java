package runtimeDatabase;

import java.io.File;
import java.util.ArrayList;

public class DataBase {
	
	//01_Attributes***********************************
		private ArrayList<Table> tables;
		private String directory;
		private String databaseName;
		
	//02_Constrcutor**********************************
		public DataBase(String databaseName){
			this.tables=new ArrayList<Table>();
			this.directory="";
			this.databaseName=databaseName;
		}
		public DataBase(String databaseName, String repositoryDirectory){
			this.tables=new ArrayList<Table>();
			this.directory=repositoryDirectory+File.separator+databaseName;
			this.databaseName=databaseName;
		}
	
	//03_Methods**************************************
		public void addTable(Table newTable){
			tables.add(newTable);
		}
		public Table[] getTables(){
			Table[] tmp=tables.toArray(new Table[tables.size()]);
			return tmp;
		}
		public Table getTableByName(String tableName){
			for(Table table:tables){
				if(tableName.equalsIgnoreCase(table.getTableName())){
					return table;
				}
			}
			return null;
		}
		public String getDirectory(){
			return directory;
		}
		public void removeTable(int index){
			tables.remove(index);
		}
		public int getTableIndex(String tableName){
			for(int i=1; i<=tables.size(); i++){
				boolean matchTest=tables.get(i-1).getTableName().equalsIgnoreCase(tableName);
				if(matchTest==true){
					return i-1;
				}
			}
			return -1;
		}
		public String getName(){
			return this.databaseName;
		}
		public void setDirectory(String repositoryDirectory){
			this.directory=repositoryDirectory+File.separator+databaseName;
		}
		public String[] getTableNames(){
			if(this.tables.size()==0){
				return null;
			}
			String tableNames[]=new String[this.tables.size()];
			for(int i=1; i<=tableNames.length; i++){
				tableNames[i-1]=tables.get(i-1).getTableName();
			}
			return tableNames;
		}

}
