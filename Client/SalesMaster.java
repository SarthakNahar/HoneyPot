import java.util.ArrayList;


public class SalesMaster {
	
	private String sbillno;
	private String scname;
	private int sday;
	private int smonth;
	private int syear;
	private int sexpenses;
	private int stotal;
	
	public ArrayList itemlist = new ArrayList();

	public String getScname() {
		return scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}

	public ArrayList getItemlist() {
		return itemlist;
	}

	public void setItemlist(ArrayList itemlist) {
		this.itemlist = itemlist;
	}

	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}

	public int getSday() {
		return sday;
	}

	public void setSday(int sday) {
		this.sday = sday;
	}

	public int getSexpenses() {
		return sexpenses;
	}

	public void setSexpenses(int sexpenses) {
		this.sexpenses = sexpenses;
	}

	public int getSmonth() {
		return smonth;
	}

	public void setSmonth(int smonth) {
		this.smonth = smonth;
	}

	public int getStotal() {
		return stotal;
	}

	public void setStotal(int stotal) {
		this.stotal = stotal;
	}

	public int getSyear() {
		return syear;
	}

	public void setSyear(int syear) {
		this.syear = syear;
	}

	
}
