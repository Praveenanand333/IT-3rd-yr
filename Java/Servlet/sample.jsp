<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple JDBC Example</title>
</head>
<body>
    <h2>Employee List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
            <%@ page import="java.sql.*" %>
            <%@ page import="java.util.*" %>
            
            <% 
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    // Load the JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Connect to the database
                    String url = "jdbc:mysql://localhost:3306/yourdatabase";
                    String username = "yourusername";
                    String password = "yourpassword";
                    conn = DriverManager.getConnection(url, username, password);

                    // Execute a query
                    String query = "SELECT * FROM employees";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);

                    // Display the results
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        double salary = rs.getDouble("salary");

                        out.println("<tr>");
                        out.println("<td>" + id + "</td>");
                        out.println("<td>" + name + "</td>");
                        out.println("<td>" + salary + "</td>");
                        out.println("</tr>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Close the database resources
                    try { if (rs != null) rs.close(); } catch (SQLException e) { }
                    try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
                    try { if (conn != null) conn.close(); } catch (SQLException e) { }
                }
            %>
        </tbody>
    </table>
</body>
</html>
