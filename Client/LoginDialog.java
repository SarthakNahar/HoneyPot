import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginDialog extends JDialog implements ActionListener
{
	
	JLabel l1,l2,l3,l4;
	JTextField t;
	JPasswordField t1;
	JButton b1,b2;
	
	String s,s1;
	
	int count=0;
	
	JComboBox categorycombo;
	
	
	public LoginDialog()
	{
		setTitle("Login");
		setModal(true);
		setLayout(null);
		setSize(600,800);
		setLocation(100,100);
		setResizable(false);
		Color co=new Color(20,200,140);
//	getContentPane().setBackground(co);
		
	
		l1  = GeneralUtility.getHeadingLabel("Login Screen");
		l1.setBounds(250,25,450,40);
		add(l1);
		l2=new JLabel("USERNAME :");
		l2.setBounds(210,140,200,30);
		add(l2);
	
		l2.setFont(GeneralUtility.getTextFont());
		co=new Color(0,0,150);
		l2.setForeground(co);
		t=new JTextField();
		t.setBounds(380,140,120,25);
		add(t);
		co=new Color(225,225,225);
		

		l3=new JLabel("PASSWORD :");
		l3.setBounds(210,210,200,30);
		add(l3);
		
		l3.setFont(GeneralUtility.getTextFont());
		co=new Color(0,0,150);
		l3.setForeground(co);
		t1=new JPasswordField();
		t1.setBounds(380,210,120,25);
		add(t1);
		
		
		l4=new JLabel("CATEGORY :");
		l4.setBounds(210,280,200,30);
		add(l4);
		
		l4.setFont(GeneralUtility.getTextFont());
		co=new Color(0,0,150);
		l4.setForeground(co);
		
		
		Vector v = DataBase.getCategoryList();
		v.add(0,"Select");
		v.add(0,"Apparels");
		
		categorycombo = new JComboBox(v);
		
		categorycombo.setBounds(380,280,120,25);
		add(categorycombo);
		
		
		co=new Color(225,225,225);
		

		b1=new JButton("OK");
		t1.addActionListener(this);
		b1.setBounds(210,380,100,30);
		add(b1);
		
//		b1.setBackground(GeneralUtility.getButtonColor());
		b1.addActionListener(this);

		b2=new JButton("CANCEL");
		b2.setBounds(380,380,100,30);
		add(b2);
		
//		b2.setBackground(GeneralUtility.getButtonColor());
		b2.addActionListener(this);
		
		
		//t.setText("store");
		//t1.setText("store");
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			
			s=t.getText();
			s1=t1.getText();
			
			if(s.length()==0 && s1.length()==0)
			{
				JOptionPane.showMessageDialog(null,"Enter the details");
				t.requestFocus();
				return;
			}
			else if(s.length()==0)
			{
				JOptionPane.showMessageDialog(null,"Enter the login");
				t.requestFocus();
				return;
			}
			else if(s1.length()==0)
			{
				JOptionPane.showMessageDialog(null,"Enter the password");
				t1.requestFocus();
				return;
			}
				
			UserInfo u = new UserInfo();
			u.setUsername(t.getText());
			u.setPassword ( new String(t1.getPassword()));
			u.setCategory(categorycombo.getSelectedItem().toString());
			
			if(DataBase.check(u))
			{
				if(u.getRole().equals("ADMIN"))
					JOptionPane.showMessageDialog(this, "Welcome Admin");
				else
					if(u.getRole().equals("USER"))
						JOptionPane.showMessageDialog(this, "Welcome User");
					else
					{
						count++;
						if(count<=2)
						{
							JOptionPane.showMessageDialog(this, "Invalid username/password");
							return;
						}	
						else
						{
							JOptionPane.showMessageDialog(this, "Welcome");
						}	
					}		
			
					
			
			}
			else
			{
				count++;
				if(count<=2)
				{
					JOptionPane.showMessageDialog(this, "Invalid username/password");
					return;
				}	
				else
				{
					JOptionPane.showMessageDialog(this, "Welcome");
				}	
				
			}	
			
			MainFrame mf = new MainFrame(u.getRole());
			setVisible(false);
			dispose();
			mf.setVisible(true);
			
		}
		
		
		if(e.getSource()==b2)
		{
			System.exit(0);
		}
	}
		
}

