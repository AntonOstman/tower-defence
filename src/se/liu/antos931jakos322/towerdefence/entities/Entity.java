package se.liu.antos931jakos322.towerdefence.entities;


/**
 * Entity is an abstract which can move has a Point2D position, size and movement speed.
 *
 */

import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Entity
{

    protected Point2D position;
    protected Color color;
    protected double size;
    protected double speed;
    protected Point2D movePosition;
    protected int attackPower;

    /**
     * Constructs an entity with inital speed
     *
     * @param color the entity color
     * @param size the entity size
     * @param speed the entity speed
     * @param attackPower attackpower or "damage" of the tower
     */

    protected Entity(final Color color, final double size, final double speed, int attackPower) {
        this.position = null;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.movePosition = null;
        this.attackPower = attackPower;
    }

    /**
     * Constructs an entity without inital speed
     *
     * @param color the entity color
     * @param size the entity size
     * @param attackPower attackpower or "damage" of the tower
     */
    protected Entity(final Color color, final double size, int attackPower) {
        this.attackPower = attackPower;
        this.position = null;
        this.color = color;
        this.size = size;
        this.speed = 0;
        this.movePosition = null;
    }

    /**
     * Used by entites to move towards movePosition
     *
     * */

    public void move(){

        // if the entity is not near the movePosition it will continue moving
        double newY;
        double newX;
        final double distance = 0.1;
        if (!HelperFunctions.isNear(position, movePosition, distance)) {

            double deltaX = movePosition.getX() - position.getX();
            double deltaY = movePosition.getY() - position.getY();

            // normalise the delta x and y direction to an angle
            double direction = Math.atan2(deltaY , deltaX);
            // use the angle to calcutate the x and y  relations
            double directionY = Math.sin(direction);
            double directionX = Math.cos(direction);

            newY = position.getY() + directionY * speed;
            newX = position.getX() + directionX * speed;
        }
        // otherwise the entity will stop moving
        else{
            newY = position.getY();
            newX = position.getX();
        }
        // change the actual position with the calculated coordinates
        this.position = new Point2D.Double(newX, newY);
    }

    public void setPosition(final Point2D position) { this.position = position; }

    public void setMovePosition(final Point2D movePosition) {
        this.movePosition = movePosition;
    }

    public Point2D getPosition() { return position; }

    public Color getColor() {
        return color;
    }

    public double getSize() {
        return size;
    }

    public void setAttackPower(final int attackPower) {
        this.attackPower = attackPower;
    }

    public int getAttackPower(){ return attackPower; }
}
