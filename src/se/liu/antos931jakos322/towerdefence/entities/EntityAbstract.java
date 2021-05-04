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


    public EntityAbstract(final Color color, final double drawScale, final int speed) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = speed;
        this.moveAmount = 0;
    }

    public EntityAbstract(final Color color, final double drawScale) {
        this.position = null;
        this.color = color;
        this.drawScale = drawScale;
        this.speed = 0;
        this.moveAmount = 0;
    }

    public void draw(final Graphics2D g2d, final int tileSize){

        g2d.setColor(color);

         // less size is bigger
        final int size = (int) (tileSize * drawScale);

        g2d.fillOval(drawPosX, drawPosY, size, size);
    }


    public void setPosition(final Point position) {
        this.position = position;
    }

    public boolean isMovementDone(Point end){

        if(position == null){
            position = end;
        }
        double difX = end.x - position.x;
        double difY = end.y - position.y;

        drawX =  (position.x + (difX / speed) * moveAmount);
        drawY =  (position.y + (difY / speed) * moveAmount);

        int tileSize = 50; // this should be fixed...
        final int size = (int) (50 * drawScale);
        final int offset = tileSize / 2 - size / 2;
        // the reason for this is because projectiles need to decide drawposx from other kriteria
        drawPosX = (int) (drawX * tileSize) + offset;
        drawPosY = (int) (drawY * tileSize) + offset;


        if(moveAmount < speed){ moveAmount++; }
        else{
            moveAmount = 1;
            position = end;
            return true;
        }
        return false;

    }

    public void drawMove2(double deltaX, double deltaY) {


        drawX = (position.x + ((double) drawPosX / speed) * moveAmount);
        drawY = (position.y + ((double) drawPosY / speed) * moveAmount);

        if (moveAmount < speed) {
            moveAmount++;
        } else {
            moveAmount = 1;
            position.x += (int) deltaX/50;
            position.y += (int) deltaY/50;

        }
    }


    public Point getPosition() {
        int posx = position.x;
        int posy = position.y;
        return new Point(posx, posy);
    }

    public Color getColor() {
        return color;
    }

    public Point getDrawPoint(){
        int drawPossX = drawPosX;
        int drawPossY = drawPossX;
        return new Point(drawPossX,drawPossY);
    }

    public int getDrawPosX() {
        int drawPos = drawPosX;
        return drawPos;
    }

    public int getDrawPosY() {
        int drawPos = drawPosY;
        return drawPos;
    }

    public void setDrawX(final double drawX) {
        this.drawX = drawX;
    }

    public void setDrawY(final double drawY) {
        this.drawY = drawY;
    }

    public void setDrawPosX(final int drawPosX) {
        this.drawPosX = drawPosX;
    }

    public void setDrawPosY(final int drawPosY) {
        this.drawPosY = drawPosY;
    }
}
