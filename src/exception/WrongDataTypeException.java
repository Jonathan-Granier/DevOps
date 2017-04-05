package exception;

public class WrongDataTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String error_msg;
	
	public WrongDataTypeException(String error){
		error_msg = error;
	}
	
	public WrongDataTypeException(){
		error_msg="WrongDataType";
	}
	
}
