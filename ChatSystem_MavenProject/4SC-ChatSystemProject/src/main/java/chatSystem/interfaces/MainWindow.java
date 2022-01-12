package chatSystem.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final JSeparator separator = new JSeparator();
	private JTextField textField;
	private JPasswordField ChatSpace;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MainWindow() {
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
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblUserList = new JLabel("Connected User List");
		scrollPane.setColumnHeaderView(lblUserList);
		
		JButton btnChangePseudo = new JButton("Change Pseudo");
		btnChangePseudo.setBounds(26, 494, 154, 25);
		contentPane.add(btnChangePseudo);
		
		JButton btnChangePseudo_1 = new JButton("Disconnect");
		btnChangePseudo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnChangePseudo_1.setBounds(26, 531, 154, 25);
		contentPane.add(btnChangePseudo_1);
		
		textField = new JTextField();
		textField.setBounds(226, 391, 441, 64);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSend.setBounds(494, 467, 117, 25);
		contentPane.add(btnSend);
		
		ChatSpace = new JPasswordField();
		ChatSpace.setBounds(238, 46, 417, 308);
		contentPane.add(ChatSpace);
		
		JButton btnStart = new JButton("Start Session");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStart.setBounds(26, 457, 154, 25);
		contentPane.add(btnStart);
		
		JButton btnFile = new JButton("File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFile.setBounds(568, 517, 86, 25);
		contentPane.add(btnFile);
		
		JButton btnQuit = new JButton("Quit"
				+ "");
		btnQuit.setBounds(449, 517, 86, 25);
		contentPane.add(btnQuit);
		
		JLabel lblMyPseudo = new JLabel("My Pseudo ");
		lblMyPseudo.setBounds(26, 3, 111, 15);
		contentPane.add(lblMyPseudo);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(638, 163, 17, 61);
		contentPane.add(scrollBar);
	}
}
