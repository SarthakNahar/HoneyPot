import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sun.io.Converters;
import sun.java2d.loops.FillSpans;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


 class AddOrderDialog extends JDialog implements ActionListener,FocusListener{
        JLabel x1, x2, x3,x4,x5,l1,x6;

        JTextField torderid,  t3,t4,ttotal;
        int total=0;
        
	JButton b1, b2,b3;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable();
	Font f;
	Color co=new Color(20,200,140);
	JComboBox comboday,combomonth,comboyear,combosupplier;
	OrderMaster ordermaster  = new OrderMaster();
        public AddOrderDialog() {
	
draw();
setFont();
//setButtonColor();
	}
        
        public void draw()
        {
        	setModal(true);
            setTitle("ADD ORDER DETAILS");
//	setLayout(null);
	setSize(940,550);
	setLocation(100,100);
           addleft(); 
           addright();
	
	//Registration
	b1.addActionListener(this);
	b2.addActionListener(this);
	
	b3.addActionListener(this);
	
	torderid.setText(DataBase.getNextOrderId()+"");
	
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
//			getContentPane().setBackground(co);

	                x1 = new JLabel("Order ID");
			x1.setBounds(110,100,150,25);
			add(x1);
	                torderid  =  new JTextField(14);
			torderid.setBounds(210,100,150,25);
			add(torderid);
			

			x2 = new JLabel("SupplierID");
	                x2.setBounds(410,100,150,25);
			add(x2);
			combosupplier = new JComboBox();
			fillcombosupplier();
			combosupplier.setBounds(510,100,150,25);
	                add(combosupplier);
	                x3 = new JLabel("Other Exp.");
			x3.setBounds(110,150,150,25);
			add(x3);
			t3 = new JTextField(14);
	                t3.setBounds(210,150,150,25);
	                t3.setDocument(new IntTextDocument());
	                add(t3);
	                x4 = new JLabel("Quantity");
	                x4.setVisible(false);
			x4.setBounds(410,150,150,25);
			add(x4);
	                t4 = new JTextField(14);
	                t4.setVisible(false);
	                t4.setBounds(510,150,150,25);
	                add(t4); 
	                
	                x5 = new JLabel("Net");
			x5.setBounds(110,200,150,25);
			add(x5);
	                ttotal = new JTextField(14);
	                ttotal.setBounds(210,200,150,25);
	                add(ttotal);  
	              ttotal.setEditable(false);
	                
	                x6  =new JLabel("OrderDate");
	                x6.setBounds(410,200,200,25);
	                add(x6);
	                comboday.setBounds(510,200,60,25);
	        		combomonth.setBounds(570,200,60,25);
	        		comboyear.setBounds(630,200,60,25);
	        		add(comboday);
	        		add(combomonth);
	        		add(comboyear);
	              	
			b1 = new JButton("Save Order");
			b1.setBounds(210,250,100,30);
	                add(b1); 	
			b2 = new JButton("Clear");
	                b2.setBounds(330,250,100,30);
	                add(b2);
	                
	                b3 = new JButton("Add Item");
	                b3.setBounds(450,250,100,30);
	                add(b3);
	                t3.addFocusListener(this);
		         	
	    			
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

    		torderid.setText(DataBase.getNextOrderId()+"");
		 }
        
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	
		
			if(ae.getSource()==b1)
			{
				if(torderid.getText().length()==0 || ttotal.getText().length()==0 || t3.getText().length()==0)
				 {
					 JOptionPane.showMessageDialog(this,"Please enter all the details");
					 return;
				 }
			 if(ordermaster.getItemlist().size()==0)
			 {
				 JOptionPane.showMessageDialog(this,"Please add item(s) for ordering");
				 return;
			 }
			 
			 if(!GeneralUtility.checkDataCombo(comboday, combomonth, comboyear))
			 {
				 JOptionPane.showMessageDialog(this,"Please select the date");
				 return;
			 }
	
			 ordermaster.setOrderday(Integer.parseInt((String)comboday.getSelectedItem()));
			 ordermaster.setOrdermonth(Integer.parseInt((String)combomonth.getSelectedItem()));
			 ordermaster.setOrderyear(Integer.parseInt((String)comboyear.getSelectedItem()));
			 ordermaster.setOrderid(torderid.getText());
			 ordermaster.setOrderid((String)combosupplier.getSelectedItem());
			  total=0;
			 for(int k = 0; k< ordermaster.getItemlist().size();k++)
			 {
				 total = total +  ((OrderDetail) ordermaster.getItemlist().get(k)).getProduct().getPqty() * ((OrderDetail) ordermaster.getItemlist().get(k)).getProduct().getPrate();
			 }
			 ordermaster.setOrdertotal(total);
			 ttotal.setText(total+"");
			 
				
				int x =1; DataBase.addordermaster(ordermaster);
				if(x>=1)
				JOptionPane.showMessageDialog(this,"Record added successfully" );
				else
					JOptionPane.showMessageDialog(this,"Record could not be added. Please try again" );
				torderid.setText(DataBase.getNextSupplierId()+"");
	      clearcontrols();
	      setVisible(false);
	      dispose();
	                       
	                }       
			
                      
                       
         
		
		if(ae.getSource()==b2)
		{
			clearcontrols();
		}
		if(ae.getSource()==b3)
		{
			AddOrderItemDialog d = new AddOrderItemDialog(torderid.getText());
			d.setVisible(true);
			ordermaster.setItemlist(d.getAl());
			System.out.println(ordermaster.getItemlist());
			convetarraylistintovectorofvector();
		}
	
	
	
 	}
 	public void clearcontrols()
 	{
 		ordermaster = new OrderMaster();
 		
 		combosupplier.setSelectedIndex(0);
 		t3.setText("");
 		t4.setText("");
 		ttotal.setText("");
 		total = 0;
 		for(int k=model.getRowCount()-1;k>=0;k--)
 		{
 			model.removeRow(k);
 		}
 		}	
 	
 	
 	
 	public  void convetarraylistintovectorofvector() {

		
                  total=0;
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
 	public void  	fillcombosupplier()
 	{
 	Vector v =    DataBase.getSuplierModel();
 	combosupplier.removeAllItems();
 	for(int k=0;k<v.size();k++)
 	combosupplier.addItem(((Vector)v.elementAt(k)).elementAt(0));
 	}
 	
 	
 	public void setFont()
 	{
 		x1.setFont(GeneralUtility.getTextFont());
 		x2.setFont(GeneralUtility.getTextFont());
 		x3.setFont(GeneralUtility.getTextFont());
 		x4.setFont(GeneralUtility.getTextFont());
 		x5.setFont(GeneralUtility.getTextFont());
 		x6.setFont(GeneralUtility.getTextFont());
 	}
 	
 	public void setButtonColor()
 	{
 		b1.setBackground(GeneralUtility.getButtonColor());
 		b2.setBackground(GeneralUtility.getButtonColor());
 	}
 	
 	  public void focusGained(FocusEvent e) {
 			
 			
 		}

 		public void focusLost(FocusEvent e) {
 		
 			try
 			{
 			ttotal.setText(total + Integer.parseInt(t3.getText()) + "");
 			}
 			catch(Exception ex)
 			{
 				t3.setText("0");	
 			}
 			
 		}
 	public static void main(String args[])
 	{
 		AddOrderDialog d = new AddOrderDialog();
 		d.setVisible(true);
 	}

}


