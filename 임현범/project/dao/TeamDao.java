package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeamDao {
    private static final String URL ="jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String USERNAME = "c##idev";
    private static final String PASSWORD = "123456";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
