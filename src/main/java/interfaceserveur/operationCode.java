package main.java.interfaceserveur;

import main.java.commande_structure.Request.opCode;


public abstract class operationCode {
	public static int reqNumber =			0;
	public final static char 	set=		1;
	public final static char	get=		2;
	public final static char	get_elem_of_list_at_index =	3;
	public final static char 	increment= 	4;
	public final static char 	list_add=	5;
	public final static char 	list_remove=6;
	public final static char 	remove=		7;
	public final static char	opSpe=		127;
	
	public final static int char_length = 1;//Redondant disponible dans java
	public final static int int_length = 4;// idem
	
	public final static int header_length = char_length + int_length*3;
	
	public static synchronized int reqNumber(){
		return reqNumber++;
	}
	
}