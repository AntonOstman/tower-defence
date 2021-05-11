package se.liu.antos931jakos322.towerdefence.entities;


import java.awt.*;
import java.awt.geom.Point2D;

public class Entity
{


    // temporary class to be used while tranfering methods from tower and enemy to the abstract
    // so we still can use Entity Interface as to keep the game from breaking in the meanwhile

    protected Point2D position;
    protected Color color;
    protected double drawScale; // many fields, can any be removed/moved?
    protected double speed;
    protected final static int TILE_SIZE = 50; // this should definately be changed later to not be hard coded


    public Entity(final Color color, final double drawScale, final double speed) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = speed;
    }
    // towers dont need a speed
    public Entity(final Color color, final double drawScale) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = 0;
    }

    public void move(Point2D deltaDirection){
        // normalise the delta x and y direction to an angle
        double direction = Math.atan2(deltaDirection.getY() , deltaDirection.getX());
        // use the angle to calcutate the x and y move relations
        double directionY = Math.sin(direction);
        double directionX = Math.cos(direction);

        // set the draw position with the direction, later we will only use the Point2d
        double newY = position.getY() + directionY * speed ;
        double newX = position.getX() + directionX * speed ;

        // change the actual position with the calculated coordinates
        this.position = new Point2D.Double(newX, newY);
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
