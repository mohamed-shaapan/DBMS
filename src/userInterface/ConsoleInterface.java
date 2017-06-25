package userInterface;

import java.util.Scanner;

import application.DBMS;

public class ConsoleInterface {

	
	public static void main(String[] args) {
		
		DBMS databaseManager=new DBMS("RepositoryDirectory");
		Scanner scanner = new Scanner(System.in);
		String userQuery="";
		
		while(true){
			System.out.println(">>\n");
			userQuery=scanner.nextLine();
			while(!userQuery.trim().endsWith(";")){
				userQuery=userQuery+" "+scanner.nextLine();
			}
			userQuery=userQuery.toLowerCase();
			databaseManager.implementQuery(userQuery);
		}
	}
	
	
}
