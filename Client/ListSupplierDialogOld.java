import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class ListSupplierDialogOld extends JDialog implements ActionListener{
	JButton b1, b2;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable();
	JComboBox combosupplierid;
	ListSupplierDialogOld()
	{
		setModal(true);
		setSize(700,470);
		setLocation(70,80);
		
		b1 = new JButton("Modify");
		b2 = new JButton("Remove");
		
		Color co=GeneralUtility.getColor();
//		getContentPane().setBackground(co);
		
		
		
		JLabel	l1=GeneralUtility.getHeadingLabel("LIST SUPPLIER DETAILS");
			
		
		
		
		combosupplierid  = new JComboBox();
		combosupplierid.addItem("Category1");
		combosupplierid.addItem("Category2");
		combosupplierid.addItem("Category3");
		combosupplierid.addItem("Category4");
		combosupplierid.setBounds(210,150,200,25);
                add(combosupplierid);
		JPanel p = new JPanel();
		p.setLayout(null); //	
		model.addColumn("Supplier ID");
		model.addColumn("Name");		
		model.addColumn("Address");
		model.addColumn("City");
		model.addColumn("Pincode");		

//		fillSupplierModel();
			          		
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p.add(scrollPane);
		p.add(l1);
		p.add(combosupplierid);
		p.add(b1);
		p.add(b2);
		
		l1.setBounds(140,5,450,30);
//		combosupplierid.setBounds(140,25,150,30);
		b1.setBounds(10,400,100,30);
		b2.setBounds(110,400,100,30);
		add(p);
//		table.setBackground(co);
//		p.setBackground(co);
		scrollPane.setBounds(10, 50, 600, 300);
		
		
		
		//Registration
		b1.addActionListener(this);
		b2.addActionListener(this);
		
	}
	
	 public void actionPerformed(ActionEvent ae)
	 	{
		
		
			if(ae.getSource()==b1)
			{
				if(table.getSelectedRows().length!=0)
				{
				ModifySupplierDialogOld d = new ModifySupplierDialogOld();
				d.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(this,"Please select a row to modify" );
			}
			
			if(ae.getSource()==b2)
			{
				
			int []	a = table.getSelectedRows();
			if(table.getSelectedRows().length==0)
			{
				JOptionPane.showMessageDialog(this,"Please select alteast one row to remove" );
				return;
			}
			String b[] = new String[a.length];
			for(int k=0;k<b.length;k++)
			{
				b[k] = (String)model.getValueAt(a[k],0);
			}
			DataBase.deletesupplier(GeneralUtility.convertarrayintostringlist(b) );
               for(int k=a.length-1; k>=0;k--)
//            	   for(int k=0; k<=a.length-1;k++)
               {
            	   model.removeRow(a[k]);
            	   System.out.println("a[" + k+"] " + a[k]);
               }
				
				JOptionPane.showMessageDialog(this,"Record(s) records deleted successfully" );
			}
	 	}
	 
	 public void fillSupplierModel()
	 {
		 Vector v = DataBase.getSuplierModel();
		 for(int k=0;k<v.size();k++)
		 model.addRow((Vector)v.get(k));
 
	 }
	 
	 public static void main(String  args[])
	 {
		 ListSupplierDialogOld d = new ListSupplierDialogOld();
			d.setVisible(true);
	 }
}
