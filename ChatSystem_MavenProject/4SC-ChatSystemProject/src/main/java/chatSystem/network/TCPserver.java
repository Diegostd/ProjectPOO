package chatSystem.network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import chatSystem.controller.NetworkController;
import chatSystem.model.LocalUser;
import chatSystem.model.User;


//multi-threaded  version 
//à décliner pour un échange entre 2 agents sur des postes différents
//contient un constructeur et une méthode receive 


public class TCPserver extends Thread{
     
	private int timeout = 1500;
	private boolean running;
	private User serverAgent = new User(null, null, null);
	private ServerSocket serverSock ; 
	private ArrayList<TCPThreads> threads;
	private NetworkController networkController;
	
	
	//getter et setter 
		public User getServerAgent() {
			return serverAgent;
		}

		public void setServerAgent(User serverAgent) {
			this.serverAgent = serverAgent;
		}
	
	
	 //constructeur : permet d'initaliser le socket d'écoute en prenant en paramètre l'@ IP de l'agent 
	public TCPserver(int port, NetworkController ntwController) throws Exception {
		// TODO Auto-generated constructor stub
		this.networkController = ntwController;
	    this.threads = new ArrayList<TCPThreads>();
		this.serverSock = new ServerSocket(port);
		
		//System.out.println("\n[TCPServer] The listening port is active on the machine" + serverSock); 
		
		//mise à jour de l'@IP et du numéro de port du serveur 
		serverAgent.setIp(serverSock.getInetAddress());
		serverAgent.setUserPort(serverSock.getLocalPort());
		System.out.println("The listening port is active on the machine  " + InetAddress.getLocalHost() +" n° de port: " + serverAgent.getUserPort() ); 
	}
	
	
	 //écoute et affichage des messages reçus 
	 
	public void run() {
		try {
            listenerOfTCP();
        } catch (Exception e) {
        	System.out.println("Error, exception");
            e.printStackTrace();
        }
	 }

	private String getPort() {
		// TODO Auto-generated method stub
		return null;
	}

	private InetAddress getSocketAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void listenerOfTCP() throws IOException {
		System.out.println("[TCPServer] TCP listener is working");
		running = true;
		while (!running) {

	        Socket client;
	        serverSock.setSoTimeout(timeout);
			try {
				client = this.serverSock.accept();
				  String clientAddress = client.getInetAddress().getHostAddress();
			        System.out.println("\r\nNew connection from " + clientAddress);
			        TCPThreads th = new TCPThreads(client, this.networkController);
			        th.start();
	                threads.add(th);
			} catch (SocketTimeoutException e) {
				// TODO Auto-generated catch block
				System.out.println("[TCPServer] Timeout exception");
				e.printStackTrace();
			}
	      
	    }
		
		 for (TCPThreads th : threads) {
	            if (th.isAlive()) {
	                th.setRunning(running);
	                while (th.isAlive());
	            }
	}
		 serverSock.close();
}
	
}
//constructeur + création socket 
//à partir de connexion, récupérer son adresse IP 