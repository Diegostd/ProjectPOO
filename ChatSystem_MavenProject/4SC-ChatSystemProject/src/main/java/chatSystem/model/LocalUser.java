package chatSystem.model;
import java.awt.event.ActionListener;
import java.net.InetAddress; 

public class LocalUser {

	String pseudo; 
	String ID ; //@mail par exemple
	private InetAddress IP; //c'est mon @IP 
	int port; 
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		this.ID = id;
	} 
	
	public InetAddress getIP() {
		return IP;
	}
	public void setIP(InetAddress iP) {
		IP = iP;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	


	
}
