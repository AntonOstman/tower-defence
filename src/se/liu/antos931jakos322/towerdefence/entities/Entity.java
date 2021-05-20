package se.liu.antos931jakos322.towerdefence.entities;



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

    protected Entity(final Color color, final double size, final double speed) {
        this.position = null;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.movePosition = null;
    }
    // towers dont always need a speed so create another constructor for those
    protected Entity(final Color color, final double size) {
        this.position = null;
        this.color = color;
        this.size = size;
        this.speed = 0;
        this.movePosition = null;
    }

    /**
     * move() is the method entities can use to move towards the Point2D movePosition
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

    /*
    *
    * This is the method which is overridden by entities so they can create their own moving patterns
    * using the move(Point2d movePosition) method
    * this is later called by other classes to move the entity using its own logic for moving
    * */


    public void setPosition(final Point2D position) { this.position = position; }

    public Point2D getPosition() { return position; }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

}
