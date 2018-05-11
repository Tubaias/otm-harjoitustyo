
package platformer.domain;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import platformer.ui.GameUI;
import platformer.ui.MainMenu;

public class FakeMenuLogic implements MenuLogic {

    @Override
    public void goToMain() {
    }

    @Override
    public void goToOptions() {
    }

    @Override
    public void goToGame() {
    }

    @Override
    public void startGame() {
    }

    @Override
    public void goToLevels() {
    }

    @Override
    public void goToTimes() {
    }

    @Override
    public void exitDialog() {
    }

    @Override
    public void nameChangeDialog() {
    }

    @Override
    public void databaseResetDialog() {
    }

    @Override
    public void quit() {
    }

    @Override
    public String getUsername() {
        return "ASD";
    }

    @Override
    public void setUsername(String name) {
    }

    @Override
    public List<ClearTime> getTimes(StageNum stageN) {
        return new ArrayList<>();
    }

    @Override
    public boolean assessAndSave(ClearTime clear) {
        return true;
    }

    @Override
    public void resetTimes() {
    }

    @Override
    public void setMainMenu(MainMenu mainMenu) {
    }

    @Override
    public void setOptionsScene(Scene optionsScene) {
    }

    @Override
    public void setGameUI(GameUI gameUI) {
    }

    @Override
    public void setLevelSelect(Scene levelSelect) {
    }

    @Override
    public void setTimeDisplay(Scene timeDisplay) {
    }

    @Override
    public void setScene(Scene scene) {
    }

    @Override
    public void fitStage() {
    }
    
}
