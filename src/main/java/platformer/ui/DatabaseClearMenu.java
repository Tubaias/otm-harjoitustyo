
package platformer.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import platformer.domain.MenuLogic;

public class DatabaseClearMenu {
    private Scene scene;
    private Scene prevScene;
    private MenuLogic logic;
    
    public DatabaseClearMenu(Scene prevScene, MenuLogic logic) {
        this.prevScene = prevScene;
        this.logic = logic;
        
        this.setup();
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    private void setup() {
        GridPane exitLayout = new GridPane();
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        exitLayout.setAlignment(Pos.CENTER);

        exitLayout.add(new Label("Are you sure you want to delete all times?"), 1, 0);
        exitLayout.add(new Label(""), 1, 1);
        exitLayout.add(confirmButton, 0, 2);
        exitLayout.add(cancelButton, 2, 2);

        scene = new Scene(exitLayout, 360, 120);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                logic.setScene(prevScene);
            }
            
            if (event.getCode() == KeyCode.ENTER) {
                logic.resetTimes();
                logic.goToMain();
            }
        });

        confirmButton.setOnAction(event -> {
            logic.resetTimes();
            logic.goToMain();
        });

        cancelButton.setOnAction(event -> {
            logic.setScene(prevScene);
        });
    }
}
