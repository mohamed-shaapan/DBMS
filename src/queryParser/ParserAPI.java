package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public interface ParserAPI {
	
	public void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager);

	public String generatePattern();

}
