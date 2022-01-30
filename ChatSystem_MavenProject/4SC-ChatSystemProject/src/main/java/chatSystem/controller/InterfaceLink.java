package chatSystem.controller;
import chatSystem.interfaces.*;


public class InterfaceLink {
	 private String pseudo;
	 private String motdepassepreuve;


	public InterfaceLink() {
		// TODO Auto-generated constructor stub
		 this.pseudo="toto";
		  this.motdepassepreuve= "toto";
	}

	public boolean testPseudo (String pseudo){
		if (pseudo.equals(pseudo)&&pseudo.equals(motdepassepreuve)) {
			return true;
			
		}
		else {
			return false;	
		}
	
	
	}} 

// le controller reçoit le résultat du udp broadcast et sur la base de l'info reçue, il relaie l'info à l'utilisateur