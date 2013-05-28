package com.sample.client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

/*
 * Products UI and to add new Orders.
 */
public class SampleCartComposite extends Composite{
	
	private final OrderServiceAsync orderService = GWT.create(OrderService.class);
	private VerticalPanel mainDisplayPanel;
	private Button checkout;
	private Label headerLabel;
	private CellTable<Product> orderTable;
	private SelectionModel<Product> selectionModel;
	private SimplePager pager;
	
	private final List<Product> PRODUCTS = Arrays.asList(
			new Product("Computer", "Computer Description", "10"), 
			new Product("Car","Car Description", "20"), 
			new Product("Fotball","Football Description", "30"), 
			new Product("Touch pad","Touchpad Description", "40"), 
			new Product("Ipad","Ipad Description", "50"), 
			new Product("Jin","Jin Description", "60"), 
			new Product("Shoes","Shoes Description", "70"), 
			new Product("Wonderland", "Wonder Description","80"));
	
	
	public SampleCartComposite() {

		checkout = new Button("Checkout");
		headerLabel = new Label("Products List");
		headerLabel.setStyleName("headerLabel");
		mainDisplayPanel = new VerticalPanel();
		setupCartDisplay();
		mainDisplayPanel.add(headerLabel);
		mainDisplayPanel.add(orderTable);
		mainDisplayPanel.add(pager);
		mainDisplayPanel.add(checkout);
		
		MyHandler handler = new MyHandler();
		checkout.addClickHandler(handler);
		
		initWidget(mainDisplayPanel);
	}
	
	
	private void setupCartDisplay(){
		
		// Create a CellTable.
		orderTable = new CellTable<Product>();
	    orderTable.setPageSize(10);
	    
	    selectionModel  = new MultiSelectionModel<Product>();
		orderTable.setSelectionModel(selectionModel,  DefaultSelectionEventManager.<Product> createCheckboxManager());

	    // Product name
	    TextColumn<Product> nameColumn = new TextColumn<Product>() {
	      @Override
	      public String getValue(Product object) {
	        return object.getProductName();
	      }
	    };
	    orderTable.addColumn(nameColumn, "Product Name");

	    // Add Description
	    TextColumn<Product> addressColumn = new TextColumn<Product>() {
	      @Override
	      public String getValue(Product object) {
	        return object.getProductDescription();
	      }
	    };
	    orderTable.addColumn(addressColumn, "Description");
	    
	    // Add price
	    TextColumn<Product> priceColumn = new TextColumn<Product>() {
	      @Override
	      public String getValue(Product object) {
	        return object.getProductPrice();
	      }
	    };
	    orderTable.addColumn(priceColumn, "Price");
	   
	    Column<Product, String> quantityColumn = new Column<Product, String>(new EditTextCell()) {
	        @Override
	        public String getValue(Product object) {
	           return "0";
	        }
	    };
	    
	    orderTable.addColumn(quantityColumn, "Quantity");
        quantityColumn.setFieldUpdater(new FieldUpdater<Product, String>() {
        @Override
        public void update(int index, Product object, String value) {
        		object.setProductQuant(value);
        	}
	    });
        
        
        Column<Product, Boolean> checkColumn = new Column<Product, Boolean>(new CheckboxCell(true, false)) {
		@Override
		    public Boolean getValue(Product object) {
				if(selectionModel.isSelected(object))
					object.setProductSelect("true");
				else 
					object.setProductSelect("false");
				
				return selectionModel.isSelected(object);
		    }
		};
		orderTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		
		// Associate an async data provider to the table : Use AsyncCallback in the method onRangeChanged
	    // to actaully get the data from the server side
	    AsyncDataProvider<Product> provider = new AsyncDataProvider<Product>() {
	      @Override
	      protected void onRangeChanged(HasData<Product> display) {
	        int start = display.getVisibleRange().getStart();
	        int end = start + display.getVisibleRange().getLength();
	        end = end >= PRODUCTS.size() ? PRODUCTS.size() : end;
	        List<Product> sub = PRODUCTS.subList(start, end);
	        updateRowData(start, sub);
	      }
	    };
	    
	    provider.addDataDisplay(orderTable);
	    provider.updateRowCount(PRODUCTS.size(), true);

	    pager = new SimplePager();
	    pager.setDisplay(orderTable);
	 	
	}
	
	
	private List<Order> getOrdersLst() {
		@SuppressWarnings("deprecation")
		List<Product> lstProd = orderTable.getDisplayedItems();
		List<Order> lstOrder = new ArrayList<Order>();

		for (int x = 0; x < lstProd.size(); x++) {
			Product prd = lstProd.get(x);
			if (prd.getProductSelect().equals("true")) {
				Order order = new Order();
				Date date = new Date(System.currentTimeMillis());
				order.setOrderdescription(" Order booked at " + date.toString());
				order.setProductname(prd.getProductName());
				order.setOroductquantity(prd.getProductQuant());
				lstOrder.add(order);
			}
		}
		return lstOrder;

	}
	  
	public VerticalPanel getMainDisplayPanel() {
		return mainDisplayPanel;
	}

	public void setMainDisplayPanel(VerticalPanel mainDisplayPanel) {
		this.mainDisplayPanel = mainDisplayPanel;
	}
	
	class MyHandler implements ClickHandler, KeyUpHandler {

		public void onClick(ClickEvent event) {
			sendNameToServer();
		}

		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				sendNameToServer();
			}
		}

		private void sendNameToServer() {
			List<Order> orderLst = getOrdersLst();
			orderService.addOrderList(orderLst, new AsyncCallback<Void>() {
				public void onSuccess(Void result) {
					System.out.println("Remote Procedure Call - Success");
					OrdersListComposite ordelList = new OrdersListComposite();
					RootPanel.get("testContainer").clear();
					RootPanel.get("testContainer").add(ordelList);
				}
				public void onFailure(Throwable caught) {
					System.out.println("Remote Procedure Call - Failure");
				}
			});

		}
	}

}
