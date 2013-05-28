package com.sample.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * Async interface for Remote interface for Order service
 */

public interface OrderServiceAsync {

	void getOrder(AsyncCallback<List<Order>> callback);
	void addOrder(Order order, AsyncCallback<Void> callback);
	void addOrderList(List<Order> orderList, AsyncCallback<Void> callback);
	

}
