package storage;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import runtimeDatabase.Cell;
import runtimeDatabase.DataBase;
import runtimeDatabase.Record;
import runtimeDatabase.Table;


public class XMLFormatHandler{
	
	//01_Save Table
	//**************************************************************
	//**************************************************************
	public static boolean saveTable(Table table){
		
		String tableName=table.getTableName();
		String xmlFileDirectory=table.getDirectory()+File.separator+tableName+".xml";
		String dtdFileDirectory=table.getDirectory()+File.separator+tableName+".dtd";
		String colNames[]=table.getColNames();
		String colTypes[]=table.getColTypes();
		Record[] recordsToSave=table.getRecords();
		String dtdFileName=tableName+".dtd";
		
		
		DTDFileHandler.createDTDFile(tableName, dtdFileDirectory, colNames, colTypes);
		createEmptyXML(tableName, xmlFileDirectory, dtdFileName);
		
		try {
			//01_Import Document
				File inputFile=new File(xmlFileDirectory);
				DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
				Document doc=dBuilder.parse(inputFile);
				Element root=doc.getDocumentElement();
			//02_Save Elements
				for(int element=1; element<=table.getRecordSize(); element++){
					Record currentRecord=recordsToSave[element-1];
					Element newElement=doc.createElement(tableName);
					root.appendChild(newElement);
					for(int cellIndex=1; cellIndex<=currentRecord.getSize(); cellIndex++){
						Cell currentCell=currentRecord.getCells()[cellIndex-1];
						String cellValue=currentCell.getValue();
						String cellName=currentCell.getCellName();
						Element tmp=doc.createElement(cellName);
						tmp.appendChild(doc.createTextNode(cellValue));
						newElement.appendChild(tmp);
					}
				}
	        //02_Overwrite XML file
				String dtdFileRelativeDir=tableName+".dtd";
				TransformerFactory transformerFactory=TransformerFactory.newInstance();
				Transformer transformer=transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdFileRelativeDir);
				DOMSource source=new DOMSource(doc);
				StreamResult result=new StreamResult(new File(xmlFileDirectory));
				transformer.transform(source, result);
				
				return true;
			}catch(Exception ex){}
		return false;
	}
	//**************************************************************
	//**************************************************************
	//***********************************************************************************************************


	//02_Load Table
	//**************************************************************
	//**************************************************************
	public static Table loadTable(String tableName, DataBase parentDatabase, String databaseDirectory){
		String xmlFileDirectory=databaseDirectory+File.separator+tableName+".xml";
		String dtdFileDirectory=databaseDirectory+File.separator+tableName+".dtd";
		String colNames[]=DTDFileHandler.getTableColumnNames(dtdFileDirectory);
		String colTypes[]=DTDFileHandler.getTableColumnTypes(dtdFileDirectory);
		
		//System.out.println(dtdFileDirectory);
		//System.out.println(Arrays.toString(colNames));
		//System.out.println(Arrays.toString(colTypes));
		
		Table tmpTable=new Table(tableName, parentDatabase, colNames.length);
		tmpTable.setColumnNames(colNames);
		tmpTable.setColumnDataTypes(colTypes);
		
		try {	
			File inputFile = new File(xmlFileDirectory);
			DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
			Document table=dBuilder.parse(inputFile);
			
			NodeList tableRecords=table.getElementsByTagName(tableName);
			for(int recordIndex=1; recordIndex<=tableRecords.getLength(); recordIndex++){
				Record recordToLoad=new Record(tmpTable);
				Node currentRecord=tableRecords.item(recordIndex-1);
				NodeList currentCells=currentRecord.getChildNodes();
				for(int cellIndex=1; cellIndex<=currentCells.getLength(); cellIndex++){
					String currentCellValue=currentCells.item(cellIndex-1).getTextContent();
					recordToLoad.getCells()[cellIndex-1].setValue(currentCellValue);
				}
				tmpTable.addNewRecord(recordToLoad);
			}
		}catch(Exception ex){}
		
		return tmpTable;
		
	}

	//**************************************************************
	//**************************************************************
	//***********************************************************************************************************
	
	private static boolean createEmptyXML(String tableName, String targetDirectory, String dtdFileName){
		try{
			//01_Create File
			File xmlFile=new File(targetDirectory);
			PrintWriter fileWriter=new PrintWriter(xmlFile);
			//02_Create XML Column Structure
			dtdFileName="\""+dtdFileName+"\"";
			String prolog="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			String linkedDTDFileTag="<!DOCTYPE parent SYSTEM "+dtdFileName+">";
			String rootTag="<parent>\n</parent>";
			fileWriter.println(prolog);
			fileWriter.println(linkedDTDFileTag);
			fileWriter.println(rootTag);
			//04_Close file
			fileWriter.close();
			return true;
		}catch(IOException e){}	
		return false;
	}
	
	//**************************************************************
	//**************************************************************
	//***********************************************************************************************************
			
	
	
	

}
