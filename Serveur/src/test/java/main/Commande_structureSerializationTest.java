package test.java.main;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.*;
import main.java.commande_structure.Answer;
import main.java.commande_structure.Answer.returnCode;
import main.java.commande_structure.Request;
import main.java.commande_structure.Request.opCode;
import main.java.exception.BDDNotFoundException;

public class Commande_structureSerializationTest {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private List<Request.opCode> list_opCode;
	private List<Answer.returnCode> list_retCode;
	private List<Serializable> list_data;
	private Random rand;
	/**
     * Create the test case
     * @param testName name of the test case
     */
    public Commande_structureSerializationTest(){
    }
    
    @Before
    public void init(){
    	rand = new Random();
    	list_opCode = new ArrayList<opCode>();
    	list_opCode.add(Request.opCode.set);
    	list_opCode.add(Request.opCode.get);
    	list_opCode.add(Request.opCode.get_elem_of_list_at_index);
    	list_opCode.add(Request.opCode.increment);
    	list_opCode.add(Request.opCode.list_add);
    	list_opCode.add(Request.opCode.list_remove);
    	list_opCode.add(Request.opCode.remove);
    	
    	list_retCode = new ArrayList<Answer.returnCode>();
    	list_retCode.add(Answer.returnCode.NonExistingKey);
    	list_retCode.add(Answer.returnCode.OK);
    	list_retCode.add(Answer.returnCode.WrongDataType);
    	
    	list_data = new ArrayList<Serializable>();
    	list_data.add(entier1);
    	list_data.add(entier2);
    	list_data.add(chaine1);
    	list_data.add(chaine2);
    	list_data.add(listeEntier);
    	list_data.add(listeString);
    	list_data.add(listeVide);

    	PipedOutputStream out_pipe= new PipedOutputStream();

    	PipedInputStream in_pipe = new PipedInputStream();

    	try {
			out_pipe.connect(in_pipe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			out = new ObjectOutputStream(out_pipe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			in = new ObjectInputStream(in_pipe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
	public void test_serializeRequest() throws IOException, ClassNotFoundException{
    	int number_of_iter = rand.nextInt(258);
    	
    	for(int i =0; i < number_of_iter; i++){
			Request sent = generateRandomRequest();
			Request rcv;
			out.writeObject(sent);
			out.flush();
			rcv = (Request) in.readObject();
			assertEquals(sent,rcv);
    	}
	}
    
    @Test
	public void test_serializeAnswer() throws IOException, ClassNotFoundException{
    	int number_of_iter = rand.nextInt(258);
    	
    	for(int i =0; i < number_of_iter; i++){
			Answer sent = generateRandomAnswer();
			Answer rcv;
			out.writeObject(sent);
			out.flush();
			rcv = (Answer) in.readObject();
			assertEquals(sent,rcv);
    	}
	}
    
    private Request generateRandomRequest(){
    	opCode opC = list_opCode.get(rand.nextInt(list_opCode.size()));
    	int reqNum = rand.nextInt();
    	String key = "key";
    	Serializable data = list_data.get(rand.nextInt(list_data.size()));
    	
    	Request R = new Request(opC, key, data, reqNum);
		return R;
    }
    
    private Answer generateRandomAnswer(){
    	returnCode opC = list_retCode.get(rand.nextInt(list_retCode.size()));
    	int reqNum = rand.nextInt();
    	Serializable data = list_data.get(rand.nextInt(list_data.size()));
    	
    	Answer R = new Answer();
    	R.data=data;
    	R.reqNumber= reqNum;
    	R.return_code = opC;
		return R;
    }
    
/////////////////////// OBJETS PRE-REMPLIS ///////////////////////
static Serializable entier1 = new Integer(2);
static Serializable entier2 = new Integer(42424242);

static Serializable chaine1 = new String("Bonjour");
static Serializable chaine2 = new String("Coucou comment ca va?");

static Serializable listeEntier = new ArrayList<Integer>().add(12);

static Serializable listeString = new ArrayList<String>().add("chaine de caracteres");

static Serializable listeVide = new ArrayList<Object>();
}
