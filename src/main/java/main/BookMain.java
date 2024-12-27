package main;

import java.util.Scanner;

import com.online.book.store.Book;

import dao.BookDAO;

public class BookMain {
	public static void main(String args[]) {
		BookDAO bookDAO = new BookDAO();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\nOnline Bookstore Management System");
			System.out.println("1. Add Book");
			System.out.println("2. View All Books");
			System.out.println("3. Update Book");
			System.out.println("4. Delete Book");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); 

			switch (choice) {
			case 1:
				Book book = new Book();
				System.out.print("Enter title: ");
				book.setTitle(scanner.nextLine());
				System.out.print("Enter author: ");
				book.setAuthor(scanner.nextLine());
				System.out.print("Enter price: ");
				book.setPrice(scanner.nextDouble());
				System.out.print("Enter quantity: ");
				book.setQuantity(scanner.nextInt());
				bookDAO.saveBook(book);
				System.out.println("Book added successfully!");
				break;

			case 2:
				for (Book book1 : bookDAO.getAllBooks()) {
					System.out.println("ID: " + book1.getBookId() + ", Title: " + book1.getTitle() + ", Author: "
							+ book1.getAuthor() + ", Price: " + book1.getPrice() + ", Quantity: "
							+ book1.getQuantity());
				}
				break;

			case 3:
				System.out.print("Enter book ID to update: ");
				int updateId = scanner.nextInt();
				scanner.nextLine(); // Consume newline
				Book updateBook = new Book();
				updateBook.setBookId(updateId);
				System.out.print("Enter new title: ");
				updateBook.setTitle(scanner.nextLine());
				System.out.print("Enter new author: ");
				updateBook.setAuthor(scanner.nextLine());
				System.out.print("Enter new price: ");
				updateBook.setPrice(scanner.nextDouble());
				System.out.print("Enter new quantity: ");
				updateBook.setQuantity(scanner.nextInt());
				bookDAO.updateBook(updateBook);
				System.out.println("Book updated successfully!");
				break;

			case 4:
				System.out.print("Enter book ID to delete: ");
				int deleteId = scanner.nextInt();
				bookDAO.deleteBook(deleteId);
				System.out.println("Book deleted successfully!");
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
