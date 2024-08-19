import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Frame;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class memberdetail extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtAcno;
	private JTextField txtFName;
	private JTextField txtMName;
	private JTextField txtLName;
	private JTextField txtEmail;
	private JTextField txtGName;
	private JTextField txtLine1;
	private JTextField txtLine2;
	private JTextField txtMobile;
	private JTextField txtLandmark;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtDistrict;
	private JTextField txtPin;
	private JTextField txtLine1P;
	private JTextField txtMobileP;
	private JTextField txtLine2P;
	private JTextField txtLandmarkP;
	private JTextField txtDistrictP;
	private JTextField txtPinP;
	private JTextField txtCityP;
	private JTextField txtStateP;
	private JLabel lblTxtStatus;
	private JLabel lblTxtDate;
	private JLabel lblTxtOpenedBy;
	private JLabel lblTxtFileNo;
	private JLabel lblTxtDOB;
	private JLabel lblTxtIntroac1;
	private JLabel lblTxtIntroac2;
	private JLabel lblTxtNameN;
	private JLabel lblTxtGenderN;
	private JLabel lblTxtDOBN;
	private JLabel lblTxtAddressN;
	private JLabel lblTxtRelation;
	private JLabel lblTxtLoan;
	private JLabel lblTxtTotalAmount;
	private JLabel lblTxtPaidAmount;
	private JLabel lblTxtRestAmount; 
	private JComboBox comboGType;
	private JComboBox comboGender;
	private JComboBox comboMaritalStatus;
	private JComboBox comboCategory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					memberdetail frame = new memberdetail();
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
	public memberdetail() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(new Dimension(1364,720));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BASIC DETAIL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(62, 24, 1222, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblAcNo = new JLabel("A/C NO");
		lblAcNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAcNo.setBounds(89, 64, 62, 19);
		contentPane.add(lblAcNo);
		
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
					alldetail();
					loandetail();
				}
				

				String sql=("select * from memberdetail");
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					int count =0;
					while(rs.next()){
						if(txtAcno.getText().equals(rs.getString("acno"))){
							count=1;
						}
					}
					if (count==0 && acno >= 100000 && acno<=199999){
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtAcno.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
				
			}
		});
		txtAcno.setBounds(166, 65, 199, 21);
		contentPane.add(txtAcno);
		txtAcno.setColumns(10);
		
		lblTxtStatus = new JLabel("Status");
		lblTxtStatus.setBackground(Color.LIGHT_GRAY);
		lblTxtStatus.setOpaque(true);
		lblTxtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTxtStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTxtStatus.setBounds(425, 64, 96, 19);
		contentPane.add(lblTxtStatus);
		
		lblTxtDate = new JLabel("Date");
		lblTxtDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTxtDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTxtDate.setBounds(545, 64, 183, 19);
		contentPane.add(lblTxtDate);
		
		JLabel lblAccountOpenBy = new JLabel("Account Open By");
		lblAccountOpenBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountOpenBy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountOpenBy.setBounds(750, 64, 132, 19);
		contentPane.add(lblAccountOpenBy);
		
		lblTxtOpenedBy = new JLabel();
		lblTxtOpenedBy.setHorizontalAlignment(SwingConstants.LEFT);
		lblTxtOpenedBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtOpenedBy.setBounds(905, 64, 125, 19);
		contentPane.add(lblTxtOpenedBy);
		
		JLabel lblFile = new JLabel("File No");
		lblFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFile.setBounds(1016, 64, 96, 19);
		contentPane.add(lblFile);
		
		lblTxtFileNo = new JLabel();
		lblTxtFileNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTxtFileNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtFileNo.setBounds(1122, 64, 96, 19);
		contentPane.add(lblTxtFileNo);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(89, 106, 51, 19);
		contentPane.add(lblName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFirstName.setBounds(111, 131, 96, 19);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMiddleName.setBounds(306, 135, 96, 19);
		contentPane.add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLastName.setBounds(508, 135, 96, 19);
		contentPane.add(lblLastName);
		
		txtFName = new JTextField();
		txtFName.setEditable(false);
		txtFName.setBounds(111, 159, 159, 20);
		contentPane.add(txtFName);
		txtFName.setColumns(10);
		
		txtMName = new JTextField();
		txtMName.setEditable(false);
		txtMName.setColumns(10);
		txtMName.setBounds(306, 159, 159, 20);
		contentPane.add(txtMName);
		
		txtLName = new JTextField();
		txtLName.setEditable(false);
		txtLName.setColumns(10);
		txtLName.setBounds(508, 159, 159, 20);
		contentPane.add(txtLName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(89, 196, 51, 19);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(166, 197, 299, 21);
		contentPane.add(txtEmail);
		
		comboGType = new JComboBox();
		comboGType.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboGType.addItem("Father Name");
		comboGType.addItem("Husband Name");
		comboGType.setBounds(715, 159, 141, 20);
		contentPane.add(comboGType);
		
		txtGName = new JTextField();
		txtGName.setEditable(false);
		txtGName.setDragEnabled(true);
		txtGName.setBounds(897, 159, 333, 20);
		contentPane.add(txtGName);
		txtGName.setColumns(10);
		
		JLabel lblD = new JLabel("D O B");
		lblD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblD.setBounds(508, 200, 51, 19);
		contentPane.add(lblD);
		
		lblTxtDOB = new JLabel("D O B");
		lblTxtDOB.setHorizontalAlignment(SwingConstants.CENTER);
		lblTxtDOB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtDOB.setBounds(568, 199, 99, 19);
		contentPane.add(lblTxtDOB);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(712, 196, 62, 19);
		contentPane.add(lblGender);
		
		JLabel lblMatrialStatus = new JLabel("Marital Status");
		lblMatrialStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatrialStatus.setBounds(862, 196, 106, 19);
		contentPane.add(lblMatrialStatus);
		
		comboGender = new JComboBox();
		comboGender.addItem("Male");
		comboGender.addItem("Female");
		comboGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboGender.setBounds(771, 195, 72, 20);
		contentPane.add(comboGender);
		
		comboMaritalStatus = new JComboBox();
		comboMaritalStatus.addItem("Married");
		comboMaritalStatus.addItem("Unmarried");
		comboMaritalStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboMaritalStatus.setBounds(978, 195, 96, 20);
		contentPane.add(comboMaritalStatus);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCategory.setBounds(1084, 196, 74, 19);
		contentPane.add(lblCategory);
		
		comboCategory = new JComboBox();
		comboCategory.addItem("GEN");
		comboCategory.addItem("OBC");
		comboCategory.addItem("ST/SC");
		comboCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboCategory.setBounds(1168, 195, 62, 20);
		contentPane.add(comboCategory);
		
		JLabel lblAddressDetail = new JLabel("ADDRESS DETAIL");
		lblAddressDetail.setOpaque(true);
		lblAddressDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddressDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddressDetail.setBackground(Color.ORANGE);
		lblAddressDetail.setBounds(62, 240, 1222, 19);
		contentPane.add(lblAddressDetail);
		
		JLabel lblCurrentAdress = new JLabel("Current Adress");
		lblCurrentAdress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentAdress.setBounds(89, 270, 118, 19);
		contentPane.add(lblCurrentAdress);
		
		JLabel lblLine = new JLabel("Line 1");
		lblLine.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLine.setBounds(99, 300, 51, 19);
		contentPane.add(lblLine);
		
		JLabel lblLine_1 = new JLabel("Line 2");
		lblLine_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLine_1.setBounds(377, 301, 51, 19);
		contentPane.add(lblLine_1);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMobileNo.setBounds(89, 331, 84, 19);
		contentPane.add(lblMobileNo);
		
		JLabel lblLandmark = new JLabel("LandMark");
		lblLandmark.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLandmark.setBounds(369, 331, 84, 19);
		contentPane.add(lblLandmark);
		
		JLabel lblCity = new JLabel("city");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCity.setBounds(99, 361, 51, 19);
		contentPane.add(lblCity);
		
		txtLine1 = new JTextField();
		txtLine1.setEditable(false);
		txtLine1.setColumns(10);
		txtLine1.setBounds(183, 300, 165, 20);
		contentPane.add(txtLine1);
		
		txtLine2 = new JTextField();
		txtLine2.setEditable(false);
		txtLine2.setColumns(10);
		txtLine2.setBounds(455, 300, 169, 20);
		contentPane.add(txtLine2);
		
		txtMobile = new JTextField();
		txtMobile.setEditable(false);
		txtMobile.setColumns(10);
		txtMobile.setBounds(183, 332, 165, 20);
		contentPane.add(txtMobile);
		
		txtLandmark = new JTextField();
		txtLandmark.setEditable(false);
		txtLandmark.setColumns(10);
		txtLandmark.setBounds(455, 333, 169, 20);
		contentPane.add(txtLandmark);
		
		txtCity = new JTextField();
		txtCity.setEditable(false);
		txtCity.setColumns(10);
		txtCity.setBounds(183, 362, 165, 20);
		contentPane.add(txtCity);
		
		JLabel lblDistric = new JLabel("District");
		lblDistric.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDistric.setBounds(377, 362, 62, 19);
		contentPane.add(lblDistric);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblState.setBounds(99, 391, 51, 19);
		contentPane.add(lblState);
		
		JLabel lblPincode = new JLabel("Pincode");
		lblPincode.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPincode.setBounds(377, 392, 62, 19);
		contentPane.add(lblPincode);
		
		txtState = new JTextField();
		txtState.setEditable(false);
		txtState.setColumns(10);
		txtState.setBounds(183, 393, 165, 20);
		contentPane.add(txtState);
		
		txtDistrict = new JTextField();
		txtDistrict.setEditable(false);
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(455, 363, 169, 20);
		contentPane.add(txtDistrict);
		
		txtPin = new JTextField();
		txtPin.setEditable(false);
		txtPin.setColumns(10);
		txtPin.setBounds(455, 393, 169, 20);
		contentPane.add(txtPin);
		
		txtLine1P = new JTextField();
		txtLine1P.setEditable(false);
		txtLine1P.setColumns(10);
		txtLine1P.setBounds(784, 300, 164, 20);
		contentPane.add(txtLine1P);
		
		JLabel label_2 = new JLabel("Line 1");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(700, 300, 51, 19);
		contentPane.add(label_2);
		
		JLabel lblPermanentAdress = new JLabel("Permanent Adress");
		lblPermanentAdress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPermanentAdress.setBounds(679, 270, 141, 19);
		contentPane.add(lblPermanentAdress);
		
		JLabel label_4 = new JLabel("Mobile No");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(690, 331, 84, 19);
		contentPane.add(label_4);
		
		txtMobileP = new JTextField();
		txtMobileP.setEditable(false);
		txtMobileP.setColumns(10);
		txtMobileP.setBounds(783, 332, 165, 20);
		contentPane.add(txtMobileP);
		
		JLabel label_5 = new JLabel("Line 2");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(979, 300, 51, 19);
		contentPane.add(label_5);
		
		txtLine2P = new JTextField();
		txtLine2P.setEditable(false);
		txtLine2P.setColumns(10);
		txtLine2P.setBounds(1057, 299, 173, 20);
		contentPane.add(txtLine2P);
		
		txtLandmarkP = new JTextField();
		txtLandmarkP.setEditable(false);
		txtLandmarkP.setColumns(10);
		txtLandmarkP.setBounds(1057, 332, 173, 20);
		contentPane.add(txtLandmarkP);
		
		JLabel label_6 = new JLabel("LandMark");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(971, 330, 84, 19);
		contentPane.add(label_6);
		
		txtDistrictP = new JTextField();
		txtDistrictP.setEditable(false);
		txtDistrictP.setColumns(10);
		txtDistrictP.setBounds(1057, 362, 173, 20);
		contentPane.add(txtDistrictP);
		
		txtPinP = new JTextField();
		txtPinP.setEditable(false);
		txtPinP.setColumns(10);
		txtPinP.setBounds(1057, 392, 173, 20);
		contentPane.add(txtPinP);
		
		JLabel label_7 = new JLabel("Pincode");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(979, 391, 62, 19);
		contentPane.add(label_7);
		
		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDistrict.setBounds(979, 361, 62, 19);
		contentPane.add(lblDistrict);
		
		txtCityP = new JTextField();
		txtCityP.setEditable(false);
		txtCityP.setColumns(10);
		txtCityP.setBounds(783, 362, 165, 20);
		contentPane.add(txtCityP);
		
		JLabel label_9 = new JLabel("city");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_9.setBounds(700, 361, 51, 19);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("State");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_10.setBounds(700, 391, 51, 19);
		contentPane.add(label_10);
		
		txtStateP = new JTextField();
		txtStateP.setEditable(false);
		txtStateP.setColumns(10);
		txtStateP.setBounds(783, 393, 165, 20);
		contentPane.add(txtStateP);
		
		JLabel lblIntroducerDetail = new JLabel("INTRODUCER DETAIL");
		lblIntroducerDetail.setOpaque(true);
		lblIntroducerDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducerDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIntroducerDetail.setBackground(Color.ORANGE);
		lblIntroducerDetail.setBounds(62, 432, 375, 19);
		contentPane.add(lblIntroducerDetail);
		
		JLabel lblIntroducerstAc = new JLabel("Introducer 1st A/C");
		lblIntroducerstAc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIntroducerstAc.setBounds(89, 462, 148, 19);
		contentPane.add(lblIntroducerstAc);
		
		JLabel lblIntroducerndAc = new JLabel("Introducer 2nd A/C");
		lblIntroducerndAc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIntroducerndAc.setBounds(89, 496, 148, 19);
		contentPane.add(lblIntroducerndAc);
		
		JLabel lblNomineeDetail = new JLabel("NOMINEE DETAIL");
		lblNomineeDetail.setOpaque(true);
		lblNomineeDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomineeDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomineeDetail.setBackground(Color.ORANGE);
		lblNomineeDetail.setBounds(455, 432, 829, 19);
		contentPane.add(lblNomineeDetail);
		
		lblTxtIntroac1 = new JLabel();
		lblTxtIntroac1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtIntroac1.setBounds(254, 462, 148, 19);
		contentPane.add(lblTxtIntroac1);
		
		lblTxtIntroac2 = new JLabel();
		lblTxtIntroac2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtIntroac2.setBounds(254, 496, 148, 19);
		contentPane.add(lblTxtIntroac2);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName_1.setBounds(498, 462, 62, 19);
		contentPane.add(lblName_1);
		
		JLabel lblGender_1 = new JLabel("Gender");
		lblGender_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender_1.setBounds(760, 462, 62, 19);
		contentPane.add(lblGender_1);
		
		JLabel lblDOB_1 = new JLabel("D O B");
		lblDOB_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDOB_1.setBounds(1005, 462, 62, 19);
		contentPane.add(lblDOB_1);
		
		lblTxtNameN = new JLabel();
		lblTxtNameN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtNameN.setBounds(568, 462, 148, 19);
		contentPane.add(lblTxtNameN);
		
		lblTxtGenderN = new JLabel();
		lblTxtGenderN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGenderN.setBounds(839, 462, 141, 19);
		contentPane.add(lblTxtGenderN);
		
		lblTxtDOBN = new JLabel();
		lblTxtDOBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtDOBN.setBounds(1077, 462, 141, 19);
		contentPane.add(lblTxtDOBN);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(498, 496, 62, 19);
		contentPane.add(lblAddress);
		
		lblTxtAddressN = new JLabel();
		lblTxtAddressN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddressN.setBounds(578, 496, 266, 19);
		contentPane.add(lblTxtAddressN);
		
		JLabel lblRelationWithA = new JLabel("Relation with A/C Holder");
		lblRelationWithA.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRelationWithA.setBounds(862, 496, 193, 19);
		contentPane.add(lblRelationWithA);
		
		lblTxtRelation = new JLabel();
		lblTxtRelation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtRelation.setBounds(1077, 496, 153, 19);
		contentPane.add(lblTxtRelation);
		
		JLabel lblLoanDetail = new JLabel("LOAN DETAIL");
		lblLoanDetail.setOpaque(true);
		lblLoanDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoanDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoanDetail.setBackground(Color.ORANGE);
		lblLoanDetail.setBounds(62, 526, 1222, 19);
		contentPane.add(lblLoanDetail);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(1141, 627, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new updatemember().setVisible(true);
				dispose();
			}
		});
		btnEdit.setBounds(1016, 627, 89, 23);
		contentPane.add(btnEdit);
		
		JLabel lblLoan = new JLabel("Loan");
		lblLoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoan.setBounds(89, 572, 51, 19);
		contentPane.add(lblLoan);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalAmount.setBounds(482, 576, 112, 19);
		contentPane.add(lblTotalAmount);
		
		JLabel lblPaidAmount = new JLabel("Paid Amount");
		lblPaidAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaidAmount.setBounds(744, 576, 112, 19);
		contentPane.add(lblPaidAmount);
		
		JLabel lblRestAmount = new JLabel("Rest Amount");
		lblRestAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRestAmount.setBounds(978, 576, 112, 19);
		contentPane.add(lblRestAmount);
		
		lblTxtLoan = new JLabel();
		lblTxtLoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtLoan.setBounds(166, 572, 262, 19);
		contentPane.add(lblTxtLoan);
		
		lblTxtTotalAmount = new JLabel();
		lblTxtTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtTotalAmount.setBounds(596, 576, 132, 19);
		contentPane.add(lblTxtTotalAmount);
		
		lblTxtPaidAmount = new JLabel();
		lblTxtPaidAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtPaidAmount.setBounds(850, 576, 118, 19);
		contentPane.add(lblTxtPaidAmount);
		
		lblTxtRestAmount = new JLabel();
		lblTxtRestAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtRestAmount.setBounds(1084, 576, 146, 19);
		contentPane.add(lblTxtRestAmount);
		
		JLabel lblwithInterest = new JLabel();
		lblwithInterest.setText("(with Interest)");
		lblwithInterest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblwithInterest.setBounds(492, 592, 84, 19);
		contentPane.add(lblwithInterest);
	}
	
	private void alldetail(){
		String sql1="SELECT * FROM sss.memberdetail where acno="+ txtAcno.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql1);
			while (rs.next()){
				lblTxtStatus.setText(rs.getString("status"));
				if(rs.getString("status").equals("Active")){
					lblTxtStatus.setBackground(Color.GREEN);
					lblTxtDate.setText(rs.getString("openeddate"));
				}
				else{
					lblTxtStatus.setBackground(Color.RED);
					lblTxtDate.setText(rs.getString("deactivateddate"));
				}
				lblTxtOpenedBy.setText(rs.getString("openedby"));
				lblTxtFileNo.setText(rs.getString("fileno"));
				txtFName.setText(rs.getString("fname"));
				txtLName.setText(rs.getString("lname"));
				txtMName.setText(rs.getString("mname"));
				if(rs.getString("gtype").equals("Father")){comboGType.setSelectedIndex(0);}
				else if(rs.getString("gtype").equals("Husband")){comboGType.setSelectedIndex(1);}
				txtGName.setText(rs.getString("gname"));
				txtEmail.setText(rs.getString("email"));
				lblTxtDOB.setText(rs.getString("dob"));
				if(rs.getString("gender").equals("Male")){comboGender.setSelectedIndex(0);}
				else if(rs.getString("gender").equals("Female")){comboGender.setSelectedIndex(1);}
				if(rs.getString("maritalstatus").equals("Married")){comboMaritalStatus.setSelectedIndex(0);}
				else if(rs.getString("maritalstatus").equals("Unmarried")){comboMaritalStatus.setSelectedIndex(1);}
				if(rs.getString("category").equals("GEN")){comboCategory.setSelectedIndex(0);}
				else if(rs.getString("category").equals("OBC")){comboCategory.setSelectedIndex(1);}
				else if(rs.getString("category").equals("SC/ST")){comboCategory.setSelectedIndex(2);}
				txtLine1.setText(rs.getString("line1"));
				txtLine2.setText(rs.getString("line2"));
				txtMobile.setText(rs.getString("mobileno"));
				txtLandmark.setText(rs.getString("landmark"));
				txtCity.setText(rs.getString("city"));
				txtDistrict.setText(rs.getString("distric"));
				txtState.setText(rs.getString("state"));
				txtPin.setText(rs.getString("pincode"));
				txtLine1P.setText(rs.getString("line1p"));
				txtLine2P.setText(rs.getString("line2p"));
				txtMobileP.setText(rs.getString("mobilenop"));
				txtLandmarkP.setText(rs.getString("landmarkp"));
				txtCityP.setText(rs.getString("cityp"));
				txtDistrictP.setText(rs.getString("districp"));
				txtStateP.setText(rs.getString("statep"));
				txtPinP.setText(rs.getString("pincodep"));
				lblTxtIntroac1.setText(rs.getString("introac1"));lblTxtIntroac2.setText(rs.getString("introac2"));
				lblTxtNameN.setText(rs.getString("fnamen")+" "+rs.getString("mnamen")+" "+rs.getString("lnamen"));
				lblTxtAddressN.setText(rs.getString("addressn"));
				lblTxtGenderN.setText(rs.getString("gendern"));
				lblTxtDOBN.setText(rs.getString("dobn"));
				lblTxtRelation.setText(rs.getString("relation"));
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void loandetail(){
		String Accountno=txtAcno.getText(),opendate="", lastdate="";
		Integer totalamount=0,totalamountpaid=0;
		String sql=("SELECT acno, DATE_FORMAT(date, '%d/%m/%Y') As opendate, totalamount, paidtill, totalamountpaid FROM loantaking WHERE acno='"+Accountno+"' ");
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			int count =0;
			while(rs.next()){
				opendate=rs.getString("opendate");
				lastdate=rs.getString("paidtill");
				totalamount=rs.getInt("totalamount");
				totalamountpaid=rs.getInt("totalamountpaid");
				if(txtAcno.getText().equals(rs.getString("acno"))){
					count=1;
				}
			}
			if (count==1){
				lblTxtLoan.setText(opendate+" to "+lastdate);
				lblTxtTotalAmount.setText(String.valueOf(totalamount));
				lblTxtPaidAmount.setText(String.valueOf(totalamountpaid));
				lblTxtRestAmount.setText(String.valueOf(totalamount-totalamountpaid));
			}else{
				lblTxtLoan.setText("You havn't take any Loan");
				lblTxtTotalAmount.setText("");
				lblTxtPaidAmount.setText("");
				lblTxtRestAmount.setText("");
			}
		} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
	}
}
