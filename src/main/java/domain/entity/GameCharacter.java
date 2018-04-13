
package domain.entity;

import javafx.scene.shape.Polygon;

public class GameCharacter {
    Polygon poly;
    
    public GameCharacter(int translateX, int translateY) {
        poly = new Polygon(0, 0, 50, 0, 50, 50, 0, 50);
        
        poly.setTranslateX(translateX);
        poly.setTranslateY(translateY);
    }
    
    public Polygon getPoly() {
        return this.poly;
    }
    
    public void moveRight(int distance) {
        poly.setTranslateX(poly.getTranslateX() + distance);
    }
    
    public void moveLeft(int distance) {
        poly.setTranslateX(poly.getTranslateX() - distance);
    }
    
    public void moveUp(int distance) {
        poly.setTranslateY(poly.getTranslateY() - distance);
    }
    
    public void moveDown(int distance) {
        poly.setTranslateY(poly.getTranslateY() + distance);
    }
    
    @Override
    public String toString() {
        return poly.getTranslateX() + ":" + poly.getTranslateY();
    }
}
