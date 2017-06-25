package runtimeDatabase;

public class Tester {

	public static void main(String[] args) {
		
		//01_Create Database
		DataBase pepsi=new DataBase("Pepsi");
		
		//02_Create Table
		Table clients=new Table("clients", null, 3);
		String colNames[]={"Name", "Email", "Phone"};
		String colTypes[]={"String", "String", "Integer"};
		clients.setColumnNames(colNames);
		clients.setColumnDataTypes(colTypes);
		
		//03_Create Record
		Record r1=new Record(clients);
		String values[]={"mohamed", "icloud", "123456789"};
		r1.setCellValues(values);
		
		//03_Integrate
		clients.addNewRecord(r1);
		pepsi.addTable(clients);
		
		
		System.out.println("Success");
		

	}

}
