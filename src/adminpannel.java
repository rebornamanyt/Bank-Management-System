import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;


public class adminpannel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminpannel frame = new adminpannel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminpannel() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 548, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Pannel");
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 32, 542, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton(new ImageIcon("image/newuser.png"));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new signup().setVisible(true);
			}
		});
		btnNewButton.setBounds(31, 106, 130, 130);
		contentPane.add(btnNewButton);
		
		JButton btnU = new JButton(new ImageIcon("image/userinfo.png"));
		btnU.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new userdetail().setVisible(true);
			}
		});
		btnU.setBounds(201, 106, 130, 130);
		contentPane.add(btnU);
		
		JButton btnUserDelete = new JButton(new ImageIcon("image/userdelete.png"));
		btnUserDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUserDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new userdelete().setVisible(true);
			}
		});
		btnUserDelete.setBounds(369, 106, 130, 130);
		contentPane.add(btnUserDelete);
		
		JLabel lblNewLabel_1 = new JLabel("New User");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(63, 238, 63, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblDetailUpdate = new JLabel("Detail / Update User");
		lblDetailUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDetailUpdate.setBounds(201, 238, 130, 17);
		contentPane.add(lblDetailUpdate);
		
		JLabel lblDeleteUser = new JLabel("Delete User");
		lblDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeleteUser.setBounds(403, 238, 77, 17);
		contentPane.add(lblDeleteUser);
	}
}
