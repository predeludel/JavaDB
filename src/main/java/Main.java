import java.sql.*;

public class Main {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/example";
    static final String USER = "postgres";
    static final String PASS = "506056";

    public static void main(String[] args) {
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail");
            while (rs.next()) {
                System.out.println(String.valueOf()) + rs.getString(2)
                        + String.valueOf(rs.getInt(3)) + String.valueOf(rs.getString(4)));
                System.out.println(String.format("%d %s %d %s",4,3));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }


        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
}
