package chatSystem.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import chatSystem.interfaces.*;
import chatSystem.model.*;

public class NetworkController {
	  private String preuve;
	  private String motdepassepreuve;
	  private UDPSender udpSender;
	  private UDPReceiver udpReceiver;
	  private Thread udprThread;
	  private Model model;
	  private NetworkController nc;

	  public NetworkController() {
		  this.preuve="toto";
		  this.motdepassepreuve= "toto";
		  
		  this.udpReceiver = new UDPReceiver();
		  //this.udps = new UDPSender();
		  this.udprThread = new Thread(this.udpReceiver);
		  this.udprThread.start();
		  }
	  
	  
	public boolean testPseudo (String pseudo){
		if (pseudo.equals(preuve)&&pseudo.equals(motdepassepreuve)) {
			return true;
			
		}
		else {
			return false;	
		}
		
	}
	
	/* when user connect, he must send a NewUserBroadcast*/
	//@Test
	public void NewUserBroadcast(String newPseudo) throws UnknownHostException {
		// analyze the package that the controller sent
		udpSender = new UDPSender();
		udpSender.sendMessageBroadcast(newPseudo);
		//udpReceiver.ReceiveMessage();
		//InetAddress ipSrc = InetAddress.getByAddress(bytes);
		InetAddress adrSrc = UDPReceiver.addressSrc;
		System.out.println("[cNet212] "+ newPseudo + " say hello to me");
		//String srcIP = adrSrc.toString()
		//System.out.println("[cNet212] "+ srcIP + " IP");
		nc.receivedFirstMsgHello(adrSrc, newPseudo);
		
		
		udpReceiver.setStopThread(true);
		udpSender.closeSocket();
		udpReceiver.closeSocket();
		
		
		
	}
	
	private void receivedFirstMsgHello(InetAddress ipsrc, String message){
		String usernameLocal = this.model.getUserLocal().getUsername();
		String usernameRemote = message;
		// default respond Hello not OK
		
		System.out.println("[cNet] "+ message + " say hello to me");
		System.out.println("[cNet] "+ ipsrc + " IP");
		
		if (!usernameLocal.equals(message)){
			// if remote username is not the same pseudo in comparaison to mine
			if (this.model.getUserRemoteByName(usernameRemote) == null){
				// if remote username is valid, he is not already in my list
				// reply Hello OK and add him in my list
				//MsgHello messageAck = new MsgHello(usernameLocal, usernameRemote, true, true);
				this.udpSender.sendMessage("not OK", ipsrc);
				final String remoteUsername = message;
				User userRemote = new User(message, ipsrc, new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						aTimerHasExpired(remoteUsername);
					}
				});
				this.addUserRemote(userRemote);
			} else{
				// he is in my list, do not reply
			}
		} else{
			// he has the same username as the local, reply Hello not ok
			//MsgHello mesack = new MsgHello(usernameLocal, usernameRemote, true, false);
			this.udpSender.sendMessage("not OK", ipsrc);

		}
		
		}
		
		
		
		/*void sendMsgHello(){
			System.out.println("[cNet] sendMsgHello to all");
			MsgHello msg = new MsgHello(this.model.getUserLocal().getUsername(), "all", false, true);
			msg.setConnect(true);//hello
			this.udps.sendMessBroadcast(msg);
			this.writeAndDisplayLogLine("User sended Hello\n");
		}*/
		
		private void addUserRemote(User userRemote){
			this.model.addUserRemote(userRemote);
			//this.vue.getDefaultListModel().addElement(userRemote.getUsername());
		}
	
	
		public void aTimerHasExpired(String remoteUsername) {
			System.out.println("[cNet] User "+ remoteUsername + "'s timer has expired");
			User userRemote = this.model.getUserRemoteByName(remoteUsername);
			if (userRemote != null){
				userRemote.stopTimer();
				this.model.removeUserRemote(userRemote);
			}
		}
	
	

	}


