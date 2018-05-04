
package platformer.domain;

public class ClearTime {
    private int id;
    private String player;
    private StageNum stage;
    private long time;

    public ClearTime(int id, String player, StageNum stage, long time) {
        this.id = id;
        this.player = player;
        this.stage = stage;
        this.time = time;
    }
    
    public ClearTime(int id, String player, int stage, long time) {
        this.id = id;
        this.player = player;
        this.stage = StageNum.fromInt(stage);
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getPlayer() {
        return player;
    }

    public StageNum getStage() {
        return stage;
    }

    public long getTime() {
        return time;
    }
}
