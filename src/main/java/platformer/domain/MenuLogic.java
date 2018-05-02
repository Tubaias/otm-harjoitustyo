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

    public MenuLogic(Stage stage, Database db) {
        this.stage = stage;
        this.db = db;
        
        this.usernameDao = new UsernameDao(db);
        this.timeDao = new TimeDao(db);
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
 
    public void centerStage() {
        stage.centerOnScreen();
    }
}
