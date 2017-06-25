package application;

import java.util.ArrayList;

import operations.CreateDatabaseCommand;
import operations.CreateTableCommand;
import operations.CriteriaSet;
import operations.DeleteRecordCommand;
import operations.DropDatabaseCommand;
import operations.DropTableCommand;
import operations.InsertNewRecordCommand;
import operations.RetrieveRecordCommand;
import operations.UpdateRecordCommand;
import operations.UseDatabaseCommand;
import queryParser.QueryParser;
import runtimeDatabase.DataBase;
import runtimeDatabase.Record;
import storage.RegistryFileHandler;
import storage.RepositoryFolderHandler;

public class DBMS implements IDBMS{
	
	//******************************
	//01_Attributes****************************************************
		private String repositoryDirectory;
		private DataBase currentDatabase;
		private QueryParser queryParser;
		private ArrayList<String> databaseNamesList;
	
	//******************************
	//02_Constructor***************************************************
		public DBMS(String repositoryDirectory){
			this.repositoryDirectory=repositoryDirectory;
			this.currentDatabase=null;
			queryParser=new QueryParser(this);
			//repository folder
			RepositoryFolderHandler.initializeRepositoryFolder(repositoryDirectory);
			//database registry file
			this.databaseNamesList=RegistryFileHandler.initializeDatabaseNamesRegistryFile(repositoryDirectory);
		}

	//******************************
	//03_Methods*******************************************************
	public boolean implementQuery(String userQuery) {
		try{
			queryParser.parseQuery(userQuery);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	//Database Operations**********************
	public boolean createDatabase(String databaseName) {
		try{
			CreateDatabaseCommand createDatabase=new CreateDatabaseCommand(databaseName, repositoryDirectory, this);
			return createDatabase.execute();
		}catch(Exception ex){
			return false;
		}
	}

	public boolean dropDatabase(String databaseName) {
		try{
			DropDatabaseCommand dropDatabase=new DropDatabaseCommand(databaseName, repositoryDirectory, this);
			return dropDatabase.execute();
		}catch(Exception ex){
			return false;
		}
	}

	public boolean connectToDatabase(String databaseName) {
		try{
			UseDatabaseCommand useDatabase=new UseDatabaseCommand(databaseName, repositoryDirectory);
			this.currentDatabase=useDatabase.execute();
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	public boolean disconnectFromDatabase(){
		this.currentDatabase=null;
		return false;
	}

	//Table Operations*************************
	public boolean createTable(String tableName, String[] colNames, String[] colTypes) {
		try{
			CreateTableCommand createTable=new CreateTableCommand(currentDatabase, tableName, colNames, colTypes);
			return createTable.execute();
		}catch(Exception ex){
			return false;
		}
	}

	public boolean dropTable(String tableName) {
		try{
			DropTableCommand dropTable=new DropTableCommand(currentDatabase, tableName);
			return dropTable.execute();
		}catch(Exception ex){
			return false;
		}
	}

	//Record Operations************************
	public boolean addNewRecord(String tableName, String[] colNames, String[] colValues) {
		try{
			//01_create dummy record
			Record tmp=new Record(colValues.length);
			tmp.setCellValues(colValues);
			if(colNames!=null){
				tmp.setCellNames(colNames);
			}
			//02_try to insert this record into table
			InsertNewRecordCommand insertCommand=new InsertNewRecordCommand(currentDatabase, tableName, tmp);
			return insertCommand.execute();
		}catch(Exception ex){
			return false;
		}
	}

	public Record[] retireveRecord(String tableName, String[] colNames, CriteriaSet condition) {
		try{
			RetrieveRecordCommand retrieveCommand=new RetrieveRecordCommand(currentDatabase, tableName, colNames, condition);
			return retrieveCommand.execute();
		}catch(Exception ex){
			return null;
		}
	}

	public boolean updateRecord(String tableName, String[] colNames, String[] newData, CriteriaSet condition) {
		try{
			Record tmp=new Record(colNames.length);
			tmp.setCellNames(colNames);
			tmp.setCellValues(newData);
			UpdateRecordCommand updateCommand=new UpdateRecordCommand(currentDatabase, tableName, tmp, condition);
			return updateCommand.execute();
		}catch(Exception ex){
			return false;
		}
	}

	public boolean deleteRecord(String tableName, CriteriaSet condition) {	
		try{
			DeleteRecordCommand deleteCommand=new DeleteRecordCommand(currentDatabase, tableName, condition);
			return deleteCommand.execute();
		}catch(Exception ex){
			return false;
		}
	}
	
	//Setters & Getters
	//*********************************
	public ArrayList<String> getDatabaseNamesList(){
		return this.databaseNamesList;
	}
	
	
	
}
