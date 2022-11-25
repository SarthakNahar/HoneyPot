import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



class MainFrame extends JFrame implements ActionListener {
	
    String activitylog="";
	String role;
	long starttime;
	JMenuBar mbar;
    JToolBar toolbar; 
	JMenu prodmenu, usermenu,purmenu,salmenu,ordermenu,categorymenu,receiptmenu,paymentmenu,m3;
	
	JMenuItem listproditem,addproductitem;
	JMenuItem  listuseritem,adduseritem;
	JMenuItem  listpuritem,addpuritem;
	JMenuItem  listsaleitem,addsaleitem;
	JMenuItem  listorderitem,addorderitem;
	JMenuItem  listcatitem,addcatitem,exititem;
	JMenuItem  listreceiptitem,addreceiptitem;
	JMenuItem listpaymentitem,addpaymentitem;
	JRadioButtonMenuItem item9, item10,item11;
	JButton  blistprod,blistpurchase,baddpurchase,blistuser,badduser,blistsales,baddsales;
	JButton blistorder,baddorder,blistcat,baddcat,blistreceipt,baddreceipt,blistpayment,baddpayment;
	JButton bexit;
	MainFrame(String r) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SwingDemo2");
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().setLayout(new BorderLayout());
		role=r;

		  if(role.equalsIgnoreCase("Intruder"))
		  {
			  starttime = System.currentTimeMillis();
		  }
            createmenubar();
            createtoolbar();
            add(toolbar,BorderLayout.NORTH);
            
		listproditem.addActionListener(this);
		addproductitem.addActionListener(this);
		listuseritem.addActionListener(this);
		adduseritem.addActionListener(this);
		listpuritem.addActionListener(this);
		addpuritem.addActionListener(this);
		listsaleitem.addActionListener(this);
		addsaleitem.addActionListener(this);
		listorderitem.addActionListener(this);
		addorderitem.addActionListener(this);
		listcatitem.addActionListener(this);
		addcatitem.addActionListener(this);
		
		listreceiptitem.addActionListener(this);
		addreceiptitem.addActionListener(this);
		
		listpaymentitem.addActionListener(this);
		addpaymentitem.addActionListener(this);
		exititem.addActionListener(this);
		
		item9.addActionListener(this);
		item10.addActionListener(this);
		item11.addActionListener(this);
		blistprod.addActionListener(this);
		blistuser.addActionListener(this);
		badduser.addActionListener(this);
		blistsales.addActionListener(this);
		baddsales.addActionListener(this);
		blistorder.addActionListener(this);
		baddorder.addActionListener(this);
		blistcat.addActionListener(this);
		baddcat.addActionListener(this);
		bexit.addActionListener(this);
		blistreceipt.addActionListener(this);
		baddreceipt.addActionListener(this);
		blistpayment.addActionListener(this);
		baddpayment.addActionListener(this);
	}
	
	
	public void createmenubar()
	{
		mbar = new JMenuBar();
		setJMenuBar(mbar);

		
		 categorymenu = new JMenu("Category");
		 categorymenu.setMnemonic(KeyEvent.VK_C);
		listcatitem = new JMenuItem("List Categories",new ImageIcon("listcategory.gif"));
		listcatitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
		addcatitem = new JMenuItem("Add New Category",new ImageIcon("addcategory.gif"));
		addcatitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
		exititem = new JMenuItem("Exit",new ImageIcon("close.gif"));
		exititem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
		categorymenu.add(listcatitem);		
		categorymenu.add(addcatitem);
		categorymenu.add(exititem);
		
		

		
		prodmenu = new JMenu("Product");	
		prodmenu.setMnemonic(KeyEvent.VK_P);
		
		listproditem = new JMenuItem("List Products",new ImageIcon("listproduct.gif"));
		
		listproditem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
		addproductitem= new JMenuItem("Add New Product",new ImageIcon("addcategory.gif"));
		addproductitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
		prodmenu.add(listproditem);
		prodmenu.add(addproductitem);
		



		usermenu = new JMenu("User");
		usermenu.setMnemonic(KeyEvent.VK_S);
		listuseritem = new JMenuItem("List Users",new ImageIcon("listsupplier.gif"));
		listuseritem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
		adduseritem = new JMenuItem("Add New User",new ImageIcon("addsupplier.gif"));
		adduseritem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
		usermenu.add(listuseritem);		
		usermenu.add(adduseritem);
		
		
		
purmenu = new JMenu("Purchase");
purmenu.setMnemonic(KeyEvent.VK_U);
		listpuritem = new JMenuItem("List Purchases",new ImageIcon("listpurchase.gif"));
		listpuritem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
		addpuritem = new JMenuItem("Add New Purchase",new ImageIcon("addpurchase.gif"));
		addpuritem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
		purmenu.add(listpuritem);		
		purmenu.add(addpuritem);
		
		
		
salmenu = new JMenu("Sales");
salmenu.setMnemonic(KeyEvent.VK_A);
		listsaleitem = new JMenuItem("List Sales",new ImageIcon("listsales.gif"));
		listsaleitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
		addsaleitem= new JMenuItem("Add New Sale",new ImageIcon("addsales.gif"));
		addsaleitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
		salmenu.add(listsaleitem);		
		salmenu.add(addsaleitem);
		
		
		
		
 ordermenu = new JMenu("Order");
 ordermenu.setMnemonic(KeyEvent.VK_O);
		listorderitem = new JMenuItem("List Orders",new ImageIcon("listorder.gif"));
		listorderitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
	    addorderitem = new JMenuItem("Add New Order",new ImageIcon("addorder.gif"));
	    addorderitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
		ordermenu.add(listorderitem);		
		ordermenu.add(addorderitem);
		
		
		
		receiptmenu = new JMenu("Receipts");
		receiptmenu.setMnemonic(KeyEvent.VK_O);
				listreceiptitem = new JMenuItem("List Receipts",new ImageIcon("listorder.gif"));
				listreceiptitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
			    addreceiptitem = new JMenuItem("Add New Receipt",new ImageIcon("addorder.gif"));
			    addreceiptitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
				receiptmenu.add(listreceiptitem);
				receiptmenu.add(addreceiptitem);
				
				
				
				
				paymentmenu = new JMenu("Payments");
				receiptmenu.setMnemonic(KeyEvent.VK_O);
						listpaymentitem = new JMenuItem("List Payments",new ImageIcon("listorder.gif"));
						listpaymentitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));		
					    addpaymentitem = new JMenuItem("Add New Payment",new ImageIcon("addorder.gif"));
					    addpaymentitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK  ));
						paymentmenu.add(listpaymentitem);
						paymentmenu.add(addpaymentitem);
						
						
						
						
						if(role.equals("ADMIN"))
						{
						mbar.add(categorymenu);
						mbar.add(usermenu);
						mbar.add(paymentmenu);
						mbar.add(purmenu);
						mbar.add(ordermenu);
						}
						else
						{
						mbar.add(prodmenu);							
						mbar.add(salmenu);						
						mbar.add(receiptmenu);
						}
						

		
		
m3 = new JMenu("Look & Feel");
		ButtonGroup bg = new ButtonGroup();
		
		item9 = new JRadioButtonMenuItem("Native  look and feel");
		bg.add(item9);
		
		item10 = new JRadioButtonMenuItem("Java look and feel");
		bg.add(item10);
		
		item11 = new JRadioButtonMenuItem("Motif look and feel");
		bg.add(item11);
				
		m3.add(item9);
		m3.add(item10);
		m3.add(item11);

		mbar.add(m3);
		
		
		addWindowListener(new MyWindowAdapter());
	}

	public void createtoolbar()
	{
		toolbar = new JToolBar();
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolbar.setBorder(BorderFactory.createEtchedBorder());

		blistprod = new JButton(new ImageIcon("listproduct.gif"));
		blistpurchase = new JButton(new ImageIcon("listpurchase.gif"));
		baddpurchase = new JButton(new ImageIcon("addpurchase.gif"));
		blistuser = new JButton(new ImageIcon("listsupplier.gif"));
		badduser = new JButton(new ImageIcon("addsupplier.gif"));
		blistsales = new JButton(new ImageIcon("listsales.gif"));
		baddsales = new JButton(new ImageIcon("addsales.gif"));
		blistorder  = new JButton(new ImageIcon("listorder.gif"));
		baddorder = new JButton(new ImageIcon("addorder.gif"));
		blistcat  = new JButton(new ImageIcon("listcategory.gif"));
		baddcat = new JButton(new ImageIcon("addcategory.gif"));
		
		blistreceipt  = new JButton(new ImageIcon("listcategory.gif"));
		baddreceipt = new JButton(new ImageIcon("addcategory.gif"));

		blistpayment  = new JButton(new ImageIcon("listcategory.gif"));
		baddpayment = new JButton(new ImageIcon("addcategory.gif"));

		bexit = new JButton(new ImageIcon("close.gif"));
		
		blistprod.setBorderPainted(true);
		blistpurchase.setBorderPainted(true);
		baddpurchase.setBorderPainted(true);
		blistuser.setBorderPainted(true);
		badduser.setBorderPainted(true);
		blistsales.setBorderPainted(true);
		baddsales.setBorderPainted(true);
		blistorder.setBorderPainted(true);
		baddorder.setFocusPainted(true);
		blistcat.setBorderPainted(true);
		blistorder.setFocusPainted(true);
		blistreceipt.setBorderPainted(true);
		baddreceipt.setFocusPainted(true);

		blistpayment.setBorderPainted(true);
		baddpayment.setFocusPainted(true);

		bexit.setFocusPainted(true);
		
		blistprod.setToolTipText("List Inventory");
		blistpurchase.setToolTipText("List Purchases");
		baddpurchase.setToolTipText("Add New Purchase");	
		blistuser.setToolTipText("List Suppliers");
		badduser.setToolTipText("Add New supplier");
		blistsales.setToolTipText("List Sales");
		baddsales.setToolTipText("Add New Sale");
		blistorder.setToolTipText("List Orders");
		baddorder.setToolTipText("Add New Order");
		blistcat.setToolTipText("List Product Categories");
		baddcat.setToolTipText("Add New Product Category");
		
		blistreceipt.setToolTipText("List Receipts");
		baddreceipt.setToolTipText("Add New receipts");

		blistpayment.setToolTipText("List Payments");
		baddpayment.setToolTipText("Add New Payment");

		bexit.setToolTipText("Exit");
		
		
		
		
		
		
		if(role.equals("ADMIN"))
		{
			toolbar.add(blistprod);		
			toolbar.add(blistpurchase);
			toolbar.add(baddpurchase);		
			toolbar.add(blistuser);		
			toolbar.add(badduser);
			toolbar.add(blistsales);
			toolbar.add(baddsales);
			
			toolbar.add(blistorder);
			toolbar.add(baddorder);
			
			toolbar.add(blistcat);
			toolbar.add(baddcat);
			
			toolbar.add(blistpayment);
			toolbar.add(baddpayment);

			
		}
		else
		{
			toolbar.add(blistprod);		
			toolbar.add(blistsales);
			toolbar.add(baddsales);		
			
			
			toolbar.add(blistreceipt);
			toolbar.add(baddreceipt);
		}
		toolbar.add(bexit);
				
	}

	public void actionPerformed(ActionEvent ae) {
		
		if(role.equalsIgnoreCase("Intruder"))
		{
		long endtime= System.currentTimeMillis();
		long time = (endtime-starttime)/1000;
		if(time>=60)
		{
			System.exit(0);
		}
		
		}
		
		if (ae.getSource() == listreceiptitem || ae.getSource() == blistreceipt) {
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("List receipt is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			
			HoneyPotClient.addactivity("List receipt is clicked ");
			ListReceiptDialog d = new ListReceiptDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == addreceiptitem || ae.getSource()== baddreceipt) {
			
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("Add receipt is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			AddReceiptsDialog d = new AddReceiptsDialog();
			d.setVisible(true);
		}

		
		if (ae.getSource() == listpaymentitem || ae.getSource() == blistpayment) {
			ListPayementsDialog d = new ListPayementsDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == addpaymentitem || ae.getSource()== baddpayment) {
			AddPaymentsDialog d = new AddPaymentsDialog();
			d.setVisible(true);
		}
		
		
		if (ae.getSource() == listproditem || ae.getSource() == listcatitem || ae.getSource()== blistcat) {
			
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("List category is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			
			ListCategoryDialog d = new ListCategoryDialog();
			d.setVisible(true);
		}
		
if (ae.getSource() == addproductitem ) {
			
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("List category is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			
			ListCategoryDialog d = new ListCategoryDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == addcatitem || ae.getSource()== baddcat) {
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("Add category is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			
			AddProductDialog d = new AddProductDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == adduseritem || ae.getSource()==badduser) {
			AddSupplierDialog d = new AddSupplierDialog();
			d.setVisible(true);
		}
		
		
		if (ae.getSource() == listuseritem || ae.getSource()== blistuser) {
			ListSupplierDialog d = new ListSupplierDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == adduseritem || ae.getSource()==badduser) {
			AddSupplierDialog d = new AddSupplierDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == listsaleitem || ae.getSource()==blistsales) {
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("List sales is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			ListSalesDialog d = new ListSalesDialog();
			d.setVisible(true);
		}
		if (ae.getSource() == addsaleitem  || ae.getSource()==baddsales) {
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("Add sales is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			AddSalesDialog d = new AddSalesDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == listpuritem || ae.getSource()==blistpurchase) {
			ListPurchaseDialog d = new ListPurchaseDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == addpuritem || ae.getSource()==baddpurchase) {
			AddPurchaseDialog d = new AddPurchaseDialog();
			d.setVisible(true);
		}
		
		
		if (ae.getSource() == listorderitem || ae.getSource()==blistorder) {
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("List order is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			ListOrderDialog d = new ListOrderDialog();
			d.setVisible(true);
		}
		if (ae.getSource() == addorderitem || ae.getSource()==baddorder) {
			
			if(role.equalsIgnoreCase("Intruder"))
			{
			HoneyPotClient.addactivity("Add order is clicked ");
			JOptionPane.showMessageDialog(this, "No record available");
			return;
			}
			AddOrderDialog d = new AddOrderDialog();
			d.setVisible(true);
		}
		
		if (ae.getSource() == item9) {
			try
			{
				 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 SwingUtilities.updateComponentTreeUI(this);
			}
			catch(Exception e)
			{
				
			}
		}

		if (ae.getSource() == item10) {
			try
			{
				 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			 SwingUtilities.updateComponentTreeUI(this);
			}
			catch(Exception e)
			{
				
			}
		}
		
		if (ae.getSource() == item11) {
			try
			{
				 UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			 SwingUtilities.updateComponentTreeUI(this);
			}
			catch(Exception e)
			{
				
			}

		}
		
		if(ae.getSource()==exititem || ae.getSource()==bexit)
		{
			int choice = JOptionPane.showConfirmDialog(this ,"Are you sure to exit. ","Get Cofirmation",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION)		
				System.exit(0);
			
		}

	}
	
	
	
	
	
	
	
		
}


class MyWindowAdapter extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{
		System.exit(0);
	}
	}


