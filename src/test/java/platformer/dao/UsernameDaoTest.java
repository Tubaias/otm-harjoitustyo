
package platformer.dao;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsernameDaoTest {
    
    public UsernameDaoTest() {
    }
    
    @Test
    public void nameCanBeSavedAndRetrieved() throws SQLException {
        String dbName = "testDB.db";
        Database db = new Database(dbName);
        
        UsernameDao dao = new UsernameDao(db);
        
        dao.saveOrUpdate("TST");
        assertEquals("TST", dao.findOne(1));
    }
}
