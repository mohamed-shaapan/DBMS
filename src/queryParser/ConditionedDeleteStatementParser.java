package queryParser;

import java.util.regex.Matcher;

import application.DBMS;
import operations.CriteriaSet;

public class ConditionedDeleteStatementParser extends Chain implements ParserAPI{

	public ConditionedDeleteStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(5).replaceAll(" ", "");			
		String testCellName=queryMatcher.group(9).replaceAll(" ", "");
		String operator=queryMatcher.group(10).replaceAll(" ", "");
		String value=queryMatcher.group(11).replaceAll(" ", "").replaceAll("\"", "").replaceAll("\'", "");
		CriteriaSet conditionStatement=new CriteriaSet(testCellName, operator, value);
		databaseManager.deleteRecord(tableName, conditionStatement);
	}

	public String generatePattern() {
		String pattern="(delete)( +?)(from)( +?)(.+)( +?)(where)( +?)(.+)([<>=])(.+)( *?)(;)";
		return pattern;
	}

}
