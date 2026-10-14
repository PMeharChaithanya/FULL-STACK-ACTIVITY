package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Reg No: RA2311003011556
public class StudentManagementSystem {

    private static final String URL = "jdbc:mysql://localhost:3306/college";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection con;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Reg No: RA2311003011556");
            System.out.println("Connected to database: college");

            int choice;
            do {
                System.out.println();
                System.out.println("===== STUDENT MANAGEMENT SYSTEM =====");
                System.out.println("1. Insert Student");
                System.out.println("2. Display All Students");
                System.out.println("3. Search Student");
                System.out.println("4. Update Mark");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = readInt();

                switch (choice) {
                    case 1 -> insertStudent();
                    case 2 -> displayAllStudents();
                    case 3 -> searchStudent();
                    case 4 -> updateMark();
                    case 5 -> deleteStudent();
                    case 6 -> System.out.println("Exiting... Thank you!");
                    default -> System.out.println("Invalid choice. Enter 1 to 6.");
                }
            } while (choice != 6);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            // Resource closing
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
            sc.close();
        }
    }

    static void insertStudent() {
        System.out.print("Enter Student ID: ");
        int id = readInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Mark: ");
        int mark = readInt();

        String sql = "INSERT INTO students (student_id, name, department, mark) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setInt(4, mark);
            ps.executeUpdate();
            System.out.println("Student inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    static void displayAllStudents() {
        String sql = "SELECT * FROM students ORDER BY student_id";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.printf("%-6s %-20s %-12s %-5s%n", "ID", "Name", "Department", "Mark");
            System.out.println("---------------------------------------------");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-6d %-20s %-12s %-5d%n",
                        rs.getInt("student_id"), rs.getString("name"),
                        rs.getString("department"), rs.getInt("mark"));
            }
            if (!found) {
                System.out.println("No students found.");
            }
        } catch (SQLException e) {
            System.out.println("Display failed: " + e.getMessage());
        }
    }

    static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = readInt();

        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Student Found:");
                    System.out.println("ID         : " + rs.getInt("student_id"));
                    System.out.println("Name       : " + rs.getString("name"));
                    System.out.println("Department : " + rs.getString("department"));
                    System.out.println("Mark       : " + rs.getInt("mark"));
                } else {
                    System.out.println("No student found with ID " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    static void updateMark() {
        System.out.print("Enter Student ID: ");
        int id = readInt();
        System.out.print("Enter New Mark: ");
        int mark = readInt();

        String sql = "UPDATE students SET mark = ? WHERE student_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mark);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Mark updated successfully.");
            } else {
                System.out.println("No student found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = readInt();

        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    // Reads an integer and keeps asking until a valid number is entered
    static int readInt() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
        }
    }
}
