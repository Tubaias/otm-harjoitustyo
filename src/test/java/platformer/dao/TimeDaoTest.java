package platformer.dao;

import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import platformer.domain.ClearTime;
import platformer.domain.StageNum;

public class TimeDaoTest {

    Database db;
    TimeDao dao;

    public TimeDaoTest() {
    }

    @Before
    public void setup() throws SQLException {
        db = new Database("testDB.db");
        dao = new TimeDao(db);

        dao.saveOrUpdate(new ClearTime(1, "SLW", StageNum.ZERO, 100));

        for (int i = 0; i < 15; i++) {
            dao.saveOrUpdate(new ClearTime(1, "TST", StageNum.ZERO, i));
        }

        dao.saveOrUpdate(new ClearTime(1, "ONE", StageNum.ONE, 1));
        dao.saveOrUpdate(new ClearTime(1, "TWO", StageNum.TWO, 2));
        dao.saveOrUpdate(new ClearTime(1, "THR", StageNum.THREE, 3));
    }

    @Test
    public void findOneReturnsSlowestTime() throws SQLException {
        ClearTime retrievedTime = dao.findOne(0);
        assertEquals("SLW", retrievedTime.getPlayer());
    }

    @Test
    public void findAllReturnsAllTimes() throws SQLException {
        List<ClearTime> retrievedTimes = dao.findAll(StageNum.ZERO);
        assertEquals(16, retrievedTimes.size());
    }
    
    @Test
    public void clearAllClearsAll() throws SQLException {
        dao.clearAll();
        List<ClearTime> zero = dao.findAll(StageNum.ZERO);
        List<ClearTime> one = dao.findAll(StageNum.ONE);
        List<ClearTime> two = dao.findAll(StageNum.TWO);
        List<ClearTime> three = dao.findAll(StageNum.THREE);
        
        int times = zero.size() + one.size() + two.size() + three.size();
        
        assertEquals(0, times);
    }
}
