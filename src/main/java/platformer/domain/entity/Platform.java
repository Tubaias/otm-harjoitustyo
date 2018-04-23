
package platformer.domain.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import platformer.domain.State;

public class Platform {
    private State type;
    private Polygon poly;
    private Color color;
    
    public Platform(State type, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.type = type;
        this.poly = new Polygon(x1, y1, x2, y2, x3, y3, x4, y4);
        this.color = Color.BLACK;
        this.poly.setFill(color);
    }
    
    public Polygon getPoly() {
        return this.poly;
    }
    
    public State getType() {
        return this.type;
    }
    
    public void setTranslateX(Double translate) {
        poly.setTranslateX(translate);
    }
    
    public void setTranslateY(Double translate) {
        poly.setTranslateY(translate);
    }
    
    public void toggleColor() {
        if (color == Color.BLACK) {
            color = Color.PLUM;
        } else {
            color = Color.BLACK;
        }
        
        poly.setFill(color);
    }
}
