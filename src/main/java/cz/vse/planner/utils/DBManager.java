package cz.vse.planner.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String HOST = "bksjqhuq7n0zoldegy1x-mysql.services.clever-cloud.com";

    private static final String PORT = "3306";
    private static final String DATABASE_NAME = "bksjqhuq7n0zoldegy1x";
    private static final String USER = "uzmd8ltc64idr46o";
    private static final String PASSWORD = "oP2AY4kVV6IyAGWKR3KO";
    private static final String CONNECTION_URL = "jabba";//"jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
    }


}
