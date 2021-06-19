import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	double speed;
	boolean isActive = true;
	Rectangle collisionBox;
	
	GameObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);
	}
	
	void update() {
		collisionBox.setBounds(x, y, width-50, height-50);
	}
	
	
	
}
