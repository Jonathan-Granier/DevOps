package main.java.interfaceserveur;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import main.java.commande_structure.*;
@Deprecated
public abstract class reqDecoder {

	public static Request reqDecode(InputStream in) throws IOException, ClassNotFoundException
	{
		ObjectInputStream in_object = new ObjectInputStream(in);
		Object object_lu = in_object.readObject();
		Request req = null;
		if(object_lu instanceof Request)//TODO check if this work
			req = (Request) object_lu;
		return req;
			
		// OLD CODE. Where we used to code/decode by ourself
		/*byte[] input= new byte[operationCode.header_length];
		int read= in.read(input, 0, operationCode.header_length);
		
		if(read < operationCode.header_length)
			throw new IOException();
		
		int offset = 0;
		Request request = new Request();										// Remplissage des champs de la requête.
		
		char char_opCode = (char) input[offset];								// Get OPCODE
		request.op_code = Request.opcode_from_char(char_opCode);
		offset += operationCode.char_length;
		
		request.reqNumber = operationCode.int_from_bytebuffer(input, offset);	// Get REQNUMBER
		offset += operationCode.int_length;
		
		int keyLen = operationCode.int_from_bytebuffer(input, offset);			// Get KEY
		offset += operationCode.int_length;
		byte[] Bkey = new byte[keyLen];
		in.read(Bkey, 0, keyLen);
		String Skey = new String();
		for(byte b : Bkey){
			Skey = Skey + ((char)b);
		}
		
		int dataLen = operationCode.int_from_bytebuffer(input, offset);			// Get DATA
		offset += operationCode.int_length;
		return request;
		*/
	}
}
