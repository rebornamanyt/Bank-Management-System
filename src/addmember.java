import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Color;
import java.awt.TextArea;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

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

import org.eclipse.wb.swing.FocusTraversalOnArray;


public class addmember extends JFrame {

	Integer accountno,count1=0,count2=0;
	String uname;
	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtFileno;
	private JTextField txtFname;
	private JTextField txtMname;
	private JTextField txtLname;
	private JTextField txtGname;
	private JTextField txtEmail;
	private JTextField txtLine1;
	private JTextField txtLine2;
	private JTextField txtMobileno;
	private JTextField txtLandmark;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtDistrict;
	private JTextField txtPincode;
	private JTextField txtPincodeP;
	private JTextField txtStateP;
	private JTextField txtCityP;
	private JTextField txtDistrictP;
	private JTextField txtLandmarkP;
	private JTextField txtMobilenoP;
	private JTextField txtLine2P;
	private JTextField txtLine1P;
	private JTextField txtIntroac1;
	private JTextField txtIntroac2;
	private JTextField txtFnameN;
	private JTextField txtMnameN;
	private JTextField txtLnameN;
	private JTextField txtRelation;
	private TextArea txtArea;
	private JComboBox comboDD;
	private JComboBox comboMM;
	private JComboBox comboYYYY;
	private JComboBox comboGender;
	private JComboBox comboMarital;
	private JComboBox comboCategory;
	private JComboBox comboDDN;
	private JComboBox comboMMN;
	private JComboBox comboYYYYN;
	private JComboBox comboGenderN;
	private JLabel lblIntro1name; 
	private JLabel lblIntro2name;
	private JRadioButton rdbtnFather;
	private JRadioButton rdbtnHusband;
	private ButtonGroup btngrp;
	private JButton btnSaveBasic;
	private JButton btnResetBasic;
	private JButton btnCancelBasic;
	private JButton btnSaveAddress;
	private JButton btnResetAddress;
	private JButton btnCancelAddress; 
	private JTabbedPane tabbedPane;
	private JPanel panel_1;
	
	java.util.Date datet= Calendar.getInstance().getTime();
    DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
    DateFormat formattime= new SimpleDateFormat("hh:mm:ssa");
	String today = format.format(datet);
	String time = formattime.format(datet);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addmember frame = new addmember(null);
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
	public addmember(String username) {
		uname=username;
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(new Dimension(1364,720));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(91, 69, 1153, 557);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Basic Detail", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblBasicDetail = new JLabel("BASIC DETAIL");
		lblBasicDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasicDetail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBasicDetail.setBounds(108, 41, 918, 24);
		lblBasicDetail.setBackground(Color.ORANGE);
		lblBasicDetail.setOpaque(true);
		panel.add(lblBasicDetail);
		
		JLabel lblAccountNo = new JLabel("A/C NO");
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountNo.setBounds(213, 101, 74, 24);
		panel.add(lblAccountNo);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(461, 101, 56, 24);
		panel.add(lblDate);
		
		JLabel lblFileNo = new JLabel("File No");
		lblFileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFileNo.setBounds(693, 101, 63, 24);
		panel.add(lblFileNo);
		
		txtFileno = new JTextField();
		txtFileno.setBounds(785, 101, 134, 22);
		panel.add(txtFileno);
		txtFileno.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(213, 149, 74, 24);
		panel.add(lblName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstName.setBounds(264, 178, 74, 24);
		panel.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMiddleName.setBounds(514, 178, 93, 24);
		panel.add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLastName.setBounds(732, 178, 74, 24);
		panel.add(lblLastName);
		
		txtFname = new JTextField();
		txtFname.setBounds(264, 209, 187, 20);
		panel.add(txtFname);
		txtFname.setColumns(10);
		
		txtMname = new JTextField();
		txtMname.setColumns(10);
		txtMname.setBounds(514, 209, 169, 20);
		panel.add(txtMname);
		
		txtLname = new JTextField();
		txtLname.setColumns(10);
		txtLname.setBounds(732, 209, 187, 20);
		panel.add(txtLname);
		
		//Auto increment account Number
		int count =1;
		String sql=("select acno from memberdetail");
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				count+=1;
			}
		} catch (Exception e) { JOptionPane.showMessageDialog(null, e);}
		Integer acc=null;
		if (count<=100000){
			acc=100000+count;
		}
		else{
			JOptionPane.showMessageDialog(null, "Acount Exceed to 100000. Meet to Software developer");
		}
		accountno=acc;
		String accstr = accountno.toString();
		
		JLabel lblacno = new JLabel(accstr);
		lblacno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblacno.setBounds(317, 101, 134, 21);
		panel.add(lblacno);
		
		JLabel label = new JLabel(today);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(516, 101, 93, 21);
		panel.add(label);
		
		rdbtnFather = new JRadioButton("Father Name");
		rdbtnFather.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnFather.setBounds(213, 260, 141, 27);
		panel.add(rdbtnFather);
		
		rdbtnHusband = new JRadioButton("Husband Name");
		rdbtnHusband.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnHusband.setBounds(370, 260, 141, 27);
		panel.add(rdbtnHusband);
		
		btngrp = new ButtonGroup();
		btngrp.add(rdbtnFather);
		btngrp.add(rdbtnHusband);
		
		txtGname = new JTextField();
		txtGname.setColumns(10);
		txtGname.setBounds(557, 264, 362, 22);
		panel.add(txtGname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(213, 318, 63, 24);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(290, 321, 344, 22);
		panel.add(txtEmail);
		
		JLabel lblDOB = new JLabel("D O B");
		lblDOB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDOB.setBounds(671, 318, 44, 24);
		panel.add(lblDOB);
		
		String dd[]={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		comboDD = new JComboBox(dd);
		comboDD.setBounds(726, 315, 49, 27);
		panel.add(comboDD);
		
		String mm[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
		comboMM = new JComboBox(mm);
		comboMM.setBounds(785, 315, 50, 28);
		panel.add(comboMM);
		
		comboYYYY = new JComboBox(new Object[]{});
		for(int i=2019; i>=(2019-100); i--)
        {
        	comboYYYY.addItem(i);
        }
		comboYYYY.setBounds(845, 316, 74, 27);
		panel.add(comboYYYY);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(213, 372, 68, 24);
		panel.add(lblGender);
		
		JLabel lblMa = new JLabel("Marital Status");
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMa.setBounds(435, 372, 106, 24);
		panel.add(lblMa);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCategory.setBounds(732, 372, 68, 24);
		panel.add(lblCategory);
		
		comboGender = new JComboBox();
		comboGender.addItem("Male");
		comboGender.addItem("Female");
		comboGender.setBounds(286, 376, 87, 20);
		panel.add(comboGender);
		
		comboMarital = new JComboBox();
		comboMarital.addItem("Married");
		comboMarital.addItem("Unmarried");
		comboMarital.setBounds(561, 376, 106, 20);
		panel.add(comboMarital);
		
		comboCategory = new JComboBox();
		comboCategory.addItem("GEN");
		comboCategory.addItem("OBC");
		comboCategory.addItem("SC/ST");
		comboCategory.setBounds(832, 376, 87, 20);
		panel.add(comboCategory);
		
		btnCancelBasic = new JButton("Cancel");
		btnCancelBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelBasic.setBounds(836, 439, 89, 23);
		panel.add(btnCancelBasic);
		
		btnResetBasic = new JButton("Reset");
		btnResetBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetBasicDetail();
			}
		});
		btnResetBasic.setBounds(693, 439, 89, 23);
		panel.add(btnResetBasic);
		
		btnSaveBasic = new JButton("Save & Next");
		btnSaveBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveBasicDetail();
			}
		});
		btnSaveBasic.setBounds(531, 439, 106, 23);
		panel.add(btnSaveBasic);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Address Detail", null, panel_1, null);
		tabbedPane.setEnabledAt(1, false);
		panel_1.setLayout(null);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setOpaque(true);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBackground(Color.ORANGE);
		lblAddress.setBounds(96, 42, 954, 23);
		panel_1.add(lblAddress);
		
		JLabel lblCurrentAddress = new JLabel("Current Address");
		lblCurrentAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentAddress.setBounds(129, 85, 127, 23);
		panel_1.add(lblCurrentAddress);
		
		JLabel lblLine = new JLabel("Line 1");
		lblLine.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLine.setBounds(129, 137, 60, 23);
		panel_1.add(lblLine);
		
		JLabel lblLine_1 = new JLabel("Line 2");
		lblLine_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLine_1.setBounds(129, 181, 60, 23);
		panel_1.add(lblLine_1);
		
		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMobileNo.setBounds(129, 225, 92, 23);
		panel_1.add(lblMobileNo);
		
		JLabel lblLandmark = new JLabel("LandMark");
		lblLandmark.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLandmark.setBounds(129, 273, 72, 23);
		panel_1.add(lblLandmark);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCity.setBounds(129, 323, 44, 23);
		panel_1.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblState.setBounds(129, 376, 44, 23);
		panel_1.add(lblState);
		
		JLabel label_1 = new JLabel("");
		label_1.setBackground(Color.GRAY);
		label_1.setOpaque(true);
		label_1.setBounds(567, 76, 10, 330);
		panel_1.add(label_1);
		
		txtLine1 = new JTextField();
		txtLine1.setBounds(231, 139, 298, 23);
		panel_1.add(txtLine1);
		txtLine1.setColumns(10);
		
		txtLine2 = new JTextField();
		txtLine2.setColumns(10);
		txtLine2.setBounds(231, 181, 298, 23);
		panel_1.add(txtLine2);
		
		txtMobileno = new JTextField();
		txtMobileno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
					txtMobileno.setEditable(true);
				} else {
					txtMobileno.setEditable(false);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "* Enter only numeric digits(0-9) ");
					txtMobileno.setEditable(true);
				}
			}
		});
		txtMobileno.setColumns(10);
		txtMobileno.setBounds(231, 225, 298, 23);
		panel_1.add(txtMobileno);
		
		txtLandmark = new JTextField();
		txtLandmark.setColumns(10);
		txtLandmark.setBounds(231, 273, 298, 23);
		panel_1.add(txtLandmark);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(183, 325, 121, 23);
		panel_1.add(txtCity);
		
		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistrict.setBounds(340, 323, 48, 23);
		panel_1.add(lblDistrict);
		
		JLabel lblPincode = new JLabel("Pincode");
		lblPincode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPincode.setBounds(340, 376, 60, 23);
		panel_1.add(lblPincode);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(183, 378, 121, 23);
		panel_1.add(txtState);
		
		txtDistrict = new JTextField();
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(408, 325, 121, 23);
		panel_1.add(txtDistrict);
		
		txtPincode = new JTextField();
		txtPincode.setColumns(10);
		txtPincode.setBounds(410, 376, 121, 23);
		panel_1.add(txtPincode);
		
		txtPincodeP = new JTextField();
		txtPincodeP.setColumns(10);
		txtPincodeP.setBounds(899, 376, 121, 23);
		panel_1.add(txtPincodeP);
		
		JLabel label_2 = new JLabel("Pincode");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(829, 376, 60, 23);
		panel_1.add(label_2);
		
		txtStateP = new JTextField();
		txtStateP.setColumns(10);
		txtStateP.setBounds(672, 378, 121, 23);
		panel_1.add(txtStateP);
		
		JLabel label_3 = new JLabel("State");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(618, 376, 44, 23);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("City");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(618, 323, 44, 23);
		panel_1.add(label_4);
		
		txtCityP = new JTextField();
		txtCityP.setColumns(10);
		txtCityP.setBounds(672, 325, 121, 23);
		panel_1.add(txtCityP);
		
		JLabel lblDistrict_1 = new JLabel("District");
		lblDistrict_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistrict_1.setBounds(829, 323, 60, 23);
		panel_1.add(lblDistrict_1);
		
		txtDistrictP = new JTextField();
		txtDistrictP.setColumns(10);
		txtDistrictP.setBounds(897, 325, 121, 23);
		panel_1.add(txtDistrictP);
		
		txtLandmarkP = new JTextField();
		txtLandmarkP.setColumns(10);
		txtLandmarkP.setBounds(720, 273, 298, 23);
		panel_1.add(txtLandmarkP);
		
		JLabel label_6 = new JLabel("LandMark");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(618, 273, 72, 23);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("Mobile No.");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setBounds(618, 225, 92, 23);
		panel_1.add(label_7);
		
		txtMobilenoP = new JTextField();
		txtMobilenoP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
					txtMobilenoP.setEditable(true);
				} else {
					txtMobilenoP.setEditable(false);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "* Enter only numeric digits(0-9) ");
					txtMobilenoP.setEditable(true);
				}
			}
		});
		txtMobilenoP.setColumns(10);
		txtMobilenoP.setBounds(720, 225, 298, 23);
		panel_1.add(txtMobilenoP);
		
		txtLine2P = new JTextField();
		txtLine2P.setColumns(10);
		txtLine2P.setBounds(720, 181, 298, 23);
		panel_1.add(txtLine2P);
		
		JLabel label_8 = new JLabel("Line 2");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_8.setBounds(618, 181, 60, 23);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("Line 1");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_9.setBounds(618, 137, 60, 23);
		panel_1.add(label_9);
		
		txtLine1P = new JTextField();
		txtLine1P.setColumns(10);
		txtLine1P.setBounds(720, 139, 298, 23);
		panel_1.add(txtLine1P);
		
		JCheckBox chckbxAddressP = new JCheckBox("Permanent Address");
		chckbxAddressP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAddressP.isSelected()){
					txtLine1P.setText(txtLine1.getText());
					txtLine2P.setText(txtLine2.getText());
					txtMobilenoP.setText(txtMobileno.getText());
					txtLandmarkP.setText(txtLandmark.getText());
					txtCityP.setText(txtCity.getText());
					txtDistrictP.setText(txtDistrict.getText());
					txtStateP.setText(txtState.getText());
					txtPincodeP.setText(txtPincode.getText());	
				}
				else{
					txtLine1P.setText("");
					txtLine2P.setText("");
					txtMobilenoP.setText("");
					txtLandmarkP.setText("");
					txtCityP.setText("");
					txtDistrictP.setText("");
					txtStateP.setText("");
					txtPincodeP.setText("");
				}
			}
		});
		chckbxAddressP.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxAddressP.setBounds(619, 87, 184, 23);
		panel_1.add(chckbxAddressP);
		
		btnCancelAddress = new JButton("Cancel");
		btnCancelAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancelAddress.setBounds(927, 449, 89, 23);
		panel_1.add(btnCancelAddress);
		
		btnResetAddress = new JButton("Reset");
		btnResetAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAddressDetail();
			}
		});
		btnResetAddress.setBounds(796, 449, 89, 23);
		panel_1.add(btnResetAddress);
		
		btnSaveAddress = new JButton("Save & Next");
		btnSaveAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAddressDetail();
			}
		});
		btnSaveAddress.setBounds(640, 449, 112, 23);
		panel_1.add(btnSaveAddress);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Other Detail", null, panel_2, null);
		tabbedPane.setEnabledAt(2, false);
		panel_2.setLayout(null);
		
		JLabel lblAcIntroducer = new JLabel("A/C INTRODUCER");
		lblAcIntroducer.setOpaque(true);
		lblAcIntroducer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcIntroducer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAcIntroducer.setBackground(Color.ORANGE);
		lblAcIntroducer.setBounds(98, 47, 954, 23);
		panel_2.add(lblAcIntroducer);
		
		JLabel lblNewLabel_1 = new JLabel("Introducer 1st A/C No.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(167, 103, 187, 23);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblIntroducerndAc = new JLabel("Introducer 2nd A/C No.");
		lblIntroducerndAc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIntroducerndAc.setBounds(167, 142, 187, 23);
		panel_2.add(lblIntroducerndAc);
		
		JLabel lblNomineeDetail = new JLabel("NOMINEE DETAIL");
		lblNomineeDetail.setOpaque(true);
		lblNomineeDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomineeDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomineeDetail.setBackground(Color.ORANGE);
		lblNomineeDetail.setBounds(98, 199, 954, 23);
		panel_2.add(lblNomineeDetail);
		
		txtIntroac1 = new JTextField();
		txtIntroac1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
					txtIntroac1.setEditable(true);
				} else {
					txtIntroac1.setEditable(false);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "* Enter only numeric digits(0-9) ");
					txtIntroac1.setEditable(true);
				}
				Integer acno=Integer.valueOf(txtIntroac1.getText());
				if(acno>200000){
					txtIntroac1.setEditable(false);
					JOptionPane.showMessageDialog(null, "Please Enter valid Account No");
					txtIntroac1.setText("");
					txtIntroac1.setEditable(true);
				}
				if(acno >= 100000 && acno<=199999){
					introducer1();	
				}
				

				String sql=("select * from memberdetail");
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
						if(txtIntroac1.getText().equals(rs.getString("acno"))){
							count1=1;
						}
					}
					if (count1==0 && acno >= 100000 && acno<=199999){
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtIntroac1.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtIntroac1.setBounds(377, 106, 202, 20);
		panel_2.add(txtIntroac1);
		txtIntroac1.setColumns(10);
		
		txtIntroac2 = new JTextField();
		txtIntroac2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar()==8 || e.getKeyChar()==10) {
					txtIntroac2.setEditable(true);
				} else {
					txtIntroac2.setEditable(false);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "* Enter only numeric digits(0-9) ");
					txtIntroac2.setEditable(true);
				}
				Integer acno=Integer.valueOf(txtIntroac2.getText());
				if(acno>200000){
					txtIntroac2.setEditable(false);
					JOptionPane.showMessageDialog(null, "Please Enter valid Account No");
					txtIntroac2.setText("");
					txtIntroac2.setEditable(true);
				}
				if(acno >= 100000 && acno<=199999){
					introducer2();	
				}
				

				String sql=("select * from memberdetail");
				try {
					Statement stmt=dbc.conmethod();
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
						if(txtIntroac2.getText().equals(rs.getString("acno"))){
							count2=1;
						}
					}
					if (count2==0 && acno >= 100000 && acno<=199999){
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtIntroac2.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtIntroac2.setColumns(10);
		txtIntroac2.setBounds(377, 145, 202, 20);
		panel_2.add(txtIntroac2);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName_1.setBounds(636, 103, 73, 23);
		panel_2.add(lblName_1);
		
		JLabel label_11 = new JLabel("Name");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_11.setBounds(636, 142, 73, 23);
		panel_2.add(label_11);
		
		lblIntro1name = new JLabel();
		lblIntro1name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntro1name.setBounds(739, 103, 181, 20);
		panel_2.add(lblIntro1name);
		
		lblIntro2name= new JLabel();
		lblIntro2name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntro2name.setBounds(739, 143, 181, 20);
		panel_2.add(lblIntro2name);
		
		JLabel lblFirstName_1 = new JLabel("First Name");
		lblFirstName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstName_1.setBounds(167, 255, 100, 23);
		panel_2.add(lblFirstName_1);
		
		JLabel lblMiddleName_1 = new JLabel("Middle Name");
		lblMiddleName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMiddleName_1.setBounds(458, 255, 100, 23);
		panel_2.add(lblMiddleName_1);
		
		JLabel lblLastName_1 = new JLabel("Last Name");
		lblLastName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastName_1.setBounds(750, 255, 100, 23);
		panel_2.add(lblLastName_1);
		
		txtFnameN = new JTextField();
		txtFnameN.setColumns(10);
		txtFnameN.setBounds(167, 289, 222, 20);
		panel_2.add(txtFnameN);
		
		txtMnameN = new JTextField();
		txtMnameN.setColumns(10);
		txtMnameN.setBounds(458, 289, 222, 20);
		panel_2.add(txtMnameN);
		
		txtLnameN = new JTextField();
		txtLnameN.setColumns(10);
		txtLnameN.setBounds(750, 289, 231, 20);
		panel_2.add(txtLnameN);
		
		JLabel lblRelationWithAccount = new JLabel("Relation with Account Holder");
		lblRelationWithAccount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRelationWithAccount.setBounds(167, 336, 222, 23);
		panel_2.add(lblRelationWithAccount);
		
		txtRelation = new JTextField();
		txtRelation.setColumns(10);
		txtRelation.setBounds(422, 339, 222, 20);
		panel_2.add(txtRelation);
		
		JLabel lblDOB_1 = new JLabel("D O B");
		lblDOB_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDOB_1.setBounds(750, 336, 52, 23);
		panel_2.add(lblDOB_1);
		
		comboDDN = new JComboBox(dd);
		comboDDN.setBounds(812, 339, 40, 20);
		panel_2.add(comboDDN);
		
		comboMMN = new JComboBox(mm);
		comboMMN.setBounds(862, 339, 40, 20);
		panel_2.add(comboMMN);
		
		comboYYYYN = new JComboBox();
		for(int j=2019; j>=(2019-100); j--)
        {
        	comboYYYYN.addItem(j);
        }
		comboYYYYN.setBounds(912, 339, 69, 20);
		panel_2.add(comboYYYYN);
		
		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress_1.setBounds(167, 392, 73, 23);
		panel_2.add(lblAddress_1);
		
		txtArea = new TextArea();
		txtArea.setBounds(250, 392, 246, 76);
		panel_2.add(txtArea);
		
		JLabel lblGender_1 = new JLabel("Gender");
		lblGender_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender_1.setBounds(545, 398, 69, 23);
		panel_2.add(lblGender_1);
		
		comboGenderN = new JComboBox();
		comboGenderN.addItem("Male");
		comboGenderN.addItem("Female");
		comboGenderN.setBounds(657, 401, 69, 20);
		panel_2.add(comboGenderN);
		
		JButton btnCancel_2 = new JButton("Cancel");
		btnCancel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel_2.setBounds(892, 469, 89, 23);
		panel_2.add(btnCancel_2);
		
		JButton btnReset_2 = new JButton("Reset");
		btnReset_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetOtherDetails();
			}
		});
		btnReset_2.setBounds(775, 469, 89, 23);
		panel_2.add(btnReset_2);
		
		JButton btnCreateAc = new JButton("Create A/C");
		btnCreateAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveOtherDetails();
			}
		});
		btnCreateAc.setBounds(636, 469, 108, 23);
		panel_2.add(btnCreateAc);
	}
	private void saveBasicDetail()
	{
 		String FileNumber,FirstName,MiddleName,LastName,GType="",GName,Email,Maritalstatus,DateOfBirth,Gen,Cat,sql1;
 		FileNumber =txtFileno.getText();
 		FirstName =txtFname.getText();
 		MiddleName = txtMname.getText();
 		LastName =txtLname.getText();
 		Email= txtEmail.getText();
 		Maritalstatus=comboMarital.getSelectedItem().toString();
 		if(rdbtnFather.isSelected())
 			GType = "Father"; 
 		else if(rdbtnHusband.isSelected()) 
 			GType = "Husband";
 		else JOptionPane.showMessageDialog(null, "Select Gardian Name");
 		GName =txtGname.getText();
 		DateOfBirth = comboYYYY.getSelectedItem().toString() +"/"+ comboMM.getSelectedItem().toString() +"/"+ comboDD.getSelectedItem().toString();
 		Gen = comboGender.getSelectedItem().toString();
 		Cat = comboCategory.getSelectedItem().toString();
 		
 		sql1="INSERT INTO sss.memberdetail(acno,fileno,fname,mname,lname,gtype,gname,email,maritalstatus,dob,gender,category,openedby) values('"+accountno+"','"+FileNumber+"','"+FirstName+"','"+MiddleName+"','"+LastName+"','"+GType+"','"+GName+"','"+Email+"','"+Maritalstatus+"','"+DateOfBirth+"','"+Gen+"','"+Cat+"','"+uname+"')";
 		if (accountno.equals("") || FileNumber.equals("") || FirstName.equals("") || GType.equals("") || DateOfBirth.equals("") || Gen.equals("") || Cat.equals("")  ){
			JOptionPane.showMessageDialog(null, "Fill Requried Data");
		}else
		{
			try {
				Statement stmt=dbc.conmethod();
				stmt.execute(sql1);
        		resetBasicDetail();
        		tabbedPane.setSelectedIndex(1);
				tabbedPane.setEnabledAt(0, false);
				tabbedPane.setEnabledAt(1, true);
				JOptionPane.showMessageDialog(null, "Basic Details Stored");
				stmt.close();
			}
			catch(java.sql.SQLIntegrityConstraintViolationException e){
				resetBasicDetail();
				JOptionPane.showInternalMessageDialog(null, "Duplicate Data");
			}
			catch (SQLException e){JOptionPane.showMessageDialog(null, e); } 
			catch(java.lang.NullPointerException ex){ JOptionPane.showMessageDialog(null, ex); }}
	}
	private void resetBasicDetail()
	{
		txtFileno.setText("");
		txtFname.setText("");
		txtMname.setText("");
		txtLname.setText("");
		txtGname.setText("");
		txtEmail.setText("");
		comboDD.setSelectedIndex(0);
		comboMM.setSelectedIndex(0);
		comboYYYY.setSelectedIndex(0);
		comboCategory.setSelectedIndex(0);
		comboGender.setSelectedIndex(0);
		btngrp.clearSelection();
		txtFileno.requestFocus();
	}
	
	private void saveAddressDetail(){
		String Line1,Line2,Mobile,Lmark,City,Dist,State,Pin,Line1p,Line2p,Mobilep,Lmarkp,Cityp,Distp,Statep,Pinp,sql2;
		Line1=txtLine1.getText();
		Line2=txtLine2.getText();
		Mobile=txtMobileno.getText();
		Lmark=txtLandmark.getText();
		City= txtCity.getText();
		Dist=txtDistrict.getText();
		State=txtState.getText();
		Pin=txtPincode.getText();
		Line1p=txtLine1P.getText();
		Line2p=txtLine2P.getText();
		Mobilep=txtMobilenoP.getText();
		Lmarkp=txtLandmarkP.getText();
		Cityp=txtCityP.getText();
		Distp=txtDistrictP.getText();
		Statep=txtStateP.getText();
		Pinp=txtPincodeP.getText();
		
		sql2="UPDATE sss.memberdetail SET line1='"+ Line1 +"', line2='"+ Line2 +"', mobileno='" +Mobile+ "', landmark='"+Lmark+"', city='"+City+"', distric='"+Dist+"', state='"+State+"', pincode='"+Pin+"',line1p='"+ Line1p +"', line2p='"+ Line2p +"', mobilenop='" +Mobilep+ "', landmarkp='"+Lmarkp+"', cityp='"+Cityp+"', districp='"+Distp+"', statep='"+Statep+"', pincodep='"+Pinp+"' WHERE acno='"+accountno+"'";
		if (Line1.equals("") ||  Mobile.equals("") || Lmark.equals("") || City.equals("") || Dist.equals("") || State.equals("") || Pin.equals("") || Line1p.equals("") ||  Mobilep.equals("") || Lmarkp.equals("") || Cityp.equals("") || Distp.equals("") || Statep.equals("") || Pinp.equals("") ){
			JOptionPane.showMessageDialog(null, "Fill Required Data");
		}else
		{
			try {
			Statement stmt=dbc.conmethod();
			String mobno=txtMobileno.getText();
			String mobnop=txtMobilenoP.getText();
			if(mobno.length() != 10 || mobnop.length() != 10){
				JOptionPane.showMessageDialog(null, "Please Enter the 10 digit Mobile no");
			}else{
				stmt.execute(sql2);
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setEnabledAt(2, true);
				resetAddressDetail();
				tabbedPane.setSelectedIndex(2);
				JOptionPane.showMessageDialog(null, "Address Detais Stored");
			}
		}
		catch(java.sql.SQLIntegrityConstraintViolationException e){
			JOptionPane.showInternalMessageDialog(null, "Duplicate Data");
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
		}
	}
	
	private void resetAddressDetail() {
		txtLine1.setText("");
		txtLine2.setText("");
		txtMobileno.setText("");
		txtLandmark.setText("");
		txtCity.setText("");
		txtDistrict.setText("");
		txtState.setText("");
		txtPincode.setText("");
		txtLine1P.setText("");
		txtLine2P.setText("");
		txtMobilenoP.setText("");
		txtLandmarkP.setText("");
		txtCityP.setText("");
		txtDistrictP.setText("");
		txtStateP.setText("");
		txtPincodeP.setText("");
		txtLine1.requestFocus(true);
	}
	
	private void saveOtherDetails(){
		String Intro1, Intro2, FirstNameN, MiddleNameN, LastNameN, Relation, DateOfBirthN, AddressN, GenderN, sql3;
		Intro1=txtIntroac1.getText();
		Intro2=txtIntroac2.getText();
		FirstNameN=txtFnameN.getText();
		MiddleNameN=txtMnameN.getText();
		LastNameN=txtLnameN.getText();
		Relation=txtRelation.getText();
		DateOfBirthN= comboYYYYN.getSelectedItem().toString()+"/"+comboMMN.getSelectedItem().toString()+"/"+comboDDN.getSelectedItem().toString();
		AddressN=txtArea.getText();
		GenderN=comboGenderN.getSelectedItem().toString();
		
		sql3 = "UPDATE `sss`.`memberdetail` SET `introac1`='"+Intro1+"', `introac2`='"+Intro2+"', `fnamen`='"+FirstNameN+"', `mnamen`='"+MiddleNameN+"', `lnamen`='"+LastNameN+"', `relation`='"+Relation+"', `dobn`='"+DateOfBirthN+"', `addressn`='"+AddressN+"', `gendern`='"+GenderN+"', `openeddate`=now(), `status`='Active' WHERE `acno`='"+accountno+"'";
		if (Intro1.equals("") ||  Intro2.equals("") || FirstNameN.equals("") || Relation.equals("") || DateOfBirthN.equals("") || AddressN.equals("") || GenderN.equals("") ){
			JOptionPane.showMessageDialog(null, "Fill Required Detail");
		}else if (count1==0 ){
			JOptionPane.showMessageDialog(null, "Please Enter Correct First Introducer");
			txtIntroac1.requestFocus(true);
		}else if (count2==0 ){
			JOptionPane.showMessageDialog(null, "Please Enter Correct Second Introducer");
			txtIntroac2.requestFocus(true);
		}
		else
		{
			try {
			Statement stmt=dbc.conmethod();
			stmt.execute(sql3);
			resetOtherDetails();
			JOptionPane.showMessageDialog(null, "Account Created. Account No is : "+accountno);
			dispose();
		}
		catch(java.sql.SQLIntegrityConstraintViolationException e){
			JOptionPane.showInternalMessageDialog(null, "Duplicate Data");
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
		}
		
	}
	private void resetOtherDetails() {
		txtIntroac1.setText("");
		txtIntroac2.setText("");
		txtFname.setText("");
		txtMname.setText("");
		txtLname.setText("");
		txtRelation.setText("");
		txtArea.setText("");
		comboGenderN.setSelectedIndex(0);
		comboDDN.setSelectedIndex(0);
		comboMMN.setSelectedIndex(0);
		comboYYYYN.setSelectedIndex(0);
		lblIntro1name.setText("");
		lblIntro2name.setText("");		
	}
	
	private void cancel(){
		String sql4;
		sql4="DELETE FROM memberdetail WHERE acno='"+accountno+"'";
		try {
			Statement stmt=dbc.conmethod();
			stmt.execute(sql4);
			dispose();
		} catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
	}
	private void introducer1(){
		String sql="SELECT * FROM sss.memberdetail where acno="+ txtIntroac1.getText()+"";
		String status="", name=null;
		String intro1=txtIntroac1.getText();
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				status=rs.getString("status");
				name=rs.getString("fname") +" "+rs.getString("mname")+" "+rs.getString("lname");
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
		if(status.equals("Active")){
			lblIntro1name.setText(name);
		}else if(Integer.valueOf(intro1)>100000 && Integer.valueOf(intro1)<999999  && status.equals("Deactivate")){ 
			JOptionPane.showMessageDialog(null,	"A/c is Deactivated. Please Insert Valid A/C no.");
			txtIntroac1.setText("");
			lblIntro1name.setText("");
		}
	}
	private void introducer2(){
		String sql="SELECT * FROM sss.memberdetail where acno="+ txtIntroac2.getText()+"";
		String status="", name="";
		String intro2=txtIntroac2.getText();
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				status=rs.getString("status");
				name=rs.getString("fname") +" "+rs.getString("mname")+" "+rs.getString("lname");
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
		if(status.equals("Active")){
			lblIntro2name.setText(name);
		}else if(Integer.valueOf(intro2)>100000 && Integer.valueOf(intro2)<999999 && status.equals("Deactivate")){ 
			JOptionPane.showMessageDialog(null,	"A/c is Deactivated. Please Insert Valid A/C no.");
			txtIntroac2.setText("");
			lblIntro2name.setText("");
		}
		if(txtIntroac1.getText().equals(txtIntroac2.getText())){
			JOptionPane.showMessageDialog(null,	"Similar to A/C 1, Please Enter the Other A/C no.");
			txtIntroac2.setText("");
			lblIntro2name.setText("");
		}
	}
}
