package ChatSystem.network;

import java.net.*;
import java.io.*;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input
 * from the user and prints echoed message from the server.**/

public class TCPclient {
	
	
	public static void main(String[] args) {
        if (args.length < 2) return;
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try (Socket socket = new Socket(hostname, port)) {
 
        	
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
          
           
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
            String text;
 
            do {
                System.out.println("Enter text: ");
                text = reader.readLine(); 
 
                writer.println(text); //envoi de la donnée 
                
                //******enregistrement del'échange dans la base de données centrale 
 
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
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
