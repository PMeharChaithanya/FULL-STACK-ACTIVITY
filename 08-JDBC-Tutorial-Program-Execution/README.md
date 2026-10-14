# Activity 8: JDBC Tutorial Program Execution

**Reg No:** RA2311003011556

## Task
**Program 1 – Student Management System.** A college wants a student management system using Java and MySQL.
Database `college`, table `students (student_id INT PRIMARY KEY, name VARCHAR(50), department VARCHAR(30), mark INT)`.
Menu: 1. Insert Student, 2. Display All Students, 3. Search Student, 4. Update Mark, 5. Delete Student, 6. Exit.

**Program 2 – Supermarket.** Table `product (product_id, product_name, quantity, price)`.
Menu: 1. Add Product, 2. View Products, 3. Update Quantity, 4. Delete Product, 5. Search Product, 6. Exit.

Both programs must use **Connection, PreparedStatement, ResultSet, exception handling and resource closing**.

## Files
| File | Description |
|---|---|
| `setup.sql` | Creates databases `college` and `supermarket` and their tables |
| `src/main/java/com/example/jdbc/StudentManagementSystem.java` | Program 1 |
| `src/main/java/com/example/jdbc/SupermarketProductSystem.java` | Program 2 |
| `pom.xml` | Maven project with MySQL Connector/J dependency |
| `run-student.sh`, `run-supermarket.sh` | Helper scripts used on my machine to start MySQL and run each program |

## How each requirement is met
| Requirement | Implementation |
|---|---|
| Connection | `DriverManager.getConnection(URL, USER, PASSWORD)` |
| PreparedStatement | Every SQL statement uses `?` placeholders (`setInt`, `setString`, `setDouble`) |
| ResultSet | Display / Search / View read rows with `rs.next()` and `rs.getXxx()` |
| Exception handling | `SQLException` caught in every operation (e.g. duplicate primary key); `InputMismatchException` handled for invalid number input |
| Resource closing | try-with-resources closes `PreparedStatement` and `ResultSet`; the `Connection` is closed in a `finally` block |

## How to run
```bash
# 1. Create the databases and tables (MySQL user root / password root)
mysql -u root -p < setup.sql

# 2. Compile
mvn compile

# 3. Run
mvn exec:java -Dexec.mainClass=com.example.jdbc.StudentManagementSystem
mvn exec:java -Dexec.mainClass=com.example.jdbc.SupermarketProductSystem
```
Change `USER` / `PASSWORD` at the top of each Java file if your MySQL login is different.

## Output

### Program 1 – Student Management System
(Inputs: insert 101 & 102, display, search 102, update 102's mark to 82, delete 101, display, exit)
```
Reg No: RA2311003011556
Connected to database: college

===== STUDENT MANAGEMENT SYSTEM =====
1. Insert Student
2. Display All Students
3. Search Student
4. Update Mark
5. Delete Student
6. Exit
Enter your choice: Enter Student ID: Enter Name: Enter Department: Enter Mark: Student inserted successfully.

Enter your choice: Enter Student ID: Enter Name: Enter Department: Enter Mark: Student inserted successfully.

Enter your choice: ID     Name                 Department   Mark 
---------------------------------------------
101    Arun Kumar           CSE          88   
102    Priya S              ECE          76   

Enter your choice: Enter Student ID to search: Student Found:
ID         : 102
Name       : Priya S
Department : ECE
Mark       : 76

Enter your choice: Enter Student ID: Enter New Mark: Mark updated successfully.

Enter your choice: Enter Student ID to delete: Student deleted successfully.

Enter your choice: ID     Name                 Department   Mark 
---------------------------------------------
102    Priya S              ECE          82   

Enter your choice: Exiting... Thank you!
Database connection closed.
```

### Program 2 – Supermarket Product Management
(Inputs: add 2 products, view, update quantity of 2 to 15, search "Oil", delete 1, view, exit)
```
Reg No: RA2311003011556
Connected to database: supermarket

===== SUPERMARKET PRODUCT MANAGEMENT =====
1. Add Product
2. View Products
3. Update Quantity
4. Delete Product
5. Search Product
6. Exit
Enter your choice: Enter Product ID: Enter Product Name: Enter Quantity: Enter Price: Product added successfully.

Enter your choice: Enter Product ID: Enter Product Name: Enter Quantity: Enter Price: Product added successfully.

Enter your choice: ID     Product Name         Quantity   Price     
----------------------------------------------
1      Rice 5kg             40         320.50    
2      Sunflower Oil 1L     25         165.00    

Enter your choice: Enter Product ID: Enter New Quantity: Quantity updated successfully.

Enter your choice: Enter Product Name to search: ID: 2 | Name: Sunflower Oil 1L | Quantity: 15 | Price: 165.0

Enter your choice: Enter Product ID to delete: Product deleted successfully.

Enter your choice: ID     Product Name         Quantity   Price     
----------------------------------------------
2      Sunflower Oil 1L     15         165.00    

Enter your choice: Exiting... Thank you!
Database connection closed.
```

### Exception handling example
```
Insert failed: Duplicate entry '101' for key 'students.PRIMARY'
Please enter a valid number:
```
