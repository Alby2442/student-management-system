import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_db",
                    "root",
                    "AACA@123"
            );

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
