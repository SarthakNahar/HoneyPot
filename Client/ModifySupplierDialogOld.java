import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


 class ModifySupplierDialogOld extends JDialog implements ActionListener{
        JLabel x1, x2, x3,x4,x5,l1;

        JTextField t1, t2, t3,t4,t5;
        
	JButton b1, b2;
	
	Font f;
	
        public ModifySupplierDialogOld() {
	setModal(true);
                setTitle("MODIFY SUPPLIER DETAILS");
		setLayout(null);
		setSize(540,540);
		setLocation(250,200);
		Color co=new Color(20,200,140);
		setResizable(false);

		l1=GeneralUtility.getHeadingLabel("MODIFY SUPPLIER DETAILS");
		add(l1);
		

		Color col=new Color(188,140,240);
//		getContentPane().setBackground(col);

                x1 = new JLabel("Product ID");
		x1.setBounds(110,100,200,25);
		add(x1);
                JComboBox cb = new JComboBox();
		cb.setBounds(210,100,200,25);
		add(cb);
		cb.addItem("-- Select One --");
		cb.addItem("M001");
		cb.addItem("M002");
		cb.addItem("M003");
		cb.addItem("M004");
		cb.addItem("M005");
		cb.addItem("M006");
                cb.addItem("M007");


		x2 = new JLabel("Customer Name");
                x2.setBounds(110,150,200,25);
		add(x2);
		t1 = new JTextField(14);
		t1.setBounds(210,150,200,25);
                add(t1);
                x3 = new JLabel("Rate");
		x3.setBounds(110,200,200,25);
		add(x3);
		t2 = new JTextField(14);
                t2.setBounds(210,200,200,25);
                add(t2);
                x4 = new JLabel("Quantity");
		x4.setBounds(110,250,200,25);
		add(x4);
                t3 = new JTextField(14);
                t3.setBounds(210,250,200,25);
                add(t3); 
                x5 = new JLabel("Total");
		x5.setBounds(110,300,200,25);
		add(x5);
                t4 = new JTextField(14);
                t4.setBounds(210,300,200,25);
                add(t4);  
              
              	
		b1 = new JButton("Save");
		b1.setBounds(210,380,80,30);
                add(b1); 	
		b2 = new JButton("Clear");
                b2.setBounds(330,380,80,30);
                add(b2);
			

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
		
		

	}

				
 	 public void actionPerformed(ActionEvent ae)
 	{
	
	
		if(ae.getSource()==b1)
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
               	        t4.setText("");
		 	t5.setText("");
                      
                       
                }       
		if(ae.getSource()==b2)
		{
			System.exit(0);
		}
	
	
	
 	}
	

}


