import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Frame;


public class loanpaymentdetail extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtAcNo;
	private JLabel lblTxtName;
	private JLabel lblTxtLoanAmount;
	private JLabel lblTxtGName;
	private JLabel lblTxtInstallment;
	private JLabel lblTxtPaymentTill;
	private JLabel lblTxtAddress;
	private JLabel lblTxtTotalPaidAmount;
	private JLabel lblTxtRestAmount;
	private JLabel lblTxtLoanId;
	private JLabel lblLastLoanPayment;
	private JLabel lblTxtInterest;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	Object[] row = new Object[8];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loanpaymentdetail frame = new loanpaymentdetail();
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
	public loanpaymentdetail() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 30, 926, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOAN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 38, 920, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account No.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(57, 96, 89, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(57, 147, 65, 19);
		contentPane.add(lblName);
		
		JLabel lblLoanAmount = new JLabel("Loan Amount");
		lblLoanAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanAmount.setBounds(57, 188, 102, 19);
		contentPane.add(lblLoanAmount);
		
		JLabel lblRestAmount = new JLabel("Rest Amount");
		lblRestAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRestAmount.setBounds(313, 231, 102, 19);
		contentPane.add(lblRestAmount);
		
		JLabel lblInstallment = new JLabel("Installment");
		lblInstallment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInstallment.setBounds(593, 188, 89, 19);
		contentPane.add(lblInstallment);
		
		JLabel lblFhName = new JLabel("F/H Name");
		lblFhName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFhName.setBounds(313, 147, 89, 19);
		contentPane.add(lblFhName);
		
		JLabel lblLoanId = new JLabel("Loan Id");
		lblLoanId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoanId.setBounds(485, 96, 89, 19);
		contentPane.add(lblLoanId);
		
		JLabel lblTotalPaidAmount = new JLabel("Total Paid Amount");
		lblTotalPaidAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalPaidAmount.setBounds(57, 231, 147, 19);
		contentPane.add(lblTotalPaidAmount);
		
		JLabel lblPaymentTill = new JLabel("Payment Till");
		lblPaymentTill.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaymentTill.setBounds(593, 231, 102, 19);
		contentPane.add(lblPaymentTill);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(593, 147, 73, 19);
		contentPane.add(lblAddress);
		
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
					reset();
					JOptionPane.showMessageDialog(null, "Please Enter valid Account No");
					txtAcNo.setText("");
					txtAcNo.setEditable(true);
				}
				if(acno >= 100000 && acno<=199999){
					detail();
					loandetail();
					model.getDataVector().removeAllElements();
					paymentdetail();
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
						reset();
						model.getDataVector().removeAllElements();
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
						reset();
						model.getDataVector().removeAllElements();
						JOptionPane.showMessageDialog(null, "You havn't take any Loan Yet, Enter Valid A/C No.");
						txtAcNo.setText("");
						txtAcNo.setEditable(true);
						txtAcNo.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtAcNo.setBounds(168, 94, 158, 21);
		contentPane.add(txtAcNo);
		txtAcNo.setColumns(10);
		
		lblTxtName = new JLabel();
		lblTxtName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtName.setBounds(121, 147, 182, 19);
		contentPane.add(lblTxtName);
		
		lblTxtLoanAmount = new JLabel();
		lblTxtLoanAmount.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtLoanAmount.setBounds(169, 188, 102, 19);
		contentPane.add(lblTxtLoanAmount);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtGName.setBounds(399, 147, 188, 19);
		contentPane.add(lblTxtGName);
		
		lblTxtInstallment = new JLabel();
		lblTxtInstallment.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtInstallment.setBounds(705, 188, 89, 19);
		contentPane.add(lblTxtInstallment);
		
		lblTxtPaymentTill = new JLabel();
		lblTxtPaymentTill.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtPaymentTill.setBounds(705, 231, 102, 19);
		contentPane.add(lblTxtPaymentTill);
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtAddress.setBounds(663, 147, 259, 19);
		contentPane.add(lblTxtAddress);
		
		lblTxtTotalPaidAmount = new JLabel();
		lblTxtTotalPaidAmount.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtTotalPaidAmount.setBounds(200, 231, 109, 19);
		contentPane.add(lblTxtTotalPaidAmount);
		
		lblTxtRestAmount = new JLabel();
		lblTxtRestAmount.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtRestAmount.setBounds(425, 231, 102, 19);
		contentPane.add(lblTxtRestAmount);
		
		lblTxtLoanId = new JLabel();
		lblTxtLoanId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtLoanId.setBounds(584, 96, 89, 19);
		contentPane.add(lblTxtLoanId);
		
		lblLastLoanPayment = new JLabel();
		lblLastLoanPayment.setText("Last Loan Payment Detail");
		lblLastLoanPayment.setOpaque(true);
		lblLastLoanPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastLoanPayment.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastLoanPayment.setBackground(Color.ORANGE);
		lblLastLoanPayment.setBounds(0, 290, 922, 26);
		contentPane.add(lblLastLoanPayment);
		
		table = new JTable();
		table.setBounds(143, 363, 649, 248);
		contentPane.add(table);
		Object[] columns = {"Id","Particular","Cheque No","Amount","Date","Time","Total Amount Paid","Done By"};
        table.setModel(model);
        model.setColumnIdentifiers(columns);
        table.setRowHeight(30);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(121, 363, 671, 209);
        contentPane.setLayout(null);
        contentPane.add(pane);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		JLabel lblInterest = new JLabel("Interest");
		lblInterest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInterest.setBounds(313, 188, 89, 19);
		contentPane.add(lblInterest);
		
		lblTxtInterest = new JLabel();
		lblTxtInterest.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTxtInterest.setBounds(412, 188, 102, 19);
		contentPane.add(lblTxtInterest);
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
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void loandetail(){
		String sql="SELECT * FROM sss.loantaking where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				Integer totalamount,paidamount,restamount;
				lblTxtLoanId.setText(rs.getString("id"));
				lblTxtLoanAmount.setText(rs.getString("loanamount"));
				lblTxtInstallment.setText(rs.getString("installment"));
				lblTxtInterest.setText(rs.getString("totalinterest"));
				lblTxtPaymentTill.setText(rs.getString("paidtill"));
				lblTxtTotalPaidAmount.setText(rs.getString("totalamountpaid"));
				totalamount=rs.getInt("totalamount");
				paidamount=rs.getInt("totalamountpaid");
				restamount=totalamount-paidamount;
				lblTxtRestAmount.setText(String.valueOf(restamount));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}

	private void paymentdetail(){
		String id=null, particular=null, cheque=null, amount=null, date=null, time=null, totalamountpaid=null, doneby=null;
		String sql1="SELECT * FROM sss.loanpayment where acno='"+ txtAcNo.getText() +"' ORDER BY date DESC";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql1);
			while (rs.next()){
				id = rs.getString("id");
				particular = rs.getString("particular");
				cheque = rs.getString("cheque");
				amount = rs.getString("amount");
				date = rs.getString("date");
				time = rs.getString("time");
				totalamountpaid = rs.getString("amountpaid");
				doneby = rs.getString("doneby");
				
				row[0]= id;
				row[1]= particular;
				row[2]= cheque;
				row[3]= amount;
				row[4]= date;
				row[5]= time;
				row[6]= totalamountpaid;
				row[7]= doneby;
				
				model.addRow(row);
			}
			
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}	
	}
	private void reset(){
		lblTxtLoanId.setText("");
		lblTxtName.setText("");
		lblTxtGName.setText("");
		lblTxtAddress.setText("");
		lblTxtLoanAmount.setText("");
		lblTxtInstallment.setText("");
		lblTxtInterest.setText("");
		lblTxtPaymentTill.setText("");
		lblTxtTotalPaidAmount.setText("");
		lblTxtRestAmount.setText("");
	}
}
