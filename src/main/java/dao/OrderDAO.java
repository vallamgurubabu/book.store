package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.online.book.store.Order;

public class OrderDAO {
	private SessionFactory factory;

	public OrderDAO() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		factory = configuration.buildSessionFactory();
	}

	public void svaeOrder(Order order) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(order);
		tx.commit();
		session.close();
	}

	public List<Order> getAllOrders() {
		Session session = factory.openSession();
		List<Order> orders = session.createQuery("from Order", Order.class).list();
		session.close();
		return orders;
	}

	public void updateOrder(Order order) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(order);
		tx.commit();
		session.close();
	}

	public void deleteOrder(int orderId) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Order orderDelete = session.get(Order.class, orderId);
		if (orderDelete != null) {
			session.delete(orderDelete);
		}
		tx.commit();
		session.close();
	}
	public Order getOrderById(int updateOrderId) {
		Session session = factory.openSession();
		Order order = session.get(Order.class, updateOrderId);
		if(order !=null) {
			return order;
		}
		return null;
	}
	
	public List<Order> getOrderDetails() {
        Session session = factory.openSession();
        String hql = "SELECT new com.online.book.store.OrderDetails(O.orderId, C.customerId, C.name, B.bookId, B.title) "
                   + "FROM Order O "
                   + "JOIN O.customer C "
                  + "JOIN O.book B";

        Query<Order> query = session.createQuery(hql, Order.class);
        List<Order> orderDetailsList = query.getResultList();
        session.close();
        return orderDetailsList;
    }

}
