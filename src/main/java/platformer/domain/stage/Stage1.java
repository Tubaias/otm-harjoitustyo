
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
        
        Platform ground = new Platform(State.GROUND, 0, 0, windowX.intValue(), 0, windowX.intValue(), 1, 0, 1);
        
        Platform plat1rightWall = new Platform(State.WALL, 0, 0, 1, 0, 1, 500, 0, 500);
        Platform plat2leftWall = new Platform(State.WALL, 0, 0, 1, 0, 1, 500, 0, 500);
        
        plat1.setTranslateY(windowY * 0.8);
        
        plat2.setTranslateX(windowX - 200);
        plat2.setTranslateY(windowY * 0.8);
        
        ground.setTranslateY(windowY - 1);
        
        plat1rightWall.setTranslateX(200.0);
        plat1rightWall.setTranslateY(windowY * 0.8);
        
        plat2leftWall.setTranslateX(windowX - 201);
        plat2leftWall.setTranslateY(windowY * 0.8);
        
        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(ground);
        platforms.add(plat1rightWall);
        platforms.add(plat2leftWall);
    }
}
