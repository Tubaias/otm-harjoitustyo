
package platformer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Database access object for saving and retrieving the current username.
 * @author tote
 */
public class UsernameDao implements Dao<String, Integer> {
    private Database db;
    
    /**
     * UsernameDao class constructor. Sets the database to search from and save to.
     * @param db Database abstraction for the DAO to use
     */
    public UsernameDao(Database db) {
        this.db = db;
    }

    /**
     * Searches the current username from the database and returns it.
     * @param key Id to search the username with. Should always be 1 when getting
     * the current name.
     * @return Current username fetched from the database. 
     */
    @Override
    public String findOne(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Username WHERE id = ?");
        stmt.setInt(1, key);
        
        ResultSet rs = stmt.executeQuery();
        String name = rs.getString("name");
        
        rs.close();
        stmt.close();
        conn.close();
        
        return name;
    }

    /**
     * Not supported 
     */
    @Override
    public List<String> findAll() throws SQLException {
        return null;
    }

    /**
     * Deletes the previous username from the database and saves the new one.
     * @param name New username to save into the database
     * @return Returns back the given name
     */
    @Override
    public String saveOrUpdate(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement clear = conn.prepareStatement("DELETE FROM Username WHERE 1");
        clear.execute();
        
        PreparedStatement addName = conn.prepareStatement("INSERT INTO Username (name) VALUES (?)");
        addName.setString(1, name);
        addName.execute();
        
        clear.close();
        addName.close();
        conn.close();
        
        return name;
    }

    /**
     * Not supported 
     */
    @Override
    public void delete(Integer key) throws SQLException {
    }
}