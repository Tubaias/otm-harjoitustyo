package platformer.domain.entity;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import platformer.domain.State;

public class GameCharacter {

    private Polygon poly;
    private Double x;
    private Double y;
    private Double dX;
    private Double dY;
    private State state;
    private boolean charged;
    private Double chargeDelta;

    public GameCharacter(Double translateX, Double translateY) {
        poly = new Polygon(0, 0, 10, 0, 10, 10, 0, 10);
        poly.setTranslateX(translateX);
        poly.setTranslateY(translateY);

        this.x = translateX;
        this.y = translateY;
        this.dX = 0d;
        this.dY = 0d;
        this.chargeDelta = 0d;

        this.state = State.GROUND;
        this.charged = false;
    }

    public Polygon getPoly() {
        return this.poly;
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public Double getDeltaX() {
        return this.dX;
    }

    public Double getDeltaY() {
        return this.dY;
    }

    public State getState() {
        return this.state;
    }

    public boolean isCharged() {
        return this.charged;
    }

    public void chargeUp() {
        charged = true;
        chargeDelta = (Math.abs(dX) + Math.abs(dY)) * 0.5;
        this.setColor(Color.RED);
    }

    public void unCharge() {
        this.charged = false;
        this.chargeDelta = 0d;
        this.setColor(Color.BLACK);
    }

    public void setColor(Color color) {
        this.poly.setFill(color);
    }

    public void update() {
        //everything here is subject to change
        x += dX;
        y += dY;

        if (y > 500) {
            if (state != State.GROUND) {
                this.chargeUp();
            }

            y = 500.0;
            dY = 0.0;
            this.resetJump();
        }

        poly.setTranslateX(x);
        poly.setTranslateY(y);

        dY += 0.0015;
    }

    public void simpleJump() {
        if (state == State.GROUND) {
            state = State.AIR;
            this.dY -= 0.25;
        }
    }

    public void jump(KeyCode dir) {
        if (state == State.GROUND) {
            if (charged) {
                if (dir == KeyCode.UP) {
                    this.dY = -chargeDelta - 0.25;
                    state = State.AIR;
                } else if (dir == KeyCode.RIGHT) {
                    this.dY = -0.25;
                    this.dX = chargeDelta;
                    state = State.TURBO;
                } else if (dir == KeyCode.LEFT) {
                    this.dY = -0.25;
                    this.dX = -chargeDelta;
                    state = State.TURBO;
                }

                this.unCharge();
                this.setColor(Color.BLUE);
            } else {
                state = State.AIR;
                this.dY -= 0.25;
            }
        }
    }

    public void resetJump() {
        state = State.GROUND;
    }

    public void stopOnGround() {
        this.dX = 0d;
    }

    public void moveRight() {
        if (state == State.TURBO && dX < 0) {
            return;
        }

        if (dX < 0.15) {
            dX += 0.005;
        }
    }

    public void moveLeft() {
        if (state == State.TURBO && dX > 0) {
            return;
        }

        if (dX > -0.15) {
            dX -= 0.005;
        }
    }

    public void moveUp(int distance) {
        y -= distance;
        poly.setTranslateY(poly.getTranslateY() - distance);
    }

    public void moveDown() {
        y += 0.5;
        poly.setTranslateY(poly.getTranslateY() + 0.5);
    }

    @Override
    public String toString() {
        return poly.getTranslateX() + ":" + poly.getTranslateY();
    }
}
