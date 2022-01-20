package ChatSystem.network;

import chatSystem.database.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import chatSystem.model.LocalUser;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input
 * from the user and prints echoed message from the server. **/

public class TCPclient extends Thread {
	
	//this serverAgent was selected in the connected userlist (method to be implemented) --> that's how we will have acces to the receiver ID
	
	//the client is sending a msg --> we can have access locally to 
	//récupérer le contenu du msg 
	
	//(et horodatage --> clé primaire : pk_history : IDsender, IDreceiver, et horodatage ) 	
	private LocalUser clientMe;
	private LocalUser receiver; 
	
	private Horodator clock = new Horodator(); 
	private Socket socketCli;
    private Scanner scanner;
	
	public TCPclient(InetAddress serverAddress, int serverPort) throws Exception {	
		System.out.println ("heho"); 
		this.socketCli = new Socket(serverAddress, serverPort); 
		//une fois le serveur lancé, si je remplace serverAddress par l'InetAddress du serveur et serverPort par le numéro de port du serveur ca fait un échec de connexion 
		System.out.println("Le port d'écoute est actif sur la machine  " + socketCli.getLocalAddress() +" n° de port: " + socketCli.getPort() ); 
        this.scanner = new Scanner(System.in);
	}
	

	 public void run() {
		   String input;
	       while (true) {
	    	   do {
	        	System.out.println("Enter text: ");
	            input = scanner.nextLine();
	            PrintWriter out;
	            
				try {
					out = new PrintWriter(this.socketCli.getOutputStream(), true);
					out.println(input);
		            out.flush();
		            
		            //horodatage 
		            String msgDate = clock.horodateMsg(); 
		            System.out.println("Enregistrement dans la BDD " + msgDate);  
		            //enregistrement dans la base de données    
		            java.sql.Connection con = MysqlCon.connectBDD(); 
	                MysqlCon mysqlObj = new MysqlCon(); 
	                //mysqlObj.addHistoryLine(con,clientMe.getID(),receiver.getID(), input, msgDate);	   
	                
	                mysqlObj.addHistoryLine(con,"me","you", input, msgDate);
	              
			            
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	}  
				
				while (!input.equals("bye"));	          
	          
	            InputStream input1;
				try {
					input1 = socketCli.getInputStream();
					BufferedReader reader1 = new BufferedReader(new InputStreamReader(input1)); //définition d'un nouveau buffer d'écoute sur le flux entrant
	                String time = reader1.readLine();
	                System.out.println(time);           
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  try {
					socketCli.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	                 
	       }
	       
	    }

	public static void main(String[] args) { 
        
		 	TCPclient client;
			try {
				client = new TCPclient(
				        InetAddress.getByName(args[0]), 
				        Integer.parseInt(args[1]));
				System.out.println("\r\nConnected to Server: " + client.socketCli.getInetAddress());
		        client.start();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       	        
	
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
