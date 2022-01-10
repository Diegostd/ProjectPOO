package ChatSystem.network;

import java.io.*;
import java.net.*;

//multi-threaded  version 
//à décliner pour un échange 
public class TCPserver {

	public TCPserver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		 if (args.length < 1) return;
		 int port = Integer.parseInt(args[0]);
	 
	    try (ServerSocket serverSocket = new ServerSocket(port)) {
	 
	    System.out.println("Server is listening on port " + port);
	    while (true) {
	                Socket socket = serverSocket.accept();
	                System.out.println("New client connected");
	 
	                new ServerThread(socket).start(); //appellera la méthode run 
	            }
	 
	        } catch (IOException ex) {
	            System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
			
		
	}
}

//constructeur + création socket 
//à partir de la liste des utilisateurs, récupérer son adresse IP 