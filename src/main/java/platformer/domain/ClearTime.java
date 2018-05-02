
package platformer.domain;

public class ClearTime {
    private String player;
    private StageNo stage;
    private Double time;

    public ClearTime(String player, StageNo stage, Double time) {
        this.player = player;
        this.stage = stage;
        this.time = time;
    }

    public String getPlayer() {
        return player;
    }

    public StageNo getStage() {
        return stage;
    }

    public Double getTime() {
        return time;
    }
}
