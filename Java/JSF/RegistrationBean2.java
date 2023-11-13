import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "registrationBean")
@RequestScoped
public class RegistrationBean {

    private String username;
    private String password;
    private String email;
    private String message;
    private List<User> allUsers; // New property to store all users

    // Existing getters and setters

    public List<User> getAllUsers() {
        return allUsers;
    }

    public String register() {
        // Existing registration code

        // Fetch all users after registration
        allUsers = fetchAllUsers();

        // Continue with existing logic

        return "success";
    }

    // New method to fetch all users
    private List<User> fetchAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "yourpassword");
            String sql = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                userList.add(user);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
