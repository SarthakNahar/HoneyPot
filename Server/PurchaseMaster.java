import java.util.ArrayList;


public class PurchaseMaster {
	
	private String pinvoiceno;
	private String porderid;
	
	private int pday;
	private int pmonth;
	private int pear;
	private int pexpenses;
	private int ptotal;
	
	public ArrayList itemlist = new ArrayList();

	public ArrayList getItemlist() {
		return itemlist;
	}
	public void setItemlist(ArrayList itemlist) {
		this.itemlist = itemlist;
	}
	public int getPday() {
		return pday;
	}
	public void setPday(int pday) {
		this.pday = pday;
	}
	public int getPear() {
		return pear;
	}
	public void setPear(int pear) {
		this.pear = pear;
	}
	public int getPexpenses() {
		return pexpenses;
	}
	public void setPexpenses(int pexpenses) {
		this.pexpenses = pexpenses;
	}
	public String getPinvoiceno() {
		return pinvoiceno;
	}
	public void setPinvoiceno(String pinvoiceno) {
		this.pinvoiceno = pinvoiceno;
	}
	public int getPmonth() {
		return pmonth;
	}
	public void setPmonth(int pmonth) {
		this.pmonth = pmonth;
	}
	public String getPorderid() {
		return porderid;
	}
	public void setPorderid(String porderid) {
		this.porderid = porderid;
	}
	public int getPtotal() {
		return ptotal;
	}
	public void setPtotal(int ptotal) {
		this.ptotal = ptotal;
	}
		
	

}
