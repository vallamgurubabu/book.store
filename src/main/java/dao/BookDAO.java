package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import com.online.book.store.Book;

import org.hibernate.Transaction;

public class BookDAO {
	private SessionFactory factory;

	public BookDAO() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		factory = configuration.buildSessionFactory();

	}

	public void saveBook(Book book) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(book);
		tx.commit();
		session.close();
	}

	public List<Book> getAllBooks() {
		Session session = factory.openSession();
		List<Book> books = session.createQuery("from Book", Book.class).list();
		session.close();
		return books;

	}

	public void updateBook(Book book) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(book);
		tx.commit();
		session.close();
	}

	public void deleteBook(int bookId) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Book book = session.get(Book.class, bookId);
		if (book != null) {
			session.delete(book);
		}
		tx.commit();
		session.close();
	}
}
