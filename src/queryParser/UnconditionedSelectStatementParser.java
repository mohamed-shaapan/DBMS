package queryParser;

import java.util.regex.Matcher;

import application.DBMS;
import operations.CriteriaSet;

public class UnconditionedSelectStatementParser extends Chain implements ParserAPI{

	public UnconditionedSelectStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(7).replaceAll(" ", "");
		String[] colNames= queryMatcher.group(3).replaceAll(" ", "").split(",");
		CriteriaSet conditionStatement=new CriteriaSet();
		databaseManager.retireveRecord(tableName, colNames, conditionStatement);
	}

	public String generatePattern() {
		String pattern="(select)( +?)(.+)( +?)(from)( +?)(.+)( *?)(;)";
		return pattern;
	}

}
