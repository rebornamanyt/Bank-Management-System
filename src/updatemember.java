import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class updatemember extends JFrame {

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
	private JLabel lblTxtDate;
	private JLabel lblTxtStatus;
	private JLabel lblTxtFileNo;
	private JComboBox comboGType;
	private JComboBox comboGender;
	private JComboBox comboMaritalStatus;
	private JComboBox comboCategory;
	private JTextField txtDOB;
	private JCheckBox chckbxAddressP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updatemember frame = new updatemember();
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
	public updatemember() {
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
					detail();	
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
						txtAcno.setEditable(false);
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtAcno.setEditable(true);
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
		lblTxtDate.setBounds(555, 64, 199, 19);
		contentPane.add(lblTxtDate);
		
		JLabel lblAccountOpenBy = new JLabel("Account Open By");
		lblAccountOpenBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountOpenBy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccountOpenBy.setBounds(764, 64, 132, 19);
		contentPane.add(lblAccountOpenBy);
		
		JLabel lblFileNo = new JLabel("File No");
		lblFileNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFileNo.setBounds(1016, 64, 96, 19);
		contentPane.add(lblFileNo);
		
		lblTxtFileNo = new JLabel();
		lblTxtFileNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTxtFileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
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
		txtFName.setBounds(111, 159, 159, 20);
		contentPane.add(txtFName);
		txtFName.setColumns(10);
		
		txtMName = new JTextField();
		txtMName.setColumns(10);
		txtMName.setBounds(306, 159, 159, 20);
		contentPane.add(txtMName);
		
		txtLName = new JTextField();
		txtLName.setColumns(10);
		txtLName.setBounds(508, 159, 159, 20);
		contentPane.add(txtLName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(89, 196, 51, 19);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
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
		txtGName.setDragEnabled(true);
		txtGName.setBounds(897, 159, 333, 20);
		contentPane.add(txtGName);
		txtGName.setColumns(10);
		
		JLabel lblDOB = new JLabel("D O B");
		lblDOB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDOB.setBounds(491, 196, 43, 19);
		contentPane.add(lblDOB);
		
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(700, 197, 62, 19);
		contentPane.add(lblGender);
		
		JLabel lblMatrialStatus = new JLabel("Marital Status");
		lblMatrialStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatrialStatus.setBounds(862, 197, 106, 19);
		contentPane.add(lblMatrialStatus);
		
		comboGender = new JComboBox();
		comboGender.addItem("Male");
		comboGender.addItem("Female");
		comboGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboGender.setBounds(772, 198, 72, 20);
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
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCity.setBounds(99, 361, 51, 19);
		contentPane.add(lblCity);
		
		txtLine1 = new JTextField();
		txtLine1.setColumns(10);
		txtLine1.setBounds(183, 300, 165, 20);
		contentPane.add(txtLine1);
		
		txtLine2 = new JTextField();
		txtLine2.setColumns(10);
		txtLine2.setBounds(455, 300, 169, 20);
		contentPane.add(txtLine2);
		
		txtMobile = new JTextField();
		txtMobile.setColumns(10);
		txtMobile.setBounds(183, 332, 165, 20);
		contentPane.add(txtMobile);
		
		txtLandmark = new JTextField();
		txtLandmark.setColumns(10);
		txtLandmark.setBounds(455, 333, 169, 20);
		contentPane.add(txtLandmark);
		
		txtCity = new JTextField();
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
		txtState.setColumns(10);
		txtState.setBounds(183, 393, 165, 20);
		contentPane.add(txtState);
		
		txtDistrict = new JTextField();
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(455, 363, 169, 20);
		contentPane.add(txtDistrict);
		
		txtPin = new JTextField();
		txtPin.setColumns(10);
		txtPin.setBounds(455, 393, 169, 20);
		contentPane.add(txtPin);
		
		txtLine1P = new JTextField();
		txtLine1P.setColumns(10);
		txtLine1P.setBounds(784, 300, 164, 20);
		contentPane.add(txtLine1P);
		
		JLabel label_2 = new JLabel("Line 1");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(700, 300, 51, 19);
		contentPane.add(label_2);
		
		JLabel label_4 = new JLabel("Mobile No");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(690, 331, 84, 19);
		contentPane.add(label_4);
		
		txtMobileP = new JTextField();
		txtMobileP.setColumns(10);
		txtMobileP.setBounds(783, 332, 165, 20);
		contentPane.add(txtMobileP);
		
		JLabel label_5 = new JLabel("Line 2");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(979, 300, 51, 19);
		contentPane.add(label_5);
		
		txtLine2P = new JTextField();
		txtLine2P.setColumns(10);
		txtLine2P.setBounds(1057, 299, 173, 20);
		contentPane.add(txtLine2P);
		
		txtLandmarkP = new JTextField();
		txtLandmarkP.setColumns(10);
		txtLandmarkP.setBounds(1057, 332, 173, 20);
		contentPane.add(txtLandmarkP);
		
		JLabel label_6 = new JLabel("LandMark");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(971, 330, 84, 19);
		contentPane.add(label_6);
		
		txtDistrictP = new JTextField();
		txtDistrictP.setColumns(10);
		txtDistrictP.setBounds(1057, 362, 173, 20);
		contentPane.add(txtDistrictP);
		
		txtPinP = new JTextField();
		txtPinP.setColumns(10);
		txtPinP.setBounds(1057, 392, 173, 20);
		contentPane.add(txtPinP);
		
		JLabel label_7 = new JLabel("Pincode");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(979, 391, 62, 19);
		contentPane.add(label_7);
		
		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDistrict.setBounds(979, 361, 68, 19);
		contentPane.add(lblDistrict);
		
		txtCityP = new JTextField();
		txtCityP.setColumns(10);
		txtCityP.setBounds(784, 362, 165, 20);
		contentPane.add(txtCityP);
		
		JLabel lblCity_1 = new JLabel("City");
		lblCity_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCity_1.setBounds(700, 361, 51, 19);
		contentPane.add(lblCity_1);
		
		JLabel label_10 = new JLabel("State");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_10.setBounds(700, 391, 51, 19);
		contentPane.add(label_10);
		
		txtStateP = new JTextField();
		txtStateP.setColumns(10);
		txtStateP.setBounds(783, 393, 165, 20);
		contentPane.add(txtStateP);
		
		chckbxAddressP = new JCheckBox("Same As Permanent Address");
		chckbxAddressP.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxAddressP.setBounds(677, 270, 237, 23);
		contentPane.add(chckbxAddressP);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(1141, 473, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				dispose();
			}
		});
		btnUpdate.setBounds(1016, 473, 89, 23);
		contentPane.add(btnUpdate);
		
		txtDOB = new JTextField();
		txtDOB.setColumns(10);
		txtDOB.setBounds(544, 197, 123, 20);
		contentPane.add(txtDOB);
	}
	
	private void detail(){
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
				lblTxtFileNo.setText(rs.getString("fileno"));
				txtFName.setText(rs.getString("fname"));
				txtLName.setText(rs.getString("lname"));
				txtMName.setText(rs.getString("mname"));
				if(rs.getString("gtype").equals("Father")){comboGType.setSelectedIndex(0);}
				else if(rs.getString("gtype").equals("Husband")){comboGType.setSelectedIndex(1);}
				txtGName.setText(rs.getString("gname"));
				txtEmail.setText(rs.getString("email"));
				txtDOB.setText(rs.getString("dob"));
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
				if(rs.getString("line1").equals(rs.getString("line1P")) && rs.getString("city").equals(rs.getString("cityP")))
				{
					chckbxAddressP.setSelected(true);
				}
			}
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	
	private void update(){
		String Accountno,FirstName,MiddleName,LastName,GType="",GName,DOB,Email,Maritalstatus,Gen,Cat,Line1,Line2,Mobile,Lmark,City,Dist,State,Pin,Line1p,Line2p,Mobilep,Lmarkp,Cityp,Distp,Statep,Pinp,sql;
 		Accountno=txtAcno.getText();
		FirstName =txtFName.getText();
 		MiddleName = txtMName.getText();
 		LastName =txtLName.getText();
 		Email= txtEmail.getText();
 		Maritalstatus=comboMaritalStatus.getSelectedItem().toString();
 		GType=comboGType.getSelectedItem().toString();
 		GName =txtGName.getText();
 		DOB = txtDOB.getText();
 		Gen = comboGender.getSelectedItem().toString();
 		Cat = comboCategory.getSelectedItem().toString();
		Line1=txtLine1.getText();
		Line2=txtLine2.getText();
		Mobile=txtMobile.getText();
		Lmark=txtLandmark.getText();
		City= txtCity.getText();
		Dist=txtDistrict.getText();
		State=txtState.getText();
		Pin=txtPin.getText();
		Line1p=txtLine1P.getText();
		Line2p=txtLine2P.getText();
		Mobilep=txtMobileP.getText();
		Lmarkp=txtLandmarkP.getText();
		Cityp=txtCityP.getText();
		Distp=txtDistrictP.getText();
		Statep=txtStateP.getText();
		Pinp=txtPinP.getText();
		
		sql="UPDATE sss.memberdetail SET fname='"+FirstName+"', mname='"+MiddleName+"', lname='"+LastName+"', gtype='"+ GType +"', gname='"+ GName +"', email='"+Email+"', maritalstatus='"+Maritalstatus+"', dob='"+DOB+"',gender='"+Gen+"', category='"+Cat+"', line1='"+Line1+"', line2='"+Line2+"', mobileno='" +Mobile+ "', landmark='"+Lmark+"', city='"+City+"', distric='"+Dist+"', state='"+State+"', pincode='"+Pin+"',line1p='"+ Line1p +"', line2p='"+ Line2p +"', mobilenop='" +Mobilep+ "', landmarkp='"+Lmarkp+"', cityp='"+Cityp+"', districp='"+Distp+"', statep='"+Statep+"', pincodep='"+Pinp+"' WHERE acno='"+Accountno+"'";
		if (FirstName.equals("") || MiddleName.equals("") || LastName.equals("") || Email.equals("") || DOB.equals("") || GName.equals("") || Line1.equals("") ||  Mobile.equals("") || Lmark.equals("") || City.equals("") || Dist.equals("") || State.equals("") || Pin.equals("") || Line1p.equals("") ||  Mobilep.equals("") || Lmarkp.equals("") || Cityp.equals("") || Distp.equals("") || Statep.equals("") || Pinp.equals("") ){
			JOptionPane.showMessageDialog(null, "Fill Required Data");
		}else
		{
			try {
			Statement stmt=dbc.conmethod();
			stmt.execute(sql);
			JOptionPane.showMessageDialog(null, "Detail is Updated");
		}
		catch(java.sql.SQLIntegrityConstraintViolationException e){
			JOptionPane.showInternalMessageDialog(null, "Duplicate Data");
		}
		catch (SQLException e){JOptionPane.showMessageDialog(null, e);} 
		}
	}
}
