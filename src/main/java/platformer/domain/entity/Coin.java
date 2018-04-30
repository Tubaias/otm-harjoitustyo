
package platformer.domain.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin {
    private Circle shape;

    public Coin(Double x, Double y) {
        this.shape = new Circle(x, y, 20);
        this.shape.setFill(Color.ORANGE);
    }
    
    public Circle getShape() {
        return this.shape;
    }
    
    public void setTranslateX(Double translate) {
        shape.setTranslateX(translate);
    }
    
    public void setTranslateY(Double translate) {
        shape.setTranslateY(translate);
    }
}
