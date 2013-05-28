package com.sample.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/*
 * Class remote interface for Orde service
 */

@RemoteServiceRelativePath("order")
public interface OrderService extends RemoteService {

	public List<Order> getOrder();
	public void addOrder(Order order);
	public void addOrderList(List<Order> orderList);

}
