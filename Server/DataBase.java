

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DataBase {

	private static Connection con;

	static {

		try {
			 //String url="jdbc:mysql://localhost:3306/share?user=root&password=mousam";
		   	 Class.forName("com.mysql.jdbc.Driver");
		   	 //con = DriverManager.getConnection(url);
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException ce) {
			System.out.println("Error in loading the class ");
		}

		try {

			String url="jdbc:mysql://localhost:3306/honeypot?user=root&password=ravi";
			con = DriverManager.getConnection(url);
			 //con = DriverManager.getConnection("jdbc:odbc:shop");
			
			

		}

		catch (SQLException se) {
			System.out.println("SQL Exception generated");
		}

	}

	public static boolean check(UserInfo u) {

		try {
			
			String query ="";
			if(u.getCategory().equals("Select"))
			query = "select * from userinfo where username = ? and password = ? and role='ADMIN' ";
			else
				query = "select * from userinfo where username = ? and password = ? and category = ? and role='USER' ";
			
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			if(!u.getCategory().equals("Select"))
				ps.setString(3, u.getCategory());
			

			ResultSet rs = ps.executeQuery();
			
			if (rs.next() == true)
			{
             u.setRole(rs.getString("ROLE"));
				return true;
			}	

			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Error in loading driver and making connection");
		}

		return false;
	}

	// //////////////////////////////////////////////////////user/////////////////
	
///////////////////////////////////Add Product Details Start////////////////////////
	public static int getNextProductId() {

		int nextid = 0;
		try {
			String query = "select max(pid)from productmaster";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			System.out.println("Error in loading driver and making connection");
		}

		return ++nextid;
	}

	public static int addproduct(Product pm) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into productmaster values(?,?,?,?,?)");
			ps.setString(1,pm.getPid());
			ps.setString(2,  pm.getPdescription());
			ps.setInt(3, pm.getPqty());
			ps.setInt(4, pm.getPrate());
			ps.setInt(5, pm.getPreorderlvl());

			x = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	public static int modifydecreaseproduct(Product pm ) {
		
		Product oldproduct = getProduct(pm.getPid());
		int newqty =oldproduct.getPqty()- pm.getPqty(); 
		System.out.println("oldproduct.getPqty()" + oldproduct.getPqty());
		
		System.out.println("pm.getPqty()  " + pm.getPqty());
		System.out.println("pm.getPrate()  " + pm.getPrate());
		System.out.println("pm.getPid()  " + pm.getPid());
		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("update productmaster set  pqty = ?  where pid = ? ");

			
	
			
			ps.setInt(1, newqty);
			
			ps.setString(2,pm.getPid());
			x = ps.executeUpdate();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return x;
	}

	public static int modifyincreaseproduct(Product pm ) {

		Product oldproduct = getProduct(pm.getPid());
		int newqty =oldproduct.getPqty()+ pm.getPqty(); 
		System.out.println("pm.getPqty()  " + pm.getPqty());
		System.out.println("pm.getPrate()  " + pm.getPrate());
		System.out.println("pm.getPid()  " + pm.getPid());
		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("update productmaster set  pqty= ?,prate= ? where pid=?");

			
	
			ps.setInt(1, newqty);
			ps.setInt(2, pm.getPrate());			
			ps.setString(3,pm.getPid());
			x = ps.executeUpdate();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return x;
	}

	public static Vector getCategoryList() {

		Vector v = new Vector();

		try {

			PreparedStatement ps = con
					.prepareStatement("select cname from categorymaster");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				v.add(rs.getString("cname"));				
				
				

			}
			
			rs.close();
			ps.close();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return v;
	}
	
	public static ArrayList getProductList() {

		ArrayList productlist = new ArrayList();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from productmaster");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product pm = new Product();
				pm.setPid(rs.getString("pid"));
				pm.setPdescription(rs.getString("pdescription"));				
				pm.setPqty(rs.getInt("pqty"));
				pm.setPrate(rs.getInt("prate"));
				pm.setPreorderlvl(rs.getInt("preorderlevel"));
				productlist.add(pm);

			}
			
			rs.close();
			ps.close();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return productlist;
	}

	public static Product getProduct(String pid) {

		
		Product pm = new Product();
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from productmaster where pid=?");
			ps.setString(1, pid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				

				pm.setPid(rs.getString("pid"));
				pm.setPdescription(rs.getString("pdescription"));				
				pm.setPqty(rs.getInt("pqty"));
				pm.setPrate(rs.getInt("prate"));
				pm.setPreorderlvl(rs.getInt("preorderlevel"));
		

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return pm;
	}
	
	public static Vector getProductModel() {

		Vector v1 = new Vector();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from productmaster");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();

				v.addElement(rs.getString("pid"));
				v.addElement(rs.getString("pdescription"));				
				v.addElement(rs.getInt("pqty")+"");
				v.addElement(rs.getInt("prate")+"");
				v.addElement(rs.getInt("preorderlevel")+"");
				v1.add(v);

			}
			
			rs.close();
			ps.close();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return v1;
	}
	///////////////////////////////Add Product Detaiils End////////////////////////


	
	

	// ///////////////////////////////////////////////// Adding Customer Info
	// ////////////////////////////////////////////////////
	public static int getNextSupplierId() {

		int nextid = 0;
		try {
			String query = "select max(supplierid)from supplier";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			System.out.println("Error in loading driver and making connection");
		}

		return ++nextid;
	}

	public static int addsupplier(Supplier s) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into supplier values(?,?,?,?,?)");
			ps.setString(1, s.getSupplierid());
			ps.setString(2, s.getSname());
			ps.setString(3, s.getSaddress());
			ps.setString(4, s.getScity());
			ps.setString(5, s.getSpincode());

			x = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	public static int modifysupplier(Supplier s) {
		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("update supplier set  sname= ? ,saddress=?,scity= ?,spincode = ? where supplierid=?");

			
			ps.setString(1, s.getSname());
			ps.setString(2, s.getSaddress());
			ps.setString(3, s.getScity());
			ps.setString(4, s.getSpincode());
			ps.setString(5, s.getSupplierid());

			x = ps.executeUpdate();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return x;
	}

	public static int deletesupplier(ArrayList supplieridlist) {
		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("delete from supplier  where supplierid = ?");
			for(int k = 0;k<supplieridlist.size();k++)
			{
			ps.setString(1, (String)supplieridlist.get(k));

			x = ps.executeUpdate();
			}

		} catch (SQLException se) {
//			System.out.println("SQL Exception generated" + se);
		}

		return x;
	}

	public static ArrayList getSuplierList() {

		ArrayList suppList = new ArrayList();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from supplier");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Supplier s = new Supplier();

				s.setSupplierid(rs.getString("supplierid"));
				s.setSname(rs.getString("sname"));				
				s.setSaddress(rs.getString("saddress"));
				s.setScity(rs.getString("scity"));
				s.setSpincode(rs.getString("spincode"));
				suppList.add(s);

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return suppList;
	}

	public static Supplier getSuplier(String supplierid) {

		System.out.println("Inside supplier");
		Supplier s = new Supplier();
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from supplier where supplierid=?");
			ps.setString(1, supplierid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				

				s.setSupplierid(rs.getString("supplierid"));
				s.setSname(rs.getString("sname"));				
				s.setSaddress(rs.getString("saddress"));
				s.setScity(rs.getString("scity"));
				s.setSpincode(rs.getString("spincode"));
		

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return s;
	}

	public static int getNextOrderId() {

		int nextid = 0;
		try {
			String query = "select max(orderid)from ordermaster";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			System.out.println("Error in loading driver and making connection");
		}

		return ++nextid;
	}

	public static int addordermaster(OrderMaster om) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into ordermaster values(?,?,?,?,?,?,?,?)");
			ps.setString(1, om.getOrderid());
			ps.setString(2, om.getSupplier().getSupplierid());
			ps.setInt(3, om.getOrderday());
			ps.setInt(4, om.getOrdermonth());
			ps.setInt(5, om.getOrderyear());
			ps.setInt(6, om.getOrderexpenses());
			ps.setInt(7, om.getOrdertotal());
			ps.setInt(8, om.getOrdertotal());
             addorderdetail(om.getItemlist());
			x = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	public static int addorderdetail(ArrayList orderdetailist) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into orderdetail values(?,?,?,?)");
			for(int k=0;k<orderdetailist.size();k++)
			{
				OrderDetail od = (OrderDetail)orderdetailist.get(k);
			ps.setString(1, od.getOrderid());
			ps.setInt(2, od.getProduct().getPrate());
			ps.setInt(3, od.getProduct().getPqty());
			ps.setString(4, od.getProduct().getPdescription());
			
			

			x = ps.executeUpdate();
			}
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	
	public static OrderMaster getOrder(String orderid) {

		System.out.println("orderid = " + orderid);
		ArrayList suppList = new ArrayList();
		OrderMaster om = new OrderMaster();
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from ordermaster where orderid=?");
			ps.setString(1, orderid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Rrcord foundddddddddddddddddddddddd");

				om.setOrderid(rs.getString("orderid"));
				om.setSupplier(getSuplier(rs.getString("ordersid")));
				om.setOrderday(rs.getInt("orderday"));				
				om.setOrdermonth(rs.getInt("ordermonth"));
				om.setOrderyear(rs.getInt("orderyear"));
				om.setOrderexpenses(rs.getInt("orderexpenses"));
				om.setOrdertotal(rs.getInt("ordertotal"));
				om.setItemlist(getOrderDetailList(orderid));
				

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return om;
	}

	public static ArrayList getOrderList() {

		ArrayList orderList = new ArrayList();
		
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from ordermaster ");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			OrderMaster om = new OrderMaster();	
				om.setOrderid(rs.getString("orderid"));
				om.setSupplier(getSuplier(rs.getString("ordersid")));
				om.setOrderday(rs.getInt("orderday"));				
				om.setOrdermonth(rs.getInt("ordermonth"));
				om.setOrderyear(rs.getInt("orderyear"));
				om.setOrderexpenses(rs.getInt("orderexpenses"));
				om.setOrdertotal(rs.getInt("ordertotal"));
				om.setItemlist( getOrderDetailList( om.getOrderid()));
			orderList.add(om);
								

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("getOrderList SQL Exception generated" + se);
		}

		return orderList;
	}


	public static ArrayList getOrderDetailList(String orderid) {

		System.out.println("Inside getOrderDetailList");
		ArrayList orderdetailist = new ArrayList();
		
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from orderdetail where orderid=?");
			ps.setString(1, orderid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("111111111111111111111");
 OrderDetail od = new OrderDetail(); 
				od.setOrderid(rs.getString("orderid"));
				Product p = new Product();
				
				p.setPdescription(rs.getString("productcategory"));
				System.out.println("2222222222222222222");
				p.setPqty(rs.getInt("productqty"));
				p.setPrate(rs.getInt("productrate"));
				System.out.println("333333333333333333");
				od.setProduct(p);
				orderdetailist.add(od);

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("getOrderDetailList SQL Exception generated" + se);
		}

		return orderdetailist;
	}

	public static Vector getSuplierModel() {

		Vector v1 = new Vector();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from supplier");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();

				v.addElement(rs.getString("supplierid"));
				v.addElement(rs.getString("sname"));				
				v.addElement(rs.getString("saddress"));
				v.addElement(rs.getString("scity"));
				v.addElement(rs.getString("spincode"));
				v1.add(v);

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return v1;
	}

	
	public static int addsalesmaster(SalesMaster sm) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into salesmaster values(?,?,?,?,?,?,?)");
			ps.setString(1, sm.getSbillno());
			ps.setString(2, sm.getScname());
			ps.setInt(3, sm.getSday());
			ps.setInt(4, sm.getSmonth());
			ps.setInt(5, sm.getSyear());
			ps.setInt(6, sm.getSexpenses());
			ps.setInt(7, sm.getStotal());
             addsalesdetail(sm.getItemlist());
			x = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	public static int addsalesdetail(ArrayList salesdetailist) {

		
		System.out.println("salesdetailist>>>>>>>>" + salesdetailist);
		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into salesdetail values(?,?,?,?,?)");
			for(int k=0;k<salesdetailist.size();k++)
			{
				SalesDetail sd = (SalesDetail)salesdetailist.get(k);
			ps.setString(1, sd.getSbillno());
			ps.setString(2, sd.getProduct().getPid());
			ps.setInt(3, sd.getProduct().getPrate());
			ps.setInt(4, sd.getProduct().getPqty());
			ps.setString(5, sd.getProduct().getPdescription());
			
			

			x = ps.executeUpdate();
			}
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	
	public static SalesMaster getSales(String billno) {

		
		ArrayList suppList = new ArrayList();
		SalesMaster sm = new SalesMaster();
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from salesmaster where sbillno=?");
			ps.setString(1, billno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Rrcord foundddddddddddddddddddddddd");

				sm.setSbillno(rs.getString("sbillno"));
				sm.setScname(rs.getString("scname"));				
				sm.setSday(rs.getInt("sday"));
				sm.setSmonth(rs.getInt("smonth"));
				sm.setSyear(rs.getInt("syear"));
				sm.setSexpenses(rs.getInt("sexpenses"));
				sm.setStotal(rs.getInt("stotal"));
				sm.setItemlist(getSalesDetailList(billno));
				

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return sm;
	}

	public static ArrayList getSalesList() {

		ArrayList salesList = new ArrayList();
		
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from salesmaster ");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			 SalesMaster sm = new SalesMaster();
			 sm.setSbillno(rs.getString("sbillno"));
				sm.setScname(rs.getString("scname"));				
				sm.setSday(rs.getInt("sday"));
				sm.setSmonth(rs.getInt("smonth"));
				sm.setSyear(rs.getInt("syear"));
				sm.setSexpenses(rs.getInt("sexpenses"));
				sm.setStotal(rs.getInt("stotal"));
				sm.setItemlist(getSalesDetailList(sm.getSbillno()));

			salesList.add(sm);
								

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("getOrderList SQL Exception generated" + se);
		}

		return salesList;
	}


	public static ArrayList getSalesDetailList(String billno) {

		ArrayList salesdetailist = new ArrayList();
		
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from salesdetail where sbillno=?");
			ps.setString(1, billno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("111111111111111111111");
 SalesDetail sd = new SalesDetail(); 
 Product p = new Product();
				sd.setSbillno(rs.getString("sbillno"));
				p.setPid(rs.getString("pid"));
				System.out.println("2222222222222222222");
				p.setPrate(rs.getInt("prate"));
				p.setPqty(rs.getInt("pqty"));
				p.setPdescription(rs.getString("pcategory"));
				System.out.println("333333333333333333");
				sd.setProduct(p);
				salesdetailist.add(sd);

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("salesdetailist SQL Exception generated" + se);
		}

		return salesdetailist;
	}

	public static int getNextSalesId() {

		int nextid = 0;
		try {
			String query = "select max(sbillno) from salesmaster";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in loading driver and making connection");
		}

		return ++nextid;
	}

	public static int addpurchasemaster(PurchaseMaster pm) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into purchasemaster values(?,?,?,?,?,?,?)");
			ps.setString(1, pm.getPinvoiceno());
			ps.setString(2, pm.getPorderid());
			ps.setInt(3, pm.getPday());
			ps.setInt(4, pm.getPmonth());
			ps.setInt(5, pm.getPear());
			ps.setInt(6, pm.getPexpenses());
			ps.setInt(7, pm.getPtotal());
             addpurchasedetail(pm.getItemlist());
			x = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("addpurchasedetail SQL Exception generated" + se);
		}
				return x;

	}

	public static int addpurchasedetail(ArrayList purchaseetailist) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into purchasedetail values(?,?,?,?,?)");
			for(int k=0;k<purchaseetailist.size();k++)
			{
				PurchaseDetail pd = (PurchaseDetail)purchaseetailist.get(k);
			ps.setString(1, pd.getPinvoiceno());
			ps.setString(2, pd.getProduct().getPid());
			
			ps.setInt(3, pd.getProduct().getPrate());
			ps.setInt(4, pd.getProduct().getPqty());
			ps.setString(5, pd.getProduct().getPdescription());
			modifyincreaseproduct(pd.getProduct());
			
			

			x = ps.executeUpdate();
			}
		} catch (SQLException se) {
			System.out.println("addpurchasedetail SQL Exception generated" + se);
		}
				return x;

	}

	
	public static  PurchaseMaster getPurchases(String invoiceno) {

		
		ArrayList suppList = new ArrayList();
		PurchaseMaster pm = new PurchaseMaster();
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from purchasemaster where pinvoiceno=?");
			ps.setString(1, invoiceno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Rrcord foundddddddddddddddddddddddd");

				pm.setPinvoiceno(rs.getString("pinvoiceno"));
				pm.setPorderid(rs.getString("porderid"));				
				pm.setPday(rs.getInt("pday"));
				pm.setPmonth(rs.getInt("pmonth"));
				pm.setPear(rs.getInt("pyear"));
				pm.setPexpenses(rs.getInt("pexpensesl"));
				pm.setPtotal(rs.getInt("ptotal"));
				pm.setItemlist(getPurchaseDetailList(invoiceno));
				

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return pm;
	}

	public static ArrayList getPurchaseList() {

		ArrayList purchaseList = new ArrayList();
		
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from purchasemaster ");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				PurchaseMaster  p  = new PurchaseMaster();
				p.setPinvoiceno(rs.getString("pinvoiceno"));
				p.setPorderid(rs.getString("porderid"));	
						
				p.setPtotal(rs.getInt("ptotal"));
				p.setPday(rs.getInt("pday"));
				p.setPmonth(rs.getInt("pmonth"));
				p.setPear(rs.getInt("pyear"));
				
				
				purchaseList.add(p);
								

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("getPurchaseList SQL Exception generated" + se);
		}

		return purchaseList;
	}


	public static ArrayList getPurchaseDetailList(String pinvoiceno) {

		ArrayList purchasedetailist = new ArrayList();
		
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from purchasedetail where pinvoiceno=?");
			ps.setString(1, pinvoiceno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("111111111111111111111");
        PurchaseDetail pd = new PurchaseDetail();
        Product p = new Product();
				pd.setPinvoiceno(rs.getString("pinvoiceno"));
				p.setPid(rs.getString("pid"));
				
				p.setPrate(rs.getInt("prate"));
				p.setPqty(rs.getInt("pqty"));
				p.setPdescription(rs.getString("pcategory"));
				System.out.println("333333333333333333");
				pd.setProduct(p);
				purchasedetailist.add(pd);

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("getOrderDetailList SQL Exception generated" + se);
		}

		return purchasedetailist;
	}

	
	public static int getNextPurchaseId() {

		int nextid = 0;
		try {
			String query = "select max(pinvoiceno)from purchasemaster";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in loading driver and making connection");
		}

		return ++nextid;
	}


	
	public static int getNextProductId(String category) {

		int nextid = 0;
		try {
			String query = "select max(pid)from purchasemaster where pcategory=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, category);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			System.out.println("Error in loading driver and making connection");
		}

		return ++nextid;
	}
	///////////////////////////////////Receipts start///////////////////////
	public static int getNextReceiptId() {

		int nextid = 0;
		try {
			String query = "select max(receiptid)from receipts";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in getNextReceiptId");
		}

		return ++nextid;
	}

	public static int addreceipt(Receipts r) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into receipts values(?,?,?,?,?,?,?)");
			ps.setString(1,r.getReceiptid());
			ps.setString(2,  r.getBillno());
			ps.setInt(3, r.getTotal());
			ps.setInt(4, r.getAmount());
			ps.setInt(5, r.getDay());
			ps.setInt(6, r.getMonth());
			ps.setInt(7, r.getYear());
			

			x = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	
		
	public static ArrayList getReceiptList() {

		ArrayList receiptlist = new ArrayList();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from receipts");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Receipts r = new Receipts();
				r.setReceiptid(rs.getString("receiptid"));
				r.setBillno(rs.getString("billno"));				
				r.setTotal(rs.getInt("total"));
				
				r.setBalance(rs.getInt("due"));
				r.setDay(rs.getInt("day"));
				r.setMonth(rs.getInt("month"));
				r.setYear(rs.getInt("year"));
				
				receiptlist.add(r);

			}
			
			rs.close();
			ps.close();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQL Exception generated" + se);
		}

		return receiptlist;
	}

	public static Receipts getReceipts(String receiptsid) {

		
		Receipts r = new Receipts();
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from productmaster where pid=?");
			ps.setString(1, receiptsid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				

				r.setReceiptid(rs.getString("receiptid"));
				r.setBillno(rs.getString("billno"));				
				r.setTotal(rs.getInt("total"));
				r.setAmount(rs.getInt("amount"));
				r.setBalance(rs.getInt("balance"));
				r.setDay(rs.getInt("day"));
				r.setMonth(rs.getInt("month"));
				r.setYear(rs.getInt("year"));
				
		

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return r;
	}
	
	public static Vector getReceiptsModel() {

		Vector v1 = new Vector();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from receipts");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();

				v.addElement(rs.getString("receiptid"));
				v.addElement(rs.getString("billno"));				
				v.addElement(rs.getInt("total")+"");
				v.addElement(rs.getInt("amount")+"");
				v.addElement(rs.getInt("balance")+"");
				v.addElement(rs.getInt("day")+"");
				v.addElement(rs.getInt("month")+"");
				v.addElement(rs.getInt("year")+"");
				v1.add(v);

			}
			
			rs.close();
			ps.close();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return v1;
	}
	///////////////////////////////Add Product Detaiils End////////////////////////
	////////////////////////////////////Receipts end//////////////////////
	
	
///////////////////////////////////Payments start///////////////////////
	public static int getNextPaymentId() {

		int nextid = 0;
		try {
			String query = "select max(paymentid)from payments";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextid = rs.getInt(1);

			}

		} catch (Exception e) {
			System.out.println("Error in getNextPaymentId");
		}

		return ++nextid;
	}

	public static int addpayment(Payments r) {

		int x = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into payments values(?,?,?,?,?,?,?)");
			ps.setString(1,r.getPaymentid());
			ps.setString(2,  r.getInvoiceno());
			ps.setInt(3, r.getTotal());
			ps.setInt(4, r.getAmount());
			ps.setInt(5, r.getDay());
			ps.setInt(6, r.getMonth());
			ps.setInt(7, r.getYear());
			

			x = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}
				return x;

	}

	
		
	public static ArrayList getPaymentList() {

		ArrayList paymentlist = new ArrayList();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from payments");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Payments r = new Payments();
				r.setPaymentid(rs.getString("paymentid"));
				r.setInvoiceno(rs.getString("invoiceno"));				
				r.setTotal(rs.getInt("total"));
				
				r.setBalance(rs.getInt("balance"));
				r.setDay(rs.getInt("day"));
				r.setMonth(rs.getInt("month"));
				r.setYear(rs.getInt("year"));
				
				paymentlist.add(r);

			}
			
			rs.close();
			ps.close();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return paymentlist;
	}

	public static Payments getPayments(String paymentsid) {

		
		Payments r = new Payments();
		try {

			PreparedStatement ps = con
					.prepareStatement("select * from productmaster where pid=?");
			ps.setString(1, paymentsid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				

				r.setPaymentid(rs.getString("paymentid"));
				r.setInvoiceno(rs.getString("invoiceno"));				
				r.setTotal(rs.getInt("total"));
				r.setAmount(rs.getInt("amount"));
				r.setBalance(rs.getInt("balance"));
				r.setDay(rs.getInt("day"));
				r.setMonth(rs.getInt("month"));
				r.setYear(rs.getInt("year"));
				
		

			}
			rs.close();
			ps.close();
			

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return r;
	}
	
	public static Vector getPaymentsModel() {

		Vector v1 = new Vector();

		try {

			PreparedStatement ps = con
					.prepareStatement("select * from payments");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();

				v.addElement(rs.getString("paymentid"));
				v.addElement(rs.getString("invoiceno"));				
				v.addElement(rs.getInt("total")+"");
				v.addElement(rs.getInt("amount")+"");
				v.addElement(rs.getInt("balance")+"");
				v.addElement(rs.getInt("day")+"");
				v.addElement(rs.getInt("month")+"");
				v.addElement(rs.getInt("year")+"");
				v1.add(v);

			}
			
			rs.close();
			ps.close();

		} catch (SQLException se) {
			System.out.println("SQL Exception generated" + se);
		}

		return v1;
	}
	
	////////////////////////////////////Payments end//////////////////////


	public static void main(String args[])
	{
		
		System.out.println(getPaymentList());
		
	}
}
