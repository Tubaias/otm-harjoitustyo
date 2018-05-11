
package platformer.domain.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class representing the goal point in a GameStage.
 * @author tote
 */
public class EndPoint {
    private Polygon poly;

    /**
     * Constructor.
     * @param x1 x-coordinate of the first corner.
     * @param y1 y-coordinate of the first corner.
     * @param x2 x-coordinate of the second corner.
     * @param y2 y-coordinate of the second corner.
     * @param x3 x-coordinate of the third corner.
     * @param y3 y-coordinate of the third corner.
     * @param x4 x-coordinate of the fourth corner.
     * @param y4 y-coordinate of the fourth corner.
     */
    public EndPoint(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.poly = new Polygon(x1, y1, x2, y2, x3, y3, x4, y4);
        this.poly.setFill(Color.GREEN);
    }
    
    public Polygon getPoly() {
        return this.poly;
    }
    
    public void setTranslateX(Double translate) {
        poly.setTranslateX(translate);
    }
    
    public void setTranslateY(Double translate) {
        poly.setTranslateY(translate);
    }
}
