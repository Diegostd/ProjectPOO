package chatSystem.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chatSystem.controller.NetworkController;
import chatSystem.controller.NetworkController2;
import chatSystem.controller.UDPReceiver;

public class ConnexionWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textPseudo;
	private JTextField textMotDePasse;
	private NetworkController ntcon;
	private NetworkController2 udpSender;
	private UDPReceiver udpReceiver;
	JFrame frame = new JFrame();
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionWindow frame = new ConnexionWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the frame.
	 */
	public ConnexionWindow() {
		//JFrame frame = new JFrame();
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		GridBagConstraints gbc_lblPseudo = new GridBagConstraints();
		gbc_lblPseudo.anchor = GridBagConstraints.EAST;
		gbc_lblPseudo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPseudo.gridx = 0;
		gbc_lblPseudo.gridy = 0;
		contentPane.add(lblPseudo, gbc_lblPseudo);
		
		textPseudo = new JTextField();
		GridBagConstraints gbc_textPseudo = new GridBagConstraints();
		gbc_textPseudo.insets = new Insets(0, 0, 5, 0);
		gbc_textPseudo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPseudo.gridx = 1;
		gbc_textPseudo.gridy = 0;
		contentPane.add(textPseudo, gbc_textPseudo);
		textPseudo.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 1;
		contentPane.add(lblMotDePasse, gbc_lblMotDePasse);
		
		textMotDePasse = new JTextField();
		GridBagConstraints gbc_textMotDePasse = new GridBagConstraints();
		gbc_textMotDePasse.insets = new Insets(0, 0, 5, 0);
		gbc_textMotDePasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMotDePasse.gridx = 1;
		gbc_textMotDePasse.gridy = 1;
		contentPane.add(textMotDePasse, gbc_textMotDePasse);
		textMotDePasse.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 3;
		contentPane.add(lblLogin, gbc_lblLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//boolean pseudoOk=myNetworkComtoller();
				//if pseudOk -- fermer la fen actuelle et ouvrir la fen principale
				// if psudoOk est egale a false -- afficher ds un JText un msg d erreur
				String pseudoOk=textPseudo.getText();
				ntcon = new NetworkController();
				if(ntcon.testPseudo(pseudoOk)== true) {
					//Close current window and open mainwindow
					System.out.println("ok");
					dispose();
					udpSender.sendPseudosBroadcast(pseudoOk);
					udpReceiver.ReceiveMessage();
					udpReceiver.setStopThread(true);
					udpSender.closeSocket();
					udpReceiver.closeSocket();
					new MainWindow2();
				}
				else {
					System.out.println("pas ok");
				}
			}
		});
		
		
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 4;
		contentPane.add(btnLogin, gbc_btnLogin);
		setVisible(true);
	}

}
