/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package libraryborrowingandreservationsystem;

import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class LibraryBorrowingandReservationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize the database connection for users (THIS REPLACES THE ARRAY)
        UserDatabaseManager userDb = new UserDatabaseManager();
        
        boolean running = true;

        while (running) {
            System.out.println("\n=== Library System Menu ===");
            System.out.println("1. Register Account");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                // --- REGISTRATION PHASE (Saves to Database) ---
                System.out.print("Enter User ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                System.out.println("Registering as: 1. Librarian | 2. Student");
                System.out.print("Choose user type: ");
                int type = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (type == 1) {
                    System.out.print("Enter Staff ID: ");
                    String staffId = scanner.nextLine();
                    
                    Librarian newLib = new Librarian(id, name, email, password, staffId);
                    
                    // Save directly to database using userDb
                    if (userDb.registerLibrarian(newLib)) {
                        System.out.println(">> Librarian saved to database successfully!");
                    }
                    
                } else if (type == 2) {
                    Students newStudent = new Students(id, name, email, password);
                    
                    // Save directly to database using userDb
                    if (userDb.registerStudent(newStudent)) {
                        System.out.println(">> Student saved to database successfully!");
                    }
                } else {
                    System.out.println("Invalid user type selected.");
                }

            } else if (choice == 2) {
                // --- SIGN IN PHASE (Reads from Database) ---
                System.out.print("\nEnter User ID to Login: ");
                String loginId = scanner.nextLine();
                System.out.print("Enter Password: ");
                String loginPass = scanner.nextLine();

                // Authenticate against the database instead of looping through an array
                User currentUser = userDb.authenticateUser(loginId, loginPass);
                
                if (currentUser != null) {
                    System.out.println("\n>> Login successful! Welcome, " + currentUser.getName());
                    
                    if (currentUser instanceof Librarian) {
                        System.out.println(">> Identity: LIBRARIAN");
                        
                        BookDatabaseManager dbManager = new BookDatabaseManager();
                        boolean staffMenu = true;
                        
                        while (staffMenu) {
                            System.out.println("\n--- Librarian CRUD Menu ---");
                            System.out.println("1. Add New Book (Create)");
                            System.out.println("2. View Catalog (Read)");
                            System.out.println("3. Update Book Status (Update)");
                            System.out.println("4. Remove Book (Delete)");
                            System.out.println("5. Logout");
                            System.out.print("Choose option: ");
                            
                            int crudChoice = scanner.nextInt();
                            scanner.nextLine();
                            
                            if (crudChoice == 1) dbManager.insertBook();
                            else if (crudChoice == 2) dbManager.queryAllBooks();
                            else if (crudChoice == 3) dbManager.updateBookStatus();
                            else if (crudChoice == 4) dbManager.deleteBook();
                            else if (crudChoice == 5) {
                                currentUser.logout();
                                staffMenu = false; 
                            } else System.out.println("Invalid choice.");
                        }
                        
                    } else if (currentUser instanceof Students) {
                        System.out.println(">> Identity: STUDENT");
                        Students currentStudent = (Students) currentUser;
                        
                        boolean studentMenu = true;
                        while(studentMenu) {
                            System.out.println("\n--- Student Menu ---");
                            System.out.println("1. View Catalog");
                            System.out.println("2. Logout");
                            System.out.print("Choose option: ");
                            
                            int studentChoice = scanner.nextInt();
                            scanner.nextLine();
                            
                            if (studentChoice == 1) {
                                BookDatabaseManager dbManager = new BookDatabaseManager();
                                dbManager.queryAllBooks();
                            } else if (studentChoice == 2) {
                                currentStudent.logout();
                                studentMenu = false;
                            }
                        }
                    }
                } else {
                    // This triggers if currentUser is null (ID or Password didn't match the database)
                    System.out.println(">> Incorrect ID or Password. Please try again.");
                }

            } else if (choice == 3) {
                running = false;
                System.out.println("Exiting the system. Goodbye!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
}