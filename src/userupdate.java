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


public class userupdate extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtAdress;
	private JTextField txtAdminPassword;
	private JComboBox comboType;
	private JTextField txtMobileNo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userupdate frame = new userupdate();
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
	public userupdate() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignUpForm = new JLabel("User Update Form");
		lblSignUpForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUpForm.setBackground(Color.ORANGE);
		lblSignUpForm.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSignUpForm.setOpaque(true);
		lblSignUpForm.setBounds(34, 36, 486, 27);
		contentPane.add(lblSignUpForm);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(112, 135, 54, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(112, 94, 75, 19);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(112, 177, 75, 19);
		contentPane.add(lblPassword);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(112, 219, 85, 19);
		contentPane.add(lblAddress);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblType.setBounds(112, 293, 54, 19);
		contentPane.add(lblType);
		
		JLabel lblAdminPassword = new JLabel("Admin Password");
		lblAdminPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdminPassword.setBounds(112, 329, 123, 19);
		contentPane.add(lblAdminPassword);
		
		txtName = new JTextField();
		txtName.setBounds(279, 135, 164, 20);
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
		txtUserName.requestFocus(true);		
		txtUserName.setBounds(279, 95, 164, 20);
		contentPane.add(txtUserName);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(279, 178, 164, 20);
		contentPane.add(txtPassword);
		
		txtAdress = new JTextField();
		txtAdress.setColumns(10);
		txtAdress.setBounds(279, 220, 164, 20);
		contentPane.add(txtAdress);
		
		txtAdminPassword = new JPasswordField();
		txtAdminPassword.setColumns(10);
		txtAdminPassword.setBounds(279, 330, 164, 20);
		contentPane.add(txtAdminPassword);
		
		comboType = new JComboBox();
		comboType.setBounds(279, 294, 164, 20);
		comboType.addItem("Employee");
		comboType.addItem("Admin");
		contentPane.add(comboType);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(394, 382, 85, 23);
		contentPane.add(btnCancel);
		
		JButton btnSignup = new JButton("Update");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname=txtUserName.getText();
				String apass=txtAdminPassword.getText();
				String sql=("select * from login");
				int count=0;
				String adminpassword="",type="";
				
				try {
				Statement stmt=dbc.conmethod();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					String username=rs.getString("username");
					adminpassword=rs.getString("password");
					type=rs.getString("type");
					if((username.equals(uname))){
						count=1;
					}
				}
				if(count==1){
					userupdate1();
				}else if((adminpassword.equals(apass))&&(type.equals("Admin"))){
					userupdate1();
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid Username or Admin Password");
				}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		btnSignup.setBounds(299, 382, 85, 23);
		contentPane.add(btnSignup);
		
		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMobileNo.setBounds(113, 255, 85, 19);
		contentPane.add(lblMobileNo);
		
		txtMobileNo = new JTextField();
		txtMobileNo.setColumns(10);
		txtMobileNo.setBounds(280, 256, 164, 20);
		contentPane.add(txtMobileNo);
	}
	private void userdetail(){
		String sql="SELECT * FROM sss.login where username='"+ txtUserName.getText() +"'";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				txtName.setText(rs.getString("name"));
				txtAdress.setText(rs.getString("address"));
				txtPassword.setText(rs.getString("password"));
				txtMobileNo.setText(rs.getString("mobileno"));
				if(rs.getString("type").equals("Employee")){comboType.setSelectedIndex(0);}
				else if(rs.getString("type").equals("Admin")){comboType.setSelectedIndex(1);}
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}

	private void userupdate1(){
		String name,username,password,type,address,mobileno,sql;
		name=txtName.getText();
		username=txtUserName.getText();
		password=txtPassword.getText();
		type=comboType.getSelectedItem().toString();
		address=txtAdress.getText();
		mobileno=txtMobileNo.getText();
		sql="UPDATE sss.login SET name='"+name+"',password='"+password+"',type='"+type+"',address='"+address+"',mobileno='"+mobileno+"' WHERE username='"+username+"' ";
		if (name.equals("") || username.equals("") || password.equals("") || address.equals("") || mobileno.equals("")){
			JOptionPane.showMessageDialog(null, "Please fill the all field");
		}else
		{
			try {
			Statement stmt=dbc.conmethod();
			stmt.execute(sql);
			JOptionPane.showMessageDialog(null, "Data Entered");
			dispose();
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
		}
	}

}
