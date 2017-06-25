package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class RegistryFileHandler {

	
	//**************************************************************
	//**************************************************************
	
	public static boolean updateTableNamesRegistery(String databaseDirectory, String[] tableNamesList) {
		String tableNamesFileDirectory=databaseDirectory+File.separator+"tableNamesRegistery.bin";
		File tableNamesFile=new File(tableNamesFileDirectory);
		try{
			//open file
			FileOutputStream tmp=new FileOutputStream(tableNamesFile);
			ObjectOutputStream fileWriter=new ObjectOutputStream(tmp);
			//write to file
			fileWriter.writeObject(tableNamesList);
			//close file
			fileWriter.close();
			tmp.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//**************************************************************
	//**************************************************************
	
	public static String[] loadTableNamesRegistery(String databaseDirectory){
		String tableNamesFileDirectory=databaseDirectory+File.separator+"tableNamesRegistery.bin";
		File tableNamesFile=new File(tableNamesFileDirectory);
		String tableNamesList[]=null;
		try{
			//open file
			FileInputStream tmp=new FileInputStream(tableNamesFile);
			ObjectInputStream fileReader=new ObjectInputStream(tmp);
			//read from file
			tableNamesList=(String[])fileReader.readObject();
			//close file
			fileReader.close();
			tmp.close();
			return tableNamesList;
		}catch (Exception e){
			return null;
		}
	}
	
	//**************************************************************
	//**************************************************************
	
	public static boolean updateDatabaseNamesRegistery(String repositoryDirectory, String[] databaseNamesList) {
		String databaseNamesFileDirectory=repositoryDirectory+File.separator+"databaseNamesRegistery.bin";
		File databaseNamesFile=new File(databaseNamesFileDirectory);
		try{
			//open file
			FileOutputStream tmp=new FileOutputStream(databaseNamesFile);
			ObjectOutputStream fileWriter=new ObjectOutputStream(tmp);
			//write to file
			fileWriter.writeObject(databaseNamesList);
			//close file
			fileWriter.close();
			tmp.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//**************************************************************
	//**************************************************************
	public static ArrayList<String> initializeDatabaseNamesRegistryFile(String repositoryDirectory){
		boolean registryFileExists=databaseRegistryFileExists(repositoryDirectory);
		if(registryFileExists==true){
			String names[]=loadDatabaseNamesRegistery(repositoryDirectory);
			if(names!=null){
				return new ArrayList<String>(Arrays.asList(names));
			}else{
				return new ArrayList<String>();
			}
		}else{
			updateDatabaseNamesRegistery(repositoryDirectory, null);
			return new ArrayList<String>();
		}
	}
	
	//**************************************************************
	//**************************************************************
	
	public static String[] loadDatabaseNamesRegistery(String repositoryDirectory){
		String databaseNamesFileDirectory=repositoryDirectory+File.separator+"databaseNamesRegistery.bin";
		File databaseNamesFile=new File(databaseNamesFileDirectory);
		String databaseNamesList[]=null;
		try{
			//open file
			FileInputStream tmp=new FileInputStream(databaseNamesFile);
			ObjectInputStream fileReader=new ObjectInputStream(tmp);
			//read from file
			databaseNamesList=(String[])fileReader.readObject();
			//close file
			fileReader.close();
			tmp.close();
			return databaseNamesList;
		}catch (Exception e){
			return null;
		}
	}
	
	//**************************************************************
	//**************************************************************
	public static boolean databaseRegistryFileExists(String repositoryDirectory){
		String databaseNamesFileDirectory=repositoryDirectory+File.separator+"databaseNamesRegistery.bin";
		File databaseNamesFile=new File(databaseNamesFileDirectory);
		if(databaseNamesFile.exists()){
			return true;
		}
		return false;
	}
	//**************************************************************
	//**************************************************************
	public static boolean databaseRegistered(String databaseName, String repositoryDirectory){
		String dbNames[]=loadDatabaseNamesRegistery(repositoryDirectory);
		if(dbNames==null){
			return false;
		}
		for(String currentName:dbNames){
			if(databaseName.equalsIgnoreCase(currentName)){
				return true;
			}
		}
		return false;
	}

	
	
	
}
