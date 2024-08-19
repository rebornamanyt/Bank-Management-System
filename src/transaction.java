import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class transaction extends JFrame {

	dbconnection dbc = new dbconnection();
	String uname;
	private JPanel contentPane;
	private JTextField txtAcno;
	private JTextField txtAmount;
	private JTextField txtChequeNo;
	private JLabel lblTxtName;
	private JLabel lblTxtAddress;
	private JLabel lblTxtDate;
	private JLabel lblTxtGName;
	private JLabel lblTxtAcBalance;
	private JComboBox comboParticular;
	private Integer currentbalance;
	
	java.util.Date datet= Calendar.getInstance().getTime();
    DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
	String today = format.format(datet);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transaction frame = new transaction(null);
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
	public transaction(String username) {
		uname=username;
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transaction");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 26, 628, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A/C No.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(63, 82, 70, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(63, 125, 70, 22);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(63, 174, 70, 22);
		contentPane.add(lblAddress);
		
		JLabel lblAccountBalance = new JLabel("Account Balance");
		lblAccountBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountBalance.setBounds(338, 174, 122, 22);
		contentPane.add(lblAccountBalance);
		
		JLabel lblSho = new JLabel("S/H/O");
		lblSho.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSho.setBounds(338, 125, 70, 22);
		contentPane.add(lblSho);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(338, 82, 70, 22);
		contentPane.add(lblDate);
		
		txtAcno = new JTextField();
		txtAcno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
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
					txtAcno.setText("");
					txtAcno.setEditable(true);
					txtAcno.requestFocus(true);
				}
				if(status.equals("Deactivate")){
					JOptionPane.showMessageDialog(null, "A/C Deactivated, please Enter Active A/c.");
					txtAcno.setText("");
				}
			} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtAcno.setBounds(143, 85, 148, 20);
		contentPane.add(txtAcno);
		txtAcno.setColumns(10);
		
		lblTxtName = new JLabel();
		lblTxtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtName.setBounds(143, 125, 148, 22);
		contentPane.add(lblTxtName);
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddress.setBounds(143, 174, 148, 22);
		contentPane.add(lblTxtAddress);
		
		lblTxtDate = new JLabel(today);
		lblTxtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtDate.setBounds(418, 82, 148, 22);
		contentPane.add(lblTxtDate);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGName.setBounds(418, 125, 163, 22);
		contentPane.add(lblTxtGName);
		
		lblTxtAcBalance = new JLabel();
		lblTxtAcBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAcBalance.setBounds(470, 174, 122, 22);
		contentPane.add(lblTxtAcBalance);
		
		JLabel lblParticulars = new JLabel("Particulars");
		lblParticulars.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblParticulars.setBounds(63, 232, 91, 22);
		contentPane.add(lblParticulars);
		
		JLabel lblChequeNo = new JLabel("Cheque No.");
		lblChequeNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChequeNo.setBounds(198, 232, 91, 22);
		contentPane.add(lblChequeNo);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmount.setBounds(338, 232, 91, 22);
		contentPane.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(338, 265, 107, 20);
		contentPane.add(txtAmount);
		
		txtChequeNo = new JTextField();
		txtChequeNo.setEditable(false);
		txtChequeNo.setColumns(10);
		txtChequeNo.setBounds(198, 265, 107, 20);
		contentPane.add(txtChequeNo);
		
		comboParticular = new JComboBox();
		comboParticular.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboParticular.getSelectedItem().equals("By Cheque")){
					txtChequeNo.setEditable(true);
				}
				else{
					txtChequeNo.setEditable(false);
				}
			}
		});
		comboParticular.addItem("By Cash");
		comboParticular.addItem("By Cheque");
		comboParticular.addItem("To Cash");
		comboParticular.setBounds(63, 265, 107, 20);
		contentPane.add(comboParticular);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transaction1();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(477, 258, 89, 30);
		contentPane.add(btnNewButton);
	}
	private void detail(){
		String sql="SELECT * FROM sss.memberdetail where acno="+ txtAcno.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				currentbalance=rs.getInt("amount");
				lblTxtName.setText(rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname"));
				lblTxtGName.setText(rs.getString("gname"));
				lblTxtAddress.setText(rs.getString("line1")+", "+rs.getString("line2")+", "+rs.getString("city"));
				lblTxtAcBalance.setText(rs.getString("amount"));
				}
			}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void transaction1(){
		String accountno,particular,cheque,sql1,sql2;
		Integer totalamount=0,amount=0;
		accountno=txtAcno.getText();
		particular=comboParticular.getSelectedItem().toString();
		cheque=txtChequeNo.getText();
		amount=Integer.parseInt(txtAmount.getText());
		if (particular.equals("By Cash")|| particular.equals("By Cheque")){
			totalamount=currentbalance+amount;
		} else if(particular.equals("To Cash")){
			totalamount=currentbalance-amount;
		}
		
		sql1="INSERT INTO sss.transaction(acno,particular,chequeno,amount,date,time,curentbalance,doneby) values('"+accountno+"','"+particular+"','"+cheque+"','"+amount+"',now(),now(),'"+totalamount+"','"+uname+"')";
		sql2="UPDATE sss.memberdetail SET amount='"+totalamount+"' WHERE acno='"+accountno+"' ";
		if (txtAmount.getText().equals("") || accountno.equals("") || (comboParticular.getSelectedItem().equals("To Cheque")&&txtChequeNo.getText()=="")){
			JOptionPane.showMessageDialog(null, "Please Fill the all field");
		}else{
			try {
			Statement stmt=dbc.conmethod();
			if(currentbalance<amount&&comboParticular.getSelectedItem().equals("To Cash")){
				JOptionPane.showMessageDialog(null, "Enter Amount is more than Account Balance");
				txtAmount.setText(String.valueOf(currentbalance));
			}
			else{
				stmt.execute(sql1);
				stmt.execute(sql2);
				JOptionPane.showMessageDialog(null, "Transaction Successsfull");
				dispose();
			}
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
		}
	}
}
