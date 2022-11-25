import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


 class AddPurchaseItemDialog extends JDialog implements ActionListener{
        JLabel x1, x2, x4,x5,l1;

        JTextField tpinvoiceno,  tpqty,tprate;
        JComboBox combocategory;
        
	JButton b1, b2;
	
	Font f;
	ArrayList al = new ArrayList();
	ArrayList productlist;
        public AddPurchaseItemDialog(String invoiceno) {
        	setModal(true);
                setTitle("ADD PURCHASE ITEM DETAILS");
		setLayout(null);
		setSize(540,500);
		setLocation(100,100);
		Color co=new Color(20,200,140);
		setResizable(false);

		l1=GeneralUtility.getHeadingLabel("ADD ITEM DETAILS");
		add(l1);
		

		Color col=new Color(188,140,240);
//		getContentPane().setBackground(col);

                x1 = new JLabel("INVOICE NO.");
		x1.setBounds(110,100,200,25);
		add(x1);
                
                tpinvoiceno = new JTextField(14);
                tpinvoiceno.setEditable(false);
                tpinvoiceno.setText(invoiceno);
        		tpinvoiceno.setBounds(210,100,200,25);
                        add(tpinvoiceno);
                
		x2 = new JLabel("DECRIPTION");
                x2.setBounds(110,150,200,25);
		add(x2);
		combocategory  = new JComboBox();
		
		combocategory.setBounds(210,150,200,25);
                add(combocategory);
                
                
                x4 = new JLabel("QUANTITY");
		x4.setBounds(110,200,200,25);
		add(x4);
                tpqty = new JTextField(14);
                tpqty.setBounds(210,200,200,25);
                tpqty.setDocument(new IntTextDocument());
                add(tpqty); 
                  
                x5 = new JLabel("RATE");
        		x5.setBounds(110,250,200,25);
        		add(x5);
                        tprate = new JTextField(14);
                        tprate.setBounds(210,250,200,25);
                        tprate.setDocument(new IntTextDocument());
                        add(tprate);
              
              	
		b1 = new JButton("SAVE");
		b1.setBounds(210,330,80,30);
                add(b1); 	
		b2 = new JButton("CLEAR");
                b2.setBounds(330,330,80,30);
                add(b2);
			

		//add(x1);
		//add(cb);
		//add(x2);
		//add(tproductid);
		//add(x3);
		//add(tpqty);
                //add(x4);
		//add(tprate);
                //add(x5);
		//add(t5);
                

		
		
		
		
		//Registration
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		fillcombocategory();
		setFont();

	}

				
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	
		if(ae.getSource()==b1)
		{
			
			if(tpqty.getText().length()==0 || tprate.getText().length()==0 )
			 {
				 JOptionPane.showMessageDialog(this,"Please enter all the details");
				 return;
			 }
			PurchaseDetail purchasedetail = new PurchaseDetail();
			purchasedetail.setPinvoiceno(tpinvoiceno.getText());
			Product p = new Product();
			p.setPid(((Product) productlist.get(combocategory.getSelectedIndex())).getPid());
			p.setPdescription((String)combocategory.getSelectedItem());
			p.setPqty(Integer.parseInt(tpqty.getText()));
			p.setPrate(Integer.parseInt(tprate.getText()));
			purchasedetail.setProduct(p);
			al.add(purchasedetail);
			
			
			JOptionPane.showMessageDialog(this,"Item details added successfully" );
			int choice = JOptionPane.showConfirmDialog(this ,"Do you want to add more items ","Get Cofirmation",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION)		
				clearcontrols();
			else
				setVisible(false);
				
			             
                       
                }       
		if(ae.getSource()==b2)
		{
			clearcontrols();
		}
	
	
	
 	}
	
public void clearcontrols()
{
	
	tpqty.setText("");
	
	}
 
public void  	fillcombocategory()
{
 productlist =    DataBase.getProductList();
combocategory.removeAllItems();
for(int k=0;k<productlist.size();k++)
{
	
	Product pm = (Product)productlist.get(k);
combocategory.addItem(pm.getPdescription());


}
}

public void setFont()
{
	x1.setFont(GeneralUtility.getTextFont());
	x2.setFont(GeneralUtility.getTextFont());
	
	x4.setFont(GeneralUtility.getTextFont());
	x5.setFont(GeneralUtility.getTextFont());
}
public static void main(String args[])
{
	AddPurchaseItemDialog d = new AddPurchaseItemDialog("1");
	d.setVisible(true);
	System.out.println(d.getAl());
	}


public ArrayList getAl() {
	return al;
}
}


