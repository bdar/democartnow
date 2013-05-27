package com.sample.client;

import java.io.Serializable;

public class Order implements Serializable{
	
	/*
	 * Class is not really required added for safty for GAE.
	 */
	
	
	private static final long serialVersionUID = 1L;
	private Long orderid;
	private String productname;
	private String productquantity;
	private String orderdescription;
	
	
	public Order() {
		super();
		
	}
	
	public Order(Long orderid, String productname, String productquantity, String orderdescription) {
		super();
		this.orderid = orderid;
		this.productname = productname;
		this.productquantity = productquantity;
		this.orderdescription = orderdescription;
	}
	
	public Order(String productname, String productquantity, String orderdescription) {
		super();
		this.productname = productname;
		this.productquantity = productquantity;
		this.orderdescription = orderdescription;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	
	public String getOroductquantity() {
		return productquantity;
	}

	public void setOroductquantity(String productquantity) {
		this.productquantity = productquantity;
	}


	public String getOrderdescription() {
		return orderdescription;
	}
	
	public void setOrderdescription(String orderdescription) {
		this.orderdescription = orderdescription;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", productname=" + productname  + ", productquantity=" + productquantity  + ", orderdescription=" + orderdescription + "]";
	}

}
