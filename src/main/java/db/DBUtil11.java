package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil11 extends Configs {

    static {

    }

    private DBUtil11() {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection dbConnection;
        String connectionString = "jdbc:mysql//" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

        return dbConnection;
    }

}