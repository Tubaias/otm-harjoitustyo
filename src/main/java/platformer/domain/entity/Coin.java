
package platformer.domain.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class representing a coin-pickup in the game.
 * @author tote
 */
public class Coin {
    private Circle shape;

    /**
     * Constructor.
     * @param x x-coordinate for the coin to spawn in.
     * @param y y-coordinate for the coin to spawn in.
     */
    public Coin(Double x, Double y) {
        this.shape = new Circle(x, y, 20);
        this.shape.setFill(Color.ORANGE);
    }
    
    public Circle getShape() {
        return this.shape;
    }
}
