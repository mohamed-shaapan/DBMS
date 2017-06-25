package queryParser;

import java.util.regex.Matcher;

import application.DBMS;
import operations.CriteriaSet;

public class ConditionedSelectStatementParser extends Chain implements ParserAPI{

	public ConditionedSelectStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(7).replaceAll(" ", "");
		String[] colNames= queryMatcher.group(3).replaceAll(" ", "").split(",");
		String testCellName=queryMatcher.group(11).replaceAll(" ", "");
		String operator=queryMatcher.group(12).replaceAll(" ", "");
		String value=queryMatcher.group(13).replaceAll(" ", "").replaceAll("\"", "").replaceAll("\'", "");
		CriteriaSet conditionStatement=new CriteriaSet(testCellName, operator, value);
		databaseManager.retireveRecord(tableName, colNames, conditionStatement);
	}

	public String generatePattern() {
		String pattern="(select)( +?)(.+)( +?)(from)( +?)(.+)( +?)(where)( +?)(.+)([<>=])(.+)( *?)(;)";
		return pattern;
	}

}
