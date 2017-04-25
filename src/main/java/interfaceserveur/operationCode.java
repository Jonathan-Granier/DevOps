package main.java.interfaceserveur;

import main.java.commande_structure.Request.opCode;


public abstract class operationCode {
	public static int reqNumber =			0;
	public final static char 	setInt=		1;
	public final static char	setString=	2;
	public final static char	get_elem_of_list_at_index =	3;
	public final static char	get=		4;
	public final static char 	increment= 	5;
	public final static char 	list_add=	6;
	public final static char 	list_remove=7;
	public final static char 	remove=		8;
	public final static char	opSpe=		127;
	
	public final static int char_length = 1;//Redondant disponible dans java
	public final static int int_length = 4;// idem
	
	public final static int header_length = char_length + int_length*3;
	
	public static synchronized int reqNumber(){
		return reqNumber++;
	}
	
	public static char opCode_to_char(opCode op){
		switch(op)
		{
		case setString:
			return setString;
			
		case setInt:
			return setInt;
			
		case get_elem_of_list_at_index:
			return get_elem_of_list_at_index;
			
		case get:
			return get;
			
		case increment:
			return increment;
			
		case list_add:
			return list_add;
		
		case list_remove:
			return list_remove;
			
		case remove:
			return remove; //  8
			
		default:
			return opSpe;
		}
	}
	
	/**
	 * Retourne un entier lue dans un buffer de bytes.
	 * @param buffer
	 * @param offset
	 * @return
	 */
	public static int int_from_bytebuffer(byte[] buffer, int offset){
		int retour=0;
		int decalage = (int_length-1) *8;
		for(int i= 0; i< int_length; i++){
			retour += (buffer[offset+i] << decalage) ;
			decalage -= 8;
		}
		return retour;
	}
}