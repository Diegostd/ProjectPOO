package chatSystem.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import chatSystem.controller.NetworkController;
import chatSystem.model.*;

public class TCPThreads extends Thread {
	private int timeout = 1500;
	private DataInputStream inputStream;
    private String userPhone;
    private Socket serverOfTheClient;
    private String pseudo;
    private String address;
    private boolean running;
    private NetworkController networkController;
    
    
public TCPThreads(Socket server, NetworkController networkController) {
	// TODO Auto-generated constructor stub
	
	this.networkController = networkController;
	
	this.serverOfTheClient = server;
    
    try {
        this.serverOfTheClient.setSoTimeout(timeout);
        this.inputStream = new DataInputStream(serverOfTheClient.getInputStream());
        this.address = this.inputStream.readUTF();
        this.userPhone = this.inputStream.readUTF();
        this.pseudo = this.inputStream.readUTF();
        System.out.println("A connection is established with the user: Address " + this.address+ " Pseudo: "+ this.pseudo + " Phone: " +this.userPhone);
        this.networkController.toUpdateOrAddUser(userPhone, pseudo, address);
    } catch (IOException e) {
        System.out.println("Error while creating DIS Server TCP Thread");
    }
	
}

public void run() {
    running = true;

    try {
        while (running) {
            try {
                String text = inputStream.readUTF();
                Messages msg = new Messages(text, userPhone);
                this.networkController.controllerOfANewMessageTCP(userPhone, msg);
                System.out.println("Message received: " + text + " From the user: " + this.userPhone);
            } catch (SocketTimeoutException e) {
                continue;
            }
        }

        inputStream.close();
        serverOfTheClient.close();
    } catch (Exception e) {
        System.out.println("Error in the server TCP");
    }
}
    

public void setRunning(boolean running) {
    this.running = running;
}

}
