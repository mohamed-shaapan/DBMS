package application;

import operations.CriteriaSet;
import runtimeDatabase.Record;

public interface IDBMS {
	
	
	//00_Implement Methods by passing user query
	public boolean implementQuery(String userQuery);
	
	//01_Database Operations****************************************
	public boolean createDatabase(String databaseName);
	public boolean dropDatabase(String databaseName);
	public boolean connectToDatabase(String databaseName);
	public boolean disconnectFromDatabase();
	
	//02_Table Operations*******************************************
	public boolean createTable(String tableName, String colNames[], String colTypes[]);
	public boolean dropTable(String tableName);
	
	//03_Record Operations******************************************
	public boolean addNewRecord(String tableName, String[] colNames, String[] colValues);
	public Record[] retireveRecord(String tableName, String[] colNames, CriteriaSet condition);
	public boolean updateRecord(String tableName, String[] colNames, String[] newData, CriteriaSet condition);
	public boolean deleteRecord(String tableName, CriteriaSet condition);
	
	
	
}
