
package platformer.domain.stage;

import java.util.ArrayList;
import platformer.domain.entity.EndPoint;
import platformer.domain.entity.Platform;

public class GameStage {
    ArrayList<Platform> platforms;
    Double windowX;
    Double windowY;
    EndPoint goal;
    
    public GameStage(Double windowX, Double windowY) {
        this.platforms = new ArrayList<>();
        
        this.windowX = windowX;
        this.windowY = windowY;
    }
    
    public ArrayList<Platform> getPlatforms() {
        return this.platforms;
    }
    
    public EndPoint getEndPoint() {
        return this.goal;
    }
}
