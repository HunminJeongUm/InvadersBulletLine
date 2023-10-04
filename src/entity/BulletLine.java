package entity;

import java.awt.Color;

import engine.DrawManager.SpriteType;
public class BulletLine extends Entity{
    /**
     * Constructor, establishes the ship's properties.
     *
     * @param positionX
     *            Initial position of the ship in the X axis.
     * @param positionY
     *            Initial position of the ship in the Y axis.
     */
    public BulletLine(final int positionX, final int positionY){

        super(positionX, positionY, 2 * 2,  80 * 2, Color.GRAY);

        this.spriteType = SpriteType.BulletLine;
    }
}
