
package platformer.dao;

import java.sql.SQLException;
import java.util.List;
import platformer.domain.ClearTime;

public class TimeDao implements Dao<ClearTime, Integer> {
    private Database db;
    
    public TimeDao(Database db) {
        this.db = db;
    }

    @Override
    public ClearTime findOne(Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<ClearTime> findAll() throws SQLException {
        return null;
    }

    @Override
    public ClearTime saveOrUpdate(ClearTime time) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        System.out.println("asd");
    }
    
}
