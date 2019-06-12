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
	private float angle;
	private int x = 0, y = 0, cloud1 = 0, cloud2 = 0, ground = 0, rocks = 0, sky = 0, witchCount = 0, witchHeight = 0, fireballpos = 250, batTimer = 0;
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
	boolean restart=false,right=false;
	int imgCount=0;
	Polygon poly;
	Polygon poly2;
	boolean down = false;
	boolean up = false;
	
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
				fireRight[i]= fireballRight.getSubimage(126*i, 0, 126, 124);
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
	
		

		g2d.drawImage(witch[witchCount], 0, witchHeight,null);
		for(int i =0; i<batList.size(); i++) {
			g2d.drawImage(bat[batList.get(i).getFrame()], batList.get(i).getX(), batList.get(i).getY(), null);
		}
		for(int i=0; i<fireList.size();i++) {
			g2d.drawImage(fireRight[fireList.get(i).getFrame()], fireList.get(i).getX(), fireList.get(i).getY(), null);
//			fireList.get(i).setFrame(fireList.get(i).getFrame()+1);
//			fireList.get(i).setX(fireList.get(i).getX()+10);
		}
		
		g2d.setColor(Color.MAGENTA);
		GradientPaint gp = new GradientPaint((float)0.0, (float)0.0, Color.BLUE, (float)500.0, (float)500, Color.WHITE, true);
		g2d.setPaint(gp);
		

	}
	
	public void intersection() {
		for(int x = 0; x<batList.size(); x++) {
			for(int y = 0; y< fireList.size(); y++) {
				if(fireList.size()>0) {
					System.out.println(fireList.get(y).getX()+126);
				}
				if(batList.size()>0) {
					System.out.println("\t"+batList.get(x).getX());
				}
				if(fireList.size()>0 && batList.size()>0) {
					if(fireList.get(y).getX()+100 >= batList.get(x).getX() && fireList.get(y).getX() <= batList.get(x).getX()+92 && fireList.get(y)
							.getY() + 120>= batList.get(x).getY() && fireList.get(y).getY() <= batList.get(x).getY()+94) {
						batList.remove(x);
						fireList.remove(y);
						
						//break;
						//gameOn = false;
					}
				}
			}
		}
	}
	
	
	
	public void testingfire() {
		FireballObject tempFireObject = new FireballObject();
		tempFireObject.setFrame(0);
		tempFireObject.setX(250);
		tempFireObject.setY(witchHeight);
		fireList.add(tempFireObject);
	}
	public void testingbats() {
		BatObject tempBatObject = new BatObject();
		tempBatObject.setFrame(0);
		tempBatObject.setX(1700);
		tempBatObject.setY((int)(Math.random()*500)+1);
//		tempBatObject.setY(witchHeight);
		batList.add(tempBatObject);
	}
	
	public void refresh() {
		x-=5;
		cloud1-=2;
		cloud2-=5;
		ground-=2;
		rocks-=1;
		sky--;
		intersection();
		
		if (down && witchHeight<800) {
			witchHeight += 5;
		}
		if (up && witchHeight>0) {
			witchHeight -= 5;	
		}
		for(int i =0; i<batList.size(); i++) {
			
			batList.get(i).setFrame(batList.get(i).getFrame()+1);
		}
		for(int i=0; i<fireList.size();i++) {
			
			fireList.get(i).setFrame(fireList.get(i).getFrame()+1);
			fireList.get(i).setX(fireList.get(i).getX()+5);
		}
		if(!skip) {
			witchCount++;
			
			skip = true;
		}else {
			skip = false;
		}
		if(batTimer == 200) {
			testingbats();
			batTimer = 0;
		} else {
			batTimer++;
		}
		if(witchCount==12) {
			witchCount=0;
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
		if(key.getKeyCode()==39)
		{
			right = true;
			testingfire();
			//testingbats();
//			refresh();
		}
		if(key.getKeyCode()==40) {
			down = true;
		}
		if(key.getKeyCode()==38) {
			up = true;
		}
		if(key.getKeyCode()==82)
//			restart=true;
			if(batList.size()>0) {
				batList.remove((int)Math.random()*batList.size());
			}
		}
	public void keyReleased(KeyEvent key)
	{
		if(key.getKeyCode()==39)
		{
			right=false;
			
		}
		if(key.getKeyCode()==40)
		{
			down=false;
			
		}
		if(key.getKeyCode()==38)
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