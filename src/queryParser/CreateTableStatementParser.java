package queryParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.DBMS;

public class CreateTableStatementParser extends Chain implements ParserAPI{

	public CreateTableStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);

	}


	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(5).replaceAll(" ", "");
		String[] tableColumns=queryMatcher.group(8).split(",");
		String[] colNames=new String[tableColumns.length];
		String[] colTypes=new String[tableColumns.length];
		String parameterPattern="( *?)(.+)(=*?)(.+)( *?)";
		for(int i=1; i<=tableColumns.length; i++){
			Matcher tmpMatcher=Pattern.compile(parameterPattern).matcher(tableColumns[i-1]);
			if(tmpMatcher.matches()){
				colNames[i-1]=tmpMatcher.group(2).replaceAll(" ", "");
				colTypes[i-1]=tmpMatcher.group(4).replaceAll(" ", "");
			}else{
				System.out.println("Invalid Paramters");
				break;
			}
		}
		databaseManager.createTable(tableName, colNames, colTypes);
	}


	public String generatePattern() {
		String pattern="(create)( +?)(table)( +?)(.+)( *?)([(])(.+)([)])( *?)(;)";
		return pattern;
	}

}
