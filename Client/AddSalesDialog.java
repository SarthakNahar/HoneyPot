import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sun.io.Converters;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


 class AddSalesDialog extends JDialog implements ActionListener,FocusListener{
        public void focusGained(FocusEvent e) {
	
		
	}

	public void focusLost(FocusEvent e) {
	
		try
		{
		t5.setText(Integer.parseInt( total + t3.getText())  + "");
		}
		catch(Exception ex)
		{
			t3.setText("0");	
		}
		
	}

		JLabel x1, x2, x3,x4,x5,l1,x6;

        JTextField t1, t2, t3,t4,t5;
        
	JButton b1, b2,b3;
	int total=0;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable();
	Font f;
	Color co=new Color(20,200,140);
	JComboBox comboday,combomonth,comboyear;
	SalesMaster salesmaster  = new SalesMaster();
        public AddSalesDialog() {
	draw();

	}
        
        public void draw()
        {
        	setModal(true);
		setTitle("ADD SALES DETAILS");
		// setLayout(null);
		setSize(700, 550);
		setLocation(100, 100);
		addleft();
		addright();

		// Registration
		b1.addActionListener(this);
		b2.addActionListener(this);

		b3.addActionListener(this);

		t1.setText(DataBase.getNextSalesId() + "");
		setFont();
        }

		public void addleft()
 {
			

		// setResizable(false);

		comboday = GeneralUtility.getDayCombo();
		combomonth = GeneralUtility.getMonthCombo();
		comboyear = GeneralUtility.getYearCombo();

		l1 = GeneralUtility.getHeadingLabel("SALES DETAILS");
		l1.setBounds(260, 25, 450, 40);
		add(l1);
		Color col = new Color(188, 140, 240);
		//getContentPane().setBackground(col);

		x1 = new JLabel("Bill No.");
		x1.setBounds(10, 100, 150, 25);
		add(x1);
		t1 = new JTextField(14);
		t1.setBounds(110, 100, 150, 25);
		add(t1);

		x2 = new JLabel("Customer");
		x2.setBounds(310, 100, 150, 25);
		add(x2);
		t2 = new JTextField(14);
		t2.setBounds(410, 100, 150, 25);
		add(t2);
		x3 = new JLabel("Other Exp.");
		x3.setBounds(10, 150, 150, 25);
		add(x3);
		t3 = new JTextField(14);
		t3.setBounds(110, 150, 150, 25);
		t3.setDocument(new IntTextDocument());
		add(t3);
		x4 = new JLabel("Quantity");
		x4.setEnabled(false);
		x4.setBounds(310, 150, 150, 25);
		add(x4);
		t4 = new JTextField(14);
		t4.setEnabled(false);
		t4.setBounds(410, 150, 150, 25);
		add(t4);

		x5 = new JLabel("Net");
		x5.setBounds(10, 200, 150, 25);
		add(x5);
		t5 = new JTextField(14);
		t5.setBounds(110, 200, 150, 25);
		add(t5);
        t5.setEditable(false);
		x6 = new JLabel("Sales Date");
		x6.setBounds(310, 200, 200, 25);
		add(x6);
		comboday.setBounds(410, 200, 60, 25);
		combomonth.setBounds(470, 200, 60, 25);
		comboyear.setBounds(530, 200, 60, 25);
		add(comboday);
		add(combomonth);
		add(comboyear);

		b1 = new JButton("SAVE ALL");
		b1.setBounds(110, 250, 100, 30);
		add(b1);
		b2 = new JButton("CLEAR");
		b2.setBounds(230, 250, 100, 30);
		add(b2);

		b3 = new JButton("ADD ITEM");
		b3.setBounds(350, 250, 100, 30);
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
    				

// fillSupplierModel();
    			          		
    		table.setModel(model);
    		JScrollPane scrollPane = new JScrollPane(table,
    				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    		p.add(scrollPane);
    		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Product wise details of sales"));
    		
//    		p.add(l1);
//    		l1.setBounds(140,5,450,30);
    		add(p);
    		//table.setBackground(co);
//    		p.setBackground(co);
    		scrollPane.setBounds(10, 300, 600, 150);


		 }
        
 	 public void actionPerformed(ActionEvent ae)
 	{
		
			if(ae.getSource()==b1)
			{
				if(t1.getText().length()==0 || t2.getText().length()==0 || t3.getText().length()==0)
				 {
					 JOptionPane.showMessageDialog(this,"Please enter all the details");
					 return;
				 }
			 if(salesmaster.getItemlist().size()==0)
			 {
				 JOptionPane.showMessageDialog(this,"Please add item(s) to sale.");
				 return;
			 }
			 if(!GeneralUtility.checkDataCombo(comboday, combomonth, comboyear))
			 {
				 JOptionPane.showMessageDialog(this,"Please select the date");
				 return;
			 }
			 salesmaster.setScname(t2.getText());
			 salesmaster.setSday((Integer.parseInt((String)comboday.getSelectedItem())));
			 salesmaster.setSmonth(Integer.parseInt((String)combomonth.getSelectedItem()));
			 salesmaster.setSyear(Integer.parseInt((String)comboyear.getSelectedItem()));
			 salesmaster.setSbillno(t1.getText());
			 salesmaster.setSexpenses(Integer.parseInt(t2.getText()));
			  total=0;
			 for(int k = 0; k< salesmaster.getItemlist().size();k++)
			 {
				 System.out.println("salesmaster.getItemlist().get(k)>>>>>>>>>>"+salesmaster.getItemlist().get(k).getClass());
				 total = total +  ((SalesDetail) salesmaster.getItemlist().get(k)).getProduct().getPqty() * ((SalesDetail) salesmaster.getItemlist().get(k)).getProduct().getPrate();
			 }
			 salesmaster.setStotal(total);
			 t3.setText(total+"");
			 
				
				int x = DataBase.addsalesmaster(salesmaster);
				if(x>=1)
				JOptionPane.showMessageDialog(this,"Record added successfully" );
				else
					JOptionPane.showMessageDialog(this,"Record could not be added. Please try again" );
				t1.setText(DataBase.getNextSalesId()+"");
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
			AddSalesItemDialog d = new AddSalesItemDialog(t1.getText());
			d.setVisible(true);
			salesmaster.setItemlist(d.getAl());
//			System.out.println(salesmaster.getItemlist());
			convetarraylistintovectorofvector();
		}
	
	
	
 	}
 	public void clearcontrols()
 	{
 		salesmaster= new SalesMaster();
 		
 		t2.setText("");
 		t3.setText("");
 		t4.setText("");
 		t5.setText("");
 		for(int k=model.getRowCount()-1;k>=0;k--)
 		{
 			model.removeRow(k);
 		}
 		}	
 	
 	
 	
 	public  void convetarraylistintovectorofvector() {

		
                  total=0;
					for(int k = 0 ;k<salesmaster.getItemlist().size();k++) {
				Vector v = new Vector();
				SalesDetail sd = (SalesDetail)salesmaster.getItemlist().get(k);

				v.addElement(sd.getSbillno());
				v.addElement(sd.getProduct().getPdescription());				
				v.addElement(sd.getProduct().getPqty()+"");
				v.addElement(sd.getProduct().getPrate()+"");
				total = total +(sd.getProduct().getPqty()*sd.getProduct().getPrate()); 
				v.addElement((sd.getProduct().getPqty()*sd.getProduct().getPrate()) + "");
				
				model.addRow(v);

			}
               t5.setText(total+"");			
		
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
 	
 	public static void main(String args[])
 	{
 		AddSalesDialog d = new AddSalesDialog();
 		d.setVisible(true);
 	}

	
	public synchronized void addFocusListener(FocusListener l) {
		// TODO Auto-generated method stub
		super.addFocusListener(l);
	}

}


