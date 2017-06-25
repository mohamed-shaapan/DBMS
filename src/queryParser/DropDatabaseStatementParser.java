package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public class DropDatabaseStatementParser extends Chain implements ParserAPI{

	public DropDatabaseStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String databaseName=queryMatcher.group(5).replaceAll(" ", "");
		databaseManager.dropDatabase(databaseName);
		System.out.println("Drop Database");
	}

	public String generatePattern() {
		String pattern="(drop)( +?)(database)( +?)(.+)( *?)(;)";
		return pattern;
	}

}
