package chatSystem.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;

import javax.swing.DefaultListModel;

import chatSystem.interfaces.*;
import chatSystem.model.*;

public class NetworkController implements Serializable, Cloneable{
	  private UDPSender udpSender;
	  private UDPReceiver udpReceiver;
	  private Thread udprThread;
	  private User user;
	  private HashMap<String, ModelMessages> usersList;
	  private String pseudo;
	  //private String localPhone = user.getUserPhone();
	  private String localPhone = "7894561232";
	  //private Timer timerCheck;
	  
	  //private tcp chat; line of code for the tcp
	  //private Network listener listener;
	  //private TCPListener tcplistener; listener of the tcp
	  //private Chat view chatView = null;
	  
	  //create a global list
	  
	 
	  //private ConnexionWindow interf;
	  
	  

	  public NetworkController() throws IOException {
		  this.usersList = new HashMap<String, ModelMessages>();
		  User userLocal = null;
		  this.udpSender = new UDPSender();
		  this.udprThread = new Thread(this.udpReceiver);
		  this.udpReceiver = this.getListenerThread();
		  this.udpReceiver.start();
		  this.udprThread.start();
			try {
				String ph = "338119826737";
				userLocal = new User("titi", InetAddress.getLocalHost(), ph); 
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			//this.modelM = new Model(userLocal);
			//this.cNet = new ControllerNetwork(model, interf);
		  }
	  
	  public boolean isUserConnected() {
			return user.getIp() != null;
		}
	  
	  private UDPReceiver getListenerThread() throws SocketException {
		  int portBroadcast = 5557;
		  return new UDPReceiver(portBroadcast, this);
		  
	  }
	  
	  //********Tcp listener here********
	  
	
	  
	
	public void notifyToAllUserStateUpdate(State state) throws UnknownHostException {
		System.out.println("[NetworkController notify] Type of broadcast: " + state);
		String broadcast = new UDPMessage(this.pseudo).withTheStatus(state).toString();
		udpSender.sendMessageBroadcastUDP(broadcast);
	}
	
	
	
	public HashMap<String, String> getAllConnectedUsers() {
		HashMap<String, String> pseudos = new HashMap<String, String>();
		for (Map.Entry<String, ModelMessages> user : usersList.entrySet()) {
			pseudos.put(user.getValue().getMessagePseudo(), user.getKey());
		}
		return pseudos;
		}
	
	public String getUserPhoneByPseudo(String pseudo) {
		HashMap<String, String> users = getAllConnectedUsers();
		if (users.containsKey(pseudo))
			return users.get(pseudo);
		return null;
	}
	
	public void toUpdateOrAddUser(String userPhone, String newPseudo, String address) throws IOException {
		
		if (userPhone.equals(localPhone)) {
			return;
		}
		
		if (!this.usersList.containsKey(userPhone)) {
			//ADD VERIFICATION TO ADD TO THE LIST
			ModelMessages modelM = new ModelMessages(newPseudo, address);//test
			usersList.put(userPhone, modelM);
			usersList.put("7894561233",new ModelMessages("testPseudo", address));//test;
			String x = modelM.getMessagePseudo();//test
			System.out.println("[NetworkController] Users list: " + usersList);//test
			System.out.println("[NetworkController] Model Messages, pseudoOG: " + x);//test
			System.out.println("[NetworkController] Model Messages, list test, updated: " + usersList);//test
			System.out.println("[NetworkController] Model Messages, same address: " + x);//test
	
		}
		else {
			//ADD VERIFICATION TO ADD TO THE LIST
			String oldPseudo = usersList.get(userPhone).getMessagePseudo();
			if (!newPseudo .equals(oldPseudo)) {
				usersList.get(userPhone).setPseudo(newPseudo);
				
				//Insert line to update the list view of the users
			}
		}
		//INSERT LINE TO VIEW CONNECTEDUSERSLISTS
	}
		
		public void removeUserForDisconnection(String ID) {
			System.out.println("User removed: " + ID);
			usersList.remove(ID);
			
			//INSERT LINE TO UPDATE THE VIEW OF CONNECTES USERS
		}
				
		
		
		public void controllerOfANewMessageTCP(String phone, Messages message) {
			usersList.get(phone).addANewMessage(message);
			//INSERT LINE TO UPDATE THE USERS MESSAGES, FOR VIEW
		}
		
		
		public void sendPseudo(UDPMessage message) throws UnknownHostException {
			String pseudoMSG = new UDPMessage(pseudo).withTheStatus(State.CONNECTING).serializeMessage();
			udpSender.send_MessageUDP(pseudoMSG, message.getSourceAddress());
		}
		
		
		private boolean pseudoUnicity(String pseudo, State state) throws UnknownHostException {
			if (!this.getAllConnectedUsers().containsKey(pseudo)) {
				this.pseudo = pseudo;
				this.notifyToAllUserStateUpdate(state);
				return true;
			} else {
				return false;
			}		
		}


		
		public void newReceivedBroadcastMessage(UDPMessage message) throws IOException {
			State state = message.getState();
			String phone = message.getUserPhone();
			String pseudo = message.getSourcePseudo();
			String address = message.getSourceAddress().toString();

			System.out.println("[NetworkController] Type of broadcast: " + state);
			System.out.println("[NetworkController] Phone of remote user: " + phone);
			System.out.println("[NetworkController] Pseudo of remote user: " + pseudo);
			System.out.println("[NetworkController] Address of remote user: " + address);
			
			if (state == State.CONNECTED) {
				
			}
			if (state == State.CONNECTING) {
				this.toUpdateOrAddUser(phone, pseudo, address);//soit 1ere connexion soit modification de pseudo
				//this.sendPseudo(message); //pour envoyer le pseudo UNIQUE en broadcast
			}
			if (state == State.DISCONNECTED) {
	
			}
			if (state == State.UPDATE) {
	
			}	
			
		}
	

	}


