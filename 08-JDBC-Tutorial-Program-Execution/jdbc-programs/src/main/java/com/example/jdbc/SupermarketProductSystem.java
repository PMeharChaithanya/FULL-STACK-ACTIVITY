package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Reg No: RA2311003011556
public class SupermarketProductSystem {

    private static final String URL = "jdbc:mysql://localhost:3306/supermarket";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection con;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Reg No: RA2311003011556");
            System.out.println("Connected to database: supermarket");

            int choice;
            do {
                System.out.println();
                System.out.println("===== SUPERMARKET PRODUCT MANAGEMENT =====");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Quantity");
                System.out.println("4. Delete Product");
                System.out.println("5. Search Product");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = readInt();

                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> viewProducts();
                    case 3 -> updateQuantity();
                    case 4 -> deleteProduct();
                    case 5 -> searchProduct();
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

    static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = readInt();
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = readInt();
        System.out.print("Enter Price: ");
        double price = readDouble();

        String sql = "INSERT INTO product (product_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            System.out.println("Add failed: " + e.getMessage());
        }
    }

    static void viewProducts() {
        String sql = "SELECT * FROM product ORDER BY product_id";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.printf("%-6s %-20s %-10s %-10s%n", "ID", "Product Name", "Quantity", "Price");
            System.out.println("----------------------------------------------");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-6d %-20s %-10d %-10.2f%n",
                        rs.getInt("product_id"), rs.getString("product_name"),
                        rs.getInt("quantity"), rs.getDouble("price"));
            }
            if (!found) {
                System.out.println("No products found.");
            }
        } catch (SQLException e) {
            System.out.println("View failed: " + e.getMessage());
        }
    }

    static void updateQuantity() {
        System.out.print("Enter Product ID: ");
        int id = readInt();
        System.out.print("Enter New Quantity: ");
        int qty = readInt();

        String sql = "UPDATE product SET quantity = ? WHERE product_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qty);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Quantity updated successfully.");
            } else {
                System.out.println("No product found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = readInt();

        String sql = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    static void searchProduct() {
        sc.nextLine();
        System.out.print("Enter Product Name to search: ");
        String name = sc.nextLine();

        String sql = "SELECT * FROM product WHERE product_name LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("ID: " + rs.getInt("product_id")
                            + " | Name: " + rs.getString("product_name")
                            + " | Quantity: " + rs.getInt("quantity")
                            + " | Price: " + rs.getDouble("price"));
                }
                if (!found) {
                    System.out.println("No product found matching '" + name + "'");
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

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

    static double readDouble() {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid price: ");
                sc.next();
            }
        }
    }
}
