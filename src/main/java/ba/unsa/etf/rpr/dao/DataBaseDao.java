package ba.unsa.etf.rpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DataBaseDao represents the class that is connecting us to the database
 * It implements the singleton design pattern
 */
public class DataBaseDao {
    private static Connection connection;

    private DataBaseDao(){

    }

    public static Connection getInstance() throws SQLException {
        if(connection == null){
            connection =DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_hospital_model","freedb_harun","e6zmV#ASfPv$Y&q");
        }
        return connection;
    }
}
