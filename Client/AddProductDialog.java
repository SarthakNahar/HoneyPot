import javax.swing.*;

import java.awt.event.*;
import java.awt.*;


 class AddProductDialog extends JDialog implements ActionListener{
        JLabel x1, x2, x3,x4,x5,l1;

        JTextField t1, t2, t3,t4,t5;
        
	JButton b1, b2;
	
	Font f;
	
        public AddProductDialog() {
        	setModal(true);
                setTitle("ADD PRODUCT DETAILS");
		setLayout(null);
		setSize(540,500);
		setLocation(100,100);
		Color co=new Color(20,200,140);
		setResizable(false);

		l1=GeneralUtility.getHeadingLabel("ADD PRODUCT DETAILS");
		add(l1);
		

		
//		getContentPane().setBackground(GeneralUtility.getColor());

                x1 = new JLabel("Product ID");
		x1.setBounds(110,100,200,25);
		add(x1);
                
                t1 = new JTextField(14);
                t1.setEditable(false);
        		t1.setBounds(210,100,200,25);
                        add(t1);
                
		x2 = new JLabel("Description");
                x2.setBounds(110,150,200,25);
		add(x2);
		t2 = new JTextField(14);
		t2.setBounds(210,150,200,25);
                add(t2);
                x3 = new JLabel("Qunatity");
		x3.setBounds(110,200,200,25);
		add(x3);
		t3 = new JTextField(14);
                t3.setBounds(210,200,200,25);
                add(t3);
                x4 = new JLabel("Rate");
		x4.setBounds(110,250,200,25);
		add(x4);
                t4 = new JTextField(14);
                t4.setBounds(210,250,200,25);
                add(t4); 
                x5 = new JLabel("Reorderlevel");
		x5.setBounds(110,300,200,25);
		add(x5);
                t5 = new JTextField(14);
                t5.setBounds(210,300,200,25);
                add(t5);  
              
              	
		b1 = new JButton("Save");
		b1.setBounds(210,380,80,30);
                add(b1); 	
		b2 = new JButton("Clear");
                b2.setBounds(330,380,80,30);
                add(b2);
			t1.setText(DataBase.getNextProductId()+"");
			t3.setText("0");
			t4.setText("0");
			t3.setEnabled(false);
			t4.setEnabled(false);

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
			if(t1.getText().length()==0 || t2.getText().length()==0 || t3.getText().length()==0 || t4.getText().length()==0)
			 {
				 JOptionPane.showMessageDialog(this,"Please enter all the details");
				 return;
			 }
			Product pm  = new Product();
			pm.setPid(t1.getText());
			pm.setPdescription(t2.getText());
			pm.setPqty(Integer.parseInt(t3.getText()));
			pm.setPrate(Integer.parseInt(t4.getText()));
			System.out.println("t4.getText()" + t4.getText());
			pm.setPreorderlvl(Integer.parseInt(t5.getText()));
			
			int x = DataBase.addproduct(pm);
			if(x>=1)
			JOptionPane.showMessageDialog(this,"Record added successfully" );
			else
				JOptionPane.showMessageDialog(this,"Record could not be added. Please try again" );
			t1.setText(DataBase.getNextProductId()+"");
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
	t3.setText("0");
	t4.setText("0");
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
	AddProductDialog d = new AddProductDialog();
	d.setVisible(true);
	}
}


