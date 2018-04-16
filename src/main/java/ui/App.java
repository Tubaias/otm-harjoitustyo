package ui;

import domain.MenuLogic;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("spaget");
        stage.setResizable(false);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); 
        
        int windowX = 280;
        int windowY = 360;

        int gameWindowX = 1280;
        int gameWindowY = 720;
        
        MenuLogic menuLogic = new MenuLogic(stage);
        
        OptionsMenu optionsMenu = new OptionsMenu(menuLogic, windowX, windowY);
        MainMenu mainMenu = new MainMenu(menuLogic, windowX, windowY);
        GameUI gameUI = new GameUI(menuLogic, gameWindowX, gameWindowY);
        
        menuLogic.setMainScene(mainMenu.getScene());
        menuLogic.setOptionsScene(optionsMenu.getScene());
        menuLogic.setGameUI(gameUI.getScene());

        stage.setScene(mainMenu.getScene());
        stage.show();
    }
}
