package chatSystem.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chatSystem.controller.NetworkController;
import chatSystem.model.Messages;
import chatSystem.model.State;

import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final JSeparator separator = new JSeparator();
	private NetworkController networkcontroller;
	private JTextField textFieldToSend;
	private JPasswordField ChatSpace;
	
	DefaultListModel<String> defaultListOfUsers = new DefaultListModel<String>();
	JList<String> jListOfUsersConnected = new JList<String>(defaultListOfUsers);
	String actualUserSelected = "";

	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 */
	public MainWindow(NetworkController networkcontroller) throws UnknownHostException {
		this.networkcontroller = networkcontroller;
		this.setTitle(networkcontroller.getPseudo());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		separator.setBounds(214, 30, 25, 567);
		contentPane.add(separator);
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 30, 679, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(214, 366, 465, 28);
		contentPane.add(separator_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 182, 405);
		contentPane.add(scrollPane);
		
		JList listOfUsers = new JList();
		listOfUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0 && !defaultListOfUsers.isEmpty()) {
					actualUserSelected = jListOfUsersConnected.getSelectedValue();
					String userPhone = getPhoneOfSelectedUser();
					networkcontroller.newTCPConnection(userPhone);
				}
			}
		});
		scrollPane.setViewportView(listOfUsers);
		
		JLabel lblUserList = new JLabel("Connected User List");
		scrollPane.setColumnHeaderView(lblUserList);
		
		JButton btnChangePseudo = new JButton("Change Pseudo");
		btnChangePseudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newPseudo = JOptionPane.showInputDialog(contentPane, "Introduce the new Pseudo");
				try {
					if (newPseudo != null && networkcontroller.pseudoUnicity(newPseudo, State.UPDATE,null)) {
						setTitle(newPseudo);
						JOptionPane.showMessageDialog(contentPane, "Pseudo updated correctly");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Error in updating the pseudo");
					}
				} catch (HeadlessException | UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

		});
		btnChangePseudo.setBounds(26, 494, 154, 25);
		contentPane.add(btnChangePseudo);
		
		JButton btnChangePseudo_1 = new JButton("Disconnect");
		btnChangePseudo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					processToDisconnection();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnChangePseudo_1.setBounds(26, 531, 154, 25);
		contentPane.add(btnChangePseudo_1);
		
		textFieldToSend = new JTextField();
		textFieldToSend.setBounds(226, 391, 441, 64);
		contentPane.add(textFieldToSend);
		textFieldToSend.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textFieldToSend.getText();
				if (actualUserSelected != "" && !text.isEmpty()) {
					String userPhone = getPhoneOfSelectedUser();
					networkcontroller.send_TCPMessageToUser(text, userPhone);
				}
				textFieldToSend.setText("");
				
			}
		});
		btnSend.setBounds(494, 467, 117, 25);
		contentPane.add(btnSend);
		
		ChatSpace = new JPasswordField();
		ChatSpace.setBounds(238, 46, 417, 308);
		contentPane.add(ChatSpace);
		
		JButton btnFile = new JButton("File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFile.setBounds(568, 517, 86, 25);
		contentPane.add(btnFile);
		
		JLabel lblMyPseudo = new JLabel("My Pseudo ");
		lblMyPseudo.setBounds(26, 3, 111, 15);
		contentPane.add(lblMyPseudo);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(638, 163, 17, 61);
		contentPane.add(scrollBar);
		
		JButton btnUpdateUsers = new JButton("Update List");
		btnUpdateUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				defaultListOfUsers.clear();
				for (String user : networkcontroller.getAllConnectedUsers().keySet()) {
					defaultListOfUsers.addElement(user);
				}
				if (!defaultListOfUsers.contains(actualUserSelected)) {
					actualUserSelected = "";
				}
				System.out.println("Connected users: " + defaultListOfUsers);
				
			}
		});
		btnUpdateUsers.setBounds(26, 458, 154, 25);
		contentPane.add(btnUpdateUsers);
		
		//this.networkcontroller.notifyToAllUserStateUpdate(State.UNKNOWN); //test
		setVisible(false);
	}
	
	private void processToDisconnection() throws UnknownHostException {
		this.networkcontroller.notifyToAllUserStateUpdate(State.DISCONNECTED);
		System.exit(getDefaultCloseOperation()); 
	}
	
	public String getPhoneOfSelectedUser() {
		return networkcontroller.getUserPhoneByPseudo(this.actualUserSelected);
	}
	
	
	public void updateUsersList() {
		defaultListOfUsers.clear();
		for (String user : networkcontroller.getAllConnectedUsers().keySet()) {
			defaultListOfUsers.addElement(user);
		}
		System.out.println("users connected: " + defaultListOfUsers);
	}
	
	public void updateAndShowUsersMessages() {
		String userPhone = getPhoneOfSelectedUser();
		ArrayList<Messages> messages = this.networkcontroller.getAllMessagesFromUser(userPhone);
		int i;
		for (i = 0; i < messages.size(); i++) {
			System.out.println("User ID "+userPhone+" Message: "+ messages.get(i));
		}
	}

}
