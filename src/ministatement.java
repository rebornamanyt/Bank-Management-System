import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ministatement extends JFrame {

	dbconnection dbc = new dbconnection();
	private JPanel contentPane;
	private JTextField txtAcNo;
	private JLabel lblTxtName;
	private JLabel lblTxtAddress;
	private JLabel lblTxtGName;
	private JLabel lblTxtAcBalance;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	Object[] row = new Object[7];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ministatement frame = new ministatement();
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
	public ministatement() {
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mini Statement");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 24, 664, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A/C No.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(48, 70, 76, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(48, 114, 54, 22);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(48, 160, 76, 22);
		contentPane.add(lblAddress);
		
		JLabel lblAcBalance = new JLabel("A/C Balance");
		lblAcBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAcBalance.setBounds(347, 160, 92, 22);
		contentPane.add(lblAcBalance);
		
		JLabel lblSho = new JLabel("S/H/O");
		lblSho.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSho.setBounds(347, 114, 76, 22);
		contentPane.add(lblSho);
		
		lblTxtName = new JLabel();
		lblTxtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtName.setBounds(124, 114, 157, 22);
		contentPane.add(lblTxtName);
		
		lblTxtAddress = new JLabel();
		lblTxtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAddress.setBounds(124, 160, 190, 22);
		contentPane.add(lblTxtAddress);
		
		lblTxtGName = new JLabel();
		lblTxtGName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtGName.setBounds(418, 114, 190, 22);
		contentPane.add(lblTxtGName);
		
		lblTxtAcBalance = new JLabel();
		lblTxtAcBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTxtAcBalance.setBounds(449, 160, 159, 22);
		contentPane.add(lblTxtAcBalance);
		
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
					model.getDataVector().removeAllElements();
					transactiondetail();
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
						model.getDataVector().removeAllElements();
						reset();
						JOptionPane.showMessageDialog(null, "A/C Not Found, Enter Valid A/C No.");
						txtAcNo.setEditable(true);
						txtAcNo.requestFocus(true);
					}
				} catch (Exception ex){ JOptionPane.showMessageDialog(null, ex);}
			}
		});
		txtAcNo.setBounds(125, 73, 157, 20);
		contentPane.add(txtAcNo);
		txtAcNo.setColumns(10);
		
		JLabel lblLastFiveDetails = new JLabel("Last Transaction Details");
		lblLastFiveDetails.setOpaque(true);
		lblLastFiveDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastFiveDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLastFiveDetails.setBackground(Color.ORANGE);
		lblLastFiveDetails.setBounds(0, 209, 664, 22);
		contentPane.add(lblLastFiveDetails);
		
		table = new JTable();
		table.setBounds(60, 281, 516, 198);
		contentPane.add(table);
		Object[] columns = {"ID","Particular","Amount","Date","Time","Total Amount","Done By"};
        table.setModel(model);
        model.setColumnIdentifiers(columns);
        table.setRowHeight(30);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(60, 281, 537, 198);
        contentPane.setLayout(null);
        contentPane.add(pane);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
	}
	private void transactiondetail(){
		String id=null, particular=null, amount=null, date=null,time=null,curramount=null,doneby=null;
		String sql1="SELECT * FROM sss.transaction where acno='"+ txtAcNo.getText() +"' ORDER BY date DESC";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql1);
			while (rs.next()){
				id = rs.getString("id");
				particular = rs.getString("particular");
				amount = rs.getString("amount");
				date = rs.getString("date");
				time = rs.getString("time");
				curramount = rs.getString("curentbalance");
				doneby = rs.getString("doneby");
				
				row[0]= id;
				row[1]= particular;
				row[2]= amount;
				row[3]= date;
				row[4]= time;
				row[5]= curramount;
				row[6]= doneby;
				
				model.addRow(row);
			}
			
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
		
	}
	private void detail(){
		String sql="SELECT * FROM sss.memberdetail where acno="+ txtAcNo.getText() +"";
		try {
			Statement stmt=dbc.conmethod();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()){
				lblTxtName.setText(rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname"));
				lblTxtGName.setText(rs.getString("gname"));
				lblTxtAddress.setText(rs.getString("line1")+", "+rs.getString("line2")+", "+rs.getString("city"));
				lblTxtAcBalance.setText(rs.getString("amount"));
				}
			}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
	}
	private void reset(){
		lblTxtName.setText("");
		lblTxtGName.setText("");
		lblTxtAddress.setText("");
		lblTxtAcBalance.setText("");
	}
}
