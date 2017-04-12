package main.java.interfaceserveur;

public abstract class operationCode {
	
	public static int reqNumber =0;
	public final static char setInt=1;
	public final static char	setString=2;
	public final static char	setObject=3;
	public final static char	get=4;
	public final static char	opSpe=5;
	
	public static synchronized int reqNumber(){
		return reqNumber++;
	}
}