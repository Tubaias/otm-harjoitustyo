
package platformer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsernameDao implements Dao<String, Integer> {
    private Database db;
    
    public UsernameDao(Database db) {
        this.db = db;
    }

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

    @Override
    public List<String> findAll() throws SQLException {
        return null;
    }

    @Override
    public String saveOrUpdate(String nimi) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement clear = conn.prepareStatement("DELETE FROM Username WHERE 1");
        clear.execute();
        
        PreparedStatement addName = conn.prepareStatement("INSERT INTO Username (name) VALUES (?)");
        addName.setString(1, nimi);
        addName.execute();
        
        clear.close();
        addName.close();
        conn.close();
        
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        System.out.println("asd");
    }
    
}