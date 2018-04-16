package domain;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuLogic {

    private Stage stage;
    private Scene mainScene;
    private Scene optionsScene;
    private Scene gameUI;

    public MenuLogic(Stage stage) {
        this.stage = stage;
    }

    public void goToMain() {
        stage.setScene(mainScene);
    }

    public void goToOptions() {
        stage.setScene(optionsScene);
    }

    public void goToGame() {
        stage.setScene(gameUI);
    }

    //placeholder implementation while I figure out how to make a popup window
    public void exit() {
        Scene prevScene = stage.getScene();

        GridPane exitLayout = new GridPane();
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        exitLayout.setAlignment(Pos.CENTER);

        exitLayout.add(new Label("Do you want to exit?"), 1, 0);
        exitLayout.add(new Label(""), 1, 1);
        exitLayout.add(confirmButton, 0, 2);
        exitLayout.add(cancelButton, 2, 2);

        Scene exitScene = new Scene(exitLayout, 360, 120);
        stage.setScene(exitScene);

        exitScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(prevScene);
            }
            
            if (event.getCode() == KeyCode.ENTER) {
                stage.close();
            }
        });

        confirmButton.setOnAction(event -> {
            stage.close();
        });

        cancelButton.setOnAction(event -> {
            stage.setScene(prevScene);
        });
    }

    //does not work yet
    public void toggleFullscreen() {
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
            stage.setResizable(false);
        } else {
            stage.setResizable(true);
            stage.setFullScreen(true);
        }

        System.out.println(stage.isFullScreen());
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setOptionsScene(Scene optionsScene) {
        this.optionsScene = optionsScene;
    }

    public void setGameUI(Scene gameUI) {
        this.gameUI = gameUI;
    }
 
    public void centerStage() {
        stage.centerOnScreen();
    }
}
