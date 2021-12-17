package chatSystem.controller;

import java.net.DatagramPacket;


import chatSystem.interfaces.*;

public class NetworkController {
	  private String preuve;
	  private String motdepassepreuve;
	  private UDPSender udpSender;
	  private UDPReceiver udpReceiver;

	  public NetworkController() {
		  this.preuve="toto";
		  this.motdepassepreuve= "toto";
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
	public void NewUserBroadcast(String newPseudo) {
		// analyze the package that the controller sent
		udpSender = new UDPSender();
		udpReceiver = new UDPReceiver();
		
		udpSender.sendPseudosBroadcast(newPseudo);
		udpReceiver.ReceiveMessage();
		udpReceiver.setStopThread(true);
		udpSender.closeSocket();
		udpReceiver.closeSocket();
		
		
	}
	
	
}

