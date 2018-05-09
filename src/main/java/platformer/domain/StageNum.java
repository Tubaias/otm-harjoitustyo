
package platformer.domain;

public enum StageNum {
    ZERO(0), ONE(1), TWO(2), THREE(3), GAME(99);
    
    private final int number;
    
    StageNum(int number) {
        this.number = number;
    }
    
    public int getIntValue() {
        return this.number;
    }
    
    public static StageNum fromInt(int value) {
        switch (value) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            default:
                return GAME;
        }
    }
}
