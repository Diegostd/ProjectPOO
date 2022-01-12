package ChatSystem.network;

import java.io.*;
import java.net.*;
  


/* This thread is responsible to handle client connection.*/
public class ServerThread extends Thread {
     
	
	 private Socket socket;
	 
	    public ServerThread(Socket socket) {
	        this.socket = socket;
	    }
	 
	    public void run() {
	        try {
	            InputStream input = socket.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);
	 
	 
	            String text;
	 
	            do {
	                text = reader.readLine();
	                System.out.println("Client : "+ text);
	                String viewText = new StringBuilder(text).reverse().toString();
	                writer.println("Server: " + viewText);
	                //enregistrement du message envoyé dans la bdd; 
	                //il faudra les bons identifiants
	 
	            } while (!text.equals("bye"));
	 
	            socket.close();
	        } catch (IOException ex) {
	            System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	
}
