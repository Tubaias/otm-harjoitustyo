package platformer.domain.entity;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import platformer.domain.State;

/**
 * A representation of the character the player controls in-game.
 * @author tote
 */
public class GameCharacter {

    private Polygon poly;
    private Polyline ghost;
    private double x;
    private double y;
    private double dX;
    private double dY;
    private State state;
    private boolean charged;
    private boolean goingRight;
    private boolean goingLeft;
    private double chargeDelta;
    private double jumpHeight;

    /**
     * GameCharacter class constructor. Initializes all private values.
     *
     * @param translateX Initial x-coordinate for the character to spawn in
     * @param translateY Initial y-coordinate for the character to spawn in
     */
    public GameCharacter(double translateX, double translateY) {
        poly = new Polygon(0, 0, 10, 0, 10, 10, 0, 10);
        poly.setTranslateX(translateX);
        poly.setTranslateY(translateY);

        this.x = translateX;
        this.y = translateY;
        this.dX = 0d;
        this.dY = 0d;
        this.chargeDelta = 0d;

        this.state = State.AIR;
        this.charged = false;

        this.jumpHeight = 0.35;
    }

    public Polygon getPoly() {
        return this.poly;
    }

    public Polyline getGhost() {
        return this.ghost;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return this.x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public double getDeltaX() {
        return this.dX;
    }

    public double getDeltaY() {
        return this.dY;
    }

    public State getState() {
        return this.state;
    }

    public double getChargeDelta() {
        return this.chargeDelta;
    }

    public boolean isCharged() {
        return this.charged;
    }

    /**
     * Sets the character in a charged state, saves current momentum in the
     * chargeDelta value and changes character color to red.
     */
    public void chargeUp() {
        if (charged) {
            return;
        }

        charged = true;
        chargeDelta = (Math.abs(dX) + Math.abs(dY)) * 1.20;
        this.setColor(Color.RED);
    }

    /**
     * Reverts the charged state, wipes momentum stored in chargeDelta and
     * changes character color to black.
     */
    public void unCharge() {
        this.charged = false;
        this.chargeDelta = 0d;
        this.setColor(Color.BLACK);
    }

    public void setColor(Color color) {
        this.poly.setFill(color);
    }

    public void setState(State state) {
        this.state = state;
    }

    /**
     * Checks collisions between the character and a platform and nudges the
     * character by one pixel when colliding with a wall or a ceiling.
     *
     * @param plat Platform to check collisions with
     * @return Returns true if a collision occurred, otherwise returns false.
     */
    public boolean collision(Platform plat) {
        Polygon shape = plat.getPoly();
        Shape intersection = Shape.intersect(this.poly, shape);

        if (this.ghost != null) {
            boolean collided = ghostCollision(plat);
            if (collided) {
                return true;
            }
        }

        return handlePlatformCollision(plat, intersection);
    }

    private boolean ghostCollision(Platform plat) {
        Polygon shape = plat.getPoly();
        Shape ghostIntersection = Shape.intersect(this.ghost, shape);

        return handlePlatformCollision(plat, ghostIntersection);
    }

    private boolean handlePlatformCollision(Platform plat, Shape intersection) {
        if (intersection.getBoundsInLocal().getWidth() != -1) {
            if (plat.getType() == State.RIGHTWALL) {
                x++;
            } else if (plat.getType() == State.LEFTWALL) {
                x--;
            } else if (plat.getType() == State.AIR) {
                dY = 0.0;
                y++;
            }

            return true;
        }

        return false;
    }

    /**
     * Checks collisions between the character and a coin.
     *
     * @param coin Coin to check collisions with
     * @return Returns true if a collision occurred, otherwise returns false.
     */
    public boolean coinCollision(Coin coin) {
        Shape shape = coin.getShape();
        Shape intersection = Shape.intersect(this.poly, shape);

        if (intersection.getBoundsInLocal().getWidth() != -1) {
            return true;
        }

        return false;
    }

    /**
     * Checks collisions between the character and an EndPoint.
     *
     * @param goal EndPoint to check collisions with
     * @return Returns true if a collision occurred, otherwise returns false.
     */
    public boolean goalCollision(EndPoint goal) {
        Shape shape = goal.getPoly();
        Shape intersection = Shape.intersect(this.poly, shape);

        if (intersection.getBoundsInLocal().getWidth() != -1) {
            return true;
        }

        return false;
    }

    /**
     * Handles all movement that happens on a frame.
     *
     * @param time Amount of time that's passed since the last update call.
     */
    public void update(long time) {
        double multiplier = time / 1_000_000;

        double oldX = x;
        double oldY = y;

        move();
        checkMovementBlocks();
        capDeltas();

        x += dX * multiplier;
        y += dY * multiplier;

        poly.setTranslateX(x);
        poly.setTranslateY(y);

        ghost = new Polyline(oldX + 5, oldY + 5, x + 5, y + 5);

        if (state != State.GROUND && !((state == State.LEFTWALL && charged) || (state == State.RIGHTWALL && charged))) {
            dY += 0.0015 * multiplier;
        }
    }

    private void checkMovementBlocks() {
        if (state == State.GROUND) {
            dY = 0.0;
        } else if (state == State.LEFTWALL) {
            if (charged) {
                dX = 0.0;
                dY = 0.0;
            } else if (dX > 0) {
                dX = 0.0;
            }
        } else if (state == State.RIGHTWALL) {
            if (charged) {
                dX = 0.0;
                dY = 0.0;
            } else if (dX < 0) {
                dX = 0.0;
            }
        }
    }

    private void capDeltas() {
        if (dX > 1.0) {
            dX = 1.0;
        } else if (dX < -1.0) {
            dX = -1.0;
        }

        if (dY < -1.3) {
            dY = -1.3;
        }
    }

    /**
     * Jumps in the given direction. If the character is charged a special
     * charged jump will be executed.
     *
     * @param dir Direction to jump
     */
    public void jump(KeyCode dir) {
        if (state == State.AIR || (!charged && (state == State.RIGHTWALL || state == State.LEFTWALL))) {
            return;
        }

        if (charged) {
            chargedJump(dir);
        } else {
            dY -= jumpHeight;
        }

        y--;
        state = State.AIR;
    }

    private void chargedJump(KeyCode dir) {
        if (dir == KeyCode.UP) {
            this.jumpUp();
        } else if (dir == KeyCode.RIGHT) {
            this.jumpRight();
        } else if (dir == KeyCode.LEFT) {
            this.jumpLeft();
        }

        this.unCharge();
        this.setColor(Color.BLUE);
    }

    private void jumpUp() {
        if (chargeDelta > jumpHeight) {
            dY = -chargeDelta;
        } else {
            dY = -jumpHeight;
        }
    }

    private void jumpRight() {
        if (chargeDelta > jumpHeight) {
            dX = chargeDelta * 0.5;
            dY = -chargeDelta * 0.5;
        } else {
            dX = chargeDelta;
            dY = -jumpHeight;
        }
    }

    private void jumpLeft() {
        if (chargeDelta > jumpHeight) {
            dX = -chargeDelta * 0.5;
            dY = -chargeDelta * 0.5;
        } else {
            dX = -chargeDelta;
            dY = -jumpHeight;
        }
    }

    /**
     * Stops horizontal movement in case the character is grounded to simulate
     * friction.
     */
    public void stopOnGround() {
        dX = 0d;
        goingRight = false;
        goingLeft = false;
    }

    private void move() {
        if (goingLeft && dX > -0.20) {
            dX -= 0.005;
        } else if (goingRight && dX < 0.20) {
            dX += 0.005;
        }

        goingLeft = false;
        goingRight = false;
    }

    /**
     * Sets up a flag for the character to move right in the next update.
     */
    public void moveRight() {
        goingRight = true;
        goingLeft = false;
    }

    /**
     * Sets up a flag for the character to move left in the next update.
     */
    public void moveLeft() {
        goingLeft = true;
        goingRight = false;
    }

    /**
     * Returns the character's current coordinates.
     *
     * @return Current coordinates separated by a ":" -sign
     */
    @Override
    public String toString() {
        return poly.getTranslateX() + ":" + poly.getTranslateY();
    }
}
