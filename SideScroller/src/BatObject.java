import java.awt.Rectangle;

public class BatObject {
	private int frame;
	private int x;
	private int y;
	private int initY;
	private int wiggleUp;
	private int wiggleDown;
	private boolean direction;
	private int parabola;
	private Rectangle rect;
	

	
	public int getFrame() {
		return this.frame;
	}
	
	public void setFrame(int num) {
		if(num == 4) {
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
		rect = new Rectangle(x, y, 92, 94);
		return rect;
	}

	public int getParabola() {
		return parabola;
	}

	public void setParabola(int parabola) {
		this.parabola = parabola;
	}

	public int getInitY() {
		return initY;
	}

	public void setInitY(int initY) {
		this.initY = initY;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public int getWiggleUp() {
		return wiggleUp;
	}

	public void setWiggleUp(int wiggleUp) {
		this.wiggleUp = wiggleUp;
	}

	public int getWiggleDown() {
		return wiggleDown;
	}

	public void setWiggleDown(int wiggleDown) {
		this.wiggleDown = wiggleDown;
	}





}
