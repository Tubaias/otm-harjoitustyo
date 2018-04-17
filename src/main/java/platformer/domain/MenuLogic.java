package platformer.domain;

import javafx.scene.Scene;
import javafx.stage.Stage;
import platformer.ui.ExitMenu;

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
    public void exitDialog() {
        ExitMenu exitMenu = new ExitMenu(stage.getScene(), this);
        stage.setScene(exitMenu.getScene());
    }
    
    public void quit() {
        stage.close();
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
    
    public void setScene(Scene scene) {
        stage.setScene(scene);
    }
 
    public void centerStage() {
        stage.centerOnScreen();
    }
}
