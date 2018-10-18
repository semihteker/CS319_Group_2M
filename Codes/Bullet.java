/**
 * 
 * Author:Alper �ah�stan, Semih Teker
 * 
 */
import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Bullet extends DynamicGameObject implements Drawable{
	
	private float damage;
	protected Vector2f velocity;
	private boolean enemyBullet;
	private static float BULLET_SIZE = 8.7f;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Rectangle boundary = new Rectangle(-30*BULLET_SIZE, -30*BULLET_SIZE, 
			 super.getScreenWidth()+60*BULLET_SIZE, super.getscreenHeight()+ 60*BULLET_SIZE);
	
	public Bullet(Vector2f pos, Vector2f dim, Vector2f target, float speed, float damage, boolean enemyBullet)
	{
		super(pos, dim, speed);
		this.damage = damage;
		velocity = ((target.sub(super.getPosition())).normalise()).scale(speed);
		shape = new Ellipse(super.getPosition().getX(), super.getPosition().getY(), 
				super.getDimentions().getX(), super.getDimentions().getY());
		this.enemyBullet= enemyBullet;
		super.setScreenWidth((float) screenSize.getWidth()); 
		super.setscreenHeight((float)screenSize.getHeight());
	}

	public Bullet(Vector2f pos, Vector2f target, float speed, float damage)
	{
		super(pos, new Vector2f(BULLET_SIZE, BULLET_SIZE), speed);
		this.damage = damage;
		velocity = ((target.sub(super.getPosition())).normalise()).scale(speed);
		shape = new Ellipse(super.getPosition().getX(), super.getPosition().getY(), 
				super.getDimentions().getX(), super.getDimentions().getY());
		this.enemyBullet= false;
		super.setScreenWidth((float) screenSize.getWidth()); 
		super.setscreenHeight((float)screenSize.getHeight());
	}
	
	public Bullet(Vector2f pos, Vector2f target, float speed, float damage, boolean enemyBullet)
	{
		super(pos, new Vector2f(BULLET_SIZE, BULLET_SIZE), speed);
		this.damage = damage;
		velocity = ((target.sub(super.getPosition())).normalise()).scale(speed);
		shape = new Ellipse(super.getPosition().getX(), super.getPosition().getY(), 
				super.getDimentions().getX(), super.getDimentions().getY());
		this.enemyBullet= enemyBullet;
		super.setScreenWidth((float) screenSize.getWidth()); 
		super.setscreenHeight((float)screenSize.getHeight());
	}
	
	@Override
	void update() 
	{
		shape.setCenterX(getPosition().getX()); 
		shape.setCenterY(getPosition().getY());
		move();
		//Checks if the object is out of the screen
		if(boundary.intersects(shape))
			setToBeRemoved(true);//marks for removal
	}
	
	public void draw(Graphics g) 
	{
		if(isEnemyBullet())
			g.setColor(Color.red);
		else
			g.setColor(new Color(0.1f, 0.25f, 0.7f));
		
		//getting the object drawn from the center
		//System.out.println((int)(super.pos.getX()- (super.dimentions.getX()/2)));
		g.fill(shape);
	}
	
	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
	

	public boolean isEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(boolean enemyBullet) {
		this.enemyBullet = enemyBullet;
	}

	@Override
	void move() {
		super.setPosition((super.getPosition().add(velocity)));
	}

	/*@Override
	boolean collides(GameObject other) 
	{
		return this.getShape().intersects(other.getShape());
		
	}*/

}