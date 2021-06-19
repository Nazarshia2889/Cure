import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends GameObject{
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	public boolean finishedLevel = false;
	
	public static int px = 0;
	public static int py = 0;
	
	public static boolean inBush = false;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Player(int x, int y, int width, int height){
		super(x, y, width, height);
		speed = 7;
		if(needImage) {
			loadImage("pixil-frame-0.png");
		}
		
	}
	
	void draw(Graphics g) {
	     if (gotImage) {
	    		g.drawImage(image, x, y, width, height, null);
	    	} else {
	    		g.setColor(Color.BLUE);
	    		g.fillRect(x, y, width, height);
	    	}
	}
	
	void update() {
		super.update();
		System.out.println("Updated");
		px = x;
		py = y;
			if(up == true) {
				up();
			}
			else if(down == true) {
				if(y < Cure.HEIGHT - 75) {
					down();
				}
			}
			else if(left == true) {
				if(x > 0) {
					left();
				}
			}
			else if(right == true) {
				if(x < Cure.WIDTH - 40) {
					right();
				}
			}
			
			if(y <= 0) {
				finishedLevel = true;
				System.out.println("finished level");
			}
		}
	
	
	public void up() {
		y -= speed;
	}
	public void down() {
		y += speed;
	}
	public void left() {
		x -= speed;
	}
	public void right() {
		x += speed;
	}
	
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
