package platformer.ui;

import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public interface GameUI {

    public Scene getScene();

    public Map getKeys();
    
    public void setCharacterPoly(Polygon poly);
    
    public void addShape(Shape shape);
    
    public void removeShape(Shape shape);
    
    public void setStartTime(long now);
    
    public void updateTimer(long now);
    
    public void clear();
    
    public void startGame();
}
