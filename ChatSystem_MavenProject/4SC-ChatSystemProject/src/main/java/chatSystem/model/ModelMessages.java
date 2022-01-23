package chatSystem.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

import chatSystem.model.*;


public class ModelMessages {
	private User userLocal;
	//-public static DefaultListModel<ModelUserList> userList;
	//-private Path localLog;
	private ArrayList<Messages> messages = new ArrayList<Messages>();
	private String pseudo;
	private String address;
	
	//We create the user model, using the user class
	//We also declare a new list where users are saved
	public ModelMessages(String pseudo, String address) throws IOException {
		//-this.userLocal = userLocal;
		//-ModelMessages.usersList = new DefaultListModel<ModelUserList>();
		this.pseudo = pseudo;
		this.address = address;
		//-this.userLocal.setState(State.DISCONNECTED);
		//-this.localLog = Paths.get(this.userLocal.getUsername()+"Log.txt");
		//-this.cleanLog();
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
