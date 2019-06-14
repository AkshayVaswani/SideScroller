import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
public class GameTemplate extends JPanel implements KeyListener,Runnable
{
	private int x = 0, y = 0, cloud1 = 0, cloud2 = 0, ground = 0, rocks = 0, sky = 0, batTimer = 0;
	private JFrame frame;
	Thread t;
	private boolean gameOn;
	BufferedImage guy;
	BufferedImage[] guys=new BufferedImage[11];
	BufferedImage[] bg = new BufferedImage[6];
	BufferedImage witchImg;
	BufferedImage[] witch = new BufferedImage[12];
	BufferedImage batImage;
	BufferedImage[] bat = new BufferedImage[4];
	BufferedImage fireballRight;
	BufferedImage[] fireRight = new BufferedImage[8];
	ArrayList<BatObject> batList = new ArrayList<BatObject>();
	ArrayList<FireballObject> fireList = new ArrayList<FireballObject>();
	HeroClass hero = new HeroClass();
	int witchHP = 500;
	boolean restart=false,right=false;
	int imgCount=0;
	Polygon poly;
	Polygon poly2;
	boolean down = false;
	boolean up = false;
	Rectangle hpBox;
	
	boolean skip = false;

	public GameTemplate()
	{
		frame=new JFrame();
		gameOn=true;

		try {
			guy = ImageIO.read(new File("Pictures\\st1.png"));
			witchImg = ImageIO.read(new File("witch.png"));
			batImage = ImageIO.read(new File("bat.png"));
			fireballRight = ImageIO.read(new File("more images\\fireballEditedRight.png"));
			hpBox = new Rectangle(200, 10, 1500, 50);
			
			//284 x 220 witch
			//96  x  96 bat
			int tempx=0;
			int tempy=0;
			for(int i = 0; i<4; i++) {
				witch[i]= witchImg.getSubimage(tempx, tempy, 280, 220);
				tempx+=280;
			}
			tempx = 0;
			tempy=220;
			for(int i = 4; i<8; i++) {
				witch[i]= witchImg.getSubimage(tempx, tempy, 280, 220);
				tempx+=280;
			}
			tempx=0;
			tempy=440;
			for(int i = 8; i<12; i++) {
				witch[i]= witchImg.getSubimage(tempx, tempy, 280, 220);
				tempx+=280;
			}
			for(int i =0; i<4; i++) {
				bat[i]= batImage.getSubimage(92*i, 0, 92, 94);
			}
			for(int i =0; i<8; i++) {
				fireRight[i]= fireballRight.getSubimage(126*i, 0, 126, 100);
			}
			bg[0] = ImageIO.read(new File("layers\\sky.png"));
			bg[1] = ImageIO.read(new File("layers\\rocks.png"));
			bg[2] = ImageIO.read(new File("layers\\ground.png"));
			bg[3] = ImageIO.read(new File("layers\\clouds_2.png"));
			bg[4] = ImageIO.read(new File("layers\\clouds_1.png"));
			


		}
		catch (IOException e) {
		}
//		bgs[0]=bg[0].getScaledInstance(1920, 250,Image.SCALE_DEFAULT);
//		bgs[1]=bg[1].getScaledInstance(1920, 300,Image.SCALE_DEFAULT);
//		bgs[2]=bg[2].getScaledInstance(1920, 500,Image.SCALE_SMOOTH );

		hero.setFrame(0);
		hero.setX(0);
		hero.setY(0);
		hero.setHp(500);
		
		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
	}

	public void run()
	{
		while(true)
		{
			if(gameOn)
			{
				//Math happens here!
//				if((down && right) || (up && right)) {
//					refresh();
//				}else 
					//if(down || right || up){
					//if(down || up)
						//refresh();
					if(right) 
						refresh();
				//}else {
					refresh();
			//}
				
				

			}
			if(restart)
			{
				restart=false;
				gameOn=true;
			}
			try
			{
				t.sleep(20);
			}catch(InterruptedException e)
			{
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting happens here!

		
		g2d.setColor(Color.BLACK);
		
		g2d.drawImage(bg[0],sky-960,0,null);//sky
		g2d.drawImage(bg[0],sky+960,0,null);
		g2d.drawImage(bg[0],sky+2880,0,null);
		g2d.drawImage(bg[0],sky-2880,0,null);
		g2d.drawImage(bg[1],rocks-960,0,null);//rocks
		g2d.drawImage(bg[1],rocks+960,0,null);
		g2d.drawImage(bg[1],rocks+2880,0,null);
		g2d.drawImage(bg[1],rocks-2880,0,null);
		g2d.drawImage(bg[2],ground-960,0,null);//ground
		g2d.drawImage(bg[2],ground+960,0,null);
		g2d.drawImage(bg[2],ground+2880,0,null);
		g2d.drawImage(bg[2],ground-2880,0,null);
		g2d.drawImage(bg[3],cloud2-960,0,null);//cloud 2
		g2d.drawImage(bg[3],cloud2+960,0,null);
		g2d.drawImage(bg[3],cloud2+2880,0,null);
		g2d.drawImage(bg[3],cloud2-2880,0,null);
		g2d.drawImage(bg[4],cloud1-960,0,null);//cloud 1
		g2d.drawImage(bg[4],cloud1+960,0,null);
		g2d.drawImage(bg[4],cloud1+2880,0,null);
		g2d.drawImage(bg[4],cloud1-2880,0,null);
	
		
		g2d.setColor(Color.magenta);
		g2d.drawImage(witch[hero.getFrame()], hero.getX(), hero.getY(),null);
		for(int i =0; i<batList.size(); i++) {
			g2d.drawImage(bat[batList.get(i).getFrame()], batList.get(i).getX(), batList.get(i).getY(), null);
			g2d.draw(batList.get(i).getRect());
		}
		for(int i=0; i<fireList.size();i++) {
			g2d.drawImage(fireRight[fireList.get(i).getFrame()], fireList.get(i).getX(), fireList.get(i).getY(), null);
			g2d.draw(fireList.get(i).getRect());
//			fireList.get(i).setFrame(fireList.get(i).getFrame()+1);
//			fireList.get(i).setX(fireList.get(i).getX()+10);
		}
		
		
		g2d.setColor(Color.black);
		g2d.draw(hpBox);
		g.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		g2d.drawString("HP: " + hero.getHp(), 30, 50);
		g2d.fillRect((int)hpBox.getX(), (int)hpBox.getY(), (int)hpBox.getWidth(), (int)hpBox.getHeight());
		g2d.setColor(Color.cyan);
		g2d.fillRect((int)hpBox.getX(), (int)hpBox.getY(), hero.getHp()*3, (int)hpBox.getHeight());
	
		

	}

	public void healthBar() {
		
	}
	
	
	public void intersection() {
		for(int x = 0; x<batList.size(); x++) {
			for(int y = 0; y< fireList.size(); y++) {
				if(fireList.size()>0 && batList.size()>0) {
//					if(fireList.get(y).getX()+100 >= batList.get(x).getX() && fireList.get(y).getX() <= batList.get(x).getX()+92 && fireList.get(y)
//							.getY() + 120>= batList.get(x).getY() && fireList.get(y).getY() <= batList.get(x).getY()+94) {
					if(fbToBat(fireList.get(y), batList.get(x))) {
						batList.remove(x);
						fireList.remove(y);
						if(y != 0) {
							y--;
						}
						if(x != 0) {
							x--;
						}						
						//break;
						//gameOn = false; 
					}
				}				
			}
			if(batToWitch(batList.get(x), hero)) {
				batList.remove(x);
				if(x!=0) {
					x--;
				}
			}
		}
		
	}
	
	public boolean fbToBat(FireballObject obj1, BatObject obj2) {
		if(obj1.getRect().intersects(obj2.getRect())) {
			return true;
		}
		return false;
	}
	public boolean batToWitch(BatObject obj1, HeroClass obj2) {
		if(obj1.getRect().intersects(obj2.getRect())) {
			obj2.setHp(obj2.getHp()-50);
			System.out.println(obj2.getHp());
			
			return true;
		}
		return false;
	}
	public void testingfire() {
		FireballObject tempFireObject = new FireballObject();
		tempFireObject.setFrame(0);
		tempFireObject.setX(200);
		tempFireObject.setY(hero.getY()+50);
		fireList.add(tempFireObject);
	}
	public void testingbats() {
		BatObject tempBatObject = new BatObject();
		tempBatObject.setFrame(0);
		tempBatObject.setX(1700);
		tempBatObject.setY((int)(Math.random()*500)+1);
		tempBatObject.setInitY(tempBatObject.getY());
//		if((int)(Math.random()*2)+1 == 1) {
			tempBatObject.setDirection(true);
//		}else {
//			tempBatObject.setDirection(false);
//		}
		tempBatObject.setWiggleDown(tempBatObject.getInitY()+100);
		tempBatObject.setWiggleUp(tempBatObject.getInitY()-100);
//		tempBatObject.setY(witchHeight);
		batList.add(tempBatObject);
	}
	
	public void refresh() {
		x-=5;
		cloud1-=2;
		cloud2-=5;
		ground-=2;
		rocks--;
		sky--;
		intersection();
		
		if (down && hero.getY()<800) {
			hero.setY(hero.getY()+5);
		}
		if (up && hero.getY()>0) {
			hero.setY(hero.getY()-5);
		}
		for(int i =0; i<batList.size(); i++) {
			BatObject temp = batList.get(i);
			temp.setFrame(temp.getFrame()+1);
			temp.setX(temp.getX()-3);
			//System.out.println(i+" "+temp.isDirection()+" - "+temp.getWiggleDown() + " " + temp.getWiggleUp()+" "+temp.getY());

			if(temp.isDirection()) {
				temp.setY(temp.getY()-10);
				if(temp.getY() <= temp.getWiggleUp()) {
					temp.setDirection(false);
				}
			} else {
				temp.setY(temp.getY()+10);
				if(temp.getY() >= temp.getWiggleDown()) {
					temp.setDirection(true);
				}
			}
		}
		for(int i=0; i<fireList.size();i++) {
			
			fireList.get(i).setFrame(fireList.get(i).getFrame()+1);
			fireList.get(i).setX(fireList.get(i).getX()+5);
		}
		if(!skip) {
			hero.setFrame(hero.getFrame()+1);
			skip = true;
		}else {
			skip = false;
		}
		if(batTimer == 75) {
			testingbats();
			batTimer = 0;
		} else {
			batTimer++;
		}
		if(x==-1920) {
			x=0;
		}
		if(cloud1==-1920) {
			cloud1=0;
		}
		if(cloud2==-1920) {
			cloud2=0;
		}
		if(ground==-1920) {
			ground=0;
		}
		if(rocks==-1920) {
			rocks=0;
		}
		if(sky==-1920) {
			sky=0;
		}
		repaint();
	}
	public void keyPressed(KeyEvent key)
	{
		System.out.println(key.getKeyCode());
		if(key.getKeyCode()==39 || key.getKeyCode()==68)
		{
			right = true;
			
//			testingbats();
//			refresh();
		}
		if(key.getKeyCode()==40 || key.getKeyCode()==83) {
			down = true;
		}
		if(key.getKeyCode()==38 || key.getKeyCode()==87) {
			up = true;
		}
		if(key.getKeyCode()==82) {
			restart=true;
			if(batList.size()>0) {
				batList.remove((int)Math.random()*batList.size());
			}
		}
		if(key.getKeyCode()==32) {
			testingfire();
		}
	}
	public void keyReleased(KeyEvent key)
	{
		if(key.getKeyCode()==39 || key.getKeyCode()==6)
		{
			right=false;
			
		}
		if(key.getKeyCode()==40 || key.getKeyCode()==83)
		{
			down=false;
			
		}
		if(key.getKeyCode()==38 || key.getKeyCode()==87)
		{
			up=false;
			
		}
		
		
	}
	public void keyTyped(KeyEvent key)
	{
	}
	public static void main(String args[])
	{
		GameTemplate app=new GameTemplate();
	}
}