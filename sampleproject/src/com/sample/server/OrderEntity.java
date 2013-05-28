package com.sample.server;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;




import com.sample.client.Order;

/*
 * @PersistenceCapable entity Object that can be saved in GAE.
 */

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class OrderEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long orderid;
	@Persistent
	private String productname;
	@Persistent
	private String productquantity;
	@Persistent
	private String orderdescription;
	
	public OrderEntity(Order order) {
		super();
		this.orderid = order.getOrderid();
		this.productname = order.getProductname();
		this.productquantity = order.getOroductquantity();
		this.orderdescription = order.getOrderdescription();
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
