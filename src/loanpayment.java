import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class loanpayment extends JFrame {

	dbconnection dbc = new dbconnection();
	Integer id,loanacno;
	String uname;
	private JPanel contentPane;
	private JTextField txtAcNo;
	private JTextField txtAmount;
	private JLabel lblTxtName;
	private JLabel lblTxtAddress;
	private JLabel lblTxtLoanAmount;
	private JLabel lblTxtRestAmount;
	private JLabel lblTxtInstallment;
	private JLabel lblTxtGName;
	private JLabel lblTxtLoanId;
	private JLabel lblTxtLastPaymentDate;
	JLabel lblTxtPaidAmount;
	private JComboBox comboParticular;
	int currentbalance,totalamountpaid,loanrestamount=0;
	private JTextField txtCheque;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loanpayment frame = new loanpayment(null);
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
	public loanpayment(String username) {
		uname=username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 150, 708, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//auto increment id
		int count =1;
		String sql=("select acno from loanpayment");
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				count+=1;
			}
		} catch (Exception e) { JOptionPane.showMessageDialog(null, e);}
		id=10+count;
		
		JLabel lblLoanPyament = new JLabel("Loan Pyament");
		lblLoanPyament.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoanPyament.setOpaque(true);
		lblLoanPyament.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoanPyament.setBackground(Color.ORANGE);
		lblLoanPyament.setBounds(44, 33, 603, 21);
		contentPane.add(lblLoanPyament);
		
		JLabel lblAccountNo = new JLabel("Account No");
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountNo.setBounds(77, 82, 97, 21);
		contentPane.add(lblAccountNo);
		
		txtAcNo = new JTextField();
		txtAcNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
					txtAcNo.setEditable(true);
				} else {
					txtAcNo.setEditable(false);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "* Enter only numeric digits(0-9) ");
					txtAcNo.setEditable(true);
				}
				Integer acno=Integer.valueOf(txtAcNo.getText());
				if(acno>200000){
					txtAcNo.setEditable(false);
					JOptionPane.showMessageDialog(null, "Please Enter valid Account No");
					txtAcNo.setText("");
					txtAcNo.setEditable(true);
				}
				if(acno >= 100000 && acno<=199999){
					detail();
					loandetail();
				}
				
				String Accountno=txtAcNo.getText(),status="";
				String sql=("SELECT * FROM memberdetail WHERE acno='"+Accountno+"' "),sql1=("SELECT * FROM loantaking");
				int count=0,count1=0;
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
						status=rs.getString("status");
						if(txtAcNo.getText().equals(rs.getString("acno"))){
							count=1;
						}
					}
					if (count==0 && acno >= 100000 && acno<=199999){
						txtAcNo.setEditable(false);
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtAcNo.setEditable(true);
						txtAcNo.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql1);
					while(rs.next()){
						if(txtAcNo.getText().equals(rs.getString("acno"))){
							count1=1;
						}
					}
					if (count==1 && count1==0 &&  acno >= 100000 && acno<=199999){
						txtAcNo.setEditable(false);
						JOptionPane.showMessageDialog(null, "You havn't take any Loan Yet, Enter Valid A/C No.");
						txtAcNo.setText("");
						txtAcNo.setEditable(true);
						txtAcNo.requestFocus(true);
					}else{
						if(status.equals("Deactivate")){
							JOptionPane.showMessageDialog(null, "A/C Already Deactivated");
							txtAcNo.setText("");
						}
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtAcNo.setBounds(196, 84, 136, 20);
		contentPane.add(txtAcNo);
		txtAcNo.setColumns(10);
		
		JLabel lblLoanId = new JLabel("Loan ID");
		lblLoanId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanId.setBounds(371, 82, 76, 21);
		contentPane.add(lblLoanId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(77, 129, 67, 21);
		contentPane.add(lblName);
		
		JLabel lblShName = new JLabel("S/H Name");
		lblShName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblShName.setBounds(371, 129, 88, 21);
		contentPane.add(lblShName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(77, 172, 76, 21);
		contentPane.add(lblAddress);
		
		JLabel lblInstallment = new JLabel("Installment");
		lblInstallment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInstallment.setBounds(371, 172, 97, 21);
		contentPane.add(lblInstallment);
		
		JLabel lblLoanAmount = new JLabel("Loan Amount");
		lblLoanAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanAmount.setBounds(77, 217, 97, 21);
		contentPane.add(lblLoanAmount);
		
		JLabel lblWithInterest = new JLabel("(with Interest)");
		lblWithInterest.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWithInterest.setBounds(86, 232, 88, 21);
		contentPane.add(lblWithInterest);
		
		JLabel lblRestAmount = new JLabel("Rest Amount");
		lblRestAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRestAmount.setBounds(371, 264, 97, 21);
		contentPane.add(lblRestAmount);
		
		JLabel lblLastPaymentDate = new JLabel("Last Payment Date");
		lblLastPaymentDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastPaymentDate.setBounds(77, 264, 140, 21);
		contentPane.add(lblLastPaymentDate);
		
		lblTxtName = new JLabel();
		lblTxtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtName.setBounds(196, 129, 152, 21);
		contentPane.add(lblTxtName);
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddress.setBounds(196, 172, 152, 21);
		contentPane.add(lblTxtAddress);
		
		lblTxtLoanAmount = new JLabel();
		lblTxtLoanAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanAmount.setBounds(196, 217, 152, 21);
		contentPane.add(lblTxtLoanAmount);
		
		lblTxtRestAmount = new JLabel();
		lblTxtRestAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtRestAmount.setBounds(493, 264, 162, 21);
		contentPane.add(lblTxtRestAmount);
		
		lblTxtInstallment = new JLabel();
		lblTxtInstallment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtInstallment.setBounds(493, 172, 162, 21);
		contentPane.add(lblTxtInstallment);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGName.setBounds(493, 129, 162, 21);
		contentPane.add(lblTxtGName);
		
		lblTxtLoanId = new JLabel();
		lblTxtLoanId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanId.setBounds(493, 82, 162, 21);
		contentPane.add(lblTxtLoanId);
		
		lblTxtLastPaymentDate = new JLabel();
		lblTxtLastPaymentDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLastPaymentDate.setBounds(247, 264, 112, 21);
		contentPane.add(lblTxtLastPaymentDate);
		
		JLabel lblParticulars = new JLabel("Particulars");
		lblParticulars.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblParticulars.setBounds(101, 308, 97, 21);
		contentPane.add(lblParticulars);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmount.setBounds(378, 308, 76, 21);
		contentPane.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(378, 340, 112, 20);
		contentPane.add(txtAmount);
		
		comboParticular = new JComboBox();
		comboParticular.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboParticular.getSelectedItem().equals("By Cheque")){
					txtCheque.setEditable(true);
				}
			}
		});
		comboParticular.addItem("By Cash");
		comboParticular.addItem("By Cheque");
		comboParticular.setBounds(101, 340, 88, 20);
		contentPane.add(comboParticular);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loanpaidamount();
			}
		});
		btnSubmit.setBounds(532, 339, 89, 23);
		contentPane.add(btnSubmit);
		
		txtCheque = new JTextField();
		txtCheque.setEditable(false);
		txtCheque.setColumns(10);
		txtCheque.setBounds(229, 340, 103, 20);
		contentPane.add(txtCheque);
		
		JLabel lblChequeNo = new JLabel("Cheque No.");
		lblChequeNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChequeNo.setBounds(229, 308, 103, 21);
		contentPane.add(lblChequeNo);
		
		JLabel lblPaidAmount = new JLabel("Paid Amount");
		lblPaidAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaidAmount.setBounds(371, 217, 97, 21);
		contentPane.add(lblPaidAmount);
		
		lblTxtPaidAmount = new JLabel();
		lblTxtPaidAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtPaidAmount.setBounds(493, 217, 162, 21);
		contentPane.add(lblTxtPaidAmount);
	}

	private void detail(){
		String sql="SELECT * FROM sss.memberdetail where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				lblTxtName.setText(rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname"));
				lblTxtGName.setText(rs.getString("gname"));
				lblTxtAddress.setText(rs.getString("line1")+", "+rs.getString("city"));
				currentbalance=Integer.parseInt(rs.getString("amount"));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void loandetail(){
		String sql="SELECT * FROM sss.loantaking where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				Integer totalamount=0,paidamount=0;
				totalamountpaid=rs.getInt("totalamountpaid");
				lblTxtLoanId.setText(rs.getString("id"));
				lblTxtLoanAmount.setText(rs.getString("totalamount"));
				lblTxtInstallment.setText(rs.getString("installment"));
				lblTxtLoanId.setText(rs.getString("id"));
				lblTxtLastPaymentDate.setText(rs.getString("paidtill"));
				loanacno=rs.getInt("id");
				totalamount=rs.getInt("totalamount");
				paidamount=rs.getInt("totalamountpaid");
				loanrestamount=totalamount-paidamount;
				lblTxtPaidAmount.setText(String.valueOf(paidamount));
				lblTxtRestAmount.setText(String.valueOf(loanrestamount));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}

	private void loanpaidamount(){
		String accountno,particular,cheque,sql1,sql2;
		int amount=0;
		accountno=txtAcNo.getText();
		particular=comboParticular.getSelectedItem().toString();
		cheque=txtCheque.getText();
		amount=Integer.parseInt(txtAmount.getText());
		totalamountpaid=totalamountpaid+amount;
		sql1="UPDATE sss.loantaking SET totalamountpaid='"+totalamountpaid+"' WHERE acno='"+accountno+"' ";
		sql2="INSERT INTO sss.loanpayment(id,acno,loanacno,particular,cheque,amount,date,time,amountpaid,doneby) values('"+id+"','"+accountno+"','"+loanacno+"','"+particular+"','"+cheque+"','"+amount+"',now(),now(),'"+totalamountpaid+"','"+uname+"')";
		if (txtAmount.equals("") && accountno.equals("")){
			JOptionPane.showMessageDialog(null, "Invalid Data");
		}else
		{
			try {
			Statement stmt=dbc.conmethod();
			if(Integer.valueOf(txtAmount.getText())<= loanrestamount){
				stmt.execute(sql1);
				stmt.execute(sql2);
				JOptionPane.showMessageDialog(null, "Amount paid");
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Amount Exceed to Loan Remaining Amount");
				txtAmount.setText(String.valueOf(loanrestamount));
			}
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
		}
	}
}
