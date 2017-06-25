package runtimeDatabase;

public class ValidDataTypes {

	private static final String[] validDataTypes={"String", "Integer"};
	
	public static boolean testDataType(String dataType, String value){
		if(dataType.equalsIgnoreCase(validDataTypes[0])){
			return true;
		}
		else if(dataType.equalsIgnoreCase(validDataTypes[1])){
			try{
				Integer.parseInt(value);
				return true;
			}catch(Exception ex){
				return false;
			}
		}
		return false;
	}
	
	
	
}
