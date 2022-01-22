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
import java.util.Timer;

import javax.swing.DefaultListModel;

import chatSystem.interfaces.*;
import chatSystem.model.*;

public class NetworkController implements Serializable, Cloneable{
	  private UDPSender udpSender;
	  private UDPReceiver udpReceiver;
	  private Thread udprThread;
	  private User user;
	  private Model model;
	  private NetworkController nc;
	  private Timer timerCheck;
	  
	  private HashMap<String, Messages> usersList;
	  private String nickname;
	  private String localPhone = user.getUserPhone();
	  
	  //private tcp chat; line of code for the tcp
	  //private Network listener listener;
	  //private TCPListener tcplistener; listener of the tcp
	  //private Chat view chatView = null;
	  
	  //create a global list
	  
	 
	  //private ConnexionWindow interf;
	  
	  

	  public NetworkController() throws IOException {
		  this.usersList = new HashMap<String, Messages>();
		  
		  User userLocal = null;
		  
		  //this.udpReceiver = new UDPReceiver();
		  //this.udps = new UDPSender();
		  this.udprThread = new Thread(this.udpReceiver);
		  this.udprThread.start();
			try {
				String ph = "338119826737";
				userLocal = new User("titi", InetAddress.getLocalHost(), ph); 
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			this.model = new Model(userLocal);
			//this.cNet = new ControllerNetwork(model, interf);
			
			
		  }
	  
	  public boolean isConnected() {
			return user.getIp() != null;
		}
	  
	
	/* when user connect, he must send a NewUserBroadcast*/
	//@Test
	public void NewUserBroadcast(String newPseudo) throws IOException, ClassNotFoundException {
		// analyze the package that the controller sent
		udpSender = new UDPSender();
		udpSender.sendMessageBroadcast(newPseudo);
		//udpReceiver.ReceiveMessage();
		//InetAddress ipSrc = InetAddress.getByAddress(bytes);
		
		// Recuperation of the pseudo and the address to see that the reception of the Ip
		//is ok
		InetAddress adrSrc = udpReceiver.getAddressIp();
		System.out.println("[cNet212] "+ newPseudo + " preuve 2");
		System.out.println("[cNet212] "+ adrSrc.toString() + "  IP Preuve 2");
	
		//Test to add the user to the list, and check the fonctionality of the list
		User userRemote = new User(newPseudo, adrSrc);
		this.addUserRemote(userRemote);
		nc.receivedFirstMsgHello(adrSrc, newPseudo);
		
		//Close of the thread and close of the sockets
		udpReceiver.setStopThread(true);
		udpSender.closeSocket();
		udpReceiver.closeSocket();
		
	}
	
	
	//Fonction for the first message that one person send when it connects to the system
	//The first part to check the unicity
	public void receivedFirstMsgHello(InetAddress ipsrc, String message) throws IOException, ClassNotFoundException {
		
		//Recuperation of the pseudo of the person of the local machine
		//Recuperation of the pseudo remote, the person connecting
		//System.out.println("[conNet] "+ message + " local machine pseudo");
		String usernameLocal = this.model.getUserLocal().getUsername();
		String usernameRemote = message;
		
		System.out.println("[conNet] "+ usernameLocal + " local machine pseudo");
		// default respond Hello not OK
		System.out.println("[conNet] "+ usernameRemote + " is the new pseudo");
		System.out.println("[conNet] "+ ipsrc + " IP");
		
		//We check the pseudo of the user that has just been connected with the local pseudo
		if (!usernameLocal.equals(usernameRemote)){
			System.out.println("pseudos differents");
			// if remote username is not the same pseudo in comparaison to mine
			//if (this.model.getUserRemoteByName(usernameRemote) == null){
				// if remote username is valid, he is not already in my list
				// reply Hello OK and add him in my list
				
				//**line of code under construction**MsgHello messageAck = new MsgHello(usernameLocal, usernameRemote, true, true);
				//String txt = "Ok";
				//UDPSender udpSenderr = new UDPSender(); 
				//this.udpSender.sendMessage(txt, ipsrc);
				//udpSenderr.sendMessage(txt, ipsrc);
				final String remoteUsername = message;
				User userRemote = new User(message, ipsrc);
				this.addUserRemote(userRemote);
				System.out.println("Agregado en teoria");
				//String txtest = model.getUserRemoteByName(usernameRemote).toString();
				//System.out.println("Pseudo que obtuvo en la lista: "+txtest);
			//} else{
				// he is in my list, do not reply
			//}
		} else{
			// he has the same username as the local, reply Hello not ok
			//**line of code under construction** MsgHello mesack = new MsgHello(usernameLocal, usernameRemote, true, false);
			UDPSender udps = new UDPSender();
			//String txtest = model.getUserRemoteByName(usernameRemote).toString();
			//System.out.println("Pseudo que obtuvo en la lista: "+txtest);
			udps.send_MessageNew("bye", ipsrc);
			System.out.println("Pseudo not possible, try again");

		}
		
		}
		
		
		//**line of code under construction**
		/*void sendMsgHello(){
			System.out.println("[cNet] sendMsgHello to all");
			MsgHello msg = new MsgHello(this.model.getUserLocal().getUsername(), "all", false, true);
			msg.setConnect(true);//hello
			this.udps.sendMessBroadcast(msg);
			this.writeAndDisplayLogLine("User sended Hello\n");
		}*/
	
	
		//Fonction to add the user
		private void addUserRemote(User userRemote) throws IOException, ClassNotFoundException{
			this.model.addUserRemote(userRemote);
			
			//SAVE OF THE LIST **under construction**
			/*//System.out.println("[Model]List of remoteUsers:"+this.userList.toString());
			@SuppressWarnings("unchecked")
			DefaultListModel<ModelUserList> list = model.getRemoteUsers();
			//DefaultListModel<ModelUserList> copy = new ArrayList<>(Model.userList);
			//ArrayList<String> usersListCopy = (ArrayList<String>) list.cln();
			FileOutputStream fos = new FileOutputStream("/C:/UsersList.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			FileInputStream fis = new FileInputStream("/C:/UsersList.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			DefaultListModel<ModelUserList> userLists= (DefaultListModel<ModelUserList>) ois.readObject();
			DefaultListModel<ModelUserList> usrsLits = userLists;
			System.out.println("[cNet] User List saved: "+usrsLits);
			ois.close();*/
			
			//**line of code under construction** this.interf.getDefaultListModel().addElement(userRemote.getUsername());
		}
	
		//Fonction to check if a timer has expired, is under construction aswell
		public void aTimerHasExpired(String remoteUsername) {
			System.out.println("[cNet] User "+ remoteUsername + "'s timer has expired");
			User userRemote = this.model.getUserRemoteByName(remoteUsername);
			if (userRemote != null){
				userRemote.stopTimer();
				this.model.removeUserRemote(userRemote);
			}
		}
		
		//Fonction to see if the timer of the local machine has expired, is under contstruction
		//This timer helps us to see the timer of the local user to see 
		//if he has not received a message with the same pseudo
		private void timerUserLocalHasExpired(){
			this.model.getUserLocal().stopTimer();
			if (this.model.getUserLocal().getState() == State.CONNECTING){
				//this.timerCheck.start();
				this.model.getUserLocal().setState(State.CONNECTED);
				System.out.println("[Controller] It is connected");
				//this.interf.setUIConnected();
			}else{
				//disconnected
				//this.vue.setUIDisconnected();
			}
		}
		
		//Fonction under construction, It is supposed to send a message 
		//saying that the timer has expired
		private void timerCheckHasExpired(){
			this.nc.sendMsgCheck();
		}
		
		//It is the fonction to send a "check" message to all
		//It serves to verify that the pseudo of the list is correct
		void sendMsgCheck(){
			System.out.println("[cNet] sendMsgCheck to all");
			//MsgCheck msg = new MsgCheck(this.model.getUserLocal().getUsername(), "all", false);
			//this.udpSender.sendMessBroadcast(msg);
		}
	
	
		@SuppressWarnings("unchecked")
		public DefaultListModel<ModelUserList> cln(){  
		    try{  
		        return (DefaultListModel<ModelUserList>) super.clone();  
		    }catch(Exception e){ 
		        return null; 
		    }
		}
	

	}


