package se.liu.antos931jakos322.towerdefence.entities;



import java.awt.*;
import java.awt.geom.Point2D;

public class Entity
{

    protected Point2D position;
    protected Color color;
    protected double drawScale;
    protected double speed;


    public Entity(final Color color, final double drawScale, final double speed) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = speed;

    }
    // towers dont always need a speed so create another constructor for those
    public Entity(final Color color, final double drawScale) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = 0;
    }

/*
* This is the inner method which entities use to calculate and move towards a location
* @params movePosition - the position which the entity moves towards
* */
    public void move(Point2D movePosition){

        double deltaX = movePosition.getX() - position.getX();
        double deltaY = movePosition.getY() - position.getY();


        // normalise the delta x and y direction to an angle
        double direction = Math.atan2(deltaY , deltaX);
        // use the angle to calcutate the x and y  relations
        double directionY = Math.sin(direction);
        double directionX = Math.cos(direction);

        double newY = position.getY() + directionY * speed ;
        double newX = position.getX() + directionX * speed ;

        // change the actual position with the calculated coordinates
        this.position = new Point2D.Double(newX, newY);
    }

    /*
    *
    * This is the method which is overridden by entities so they can create their own moving patterns
    * using the move(Point2d movePosition) method
    * this is later called by other classes to move the entity using its own logic for moving
    * */
    public void move(){

    }



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
