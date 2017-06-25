package operations;

import java.util.ArrayList;
import java.util.Arrays;

import runtimeDatabase.Cell;
import runtimeDatabase.Record;
import runtimeDatabase.Table;



public class SearchTableCommand implements Command{
	
	//01_Attributes**********************************************
	private Table table;
	private CriteriaSet critiria;
	
	//02_Constructor*********************************************
	public SearchTableCommand(Table table, CriteriaSet critiria){
		this.table=table;
		this.critiria=critiria;
	}
	
	//03_Methods*************************************************
	public int[] execute(){
		if(critiria==null){
			return executeA();
		}else{
			return executeB();
		}
	}
	
	private int[] executeA(){
		//WHERE condition is NOT provided
		int size=table.getRecords().length;
		int resultSet[]=new int[size];
		for(int i=1; i<=size; i++){
			resultSet[i-1]=i-1;
		}
		return resultSet;
	}
	
	private int[] executeB() {
		//WHERE condition IS provided
		String testCell=this.critiria.getTestCellName();
		ArrayList<Integer> matchRecordIndices=new ArrayList<Integer>();
		for(int index=1; index<=this.table.getRecordSize(); index++){
			Record currentRecord=this.table.getRecords()[index-1];
			Cell matchingCell=currentRecord.getCellByName(testCell);
			boolean test=testCondition(matchingCell, this.critiria);
			if(test==true){
				matchRecordIndices.add(index-1);
			}
		}
		Integer[] tmp=matchRecordIndices.toArray(new Integer[matchRecordIndices.size()]);
		int[] finalResult = Arrays.stream(tmp).mapToInt(Integer::intValue).toArray();
		System.out.println(Arrays.toString(finalResult));
		System.out.println("element" + finalResult[0]);
		return finalResult;
	}

	private boolean testCondition(Cell cellToTest, CriteriaSet critiria){
		String cellValue=cellToTest.getValue();
		String critiriaValue=critiria.getValue();
		String operation=critiria.getOperator();
		
		if(operation.equalsIgnoreCase("=")){
			if(cellValue.equalsIgnoreCase(critiriaValue)){
				return true;
			}return false;
		}else if(operation.equalsIgnoreCase(">")){
			int test=cellValue.compareToIgnoreCase(critiriaValue);
			if(test>0){
				return true;
			}return false;
		}else if(operation.equalsIgnoreCase("<")){
			int test=cellValue.compareToIgnoreCase(critiriaValue);
			if(test<0){
				return true;
			}return false;
		}
		return false;
	}
	
	public void ObligatoryExecute(){}
	
	
}
