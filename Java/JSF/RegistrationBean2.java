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
public class RegistrationBean2 {

    // ... (existing methods and properties)

    public List<User> getAllUsers() {
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

    // ... (existing methods and properties)
}
