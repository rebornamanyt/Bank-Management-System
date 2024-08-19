import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;


public class loandetail extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtAcNo;
	private JLabel lblTxtName;
	private JLabel lblTxtAcBalance;
	private JLabel lblTxtAddress;
	private JLabel lblTxtGName;
	private JLabel lblTxtPurposeOfLoan;
	private JLabel lblTxtGranterAcNo;
	private JLabel lblTxtIncomeType;
	private JLabel lblTxtTotalIncome;
	private JLabel lblTxtLoanAmount;
	private JLabel lblTxtTerms;
	private JLabel lblTxtInterest;
	private JLabel lblTxtTotalAmount;
	private JLabel lblTxtInstallment;
	private JLabel lblTxtTotalInterest;
	private JLabel lblTxtRemainToPay;
	private JLabel lblTxtPaidTill;
	private JLabel lblTxtDuration;
	private JLabel lblTxtLoanId;
	private JLabel lblTxtLoanBy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loandetail frame = new loandetail();
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
	public loandetail() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(1364,720));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				
				String sql=("SELECT * FROM memberdetail"),sql1=("SELECT * FROM loantaking");
				int count=0,count1=0;
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
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
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql1);
					while(rs.next()){
						if(txtAcNo.getText().equals(rs.getString("acno"))){
							count1=1;
						}
					}
					if (count==1 && count1==0 && acno >= 100000 && acno<=199999){
						txtAcNo.setEditable(false);
						JOptionPane.showMessageDialog(null, "You havn't take any Loan Yet, Enter Valid A/C No.");
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
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddress.setBounds(812, 195, 379, 20);
		contentPane.add(lblTxtAddress);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGName.setBounds(812, 148, 314, 20);
		contentPane.add(lblTxtGName);
		
		JLabel lblLoanDetail = new JLabel("   Loan Detail");
		lblLoanDetail.setOpaque(true);
		lblLoanDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoanDetail.setBackground(Color.ORANGE);
		lblLoanDetail.setBounds(83, 248, 1195, 27);
		contentPane.add(lblLoanDetail);
		
		JLabel lblLoanDuratinDate = new JLabel("Loan Duration date");
		lblLoanDuratinDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanDuratinDate.setBounds(164, 311, 152, 20);
		contentPane.add(lblLoanDuratinDate);
		
		JLabel lblPaidTill = new JLabel("Paid till");
		lblPaidTill.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaidTill.setBounds(163, 358, 71, 20);
		contentPane.add(lblPaidTill);
		
		JLabel lblRemainToPay = new JLabel("Remain to Pay");
		lblRemainToPay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRemainToPay.setBounds(536, 358, 108, 20);
		contentPane.add(lblRemainToPay);
		
		JLabel lblPurposeOfLoan = new JLabel("Purpose of Loan");
		lblPurposeOfLoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurposeOfLoan.setBounds(164, 404, 122, 20);
		contentPane.add(lblPurposeOfLoan);
		
		JLabel lblGranterAcNo = new JLabel("Granter A/C No.");
		lblGranterAcNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGranterAcNo.setBounds(164, 449, 122, 20);
		contentPane.add(lblGranterAcNo);
		
		JLabel lblIncomeType = new JLabel("Income Type");
		lblIncomeType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIncomeType.setBounds(536, 449, 108, 20);
		contentPane.add(lblIncomeType);
		
		JLabel lblTotalIncome = new JLabel("Total Income");
		lblTotalIncome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalIncome.setBounds(902, 449, 108, 20);
		contentPane.add(lblTotalIncome);
		
		JLabel lblLoanAmount = new JLabel("Loan Amount");
		lblLoanAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanAmount.setBounds(200, 504, 108, 20);
		contentPane.add(lblLoanAmount);
		
		JLabel lblTermsmonths = new JLabel("Terms(months)");
		lblTermsmonths.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTermsmonths.setBounds(360, 504, 122, 20);
		contentPane.add(lblTermsmonths);
		
		JLabel lblInterest = new JLabel("Interest(PM)");
		lblInterest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInterest.setBounds(536, 504, 108, 20);
		contentPane.add(lblInterest);
		
		JLabel lblTotalInterest = new JLabel("Total Interest");
		lblTotalInterest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalInterest.setBounds(697, 504, 108, 20);
		contentPane.add(lblTotalInterest);
		
		JLabel lblInstallmentpm = new JLabel("Installment(PM)");
		lblInstallmentpm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInstallmentpm.setBounds(869, 504, 132, 20);
		contentPane.add(lblInstallmentpm);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalAmount.setBounds(1054, 504, 108, 20);
		contentPane.add(lblTotalAmount);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(1126, 608, 89, 23);
		contentPane.add(btnNewButton);
		
		lblTxtDuration = new JLabel();
		lblTxtDuration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtDuration.setBounds(341, 311, 325, 20);
		contentPane.add(lblTxtDuration);
		
		lblTxtPaidTill = new JLabel();
		lblTxtPaidTill.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtPaidTill.setBounds(263, 358, 190, 20);
		contentPane.add(lblTxtPaidTill);
		
		lblTxtRemainToPay = new JLabel();
		lblTxtRemainToPay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtRemainToPay.setBounds(691, 358, 204, 20);
		contentPane.add(lblTxtRemainToPay);
		
		lblTxtPurposeOfLoan = new JLabel();
		lblTxtPurposeOfLoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtPurposeOfLoan.setBounds(326, 401, 889, 27);
		contentPane.add(lblTxtPurposeOfLoan);
		
		lblTxtGranterAcNo = new JLabel();
		lblTxtGranterAcNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGranterAcNo.setBounds(308, 449, 175, 20);
		contentPane.add(lblTxtGranterAcNo);
		
		lblTxtIncomeType = new JLabel();
		lblTxtIncomeType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtIncomeType.setBounds(663, 449, 152, 20);
		contentPane.add(lblTxtIncomeType);
		
		lblTxtTotalIncome = new JLabel();
		lblTxtTotalIncome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalIncome.setBounds(1040, 449, 175, 20);
		contentPane.add(lblTxtTotalIncome);
		
		lblTxtLoanAmount = new JLabel();
		lblTxtLoanAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanAmount.setBounds(200, 535, 108, 20);
		contentPane.add(lblTxtLoanAmount);
		
		lblTxtTerms = new JLabel();
		lblTxtTerms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTerms.setBounds(360, 535, 122, 20);
		contentPane.add(lblTxtTerms);
		
		lblTxtInterest = new JLabel();
		lblTxtInterest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtInterest.setBounds(536, 535, 108, 20);
		contentPane.add(lblTxtInterest);
		
		lblTxtTotalAmount = new JLabel();
		lblTxtTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalAmount.setBounds(1054, 535, 108, 20);
		contentPane.add(lblTxtTotalAmount);
		
		lblTxtInstallment = new JLabel();
		lblTxtInstallment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtInstallment.setBounds(869, 535, 132, 20);
		contentPane.add(lblTxtInstallment);
		
		lblTxtTotalInterest = new JLabel();
		lblTxtTotalInterest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalInterest.setBounds(697, 535, 108, 20);
		contentPane.add(lblTxtTotalInterest);
		
		JLabel lblLoanId = new JLabel("Loan ID");
		lblLoanId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanId.setBounds(661, 102, 92, 20);
		contentPane.add(lblLoanId);
		
		lblTxtLoanId = new JLabel();
		lblTxtLoanId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanId.setBounds(812, 102, 314, 20);
		contentPane.add(lblTxtLoanId);
		
		lblTxtLoanBy = new JLabel();
		lblTxtLoanBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoanBy.setBounds(844, 311, 204, 20);
		contentPane.add(lblTxtLoanBy);
		
		JLabel lblLoanAssignedBy = new JLabel("Loan Taken By");
		lblLoanAssignedBy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanAssignedBy.setBounds(689, 311, 126, 20);
		contentPane.add(lblLoanAssignedBy);
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
		String sql="SELECT * FROM sss.loantaking where acno="+ txtAcNo.getText() +"";
		Integer restamount;
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				restamount=rs.getInt("totalamount")-rs.getInt("totalamountpaid");
				lblTxtLoanId.setText(rs.getString("id"));
				lblTxtPurposeOfLoan.setText(rs.getString("purposeofloan"));
				lblTxtGranterAcNo.setText(rs.getString("granteracno"));
				lblTxtIncomeType.setText(rs.getString("incometype"));
				lblTxtTotalIncome.setText(rs.getString("totalincome"));
				lblTxtLoanAmount.setText(rs.getString("loanamount"));
				lblTxtTerms.setText(rs.getString("duration"));
				lblTxtInterest.setText(rs.getString("interest"));
				lblTxtTotalInterest.setText(rs.getString("totalinterest"));
				lblTxtInstallment.setText(rs.getString("installment"));
				lblTxtTotalAmount.setText(rs.getString("totalamount"));
				lblTxtRemainToPay.setText(restamount.toString());
				lblTxtPaidTill.setText(rs.getString("paidtill"));
				lblTxtLoanBy.setText(rs.getString("doneby"));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
		String sql1="SELECT paidtill,DATE_FORMAT(date, '%d/%m/%y') As mydate FROM sss.loantaking where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql1);
			while (rs.next()){
				lblTxtDuration.setText(rs.getString("mydate")+" to "+rs.getString("paidtill"));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
}
