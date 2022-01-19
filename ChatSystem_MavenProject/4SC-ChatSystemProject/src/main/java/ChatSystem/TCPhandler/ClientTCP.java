package ChatSystem.TCPhandler;


	import chatSystem.database.*;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.io.PrintWriter;
	import java.net.Socket;
	import java.net.UnknownHostException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import chatSystem.model.LocalUser;

	/**
	 * This program demonstrates a simple TCP/IP socket client that reads input
	 * from the user and prints echoed message from the server.**/
	public class ClientTCP {
		
		//this serverAgent was selected in the connected userlist (method to be implemented) --> that's how we will have acces to the receiver ID
		
		/*private LocalUser serverAgent = new LocalUser(); 
		private LocalUser me = new LocalUser(); 
		String senderID = me.getID(); 
		String receiverID = serverAgent.getID(); */
		
		
		//the client is sending a msg --> we can have access locally to 
		//récupérer le contenu du msg 
		
		String msgContent; //(et horodatage --> clé primaire : pk_history : IDsender, IDreceiver, et horodatage ) 	
		
		public static void main(String[] args) { 
	        if (args.length < 2) return;
	 
	        String hostname = args[0]; //hostname
	        int port = Integer.parseInt(args[1]); //numéro de port 
	 
	        try (Socket socket = new Socket(hostname, port)) {
	      	
	            OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);         
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        
	            String text;
	 
	            do {
	                System.out.println("Enter text: ");
	                text = reader.readLine(); 
	                
	               
	 
	                writer.println(text); //envoi de la donnée 
	                //***time stamp
	                String formattedDate = new String(); 
	                formattedDate = Horodator.horodateMsg(); 
	                System.out.println("Enregistrement dans la BDD " + formattedDate);  
	                
	                //***enregistrement de l'échange dans la base de données centrale                             
	                
	                java.sql.Connection con = MysqlCon.connectBDD(); 
	                MysqlCon mysqlObj = new MysqlCon(); 
	                mysqlObj.addHistoryLine(con, "me" ,"you", text, formattedDate);
	                //sender et receiver sont à définir 
	                
	                mysqlObj.disconnectBDD(con);
	                
	                
	                InputStream input = socket.getInputStream();
		            BufferedReader reader1 = new BufferedReader(new InputStreamReader(input)); //définition d'un nouveau buffer d'écoute sur le flux entrant
	                String time = reader1.readLine();
	 
	                System.out.println(time);
	                           
	               
	 
	            } while (!text.equals("bye"));
	 
	            socket.close();
	 
	        } catch (UnknownHostException ex) {
	 
	            System.out.println("Server not found: " + ex.getMessage());
	 
	        } catch (IOException ex) {
	 
	            System.out.println("I/O error: " + ex.getMessage());
	        }
		}} 

