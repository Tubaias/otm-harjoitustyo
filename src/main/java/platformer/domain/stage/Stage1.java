
package platformer.domain.stage;

import platformer.domain.StageNum;
import platformer.domain.State;
import platformer.domain.entity.Coin;
import platformer.domain.entity.EndPoint;
import platformer.domain.entity.Platform;

public class Stage1 extends GameStage {
    
    public Stage1(Double windowX, Double windowY) {
        super(windowX, windowY);
        
        number = StageNum.ONE;
        
        this.setupGround();
        this.setupWalls();
        this.setupCorners();
        this.setupEntities();
    }
    
    private void setupGround() {
        Platform plat1 = new Platform(State.GROUND, 0, 0, 200, 0, 200, 250, 0, 250);
        Platform plat2 = new Platform(State.GROUND, 0, 0, 200, 0, 200, 250, 0, 250);

        plat1.setTranslateY(windowY * 0.8);
        
        plat2.setTranslateX(windowX - 200);
        plat2.setTranslateY(windowY * 0.8);

        platforms.add(plat1);
        platforms.add(plat2);
    }
    
    private void setupWalls() {
        Platform plat1rightWall = new Platform(State.RIGHTWALL, 0, 0, 1, 0, 1, 250, 0, 250);
        Platform plat2leftWall = new Platform(State.LEFTWALL, 0, 0, 1, 0, 1, 250, 0, 250);

        Platform rightBound = new Platform(State.LEFTWALL, 0, 0, 1, 0, 1, windowY.intValue(), 0, windowY.intValue());
        
        plat1rightWall.setTranslateX(200.0);
        plat1rightWall.setTranslateY(windowY * 0.8 + 1);
        
        plat2leftWall.setTranslateX(windowX - 201);
        plat2leftWall.setTranslateY(windowY * 0.8 + 1);
        
        rightBound.setTranslateX(windowX);
        
        platforms.add(plat1rightWall);
        platforms.add(plat2leftWall);
        platforms.add(rightBound);
    }
    
    private void setupCorners() {
        Platform plat1rightCorner = new Platform(State.GROUND, 0, 0, 1, 0, 1, 1, 0, 1);
        Platform plat2leftCorner = new Platform(State.GROUND, 0, 0, 1, 0, 1, 1, 0, 1);
        
        plat1rightCorner.setTranslateX(200.0);
        plat1rightCorner.setTranslateY(windowY * 0.8);
        
        plat2leftCorner.setTranslateX(windowX - 201);
        plat2leftCorner.setTranslateY(windowY * 0.8);
        
        platforms.add(plat1rightCorner);
        platforms.add(plat2leftCorner);
    }
    
    @Override
    public void setupEntities() {
        goal = new EndPoint(0, 0, 20, 0, 20, 20, 0, 20);
        
        goal.setTranslateX(windowX - 50);
        goal.setTranslateY(windowY * 0.8 - 50);
        
        Coin coin1 = new Coin(windowX / 2, windowY / 2 - 50);
        
        coins.add(coin1);
    }
}
