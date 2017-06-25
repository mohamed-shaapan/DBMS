package storage;

import java.util.Arrays;

import application.DBMS;
import operations.CriteriaSet;
import runtimeDatabase.Record;
import runtimeDatabase.Table;
import userInterface.ResultPrinter;

public class Tester {

	public static void main(String args[]){

		
		testApplication();
		//testDatabaseNamesRegistryFile();
		//loadTableTest();
		//testTableNamesRegistryFile();
	}



	public static void testDatabaseNamesRegistryFile(){
		String tmp[]=RegistryFileHandler.loadDatabaseNamesRegistery("DatabaseRepository");
		System.out.println(Arrays.toString(tmp));
	}
	public static void testTableNamesRegistryFile(){
		String tmp[]=RegistryFileHandler.loadTableNamesRegistery("DatabaseRepository\\coca cola");
		System.out.println(Arrays.toString(tmp));
	}
	
	
	public static void testApplication(){
		//01_Initialize application
		DBMS manager=new DBMS("DatabaseRepository");
				
		//02_Create Database
		String databaseName="coca cola";
		//manager.createDatabase(databaseName);
				
		//03_Use Database
		System.out.println(manager.connectToDatabase(databaseName));
				
		//02_Create New Table
		String tableName="customers";
		/*String colNames[]={"Name", "Email", "Phone"};
		String colTypes[]={"String", "String", "Integer"};
		System.out.println(manager.createTable(tableName, colNames, colTypes));*/
				
		//03_Create New Records
		/*String r1[]={"Mohamed", "Gmail", "123"};
		String r2[]={"Ahmad", "iCloud", "321"};
		String r3[]={"Aya", "Hotmail", "456"};
		String r4[]={"Lola", "Yahoo", "654"};
		System.out.println(manager.addNewRecord(tableName, null, r1));
		System.out.println(manager.addNewRecord(tableName, null, r2));
		System.out.println(manager.addNewRecord(tableName, null, r3));
		System.out.println(manager.addNewRecord(tableName, null, r4));*/
		
		
		CriteriaSet condition=new CriteriaSet("Name", "=", "Mohamed");
		manager.deleteRecord(tableName, condition);
				
		//04_Retireve Records
		String colToRetrieve[]={"Phone", "Name"};
		String retirieveAll[]={"*"};
		Record result[]=manager.retireveRecord(tableName, retirieveAll, null);
				
		//05_Print Result
		ResultPrinter printer=new ResultPrinter();
		printer.printTable(result);
		
		
		
	}
	
	public static void loadTableTest(){
		String tableName="clients";
		String databaseDirectory="DatabaseRepository\\coca cola";
		
		TableFileHandler tableFileHandler=TableFileHandler.getInstance();
		Table currentTable=tableFileHandler.loadTable(tableName, null, databaseDirectory, "xml");
		ResultPrinter printer=new ResultPrinter();
		printer.printTable(currentTable.getRecords());
	}
	
	
	
	
	
}
