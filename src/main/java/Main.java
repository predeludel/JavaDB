import java.sql.*;
import java.util.Arrays;

public class Main {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/example";
    static final String USER = "postgres";
    static final String PASS = "506056";
    static Connection connection;

    public static void main(String[] args) throws SQLException {

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
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
        User[] users = getUserByCityId(4);
        if (users == null) {
            System.out.println("Пользователь с таким городом не найден");
        } else {
            System.out.println(Arrays.toString(users));
        }


    }

    public static User[] getUserByCityId(int id) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(String.format("SELECT COUNT(*) FROM \"Users\" WHERE \"cityId\"=%d", id));
        int size = 0;
        while (rs.next()) {
            size = (rs.getInt(1));
        }
        if (size == 0) {
            return null;
        }
        rs = st.executeQuery(String.format("SELECT * FROM \"Users\" WHERE \"cityId\"=%d", id));
        User[] users = new User[size];
        int i = 0;
        while (rs.next()) {
            User obj = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getInt(5));
            users[i] = obj;
            i++;
        }
        return users;

    }
}
