
package platformer.ui;

import platformer.domain.GameLogic;
import platformer.domain.MenuLogic;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import platformer.dao.Database;
import platformer.domain.RealMenuLogic;

public class App extends Application {

    private int windowX;
    private int windowY;
    private int gameWindowX;
    private int gameWindowY;
    private MenuLogic menuLogic;
    private GameLogic gameLogic;
    private MainMenu mainMenu;
    private OptionsMenu optionsMenu;
    private GameUI gameUI;
    private LevelSelect levelSelect;
    private TimeDisplayMenu bestTimes;

    /**
     * Initializes window sizes for the different scenes to hardcoded values.
     */
    @Override
    public void init() {
        windowX = 280;
        windowY = 360;
        gameWindowX = 1280;
        gameWindowY = 720;
    }

    /**
     * Sets up all major components of the application, establishes references
     * between them and starts the application.
     * 
     * @param   stage   main stage which is automatically created by the javafx
     * application class
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("SUPER PLATFORMER EXTREME");
        stage.setResizable(false);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.centerOnScreen();

        Database db = null;

        try {
            db = new Database("platformerDB.db");
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }

        menuLogic = new RealMenuLogic(stage, db);
        gameLogic = new GameLogic(gameWindowX, gameWindowY);
        gameLogic.setMenuLogic(menuLogic);

        setupMenus();

        gameLogic.setGameUI(gameUI);
        gameUI.setCharacterPoly(gameLogic.getCharacter().getPoly());

        gameLogic.setup();

        stage.setScene(mainMenu.getScene());
        stage.show();
    }

    private void setupMenus() {
        optionsMenu = new OptionsMenu(menuLogic, windowX, windowY);
        mainMenu = new MainMenu(menuLogic, windowX, windowY);
        gameUI = new RealGameUI(menuLogic, gameLogic, gameWindowX, gameWindowY);
        levelSelect = new LevelSelect(menuLogic, gameLogic, windowX, windowY);
        bestTimes = new TimeDisplayMenu(menuLogic, windowX, windowY);

        menuLogic.setMainMenu(mainMenu);
        menuLogic.setOptionsScene(optionsMenu.getScene());
        menuLogic.setGameUI(gameUI);
        menuLogic.setLevelSelect(levelSelect.getScene());
        menuLogic.setTimeDisplay(bestTimes.getScene());
    }
}
