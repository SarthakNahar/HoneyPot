import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;


public class GeneralUtility {
	
	public static  JComboBox getDayCombo()
	{
	JComboBox j = new JComboBox();
	j.addItem("Day");
	for(int k=1;k<=31;k++)
		j.addItem(k+"");
	
	
	return j;
	}
	
	public static  JComboBox getMonthCombo()
	{
	JComboBox j = new JComboBox();
	j.addItem("Month");
	for(int k=1;k<=12;k++)
		j.addItem(k+"");
	
	
	return j;
	}
	
	public static  JComboBox getYearCombo()
	{
	JComboBox j = new JComboBox();
	j.addItem("Year");
	for(int k=1990;k<=2050;k++)
		j.addItem(k+"");
	
	
	return j;
	}
	
	public static Color getColor()
	{
		Color c = new Color(20,200,140);
		return c;
	}
	
	public static Color getHeadingColor()
	{
		Color c = new Color(20,200,140);
		return c;
	}
	
	
	public static JLabel getHeadingLabel(String heading)
	{
	JLabel	l1=new JLabel(heading);
		l1.setBounds(150,25,450,40);
		
	Font 	f=new Font("Times New Roman",Font.ITALIC + Font.BOLD,30);
		l1.setFont(f);
	Color	co=new Color(200,20,60);
		l1.setForeground(co);
		
		return l1;
	}
	
	public static Font  getTextFont()
	{
		
	Font 	f=new Font("Arial",Font.BOLD,16);
	
	return f;

	}
	
	public static Color getButtonColor()
	{
		Color co=new Color(0,120,100);
		return co;
	}
	public static ArrayList convertarrayintostringlist(int a[])
	{
		ArrayList al = new ArrayList();
		for(int k=0;k<a.length;k++)
			al.add(a[k]+"");
	
	return al;
	}

	
	public static boolean checkDataCombo(JComboBox daycombo,JComboBox monthcombo,JComboBox yearcombo)
	{
		if(daycombo.getSelectedIndex()==0 || monthcombo.getSelectedIndex()==0 || yearcombo.getSelectedIndex()==0)
			return false;
			else
				return true;
	}
	
	
	public static ArrayList convertarrayintostringlist(String a[])
	{
		ArrayList al = new ArrayList();
		for(int k=0;k<a.length;k++)
			al.add(a[k]);
	
	return al;
	}
	
	
	
}

