import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;


 class AddOrderItemDialog extends JDialog implements ActionListener{
        JLabel x1, x2, x3,x4,l1;

        JTextField torderid, tpqty, tprate;
        JComboBox combocategory;
        
	JButton b1, b2;
	
	Font f;
	ArrayList al = new ArrayList();
	ArrayList productlist;
	
        public AddOrderItemDialog(String orderid) {
        	setModal(true);
                setTitle("ADD ORDER ITEM DETAILS");
		setLayout(null);
		setSize(540,500);
		setLocation(100,100);
		
		setResizable(false);

		l1=GeneralUtility.getHeadingLabel("ADD ITEM DETAILS");
		add(l1);
		

		Color col=new Color(188,140,240);
	//	getContentPane().setBackground(col);

                x1 = new JLabel("ORDER ID");
		x1.setBounds(110,100,200,25);
		add(x1);
                
                torderid = new JTextField(14);
                torderid.setEditable(false);
        		torderid.setBounds(210,100,200,25);
                        add(torderid);
                
		x2 = new JLabel("CATEGORY");
                x2.setBounds(110,150,200,25);
		add(x2);
		combocategory  = new JComboBox();
		fillcombocategory();
		
		combocategory.setBounds(210,150,200,25);
                add(combocategory);
                
                x3 = new JLabel("QUANTITY");
		x3.setBounds(110,200,200,25);
		add(x3);
		tpqty = new JTextField(14);
                tpqty.setBounds(210,200,200,25);
                tpqty.setDocument(new IntTextDocument());
                add(tpqty);
                x4 = new JLabel("RATE");
		x4.setBounds(110,250,200,25);
		add(x4);
                tprate = new JTextField(14);
                tprate.setBounds(210,250,200,25);
                tprate.setDocument(new IntTextDocument());
                add(tprate); 
                  
              
              	
		b1 = new JButton("Save");
		b1.setBounds(210,330,80,30);
                add(b1); 	
		b2 = new JButton("Clear");
                b2.setBounds(330,330,80,30);
                add(b2);
			torderid.setText(orderid);

		//add(x1);
		//add(cb);
		//add(x2);
		//add(tpqty);
		//add(x3);
		//add(tprate);
                //add(x4);
		//add(t4);
                //add(x5);
		//add(t5);
                

		
		
		
		
		//Registration
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		setFont();

	}

				
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	
		if(ae.getSource()==b1)
		{
			
			if(tpqty.getText().length()==0 || tprate.getText().length()==0)
			 {
				 JOptionPane.showMessageDialog(this,"Please enter all the details");
				 return;
			 }
			
			OrderDetail orderdetail = new OrderDetail();
			orderdetail.setOrderid(torderid.getText());
			Product p = new Product();
			p.setPid(((Product) productlist.get(combocategory.getSelectedIndex())).getPid());
			p.setPdescription((String)combocategory.getSelectedItem());
			p.setPqty(Integer.parseInt(tpqty.getText()));
			p.setPrate(Integer.parseInt(tprate.getText()));
			orderdetail.setProduct(p);
			al.add(orderdetail);
			
			
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
	tprate.setText("");
	
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
		x3.setFont(GeneralUtility.getTextFont());
		x4.setFont(GeneralUtility.getTextFont());
	
	}
public static void main(String args[])
{
	AddOrderItemDialog d = new AddOrderItemDialog(DataBase.getNextOrderId()+"");
	d.setVisible(true);
	System.out.println(d.getAl());
	}


public ArrayList getAl() {
	return al;
}
}


