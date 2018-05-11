
package platformer.domain.stage;

import platformer.domain.StageNum;
import platformer.domain.State;
import platformer.domain.entity.Coin;
import platformer.domain.entity.EndPoint;
import platformer.domain.entity.Platform;

public class Stage3 extends GameStage {
    public Stage3(Double windowX, Double windowY) {
        super(windowX, windowY);
        
        number = StageNum.THREE;
        
        characterX = 140d;
        characterY = windowY / 2 - 80;
        
        this.setupGround();
        this.setupWalls();
        this.setupBounds();
        this.setupCeilings();
        this.setupCorners();
        this.setupEntities();
    }
    
    private void setupGround() {
        Platform ground = new Platform(State.GROUND, 0, 0, windowX.intValue(), 0, windowX.intValue(), 50, 0, 50);
        Platform startPlat = new Platform(State.GROUND, 0, 0, 200, 0, 200, 40, 0, 40);
        Platform endPlat = new Platform(State.GROUND, 0, 0, 200, 0, 200, 40, 0, 40);

        ground.setTranslateY(windowY - 1);
        
        startPlat.setTranslateX(40d);
        startPlat.setTranslateY(windowY / 2 - 20);
        
        endPlat.setTranslateX(windowX - 240);
        endPlat.setTranslateY(windowY / 2 - 20);
        
        platforms.add(ground);
        platforms.add(startPlat);
        platforms.add(endPlat);
        
        setupGround2();
    }
    
    private void setupGround2() {
        Platform leftBottomPillar = new Platform(State.GROUND, 0, 0, 100, 0, 100, 1, 0, 1);
        Platform leftTopPillar = new Platform(State.GROUND, 0, 0, 100, 0, 100, 1, 0, 1);
        Platform rightPillar = new Platform(State.GROUND, 0, 0, 100, 0, 100, 1, 0, 1);
        
        leftBottomPillar.setTranslateX(windowX / 3 - 50);
        leftBottomPillar.setTranslateY(windowY - 100);
        
        leftTopPillar.setTranslateX(windowX / 3 - 50);
        leftTopPillar.setTranslateY(200d);
        
        rightPillar.setTranslateX(windowX / 3 * 2 - 50);
        rightPillar.setTranslateY(windowY / 2 + 50);
        
        platforms.add(rightPillar);
        platforms.add(leftBottomPillar);
        platforms.add(leftTopPillar);
    }
    
    private void setupWalls() {
        Platform plat1rightWall = new Platform(State.RIGHTWALL, 0, 0, 1, 0, 1, 39, 0, 39);
        Platform plat1leftWall = new Platform(State.LEFTWALL, 0, 0, 1, 0, 1, 39, 0, 39);
        
        Platform plat2rightWall = new Platform(State.RIGHTWALL, 0, 0, 1, 0, 1, 39, 0, 39);
        Platform plat2leftWall = new Platform(State.LEFTWALL, 0, 0, 1, 0, 1, 39, 0, 39);
        
        plat1rightWall.setTranslateX(240d);
        plat1rightWall.setTranslateY(windowY / 2 - 19);
        
        plat1leftWall.setTranslateX(39d);
        plat1leftWall.setTranslateY(windowY / 2 - 19);
        
        plat2rightWall.setTranslateX(windowX - 40);
        plat2rightWall.setTranslateY(windowY / 2 - 19);
        
        plat2leftWall.setTranslateX(windowX - 241);
        plat2leftWall.setTranslateY(windowY / 2 - 19);
        
        platforms.add(plat1rightWall);
        platforms.add(plat1leftWall);
        platforms.add(plat2rightWall);
        platforms.add(plat2leftWall);
        
        setupWalls2();
    }
    
    private void setupWalls2() {
        Platform leftBottomPillarLeft = new Platform(State.LEFTWALL, 0, 0, 51, 0, 51, 100, 0, 100);
        Platform leftBottomPillarRight = new Platform(State.RIGHTWALL, 0, 0, 50, 0, 50, 100, 0, 100);
        Platform leftTopPillarLeft = new Platform(State.LEFTWALL, 0, 0, 51, 0, 51, 100, 0, 100);
        Platform leftTopPillarRight = new Platform(State.RIGHTWALL, 0, 0, 50, 0, 50, 100, 0, 100);
        
        leftBottomPillarLeft.setTranslateX(windowX / 3 - 50);
        leftBottomPillarLeft.setTranslateY(windowY - 99);
        
        leftBottomPillarRight.setTranslateX(windowX / 3);
        leftBottomPillarRight.setTranslateY(windowY - 99);
        
        leftTopPillarLeft.setTranslateX(windowX / 3 - 50);
        leftTopPillarRight.setTranslateX(windowX / 3);
        
        platforms.add(leftBottomPillarLeft);
        platforms.add(leftBottomPillarRight);
        platforms.add(leftTopPillarLeft);
        platforms.add(leftTopPillarRight);
        
        setupWalls3();
    }
    
    private void setupWalls3() {
        Platform leftMiddlePillarLeft = new Platform(State.LEFTWALL, 0, 0, 51, 0, 51, windowY.intValue() - 401, 0, windowY.intValue() - 401);
        Platform leftMiddlePillarRight = new Platform(State.RIGHTWALL, 0, 0, 50, 0, 50, windowY.intValue() - 401, 0, windowY.intValue() - 401);
        
        leftMiddlePillarLeft.setTranslateX(windowX / 3 - 50);
        leftMiddlePillarLeft.setTranslateY(201d);
        
        leftMiddlePillarRight.setTranslateX(windowX / 3);
        leftMiddlePillarRight.setTranslateY(201d);
        
        platforms.add(leftMiddlePillarLeft);
        platforms.add(leftMiddlePillarRight);
        
        setupWalls4();
    }
    
    private void setupWalls4() {
        Platform rightBottomPillarLeft = new Platform(State.LEFTWALL, 0, 0, 51, 0, 51, windowY.intValue() / 2 - 50, 0, windowY.intValue() / 2 - 50);
        Platform rightBottomPillarRight = new Platform(State.RIGHTWALL, 0, 0, 50, 0, 50, windowY.intValue() / 2 - 50, 0, windowY.intValue() / 2 - 50);
        Platform rightTopPillarLeft = new Platform(State.LEFTWALL, 0, 0, 51, 0, 51, windowY.intValue() / 2 - 50, 0, windowY.intValue() / 2 - 50);
        Platform rightTopPillarRight = new Platform(State.RIGHTWALL, 0, 0, 50, 0, 50, windowY.intValue() / 2 - 50, 0, windowY.intValue() / 2 - 50);
        
        rightBottomPillarLeft.setTranslateX(windowX / 3 * 2 - 50);
        rightBottomPillarLeft.setTranslateY(windowY / 2 + 51);
        
        rightBottomPillarRight.setTranslateX(windowX / 3 * 2);
        rightBottomPillarRight.setTranslateY(windowY / 2 + 51);
        
        rightTopPillarLeft.setTranslateX(windowX / 3 * 2 - 50);
        rightTopPillarRight.setTranslateX(windowX / 3 * 2);
        
        platforms.add(rightBottomPillarLeft);
        platforms.add(rightBottomPillarRight);
        platforms.add(rightTopPillarLeft);
        platforms.add(rightTopPillarRight);
    }
    
    private void setupBounds() {
        Platform leftBound = new Platform(State.RIGHTWALL, 0, 0, 50, 0, 50, windowY.intValue(), 0, windowY.intValue());
        Platform rightBound = new Platform(State.LEFTWALL, 0, 0, 50, 0, 50, windowY.intValue(), 0, windowY.intValue());
        Platform ceiling = new Platform(State.AIR, 0, 0, windowX.intValue(), 0, windowX.intValue(), 50, 0, 50);
        
        leftBound.setTranslateX(-50d);
        
        rightBound.setTranslateX(windowX);
        
        ceiling.setTranslateY(-50d);
        
        platforms.add(leftBound);
        platforms.add(rightBound);
        platforms.add(ceiling);
    }
    
    private void setupCeilings() {
        Platform leftBottomPillar = new Platform(State.AIR, 0, 0, 100, 0, 100, 1, 0, 1);
        Platform leftTopPillar = new Platform(State.AIR, 0, 0, 100, 0, 100, 1, 0, 1);
        Platform rightPillar = new Platform(State.AIR, 0, 0, 100, 0, 100, 1, 0, 1);
        
        leftBottomPillar.setTranslateX(windowX / 3 - 50);
        leftBottomPillar.setTranslateY(windowY - 200);
        
        leftTopPillar.setTranslateX(windowX / 3 - 50);
        leftTopPillar.setTranslateY(100d);
        
        rightPillar.setTranslateX(windowX / 3 * 2 - 50);
        rightPillar.setTranslateY(windowY / 2 - 50);
        
        platforms.add(rightPillar);
        platforms.add(leftBottomPillar);
        platforms.add(leftTopPillar);
        
        setupCeilings2();
    }
    
    private void setupCeilings2() {
        Platform startPlat = new Platform(State.AIR, 0, 0, 202, 0, 202, 1, 0, 1);
        Platform endPlat = new Platform(State.AIR, 0, 0, 202, 0, 202, 1, 0, 1);
        
        startPlat.setTranslateX(39d);
        startPlat.setTranslateY(windowY / 2 + 20);
        
        endPlat.setTranslateX(windowX - 241);
        endPlat.setTranslateY(windowY / 2 + 20);
        
        platforms.add(startPlat);
        platforms.add(endPlat);
    }
    
    private void setupCorners() {
        Platform plat1rightCorner = new Platform(State.CORNER, 0, 0, 1, 0, 1, 1, 0, 1);
        Platform plat1leftCorner = new Platform(State.CORNER, 0, 0, 1, 0, 1, 1, 0, 1);
        Platform plat2rightCorner = new Platform(State.CORNER, 0, 0, 1, 0, 1, 1, 0, 1);
        Platform plat2leftCorner = new Platform(State.CORNER, 0, 0, 1, 0, 1, 1, 0, 1);
        
        plat1rightCorner.setTranslateX(39d);
        plat1rightCorner.setTranslateY(windowY / 2 - 20);
        
        plat1leftCorner.setTranslateX(240d);
        plat1leftCorner.setTranslateY(windowY / 2 - 20);
        
        plat2rightCorner.setTranslateX(windowX - 40);
        plat2rightCorner.setTranslateY(windowY / 2 - 20);
        
        plat2leftCorner.setTranslateX(windowX - 241);
        plat2leftCorner.setTranslateY(windowY / 2 - 20);
        
        platforms.add(plat1rightCorner);
        platforms.add(plat1leftCorner);
        platforms.add(plat2rightCorner);
        platforms.add(plat2leftCorner);
    }
    
    @Override
    public void setupEntities() {
        goal = new EndPoint(0, 0, 20, 0, 20, 20, 0, 20);
        
        goal.setTranslateX(windowX / 2 - 10);
        goal.setTranslateY(100d);
        
        Coin coin1 = new Coin(windowX - 140, windowY / 2 - 60);
        Coin coin2 = new Coin(windowX / 3, 150d);
        Coin coin3 = new Coin(windowX / 3, windowY - 150);
        Coin coin4 = new Coin(windowX / 3 * 2, windowY / 2);
        
        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);
        coins.add(coin4);
    }
}
