
package platformer.domain.stage;

import platformer.domain.StageNo;
import platformer.domain.State;
import platformer.domain.entity.Platform;

public class StageDebug extends GameStage {
    
    public StageDebug(Double windowX, Double windowY) {
        super(windowX, windowY);
        
        number = StageNo.DEBUG;
        
        this.setupGround();
        this.setupBounds();
    }
    
    private void setupGround() {
        Platform ground = new Platform(State.GROUND, 0, 0, windowX.intValue(), 0, windowX.intValue(), 1, 0, 1);
        
        ground.setTranslateY(windowY - 1);
        
        platforms.add(ground);
    }
    
    private void setupBounds() {
        Platform leftBound = new Platform(State.RIGHTWALL, 0, 0, 1, 0, 1, windowY.intValue(), 0, windowY.intValue());
        Platform rightBound = new Platform(State.LEFTWALL, 0, 0, 1, 0, 1, windowY.intValue(), 0, windowY.intValue());
        Platform ceiling = new Platform(State.AIR, 0, 0, windowX.intValue(), 0, windowX.intValue(), 1, 0, 1);
        
        leftBound.setTranslateX(-1.0);
        
        rightBound.setTranslateX(windowX);
        
        platforms.add(leftBound);
        platforms.add(rightBound);
        platforms.add(ceiling);
    }
}
