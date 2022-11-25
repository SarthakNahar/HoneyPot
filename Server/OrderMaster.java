import java.util.ArrayList;


public class OrderMaster {
	
	private String orderid;
	private Supplier supplier = new Supplier();
	private int orderday;
	private int ordermonth;
	private int orderyear;
	private int orderexpenses;
	private int ordertotal;
	private String orderstatus;
	public ArrayList itemlist = new ArrayList();
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public int getOrderday() {
		return orderday;
	}
	public void setOrderday(int orderday) {
		this.orderday = orderday;
	}
	public int getOrderexpenses() {
		return orderexpenses;
	}
	public void setOrderexpenses(int orderexpenses) {
		this.orderexpenses = orderexpenses;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getOrdermonth() {
		return ordermonth;
	}
	public void setOrdermonth(int ordermonth) {
		this.ordermonth = ordermonth;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public int getOrdertotal() {
		return ordertotal;
	}
	public void setOrdertotal(int ordertotal) {
		this.ordertotal = ordertotal;
	}
	public int getOrderyear() {
		return orderyear;
	}
	public void setOrderyear(int orderyear) {
		this.orderyear = orderyear;
	}
	public ArrayList getItemlist() {
		return itemlist;
	}
	public void setItemlist(ArrayList itemlist) {
		this.itemlist = itemlist;
	}

}
