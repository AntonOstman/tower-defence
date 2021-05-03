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

        final int size = (int) (tileSize * drawScale); // less size is bigger enemy
        final int enemyOffset = tileSize/2 - size/2; // should be changed to fit all sizes
        drawPosX = (int) (drawX * tileSize) + enemyOffset;
        drawPosY = (int) (drawY * tileSize) + enemyOffset;

        g2d.fillOval(getDrawPosX(), getDrawPosY(), size, size);
    }


    public void setPosition(final Point position) {
        this.position = position;
    }

    public void drawMove(Point start, Point end){
        double difX = start.x - position.x;
        double difY = start.y - position.y;

        drawX =  (position.x + (difX / speed) * moveAmount);
        drawY =  (position.y + (difY / speed) * moveAmount);

        if(moveAmount < speed){ moveAmount++; }
        else{
            moveAmount = 1;
            position = end;
        }



    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }


    public int getDrawPosX() {
	return drawPosX;
    }

    public int getDrawPosY() {
	return drawPosY;
    }
}
