import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class signup extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtRePassword;
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
					signup frame = new signup();
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
	public signup() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignUpForm = new JLabel("Sign Up Form");
		lblSignUpForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUpForm.setBackground(Color.ORANGE);
		lblSignUpForm.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSignUpForm.setOpaque(true);
		lblSignUpForm.setBounds(34, 36, 486, 27);
		contentPane.add(lblSignUpForm);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(112, 102, 54, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(112, 139, 75, 19);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(112, 177, 75, 19);
		contentPane.add(lblPassword);
		
		JLabel lblRepassword = new JLabel("Re-Password");
		lblRepassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRepassword.setBounds(112, 219, 99, 19);
		contentPane.add(lblRepassword);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(112, 256, 85, 19);
		contentPane.add(lblAddress);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblType.setBounds(112, 331, 54, 19);
		contentPane.add(lblType);
		
		JLabel lblAdminPassword = new JLabel("Admin Password");
		lblAdminPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdminPassword.setBounds(112, 367, 123, 19);
		contentPane.add(lblAdminPassword);
		
		txtName = new JTextField();
		txtName.setBounds(279, 102, 164, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(279, 140, 164, 20);
		contentPane.add(txtUserName);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(279, 178, 164, 20);
		contentPane.add(txtPassword);
		
		txtRePassword = new JPasswordField();
		txtRePassword.setColumns(10);
		txtRePassword.setBounds(279, 220, 164, 20);
		contentPane.add(txtRePassword);
		
		txtAdress = new JTextField();
		txtAdress.setColumns(10);
		txtAdress.setBounds(279, 257, 164, 20);
		contentPane.add(txtAdress);
		
		txtAdminPassword = new JPasswordField();
		txtAdminPassword.setColumns(10);
		txtAdminPassword.setBounds(279, 368, 164, 20);
		contentPane.add(txtAdminPassword);
		
		comboType = new JComboBox();
		comboType.setBounds(279, 332, 164, 20);
		comboType.addItem("Employee");
		comboType.addItem("Admin");
		contentPane.add(comboType);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(394, 420, 85, 23);
		contentPane.add(btnCancel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setBounds(299, 420, 85, 23);
		contentPane.add(btnReset);
		
		JButton btnSignup = new JButton("SignUp");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname=txtUserName.getText();
				String pass=txtPassword.getText();
				String repass=txtRePassword.getText();
				String apass=txtAdminPassword.getText();
				String sql=("select * from login");
				if (pass.equals(repass)) {
					try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					int count =0;
					while(rs.next()){
						String username=rs.getString("username");
						String adminpassword=rs.getString("password");
						String type=rs.getString("type");
						if((adminpassword.equals(apass))&&(type.equals("Admin"))){
								signup1();
								break;
						}
						else if(uname.equals(username)){
							count=1;
						}else{
							JOptionPane.showMessageDialog(null, "Invalid Admin Password");
							break;
						}
					}
					if (count==1){
						JOptionPane.showMessageDialog(null, "This UserName is Already Taken");
						txtUserName.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
				}else{
					JOptionPane.showMessageDialog(null, "Re-Password is not Matched");
					txtRePassword.requestFocus(true);
				}
			}
		});
		btnSignup.setBounds(204, 420, 85, 23);
		contentPane.add(btnSignup);
		
		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMobileNo.setBounds(112, 294, 85, 19);
		contentPane.add(lblMobileNo);
		
		txtMobileNo = new JTextField();
		txtMobileNo.setColumns(10);
		txtMobileNo.setBounds(279, 295, 164, 20);
		contentPane.add(txtMobileNo);
	}
	private void signup1(){
		String name,username,password,type,address,mobileno,sql;
		name=txtName.getText();
		username=txtUserName.getText();
		password=txtPassword.getText();
		type=comboType.getSelectedItem().toString();
		address=txtAdress.getText();
		mobileno=txtMobileNo.getText();
		sql="INSERT INTO sss.login(name,username,password,type,address,mobileno,date) values('"+name+"','"+username+"','"+password+"','"+type+"','"+address+"','"+mobileno+"',now())";
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
	private void reset(){
		txtName.setText("");
		txtUserName.setText("");
		txtPassword.setText("");
		txtRePassword.setText("");
		txtAdress.setText("");
		txtAdminPassword.setText("");
		txtMobileNo.setText("");
		comboType.setSelectedIndex(0);
		txtName.requestFocus(true);
	}
}
