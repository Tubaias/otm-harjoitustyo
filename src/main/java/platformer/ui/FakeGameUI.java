
package platformer.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class FakeGameUI implements GameUI {

    @Override
    public Scene getScene() {
        return null;
    }

    @Override
    public Map getKeys() {
        return new HashMap<>();
    }

    @Override
    public void setCharacterPoly(Polygon poly) {
    }

    @Override
    public void addShape(Shape shape) {
    }

    @Override
    public void removeShape(Shape shape) {
    }

    @Override
    public void setStartTime(long now) {
    }

    @Override
    public void updateTimer(long now) {
    }

    @Override
    public void clear() {
    }

    @Override
    public void startGame() {
    }
    
}
