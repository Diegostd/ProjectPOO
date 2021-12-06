package chatSystem.interfaces;

import java.awt.* ; 
import javax.swing.*; 



public class ConnexionWindow extends JFrame {
	
	JPanel connexionPanel; 
	
	public ConnexionWindow(int height, int width) {
		 
		//GUI creation
		super("ChatSytem Connexion Interface"); 
		setSize(width,height); 
		this.setLayout(new BorderLayout());
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		//Panel creation 
		
	connexionPanel = new JPanel(); 
	connexionPanel.setLayout(new BoxLayout(connexionPanel, BoxLayout.LINE_AXIS));
	 
	    //Label creation 
	connexionPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
	JLabel pseudo = new JLabel("Votre pseudo : ",JLabel.TRAILING);
	JTextField pseudoText = new JTextField(); 
	pseudoText.setSize(50, 50); 
	//JPanel wrapper = new JPanel( new FlowLayout(50,50,FlowLayout.LEFT));
	//wrapper.add( pseudoText );
	
	//wrapper.setSize(50, 50);
	//wrapper.add((Box.createVerticalGlue())); 
	connexionPanel.add(pseudo); 
	
	//connexionPanel.add((Box.createHorizontalGlue())); 
	connexionPanel.add(pseudoText); 
	//connexionPanel.add((Box.createVerticalGlue())); 
	//connexionPanel.add(wrapper); 
	//connexionPanel.add((Box.createHorizontalGlue())); 
	
	add(connexionPanel); 

	setVisible(true); 
	}	//design de la fenêtre de connexion 	
	

public static void main (String[] Args) {
	
	ConnexionWindow windowTest = new ConnexionWindow(500,500); 
	
}

}
 