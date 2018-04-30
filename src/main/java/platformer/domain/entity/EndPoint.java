
package platformer.domain.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class EndPoint {
    private Polygon poly;

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
