package queryParser;

import java.util.regex.Matcher;

import application.DBMS;

public class QueryParser implements IQueryParser{

	//01_Attributes********************************************
		private Matcher queryMatcher;
		//Chains
		private Chain createDatabase;
		private Chain dropDatabase;
		private Chain useDatabase;
		private Chain createTable;
		private Chain dropTable;
		private Chain restrictedInsert;
		private Chain unrestrictedInsert;
		private Chain conditionedSelect;
		private Chain unconditionedSelect;
		private Chain conditionedUpdate;
		private Chain unconditionedUpdate;
		private Chain conditionedDelete;
		private Chain unconditionedDelete;
	
	//02_Constructor*******************************************
		public QueryParser(DBMS databaseManager){
			this.queryMatcher=null;
			//01_initialize chains
			createDatabase=new CreateDatabaseStatementParser(queryMatcher, databaseManager);
			dropDatabase=new DropDatabaseStatementParser(queryMatcher, databaseManager);
			useDatabase=new UseDatabaseStatementParser(queryMatcher, databaseManager);
			createTable=new CreateTableStatementParser(queryMatcher, databaseManager);
			dropTable=new DropTableStatementParser(queryMatcher, databaseManager);
			restrictedInsert=new RestrictedInsertStatementParser(queryMatcher, databaseManager);
			unrestrictedInsert=new UnrestrictedInsertStatementParser(queryMatcher, databaseManager);
			conditionedSelect=new ConditionedSelectStatementParser(queryMatcher, databaseManager);
			unconditionedSelect=new UnconditionedSelectStatementParser(queryMatcher, databaseManager);
			conditionedUpdate=new ConditionedUpdateStatementParser(queryMatcher, databaseManager);
			unconditionedUpdate=new UnconditionedUpdateStatementParser(queryMatcher, databaseManager);
			conditionedDelete=new ConditionedDeleteStatementParser(queryMatcher, databaseManager);
			unconditionedDelete=new UnconditionedDeleteStatementParser(queryMatcher, databaseManager);
			//02_set next in chain(s)
			createDatabase.setNextInChain(dropDatabase);
			dropDatabase.setNextInChain(useDatabase);
			useDatabase.setNextInChain(createTable);
			createTable.setNextInChain(dropTable);
			dropTable.setNextInChain(restrictedInsert);
			restrictedInsert.setNextInChain(unrestrictedInsert);
			unrestrictedInsert.setNextInChain(conditionedSelect);
			conditionedSelect.setNextInChain(unconditionedSelect);
			unconditionedSelect.setNextInChain(conditionedUpdate);
			conditionedUpdate.setNextInChain(unconditionedUpdate);
			unconditionedUpdate.setNextInChain(conditionedDelete);
			conditionedDelete.setNextInChain(unconditionedDelete);
			unconditionedDelete.setNextInChain(null);
		}
	
	//03_Methods***********************************************
	public void parseQuery(String userQuery){
		createDatabase.parseQuery(userQuery);
	}
	
}
