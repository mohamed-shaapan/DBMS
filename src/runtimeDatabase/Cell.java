package runtimeDatabase;

public class Cell {

	//01_Attributes***********************************
		private String name;
		private String type;
		private String value;
	
	//02_Constrcutor**********************************
		public Cell(String columnName, String columnDataType){
			this.name=columnName;
			this.type=columnDataType;
			this.value=null;
		}
		
	//03_Methods**************************************
		public void setValue(String newValue){
			this.value=newValue;
		}
		public String getValue(){
			return this.value;
		}
		public String getCellName(){
			return this.name;
		}
		public void setCellName(String cellName){
			this.name=cellName;
		}
		public String getType(){
			return this.type;
		}
	
	
	
}
