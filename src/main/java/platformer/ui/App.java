package platformer.ui;

import platformer.domain.GameLogic;
import platformer.domain.MenuLogic;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class App extends Application {
    private int windowX;
    private int windowY;
    private int gameWindowX;
    private int gameWindowY;

    @Override
    public void init() {
        windowX = 280;
        windowY = 360;
        gameWindowX = 1280;
        gameWindowY = 720;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("spaget");
        stage.setResizable(false);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.centerOnScreen();

        MenuLogic menuLogic = new MenuLogic(stage);
        GameLogic gameLogic = new GameLogic(gameWindowX, gameWindowY);
        gameLogic.setMenuLogic(menuLogic);

        OptionsMenu optionsMenu = new OptionsMenu(menuLogic, windowX, windowY);
        MainMenu mainMenu = new MainMenu(menuLogic, windowX, windowY);
        GameUI gameUI = new GameUI(menuLogic, gameLogic, gameWindowX, gameWindowY);
        LevelSelect levelSelect = new LevelSelect(menuLogic, gameLogic, windowX, windowY);
        
        gameLogic.setGameUI(gameUI);
        gameUI.setCharacterPoly(gameLogic.getCharacter().getPoly());
        
        gameLogic.setup();
        
        menuLogic.setMainMenu(mainMenu);
        menuLogic.setOptionsScene(optionsMenu.getScene());
        menuLogic.setGameUI(gameUI.getScene());
        menuLogic.setLevelSelect(levelSelect.getScene());

        stage.setScene(mainMenu.getScene());
        stage.show();
    }
}
