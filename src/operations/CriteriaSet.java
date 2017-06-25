package operations;

public class CriteriaSet {

	private String testCellName;
	private String operator;
	private String value;
	
	//example WHERE Name="Mohamed"
	// testCell=Name, operation=(=), value=Mohamed
	
	public CriteriaSet(String testCellName, String operator, String value){
		this.testCellName=testCellName;
		this.operator=operator;
		this.value=value;
	}
	public CriteriaSet(){
		
	}

	public String getTestCellName() {
		return testCellName;
	}

	public String getOperator() {
		return operator;
	}

	public String getValue() {
		return value;
	}
	
}
