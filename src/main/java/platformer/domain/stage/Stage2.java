
package platformer.domain.stage;

import platformer.domain.StageNum;
import platformer.domain.State;
import platformer.domain.entity.Coin;
import platformer.domain.entity.EndPoint;
import platformer.domain.entity.Platform;

public class Stage2 extends GameStage {
    
    public Stage2(Double windowX, Double windowY) {
        super(windowX, windowY);
        
        number = StageNum.TWO;
        
        characterX = windowX / 2 - 5;
        characterY = windowY - 120;
        
        this.setupGround();
        this.setupWalls();
        this.setupCorners();
        this.setupEntities();
    }
    
    private void setupGround() {
        Platform plat1 = new Platform(State.GROUND, 0, 0, 200, 0, 200, 250, 0, 250);
        plat1.setTranslateX(windowX / 2 - 100);
        plat1.setTranslateY(windowY - 100);
        
        Platform ceiling = new Platform(State.AIR, 0, 0, windowX.intValue(), 0, windowX.intValue(), 1, 0, 1);

        platforms.add(plat1);
        platforms.add(ceiling);
    }
    
    private void setupWalls() {
        Platform plat1rightWall = new Platform(State.RIGHTWALL, 0, 0, 1, 0, 1, 250, 0, 250);
        Platform plat1leftWall = new Platform(State.LEFTWALL, 0, 0, 1, 0, 1, 250, 0, 250);
        
        Platform mainLeftWall = new Platform(State.LEFTWALL, 0, 0, 300, 0, 300, windowY.intValue(), 0, windowY.intValue());
        Platform mainRightWall = new Platform(State.RIGHTWALL, 0, 0, 300, 0, 300, windowY.intValue(), 0, windowY.intValue());
        
        plat1rightWall.setTranslateX(windowX / 2 + 100);
        plat1rightWall.setTranslateY(windowY - 99);
        
        plat1leftWall.setTranslateX(windowX / 2 - 101);
        plat1leftWall.setTranslateY(windowY - 99);
        
        mainLeftWall.setTranslateX(windowX - 300);
        
        platforms.add(plat1rightWall);
        platforms.add(plat1leftWall);
        
        platforms.add(mainLeftWall);
        platforms.add(mainRightWall);
    }
    
    private void setupCorners() {
        Platform plat1rightCorner = new Platform(State.GROUND, 0, 0, 1, 0, 1, 1, 0, 1);
        Platform plat1leftCorner = new Platform(State.GROUND, 0, 0, 1, 0, 1, 1, 0, 1);
        
        plat1rightCorner.setTranslateX(windowX / 2 + 100);
        plat1rightCorner.setTranslateY(windowY - 100);
        
        plat1leftCorner.setTranslateX(windowX / 2 - 101);
        plat1leftCorner.setTranslateY(windowY - 100);
        
        platforms.add(plat1rightCorner);
        platforms.add(plat1leftCorner);
    }
    
    @Override
    public void setupEntities() {
        goal = new EndPoint(0, 0, 20, 0, 20, 20, 0, 20);
        
        goal.setTranslateX(windowX / 2 - 10);
        goal.setTranslateY(100d);
        
        Coin coin1 = new Coin(windowX / 2, windowY / 2);
        Coin coin2 = new Coin(windowX - 330, windowY / 2 + 80);
        Coin coin3 = new Coin(330d, windowY / 2 + 80);
        
        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);
    }
}
