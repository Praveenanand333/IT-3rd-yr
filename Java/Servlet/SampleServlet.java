package Servlets;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
@WebServlet("/Formhandler")
public class Formhandler extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

            out.println("<h1>Registration Successful</h1>");
            String query2="select * from users";
            PreparedStatement ps=connection.prepareStatement(query2);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            String uname=rs.getString("username");
            String pass=rs.getString("password");
            out.println(uname);
            out.println(pass);
            	
            }
            

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Registration Failed</h1>");
        }
    }
}
