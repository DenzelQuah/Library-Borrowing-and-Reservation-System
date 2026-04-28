# 📚 Library Borrowing and Reservation System

An OOP Principle followed of Java-based desktop application for managing university library operations, including book borrowing, reservations, and user management. Built using Java Swing for the GUI and MySQL for database storage.

---

## 👥 User Roles

| Role | Description |
|---|---|
| **Librarian** | Can add, update, delete books, process borrow/return, and search catalog |
| **Student** | Can view catalog, reserve books, cancel reservations, and search |

---

## 🛠️ Technologies Used

- **Language:** Java (JDK 8 or above)
- **GUI Framework:** Java Swing (NetBeans Form Editor)
- **Database:** MySQL
- **JDBC Driver:** MySQL Connector/J
- **IDE:** Apache NetBeans

---

## 📋 Prerequisites

Before running this project, make sure you have the following installed:

- Java JDK 8 or above
- Apache NetBeans IDE
- MySQL Server (e.g., via XAMPP or standalone)
- MySQL Connector/J JAR (JDBC driver)

---

## 🗄️ Database Setup
(For SQL Queries may get from - DATABASE Query.md)
1. Open **phpMyAdmin** or any MySQL client.
2. Create a new database:
   ```sql
   CREATE DATABASE library_db; 
[Alternative way]: Copy the code from the DATABASE Query.md
  
   ```
3. Select the database and run the following SQL to create the required tables:
""
input the queries from DATABASE Query.md
""

## ⚙️ Project Configuration

Open `DatabaseConnection.java` and update the connection details to match your MySQL setup:

```java
String url  = "jdbc:mysql://localhost:3306/library_db";
String user = "root";
String pass = ""; // Change this if your MySQL has a password
```

---

## 🚀 How to Run

1. Clone or download this project folder.
2. Open the project in **Apache NetBeans**.
3. Add the **MySQL Connector/J** JAR to your project libraries:
   - Right-click project → Properties → Libraries → Add JAR/Folder
**Make sure added JAR files should be (.jar)
4. Make sure your **MySQL server is running**.
5. Run `LibraryBorrowingandReservationSystem.java` for the **console version**, or
6. Run `LoginFrame.java` for the **GUI version**.

---
## To get the JAR

1. Navigate to the MySQL Connector/J Download Page at dev.mysql.com/downloads/connector/j/.

2. Select Platform Independent from the "Operating System" dropdown menu.

3. Download the ZIP Archive (you do not need to log in or sign up; just click "No thanks, just start my download").

4. Extract the downloaded ZIP file to a folder on your computer.

5. Open the extracted folder to locate the JAR file, which will be named something like mysql-connector-j-8.x.x.jar.

6. Return to NetBeans and follow your path:  Right-click project → Properties → Libraries → Add JAR/Folder, then select that specific JAR file.

## 📁 Project Structure

```
LibraryBorrowingAndReservationSystem/
│
├── Book.java                          # Book model class
├── User.java                          # Abstract base class for all users
├── Librarian.java                     # Librarian subclass (extends User)
├── Students.java                      # Students subclass (extends User, implements Searchable)
├── Transaction.java                   # Transaction model (borrow records)
├── Reservation.java                   # Reservation model
├── Searchable.java                    # Interface for search functionality
│
├── DatabaseConnection.java            # Singleton DB connection
├── BookDatabaseManager.java           # CRUD operations for books
├── UserDatabaseManager.java           # User registration and authentication
│
├── LoginFrame.java / .form            # GUI - Login screen
├── RegisterFrame.java / .form         # GUI - Registration screen
├── StudentDashboard.java / .form      # GUI - Student portal
├── LibrarianDashboard.java / .form    # GUI - Librarian portal
│
└── LibraryBorrowingandReservationSystem.java  # Console-based main entry point
```

---

### 🔐 Authentication
- Secure login with User ID and password
- Role-based routing (Librarian → Librarian Dashboard, Student → Student Dashboard)
- User registration with auto-generated User ID (e.g., `STU-001`, `STAFF-001`)

### 📖 Book Management (Librarian)
- Add new books to the catalog
- Update book title, genre, and status
- Delete books from the system
- Process borrow transactions (validates Book ID and Student ID)
- Process book returns with automatic late fine calculation (RM 1/day)
- Search catalog by title, genre, or book ID

### 📚 Student Features
- View full library catalog
- Reserve available books
- Cancel existing reservations
- Search catalog by title, genre, or book ID

---

## 🧱 OOP Concepts Applied

| Concept | Where Applied |
|---|---|
| **Abstract Class** | `User.java` — cannot be instantiated directly |
| **Inheritance** | `Librarian` and `Students` both extend `User` |
| **Polymorphism** | `logout()` overridden in both subclasses; `authenticateUser()` returns `User` type at runtime |
| **Encapsulation** | All fields in model classes are `private` with public getters/setters |
| **Interface** | `Searchable.java` implemented by `Students` |
| **Composition** | `LibraryBorrowingandReservationSystem` owns the `ArrayList<Book>` |
| **Aggregation** | `Transaction` and `Reservation` hold a reference to `Book` |
| **Dependency** | `Librarian` and `Students` use `BookDatabaseManager` as a method parameter |

---

## 🎨 Design Pattern

**Singleton Pattern** — `DatabaseConnection.java`

Only one database connection instance is ever created and shared across all manager classes:

```java
public static DatabaseConnection getInstance() {
    if (instance == null) {
        instance = new DatabaseConnection();
    }
    return instance;
}
```

---

## 💰 Fine Calculation

Books are due **1 month** from the borrow date. Late returns are charged at:

> **RM 1.00 per day late**

---

---
