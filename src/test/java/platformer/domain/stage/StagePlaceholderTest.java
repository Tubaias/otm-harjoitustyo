
package platformer.domain.stage;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StagePlaceholderTest {
    
    public StagePlaceholderTest() {
    }
    
    @Test
    public void asdIsAsd() {
        int asd = 12;
        StagePlaceholder s = new StagePlaceholder(asd);
        
        assertEquals(asd, s.getAsd());
    }
}
