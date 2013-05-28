package com.sample.client;


import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
/*
 * Composite Class after order saved.
 */

public class OrdersListComposite extends Composite{
	
	private final OrderServiceAsync orderService = GWT.create(OrderService.class);
	private VerticalPanel mainDisplayPanel;
	private CellTable<Order> ordertable;
	private List<Order> ordersList;
	private Button home;
	private Label headerLabel;
	
	
	public OrdersListComposite() {
		
		mainDisplayPanel = new VerticalPanel();
		home = new Button("Home");
		headerLabel = new Label("Orders List");
		headerLabel.setStyleName("headerLabel");
		fetchData();
		setupOrderList();
		mainDisplayPanel.add(headerLabel);
		mainDisplayPanel.add(ordertable);
		mainDisplayPanel.add(home);
		
		MyHandler handler = new MyHandler();
		home.addClickHandler(handler);
		
		initWidget(mainDisplayPanel);
	}
	
	private void fetchData() {
		orderService.getOrder(new AsyncCallback<List<Order>>() {
			@Override
			public void onSuccess(List<Order> result) {
				ordertable.setRowCount(result.size(), true);
				ordertable.setRowData(0, result);
			}

			@Override
			public void onFailure(Throwable caught) {
				ordersList = new ArrayList<Order>();
				ordertable.setRowCount(ordersList.size(), true);
				ordertable.setRowData(0, ordersList);
			}

		});
	}
	
	public void setupOrderList() {

		// Create a CellTable.
		ordertable = new CellTable<Order>();
		ordertable.setPageSize(50);

		// Order Id
		TextColumn<Order> idColumn = new TextColumn<Order>() {
			@Override
			public String getValue(Order object) {
				return object.getOrderid().toString();
			}
		};
		ordertable.addColumn(idColumn, "Order Id");

		// Product Name
		TextColumn<Order> prodnameColumn = new TextColumn<Order>() {
			@Override
			public String getValue(Order object) {
				return object.getProductname();
			}
		};
		ordertable.addColumn(prodnameColumn, "Product Name");

		// Product Quantity
		TextColumn<Order> prodquantColumn = new TextColumn<Order>() {
			@Override
			public String getValue(Order object) {
				return object.getOroductquantity();
			}
		};
		ordertable.addColumn(prodquantColumn, "Product Quantity");

		// Order Desc.
		TextColumn<Order> orderdescColumn = new TextColumn<Order>() {
			@Override
			public String getValue(Order object) {
				return object.getOrderdescription();
			}
		};
		ordertable.addColumn(orderdescColumn, "Order Desc");

	}
	
	public List<Order> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<Order> ordersList) {
		this.ordersList = ordersList;
	}
	
	// Create a handler for the sendButton and nameField
	class MyHandler implements ClickHandler, KeyUpHandler {

		public void onClick(ClickEvent event) {
			SampleCartComposite sampleCart = new SampleCartComposite();
			RootPanel.get("testContainer").clear();
			RootPanel.get("testContainer").add(sampleCart);
		}

		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				SampleCartComposite sampleCart = new SampleCartComposite();
				RootPanel.get("testContainer").clear();
				RootPanel.get("testContainer").add(sampleCart);
			}
		}
	}
}
