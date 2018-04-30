package platformer.domain;

import javafx.scene.Scene;
import javafx.stage.Stage;
import platformer.ui.ExitMenu;
import platformer.ui.MainMenu;
import platformer.ui.NameMenu;

public class MenuLogic {
    private Stage stage;
    private MainMenu mainMenu;
    private Scene optionsScene;
    private Scene gameUI;
    private Scene levelSelect;
    private String username;

    public MenuLogic(Stage stage) {
        this.stage = stage;
        this.username = "ASD";
    }
    
    public void goToMain() {
        stage.setScene(mainMenu.getScene());
    }

    public void goToOptions() {
        stage.setScene(optionsScene);
    }

    public void goToGame() {
        stage.setScene(gameUI);
    }
    
    public void goToLevels() {
        stage.setScene(levelSelect);
    }

    public void exitDialog() {
        ExitMenu exitMenu = new ExitMenu(stage.getScene(), this);
        stage.setScene(exitMenu.getScene());
    }
    
    public void nameChangeDialog() {
        NameMenu nameMenu = new NameMenu(stage.getScene(), this);
        stage.setScene(nameMenu.getScene());
    }
    
    public void quit() {
        stage.close();
    }

    //does not work for reasons???
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
    
    public void setUsername(String name) {
        username = name.substring(0, 3).toUpperCase();
        mainMenu.updateName();
    }
    
    public String getUsername() {
        return this.username;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setOptionsScene(Scene optionsScene) {
        this.optionsScene = optionsScene;
    }

    public void setGameUI(Scene gameUI) {
        this.gameUI = gameUI;
    }
    
    public void setLevelSelect(Scene levelSelect) {
        this.levelSelect = levelSelect;
    }
    
    public void setScene(Scene scene) {
        stage.setScene(scene);
    }
 
    public void centerStage() {
        stage.centerOnScreen();
    }
}
