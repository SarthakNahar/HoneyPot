import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sun.io.Converters;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


 class ListOrderDialog extends JDialog implements ActionListener,ItemListener{
        JLabel x1, x2, x3,x4,x5,l1,x6;

        JTextField tsuppierid, texpenses,t4,ttotal;
        JComboBox jc;
        
	JButton b1;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable();
	Font f;
	Color co=new Color(20,200,140);
	JComboBox comboday,combomonth,comboyear;
	OrderMaster ordermaster  = new OrderMaster();
        public ListOrderDialog() {
	setModal(true);
                setTitle("VIEW ORDER DETAILS");
//		setLayout(null);
		setSize(940,550);
		setLocation(100,100);
               addleft(); 
               addright();
               
		
		//Registration
               jc.addItemListener(this);
		b1.addActionListener(this);
		
		
		fillcombo();
		disableAll();

	}

		public void addleft()
 {
			
//			setResizable(false);

			comboday = GeneralUtility.getDayCombo();
			combomonth = GeneralUtility.getMonthCombo();
			comboyear = GeneralUtility.getYearCombo();
			
			l1= GeneralUtility.getHeadingLabel("ORDER DETAILS");
			l1.setBounds(260,25,450,40);
			add(l1);
				Color col=new Color(188,140,240);
//			getContentPane().setBackground(col);

	                x1 = new JLabel("Select Order ID");
			x1.setBounds(110,100,150,25);
			add(x1);
	               jc  =  new JComboBox();
	           
			jc.setBounds(210,100,150,25);
			add(jc);
			

			x2 = new JLabel("Supplier ID");
	                x2.setBounds(410,100,150,25);
			add(x2);
			tsuppierid = new JTextField(14);
			tsuppierid.setBounds(510,100,150,25);
	                add(tsuppierid);
	                x3 = new JLabel("Other Expenses");
			x3.setBounds(110,150,150,25);
			add(x3);
			texpenses = new JTextField(14);
	                texpenses.setBounds(210,150,150,25);
	                texpenses.setDocument(new IntTextDocument());
	                add(texpenses);
	                x4 = new JLabel("Quantity");
	                x4.setEnabled(false);
			x4.setBounds(410,150,150,25);
			add(x4);
	                t4 = new JTextField(14);
	                t4.setEnabled(false);
	                t4.setBounds(510,150,150,25);
	                add(t4); 
	                
	                x5 = new JLabel("Total");
			x5.setBounds(110,200,150,25);
			add(x5);
	                ttotal = new JTextField(14);
	                ttotal.setBounds(210,200,150,25);
	                add(ttotal);  
	              
	                
	                x6  =new JLabel("Order Date");
	                x6.setBounds(410,200,200,25);
	                add(x6);
	                comboday.setBounds(510,200,60,25);
	        		combomonth.setBounds(570,200,60,25);
	        		comboyear.setBounds(630,200,60,25);
	        		add(comboday);
	        		add(combomonth);
	        		add(comboyear);
	              	
			b1 = new JButton("Close");
			b1.setBounds(210,250,100,30);
	                add(b1); 	
			
		         	
	    			
 }
		public void addright()
		 {
			
			
	        JPanel p = new JPanel();
    		p.setLayout(null); //
    		model.addColumn("OrderID");
            model.addColumn("Category");
    		model.addColumn("Quantity");		
    		model.addColumn("Rate");
    		model.addColumn("Total");
    				

//    		fillSupplierModel();
    			          		
    		table.setModel(model);
    		JScrollPane scrollPane = new JScrollPane(table,
    				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    		p.add(scrollPane);
    		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Product wise details of order"));
    		
//    		p.add(l1);
//    		l1.setBounds(140,5,450,30);
    		add(p);
//    		table.setBackground(co);
//    		p.setBackground(co);
    		scrollPane.setBounds(110, 300, 600, 150);


		 }
        
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	setVisible(false);
	dispose();
		
	
	
 	}
 	public void disableAll()
 	{
 		ordermaster = new OrderMaster();
 		
 		tsuppierid.setEditable(false);
 		texpenses.setEditable(false);
 		t4.setText("");
 		ttotal.setEditable(false);
 		table.setEnabled(false);
 		comboday.setEnabled(false);
 		combomonth.setEnabled(false);
 		comboyear.setEnabled(false);
 		}	
 	
 	
 	
 	public  void convetarraylistintovectorofvector() {

 		for(int k=model.getRowCount()-1;k>=0;k--)
 		{
 			model.removeRow(k);
 		}
                 int total=0;
					for(int k = 0 ;k<ordermaster.getItemlist().size();k++) {
				Vector v = new Vector();
				OrderDetail od = (OrderDetail)ordermaster.getItemlist().get(k);

				v.addElement(od.getOrderid());
				v.addElement(od.getProduct().getPdescription());				
				v.addElement(od.getProduct().getPqty()+"");
				v.addElement(od.getProduct().getPrate()+"");
				total = total +(od.getProduct().getPqty()*od.getProduct().getPrate()); 
				v.addElement((od.getProduct().getPqty()*od.getProduct().getPrate()) + "");
				
				model.addRow(v);

			}
               ttotal.setText(total+"");			
		
	}
 	
 	public void itemStateChanged(ItemEvent ie)
 	{
 	
 		String orderid = (String)jc.getSelectedItem();
 		ordermaster = DataBase.getOrder(orderid);
 		filldetails();
 	}
 	
public void  	fillcombo()
 	{
	ArrayList al =    DataBase.getOrderList();
	jc.removeAllItems();
	for(int k=0;k<al.size();k++)
		jc.addItem(((OrderMaster)  al.get(k)).getOrderid());
	if(jc.getItemCount()>0)
	{
	ordermaster = DataBase.getOrder((String)jc.getItemAt(0));
	filldetails();
	}
 	}

public void  	filldetails()
	{
	
		tsuppierid.setText(ordermaster.getSupplier().getSname());
		texpenses.setText(ordermaster.getOrderexpenses()+"");
		ttotal.setText(ordermaster.getOrdertotal()+"");
		
		comboday.setSelectedItem(ordermaster.getOrderday()+"");
		combomonth.setSelectedItem(ordermaster.getOrdermonth()+"");
		comboyear.setSelectedItem(ordermaster.getOrderyear()+"");
		convetarraylistintovectorofvector();
		
	}

 	public static void main(String args[])
 	{
 		ListOrderDialog d = new ListOrderDialog();
 		d.setVisible(true);
 	}

}


