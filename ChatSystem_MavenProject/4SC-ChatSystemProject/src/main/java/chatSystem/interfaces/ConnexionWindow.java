package chatSystem.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import chatSystem.controller.NetworkController;
import chatSystem.controller.UDPSender;
import chatSystem.model.State;
import chatSystem.model.UDPMessage;
import chatSystem.controller.UDPReceiver;
import chatSystem.controller.*;
import chatSystem.model.*;

public class ConnexionWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textPseudo;
	private JTextField textPhone;
	JButton btnLogin = new JButton();
	private NetworkController networkcontroller;
	private UDPSender udpSender;
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
	public ConnexionWindow(NetworkController networkController) {
		this.networkcontroller = networkController;
		//JFrame frame = new JFrame();
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
		
		JLabel lblMotDePasse = new JLabel("Telephone number");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 1;
		contentPane.add(lblMotDePasse, gbc_lblMotDePasse);
		
		textPhone = new JTextField();
		GridBagConstraints gbc_textMotDePasse = new GridBagConstraints();
		gbc_textMotDePasse.insets = new Insets(0, 0, 5, 0);
		gbc_textMotDePasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMotDePasse.gridx = 1;
		gbc_textMotDePasse.gridy = 1;
		contentPane.add(textPhone, gbc_textMotDePasse);
		textPhone.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 3;
		contentPane.add(lblLogin, gbc_lblLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 4;
		contentPane.add(btnLogin, gbc_btnLogin);
		setVisible(true);
	}

	@Override
		public void actionPerformed(ActionEvent e) {
			String textIntroduced;
			String phoneIntroduced;
			textIntroduced = textPseudo.getText();
			phoneIntroduced = textPhone.getText();
	        try {
				if (!this.networkcontroller.isUserConnected()) {
				    JOptionPane.showMessageDialog(btnLogin, "Error in the network");
				}
			} catch (HeadlessException | UnknownHostException e2) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(btnLogin, "HeadlessException or UnkownHostException");
				e2.printStackTrace();
			}

	        try {
				if (!textIntroduced.isEmpty() && !phoneIntroduced.isEmpty() && this.networkcontroller.pseudoUnicity(textIntroduced, State.CONNECTING,phoneIntroduced)) {
					new MainWindow(networkcontroller).setVisible(true);
					//this.networkcontroller.setMainWindow();
				    dispose();
				} else {
				    JOptionPane.showMessageDialog(btnLogin, "Invalid psuedo or invalid telephone, try again please");
				}
			} catch (HeadlessException | UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
}
