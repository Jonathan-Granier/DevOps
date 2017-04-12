package main.java.interfaceserveur;

import java.io.OutputStream;

import main.java.interfaceserveur.operationCode;

public abstract class reqEncoder {
	private final static int char_length = 1;
	private final static int int_length = 4;

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static byte[] set(String key, int value, int[] meta_data)
	{
		char opCode = operationCode.setInt;
		int keylength= key.length();
		
		// 				OPCODE			ReqNumber	Keylength	DataLength		Key						data	
		int msgLength = char_length + int_length + int_length + int_length +keylength * char_length + int_length;
				
		meta_data[0] = operationCode.reqNumber();
		meta_data[1] = msgLength;
		byte[] msg = new byte[msgLength];
		msg[0] = 0; 
		
		return msg;
	}
	
	public static int set(String key, String value, int[] meta_data)
	{
		return 0;
	}
	
	public static int set(String key, byte[] object, int objectsize, int[] meta_data)
	{
		return 0;
	}
	
	public  int get(String key, int[] meta_data)
	{
		return 0;
	}
	
	public static int opcomplexe(String key, int[] meta_data)
	{
		return 0;
	}
	
	
}
