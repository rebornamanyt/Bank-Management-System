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
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class loantaking extends JFrame {

	dbconnection dbc = new dbconnection();
	Integer loanacno;
	String uname;
	private JPanel contentPane;
	private JTextField txtAcNo;
	private JTextField txtPurpose;
	private JTextField txtGranterAc;
	private JTextField txtIncomeType;
	private JTextField txtTotalIncome;
	private JTextField txtLoanAmount;
	private JLabel lblTxtName;
	private JLabel lblTxtAcBalance;
	private JLabel lblTxtDOB;
	private JLabel lblTxtGender;
	private JLabel lblTxtAddress;
	private JLabel lblTxtGName;
	private JLabel lblTxtLoanStatus;
	private JLabel lblTxtInterest;
	private JLabel lblTxtTotalInterest;
	private JLabel lblTxtInstallment;
	private JLabel lblTxtTotalAmount;
	private JLabel lblTxtLastPaymentDate;
	private JComboBox comboTerms;
	
	java.util.Date datet= Calendar.getInstance().getTime();
    DateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
    DateFormat formattertime= new SimpleDateFormat("hh:mm:ssa");
    DateFormat formatteryear= new SimpleDateFormat("yyyy");
    DateFormat formattermonth= new SimpleDateFormat("MM");
    DateFormat formatterday= new SimpleDateFormat("dd");
	String today = formatter.format(datet);
	String time = formattertime.format(datet);
	String year = formatteryear.format(datet);
	String month = formattermonth.format(datet);
	String day = formatterday.format(datet);
	String d=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loantaking frame = new loantaking(null);
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
	public loantaking(String username) {
		uname=username;
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(1364,720));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//auto increment loan account no.
		int count =1;
		String sql=("select acno from loantaking");
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				count+=1;
			}
		} catch (Exception e) { JOptionPane.showMessageDialog(null, e);}
		loanacno=100+count;
		
		JLabel lblLo = new JLabel("   LOAN");
		lblLo.setBackground(Color.ORANGE);
		lblLo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLo.setOpaque(true);
		lblLo.setBounds(83, 41, 1195, 27);
		contentPane.add(lblLo);
		
		JLabel lblAccountNo = new JLabel("Account No.");
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountNo.setBounds(164, 102, 108, 20);
		contentPane.add(lblAccountNo);
		
		txtAcNo = new JTextField();
		txtAcNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
					status();
				}
				
				String sql=("SELECT * FROM memberdetail");
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					int count =0;
					while(rs.next()){
						if(txtAcNo.getText().equals(rs.getString("acno"))){
							count=1;
						}
					}
					if (count==0 && acno >= 100000 && acno<=199999){
						txtAcNo.setEditable(false);
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtAcNo.setText("");
						txtAcNo.setEditable(true);
						txtAcNo.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtAcNo.setBounds(341, 102, 219, 22);
		contentPane.add(txtAcNo);
		txtAcNo.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(164, 148, 80, 20);
		contentPane.add(lblName);
		
		JLabel lblAccountBalance = new JLabel("Account Balance");
		lblAccountBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountBalance.setBounds(164, 195, 122, 20);
		contentPane.add(lblAccountBalance);
		
		JLabel lblDOB = new JLabel("D O B");
		lblDOB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDOB.setBounds(164, 241, 92, 20);
		contentPane.add(lblDOB);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(661, 241, 92, 20);
		contentPane.add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(661, 195, 122, 20);
		contentPane.add(lblAddress);
		
		JLabel lblFH = new JLabel("F / H Name");
		lblFH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFH.setBounds(661, 148, 92, 20);
		contentPane.add(lblFH);
		
		lblTxtAcBalance = new JLabel();
		lblTxtAcBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAcBalance.setBounds(341, 195, 190, 20);
		contentPane.add(lblTxtAcBalance);
		
		lblTxtName = new JLabel();
		lblTxtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtName.setBounds(341, 148, 190, 20);
		contentPane.add(lblTxtName);
		
		lblTxtDOB = new JLabel();
		lblTxtDOB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtDOB.setBounds(341, 241, 190, 20);
		contentPane.add(lblTxtDOB);
		
		lblTxtGender = new JLabel();
		lblTxtGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGender.setBounds(812, 241, 214, 20);
		contentPane.add(lblTxtGender);
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddress.setBounds(812, 195, 413, 20);
		contentPane.add(lblTxtAddress);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGName.setBounds(812, 148, 214, 20);
		contentPane.add(lblTxtGName);
		
		JLabel lblLoanCalculation = new JLabel("   LOAN CALCULATION");
		lblLoanCalculation.setOpaque(true);
		lblLoanCalculation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoanCalculation.setBackground(Color.ORANGE);
		lblLoanCalculation.setBounds(83, 306, 1195, 27);
		contentPane.add(lblLoanCalculation);
		
		JLabel lblLastLoanStatus = new JLabel("Last Loan Status");
		lblLastLoanStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastLoanStatus.setBounds(164, 357, 140, 20);
		contentPane.add(lblLastLoanStatus);
		
		JLabel lblPurposeOfLoan = new JLabel("Purpose of Loan");
		lblPurposeOfLoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurposeOfLoan.setBounds(164, 402, 140, 20);
		contentPane.add(lblPurposeOfLoan);
		
		lblTxtLoanStatus = new JLabel();
		lblTxtLoanStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanStatus.setBounds(341, 357, 412, 20);
		contentPane.add(lblTxtLoanStatus);
		
		txtPurpose = new JTextField();
		txtPurpose.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtPurpose.setColumns(10);
		txtPurpose.setBounds(341, 401, 831, 27);
		contentPane.add(txtPurpose);
		
		JLabel lblGranterAcNo = new JLabel("Granter A/C No");
		lblGranterAcNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGranterAcNo.setBounds(164, 449, 140, 20);
		contentPane.add(lblGranterAcNo);
		
		txtGranterAc = new JTextField();
		txtGranterAc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
					txtGranterAc.setEditable(true);
				} else {
					txtGranterAc.setEditable(false);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "* Enter only numeric digits(0-9) ");
					txtGranterAc.setEditable(true);
				}
				Integer acno=Integer.valueOf(txtGranterAc.getText());
				if(acno>200000){
					txtGranterAc.setEditable(false);
					JOptionPane.showMessageDialog(null, "Please Enter valid Account No");
					txtGranterAc.setText("");
					txtGranterAc.setEditable(true);
				}
				String sql=("select * from memberdetail");
				int count=0;
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
						if(txtGranterAc.getText().equals(rs.getString("acno"))){
							count=1;
						}
					}
					if (count==0 && acno >= 100000 && acno<=199999){
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtGranterAc.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtGranterAc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGranterAc.setColumns(10);
		txtGranterAc.setBounds(341, 451, 148, 22);
		contentPane.add(txtGranterAc);
		
		JLabel lblIncomeType = new JLabel("Income Type");
		lblIncomeType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIncomeType.setBounds(543, 449, 116, 20);
		contentPane.add(lblIncomeType);
		
		txtIncomeType = new JTextField();
		txtIncomeType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtIncomeType.setColumns(10);
		txtIncomeType.setBounds(688, 450, 140, 22);
		contentPane.add(txtIncomeType);
		
		JLabel lblTotalIncome = new JLabel("Total Income");
		lblTotalIncome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalIncome.setBounds(886, 449, 116, 20);
		contentPane.add(lblTotalIncome);
		
		txtTotalIncome = new JTextField();
		txtTotalIncome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTotalIncome.setColumns(10);
		txtTotalIncome.setBounds(1032, 450, 140, 22);
		contentPane.add(txtTotalIncome);
				
		JLabel lblLoanAmount = new JLabel("Loan Amount");
		lblLoanAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanAmount.setBounds(191, 500, 108, 20);
		contentPane.add(lblLoanAmount);
		
		txtLoanAmount = new JTextField();
		txtLoanAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				interest();
			}
		});
		txtLoanAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLoanAmount.setColumns(10);
		txtLoanAmount.setBounds(164, 531, 157, 22);
		contentPane.add(txtLoanAmount);
		
		JLabel lblTermmonths = new JLabel("Terms(Months)");
		lblTermmonths.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTermmonths.setBounds(341, 500, 116, 20);
		contentPane.add(lblTermmonths);
		
		JLabel lblInterestpm = new JLabel("Interest(PM)");
		lblInterestpm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInterestpm.setBounds(494, 500, 108, 20);
		contentPane.add(lblInterestpm);
		
		JLabel lblTotalInterest = new JLabel("Total Interest");
		lblTotalInterest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalInterest.setBounds(627, 500, 108, 20);
		contentPane.add(lblTotalInterest);
		
		JLabel lblInstallmentpm = new JLabel("Installment(PM)");
		lblInstallmentpm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInstallmentpm.setBounds(762, 500, 140, 20);
		contentPane.add(lblInstallmentpm);
		
		JLabel lblTotalPayment = new JLabel("Total Amount");
		lblTotalPayment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalPayment.setBounds(912, 500, 108, 20);
		contentPane.add(lblTotalPayment);
		
		JLabel lblLastPaymentDate = new JLabel("Last Payment Date");
		lblLastPaymentDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastPaymentDate.setBounds(1032, 500, 140, 20);
		contentPane.add(lblLastPaymentDate);
		
		lblTxtInterest = new JLabel();
		lblTxtInterest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtInterest.setBounds(494, 533, 108, 20);
		contentPane.add(lblTxtInterest);
		
		lblTxtTotalInterest = new JLabel();
		lblTxtTotalInterest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalInterest.setBounds(627, 533, 108, 20);
		contentPane.add(lblTxtTotalInterest);
		
		lblTxtInstallment = new JLabel();
		lblTxtInstallment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtInstallment.setBounds(762, 533, 140, 20);
		contentPane.add(lblTxtInstallment);
		
		lblTxtTotalAmount = new JLabel();
		lblTxtTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalAmount.setBounds(912, 533, 108, 20);
		contentPane.add(lblTxtTotalAmount);
		
		lblTxtLastPaymentDate = new JLabel();
		lblTxtLastPaymentDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLastPaymentDate.setBounds(1032, 533, 140, 20);
		contentPane.add(lblTxtLastPaymentDate);
		
		comboTerms = new JComboBox();
		for(Integer i=1; i<=60; i++){
			comboTerms.addItem(i);
		}
		comboTerms.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				interest();
			}
		});
		comboTerms.setBounds(351, 532, 58, 20);
		contentPane.add(comboTerms);
				
		JButton btnSanctionLoan = new JButton("Sanction Loan");
		btnSanctionLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loanSaction();
			}
		});
		btnSanctionLoan.setBounds(919, 598, 116, 23);
		contentPane.add(btnSanctionLoan);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(1083, 598, 89, 23);
		contentPane.add(btnCancel);
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
				lblTxtGender.setText(rs.getString("gender"));
				lblTxtDOB.setText(rs.getString("dob"));
				}
			}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void status(){
		String status="",sql=("select * from loantaking where acno="+ txtAcNo.getText() +"");
		int count=0,flag=0;
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				flag=rs.getInt("flag");
				status=rs.getString("status");
				if(txtAcNo.getText().equals(rs.getString("acno"))){
					count=1;
				}
			}
			if (count==1){
				if(flag==1){
					lblTxtLoanStatus.setText(status);
				}
				if(flag==0){
					lblTxtLoanStatus.setText("You have already taken the Loan");
				}
			}else{
				lblTxtLoanStatus.setText("You haven't take any Loan Yet");
			}
		} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
	}
	private void interest() {
		int Terms, LoanAmount=0, TotalInterest,TotalAmount, Installment, InterestPM;
		Terms= Integer.parseInt(comboTerms.getSelectedItem().toString());
		if(txtLoanAmount.getText().isEmpty()){}else{
		LoanAmount=Integer.parseInt(txtLoanAmount.getText());}
		TotalInterest=(LoanAmount * Terms*6)/100;
		TotalAmount=LoanAmount+TotalInterest;
		Installment=TotalAmount/Terms;
		InterestPM= TotalInterest/Terms;
		lblTxtTotalAmount.setText(String.valueOf(TotalAmount));
		lblTxtInstallment.setText(String.valueOf(Installment));
		lblTxtTotalInterest.setText(String.valueOf(TotalInterest));
		lblTxtInterest.setText(String.valueOf(InterestPM));
		
		//Last Payment Date
		Integer a,c,m,y;
		c=Integer.parseInt(comboTerms.getSelectedItem().toString());
		c=c+Integer.parseInt(month);
		a=c/12;
		m=c%12;
		y=Integer.parseInt(year)+a;
		d= day+"/"+m.toString()+"/"+y.toString();
		lblTxtLastPaymentDate.setText(d);
	}
	private void loanSaction(){
 		String acno,purposeofloan,granteracno,incometype,totalincome,loanamount="",terms,interest,totalinterest,installment,totalamount,paymenttill=null,sql1;
 		acno = txtAcNo.getText();
 		purposeofloan = txtPurpose.getText();
 		granteracno = txtGranterAc.getText();
 		incometype = txtIncomeType.getText();
 		totalincome = txtTotalIncome.getText();
 		loanamount = txtLoanAmount.getText();
 		terms= comboTerms.getSelectedItem().toString();
 		interest = lblTxtInterest.getText();
 		totalinterest = lblTxtTotalInterest.getText();
 		installment = lblTxtInstallment.getText();
 		totalamount = lblTxtTotalAmount.getText();
 		paymenttill = lblTxtLastPaymentDate.getText();
 		
 		
 		sql1="INSERT INTO sss.loantaking(id,acno,date,time,loanamount,duration,totalamount,totalinterest,interest,installment,paidtill,purposeofloan,granteracno,incometype,totalincome,doneby) values('"+loanacno+"','"+acno+"',now(),now(),'"+loanamount+"','"+terms+"','"+totalamount+"','"+totalinterest+"','"+interest+"','"+installment+"','"+paymenttill+"','"+purposeofloan+"','"+granteracno+"','"+incometype+"','"+totalincome+"','"+uname+"')";
 		if (purposeofloan.equals("") || granteracno.equals("") || loanamount.equals("") ){
			JOptionPane.showMessageDialog(null, "Fill Requried Data");
		}else
		{
			try {
				Statement stmt=dbc.conmethod();
				stmt.execute(sql1);
				JOptionPane.showMessageDialog(null, "Loan Saction");
				dispose();
				stmt.close();
			}
			catch(java.sql.SQLIntegrityConstraintViolationException e){
				JOptionPane.showInternalMessageDialog(null, "Duplicate Data");
			}
			catch (SQLException e){JOptionPane.showMessageDialog(null, e); } 
			catch(java.lang.NullPointerException ex){ JOptionPane.showMessageDialog(null, ex); }}
	}
}
