package ChatSystem.network;

import java.io.*;
import java.net.*;

import chatSystem.model.LocalUser;

//multi-threaded  version 
//à décliner pour un échange entre 2 agents sur des postes différents
//contient un constructeur et une méthode receive 


public class TCPserver extends Thread{
     
	private LocalUser serverAgent = new LocalUser();
	private ServerSocket serverSock ; 
	
	
	//getter et setter 
		public LocalUser getServerAgent() {
			return serverAgent;
		}

		public void setServerAgent(LocalUser serverAgent) {
			this.serverAgent = serverAgent;
		}
	
	
	 //constructeur : permet d'initaliser le socket d'écoute en prenant en paramètre l'@ IP de l'agent 
	public TCPserver(String ipAddress, int port) throws Exception {
		// TODO Auto-generated constructor stub
		if (ipAddress != null && !ipAddress.isEmpty()) 
	          this.serverSock = new ServerSocket(port, 1, InetAddress.getByName(ipAddress));
	        else 
	          this.serverSock = new ServerSocket(port, 1, InetAddress.getLocalHost());
		
		System.out.println("Le port d'écoute est actif sur la machine  " + serverSock); 
		
		//mise à jour de l'@IP et du numéro de port du serveur 
		serverAgent.setIP(serverSock.getInetAddress());
		serverAgent.setPort(serverSock.getLocalPort());
		System.out.println("Le port d'écoute est actif sur la machine  " + serverAgent.getIP() +" n° de port: " + serverAgent.getPort() ); 
	}
	
	
	 //écoute et affichage des messages reçus 
	 
	public void run() {
		 while (true) {
			 System.out.println("heo"); 
	        String data = null;
	        Socket client;
			try {
				client = this.serverSock.accept();
				  String clientAddress = client.getInetAddress().getHostAddress();
			        System.out.println("\r\nNew connection from " + clientAddress);
			       do {
			       BufferedReader in = new BufferedReader(
			       new InputStreamReader(client.getInputStream()));        
			        while ( (data = in.readLine()) != "bye" ) {
			            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
			        }
			       }while (!data.equals("bye"));
			    	   
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	    }
	 }

	//affiche les infos d'établissement de connexion 
	/*@SuppressWarnings("unused")
	private void connexInfo (TCPserver app) {
		
		 System.out.println("\r\nRunning Server: " + 
	                "Host=" + app.getSocketAddress().getHostAddress() + 
	                " Port=" + app.getPort());
	}*/
	
	
	
	private String getPort() {
		// TODO Auto-generated method stub
		return null;
	}

	private InetAddress getSocketAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public static void main(String[] args) {
		try {
			TCPserver app = new TCPserver("",9090);  //10.1.5.28
			app.start(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	
}

//constructeur + création socket 
//à partir de la liste des utilisateurs, récupérer son adresse IP 