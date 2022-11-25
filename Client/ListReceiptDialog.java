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




public class ListReceiptDialog extends JDialog implements ActionListener,ItemListener{
	JButton b1, b2;
        JTable table;
	DefaultTableModel model; 
JLabel lblBillNo;
	
	JComboBox combobillno;
   public ListReceiptDialog()
	{
		setSize(700,470);
		setLocation(70,80);
               
		
		
               
		
		Color co=GeneralUtility.getColor();
//		getContentPane().setBackground(co);
		
		
		
		JLabel	l1= GeneralUtility.getHeadingLabel(("LIST OF RECEIPT DETAILS"));
			
		
		Font f=new Font("Times New Roman",Font.PLAIN ,20);
//		l1.setFont(f);
		JPanel p = new JPanel();
		p.setLayout(null); //
		
		lblBillNo = new JLabel("Select Bill No.");
		
//		add(lblSupplierid);
		combobillno = new JComboBox();
		
		table = new JTable(model);
		 model = new DefaultTableModel();
		
		model.addColumn("Receipt ID");
		model.addColumn("Bill No.");		
		model.addColumn("Amount");
		model.addColumn("Date");
		
		
                b1 = new JButton("Modify");
		b2 = new JButton("Delete");
               
		

		
		
		
		

		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p.add(scrollPane);
		p.add(l1);
		 p.add(lblBillNo);
	       p.add(combobillno);
	        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
	        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Receipts details"));
//		p.add(b1);
//		p.add(b2);
        
		l1.setBounds(140,5,450,30);
		lblBillNo.setBounds(140,50,150,25);
		combobillno.setBounds(260,50,150,25);
//		b1.setBounds(10,400,100,30);
//		b2.setBounds(110,400,100,30);
        
		add(p);
//		table.setBackground(co);
//		p.setBackground(co);
		scrollPane.setBounds(10, 100, 600, 300);
		
		
		
		//Registration
		b1.addActionListener(this);
		b2.addActionListener(this);
        
		 combobillno.addItemListener(this);
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
	         showrecords();
		
		}
		
			 
	 	}
	 
	 public void  	fillcombo()
	 	{
		ArrayList al =    DataBase.getReceiptList();
		combobillno.removeAllItems();
		combobillno.addItem("All Receipts");
		for(int k=0;k<al.size();k++)
			combobillno.addItem( ((Receipts )al.get(k)).getBillno());
		
		showrecords();
		
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
		PreparedStatement ps = con.prepareStatement("select * from receipts");

		if(combobillno.getSelectedIndex()>0)
		{
				 ps = con.prepareStatement("select * from receipts where billno = ?");
				 ps.setString(1, (String)combobillno.getSelectedItem());
		}
		
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Vector v = new Vector();
			v.add(rs.getString("receiptid"));
			 v.add(rs.getString("billno"));
			 v.add(rs.getString("total"));
			 v.add(rs.getInt("day")+ "-" + rs.getInt("month") + "-" + rs.getInt("year"));	
		 
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
       public static void main(String args[])
	{       
		            ListReceiptDialog w = new ListReceiptDialog();
		            w.setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		showrecords();
		
	}
  
}
