package storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DTDFileHandler {
	
	//**************************************************************
	//**************************************************************
	public static void createDTDFile(String tableName, String fileDirectory, String[] colNames, String[] colTypes){
		try{
			//01_Create File
			File dtdFile=new File(fileDirectory);
			PrintWriter fileWriter=new PrintWriter(dtdFile);
			//02_Create XML Column Structure
			String rootNode="<!ELEMENT parent ("+tableName+"*)>";
			fileWriter.println(rootNode);
			
			String parentNode="<!ELEMENT "+tableName+" (";
			for(int i=1; i<=colNames.length; i++){
				parentNode=parentNode+colNames[i-1];
				if(!(i==colNames.length)){parentNode+=",";}
			}
			parentNode=parentNode+")>";
			fileWriter.println(parentNode);
			for(String tableCol: colNames){
				String line="<!ELEMENT "+tableCol+" (#PCDATA)>";
				fileWriter.println(line);
			}
			//03_Create XML DataTypes
			fileWriter.println("");
			for(int i=1; i<=colNames.length; i++){
				String colName=colNames[i-1];
				String colType=colTypes[i-1];
				String line="<!ATTLIST "+colName+" type CDATA \""+colType+"\">";
				fileWriter.println(line);
			}
			//04_Close file
			fileWriter.close();
		}catch(IOException e){}	
	}
	
	//**************************************************************
	//**************************************************************
	//***********************************************************************************************************
	
	public static String[] getTableColumnNames(String dtdFileDirectory){
		ArrayList<String> result=new ArrayList<>();
		Matcher matchTester;
		String pattern="(<!)(ELEMENT)( +?)(.+)( +?)([(])(#PCDATA)([)])( *?)(>)";
		File targetFile=new File(dtdFileDirectory);
		try{
			Scanner fileReader=new Scanner(targetFile);
			while(fileReader.hasNext()){
				String currentLine=fileReader.nextLine();
				matchTester=Pattern.compile(pattern).matcher(currentLine);
				if(matchTester.matches()){
					String colName=matchTester.group(4).replaceAll(" ", "");
					result.add(colName);
				}
			}
			fileReader.close();	
			String[] finalResult=result.toArray(new String[result.size()]);
			return finalResult;
		}catch(Exception ex){}
		return null;
	}
	
	
	public String[] getTableAllColumnNames(String dtdFileDirectory){
		ArrayList<String> result=new ArrayList<>();
		Matcher matchTester;
		String pattern="(<!)(ELEMENT)( +?)(.+)( +?)([(])(#PCDATA)([)])( *?)(>)";
		File targetFile=new File(dtdFileDirectory);
		try{
			Scanner fileReader=new Scanner(targetFile);
			while(fileReader.hasNext()){
				String currentLine=fileReader.nextLine();
				matchTester=Pattern.compile(pattern).matcher(currentLine);
				if(matchTester.matches()){
					String colName=matchTester.group(4).replaceAll(" ", "");
					result.add(colName);
				}
			}
			fileReader.close();	
			String[] finalResult=result.toArray(new String[result.size()]);
			return finalResult;
		}catch(Exception ex){}
		return null;
	}
	//**************************************************************
	//**************************************************************
	//***********************************************************************************************************
	
	public static String[] getTableColumnTypes(String dtdFileDirectory){
		ArrayList<String> result=new ArrayList<>();
		Matcher matchTester;
		String pattern="(<!)(ATTLIST)( +?)(.+)( +?)(type)( +?)(CDATA)( +?)(.+)( *?)(>)";
		File targetFile=new File(dtdFileDirectory);
		try{
			Scanner fileReader=new Scanner(targetFile);
			while(fileReader.hasNext()){
				String currentLine=fileReader.nextLine();
				matchTester=Pattern.compile(pattern).matcher(currentLine);
				if(matchTester.matches()){
					String coType=matchTester.group(10).replaceAll(" ", "").replaceAll("\"", "");
					result.add(coType);
				}
			}
			fileReader.close();	
			String[] finalResult=result.toArray(new String[result.size()]);
			return finalResult;
		}catch(Exception ex){}
		return null;
	}
	//**************************************************************
	//**************************************************************
	//***********************************************************************************************************
	
	
	
	

}
