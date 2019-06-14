import java.awt.Rectangle;

public class HeroClass {
	private int frame;
	private int x;
	private int y;
	private int hp;
	private Rectangle rect;
	
	public int getFrame() {
		return this.frame;
	}
	
	public void setFrame(int num) {
		if(num == 12) {
			num = 0;
		}
		this.frame = num;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	public Rectangle getRect() {
		rect = new Rectangle(x, y, 280, 220);
		return rect;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	

}


