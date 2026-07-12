import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "komal";   // तुमचा MySQL password

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL Driver Not Found.");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed.");
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        Connection con = getConnection();

        if (con != null) {
            System.out.println("Connected Successfully.");
        } else {
            System.out.println("Connection Failed.");
        }
    }
}