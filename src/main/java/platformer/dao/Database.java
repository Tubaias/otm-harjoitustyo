
package platformer.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private String dbName;
    private String address;

    public Database(String dbName) throws SQLException {
        this.dbName = dbName;
        this.address = "jdbc:sqlite:" + dbName;
        
        makeIfDoesntExist();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(address);
    }
    
    private void makeIfDoesntExist() throws SQLException {
        File check = new File(dbName);
        
        if (check.exists()) {
            return;
        }
        
        Connection conn = getConnection();
        
        PreparedStatement createCurrentName = conn.prepareStatement("CREATE TABLE Username (id integer PRIMARY KEY, name varchar(3))");
        createCurrentName.execute();
        
        PreparedStatement createTimes = conn.prepareStatement("CREATE TABLE ClearTime (id integer PRIMARY KEY, player varchar(3), stage integer, time bigint)");
        createTimes.execute();
        
        PreparedStatement insertDefaultName = conn.prepareStatement("INSERT INTO Username (name) VALUES ('ASD')");
        insertDefaultName.execute();
        
        createTimes.close();
        createCurrentName.close();
        insertDefaultName.close();
        conn.close();
        
        System.out.println("database created");
    }
}
