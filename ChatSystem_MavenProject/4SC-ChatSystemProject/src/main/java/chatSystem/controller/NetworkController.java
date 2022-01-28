package chatSystem.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import chatSystem.interfaces.*;
import chatSystem.model.*;
import chatSystem.network.UDPReceiver;
import chatSystem.network.UDPSender;

public class NetworkController {
	  private UDPSender udpSender;
	  private UDPReceiver udpReceiver;
	  private MainWindow mainWindow = null;
	  private Thread udprThread;
	  private HashMap<String, ModelMessages> usersList;
	  private String pseudo;
	  private static String localPhone;
	  
	  //private tcp chat; line of code for the tcp
	  //private NetworkforTcp listener listener;
	  //private TCPListener tcplistener; listener of the tcp
	  //private MainWindow programView = null;
	  
	  //create a global list
	  
	 
	  //private ConnexionWindow interf;
	  
	  

	  public NetworkController() throws IOException {
		  this.usersList = new HashMap<String, ModelMessages>();
		  this.udpSender = new UDPSender();
		  this.udprThread = new Thread(this.udpReceiver);
		  this.udpReceiver = this.getListenerThread();
		  this.udpReceiver.start();
		  this.udprThread.start();
		  }
	  
	  public String getPseudo() {
			return this.pseudo;
		}
	  
	  /*public void setViewOfTheChat() {
			this.chatView = new ChatView(this);
		}*/
	  
	  public boolean isUserConnected() throws UnknownHostException {
			return InetAddress.getLocalHost() != null;
		}

	  
	  private UDPReceiver getListenerThread() throws SocketException {
		  int portBroadcast = 5557;
		  return new UDPReceiver(portBroadcast, this);  
	  }
	  
	  
	  //********Tcp listener here********
	  
	
	  
	
	public void notifyToAllUserStateUpdate(State state) throws UnknownHostException {
		System.out.println("[NetworkController notify] Type of broadcast: " + state);
		User usr = new User (this.pseudo,InetAddress.getLocalHost(),NetworkController.getLocalPhone());
		String broadcast = new UDPMessage(usr).withTheStatus(state).serializeMessage();
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
		
		if (userPhone.equals(getLocalPhone())) {
			System.out.println("SAME TELEPHONE" + usersList);//test
			return;
		}
		
		if (!this.usersList.containsKey(userPhone)) {
			//ADD VERIFICATION TO ADD TO THE LIST
			System.out.println("[NetworkController] He is entering to the list, because he is not already in the list");//test
			
			ModelMessages modelM = new ModelMessages(newPseudo, address);//test
			usersList.put(userPhone, modelM);
			System.out.println("[NetworkController] Users list, OG: " + usersList);//test
			usersList.put("7894561233",new ModelMessages("testPseudo", address));//test;
			String x = modelM.getMessagePseudo();//test
			System.out.println("[NetworkController] Users list: " + usersList);//test
			System.out.println("[NetworkController] Model Messages, pseudoOG: " + x);//test
			System.out.println("[NetworkController] Model Messages, list test, updated: " + usersList);//test
			System.out.println("[NetworkController] Model Messages, same address: " + address);//test
	
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
			User urs = message.getUser();
			String pseudoMSG = new UDPMessage(urs).withTheStatus(State.CONNECTING).serializeMessage();
			System.out.println("[NetworkController] Pseudo to send, in the method sendPseudo: " + pseudoMSG);//
			System.out.println("[NetworkController] Address to send, in the method sendPseudo: " + message.getSourceAddress());//		
			udpSender.send_MessageUDP(pseudoMSG, message.getSourceAddress());
		}
		

		public boolean pseudoUnicity(String pseudo, State state, String phone) throws UnknownHostException {
			if (!this.getAllConnectedUsers().containsKey(pseudo)) {
				this.pseudo = pseudo;
				NetworkController.setLocalPhone(phone);
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
				this.toUpdateOrAddUser(phone, pseudo, address);
			}
			if (state == State.CONNECTING) {
				this.toUpdateOrAddUser(phone, pseudo, address);//soit 1ere connexion soit modification de pseudo
				//this.sendPseudo(message); //pour envoyer le pseudo UNIQUE en broadcast
			}
			if (state == State.UNKNOWN) { //this is for testing
				this.toUpdateOrAddUser(phone, pseudo, address);
				this.sendPseudo(message);
			}
			if (state == State.DISCONNECTED) {
				this.removeUserForDisconnection(phone);
			}
			if (state == State.UPDATE) {
				this.toUpdateOrAddUser(phone, pseudo, address);
			}	
			
		}

		public static String getLocalPhone() {
			return localPhone;
		}

		public static void setLocalPhone(String localPhone) {
			NetworkController.localPhone = localPhone;
		}
	

	}


