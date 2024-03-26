package Assignment5;

import java.sql.*;

public class Department {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Replace with your actual database credentials
        String url = "jdbc:oracle:thin:@localhost:1521/your_sid";
        String username = "your_username";
        String password = "your_password";

        // Department object to store
        Department department = new Department(10, "Engineering");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection to database
            connection = DriverManager.getConnection(url, username, password);

            // SQL statement with placeholders for department details
            String sql = "INSERT INTO department (id, name) VALUES (?, ?)";

            // Create a PreparedStatement object
            statement = connection.prepareStatement(sql);

            // Set values for placeholders in the prepared statement
            statement.setInt(1, department.getId());
            statement.setString(2, department.getName());

            // Execute the update query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Department record inserted successfully!");
            } else {
                System.out.println("No records inserted!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: Oracle JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Database connection failed!");
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of opening
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

class DepartmentData {
    private int id;
    private String name;

    public DepartmentData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters (optional)
}
