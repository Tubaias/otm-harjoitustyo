package platformer.domain;

import javafx.scene.Scene;
import javafx.stage.Stage;
import platformer.dao.Database;
import platformer.dao.TimeDao;
import platformer.dao.UsernameDao;
import platformer.ui.ErrorScreen;
import platformer.ui.ExitMenu;
import platformer.ui.MainMenu;
import platformer.ui.NameMenu;

public class MenuLogic {
    private Stage stage;
    private Database db;
    private UsernameDao usernameDao;
    private TimeDao timeDao;
    private MainMenu mainMenu;
    private Scene optionsScene;
    private Scene gameUI;
    private Scene levelSelect;

    /**
     * MenuLogic class constructor.
     * @param stage main stage that the menulogic manipulates
     * @param db database for saving username and times
     */
    public MenuLogic(Stage stage, Database db) {
        this.stage = stage;
        this.db = db;
        
        this.usernameDao = new UsernameDao(db);
        this.timeDao = new TimeDao(db);
    }
    
    /**
     * Changes scene to the main menu screen.
     */
    public void goToMain() {
        stage.setScene(mainMenu.getScene());
    }

    /**
     * Changes scene to the options menu.
     */
    public void goToOptions() {
        stage.setScene(optionsScene);
    }

    /**
     * Changes scene to the game screen.
     */
    public void goToGame() {
        stage.setScene(gameUI);
    }
    
    /**
     * Changes scene to the level select menu.
     */
    public void goToLevels() {
        stage.setScene(levelSelect);
    }

    /**
     * Displays a dialog asking if the user wants to exit the application.
     */
    public void exitDialog() {
        ExitMenu exitMenu = new ExitMenu(stage.getScene(), this);
        stage.setScene(exitMenu.getScene());
    }
    
    /**
     * Displays a screen for changing the current username.
     */
    public void nameChangeDialog() {
        NameMenu nameMenu = new NameMenu(stage.getScene(), this);
        stage.setScene(nameMenu.getScene());
    }
    
    /**
     * Exits the application.
     */
    public void quit() {
        stage.close();
    }

    /**
     * Toggles between windowed and fullscreen mode. Does not work properly in it's
     * current state.
     */
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
    
    /**
     * Fetches the current username from the database and returns it
     * @return current username
     */
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
     * @param name The name you wish to save to the database. Doesn't have to be
     * in correct format when given as a parameter.
     */
    public void setUsername(String name) {
        String propername = name.substring(0, 3).toUpperCase();
        
        try {
            usernameDao.saveOrUpdate(propername);
        } catch (Exception e) {
            ErrorScreen es = new ErrorScreen(stage, e);
            stage.setScene(es.getScene());
        }
        
        mainMenu.updateName();
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
 
    /**
     * Centers the stage on the monitor. Doesn't seem to work in it's current state.
     */
    public void centerStage() {
        stage.centerOnScreen();
    }
}
