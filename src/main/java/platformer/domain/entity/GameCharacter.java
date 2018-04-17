
package platformer.domain.entity;

import javafx.scene.shape.Polygon;

public class GameCharacter {
    private Polygon poly;
    private Double x;
    private Double y;
    private Double dX;
    private Double dY;
    private boolean canJump;
    
    public GameCharacter(Double translateX, Double translateY) {
        poly = new Polygon(0, 0, 20, 0, 20, 20, 0, 20);
        poly.setTranslateX(translateX);
        poly.setTranslateY(translateY);
        
        this.x = translateX;
        this.y = translateY;
        this.dX = 0d;
        this.dY = 0d;
        
        this.canJump = true;
    }
    
    public Polygon getPoly() {
        return this.poly;
    }
    
    public Double getX() {
        return this.x;
    }
    
    public Double getY() {
        return this.y;
    }
    
    public Double getDeltaX() {
        return this.dX;
    }
    
    public Double getDeltaY() {
        return this.dY;
    }
    
    public void update() {
        //everything here is subject to change
        
        if (dX > 0.3) {
            dX = 0.3;
        }
        
        if (dX < -0.3) {
            dX = -0.3;
        }
        
        x += dX;
        y += dY;
        
        if (y > 400) {
            y = 400.0;
            dY = 0.0;
            this.resetJump();
        }
        
        poly.setTranslateX(x);
        poly.setTranslateY(y);
        
        dY += 0.0015;
    }
    
    public void jump() {
        if (canJump) {
            canJump = false;
            this.dY -= 0.5;
        }
    }
    
    public void resetJump() {
        canJump = true;
    }
    
    public void moveRight(int distance) {
        dX += distance * 0.01;
    }
    
    public void moveLeft(int distance) {
        dX -= distance * 0.01;
    }
    
    public void moveUp(int distance) {
        y -= distance;
        poly.setTranslateY(poly.getTranslateY() - distance);
    }
    
    public void moveDown(int distance) {
        y += distance;
        poly.setTranslateY(poly.getTranslateY() + distance);
    }
    
    @Override
    public String toString() {
        return poly.getTranslateX() + ":" + poly.getTranslateY();
    }
}
