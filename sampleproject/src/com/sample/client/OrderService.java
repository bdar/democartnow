package com.sample.client;

import java.util.List;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("order")
public interface OrderService extends RemoteService {

	public List<Order> getOrder();
	public void addOrder(Order order);
	public void addOrderList(List<Order> orderList);

}
