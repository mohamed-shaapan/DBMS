package runtimeDatabase;

public class Record {
	
	//01_Attributes***********************************
		private Cell[] cells;
		private int recordID;
		private int size;
	
	//02_Constrcutor**********************************
		public Record(Table parentTable){
			cells=new Cell[parentTable.getColNames().length];
			recordID=-1;
			this.size=cells.length;
			String colNames[]=parentTable.getColNames();
			String colTypes[]=parentTable.getColTypes();
			for(int i=1; i<=size; i++){
				String cellName=colNames[i-1];
				String cellType=colTypes[i-1];
				Cell tmp=new Cell(cellName, cellType);
				cells[i-1]=tmp;
			}
		}
		
		public Record(int size){
			cells=new Cell[size];
			recordID=-1;
			this.size=size;
			for(int i=1; i<=size; i++){
				Cell tmp=new Cell(null, null);
				cells[i-1]=tmp;
			}
			
		}
		
	//03_Methods**************************************
		public void setCellValues(String cellValues[]){
			for(int i=1; i<=cells.length; i++){
				cells[i-1].setValue(cellValues[i-1]);
			}
		}
		
		public void setCellNames(String cellNames[]){
			for(int i=1; i<=cells.length; i++){
				cells[i-1].setCellName(cellNames[i-1]);
			}
		}
		
		public void setID(int recordID){
			this.recordID=recordID;
		}
		
		public void setSingleCellValue(String colName, String value){
			int cellIndex=-1;
			String colNames[]=this.getColumnNames();
			for(int i=1; i<=colNames.length; i++){
				if(colNames[i-1].equalsIgnoreCase(colName)){
					cellIndex=i;
					break;
				}
			}
			cells[cellIndex-1].setValue(value);
		}
		
		//****************
		public Cell[] getCells(){
			return this.cells;
		}
		public int getSize(){
			return this.size;
		}
		public String[] getColumnNames(){
			String result[]=new String[cells.length];
			for(int i=1; i<=cells.length; i++){
				result[i-1]=cells[i-1].getCellName();
			}
			return result;
		}
		public Cell getCellByName(String cellName){
			for(Cell currentCell:cells){
				boolean test=currentCell.getCellName().equalsIgnoreCase(cellName);
				if(test==true){
					return currentCell;
				}
			}
			return null;
		}
		public int getID(){
			return this.recordID;
		}
		
		

}

