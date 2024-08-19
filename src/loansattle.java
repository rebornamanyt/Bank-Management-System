import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class loansattle extends JFrame {

	dbconnection dbc = new dbconnection();
	Integer flag=0,restamount;
	private JPanel contentPane;
	private JTextField txtAcNo;
	private JTextField txtRemark;
	private JLabel lblTxtName;
	private JLabel lblTxtAcBalance;
	private JLabel lblTxtGName;
	private JLabel lblTxtAddress;
	private JLabel lblTxtLoanId;
	private JLabel lblTxtPaidTill;
	private JLabel lblTxtPurposeOfLoan;
	private JLabel lblTxtRemainToPay;
	private JLabel lblTxtTotalAmount;
	private JLabel lblTxtInstallment;
	private JLabel lblTxtTotalInterest;
	private JLabel lblTxtInterest;
	private JLabel lblTxtTerms;
	private JLabel lblTxtLoanAmount;
	private JLabel lblTxtLoanDuration;
	private JComboBox comboStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loansattle frame = new loansattle();
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
	public loansattle() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(1364,720));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  LOAN");
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(84, 40, 1183, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblCreditHistory = new JLabel("  Credit History");
		lblCreditHistory.setOpaque(true);
		lblCreditHistory.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCreditHistory.setBackground(Color.ORANGE);
		lblCreditHistory.setBounds(84, 247, 1183, 23);
		contentPane.add(lblCreditHistory);
		
		JLabel lblNewLabel_1 = new JLabel("Account No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(141, 91, 102, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(141, 138, 102, 23);
		contentPane.add(lblName);
		
		JLabel lblAccountBalance = new JLabel("Account Balance");
		lblAccountBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountBalance.setBounds(141, 187, 131, 23);
		contentPane.add(lblAccountBalance);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(667, 187, 86, 23);
		contentPane.add(lblAddress);
		
		JLabel lblFhName = new JLabel("F/H Name");
		lblFhName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFhName.setBounds(667, 138, 86, 23);
		contentPane.add(lblFhName);
		
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
		txtAcNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAcNo.setBounds(313, 91, 163, 23);
		contentPane.add(txtAcNo);
		txtAcNo.setColumns(10);
		
		lblTxtName = new JLabel();
		lblTxtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtName.setBounds(313, 138, 262, 23);
		contentPane.add(lblTxtName);
		
		lblTxtAcBalance = new JLabel();
		lblTxtAcBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAcBalance.setBounds(313, 187, 131, 23);
		contentPane.add(lblTxtAcBalance);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGName.setBounds(798, 138, 312, 23);
		contentPane.add(lblTxtGName);
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddress.setBounds(798, 187, 377, 23);
		contentPane.add(lblTxtAddress);
		
		JLabel lblLoanDurationDate = new JLabel("Loan Duration Date");
		lblLoanDurationDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanDurationDate.setBounds(141, 300, 154, 23);
		contentPane.add(lblLoanDurationDate);
		
		JLabel lblPaidTill = new JLabel("Paid Till");
		lblPaidTill.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaidTill.setBounds(141, 349, 86, 23);
		contentPane.add(lblPaidTill);
		
		JLabel lblPurposeOfLoan = new JLabel("Purpose of Loan");
		lblPurposeOfLoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurposeOfLoan.setBounds(141, 402, 140, 23);
		contentPane.add(lblPurposeOfLoan);
		
		lblTxtLoanDuration = new JLabel();
		lblTxtLoanDuration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanDuration.setBounds(313, 300, 411, 23);
		contentPane.add(lblTxtLoanDuration);
		
		lblTxtPaidTill = new JLabel();
		lblTxtPaidTill.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtPaidTill.setBounds(313, 349, 219, 23);
		contentPane.add(lblTxtPaidTill);
		
		lblTxtPurposeOfLoan = new JLabel();
		lblTxtPurposeOfLoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtPurposeOfLoan.setBounds(313, 402, 797, 23);
		contentPane.add(lblTxtPurposeOfLoan);
		
		JLabel lblRemainToPay = new JLabel("Remain To Pay");
		lblRemainToPay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRemainToPay.setBounds(606, 349, 140, 23);
		contentPane.add(lblRemainToPay);
		
		lblTxtRemainToPay = new JLabel();
		lblTxtRemainToPay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtRemainToPay.setBounds(756, 349, 219, 23);
		contentPane.add(lblTxtRemainToPay);
		
		JLabel lblLoanAmount = new JLabel("Loan Amount");
		lblLoanAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanAmount.setBounds(188, 457, 112, 23);
		contentPane.add(lblLoanAmount);
		
		JLabel lblTermsmonths = new JLabel("Terms(months)");
		lblTermsmonths.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTermsmonths.setBounds(344, 457, 123, 23);
		contentPane.add(lblTermsmonths);
		
		JLabel lblInterestpm = new JLabel("Interest(PM)");
		lblInterestpm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInterestpm.setBounds(512, 457, 112, 23);
		contentPane.add(lblInterestpm);
		
		JLabel lblTotalInterest = new JLabel("Installment");
		lblTotalInterest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalInterest.setBounds(863, 457, 112, 23);
		contentPane.add(lblTotalInterest);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalAmount.setBounds(1026, 457, 112, 23);
		contentPane.add(lblTotalAmount);
		
		JLabel lblTotalInterset = new JLabel("Total Interset");
		lblTotalInterset.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalInterset.setBounds(686, 457, 112, 23);
		contentPane.add(lblTotalInterset);
		
		lblTxtTotalAmount = new JLabel();
		lblTxtTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalAmount.setBounds(1026, 486, 112, 23);
		contentPane.add(lblTxtTotalAmount);
		
		lblTxtInstallment = new JLabel();
		lblTxtInstallment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtInstallment.setBounds(863, 486, 112, 23);
		contentPane.add(lblTxtInstallment);
		
		lblTxtTotalInterest = new JLabel();
		lblTxtTotalInterest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalInterest.setBounds(686, 486, 112, 23);
		contentPane.add(lblTxtTotalInterest);
		
		lblTxtInterest = new JLabel();
		lblTxtInterest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtInterest.setBounds(512, 486, 112, 23);
		contentPane.add(lblTxtInterest);

		lblTxtTerms = new JLabel();
		lblTxtTerms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTerms.setBounds(344, 486, 123, 23);
		contentPane.add(lblTxtTerms);
		
		lblTxtLoanAmount = new JLabel();
		lblTxtLoanAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanAmount.setBounds(188, 486, 112, 23);
		contentPane.add(lblTxtLoanAmount);
		
		JLabel lblStatusOfLoan = new JLabel("Status of Loan");
		lblStatusOfLoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStatusOfLoan.setBounds(141, 546, 112, 23);
		contentPane.add(lblStatusOfLoan);
		
		comboStatus = new JComboBox();
		comboStatus.addItem("Good");
		comboStatus.addItem("On Timed");
		comboStatus.addItem("Satisfied");
		comboStatus.addItem("Bad");
		comboStatus.setBounds(290, 546, 131, 23);
		contentPane.add(comboStatus);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRemark.setBounds(562, 546, 80, 23);
		contentPane.add(lblRemark);
		
		txtRemark = new JTextField();
		txtRemark.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRemark.setColumns(10);
		txtRemark.setBounds(653, 546, 522, 23);
		contentPane.add(txtRemark);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(1155, 610, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnSattelLoan = new JButton("Sattel Loan");
		btnSattelLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(restamount==0){
					loansattled();
				}else{
					JOptionPane.showMessageDialog(null, "Please first pay All Amount");
				}
			}
		});
		btnSattelLoan.setBounds(1003, 610, 112, 23);
		contentPane.add(btnSattelLoan);
		
		JLabel lblLoanId = new JLabel("Loan ID");
		lblLoanId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanId.setBounds(667, 91, 86, 23);
		contentPane.add(lblLoanId);
		
		lblTxtLoanId = new JLabel();
		lblTxtLoanId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanId.setBounds(798, 91, 312, 23);
		contentPane.add(lblTxtLoanId);
	}
	private void detail(){
		String sql="SELECT * FROM sss.memberdetail where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				lblTxtName.setText(rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname"));
				lblTxtGName.setText(rs.getString("gname"));
				lblTxtAddress.setText(rs.getString("line1")+", "+rs.getString("line2")+", "+rs.getString("city")+", "+rs.getString("distric")+", "+rs.getString("state"));
				lblTxtAcBalance.setText(rs.getString("amount"));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void loandetail(){
		String sql1="SELECT * FROM sss.loantaking where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql1);
			while (rs.next()){
				restamount=rs.getInt("totalamount")-rs.getInt("totalamountpaid");
				lblTxtLoanId.setText(rs.getString("id"));
				lblTxtPurposeOfLoan.setText(rs.getString("purposeofloan"));
				lblTxtLoanAmount.setText(rs.getString("loanamount"));
				lblTxtTerms.setText(rs.getString("duration"));
				lblTxtInterest.setText(rs.getString("interest"));
				lblTxtTotalInterest.setText(rs.getString("totalinterest"));
				lblTxtInstallment.setText(rs.getString("installment"));
				lblTxtTotalAmount.setText(rs.getString("totalamount"));
				lblTxtRemainToPay.setText(restamount.toString());
				lblTxtPaidTill.setText(rs.getString("paidtill"));
				flag=rs.getInt("flag");
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
		String sql2="SELECT paidtill,DATE_FORMAT(date, '%d/%m/%y') As mydate FROM sss.loantaking where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql2);
			while (rs.next()){
				lblTxtLoanDuration.setText(rs.getString("mydate")+" to "+rs.getString("paidtill"));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void loansattled(){
		String accountno,status,remark,sql;
		accountno=txtAcNo.getText();
		status=comboStatus.getSelectedItem().toString();
		remark=txtRemark.getText();
		sql="UPDATE sss.loantaking SET status='"+status+"', remark='"+remark+"' , satelmentdate=now()  , satelmenttime=now(), flag='1' WHERE acno='"+accountno+"' ";
		if (remark.equals("") || accountno.equals("")){
			JOptionPane.showMessageDialog(null, "Please fill the required data");
		}else
		{
			try {
			Statement stmt=dbc.conmethod();
			if(flag==1){
				JOptionPane.showMessageDialog(null, "You have Already Satteled the Loan");
			}else{
				stmt.execute(sql);
				JOptionPane.showMessageDialog(null, "Loan Satteled");
				dispose();
			}
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);}
		}
	}
}
