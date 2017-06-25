package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public class UnrestrictedInsertStatementParser extends Chain implements ParserAPI{

	public UnrestrictedInsertStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(5).replaceAll(" ", "");
		String[] colValues=queryMatcher.group(10).split(",");
		for(int i=1; i<=colValues.length; i++){
			colValues[i-1]=colValues[i-1].trim().replaceAll("\"", "").replaceAll("\'", "");
		}
		databaseManager.addNewRecord(tableName, null, colValues);
	}

	public String generatePattern() {
		String pattern="(insert)( +?)(into)( +?)(.+)( +?)(values)( *?)([(])(.+)([)])( *?)(;)";
		return pattern;
	}
	
}
