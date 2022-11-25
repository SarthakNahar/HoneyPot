import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.*;


 class ModifySupplierDialog extends JDialog implements ActionListener{
	JLabel x1, x2, x3,x4,x5;

	JTextField tsid, tsname, tsadd,tscity,tspin;

	JButton b1, b2;

	 public ModifySupplierDialog(Vector v)
	 {
	
		setTitle("MODIFY SUPPLIER");
		setLayout(null);
		setModal(true);
		setSize(540,500);
		setLocation(200,110);      
		Color co=new Color(20,200,140);
		setResizable(false);
		
	
		x1 = new JLabel("Supplier ID");
		x2 = new JLabel("Name");
		x3 = new JLabel("Address ");
                x4 = new JLabel("City");
		x5 = new JLabel("Pincode");

             	tsid = new JTextField(10);
		tsname = new JTextField(14);
		tsadd = new JTextField(14);
		tscity = new JTextField(14);
		tspin = new JTextField(14);
		tsid.setText((String)v.elementAt(0));
		tsname.setText((String)v.elementAt(1));
		tsadd.setText((String)v.elementAt(2));
		tscity.setText((String)v.elementAt(3));
		tspin.setText((String)v.elementAt(4));
		tsid.setEnabled(false);
		b1 = new JButton("Save");
		b2 = new JButton("Clear");
		
		
		Color col=new Color(20,200,140);
//		getContentPane().setBackground(col);
			
		
		JLabel	l1=new JLabel(" SUPPLIER DETAILS");
			l1.setBounds(130,30,450,30);
		add(l1);
		//Font f=new Font("Times New Roman",Font.PLAIN ,20);
		//l1.setFont(f);
                Font f=new Font("Imprint MT Shadow",Font.PLAIN,30);
		l1.setFont(f);
		co=new Color(200,20,60);
		l1.setForeground(co);
  
		x1.setBounds(110,100,200,25);
		tsid.setBounds(210,100,200,25);
		
		x2.setBounds(110,150,200,25);
		tsname.setBounds(210,150,200,25);
		
		x3.setBounds(110,200,200,25);
		tsadd.setBounds(210,200,200,25);
		
		x4.setBounds(110,250,200,25);
		tscity.setBounds(210,250,200,25);
		
		x5.setBounds(110,300,200,25);
		tspin.setBounds(210,300,200,25);
		
		b1.setBounds(210,350,80,30);
		b2.setBounds(330,350,80,30);
		
		
			

		add(x1);
		add(tsid);
		add(x2);
		add(tsname);
		add(x3);
		add(tsadd);
                add(x4);
		add(tscity);
                add(x5);
		add(tspin);

		add(b1);
		add(b2);
		
		
		//Registration
		b1.addActionListener(this);
		b2.addActionListener(this);
		setFont();
		

	}

				
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	
		if(ae.getSource()==b2)
		{
			tsid.setText("");
			tsname.setText("");
			tsadd.setText("");
               	        tscity.setText("");
		 	tspin.setText(""); 
		}
		if(ae.getSource()==b1)
		{
			if(tsid.getText().length()==0 || tsname.getText().length()==0 || tsadd.getText().length()==0 
					|| tscity.getText().length()==0 || tspin.getText().length()==0)
			 {
				 JOptionPane.showMessageDialog(this,"Please enter all the details");
				 return;
			 }
			//////////////////////////////////
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch (ClassNotFoundException ce) {
				System.out.println("Error in loading the class");
			}

			try {
				Connection con = DriverManager.getConnection("jdbc:odbc:shop");
				PreparedStatement ps = con.prepareStatement("update supplier set sname= ? , saddress=? , scity=? , spincode=? where supplierid=?");

				
				ps.setString(1, tsname.getText());
				ps.setString(2, tsadd.getText());
				ps.setString(3, tscity.getText());
				ps.setString(4, tspin.getText());
				ps.setString(5, tsid.getText());

				int x = ps.executeUpdate();
				if (x >= 1)
					JOptionPane.showMessageDialog(this,
							"Record updated successfully");
				else
					JOptionPane.showMessageDialog(this,
							"Record could not be updated");
				ps.close();
				con.close();

			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("Error in loading the class");
			}
			// /////////////////////////////////
			
		
		}
	
	
	
 }
	
 	public void setFont()
 	{
 		x1.setFont(GeneralUtility.getTextFont());
 		x2.setFont(GeneralUtility.getTextFont());
 		x3.setFont(GeneralUtility.getTextFont());
 		x4.setFont(GeneralUtility.getTextFont());
 		x5.setFont(GeneralUtility.getTextFont());
 		
 	}
}


