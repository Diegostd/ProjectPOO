package chatSystem.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

import chatSystem.model.*;

//Model to save the messages

public class ModelMessages {
	private ArrayList<Messages> messages = new ArrayList<Messages>();
	private String pseudo;
	private String address;
	
	public ModelMessages(String pseudo, String address) throws IOException {
		this.pseudo = pseudo;
		this.address = address;
	}
	
	public String getMessagePseudo() {
		return pseudo;
	}
	
	public String getMessageIp() {
		return address;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public ArrayList<Messages> getAllMessages() {
		return messages;
	}
	
	public void addANewMessage(Messages message) {
		this.messages.add(message);
	}

	

}
