package ChatSystem.network;


import java.net.DatagramPacket;
import java.net.InetAddress;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.lang.Object.*; 
import chatSystem.interfaces.*;

public class NetworkController {
	  private String preuve; //pseudo valide ? 
	  private String motdepassepreuve;
	  private UDPSender udpSender;
	  private UdpReceiver udpReceiver;

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
	//rajouter un timer 
	//timer pour écouter si il y a un retour 
	//@Test
	public void NewUserBroadcast(String newPseudo) {
		// analyze the package that the controller sent
		udpSender = new UDPSender();
		udpReceiver = new UdpReceiver();
		udpSender.sendPseudosBroadcast(newPseudo); 				
		udpReceiver.ReceiveMessage();
		
		udpReceiver.setStopThread(true);
		udpSender.closeSocket();
		udpReceiver.closeSocket(); 
		
		
		 // Création et lancement du timer	
		
	 
	  }
		//si pas de retour --> on valide le pseudo et on redit out le monde que et on bascule sur la main window 
		
		
		//sinon on affiche un msg d'erreur sur la fenêtre de connexion 
	
	
	//On suppose que le pseudo est unique, il faudra créer la liste des utilisateurs connectés et initier un échange dde msg tcp entre deux users et gérer l'historique  
	
	}
	
	
