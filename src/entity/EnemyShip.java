package entity;

import java.awt.Color;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager.SpriteType;


/**
 * Implements a enemy ship, to be destroyed by the player.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class EnemyShip extends Entity {
	
	/** Point value of a type A enemy. */
	private static final int A_TYPE_POINTS = 10;
	/** Point value of a type B enemy. */
	private static final int B_TYPE_POINTS = 20;
	/** Point value of a type C enemy. */
	private static final int C_TYPE_POINTS = 30;
	/** Point value of a bonus enemy. */
	private static final int BONUS_TYPE_POINTS = 100;
	/** Point value of a boss enemy. */
	private static final int BOSS_TYPE_POINTS = 1000;

	/** Cooldown between sprite changes. */
	private Cooldown animationCooldown;
	/** Checks if the ship has been hit by a bullet. */
	private boolean isDestroyed;
	/** Values of the ship, in points, when destroyed. */
	private int pointValue;
	/** Lives of ship, ship will be destroyed when life becomes 0. */
	private int EnemyLife;

	/**
	 * Constructor, establishes the ship's properties.
	 * 
	 * @param positionX
	 *            Initial position of the ship in the X axis.
	 * @param positionY
	 *            Initial position of the ship in the Y axis.
	 * @param spriteType
	 *            Sprite type, image corresponding to the ship.
	 * @param enemyColor
	 * 			  Color of enemyship.
	 */
	public EnemyShip(final int positionX, final int positionY,
			final SpriteType spriteType, Color enemyColor) {
		super(positionX, positionY, 12 * 2, 8 * 2, enemyColor);

		this.spriteType = spriteType;
		this.animationCooldown = Core.getCooldown(500);
		this.isDestroyed = false;

		switch (this.spriteType) {
		case EnemyShipA1:
		case EnemyShipA2:
			this.pointValue = A_TYPE_POINTS;
			this.EnemyLife = 1;
			break;
		case EnemyShipB1:
		case EnemyShipB2:
			this.pointValue = B_TYPE_POINTS;
			this.EnemyLife = 1;
			break;
		case EnemyShipC1:
		case EnemyShipC2:
			this.pointValue = C_TYPE_POINTS;
			this.EnemyLife = 2;
			break;
		default:
			this.pointValue = 0;
			break;
		}
	}

	/**
	 * Constructor, establishes the ship's properties for a special ship, with
	 * known starting properties.
	 *
	 * @param specialEnemyColor
	 * 			   Color of the special ship.
	 */
	public EnemyShip(Color specialEnemyColor) {
		super(-32, 60, 16 * 2, 7 * 2, specialEnemyColor);
		this.spriteType = SpriteType.EnemyShipSpecial;
		this.isDestroyed = false;
		this.pointValue = BONUS_TYPE_POINTS;
		this.EnemyLife = 1;
	}

	/**
	 * Constructor, establishes the ship's properties for a boss ship.
	 *
	 * @param enemylife
	 *            Lives of the boss ship.
	 * @param bossColor
	 * 			  Color of the boss ship.
	 */
	public EnemyShip(final int positionX, final int positionY, final int enemylife, Color bossColor) {
		super(positionX, positionY, 12 * 2, 8 * 2, Color.BLUE);
		this.spriteType = SpriteType.Boss;
		this.animationCooldown = Core.getCooldown(500);
		this.isDestroyed = false;
		this.pointValue = BOSS_TYPE_POINTS;
		this.EnemyLife = enemylife;

	}

	/**
	 * Getter for the score bonus if this ship is destroyed.
	 * 
	 * @return Value of the ship.
	 */
	public final int getPointValue() {
		return this.pointValue;
	}

	/**
	 * Moves the ship the specified distance.
	 * 
	 * @param distanceX
	 *            Distance to move in the X axis.
	 * @param distanceY
	 *            Distance to move in the Y axis.
	 */
	public final void move(final int distanceX, final int distanceY) {
		this.positionX += distanceX;
		this.positionY += distanceY;
	}

	/**
	 * Updates attributes, mainly used for animation purposes.
	 */
	public final void update() {
		if (this.animationCooldown.checkFinished()) {
			this.animationCooldown.reset();

			switch (this.spriteType) {
			case EnemyShipA1:
				this.spriteType = SpriteType.EnemyShipA2;
				break;
			case EnemyShipA2:
				this.spriteType = SpriteType.EnemyShipA1;
				break;
			case EnemyShipB1:
				this.spriteType = SpriteType.EnemyShipB2;
				break;
			case EnemyShipB2:
				this.spriteType = SpriteType.EnemyShipB1;
				break;
			case EnemyShipC1:
				this.spriteType = SpriteType.EnemyShipC2;
				break;
			case EnemyShipC2:
				this.spriteType = SpriteType.EnemyShipC1;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Reduces enemy's life when hit
	 */
	public final void reduceEnemyLife() {
		this.EnemyLife -= 1;
	}
	/**
	 * Getter for the life of enemyship.
	 *
	 * @return the rest of the enemy's life.
	 */
	public final int getEnemyLife() {
		return this.EnemyLife;
	}

	/**
	 * Destroys the ship, causing an explosion.
	 */
	public final void destroy() {
		this.isDestroyed = true;
		this.spriteType = SpriteType.Explosion;
	}

	/**
	 * Checks if the ship has been destroyed.
	 * 
	 * @return True if the ship has been destroyed.
	 */
	public final boolean isDestroyed() {
		return this.isDestroyed;
	}
}
