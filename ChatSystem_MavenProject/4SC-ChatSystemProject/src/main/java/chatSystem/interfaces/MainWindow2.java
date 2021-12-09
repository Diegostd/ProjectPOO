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
        // cr�er un nouveau frame
        JFrame frame = new JFrame("Exemple de JSplitPane");
 
        // cr�er un panneau
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
 
        // cr�er des zones de texte
        JTextArea t1 = new JTextArea(10, 16);
        JTextArea t2 = new JTextArea(10, 16);
 
        // d�finir des textes
        t1.setText("Text Area 1");
        t2.setText("Text Area 2");
 
        // ajouter les zones de texte au panneau
        p1.add(t1);
        p2.add(t2);
 
        // cr�er un s�parateur de panneau
        JSplitPane sep = new JSplitPane(SwingConstants.VERTICAL, p1, p2);
 
        // d�finir l'orientation du s�parateur
        sep.setOrientation(SwingConstants.VERTICAL);
 
        // Ajouter le s�parateur
        frame.getContentPane().add(sep);
 
        // d�finir la taille du frame
        frame.setSize(400, 250);            
        frame.setVisible(true);
    }
    
    //public static void main(String[] Args) {

//    	MainWindow2 MainwindowTest = new MainWindow2();

//        }
}