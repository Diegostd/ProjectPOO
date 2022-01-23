package chatSystem.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Base64;


public class UDPMessage implements Serializable{
	
	private State status;
	private String message;
	private Timestamp timer;
	private String userPseudo;
	private String userPhone;
	private InetAddress sourceAddress;
	private User user;
	
	
	public UDPMessage(String pseudo) {
		// TODO Auto-generated constructor stub
		this.userPseudo = pseudo;
		this.userPhone = user.getUserPhone();
		this.sourceAddress = user.getIp();
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
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			String objSerialized = "";
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(this);
			so.flush();
			objSerialized = new String(Base64.getEncoder().encode(bo.toByteArray()));
			return objSerialized;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static UDPMessage deserializeMessage(String objSerialized) {
		try {
			byte bytes[] = Base64.getDecoder().decode(objSerialized.getBytes());
			ByteArrayInputStream bains = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bains);
			return (UDPMessage) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new UDPMessage();
		}
	}
	
}
