package chatSystem.interfaces;

import java.awt.* ; 
import javax.swing.*; 


public class ConnexionWindow extends JFrame {

JPanel connexionPanel;

public ConnexionWindow(int height, int width) {

		// GUI creation
JLabel pseudoLabel = new JLabel("Pseudo");
        JTextField pseudoField = new JTextField();
        JButton button = new JButton();
        button.setText("Connexion");
       
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        //GroupLayout works with the horizontal and vertical layouts separately.
        //The layout is defined for each dimension independently.
        //We have to define both of them
        gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(pseudoLabel)
                .addComponent(pseudoField)
                .addComponent(button)
        );

       
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(pseudoLabel)
                .addComponent(pseudoField, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(button, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
       
        setTitle("ChatSystem - Login");
        setSize(height, width);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        setVisible(true);
} 

public static void main(String[] Args) {

ConnexionWindow windowTest = new ConnexionWindow(300, 200);

}

}



/*public class ConnexionWindow extends JFrame {
	
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

}*/ 
 