package chatSystem.controller;
import chatSystem.interfaces.*;

public class NetworkController {
	  private String preuve;
	  private String motdepassepreuve;


	  public NetworkController() {
		  this.preuve="toto";
		  this.motdepassepreuve= "toto";
		  }
	  
	  
	public boolean testPseudo (String pseudo){
		if (pseudo.equals(preuve)&&pseudo.equals(motdepassepreuve)) {
			return true;
			
		}
		else {
			return false;	
		}
		
	}
}

