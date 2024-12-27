package main;

import java.util.Scanner;

import com.online.book.store.Customer;

import dao.CustomerDAO;

public class CustomerMain {
	public static void main(String args[]) {
		CustomerDAO customerDAO = new CustomerDAO();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\nOnline BookStore Management System");
			System.out.println("1. Add Customer");
			System.out.println("2. View All Customers");
			System.out.println("3. Update Customer");
			System.out.println("4. Delete Customer");
			System.out.println("5. Exit");
			System.out.println("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				Customer customer = new Customer();
				System.out.print("Enter customer name: ");
				customer.setName(scanner.nextLine());
				System.out.print("Enter customer email: ");
				customer.setEmail(scanner.nextLine());
				System.out.print("Enter customer phone number: ");
				customer.setPhone(scanner.nextLine());
				customerDAO.saveCustomer(customer);
				System.out.println("Customer added successfully!");
				break;
			case 2:
				for(Customer c1 : customerDAO.getAllCustomers()) {
					System.out.println("ID: "+c1.getCustomerId()+",Customer Name: "+c1.getName()+",Email: "+c1.getEmail()+",Pnone: "+c1.getPhone());
					
				}
				break;
			case 3:
				System.out.print("Enter Customer ID to update: ");
				int updateId = scanner.nextInt();
				scanner.nextLine();
				Customer updatedCustomer = new Customer();
				updatedCustomer.setCustomerId(updateId);
				updatedCustomer.getCustomerId();
				System.out.println("Enter customer name: ");
				updatedCustomer.setName(scanner.nextLine());
				System.out.println("Enter customer Email: ");
				updatedCustomer.setEmail(scanner.nextLine());
				System.out.println("Enter customer Phone: ");
				updatedCustomer.setPhone(scanner.nextLine());
				customerDAO.updateCustomer(updatedCustomer);
				System.out.println("Customer updated successfully!");
				break;
			case 4:
				System.out.print("Enter Customer ID to delete: ");
				int deleteId = scanner.nextInt();
				customerDAO.deleteCustomer(deleteId);
				System.out.println("Customer deleted successfully!");
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
