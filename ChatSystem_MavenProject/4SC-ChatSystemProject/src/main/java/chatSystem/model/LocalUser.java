package chatSystem.model;

public class LocalUser {

	String pseudo; 
	String ID ; //@mail par exemple
	String IP; //c'est mon @IP 
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
	
	
}
