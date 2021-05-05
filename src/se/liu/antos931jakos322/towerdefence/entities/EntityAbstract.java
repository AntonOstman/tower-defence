package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public class EntityAbstract
{


    // temporary class to be used while tranfering methods from tower and enemy to the abstract
    // so we still can use Entity Interface as to keep the game from breaking in the meanwhile

    protected Point position;
    protected Color color;
    protected double moveAmount;
    protected int drawPosX;
    protected int drawPosY;
    protected double drawScale; // many fields, can any be removed/moved?
    protected int speed;
    protected double drawX;
    protected double drawY;
    protected final static int TILE_SIZE = 50; // this should definately be changed later to not be hard coded


    public EntityAbstract(final Color color, final double drawScale, final int speed) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = speed;
        this.moveAmount = 0;
    }
    // towers dont need a speed
    public EntityAbstract(final Color color, final double drawScale) {
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
        final int offset = TILE_SIZE / 2 - size / 2;

        g2d.fillOval(drawPosX + offset, drawPosY + offset, size, size);
    }


    public void setPosition(final Point position) {
        this.position = position;
    }

    public boolean isMovementDone(Point end){
        // this move method can be done more generic to work for several entities
        // for example as done with projectiles we dont set a specifik end "tile" only a direction
        if(position == null){
            position = end;
        }

        double difX = end.x - position.x;
        double difY = end.y - position.y;

        drawX =  (position.x + (difX / speed) * moveAmount);
        drawY =  (position.y + (difY / speed) * moveAmount);

      // this should be fixed...
        // the reason for this is because projectiles need to decide drawposx from other kriteria
        drawPosX = (int) (drawX * TILE_SIZE);
        drawPosY = (int) (drawY * TILE_SIZE);


        if(moveAmount < speed){ moveAmount++; }
        else{
            moveAmount = 1;
            position = end;
            return true;
        }
        return false;

    }


    public Point getPosition() {
        int posx = position.x;
        int posy = position.y;
        return new Point(posx, posy);
    }

    public Color getColor() {
        return color;
    }



    public int getDrawPosX() {
        int drawPos = drawPosX;
        return drawPos;
    }

    public int getDrawPosY() {
        int drawPos = drawPosY;
        return drawPos;
    }


    public void setDrawPosX(final int drawPosX) {
        this.drawPosX = drawPosX;
    }

    public void setDrawPosY(final int drawPosY) {
        this.drawPosY = drawPosY;
    }
}
