package ChatSystem.TCPhandler;

import java.net.*;
import java.io.*;

import chatSystem.model.LocalUser;

public class ServerTCP extends Thread {
   	
	private LocalUser ServerMe ; 
	private ServerSocket server; 
	
	//création du socket serveur à partir de son IP 
	
    public void MyServerSocket(String ipAddress) throws Exception {
        if (ipAddress != null && !ipAddress.isEmpty()) 
          this.server = new ServerSocket(0, 1, InetAddress.getByName(ipAddress));
        else 
          this.server = new ServerSocket(0, 1, InetAddress.getLocalHost());
    }
	
	//méthode pour lire les messages reçus 
    
    @SuppressWarnings("unused")
	private void listen() throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));        
        while ( (data = in.readLine()) != null ) {
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
        }
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }
    
    public int getPort() {
        return this.server.getLocalPort();
    }
    	
	
}
