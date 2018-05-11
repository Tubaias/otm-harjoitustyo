
package platformer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import platformer.domain.ClearTime;
import platformer.domain.StageNum;

/**
 * Database access object for saving and retrieving clear times.
 * @author tote
 */
public class TimeDao implements Dao<ClearTime, Integer> {
    private Database db;
    
    /**
     * Constructor.
     * @param db Database that times will be saved to and retrieved from.
     */
    public TimeDao(Database db) {
        this.db = db;
    }

    /**
     * Returns the current slowest time on a given stage.
     * @param stageNumber Stage to search for times on.
     * @return Returns the current slowest time or null if there are less than 10 times.
     * @throws SQLException 
     */
    @Override
    public ClearTime findOne(Integer stageNumber) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cleartime WHERE stage = ? ORDER BY time DESC");
        stmt.setInt(1, stageNumber);
        
        ResultSet rs = stmt.executeQuery();
        
        ClearTime worst = null;
        
        if (rs.next()) {
            worst = new ClearTime(rs.getInt("id"), rs.getString("player"), rs.getInt("stage"), rs.getLong("time"));
        }
        
        for (int i = 0; i < 10; i++) {
            if (!rs.next()) {
                worst = null;
            }
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return worst;
    }

    /**
     * Unparameterized version not supported.
     */
    @Override
    public List<ClearTime> findAll() throws SQLException {
        return null;
    }
    
    public List<ClearTime> findAll(StageNum stage) throws SQLException {
        ArrayList<ClearTime> times = new ArrayList<>();
        
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cleartime WHERE stage = ? ORDER BY time ASC");
        stmt.setInt(1, stage.getIntValue());
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            ClearTime clear = new ClearTime(rs.getInt("id"), rs.getString("player"), rs.getInt("stage"), rs.getLong("time"));
            times.add(clear);
        }
        
        return times;
    }

    /**
     * Saves the given time to the database.
     * @param clear Time to be saved to the database.
     * @return The ClearTime that was given in as a parameter.
     * @throws SQLException 
     */
    @Override
    public ClearTime saveOrUpdate(ClearTime clear) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Cleartime (player, stage, time) VALUES (?, ?, ?)");
        stmt.setString(1, clear.getPlayer());
        stmt.setInt(2, clear.getStage().getIntValue());
        stmt.setLong(3, clear.getTime());
        
        stmt.execute();
        
        stmt.close();
        conn.close();
        
        return clear;
    }

    /**
     * Removes a specified time from the database.
     * @param key Id of the time to be removed.
     * @throws SQLException 
     */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cleartime WHERE id = ?");
        stmt.setInt(1, key);
        
        stmt.execute();
        
        stmt.close();
        conn.close();
    }
    
    /**
     * Wipes all times from the database and performs a vacuum to save space.
     * @throws SQLException 
     */
    public void clearAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement delete = conn.prepareStatement("DELETE FROM Cleartime");
        PreparedStatement vacuum = conn.prepareStatement("VACUUM");
        
        delete.execute();
        vacuum.execute();
        
        delete.close();
        vacuum.close();
        conn.close();
    }
}
