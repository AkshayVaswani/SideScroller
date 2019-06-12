
public class BatObject {
	private int frame;
	private int x;
	private int y;
	
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
	
	

}
