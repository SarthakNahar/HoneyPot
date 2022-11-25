import javax.swing.*;

import java.awt.event.*;
import java.awt.*;


 class AddSupplierDialog extends JDialog implements ActionListener{
        JLabel x1, x2, x3,x4,x5,l1;

        JTextField t1, t2, t3,t4,t5;
        
	JButton b1, b2;
	
	Font f;
	
        public AddSupplierDialog() {
        	setModal(true);
                setTitle("ADD SUPPLIER DETAILS");
		setLayout(null);
		setSize(540,500);
		setLocation(100,100);
		Color co=new Color(20,200,140);
		setResizable(false);

		l1=GeneralUtility.getHeadingLabel("ADD SUPPLIER DETAILS");
		add(l1);
		

		Color col=GeneralUtility.getColor();
//		getContentPane().setBackground(col);

                x1 = new JLabel("Supplier ID");
		x1.setBounds(110,100,200,25);
		add(x1);
                
                t1 = new JTextField(14);
                t1.setEditable(false);
        		t1.setBounds(210,100,200,25);
                        add(t1);
                
		x2 = new JLabel("Name");
                x2.setBounds(110,150,200,25);
		add(x2);
		t2 = new JTextField(14);
		t2.setBounds(210,150,200,25);
                add(t2);
                x3 = new JLabel("Address");
		x3.setBounds(110,200,200,25);
		add(x3);
		t3 = new JTextField(14);
                t3.setBounds(210,200,200,25);
                add(t3);
                x4 = new JLabel("City");
		x4.setBounds(110,250,200,25);
		add(x4);
                t4 = new JTextField(14);
                t4.setBounds(210,250,200,25);
                add(t4); 
                x5 = new JLabel("Pincode");
		x5.setBounds(110,300,200,25);
		add(x5);
                t5 = new JTextField(14);
                t5.setBounds(210,300,200,25);
                add(t5);  
              
              	
		b1 = new JButton("SAVE");
		b1.setBounds(210,380,80,30);
                add(b1); 	
		b2 = new JButton("CLEAR");
                b2.setBounds(330,380,80,30);
                add(b2);
			t1.setText(DataBase.getNextSupplierId()+"");

		//add(x1);
		//add(cb);
		//add(x2);
		//add(t2);
		//add(x3);
		//add(t3);
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
			if(t1.getText().length()==0 || t2.getText().length()==0 || t3.getText().length()==0
					|| t4.getText().length()==0 || t5.getText().length()==0 )
			 {
				 JOptionPane.showMessageDialog(this,"Please enter all the details");
				 return;
			 }
			Supplier s = new Supplier();
			s.setSupplierid(t1.getText());
			s.setSname(t2.getText());
			s.setSaddress(t3.getText());
			s.setScity(t4.getText());
			s.setSpincode(t5.getText());
			
			int x = DataBase.addsupplier(s);
			if(x>=1)
			JOptionPane.showMessageDialog(this,"Record added successfully" );
			else
				JOptionPane.showMessageDialog(this,"Record could not be added. Please try again" );
			t1.setText(DataBase.getNextSupplierId()+"");
      clearcontrols();             
                       
                }       
		if(ae.getSource()==b2)
		{
			clearcontrols();
		}
	
	
	
 	}
	
public void clearcontrols()
{
	t2.setText("");
	t3.setText("");
	t4.setText("");
	t5.setText("");
	}
 
public void setFont()
	{
		x1.setFont(GeneralUtility.getTextFont());
		x2.setFont(GeneralUtility.getTextFont());
		x3.setFont(GeneralUtility.getTextFont());
		x4.setFont(GeneralUtility.getTextFont());
		x5.setFont(GeneralUtility.getTextFont());
		
	}
public static void main(String args[])
{
	AddSupplierDialog d = new AddSupplierDialog();
	d.setVisible(true);
	}
}


