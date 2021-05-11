package se.liu.antos931jakos322.towerdefence.entities;


import java.awt.*;
import java.awt.geom.Point2D;

public class Entity
{


    // temporary class to be used while tranfering methods from tower and enemy to the abstract
    // so we still can use Entity Interface as to keep the game from breaking in the meanwhile

    protected Point2D position;
    protected Color color;
    protected double moveAmount;
    protected double drawPosX;
    protected double drawPosY;
    protected double drawScale; // many fields, can any be removed/moved?
    protected int speed;
    protected double drawX;
    protected double drawY;
    protected final static int TILE_SIZE = 50; // this should definately be changed later to not be hard coded


    public Entity(final Color color, final double drawScale, final int speed) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = speed;
        this.moveAmount = 0;
    }
    // towers dont need a speed
    public Entity(final Color color, final double drawScale) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = 0;
        this.moveAmount = 0;
    }

    public void draw(final Graphics2D g2d, final int tileSize){
        // drawPosX could be changed to a double and then in this draw method we cast to int
        // problem with a direct int is for example we cannot go "between" tiles only straight to it

        g2d.setColor(color);

        final int size = (int) (tileSize * drawScale);
        final int offset = tileSize / 2 - size / 2;

        int drawPositionX = (int) (drawPosX * tileSize) + offset;
        int drawPositionY = (int) (drawPosY * tileSize) + offset;



        g2d.fillOval(drawPositionX, drawPositionY, size, size);
    }

    public void move2(Point2D deltaDirection, double moveSpeed){


        // normalise the delta x and y direction to an angle
        double direction = Math.atan2(deltaDirection.getY() , deltaDirection.getX());
        // use the angle to calcutate the x and y move relations
        double directionY = Math.sin(direction);
        double directionX = Math.cos(direction);

        // set the draw position with the direction, later we will only use the Point2d
        double drawPosy = getDrawPosY() + directionY * moveSpeed ;
        double drawPosx = getDrawPosX() + directionX * moveSpeed ;

        drawPosX = drawPosx;
        drawPosY = drawPosy;


        // change the actual position with the calculated coordinates
        Point2D position = new Point2D.Double(getDrawPosX(),getDrawPosY());
        setPosition(position);

    }


    public void setPosition(final Point2D position) {
        this.position = position;
    }

    public boolean isMovementDone(Point2D end){
        // this move method can be done more generic to work for several entities
        // for example as done with projectiles we dont set a specifik end "tile" only a direction
        if(position == null){
            position = end;
        }

        double difX = end.getX() - position.getX();
        double difY = end.getY() - position.getY();

        drawX =  (position.getX() + (difX / speed) * moveAmount);
        drawY =  (position.getY() + (difY / speed) * moveAmount);

        // this should be fixed...
        // the reason for this is because projectiles need to decide drawposx from other kriteria
        drawPosX = (drawX);
        drawPosY = (drawY);


        if(moveAmount < speed){ moveAmount++; }
        else{
            moveAmount = 1;
            position = end;
            return true;
        }
        return false;

    }


    public Point2D getPosition() {
        double posx = position.getX();
        double posy = position.getY();
        return new Point2D.Double(posx, posy);
    }

    public Color getColor() {
        return color;
    }


    public double getDrawPosX() {
        return drawPosX;
    }

    public double getDrawPosY() {
        return drawPosY;
    }

    public void setDrawPosX(final int drawPosX) {
        this.drawPosX = drawPosX;
    }

    public void setDrawPosY(final int drawPosY) {
        this.drawPosY = drawPosY;
    }
}
