import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sun.io.Converters;
import sun.java2d.loops.FillSpans;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


 class AddPaymentsDialog extends JDialog implements ActionListener{
        JLabel x1, x2, x3,x4,x5,l1,x6;

        JTextField tpayment,  ttotal,texpenses,tnet;
        
	JButton b1, b2;
	
	
	Font f;
	Color co=new Color(20,200,140);
	JComboBox comboday,combomonth,comboyear,comboinvoiceno;
	Payments p = new Payments();
	PurchaseMaster pm = new PurchaseMaster();
	ArrayList purchaselist = new ArrayList();
        public AddPaymentsDialog() {
	
draw();
setFont();
//setButtonColor();
fillcomboinvoiceno();
	}
        
        public void draw()
        {
        	setModal(true);
            setTitle("ADD PAYMENT DETAILS");
	setLayout(null);
	setSize(740,400);
	setLocation(100,100);
           addleft(); 
           addright();
	
	//Registration
	b1.addActionListener(this);
	b2.addActionListener(this);
	
	
	
	tpayment.setText(DataBase.getNextReceiptId()+"");
	
        }

		public void addleft()
 {
			
//			setResizable(false);

			comboday = GeneralUtility.getDayCombo();
			combomonth = GeneralUtility.getMonthCombo();
			comboyear = GeneralUtility.getYearCombo();
			
			l1= GeneralUtility.getHeadingLabel("PAYMENT DETAILS");
			l1.setBounds(260,25,450,40);
			add(l1);
				Color col=new Color(188,140,240);
//			getContentPane().setBackground(co);

	                x1 = new JLabel("Receipt ID");
			x1.setBounds(110,100,150,25);
			add(x1);
	                tpayment  =  new JTextField(14);
			tpayment.setBounds(210,100,150,25);
			add(tpayment);
			

			x2 = new JLabel("Invoice No.");
	                x2.setBounds(410,100,150,25);
			add(x2);
			comboinvoiceno = new JComboBox();
			
			comboinvoiceno.setBounds(510,100,150,25);
	                add(comboinvoiceno);
	                x3 = new JLabel("Total");
			x3.setBounds(110,150,150,25);
			add(x3);
			ttotal = new JTextField(14);
	                ttotal.setBounds(210,150,150,25);
	                ttotal.setDocument(new IntTextDocument());
	                add(ttotal);
	                x4 = new JLabel("Other Exp.");
	                x4.setEnabled(false);
			x4.setBounds(410,150,150,25);
			add(x4);
	                texpenses = new JTextField(14);
	                texpenses.setEnabled(false);
	                texpenses.setBounds(510,150,150,25);
	                add(texpenses); 
	                
	                x5 = new JLabel("Net Total");
			x5.setBounds(110,200,150,25);
			add(x5);
	                tnet = new JTextField(14);
	                tnet.setBounds(210,200,150,25);
	                add(tnet);  
	              
	                
	                x6  =new JLabel("Date");
	                x6.setBounds(410,200,200,25);
	                add(x6);
	                comboday.setBounds(510,200,60,25);
	        		combomonth.setBounds(570,200,60,25);
	        		comboyear.setBounds(630,200,60,25);
	        		add(comboday);
	        		add(combomonth);
	        		add(comboyear);
	              	
			b1 = new JButton("Save ");
			b1.setBounds(210,250,100,30);
	                add(b1); 	
			b2 = new JButton("Clear");
	                b2.setBounds(330,250,100,30);
	                add(b2);
	                
	                
		         	
	    			
 }
		public void addright()
		 {
			
			
	        
    				

//    		fillSupplierModel();
    			          		
    		

    		tpayment.setText(DataBase.getNextOrderId()+"");
		 }
        
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	
		
			if(ae.getSource()==b1)
			{
			 
			 
			 if(!GeneralUtility.checkDataCombo(comboday, combomonth, comboyear))
			 {
				 JOptionPane.showMessageDialog(this,"Please select the date");
				 return;
			 }
	
			 p.setDay(Integer.parseInt((String)comboday.getSelectedItem()));
			 p.setMonth(Integer.parseInt((String)combomonth.getSelectedItem()));
			 p.setYear(Integer.parseInt((String)comboyear.getSelectedItem()));
			 p.setPaymentid(tpayment.getText());
			 p.setInvoiceno((String)comboinvoiceno.getSelectedItem());
			 p.setTotal(Integer.parseInt(tnet.getText()));
			 p.setBalance(0);
			 
				
				int x =1; DataBase.addpayment(p);
				if(x>=1)
				JOptionPane.showMessageDialog(this,"Record added successfully" );
				else
					JOptionPane.showMessageDialog(this,"Record could not be added. Please try again" );
				tpayment.setText(DataBase.getNextSupplierId()+"");
	      clearcontrols();
	      setVisible(false);
	      dispose();
	                       
	                }       
			
                      
                       
         
		
		if(ae.getSource()==b2)
		{
			clearcontrols();
		}
		
	
	
	
 	}
 	 
 	 
 	public void itemStateChanged(ItemEvent ie)
 	{
 	
 		int index = comboinvoiceno.getSelectedIndex();
 		pm = ((PurchaseMaster) purchaselist.get(index));
 		
 		filldetails();
 	}
 	
 	public void filldetails()
 	{
 		System.out.println("pm.getPtotal()  " + pm.getPtotal());
 		ttotal.setText(pm.getPtotal() +"");
 		tnet.setText(pm.getPtotal()+ pm.getPexpenses() +"");
 		texpenses.setText(pm.getPexpenses()+"");
 	}
 	
 	public void clearcontrols()
 	{
 		p = new Payments();
 		
 		comboinvoiceno.setSelectedIndex(0);
 		ttotal.setText("");
 		texpenses.setText("");
 		tnet.setText("");
 		
 		}	
 	
 	
 	
 	
 	public void  	fillcomboinvoiceno()
 	{
 	purchaselist =    DataBase.getPurchaseList();
 	comboinvoiceno.removeAllItems();
 	for(int k=0;k<purchaselist.size();k++)
 	comboinvoiceno.addItem( ((PurchaseMaster) purchaselist.get(k)).getPinvoiceno());
 	if(purchaselist.size()>0)
 	pm = ((PurchaseMaster) purchaselist.get(0));
 	
 	filldetails();
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
 	public static void main(String args[])
 	{
 		AddPaymentsDialog d = new AddPaymentsDialog();
 		d.setVisible(true);
 	}

}


