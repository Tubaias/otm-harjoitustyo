
package platformer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import platformer.domain.ClearTime;
import platformer.domain.StageNum;

public class TimeDao implements Dao<ClearTime, Integer> {
    private Database db;
    
    public TimeDao(Database db) {
        this.db = db;
    }

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

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cleartime WHERE id = ?");
        stmt.setInt(1, key);
        
        stmt.execute();
        
        stmt.close();
        conn.close();
    }
    
}
