package com.sample.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OrderServiceAsync {

	void getOrder(AsyncCallback<List<Order>> callback);
	void addOrder(Order order, AsyncCallback<Void> callback);
	void addOrderList(List<Order> orderList, AsyncCallback<Void> callback);
	

}
