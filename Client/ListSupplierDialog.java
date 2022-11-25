import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;




public class ListSupplierDialog extends JDialog implements ActionListener,ItemListener{
	JButton b1, b2;
        JTable table;
	DefaultTableModel model; 
	
	JLabel lblSupplierid;
	
	JComboBox combosupplierid;
         
   public ListSupplierDialog()
	{
		setSize(700,570);
		setLocation(70,80);
               	
		Color co=GeneralUtility.getColor();
		Color c = new Color(188,140,240);
	//	getContentPane().setBackground(c);
		
		
		
		JLabel	l1= GeneralUtility.getHeadingLabel(("LIST SUPPLIER DETAILS"));
			
		
		Font f=new Font("Times New Roman",Font.PLAIN ,20);
//		l1.setFont(f);
		///////////////////////////////
		JPanel panel = new JPanel();
		panel.setLayout(null);
		lblSupplierid = new JLabel("Select SupplierId");
		
//add(lblSupplierid);
combosupplierid = new JComboBox();


//        add(combosupplierid);
//        panel.add(lblSupplierid);
//        panel.add(combosupplierid);
//        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Product wise details of order"));
//        add(panel);
		///////////////////////////////////
		JPanel p = new JPanel();
		p.setLayout(null); //
		
		table = new JTable(model);
		 model = new DefaultTableModel();
		
		model.addColumn("Supplier ID");
		model.addColumn("Name");		
		model.addColumn("Address");
		model.addColumn("City");
		model.addColumn("Pincode");
		
                b1 = new JButton("Modify");
		b2 = new JButton("Delete");	
		
		
		

		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p.add(scrollPane);
		p.add(l1);
		p.add(b1);
		p.add(b2);
       p.add(lblSupplierid);
       p.add(combosupplierid);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Supplier details"));
		l1.setBounds(140,5,450,30);
		lblSupplierid.setBounds(140,50,150,25);
		combosupplierid.setBounds(260,50,150,25);
		b1.setBounds(10,500,100,30);
		b2.setBounds(110,500,100,30);
       // getContentPane().setLayout(null);
		add(p);
	//table.setBackground(c);
//		p.setBackground(co);
		scrollPane.setBounds(10, 100, 600, 300);
		
		
		
		//Registration
		b1.addActionListener(this);
		b2.addActionListener(this);
        combosupplierid.addItemListener(this);
		fillcombo();
	}
	
	 public void actionPerformed(ActionEvent ae)
	 	{
		            
                        if(ae.getSource()==b1)
		       {
				if(table.getSelectedRows().length==0)
				{
					JOptionPane.showMessageDialog(this,"Please select  one row to update." );
					return;
				}
                        	
                        	int x = table.getSelectedRow();
                        	Vector v = new Vector();
                        	for(int k=0;k<table.getColumnCount();k++)
                        	{
                        		
                        		v.addElement(table.getValueAt(x,k));
                        		
                        	}
                        	
                            ModifySupplierDialog m = new  ModifySupplierDialog(v);
		            m.setVisible(true);
		           this.setVisible(false);
		       }


	    
	   //  DELETE BUTTON CODDING  
          //----------------------------------------------------
	
			if(ae.getSource()==b2)
		{
				
	         deleterecords();
	         fillcombo();
	         showrecords();
		
		}
		
			 
	 	}
	 public void showrecords()
	 {
		 int x = table.getRowCount();
         for(int k=x-1; k>=0;k--)

         {
      	   model.removeRow(k);      	   
         }

                try {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    } 
	    catch (ClassNotFoundException ce) 
	    {
		System.out.println("Error in loading the class");
	    }

	try {
		Connection con = DriverManager.getConnection("jdbc:odbc:shop");
		
		
		PreparedStatement ps = con.prepareStatement("select * from supplier");
		
		if(combosupplierid.getSelectedIndex()>0)
		{
				 ps = con.prepareStatement("select * from supplier where supplierid = ?");
				 ps.setString(1, (String)combosupplierid.getSelectedItem());
		}
		
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Vector v = new Vector();
			v.add(rs.getString("supplierid"));
		 v.add(rs.getString("sname"));
		 v.add(rs.getString("saddress"));
		 v.add(rs.getString("scity"));				 
		 v.add(rs.getString("spincode"));
		 
		 model.addRow(v);
		}
		
		ps.close();
		con.close();

	    } 
	    catch (SQLException se) 
	    {
		se.printStackTrace();
		System.out.println("Error in loading the class");
	    }



	}
		 
	 
	 
	 
	 
	 public void deleterecords()
	 {
		 int []	a = table.getSelectedRows();
			if(table.getSelectedRows().length==0)
			{
				JOptionPane.showMessageDialog(this,"Please select alteast one row to remove" );
				return;
			}
			String b;
			for(int k=0;k<a.length;k++)
			{
				b = (String)model.getValueAt(a[k],0);
                try {
            		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            	    } 
            	    catch (ClassNotFoundException ce) 
            	    {
            		System.out.println("Error in loading the class");
            	    }

            	try {
            		Connection con = DriverManager.getConnection("jdbc:odbc:shop");
 		PreparedStatement ps = con.prepareStatement("delete from  supplier where supplierid=?");

            		ps.setString(1, b);
            		ps.executeUpdate();
            		           		
            		ps.close();
            		con.close();

            	    } 
            	    catch (SQLException se) 
            	    {
            		se.printStackTrace();
            		System.out.println("Error in loading the class");
            	    }

				
				
			}
			
            for(int k=a.length-1; k>=0;k--)

            {
         	   model.removeRow(a[k]);
         	   System.out.println("a[" + k+"] " + a[k]);
            }
				
				JOptionPane.showMessageDialog(this,"Record(s) records deleted successfully" );
					
	
		 
	 }
	 
	 public void itemStateChanged(ItemEvent ie)
	 {
		 showrecords();
	 }
	 
	 public void  	fillcombo()
	 	{
		ArrayList al =    DataBase.getSuplierList();
		combosupplierid.removeAllItems();
		combosupplierid.addItem("All Suppliers");
		for(int k=0;k<al.size();k++)
			combosupplierid.addItem( ((Supplier )al.get(k)).getSupplierid());
		
		showrecords();
		
	 	}

	 
	 
       public static void main(String args[])
	{       
		            ListSupplierDialog w = new ListSupplierDialog();
		            w.setVisible(true);
	}
  
}
