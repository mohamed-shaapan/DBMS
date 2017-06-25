package queryParser;

import java.util.regex.Matcher;

import application.DBMS;
import operations.CriteriaSet;

public class UnconditionedUpdateStatementParser extends Chain implements ParserAPI{

	public UnconditionedUpdateStatementParser(Matcher queryMatcher, DBMS databaseManager) {
		super(queryMatcher, databaseManager);
	}

	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager) {
		String tableName=queryMatcher.group(3).replaceAll(" ", "");
		String[] updates= queryMatcher.group(7).split(",");
		String[] colNames=new String[updates.length];
		String[] newData=new String[updates.length];
		for(int i=1; i<=updates.length; i++){
			String[] update=updates[i-1].trim().split("=");
			colNames[i-1]=update[0].trim();
			newData[i-1]=update[1].trim().replaceAll("\"", "").replaceAll("\'", "");
		}
		CriteriaSet conditionStatement=new CriteriaSet();
		databaseManager.updateRecord(tableName, colNames, newData, conditionStatement);
	}

	public String generatePattern() {
		String pattern="(update)( +?)(.+)( +?)(set)( +?)(.+)( *?)(;)";
		return pattern;
	}

}
