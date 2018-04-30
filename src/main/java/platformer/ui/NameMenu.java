
package platformer.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import platformer.domain.MenuLogic;

public class NameMenu {
    private Scene scene;
    private Scene prevScene;
    private MenuLogic logic;
    
    public NameMenu(Scene prevScene, MenuLogic logic) {
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
        TextField nameField = new TextField("new three letter name");
        
        exitLayout.setAlignment(Pos.CENTER);

        exitLayout.add(new Label("Current username: " + logic.getUsername()), 1, 0);
        exitLayout.add(new Label(""), 1, 1);
        exitLayout.add(nameField, 1, 2);
        exitLayout.add(new Label(""), 1, 3);
        exitLayout.add(confirmButton, 0, 4);
        exitLayout.add(cancelButton, 2, 4);

        scene = new Scene(exitLayout, 360, 120);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                logic.setScene(prevScene);
            }
            
            if (event.getCode() == KeyCode.ENTER) {
                logic.setUsername(nameField.getText());
                logic.setScene(prevScene);
            }
        });

        confirmButton.setOnAction(event -> {
            logic.setUsername(nameField.getText());
            logic.setScene(prevScene);
        });

        cancelButton.setOnAction(event -> {
            logic.setScene(prevScene);
        });
    }
}
