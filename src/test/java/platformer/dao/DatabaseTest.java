
package platformer.dao;

import java.io.File;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.Test;

public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @Test
    public void databaseIsMadeIfDoesntExist() throws SQLException {
        String dbName = "testDB.db";
        File check = new File(dbName);
        
        if (check.exists()) {
            check.delete();
        }
        
        Database db = new Database(dbName);
        
        File checkAgain = new File(dbName);
        assertTrue(checkAgain.exists());
    }
    
    @Test
    public void databaseIsNotMadeIfExists() throws SQLException {
        String dbName = "testDB.db";
        Database db1 = new Database(dbName);
        Database db2 = new Database(dbName);
        
        UsernameDao dao1 = new UsernameDao(db1);
        UsernameDao dao2 = new UsernameDao(db2);
        
        dao1.saveOrUpdate("AAA");
        assertEquals("AAA", dao2.findOne(1));
    }
}
