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
	
	public UDPMessage(User user) throws UnknownHostException {
		// TODO Auto-generated constructor stub
		InetAddress localHost = user.getIp();
		this.userPseudo = user.getUsername();
		this.userPhone = user.getUserPhone();
		this.user = user;
		this.sourceAddress = localHost;
		//When the connection begins, it commences the timer of the pseudo
		Timestamp timestartOfConnection = new Timestamp(System.currentTimeMillis());
		this.timer = timestartOfConnection;
		this.status = user.getState();
	}
	
	private UDPMessage() {
	}

	
	//Functions for the UDPMessage
	public User getUser() {
		return this.user;
	}
	public UDPMessage getMessageContent(String message) {
		this.message = message;
		return this;
	}

	public State getState() {
		return this.status;
	}

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
	
	public UDPMessage setSourceAddress(InetAddress srcAddress) {
		this.sourceAddress = srcAddress;
		return this;
	}
	
	public UDPMessage withTheStatus(State state) {
		this.status = state;
		return this;
	}

	public String serializeMessage() {
		try {
			String objSerialized = userPseudo;
			State state = getState();
			String phone = userPhone;
			System.out.println("[UDPMessage, serialize] State is: "+ state);//test
			System.out.println("[UDPMessage, serialize] phone is: "+ phone);//test
			//Sequence de concatenation des champs d'un msg udp : state - pseudo - 
			String str = state+"@"+objSerialized+"@"+phone;//test
			String[] split = str.split("@"); //test	
			String s = Stream.of(split).collect(Collectors.joining("@"));
			for (int i = 0; i<split.length; i++) {//test
				 System.out.println("[UDPMessage], serialize message: "+i+" " + split[i]);//test
			}//test
			System.out.println("[UDPMessage, serialize] String sended: "+s);//test
			//inserer code pour creer le contenu de notre objet msg
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static UDPMessage deserializeMessage(String text) {
		try {
			String[] split = text.split("@");
			for (int i = 0; i<split.length; i++) {//test
				System.out.println("[UDPMessage], deserialized message: "+i+" " + split[i]);//test
			}//test
			String state =split[0];//test
			String pseudo = split[1];
			String phone = split[2];
			State stateOfMsg = State.valueOf(state);
			System.out.println("[UDPMessage, deserialized received, test state):"+state);//
			InetAddress addressSource = InetAddress.getLocalHost(); 
			User usr = new User(pseudo, addressSource, phone);
			UDPMessage aReturner = new UDPMessage(usr).withTheStatus(stateOfMsg);
			return aReturner;//test*/	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[UDPMessage error]");//
			return new UDPMessage();
		}
	}
	
}
