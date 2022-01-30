package chatSystem.model;

import java.net.InetAddress;

public class UDPMsg {
    
	public UDPMsg() {
		
		// TODO Auto-generated constructor stub
	}
	
	//méthode qui crée le format du paquet de début de connexion 
	public  String Packet1 (State state, String pseudo) {
		 String str = state+"@"+pseudo;
		
		 return str; 
	}
	
	public  String Paket2 (State state, String pseudo, String oldpseudo) {
		 String str = state+"@"+pseudo+"@"+oldpseudo;
		// String[] arrOfStr = str.split("@", 5);
		 return str; 
	}
	
	public  String Paket3 (State state, String pseudo,int ID, int port,InetAddress IP) {
		 String str = state+"@"+pseudo+"@"+ID+"@"+port+"@"+IP;
		 return str; 
	}
	
	public String[] splitStr (String str, int slice) {
		return str.split("@",slice); 
	}
	 //String[] arrOfStr = str.split("@", 2);
	public static void main(String[] args) {
		UDPMsg packetTest = new UDPMsg(); 
		String packet1 = packetTest.Packet1(State.CONNECTED,"Gaston"); 
		System.out.println(packet1); 
		String[] array1 = packet1.split("@",5);  
		 for (String a : array1)
	            System.out.println(a);
		 System.out.println(array1[0]);
	    }
	
	}

