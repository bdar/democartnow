package com.sample.server;

import java.util.ArrayList;
import java.util.List;
import com.sample.client.Order;
import com.sample.client.OrderService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *  Persistence Manager JPA / Hibernate Equivalent to save and order objects.
 */
public class OrderServiceImpl extends RemoteServiceServlet implements OrderService {
	
	private static final long serialVersionUID = 1L;
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class.getName());

	@Override
	public void addOrder(Order order) {
		
		PersistenceManager pm = getPersistenceManager();
		try {
			OrderEntity orderEntity= new OrderEntity (order);
			pm.makePersistent(orderEntity);
		
		}catch(Exception e){
			//System.out.println("Exception addOrder");
			LOG.log(Level.SEVERE, "Exception addOrder");
			e.printStackTrace();
		} 
		finally {
			pm.close();
		}
		
	}

	@Override
	public void addOrderList(List<Order> orderList) {
		// TODO Auto-generated method stub
		
		PersistenceManager pm = getPersistenceManager();
		try {
			
			for (Order or : orderList) {
				OrderEntity orderEntity= new OrderEntity (or);
				pm.makePersistent(orderEntity);
			}
			
			pm.flush();
			pm.refreshAll();
			pm.checkConsistency();
			
		}catch(Exception e){
			LOG.log(Level.SEVERE, "Exception addOrderList");
			e.printStackTrace();
		} 
		finally {
			pm.close();
		}
		
		pm = getPersistenceManager();
		try {
			String query= "select from "+OrderEntity.class.getName();
			@SuppressWarnings("unchecked")
			List<OrderEntity> result = (List<OrderEntity>) pm.newQuery(query).execute();
			
			for (OrderEntity or : result) {
				or.toString();
			}
		
		}catch(Exception e){
			LOG.log(Level.SEVERE, "Exception List after addOrderList");
			e.printStackTrace();
		} 
		finally {
			pm.close();
		}
		
	}
	
	@Override
	public List<Order> getOrder() {
		// TODO Auto-generated method stub
		List <Order> ordersLst = new ArrayList <Order>();
		
		PersistenceManager pm = getPersistenceManager();
		try {
			String query= "select from "+OrderEntity.class.getName();
			@SuppressWarnings("unchecked")
			List<OrderEntity> result = (List<OrderEntity>) pm.newQuery(query).execute();
			for (OrderEntity or : result) {
				Order order = new Order(or.getOrderid(),or.getProductname(),or.getOroductquantity(),or.getOrderdescription());
				ordersLst.add(order);
			}
			
		}catch(Exception e){
			LOG.log(Level.SEVERE, "Exception getOrder");
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
		return ordersLst;
	}
	
	
	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}



}
