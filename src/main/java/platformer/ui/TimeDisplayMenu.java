package platformer.ui;

import java.text.DecimalFormat;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import platformer.domain.ClearTime;
import platformer.domain.MenuLogic;
import platformer.domain.StageNum;

public class TimeDisplayMenu {

    private MenuLogic logic;
    private Scene scene;
    private int windowX;
    private int windowY;
    private DecimalFormat formatter;
    private VBox times;

    public TimeDisplayMenu(MenuLogic logic, int windowX, int windowY) {
        this.logic = logic;
        this.windowX = windowX;
        this.windowY = windowY;
        formatter = new DecimalFormat("0.00");

        this.setup();
    }

    public Scene getScene() {
        return this.scene;
    }

    private void setup() {
        BorderPane mainLayout = new BorderPane();
        HBox levels = new HBox();
        times = new VBox();
        
        times.setSpacing(5);
        times.setAlignment(Pos.CENTER);
        times.setPadding(new Insets(10, 0, 10, 0));

        mainLayout.setTop(levels);
        mainLayout.setCenter(times);

        Button stageZero = new Button("Stage 0");
        Button stageOne = new Button("Stage 1");
        Button stageTwo = new Button("Stage 2");
        Button stageThree = new Button("Stage 3");
        Button fullGame = new Button("Full game");

        levels.getChildren().addAll(stageZero, stageOne, stageTwo, stageThree, fullGame);
        
        scene = new Scene(mainLayout);

        stageZero.setOnAction((event) -> {
            displayTimes(StageNum.ZERO);
        });

        stageOne.setOnAction((event) -> {
            displayTimes(StageNum.ONE);
        });

        stageTwo.setOnAction((event) -> {
            displayTimes(StageNum.TWO);
        });

        stageThree.setOnAction((event) -> {
            displayTimes(StageNum.THREE);
        });
        
        fullGame.setOnAction((event) -> {
            displayTimes(StageNum.GAME);
        });

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                logic.goToMain();
            }
        });
    }

    private void displayTimes(StageNum stage) {
        List<ClearTime> cleartimes = logic.getTimes(stage);
        
        if (cleartimes == null) {
            System.out.println("asd");
            return;
        }
        
        times.getChildren().clear();

        for (int i = 0; i < cleartimes.size(); i++) {
            ClearTime c = cleartimes.get(i);

            times.getChildren().add(new Label(c.getPlayer() + " : " + formatter.format((double) c.getTime() / 1_000_000_000)));
        }
        
        logic.fitStage();
    }
}
