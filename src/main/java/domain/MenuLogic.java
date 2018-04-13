
package domain;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuLogic {
    private Stage stage;
    private Scene mainScene;
    
    public MenuLogic (Stage stage) {
        this.stage = stage;
    }
    
    public void returnToMain() {
        stage.setScene(mainScene);
    }
    
    public void exit() {
        stage.close();
    }
    
    public void toggleFullscreen() {
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
        
        System.out.println(stage.isFullScreen());
    }
    
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}
