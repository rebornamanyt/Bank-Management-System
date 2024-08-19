import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Frame;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class mainpage extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JLabel lblTxtTCrAmount;
	private JLabel lblTxtTDrAmount;
	private JLabel lblTxtTLPAmount;
	private JLabel lblTxtLoanSatteled;
	private JLabel lblTxtLoanSaction;
	private JLabel lblTxtTransaction;

	java.util.Date datet= Calendar.getInstance().getTime();
    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
    String today = formatter.format(datet);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage frame = new mainpage(null);
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
	public mainpage(String username) {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setTitle("Welcome "+username);
		setSize(new Dimension(1370,730));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddMember = new JButton(new ImageIcon("image/member.png"));
		btnAddMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddMember.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addmember(username).setVisible(true);
			}
		});
		btnAddMember.setBounds(111, 270, 150, 150);
		contentPane.add(btnAddMember);
		
		JButton btnMemberDetail = new JButton(new ImageIcon("image/memberinfo.png"));
		btnMemberDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMemberDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new memberdetail().setVisible(true);
			}
		});
		btnMemberDetail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMemberDetail.setBounds(353, 270, 150, 150);
		contentPane.add(btnMemberDetail);
		
		JButton btnDeleteMember = new JButton(new ImageIcon("image/memberminus.png"));
		btnDeleteMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new deactivatemember().setVisible(true);
			}
		});
		btnDeleteMember.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteMember.setBounds(596, 270, 150, 150);
		contentPane.add(btnDeleteMember);
		
		JButton btnLoanTaking = new JButton(new ImageIcon("image/loan.png"));
		btnLoanTaking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoanTaking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loantaking(username).setVisible(true);
			}
		});
		btnLoanTaking.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoanTaking.setBounds(111, 486, 150, 150);
		contentPane.add(btnLoanTaking);
		
		JButton btnLoanPayment = new JButton(new ImageIcon("image/loanpay.png"));
		btnLoanPayment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoanPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loanpayment(username).setVisible(true);
			}
		});
		btnLoanPayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoanPayment.setBounds(596, 57, 150, 150);
		contentPane.add(btnLoanPayment);
		
		JButton btnLoanDetail = new JButton(new ImageIcon("image/loaninfo.png"));
		btnLoanDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoanDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loandetail().setVisible(true);
			}
		});
		btnLoanDetail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoanDetail.setBounds(353, 486, 150, 150);
		contentPane.add(btnLoanDetail);
		
		JButton btnTransaction = new JButton(new ImageIcon("image/transaction.png"));
		btnTransaction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new transaction(username).setVisible(true);
			}
		});
		btnTransaction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTransaction.setBounds(109, 57, 150, 150);
		contentPane.add(btnTransaction);
		
		JButton btnMiniStatement = new JButton(new ImageIcon("image/ministatement.png"));
		btnMiniStatement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMiniStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ministatement().setVisible(true);
			}
		});
		btnMiniStatement.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMiniStatement.setBounds(353, 57, 150, 150);
		contentPane.add(btnMiniStatement);
		
		JButton btnLoanSettlemnt = new JButton(new ImageIcon("image/loansat.png"));
		btnLoanSettlemnt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoanSettlemnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loansattle().setVisible(true);
			}
		});
		btnLoanSettlemnt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoanSettlemnt.setBounds(596, 486, 150, 150);
		contentPane.add(btnLoanSettlemnt);
		
		JPanel panel = new JPanel();
		panel.setBounds(1093, 240, 203, 321);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Total Cr. Amount");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 123, 17);
		panel.add(lblNewLabel);
		
		JLabel lblTotalDrAmount = new JLabel("Total Dr. Amount");
		lblTotalDrAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalDrAmount.setBounds(10, 71, 123, 17);
		panel.add(lblTotalDrAmount);
		
		JLabel lblTotalLoanPaid = new JLabel("Total Loan Paid Amount");
		lblTotalLoanPaid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalLoanPaid.setBounds(10, 137, 166, 17);
		panel.add(lblTotalLoanPaid);
		
		JLabel lblNoOfTransaction = new JLabel("No. of Transaction");
		lblNoOfTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNoOfTransaction.setBounds(10, 202, 136, 17);
		panel.add(lblNoOfTransaction);
		
		JLabel lblNoOfLoan = new JLabel("No. of Loan Saction");
		lblNoOfLoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNoOfLoan.setBounds(10, 234, 136, 17);
		panel.add(lblNoOfLoan);
		
		JLabel lblNoOfLoan_1 = new JLabel("No. of Loan Satteled");
		lblNoOfLoan_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNoOfLoan_1.setBounds(10, 266, 146, 17);
		panel.add(lblNoOfLoan_1);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRs.setBounds(20, 32, 27, 17);
		panel.add(lblRs);
		
		JLabel label = new JLabel("Rs");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(20, 91, 27, 17);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Rs");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(20, 158, 27, 17);
		panel.add(label_1);
		
		lblTxtTCrAmount = new JLabel("0");
		lblTxtTCrAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxtTCrAmount.setBounds(57, 32, 99, 17);
		panel.add(lblTxtTCrAmount);
		
		lblTxtTDrAmount = new JLabel("0");
		lblTxtTDrAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxtTDrAmount.setBounds(55, 91, 99, 17);
		panel.add(lblTxtTDrAmount);
		
		lblTxtTLPAmount = new JLabel("0");
		lblTxtTLPAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxtTLPAmount.setBounds(51, 157, 99, 17);
		panel.add(lblTxtTLPAmount);
		
		lblTxtLoanSatteled = new JLabel("0");
		lblTxtLoanSatteled.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxtLoanSatteled.setBounds(160, 264, 34, 17);
		panel.add(lblTxtLoanSatteled);
		
		lblTxtLoanSaction = new JLabel("0");
		lblTxtLoanSaction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxtLoanSaction.setBounds(159, 235, 35, 17);
		panel.add(lblTxtLoanSaction);
		
		lblTxtTransaction = new JLabel("0");
		lblTxtTransaction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxtTransaction.setBounds(160, 204, 32, 17);
		panel.add(lblTxtTransaction);
		
		JButton btnPaymentDetail = new JButton(new ImageIcon("image/loanpayinfo.png"));
		btnPaymentDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaymentDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loanpaymentdetail().setVisible(true);
			}
		});
		btnPaymentDetail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPaymentDetail.setBounds(837, 57, 150, 150);
		contentPane.add(btnPaymentDetail);
		
		JButton btnAdminPannel = new JButton(new ImageIcon("image/userpannel.png"));
		btnAdminPannel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdminPannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminpannel().setVisible(true);
			}
		});
		btnAdminPannel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdminPannel.setBounds(837, 270, 150, 150);
		contentPane.add(btnAdminPannel);
		
		JLabel lblNewLabel_1 = new JLabel("Today");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setVisible(true);
				today();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setVisible(false);
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		lblNewLabel_1.setBackground(Color.ORANGE);
		lblNewLabel_1.setBounds(1127, 192, 135, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("image/logout.png"));
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new login().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setBounds(1165, 79, 46, 42);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Transaction");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(147, 207, 76, 17);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblStatement = new JLabel("Statement");
		lblStatement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatement.setBounds(397, 207, 76, 17);
		contentPane.add(lblStatement);
		
		JLabel lblLoanPayment = new JLabel("Loan Payment");
		lblLoanPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoanPayment.setBounds(631, 207, 89, 17);
		contentPane.add(lblLoanPayment);
		
		JLabel lblLoanPaymentDetail = new JLabel("Loan Payment Detail");
		lblLoanPaymentDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoanPaymentDetail.setBounds(847, 210, 140, 17);
		contentPane.add(lblLoanPaymentDetail);
		
		JLabel lblAddMember = new JLabel("Add Member");
		lblAddMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddMember.setBounds(147, 422, 89, 17);
		contentPane.add(lblAddMember);
		
		JLabel lblDetailUpdate = new JLabel("Detail / Update Member");
		lblDetailUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDetailUpdate.setBounds(353, 422, 150, 17);
		contentPane.add(lblDetailUpdate);
		
		JLabel lblDeactivateMember = new JLabel("Deactivate Member");
		lblDeactivateMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeactivateMember.setBounds(606, 422, 126, 17);
		contentPane.add(lblDeactivateMember);
		
		JLabel lblUserPannel = new JLabel("User Pannel");
		lblUserPannel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserPannel.setBounds(878, 422, 76, 17);
		contentPane.add(lblUserPannel);
		
		JLabel lblLoanTaking = new JLabel("Loan Taking");
		lblLoanTaking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoanTaking.setBounds(147, 636, 76, 17);
		contentPane.add(lblLoanTaking);
		
		JLabel lblLoanDetail = new JLabel("Loan Detail");
		lblLoanDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoanDetail.setBounds(386, 636, 76, 17);
		contentPane.add(lblLoanDetail);
		
		JLabel lblLoanSattelment = new JLabel("Loan Sattelment");
		lblLoanSattelment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoanSattelment.setBounds(621, 639, 111, 17);
		contentPane.add(lblLoanSattelment);
	}
	private void today(){
		String sql1,sql2,sql3,sql4,Particular;
		Integer count =0,cr=0,dr=0,loan=0;
		
		sql1="select * from sss.transaction where date='"+today+"'";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql1);
			while(rs.next()){
				count++;
				Particular=rs.getString("particular");
				if(Particular.equals("To Cash") ||Particular.equals("To Cheque")){
					cr=cr+Integer.parseInt(rs.getString("amount"));
				}else if(Particular.equals("By Cash") ||Particular.equals("By Cheque")){
					dr=dr+Integer.parseInt(rs.getString("amount"));
				}
			}
			lblTxtTransaction.setText(count.toString());
			lblTxtTCrAmount.setText(cr.toString());
			lblTxtTDrAmount.setText(dr.toString());
		} catch (Exception e) { JOptionPane.showMessageDialog(null, e);}
		sql2="select * from sss.loanpayment where date='"+today+"'";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql2);
			while(rs.next()){
				loan=loan+Integer.parseInt(rs.getString("amount"));	
			}
			lblTxtTLPAmount.setText(String.valueOf(loan));
		} catch (Exception e) { JOptionPane.showMessageDialog(null, e);}
		sql3="select * from sss.loantaking where date='"+today+"'";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql3);
			Integer x=0;
			while(rs.next()){
				Integer z=Integer.parseInt(rs.getString("flag"));
				if(z==0){
					x++;
				}
			}
			lblTxtLoanSaction.setText(x.toString());
		} catch (Exception e) { JOptionPane.showMessageDialog(null, e);}
		sql4="select * from sss.loantaking where satelmentdate='"+today+"'";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql4);
			Integer y=0;
			while(rs.next()){
				Integer z=Integer.parseInt(rs.getString("flag"));
				if(z==1){
					y++;
				}
			}
			lblTxtLoanSatteled.setText(y.toString());
		} catch (Exception e) { JOptionPane.showMessageDialog(null, e);}
	}
}
