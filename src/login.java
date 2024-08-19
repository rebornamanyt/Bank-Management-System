import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;


public class login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	dbconnection dbc = new dbconnection();
	private JTextField password;

	/*
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 17));
		username.setBounds(339, 158, 157, 24);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername.setBounds(193, 158, 96, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassword.setBounds(193, 209, 96, 20);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 17));
		password.setBounds(339, 211, 162, 23);
		contentPane.add(password);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("image/logo1.png"));
		lblNewLabel.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 34));
		lblNewLabel.setBounds(10, 26, 572, 80);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("image/log.png"));
		lblNewLabel_2.setBounds(60, 124, 112, 138);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("image/login.png"));
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logincode();
			}
		});
		lblNewLabel_1.setBounds(371, 266, 135, 45);
		contentPane.add(lblNewLabel_1);
	}
	private void logincode() {
		// TODO Auto-generated method stub
		String uname=username.getText();
		String upass=password.getText();
		String sql=("select * from login");
		if (uname.equals("") || upass.equals("")) {
			reset();
			JOptionPane.showMessageDialog(null, "Username or Password must required");
		}else{
			try {
				Statement stmt=dbc.conmethod();
				ResultSet rs=stmt.executeQuery(sql);
				int count =0;
				while(rs.next()){
					String user=rs.getString("username");
					String pass=rs.getString("password");
					String username=rs.getString("name");
					if((uname.equals(user))&&(upass.equals(pass))){
							count+=1;
							new mainpage(username).setVisible(true);
							dispose();
					}
				}
				if (count==0){
					reset();
					JOptionPane.showMessageDialog(null, "Invalid user or password");
				}
			} catch (Exception e){ JOptionPane.showMessageDialog(null, e);}
		}
	}
	public void reset(){
		username.setText("");
		password.setText("");
		username.requestFocus(true);
	}
}
