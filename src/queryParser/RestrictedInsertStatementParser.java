package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public class RestrictedInsertStatementParser extends Chain implements ParserAPI{

	public RestrictedInsertStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(5).replaceAll(" ", "");
		String[] colNames=queryMatcher.group(8).replaceAll(" ", "").split(",");
		String[] colValues=queryMatcher.group(14).split(",");
		for(int i=1; i<=colValues.length; i++){
			colValues[i-1]=colValues[i-1].trim().replaceAll("\"", "").replaceAll("\'", "");
		}
		databaseManager.addNewRecord(tableName, colNames, colValues);
	}

	public String generatePattern() {
		String pattern="(insert)( +?)(into)( +?)(.+)( *?)([(])(.+)([)])( +?)(values)( *?)([(])(.+)([)])( *?)(;)";
		return pattern;
	}
	
}
