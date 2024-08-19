import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class userdelete extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUserName;
	private JTextField txtAdress;
	private JTextField txtAdminPassword;
	private JComboBox comboType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userdelete frame = new userdelete();
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
	public userdelete() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignUpForm = new JLabel("User Delete Form");
		lblSignUpForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUpForm.setBackground(Color.ORANGE);
		lblSignUpForm.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSignUpForm.setOpaque(true);
		lblSignUpForm.setBounds(34, 36, 486, 27);
		contentPane.add(lblSignUpForm);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(112, 141, 54, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(112, 100, 75, 19);
		contentPane.add(lblUsername);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(112, 183, 85, 19);
		contentPane.add(lblAddress);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblType.setBounds(112, 221, 54, 19);
		contentPane.add(lblType);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(279, 141, 164, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUserName = new JTextField();
		txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				userdetail();
			}
		});
		txtUserName.setColumns(10);
		txtUserName.setBounds(279, 101, 164, 20);
		contentPane.add(txtUserName);
		
		txtAdress = new JTextField();
		txtAdress.setEditable(false);
		txtAdress.setColumns(10);
		txtAdress.setBounds(279, 184, 164, 20);
		contentPane.add(txtAdress);
		
		comboType = new JComboBox();
		comboType.setBounds(279, 222, 164, 20);
		comboType.addItem("Employee");
		comboType.addItem("Admin");
		contentPane.add(comboType);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(405, 308, 85, 23);
		contentPane.add(btnCancel);
		
		JButton btnSignup = new JButton("Delete");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String apass=txtAdminPassword.getText();
				String sql=("select * from login");
				
				try {
				Statement stmt=dbc.conmethod();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					String adminpassword=rs.getString("password");
					String type=rs.getString("type");
					if((adminpassword.equals(apass))&&(type.equals("Admin"))){
							userremove();
							break;
					}else{
						JOptionPane.showMessageDialog(null, "Invalid Admin Password");
						break;
					}
				}
			} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		btnSignup.setBounds(310, 308, 85, 23);
		contentPane.add(btnSignup);
		
		JLabel lblAdminPassword = new JLabel("Admin Password");
		lblAdminPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdminPassword.setBounds(112, 263, 123, 19);
		contentPane.add(lblAdminPassword);
		
		txtAdminPassword = new JPasswordField();
		txtAdminPassword.setColumns(10);
		txtAdminPassword.setBounds(279, 264, 164, 20);
		contentPane.add(txtAdminPassword);
	}

	private void userdetail(){
		String sql="SELECT * FROM sss.login where username='"+ txtUserName.getText() +"'";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				txtName.setText(rs.getString("name"));
				txtAdress.setText(rs.getString("address"));
				if(rs.getString("type").equals("Employee")){comboType.setSelectedIndex(0);}
				else if(rs.getString("type").equals("Admin")){comboType.setSelectedIndex(1);}
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void userremove(){
		String username,sql;
		username=txtUserName.getText();
		sql="DELETE FROM sss.login WHERE username='"+username+"' ";
		try {
			Statement stmt=dbc.conmethod();
			stmt.execute(sql);
			JOptionPane.showMessageDialog(null, "Account Deleted");
			dispose();
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
	}
}
