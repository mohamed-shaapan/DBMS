package queryParser;

import java.util.regex.Matcher;

import application.DBMS;
import operations.CriteriaSet;

public class UnconditionedDeleteStatementParser extends Chain implements ParserAPI{

	public UnconditionedDeleteStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(5).replaceAll(" ", "");	
		CriteriaSet conditionStatement=new CriteriaSet();
		databaseManager.deleteRecord(tableName, conditionStatement);
	}

	public String generatePattern() {
		String pattern="(delete)( +?)(from)( +?)(.+)( *?)(;)";
		return pattern;
	}

}
