package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public class CreateDatabaseStatementParser extends Chain implements ParserAPI{

	public CreateDatabaseStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String databaseName=queryMatcher.group(5).replaceAll(" ", "");
		databaseManager.createDatabase(databaseName);
		System.out.println("Create Database");
	}

	public String generatePattern() {
		String pattern="(create)( +?)(database)( +?)(.+)( *?)(;)";
		return pattern;
	}



}
