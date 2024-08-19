import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class deactivatemember extends JFrame {

	dbconnection dbc = new dbconnection();
	Integer flag=0;
	private JPanel contentPane;
	private JTextField txtAcno;
	private TextArea txtArea;
	private JLabel lblTxtAddress;
	private JLabel lblTxtName;
	private JLabel lblTxtOpenDate;
	private JLabel lblTxtAcBalance;
	private JLabel lblTxtLoanStatus;
	private JLabel lblTxtGName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deactivatemember frame = new deactivatemember();
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
	public deactivatemember() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Deactivate A/C");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 33, 692, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A/C No. :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(64, 97, 77, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(64, 140, 77, 22);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(64, 185, 77, 22);
		contentPane.add(lblAddress);
		
		JLabel lblDeactivationReasion = new JLabel("Deactivation Reasion :");
		lblDeactivationReasion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDeactivationReasion.setBounds(64, 229, 181, 22);
		contentPane.add(lblDeactivationReasion);
		
		JLabel lblLoanStatus = new JLabel("Loan Status");
		lblLoanStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanStatus.setBounds(372, 229, 118, 22);
		contentPane.add(lblLoanStatus);
		
		JLabel lblAccountBalance = new JLabel("Account Balance");
		lblAccountBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountBalance.setBounds(372, 185, 138, 22);
		contentPane.add(lblAccountBalance);
		
		JLabel lblSho = new JLabel("S/H/O :");
		lblSho.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSho.setBounds(372, 140, 77, 22);
		contentPane.add(lblSho);
		
		JLabel lblAcOpenedDate = new JLabel("A/C Opened Date :");
		lblAcOpenedDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAcOpenedDate.setBounds(372, 97, 138, 22);
		contentPane.add(lblAcOpenedDate);
		
		txtAcno = new JTextField();
		txtAcno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
					txtAcno.setEditable(true);
				} else {
					txtAcno.setEditable(false);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "* Enter only numeric digits(0-9) ");
					txtAcno.setEditable(true);
				}
				Integer acno=Integer.valueOf(txtAcno.getText());
				if(acno>200000){
					txtAcno.setEditable(false);
					JOptionPane.showMessageDialog(null, "Please Enter valid Account No");
					txtAcno.setText("");
					txtAcno.setEditable(true);
				}
				if(acno >= 100000 && acno<=199999){
					detail();
					status();
				}
				
				String Accountno=txtAcno.getText(),status="";
				String sql=("SELECT * FROM memberdetail WHERE acno='"+Accountno+"' ");
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					int count =0;
					while(rs.next()){
						status=rs.getString("status");
						if(txtAcno.getText().equals(rs.getString("acno"))){
							count=1;
						}
					}
					if (count==0 && acno >= 100000 && acno<=199999){
						txtAcno.setEditable(false);
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtAcno.setEditable(true);
						txtAcno.requestFocus(true);
					}
					if(status.equals("Deactivate")){
						JOptionPane.showMessageDialog(null, "A/C Already Deactivated");
						txtAcno.setText("");
						clearall();
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtAcno.setBounds(147, 97, 187, 20);
		contentPane.add(txtAcno);
		txtAcno.setColumns(10);
		
		lblTxtName = new JLabel();
		lblTxtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtName.setBounds(151, 142, 183, 22);
		contentPane.add(lblTxtName);
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddress.setBounds(151, 185, 222, 22);
		contentPane.add(lblTxtAddress);
		
		lblTxtOpenDate = new JLabel();
		lblTxtOpenDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtOpenDate.setBounds(520, 97, 123, 22);
		contentPane.add(lblTxtOpenDate);
		
		lblTxtAcBalance = new JLabel();
		lblTxtAcBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAcBalance.setBounds(520, 185, 123, 22);
		contentPane.add(lblTxtAcBalance);
		
		lblTxtLoanStatus = new JLabel();
		lblTxtLoanStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanStatus.setBounds(484, 229, 181, 22);
		contentPane.add(lblTxtLoanStatus);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGName.setBounds(460, 140, 183, 22);
		contentPane.add(lblTxtGName);
		
		txtArea = new TextArea();
		txtArea.setBounds(74, 257, 260, 81);
		contentPane.add(txtArea);
		
		JButton btnNewButton = new JButton("Deactivate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deactivate();
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(503, 295, 123, 32);
		contentPane.add(btnNewButton);
	}
	private void detail(){
		String sql="SELECT * FROM sss.memberdetail where acno="+ txtAcno.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				lblTxtName.setText(rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname"));
				lblTxtOpenDate.setText(rs.getString("openeddate"));
				lblTxtGName.setText(rs.getString("gname"));
				lblTxtOpenDate.setText(rs.getString("openeddate"));
				lblTxtAddress.setText(rs.getString("line1")+", "+rs.getString("line2")+", "+rs.getString("city"));
				lblTxtAcBalance.setText(rs.getString("amount"));
				}
			}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void status(){
		String status="",sql=("select * from loantaking where acno="+ txtAcno.getText() +"");
		int count=0;
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				flag=rs.getInt("flag");
				status=rs.getString("status");
				if(txtAcno.getText().equals(rs.getString("acno"))){
					count=1;
				}
			}
			if (count==1){
				if(flag==1){
					lblTxtLoanStatus.setText(status);
				}
				if(flag==0){
					lblTxtLoanStatus.setText("You have take Loan");
				}
			}else{
				lblTxtLoanStatus.setText("haven't take Loan");
			}
		} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
	}
	private void deactivate(){
		String Accountno,reason,sql;
		Accountno=txtAcno.getText();
		reason =txtArea.getText();
		sql="UPDATE sss.memberdetail SET  deactivatedreason='"+reason+"', deactivateddate=now(), status='"+"Deactivate"+"' WHERE acno='"+Accountno+"'";
		if (txtArea.getText().equals("") || reason.equals("")){
			JOptionPane.showMessageDialog(null, "Fill Required Data");
		}else
		{
			try {
			Statement stmt=dbc.conmethod();
			if(flag==0){
				JOptionPane.showMessageDialog(null, "You haven't paid the Loan, please paid first");
			}else{
				stmt.execute(sql);
				JOptionPane.showMessageDialog(null, "Account is Deactivated");
				// clearall();
				dispose();
			}
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
		}
	}
	public void clearall() {
		lblTxtName.setText("");
		lblTxtOpenDate.setText("");
		lblTxtGName.setText("");
		lblTxtOpenDate.setText("");
		lblTxtAddress.setText("");
		lblTxtAcBalance.setText("");
	}
}
