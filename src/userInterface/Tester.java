package userInterface;

import application.DBMS;
import runtimeDatabase.Record;

public class Tester {

	public static void main(String[] args) {
		
		//01_Initialize application
		DBMS manager=new DBMS("DatabaseRepository");
		
		//02_Create New Table
		String colNames[]={"Name", "Email", "Phone"};
		String colTypes[]={"String", "String", "Integer"};
		manager.createTable("clients", colNames, colTypes);
		
		//03_Create New Records
		String r1[]={"Mohamed", "Gmail", "123"};
		String r2[]={"Ahmad", "iCloud", "321"};
		String r3[]={"Aya", "Hotmail", "456"};
		String r4[]={"Lola", "Yahoo", "654"};
		manager.addNewRecord("clients", null, r1);
		manager.addNewRecord("clients", null, r2);
		manager.addNewRecord("clients", null, r3);
		manager.addNewRecord("clients", null, r4);
		
		//04_Retireve Records
		String colToRetrieve[]={"Phone", "Name"};
		String retirieveAll[]={"*"};
		Record result[]=manager.retireveRecord("clients", retirieveAll, null);
		
		//05_Print Result
		ResultPrinter printer=new ResultPrinter();
		printer.printTable(result);
		
	}
	
	
	public static void validQueryStatements(){
		
		String q1="create database pepsi;";
		String q2="drop database pepsi;";
		String q3="use pepsi;";
		String q4="create table clients (name string, email=string, phone=integer);";
		String q5="drop table clients;";
		String q6="insert into clients (name, email, phone) values ('mohamed', 'gmail', '123');";
		String q7="select * from clients;";
		String q8="select * from clients where name='mohamed';";
		String q9="update clients set name='mohab'";
		String q10="update clients set name='mohab' where name='mohamed';";
		String q11="delete from clients where name='mohamed';";
		String q12="delete from clients;";
		
	}

	
	
	
	
	

}
