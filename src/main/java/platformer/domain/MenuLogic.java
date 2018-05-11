package platformer.domain;

import java.util.List;
import javafx.scene.Scene;
import platformer.ui.GameUI;
import platformer.ui.MainMenu;

public interface MenuLogic {

    public void goToMain();

    public void goToOptions();

    public void goToGame();

    public void startGame();

    public void goToLevels();

    public void goToTimes();

    public void exitDialog();

    public void nameChangeDialog();

    public void databaseResetDialog();

    public void quit();

    public String getUsername();

    public void setUsername(String name);
    
    public List<ClearTime> getTimes(StageNum stageN);

    public boolean assessAndSave(ClearTime clear);

    public void resetTimes();

    public void setMainMenu(MainMenu mainMenu);

    public void setOptionsScene(Scene optionsScene);

    public void setGameUI(GameUI gameUI);

    public void setLevelSelect(Scene levelSelect);
    
    public void setTimeDisplay(Scene timeDisplay);

    public void setScene(Scene scene);
    
    public void fitStage();
}
