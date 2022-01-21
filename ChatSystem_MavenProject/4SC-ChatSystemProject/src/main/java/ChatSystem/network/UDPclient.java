package ChatSystem.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*cette classe doit gérer l'envoi de plusieurs types de messages en mode broadcast ou pas :
  1) le format des messages à envoyer doit permettre de distinguer la raison de l'envoi (notification pseudo, modif pseudo, déconnexion etc)
     les types de champs possibles : PSEUDO, CONNECTE, EXISTE, DECONNECTE, MODIFIE 
     
 */


public class UDPclient {
	private String hostname = "255.255.255.255"; 
	private int port = 5555; 
	private DatagramSocket dgramSocket;
	private InetAddress address;
	
	
	
		
	public UDPclient() throws SocketException, UnknownHostException {
		//Create a DatagramSocket object
		this.dgramSocket = new DatagramSocket();
		//conversion of hostname to InetAddress needed to send data packet
		this.address = InetAddress.getByName(hostname);		
	
	}
	
	public void send_Message() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text;
        System.out.println(address); 
   
        do {
        	System.out.print("Enter something:");
            text = br.readLine();
            DatagramPacket outPacket = new DatagramPacket(text.getBytes(), text.length(), address, port);     
      
            System.out.println("le paquet" + outPacket.getData() +" "+outPacket.getLength()); 
            dgramSocket.send(outPacket);
                   
            //Create a buffer for incoming datagrams
            byte[] buffer = new byte[256];
            //Create a DatagramPacket object for the incoming datagram
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            //Accept an incoming datagram
            dgramSocket.receive(inPacket);
            //Retrieve the data from the buffer
            String response = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("received DATA from UDP Server = " + response); 
           
 
            
        } while (!text.equals("bye"));
        //Close the DatagramSocket:
        dgramSocket.close();
	}	
	
	
}
