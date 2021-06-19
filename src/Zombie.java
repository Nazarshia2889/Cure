import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Zombie extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Zombie(int x, int y, int width, int height){
		super(x, y, width, height);
		if(GamePanel.currentState == 1) {
			speed = 2;
		}
		else if(GamePanel.currentState == 2 | GamePanel.currentState == 3) {
			speed = 2.5;
		}
		else if(GamePanel.currentState == 4) {
			speed = 3;
		}
		else if(GamePanel.currentState == 5) {
			speed = 4;
		}

		if (needImage) {
		    loadImage ("zombie.gif");
		}
	}
	
	void update() {
		super.update();
		if(GamePanel.currentState == 1 | GamePanel.currentState == 2 | GamePanel.currentState == 3 | GamePanel.currentState == 4) {
			if(Player.inBush == false) {
				if(x < Player.px) {
					x += speed;
				}
				if(x > Player.px) {
					x -= speed;
				}
				if(y < Player.py) {
					y += speed;
				}
				if(y > Player.py){
				y -= speed;
				}
			}
			else if(Player.inBush == true){
				x += speed;
			}
		}
		else if(GamePanel.currentState == 5) {
			if(Player.inBush == false) {
				if(x < Player.px) {
					x += speed;
				}
				if(x > Player.px) {
					x -= speed;
				}
				if(y < Player.py) {
					y += speed;
				}
				if(y > Player.py){
					y -= speed;
				}
			}
			else if(Player.inBush == true){
				x += speed;
			}
		}
	}
	
	 void draw(Graphics g) {
	     if (gotImage) {
	    		g.drawImage(image, x, y, width, height, null);
	    	} else {
	    		g.setColor(Color.YELLOW);
	    		g.fillRect(x, y, width, height);
	    	}
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
