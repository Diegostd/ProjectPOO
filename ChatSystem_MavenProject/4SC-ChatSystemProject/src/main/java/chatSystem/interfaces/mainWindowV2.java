package chatSystem.interfaces;

import javax.swing.event.*; 
import java.awt.*; 
import javax.swing.*; 

public class mainWindowV2 extends JFrame 
{  
	 static JFrame frame; 
	
	public mainWindowV2() throws HeadlessException //signature du constructeur 
    { 
        // créer un nouveau frame 
        frame = new JFrame("Exemple de JSplitPane"); 
  
        // créer un panneau
        JPanel mainbar = new JPanel(); 
        JPanel p1 = new JPanel(); 
        JPanel p2 = new JPanel(); 
  
        //créer des zones de texte 
        JList t1 = new JList(); 
        //JTextArea t2 = new JTextArea(10, 16); 
  
        // définir des textes
       // t1.setText("Connected user list "); 
        //t2.setText("Text Area 2"); 
  
        // ajouter les zones de texte au panneau
        p1.add(t1); 
       // p2.add(t2);
  
        // créer un séparateur de panneau
        
        JSplitPane sep2 = new JSplitPane(SwingConstants.HORIZONTAL,p1, mainbar); 
        JSplitPane sep = new JSplitPane(SwingConstants.VERTICAL, p1, p2); 
        // définir l'orientation du séparateur
        sep2.setOrientation(SwingConstants.HORIZONTAL); 
        sep.setOrientation(SwingConstants.VERTICAL); 
  
        // Ajouter le séparateur
     
        frame.add(sep2); 
        frame.add(sep); 
        //frame.setContentPane(p1);
  
        // définir la taille du frame 
        frame.setSize(400, 250);            
        frame.setVisible(true); 
    } 



	public static void main(String[] Args) {

		mainWindowV2 MainwindowTest = new mainWindowV2();

		}
}