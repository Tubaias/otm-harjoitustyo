
package platformer.domain.stage;

import platformer.domain.State;
import platformer.domain.entity.Platform;

public class Stage1 extends GameStage {
    
    public Stage1(Double windowX, Double windowY) {
        super(windowX, windowY);
        
        this.setup();
    }
    
    private void setup() {
        Platform plat1 = new Platform(State.GROUND, 0, 0, 200, 0, 200, 500, 0, 500);
        Platform plat2 = new Platform(State.GROUND, 0, 0, 200, 0, 200, 500, 0, 500);
        
        plat1.setTranslateY(windowY * 0.8);
        
        plat2.setTranslateX(windowX - 200);
        plat2.setTranslateY(windowY * 0.8);
        
        platforms.add(plat1);
        platforms.add(plat2);
    }
}
