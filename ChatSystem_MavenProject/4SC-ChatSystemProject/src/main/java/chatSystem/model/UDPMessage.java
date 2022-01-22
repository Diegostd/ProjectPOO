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
	private int userPhone;
	private InetAddress sourceAddress;
	private User user;
	
	
	public UDPMessage(String pseudo) {
		// TODO Auto-generated constructor stub
		this.userPseudo = pseudo;
		this.userPhone = user.getUserPhone();
		this.sourceAddress = user.getIp();
		Timestamp startOfConnection = new Timestamp(System.currentTimeMillis());
		this.timer = startOfConnection;
	}
	
	private UDPMessage() {
	}

	
	//Functions for the UDPMessage

	public UDPMessage getMessageContent(String message) {
		this.message = message;
		return this;
	}

	public UDPMessage getStatus(State status) {
		this.status = status;
		return this;
	}

	public UDPMessage getSourceAddress(InetAddress srcAddress) {
		this.sourceAddress = srcAddress;
		return this;
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

	public State getStatus() {
		return this.status;
	}

	public int getUserPhone() {
		return this.userPhone;
	}

	public String serializeMessage() {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			String serializedObject = "";
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(this);
			so.flush();
			serializedObject = new String(Base64.getEncoder().encode(bo.toByteArray()));
			return serializedObject;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static UDPMessage deserializeMessage(String serializedObject) {
		try {
			byte b[] = Base64.getDecoder().decode(serializedObject.getBytes());
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			return (UDPMessage) si.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new UDPMessage();
		}
	}
	
}
