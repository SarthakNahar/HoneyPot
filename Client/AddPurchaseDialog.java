import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sun.io.Converters;
import sun.java2d.loops.FillSpans;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


 class AddPurchaseDialog extends JDialog implements ActionListener,ItemListener{
       
		JLabel x1, x2, x3,x4,x5,l1,x6;

        JTextField t1,  t3,t4,t5;
        
	JButton b1, b2,b3;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable();
	Font f;
	Color co=new Color(20,200,140);
	JComboBox comboday,combomonth,comboyear,comboorderid;
	PurchaseMaster purchasemaster  = new PurchaseMaster();
	ArrayList ordermasterist;
        public AddPurchaseDialog() {
	setModal(true);
                setTitle("ADD PURCHASE DETAILS");
//		setLayout(null);
		setSize(940,550);
		setLocation(100,100);
               addleft(); 
               addright(); 

		        

		
		
		
		
		//Registration
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		b3.addActionListener(this);
		comboorderid.addItemListener(this);
		t1.setText(DataBase.getNextPurchaseId()+"");
		
		fillcomboorder();
		setFont();

	}

		public void addleft()
 {
			
//			setResizable(false);

			comboday = GeneralUtility.getDayCombo();
			combomonth = GeneralUtility.getMonthCombo();
			comboyear = GeneralUtility.getYearCombo();
			
			l1= GeneralUtility.getHeadingLabel("PURCHASE DETAILS");
			l1.setBounds(260,25,450,40);
			add(l1);
				Color col=new Color(188,140,240);
//			getContentPane().setBackground(col);

	                x1 = new JLabel("INVOICE NO.");
			x1.setBounds(110,100,150,25);
			add(x1);
	                t1  =  new JTextField(14);
			t1.setBounds(210,100,150,25);
			add(t1);
			

			x2 = new JLabel("Order ID");
	                x2.setBounds(410,100,150,25);
			add(x2);
			comboorderid = new JComboBox();
		
		comboorderid.setBounds(510,100,150,25);
	                add(comboorderid);
	                x3 = new JLabel("Suppplier");
			x3.setBounds(110,150,150,25);
			add(x3);
			t3 = new JTextField(14);
	                t3.setBounds(210,150,150,25);
	                add(t3);
	                x4 = new JLabel("OTHER EXPENSES");
	                
			x4.setBounds(410,150,150,25);
			add(x4);
	                t4 = new JTextField(14);
	                t4.setDocument(new IntTextDocument());
	                
	                t4.setBounds(510,150,150,25);
	                add(t4); 
	                
	                x5 = new JLabel("TOTAL");
			x5.setBounds(110,200,150,25);
			add(x5);
	                t5 = new JTextField(14);
	                t5.setBounds(210,200,150,25);
	                add(t5);  
	              
	                
	                x6  =new JLabel("DATE");
	                x6.setBounds(410,200,200,25);
	                add(x6);
	                comboday.setBounds(510,200,60,25);
	        		combomonth.setBounds(570,200,60,25);
	        		comboyear.setBounds(630,200,60,25);
	        		add(comboday);
	        		add(combomonth);
	        		add(comboyear);
	              	
			b1 = new JButton("SAVE ALL");
			b1.setBounds(210,250,100,30);
	                add(b1); 	
			b2 = new JButton("CLEAR");
	                b2.setBounds(330,250,100,30);
	                add(b2);
	                
	                b3 = new JButton("ADD ITEM");
	                b3.setBounds(450,250,100,30);
	                add(b3);
		         	
	    			
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
    		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Product wise details of purchase"));
    		
//    		p.add(l1);
//    		l1.setBounds(140,5,450,30);
    		add(p);
//    		table.setBackground(co);
//    		p.setBackground(co);
    		scrollPane.setBounds(110, 300, 600, 150);


		 }
        
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	
		
			if(ae.getSource()==b1)
			{
				if(t1.getText().length()==0  || t3.getText().length()==0 || t4.getText().length()==0)
				 {
					 JOptionPane.showMessageDialog(this,"Please enter all the details");
					 return;
				 }
			 if(purchasemaster.getItemlist().size()==0)
			 {
				 JOptionPane.showMessageDialog(this,"Please add item(s) to purchase");
				 return;
			 }
			 
			 if(!GeneralUtility.checkDataCombo(comboday, combomonth, comboyear))
			 {
				 JOptionPane.showMessageDialog(this,"Please select the date");
				 return;
			 }
	
			 purchasemaster.setPday(Integer.parseInt((String)comboday.getSelectedItem()));
			 purchasemaster.setPmonth(Integer.parseInt((String)combomonth.getSelectedItem()));
			 purchasemaster.setPear(Integer.parseInt((String)comboyear.getSelectedItem()));
			 purchasemaster.setPinvoiceno(t1.getText());
			// purchasemaster.setOrderexpenses(Integer.parseInt(combosupplierid.getText()));
			 int total=0;
			 for(int k = 0; k< purchasemaster.getItemlist().size();k++)
			 {
				 total = total +  ((PurchaseDetail) purchasemaster.getItemlist().get(k)).getProduct().getPqty() * ((PurchaseDetail) purchasemaster.getItemlist().get(k)).getProduct().getPrate();
			 }
			 purchasemaster.setPtotal(total);
			 t3.setText(total+"");
			 
				
				int x =DataBase.addpurchasemaster(purchasemaster);
				if(x>=1)
				JOptionPane.showMessageDialog(this,"Record added successfully" );
				else
					JOptionPane.showMessageDialog(this,"Record could not be added. Please try again" );
				t1.setText(DataBase.getNextPurchaseId()+"");
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
			AddPurchaseItemDialog d = new AddPurchaseItemDialog(t1.getText());
			d.setVisible(true);
			purchasemaster.setItemlist(d.getAl());
			System.out.println(purchasemaster.getItemlist());
			convetarraylistintovectorofvector();
		}
	
	
	
 	}
 	public void clearcontrols()
 	{
 		purchasemaster = new PurchaseMaster();
 		
 		comboorderid.setSelectedIndex(0);
 		t3.setText("");
 		t4.setText("");
 		t5.setText("");
 		for(int k=model.getRowCount()-1;k>=0;k--)
 		{
 			model.removeRow(k);
 		}
 		}	
 	
 	
 	
 	public  void convetarraylistintovectorofvector() {

		
                 int total=0;
					for(int k = 0 ;k<purchasemaster.getItemlist().size();k++) {
				Vector v = new Vector();
				PurchaseDetail od = (PurchaseDetail)purchasemaster.getItemlist().get(k);

				v.addElement(od.getPinvoiceno());
				v.addElement(od.getProduct().getPdescription());				
				v.addElement(od.getProduct().getPqty()+"");
				v.addElement(od.getProduct().getPrate()+"");
				total = total +(od.getProduct().getPqty()*od.getProduct().getPrate()); 
				v.addElement((od.getProduct().getPqty()*od.getProduct().getPrate()) + "");
				
				model.addRow(v);

			}
               t5.setText(total+"");			
		
	}
 	
 	public void  	fillcomboorder()
 	{
 	ordermasterist =    DataBase.getOrderList();
 	comboorderid.removeAllItems();
 	for(int k=0;k<ordermasterist.size();k++)
 	{
 		if(k==0)
 		{
 		t3.setText(((OrderMaster)ordermasterist.get(k)).getSupplier().getSname());
 		}
 		comboorderid.addItem(((OrderMaster)ordermasterist.get(k)).getOrderid());
 	}
 	}	
 	
 	
 	 public void itemStateChanged(ItemEvent e) {
 		t3.setText(((OrderMaster)ordermasterist.get(comboorderid.getSelectedIndex())).getSupplier().getSname());
 		
 	}
 	 
 	public void setFont()
	{
		x1.setFont(GeneralUtility.getTextFont());
		x2.setFont(GeneralUtility.getTextFont());
		x3.setFont(GeneralUtility.getTextFont());
		x4.setFont(GeneralUtility.getTextFont());
	
	}
 	public static void main(String args[])
 	{
 		AddPurchaseDialog d = new AddPurchaseDialog();
 		d.setVisible(true);
 	}

}


