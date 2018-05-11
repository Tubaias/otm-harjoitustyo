
package platformer.domain.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import platformer.domain.State;

/**
 * A platform that the GameStages are built using.
 * @author tote
 */
public class Platform {
    private State type;
    private Polygon poly;
    private Color color;
    
    /**
     * Constructor.
     * @param type Type of the platform for collision detection code use.
     * @param x1 x-coordinate of the first corner.
     * @param y1 y-coordinate of the first corner.
     * @param x2 x-coordinate of the second corner.
     * @param y2 y-coordinate of the second corner.
     * @param x3 x-coordinate of the third corner.
     * @param y3 y-coordinate of the third corner.
     * @param x4 x-coordinate of the fourth corner.
     * @param y4 y-coordinate of the fourth corner.
     */
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
}
