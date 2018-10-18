import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class SeniorChest extends Chest {

	Vector2f position;
	//Vector2f dimension;
	float duration;
	float remainingTime;
	Image img;
	Icon senChestIcon;
	
	public SeniorChest(Vector2f position, Image img) {
		this.position=position;
		//this.dimension=dimension;
		this.img = img;
		senChestIcon = new Icon(position, img);
		super.shape = new Rectangle(position.x, position.y, img.getWidth(), img.getHeight());
	}
	
	public SeniorChest(Vector2f position, Image img, float duration, float remainingTime){
		this.position=position;
		//this.dimension=dimension;
		this.img = img;
		this.duration = duration;
		this.remainingTime = remainingTime;
		senChestIcon = new Icon(position, img);
		super.shape = new Rectangle(position.x, position.y, img.getWidth(), img.getHeight());
	}
	
	public SeniorChest() 
	{
	}

	public void setPosition(Vector2f pos) {
		this.position = pos;		
	}

	public boolean collect(){
		return pickedUp;	
	}
	
	@Override
	public void draw(Graphics g) {
		if(!pickedUp){
			img.draw(position.x, position.y);
		}	
	}
	
	@Override
	public void update(){
		if(pickedUp){
			this.setToBeRemoved(true);
		}
		senChestIcon.update();
		if(remainingTime > 0){
			remainingTime--;
		}
		else{
			this.setToBeRemoved(true);
			if(remainingTime <= duration){
				remainingTime++;
			}
		}
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Item unlock()
	{
		double rand =  Math.random();
		if (rand<=0.18)
		{
			return new CaramelMacchiato();
		}
		
		else if (rand > 0.18 && rand <= 0.43)
		{
			return new OldExams();
		}
		
		else if (rand > 0.43 && rand <= 0.75)
		{
			return new Prozac();
		}
		
		else
		{
			return new HigherIQ();
		}
	}

}