package queryParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.DBMS;


public abstract class Chain implements IChain{

	//01_Attributes**************************************************
		private String pattern;
		private Matcher queryMatcher;
		private IChain nextInChain;
		private DBMS databaseManager;
		
	//02_Constructor*************************************************
		public Chain(Matcher queryMatcher, DBMS databaseManager){
			this.databaseManager=databaseManager;
			this.queryMatcher=queryMatcher;
			this.pattern=generatePattern();
		}
	
	//03_Methods*****************************************************
		public void parseQuery(String userQuery){
			queryMatcher=Pattern.compile(this.pattern).matcher(userQuery);
			if(queryMatcher.matches()){
				//System.out.println(pattern);
				extractQueryParameters(this.queryMatcher, this.databaseManager);
			}else{
				nextInChain.parseQuery(userQuery);
			}
		}
		
		public void setNextInChain(IChain nextInChain){
			this.nextInChain=nextInChain;
		}
		
		
		public abstract void extractQueryParameters(Matcher queryMatcher, DBMS databaseManager);
		
		public abstract String generatePattern();
	
	
}
