package platformer.domain;

import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;
import platformer.dao.Database;
import platformer.dao.TimeDao;
import platformer.dao.UsernameDao;
import platformer.ui.DatabaseClearMenu;
import platformer.ui.ErrorScreen;
import platformer.ui.ExitMenu;
import platformer.ui.GameUI;
import platformer.ui.MainMenu;
import platformer.ui.NameMenu;

/**
 * Main logic class for handling all scene- and menu-related functionality.
 * @author tote
 */
public class RealMenuLogic implements MenuLogic {

    private Stage stage;
    private Database db;
    private UsernameDao usernameDao;
    private TimeDao timeDao;
    private MainMenu mainMenu;
    private Scene optionsScene;
    private GameUI gameUI;
    private Scene levelSelect;
    private Scene timeDisplay;

    /**
     * RealMenuLogic class constructor.
     *
     * @param stage main stage that the menulogic manipulates
     * @param db database for saving username and times
     */
    public RealMenuLogic(Stage stage, Database db) {
        this.stage = stage;
        this.db = db;

        this.usernameDao = new UsernameDao(db);
        this.timeDao = new TimeDao(db);
    }

    /**
     * Changes scene to the main menu screen.
     */
    @Override
    public void goToMain() {
        stage.setScene(mainMenu.getScene());
    }

    /**
     * Changes scene to the options menu.
     */
    @Override
    public void goToOptions() {
        stage.setScene(optionsScene);
    }

    /**
     * Changes scene to the game screen.
     */
    @Override
    public void goToGame() {
        stage.setScene(gameUI.getScene());
    }

    /**
     * Signals the gameUI to start a new playthrough and switches scene.
     */
    @Override
    public void startGame() {
        gameUI.startGame();
        goToGame();
    }

    /**
     * Changes scene to the level select menu.
     */
    @Override
    public void goToLevels() {
        stage.setScene(levelSelect);
    }

    /**
     * Changes scene to the time display menu.
     */
    @Override
    public void goToTimes() {
        stage.setScene(timeDisplay);
    }

    /**
     * Displays a dialog asking if the user wants to exit the application.
     */
    @Override
    public void exitDialog() {
        ExitMenu exitMenu = new ExitMenu(stage.getScene(), this);
        stage.setScene(exitMenu.getScene());
    }

    /**
     * Displays a screen for changing the current username.
     */
    @Override
    public void nameChangeDialog() {
        NameMenu nameMenu = new NameMenu(stage.getScene(), this);
        stage.setScene(nameMenu.getScene());
    }

    /**
     * Displays a dialog asking if the user wants to delete all times.
     */
    @Override
    public void databaseResetDialog() {
        DatabaseClearMenu clearMenu = new DatabaseClearMenu(stage.getScene(), this);
        stage.setScene(clearMenu.getScene());
    }

    /**
     * Exits the application.
     */
    @Override
    public void quit() {
        stage.close();
    }

    /**
     * Fetches the current username from the database and returns it
     *
     * @return current username
     */
    @Override
    public String getUsername() {
        String name = "";

        try {
            name = usernameDao.findOne(1);
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }

        return name;
    }

    /**
     * Modifies given name to correct format, saves it to the database as the
     * current username and updates the main menu to display the new name.
     *
     * @param name The name you wish to save to the database. Doesn't have to be
     * in correct format when given as a parameter.
     */
    @Override
    public void setUsername(String name) {
        while (name.length() < 3) {
            name += "_";
        }

        String propername = name.substring(0, 3).toUpperCase();

        try {
            usernameDao.saveOrUpdate(propername);
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }

        mainMenu.updateName();
    }

    @Override
    public List<ClearTime> getTimes(StageNum stageN) {
        try {
            return timeDao.findAll(stageN);
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }

        return null;
    }

    /**
     * Saves a ClearTime to the database or discards it if the time isn't good
     * enough.
     *
     * @param clear ClearTime to be assessed and saved
     * @return Returns true if the time was saved and false otherwise.
     */
    @Override
    public boolean assessAndSave(ClearTime clear) {
        ClearTime worst = null;

        try {
            worst = timeDao.findOne(clear.getStage().getIntValue());
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }

        if (worst == null) {
            saveTime(clear);
            return true;
        }

        if (worst.getTime() > clear.getTime()) {
            deleteTime(worst);
            saveTime(clear);
            return true;
        }

        return false;
    }

    private void saveTime(ClearTime clear) {
        try {
            timeDao.saveOrUpdate(clear);
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }
    }

    private void deleteTime(ClearTime clear) {
        try {
            timeDao.delete(clear.getId());
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }
    }

    /**
     * Deletes all cleartimes from the database.
     */
    @Override
    public void resetTimes() {
        try {
            timeDao.clearAll();
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }
    }

    @Override
    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void setOptionsScene(Scene optionsScene) {
        this.optionsScene = optionsScene;
    }

    @Override
    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    @Override
    public void setLevelSelect(Scene levelSelect) {
        this.levelSelect = levelSelect;
    }

    @Override
    public void setTimeDisplay(Scene timeDisplay) {
        this.timeDisplay = timeDisplay;
    }

    @Override
    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    /**
     * Resizes the stage to fit the current Scene.
     */
    @Override
    public void fitStage() {
        stage.sizeToScene();
    }
}
