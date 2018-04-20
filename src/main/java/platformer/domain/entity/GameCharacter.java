package platformer.domain.entity;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
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
    private Double jumpHeight;

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
        this.jumpHeight = 0.25;
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
        chargeDelta = (Math.abs(dX) + Math.abs(dY)) * 1.15;
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
    
    public boolean collision(Platform other) {
        Polygon shape = other.getPoly();

        Shape intersection = Shape.intersect(this.poly, shape);
        
        if (intersection.getBoundsInLocal().getWidth() != -1) {
            state = other.getType();
            return true;
        } else {
            return false;
        }
    }

    public void update() {
        //everything here is subject to change
        if (dX > 0.8) {
            dX = 0.8;
        } else if (dX < -0.8) {
            dX = -0.8;
        }
        
        if (dY < -1.1) {
            dY = -1.1;
        }
        
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
            this.dY -= jumpHeight;
        }
    }

    public void jump(KeyCode dir) {
        if (state == State.GROUND) {
            if (charged) {
                if (dir == KeyCode.UP) {
                    if (chargeDelta > jumpHeight) {
                        dY = -chargeDelta;
                    } else {
                        dY = -jumpHeight;
                    }

                    state = State.AIR;
                } else if (dir == KeyCode.RIGHT) {
                    if (chargeDelta > jumpHeight) {
                        dX = chargeDelta * 0.5;
                        dY = -chargeDelta * 0.5;
                    } else {
                        dX = chargeDelta;
                        dY = -jumpHeight;
                    }

                    state = State.TURBO;
                } else if (dir == KeyCode.LEFT) {
                    if (chargeDelta > jumpHeight) {
                        dX = -chargeDelta * 0.5;
                        dY = -chargeDelta * 0.5;
                    } else {
                        dX = -chargeDelta;
                        dY = -jumpHeight;
                    }

                    state = State.TURBO;
                }

                this.unCharge();
                this.setColor(Color.BLUE);
            } else {
                state = State.AIR;
                dY -= jumpHeight;
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
