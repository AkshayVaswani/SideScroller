import java.awt.Rectangle;

public class FireballObject {

	private int frame;
	private int x;
	private int y;
	private Rectangle rect;
	
	public int getFrame() {
		return this.frame;
	}
	
	public void setFrame(int num) {
		if(num == 8) {
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
		rect = new Rectangle(x, y+30, 126, 50);
		return rect;
	}

}
