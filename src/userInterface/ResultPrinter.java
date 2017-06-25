package userInterface;


import static java.lang.String.format;
import static java.lang.System.out;

import runtimeDatabase.Cell;
import runtimeDatabase.Record;

public final class ResultPrinter {
	

		//*******************
		//01_Attributes*****************************************
		    private final char BORDER_KNOT = '+';
		    private final char HORIZONTAL_BORDER = '-';
		    private final char VERTICAL_BORDER = '|';
		    private final String DEFAULT_AS_NULL = "(NULL)";


	    //*******************
	  	//02_Constructor****************************************
		    public ResultPrinter(){
		    	
		    }

		//*******************
		//03_Methods********************************************
		    public void printTable(Record[] recordsToPrint){
		    	String[] colNames=recordsToPrint[0].getColumnNames();
				String[][] dataToDisplay=recordsAsArray(recordsToPrint);
				String[][] completeTableToPrint=constructCompleteTable(colNames, dataToDisplay);
				printTablePatternA(completeTableToPrint);
		    }
		//**********************
		    private void printTablePatternA(String[][] completeTableToPrint) {
		    	final int[] widths = new int[getMaxColumns(completeTableToPrint)];
		    	adjustColumnWidths(completeTableToPrint, widths);
		    	printPreparedTable(completeTableToPrint, widths, getHorizontalBorder(widths));
		    }
		    
		    private String[][] recordsAsArray(Record[] records){
		    	int colSize=records[0].getCells().length;
		    	int rowSize=records.length;
		    	String[][] result=new String[rowSize][colSize];
		    	for(int rowIndex=1; rowIndex<=rowSize; rowIndex++){
		    		Record currentRecord=records[rowIndex-1];
					Cell cells[]=currentRecord.getCells();
					String recordValues[]=new String[cells.length];
					for(int i=1; i<=cells.length; i++){
						recordValues[i-1]=cells[i-1].getValue();
					}
					result[rowIndex-1]=recordValues;
				}
		    	return result;
			}
		    
		    private String[][] constructCompleteTable(String[] colNames, String[][] dataToDisplay){
		    	int rows=dataToDisplay.length+1;
		    	int columns=dataToDisplay[0].length;
		    	String[][] result=new String[rows][columns];
		    	result[0]=colNames;
		    	for(int i=2; i<=rows; i++){
		    		result[i-1]=dataToDisplay[i-2];
		    	}
		    	return result;
		    }  
		//******************************************************
		//Methods (Not Mine)
		//******************************************************
		    private void printPreparedTable(String[][] table, int widths[], String horizontalBorder) {
		        final int lineLength = horizontalBorder.length();
		        out.println(horizontalBorder);
		        for ( final String[] row : table ) {
		            if ( row != null ) {
		                out.println(getRow(row, widths, lineLength));
		                out.println(horizontalBorder);
		            }
		        }
		    }
	    //******************************************************
		    private String getRow(String[] row, int[] widths, int lineLength) {
		        final StringBuilder builder = new StringBuilder(lineLength).append(VERTICAL_BORDER);
		        final int maxWidths = widths.length;
		        for ( int i = 0; i < maxWidths; i++ ) {
		            builder.append(padRight(getCellValue(safeGet(row, i, null)), widths[i])).append(VERTICAL_BORDER);
		        }
		        return builder.toString();
		    }
	    //******************************************************
		    private String getHorizontalBorder(int[] widths) {
		        final StringBuilder builder = new StringBuilder(256);
		        builder.append(BORDER_KNOT);
		        for ( final int w : widths ) {
		            for ( int i = 0; i < w; i++ ) {
		                builder.append(HORIZONTAL_BORDER);
		            }
		            builder.append(BORDER_KNOT);
		        }
		        return builder.toString();
		    }
	    //******************************************************
		    private int getMaxColumns(String[][] rows) {
		        int max = 0;
		        for ( final String[] row : rows ) {
		            if ( row != null && row.length > max ) {
		                max = row.length;
		            }
		        }
		        return max;
		    }
	    //******************************************************
		    private void adjustColumnWidths(String[][] rows, int[] widths) {
		        for ( final String[] row : rows ) {
		            if ( row != null ) {
		                for ( int c = 0; c < widths.length; c++ ) {
		                    final String cv = getCellValue(safeGet(row, c, DEFAULT_AS_NULL));
		                    final int l = cv.length();
		                    if ( widths[c] < l ) {
		                        widths[c] = l;
		                    }
		                }
		            }
		        }
		    }
	    //******************************************************
		    private String padRight(String s, int n) {
		        return format("%1$-" + n + "s", s);
		    }
	    //******************************************************
		    private String safeGet(String[] array, int index, String defaultValue) {
		        return index < array.length ? array[index] : defaultValue;
		    }
	    //******************************************************
		    private String getCellValue(Object value) {
		        return value == null ? DEFAULT_AS_NULL : value.toString();
		    }
	    //******************************************************

}