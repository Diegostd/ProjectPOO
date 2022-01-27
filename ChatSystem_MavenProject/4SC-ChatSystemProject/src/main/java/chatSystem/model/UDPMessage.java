package chatSystem.model;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UDPMessage implements Serializable{
	
	private State status;
	private String message;
	private Timestamp timer;
	private String userPseudo;
	private String userPhone;
	private InetAddress sourceAddress;
	private User user;
	
	//Il faut changer 
	public UDPMessage(String pseudo) throws UnknownHostException {
		// TODO Auto-generated constructor stub
		//Se puede quitar el inetaddress
		InetAddress localHost = InetAddress.getLocalHost();
		this.userPseudo = pseudo;
		//this.userPhone = user.getUserPhone();
		this.userPhone = "7894561231";
		//this.sourceAddress = user.getIp();
		this.sourceAddress = localHost;
		//When the connection begins, timer of the pseudo
		Timestamp timestartOfConnection = new Timestamp(System.currentTimeMillis());
		this.timer = timestartOfConnection;
	}
	
	private UDPMessage() {
	}

	
	//Functions for the UDPMessage

	public UDPMessage getMessageContent(String message) {
		this.message = message;
		return this;
	}

	public State getState() {
		return this.status;
	}

	/*public UDPMessage getSourceAddress(InetAddress srcAddress) {
		this.sourceAddress = srcAddress;
		return this;
	}*/

	public InetAddress getSourceAddress() {
		return this.sourceAddress;
	}

	public Timestamp getTimestamp() {
		return this.timer;
	}

	public String getContentofMessage() {
		return this.message;
	}

	public String getSourcePseudo() {
		return this.userPseudo;
	}

	public String getUserPhone() {
		return this.userPhone;
	}
	
	public UDPMessage withTheStatus(State state) {
		this.status = state;
		return this;
	}

	public String serializeMessage() {
		try {
			String objSerialized = "hello";
			State state = getState();
			System.out.println("[UDPMessage, serialize] State is: "+ state);//test
			//Sequence de concatenation des champs d'un msg udp : state - pseudo - 
			String str = state+"@"+objSerialized;//test
			String[] split = str.split("@"); //test	
			String s = Stream.of(split).collect(Collectors.joining("@"));
			for (int i = 0; i<split.length; i++) {//test
				 System.out.println(split[i]);//test
			}//test
			System.out.println("[UDPMessage, serialize] Array splited: "+split);//test
			System.out.println("[UDPMessage, serialize] Array joined again: "+s);//test
			//inserer code pour creer le contenu de notre objet msg
		    //byte[] listData = out.toByteArray();
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static UDPMessage deserializeMessage(String text) {
		try {
			/*byte bytes[] = text.getBytes();
			ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));//test
			String msg = (String)inputStream.readObject();*/
			String[] split = text.split("@");
			for (int i = 0; i<split.length; i++) {//test
				 System.out.println(split[i]);//test
			}//test
			//System.out.println("[UDPMessage, deserialized msg to send: "+text);//test
			String state =split[0];//test
			String pseudo = split[1];
			State stateOfMsg = State.valueOf(state);
			System.out.println("[UDPMessage, deserialized received, test state):"+state);//
			UDPMessage aReturner = new UDPMessage(pseudo).withTheStatus(stateOfMsg);
			return aReturner;//test*/	
			//Array list = (Array) inputStream.readObject();//test*/
		    //UDPMessage msg = new UDPMessage(list);
		    /*for (int i = 0; i<list.length; i++) {//test
				 System.out.println(list[i]);//test
			}//test
			System.out.println("[UDPMessage] Array deserialized: "+list);//test
			
			/*byte bytes[] = msgSerialized.getBytes();
			ByteArrayInputStream bains = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bains);
			return (UDPMessage) ois.readObject();*/
			
			//String pseudo = split[1];
			
		    //return (UDPMessage) list;
		} catch (Exception e) {
			e.printStackTrace();
			return new UDPMessage();
		}
	}
	
}
