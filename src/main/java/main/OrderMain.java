package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.online.book.store.Book;
import com.online.book.store.Customer;
import com.online.book.store.Order;

import dao.OrderDAO;

public class OrderMain {
	public static void main(String args[]) {
		OrderDAO orderDAO = new OrderDAO();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\nOnline BookStore Management System - Orders");
			System.out.println("1. Add Order");
			System.out.println("2. View All Orders");
			System.out.println("3. Update Order");
			System.out.println("4. Delete Order");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				Order newOrder = new Order();

				System.out.print("Enter Customer ID: ");
				int customerId = scanner.nextInt();
				scanner.nextLine(); // consume newline
				Customer customer = new Customer();
				customer.setCustomerId(customerId);
				newOrder.setCustomer(customer);

				System.out.print("Enter Book ID: ");
				int bookId = scanner.nextInt();
				scanner.nextLine(); // consume newline
				Book book = new Book();
				book.setBookId(bookId);
				newOrder.setBook(book);

				System.out.print("Enter Quantity: ");
				int quantity = scanner.nextInt();
				scanner.nextLine();
				newOrder.setQuantity(quantity);
				newOrder.setOrderDate(new java.sql.Date(System.currentTimeMillis()));
				System.out.print("Enter Order Status: ");
				String status = scanner.nextLine();
				newOrder.setStatus(status);
				orderDAO.svaeOrder(newOrder);
				System.out.println("Order added successfully!");
				break;

			case 2:
				System.out.println("\nList of Orders:");
				for (Order order : orderDAO.getAllOrders()) {
					System.out.println(
							"Order ID: " + order.getOrderId() + ", Customer ID: " + order.getCustomer().getCustomerId()
									+ ", Book ID: " + order.getBook().getBookId() + ", Quantity: " + order.getQuantity()
									+ ", Order Date: " + order.getOrderDate() + ", Status: " + order.getStatus());
				}

				break;

			case 3:
				System.out.print("Enter Order ID to update: ");
				int updateOrderId = scanner.nextInt();
				scanner.nextLine();

				Order updatedOrder = orderDAO.getOrderById(updateOrderId);
				if (updatedOrder != null) {
					System.out.print("Enter new Quantity: ");
					updatedOrder.setQuantity(scanner.nextInt());
					scanner.nextLine();

					System.out.print("Enter new Status: ");
					updatedOrder.setStatus(scanner.nextLine());

					orderDAO.updateOrder(updatedOrder);
					System.out.println("Order updated successfully!");
				} else {
					System.out.println("Order not found.");
				}
				break;

			case 4:
				System.out.print("Enter Order ID to delete: ");
				int deleteOrderId = scanner.nextInt();
				scanner.nextLine(); // consume newline

				orderDAO.deleteOrder(deleteOrderId);
				System.out.println("Order deleted successfully!");
				break;

			case 5:
				System.out.println("Exiting...");
				scanner.close();
				System.exit(0);

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
