package chatSystem.network;

import chatSystem.database.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
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
	private DataOutputStream dous;
    private Scanner scanner;
	
	public TCPclient(String hostPseudo, int serverPort, String myPhone,String pseudo, String address) throws Exception {	
		System.out.println ("[TCP Client] Connection of the TCPClient");
		this.socketCli = new Socket(hostPseudo, serverPort); 
		//une fois le serveur lancé, si je remplace serverAddress par l'InetAddress du serveur et serverPort par le numéro de port du serveur ca fait un échec de connexion 
		System.out.println("Le port d'écoute est actif sur la machine  " + socketCli.getLocalAddress() +" n° de port: " + socketCli.getPort() );
		this.dous = new DataOutputStream(socketCli.getOutputStream());
        this.send_TCPMessage(myPhone);
        this.send_TCPMessage(pseudo);
        this.send_TCPMessage(address);
        this.scanner = new Scanner(System.in);
	}
	

	 public void sendMesaggeAndSaveToDatabase(String message, String myPhone, String yourPhone) {
		   //String input;
	    	  /* do {
	        	System.out.println("Enter text: ");
	            input = scanner.nextLine();
	            PrintWriter out;*/
	            
				try {
					PrintWriter out = new PrintWriter(this.socketCli.getOutputStream(), true);
					out.println(message);
		            out.flush();
		            
		            //horodatage 
		            String msgDate = clock.horodateMsg(); 
		            System.out.println("Enregistrement dans la BDD " + msgDate);  
		            //enregistrement dans la base de données    
		            java.sql.Connection con = MysqlCon.connectBDD(); 
	                MysqlCon mysqlObj = new MysqlCon(); 
	                //mysqlObj.addHistoryLine(con,clientMe.getID(),receiver.getID(), input, msgDate);	   
	                
	                mysqlObj.addHistoryLine(con,myPhone,yourPhone, message, msgDate);
	              
			            
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//}  
				
				//while (!message.equals("bye"));	          
	            
	       }
	       
	 public boolean send_TCPMessage(String message) {
	        try {
	            dous.writeUTF(message);
	            dous.flush();
	            return true;
	        } catch (SocketException e) {
	            return false;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public void closeTCPSocket() {
	        try {
	        	dous.close();
		        socketCli.close();
	            System.out.println("Socket of the connection TCP is closed");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error while closing the TCP Socket");
	        }
	    }
	
	}


