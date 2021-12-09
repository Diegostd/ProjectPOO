package chatSystem.interfaces;

import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;

public class MainWindow2 extends JFrame{
	JFrame frame;
	public MainWindow2() {
		
		JLabel lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}  
    
    public static void mainWindow2(String[] args)
    {
        // créer un nouveau frame
        JFrame frame = new JFrame("Exemple de JSplitPane");
 
        // créer un panneau
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
 
        // créer des zones de texte
        JTextArea t1 = new JTextArea(10, 16);
        JTextArea t2 = new JTextArea(10, 16);
 
        // définir des textes
        t1.setText("Text Area 1");
        t2.setText("Text Area 2");
 
        // ajouter les zones de texte au panneau
        p1.add(t1);
        p2.add(t2);
 
        // créer un séparateur de panneau
        JSplitPane sep = new JSplitPane(SwingConstants.VERTICAL, p1, p2);
 
        // définir l'orientation du séparateur
        sep.setOrientation(SwingConstants.VERTICAL);
 
        // Ajouter le séparateur
        frame.getContentPane().add(sep);
 
        // définir la taille du frame
        frame.setSize(400, 250);            
        frame.setVisible(true);
    }
    
    //public static void main(String[] Args) {

//    	MainWindow2 MainwindowTest = new MainWindow2();

//        }
}