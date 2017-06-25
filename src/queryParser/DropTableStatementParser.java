package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public class DropTableStatementParser extends Chain implements ParserAPI{

	public DropTableStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(5).replaceAll(" ", "");
		databaseManager.dropTable(tableName);
	}

	public String generatePattern() {
		String pattern="(drop)( +?)(table)( +?)(.+)( *?)(;)";
		return pattern;
	}

}
