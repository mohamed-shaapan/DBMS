package operations;

import runtimeDatabase.Record;
import runtimeDatabase.Table;

public class Tester {

	public static void main(String args[]){
		
		//create table
		Table test=new Table("clients", null, 3);
		String colNames[]={"Name", "Email", "Phone"};
		String colTypes[]={"String", "String", "Integer"};
		test.setColumnNames(colNames);
		test.setColumnDataTypes(colTypes);
		
		//create records
		Record r1=new Record(test);
		Record r2=new Record(test);
		Record r3=new Record(test);
		Record r4=new Record(test);
		
		String v1[]={"Mohamed", "Gmail", "01129168505"};
		String v2[]={"Ahmad", "iCloud", "0103808083"};
		String v3[]={"Lola", "Outlook", "01204498368"};
		String v4[]={"Aya", "Yahoo", "01119619560"};
		
		r1.setCellValues(v1);
		r2.setCellValues(v2);
		r3.setCellValues(v3);
		r4.setCellValues(v4);
		
		//add records
		test.addNewRecord(r1);
		test.addNewRecord(r2);
		test.addNewRecord(r3);
		test.addNewRecord(r4);
		
		//search table
		CriteriaSet condition=new CriteriaSet("Email", "=", "icloud");
		
		SearchTableCommand search=new SearchTableCommand(test, condition);
		int[] result=search.execute();
		System.out.println("Result : "+result[0]);
		
		
		
		
		
	}

}
