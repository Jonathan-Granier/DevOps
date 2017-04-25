package client.java.fichiers_client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import main.java.commande_structure.Request;
import main.java.interfaceserveur.operationCode;

@Deprecated
public abstract class reqEncoder {
	
	//Redondant si Answer/request serializable?
	/**
	 * Return an array of bytes corresponding to the request
	 * @param opCode
	 * @param reqNum
	 * @param keyLen
	 * @param dataLen
	 * @param key
	 * @param data
	 * @return Array of bytes as the request
	 */
	public static byte[] getRequest(char opCode, int reqNum, int keyLen, int dataLen,  String key, byte[] data) {
		
		int reqLen = operationCode.char_length 		//Opcode
				+ operationCode.int_length * 3 		// reqnum | keyLen | dataLen 
				+ keyLen*operationCode.char_length	// key
				+ dataLen 						;	// data

				
		byte[] request = new byte[reqLen];
		request[0] = (byte)opCode;
		int offset = operationCode.char_length;
		
		int_into_bytebuffer(reqNum, request, offset);
		offset += operationCode.int_length;
		
		int_into_bytebuffer(keyLen, request, offset);
		offset += operationCode.int_length;
		
		int_into_bytebuffer(dataLen, request, offset);
		offset += operationCode.int_length;
		
		for(byte b: key.getBytes()){
			request[offset]= b;
			offset++;
		}
		
		for(byte b: data){
			request[offset]= b;
			offset++;
		}
		return request;
	}

	/**
	 * return a SET request for a INT data
	 * @param key
	 * @param value
	 * @param meta_data [ reqNum | msgLen ]
	 * @return
	 */
	public static byte[] set(String key, int value, int[] meta_data)
	{
		char opCode = operationCode.setInt;
		int keyLen= key.length();
		int dataLen = operationCode.int_length;
		int reqNum = operationCode.reqNumber();
		meta_data[0] = reqNum;
		
		//initialize data as byte buffer
		byte[] data = new byte[dataLen];
		int_into_bytebuffer(value, data, 0);
		
		//create the request
		byte[] request = getRequest(opCode, reqNum, keyLen, dataLen, key, data);
		meta_data[1] = request.length;
		
		return request;
	}
	
	/**
	 * return a SET request for a STRING data
	 * @param key
	 * @param value
	 * @param meta_data [ reqNum | msgLen ]
	 * @return
	 */
	public static byte[] set(String key, String value, int[] meta_data)
	{
		char opCode = operationCode.setString;
		int keyLen= key.length();
		int dataLen = operationCode.char_length * value.length();
		int reqNum = operationCode.reqNumber();
		meta_data[0] = reqNum;
		
		//initialize data as byte buffer
		byte[] data = new byte[dataLen];
		string_into_bytebuffer(value, data, 0);
		
		
		//create the request
		byte[] request = getRequest(opCode, reqNum, keyLen, dataLen, key, data);
		meta_data[1] = request.length;
		
		return request;
	}
	
	/**
	 * return a SET request for a OBJECT data
	 * @param key
	 * @param object
	 * @param objectsize
	 * @param meta_data [ reqNum | msgLen ]
	 * @return
	 */
	public static <T extends Serializable> byte[] set(String key, T object, int objectsize, int[] meta_data)
	{
		char opCode = operationCode.setObject;
		int keyLen= key.length();
		int dataLen = objectsize;
		int reqNum = operationCode.reqNumber();
		meta_data[0] = reqNum;
		
		//TODO
		//create the request
		//byte[] request = getRequest(opCode, reqNum, keyLen, dataLen, key, object);
		//meta_data[1] = request.length;
		
		//return request;
		return null;
	}
	
	/**
	 * return a GET request
	 * @param key
	 * @param meta_data [ reqNum | msgLen ]
	 * @return
	 */
	public  static byte[] get(String key, int[] meta_data)
	{
		char opCode = operationCode.get;
		int keyLen= key.length();
		int dataLen = 0;
		int reqNum = operationCode.reqNumber();
		meta_data[0] = reqNum;
		
		//create the request
		byte[] request = getRequest(opCode, reqNum, keyLen, dataLen, key, null);
		meta_data[1] = request.length;
		
		return request;
	}
	
	/**
	 * 
	 * @param req
	 * @param metaData [ reqNum | msgLen ]
	 * @return
	 */
	public static byte[] getRequest(Request req, int[] metaData){//TODO
		char opCode = operationCode.opCode_to_char(req.op_code);
		int keyLen = req.key.length();
		//int dataLen = req.data_as_byte.length;
		//byte[] request =  getRequest(opCode, req.reqNumber, keyLen, dataLen, req.key, req.data_as_byte);
		
		metaData[0]= req.reqNumber;
		//metaData[1] = request.length;
		
		//return request;
		return null;
	}
	
	/**
	 * Rempli un buffer déjà allouer avec un entier a partir de l'offset indiqué
	 * @param input
	 * @param buffer
	 * @param offset
	 */
	private static void int_into_bytebuffer(int input, byte[] buffer, int offset){
		for(int i=0; i<operationCode.int_length; i++)
		{
			int decalage = (operationCode.int_length - i -1) * 8;//Decalage de 24 > 16 > 8 > 0
			buffer[offset+i]=(byte)(input >> decalage);
		}
	}
	
	/**
	 * Rempli un buffer déjà allouer avec une chaine de char a partir de l'offset indiqué
	 * @param input
	 * @param buffer
	 * @param offset
	 */
	private static void string_into_bytebuffer(String input, byte[] buffer, int offset){
		for(byte b: input.getBytes())
		{
			buffer[offset]=b;
			offset++;
		}
	}
}
