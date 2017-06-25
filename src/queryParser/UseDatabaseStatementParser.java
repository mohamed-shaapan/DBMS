package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public class UseDatabaseStatementParser extends Chain implements ParserAPI{

	public UseDatabaseStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String databaseName=queryMatcher.group(3).replaceAll(" ", "");
		databaseManager.connectToDatabase(databaseName);
		System.out.println("Use Database");
	}

	public String generatePattern() {
		String pattern="(use)( +?)(.+)( *?)(;)";
		return pattern;
	}

}
