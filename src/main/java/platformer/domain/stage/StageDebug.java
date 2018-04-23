
package platformer.domain.stage;

import platformer.domain.State;
import platformer.domain.entity.Platform;

public class StageDebug extends GameStage {
    
    public StageDebug(Double windowX, Double windowY) {
        super(windowX, windowY);
        
        this.setup();
    }
    
    private void setup() {
        Platform plat1 = new Platform(State.GROUND, 0, 0, 50, 0, 50, 50, 0, 50);
        Platform plat2 = new Platform(State.GROUND, 100, 100, 150, 100, 150, 150, 100, 150);
        Platform plat3 = new Platform(State.GROUND, -100, -100, -150, -100, -150, -150, -100, -150);
        
        plat1.setTranslateX(windowX / 2);
        plat1.setTranslateY(windowY / 2);
        
        plat2.setTranslateX(windowX / 2);
        plat2.setTranslateY(windowY / 2);
        
        plat3.setTranslateX(windowX / 2);
        plat3.setTranslateY(windowY / 2);
        
        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(plat3);
    }
}
