package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

import com.online.book.store.Customer;

public class CustomerDAO {
	private org.hibernate.SessionFactory factory;

	public CustomerDAO() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		factory = configuration.buildSessionFactory();
	}

	public void saveCustomer(Customer customer) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(customer);
		tx.commit();
		session.close();
	}

	public List<Customer> getAllCustomers() {
		Session session = factory.openSession();
		List<Customer> customers = session.createQuery("from Customer", Customer.class).list();
		session.close();
		return customers;
	}

	public void updateCustomer(Customer customer) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(customer);
		tx.commit();
		session.close();
	}

	public void deleteCustomer(int customerId) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Customer customer = session.get(Customer.class, customerId);
		if (customer != null) {
			session.delete(customer);
		}
		tx.commit();
		session.close();
	}
   
}
