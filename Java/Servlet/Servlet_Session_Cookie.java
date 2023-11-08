import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLException;

@WebServlet("/UserServlet")
public class Servlet_Session_Cookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(); // Create or retrieve a session

        String username = request.getParameter("username");
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            // Simulate a login by checking the user in the database
            Connection connection = DBConnection.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    session.setAttribute("username", username); // Store username in the session
                    response.addCookie(new Cookie("username", username)); // Store username in a cookie
                    out.println("Login successful. Welcome, " + username + "!");
                } else {
                    out.println("Login failed. User not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("An error occurred during login.");
            }
        } else if ("logout".equals(action)) {
            // Simulate a logout by invalidating the session
            session.invalidate();
            out.println("Logout successful. Session invalidated.");
        } else {
            out.println("Invalid action.");
        }
    }
}
