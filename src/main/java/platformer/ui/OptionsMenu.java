
package platformer.ui;

import platformer.domain.MenuLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class OptionsMenu {
    private MenuLogic logic;
    private Scene scene;
    private int windowX;
    private int windowY;
    
    public OptionsMenu(MenuLogic logic, int windowX, int windowY) {
        this.logic = logic;
        this.windowX = windowX;
        this.windowY = windowY;
        
        this.setup();
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    private void setup() {
        VBox options = new VBox();
        ScrollPane scroll = new ScrollPane(options);
        this.scene = new Scene(scroll, windowX, windowY);
        
        options.setSpacing(10);
        options.setAlignment(Pos.CENTER);
        options.setPadding(new Insets(10, 0, 10, 0));
        
        scroll.setMaxWidth(windowX);
        scroll.setMaxHeight(windowY);
        scroll.setFitToWidth(true);
        
        Button fullscreenButton = new Button("Toggle fullscreen mode");
        options.getChildren().add(fullscreenButton);
        
        Button changeNameButton = new Button("Change your username");
        options.getChildren().add(changeNameButton);
        
        for (int i = 1; i < 16; i++) {
            options.getChildren().add(new Button("Placeholder " + i));
        }
        
        fullscreenButton.setOnAction((event) -> {
            logic.toggleFullscreen();
        });
        
        changeNameButton.setOnAction((event) -> {
            logic.nameChangeDialog();
        });
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                logic.goToMain();
            }
        });
    }
}
