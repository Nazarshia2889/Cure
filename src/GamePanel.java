import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final static int MENU = 0;
	final static int LEVELONE = 1;
	final static int LEVELTWO = 2;
	final static int LEVELTHREE = 3;
	final static int LEVELFOUR = 4;
	final static int LEVELFIVE = 5;
	final static int END = 6;
	final static int GAMEOVER = 7;
	
	static int currentState = MENU;
	
	Font titleFont;
    Font titleFontTwo;
    Font titleFontThree;
    Font titleFontFour;
    
    Font endFont;
    Font endFontTwo;
    Font endFontThree;
    Font endFontFour;
    
    Timer frameDraw;
    Timer zombieSpawn;
    
    Player player;
    ObjectManager objects;
    
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;
	
	GamePanel(){
		titleFont = new Font("Arial", Font.BOLD, 48);
    	titleFontTwo = new Font("Arial", Font.BOLD, 35);
    	titleFontThree = new Font("Albertus", Font.BOLD, 28);
    	titleFontFour = new Font("Albertus", Font.BOLD, 28);
    	
    	endFont = new Font("Charlesworth", Font.BOLD, 48);
    	endFontTwo = new Font("Charlesworth", Font.BOLD, 24);
    	endFontThree = new Font("Heather", Font.BOLD, 30);
    	endFontFour = new Font("Heather", Font.BOLD, 48);
    	
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.restart();
    	
    	if (needImage) {
    	    loadImage("grass.jpg");
    	}
	}
	
	
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU) {
			drawMenu(g);
		}
		else if(currentState == 1 || currentState == 2 || currentState == 3 || currentState == 4 || currentState == 5) {
			drawLevels(g);
		}
		else if(currentState == END) {
			drawEnd(g);
		}
		else if(currentState == GAMEOVER) {
			drawGameOver(g);
		}

	}

	
	//updates game states
	void updateMenu() {
		
	}
	void updateLevels() {
		System.out.println(player.isActive);
		if(player.isActive == true){
			player.update();
			objects.update();
			
			if(player.finishedLevel == true){
				currentState = currentState + 1;
				player.finishedLevel = false;
				
				if(currentState == 2) {
					zombieSpawn.stop();
					objects.bushes.clear();
					objects.zombies.clear();
					player.x = 250;
				    player.y = 700;
					startGameLevelTwo();
				    objects.addBushLevelTwo();
					}
				if(currentState == 3) {
					objects.bushes.clear();
					objects.zombies.clear();
					zombieSpawn.stop();
					startGameLevelThree();
				    objects.addBushLevelThree();
				    player.x = 250;
				    player.y = 700;
				    }
				if(currentState == 4) {
					objects.bushes.clear();
					objects.zombies.clear();
					zombieSpawn.stop();
				    startGameLevelFour();
				    objects.addBushLevelFour();
				    player.x = 250;
				    player.y = 700;
				    }
				if(currentState == 5) {
					objects.bushes.clear();
					objects.zombies.clear();
					zombieSpawn.stop();
			    	startGameLevelFive();
			    	objects.addBushLevelFive();
			    	player.x = 250;
				    player.y = 700;
				    }
			}

		}
	}
	void updateEnd() {
		
	}
	
	//draws game states
	void drawMenu(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, Cure.WIDTH, Cure.HEIGHT);
		
		if (gotImage) {
			g.drawImage(image, 0, 0, Cure.WIDTH, Cure.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Cure.WIDTH, Cure.HEIGHT);
		}
		
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.drawString("CURE", 450, 150);
		
		g.setFont(titleFontTwo);
		g.setColor(Color.WHITE);
		g.drawString("HOW TO PLAY:", 400, 300);
		
		g.setFont(titleFontThree);
		g.setColor(Color.WHITE);
		g.drawString("Oh no! A virus has arrived—zombies are everywhere! To move around, use the", 10, 400);
		g.drawString("arrowkeys. Bushes help you. Find and stand in them throughout each level to hide.", 10, 450);
		g.drawString("Survive five levels of unforgiving zombie attacks, and find the hospital to cure", 10, 500);
		g.drawString("the virus!", 500, 550);
		
		g.setFont(titleFontThree);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start", 400, 750);
	}
	void drawLevels(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, Cure.WIDTH, Cure.HEIGHT);
		if (gotImage) {
			g.drawImage(image, 0, 0, Cure.WIDTH, Cure.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Cure.WIDTH, Cure.HEIGHT);
		}
		
		if(currentState == 1 || currentState == 2 || currentState == 3 || currentState == 4) {
			objects.draw(g);
		}
		else if(currentState == 5) {
			objects.draw(g);
			objects.drawHospital(g);
		}
		
		if(currentState == 1) {
			g.setFont(titleFontThree);
			g.setColor(Color.WHITE);
			g.drawString("LEVEL ONE", 900, 25);
			g.drawString("Zombie Speed: 2", 900, 50);
		}
		else if(currentState == 2) {
			g.setFont(titleFontThree);
			g.setColor(Color.WHITE);
			g.drawString("LEVEL TWO", 900, 25);
			g.drawString("Zombie Speed: 2.5", 900, 50);
		}
		else if(currentState == 3) {
			g.setFont(titleFontThree);
			g.setColor(Color.WHITE);
			g.drawString("LEVEL THREE", 900, 25);
			g.drawString("Zombie Speed: 2.5", 900, 50);
		}
		else if(currentState == 4) {
			g.setFont(titleFontThree);
			g.setColor(Color.WHITE);
			g.drawString("LEVEL FOUR", 900, 25);
			g.drawString("Zombie Speed: 3", 900, 50);
		}
		else if(currentState == 5) {
			g.setFont(titleFontThree);
			g.setColor(Color.WHITE);
			g.drawString("FINAL LEVEL—Get to the cure!", 750, 25);
			g.drawString("Zombie Speed: 4", 900, 50);
		}
		
	}
	void drawGameOver(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Cure.WIDTH, Cure.HEIGHT);
		
		g.setFont(endFont);
		g.setColor(Color.BLUE);
		g.drawString("GAME OVER", 450, 200);
		
		g.setFont(endFontTwo);
		g.setColor(Color.BLUE);
		g.drawString("The zombies got you!", 450, 400);
		
		g.setFont(endFontThree);
		g.setColor(Color.BLUE);
		g.drawString("TIP: The longer you stall, the more zombies you will attract.", 100, 450);
		g.drawString("Move Quickly!", 500, 500);
		
		g.setFont(endFontTwo);
		g.setColor(Color.BLUE);
		g.drawString("Press ENTER to play again", 425, 650);
		
	}
	
	void drawEnd(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, Cure.WIDTH, Cure.HEIGHT);
		
		g.setFont(endFontFour);
		g.setColor(Color.BLUE);
		g.drawString("You did it!", 450, 200);
		
		g.setFont(endFontTwo);
		g.setColor(Color.BLUE);
		g.drawString("The cure has been found.", 425, 400);
		
		g.setFont(endFontTwo);
		g.setColor(Color.BLUE);
		g.drawString("Press ENTER to play again", 425, 500);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenu();
		}
		else if(currentState == 1 || currentState == 2 || currentState == 3 || currentState == 4 || currentState == 5){
		    updateLevels();
		}
		else if(currentState == END){
		    updateEnd();
		}
		
		
		repaint();
		
	}
	
	void startGameLevelOne() {
		player = new Player(250, 700, 125, 125);
	    objects = new ObjectManager(player);
		zombieSpawn = new Timer(3000, objects); 
		zombieSpawn.start();
	}
		
	void startGameLevelTwo() {
		zombieSpawn = new Timer(1000, objects);
		zombieSpawn.start();
		
	}
		
	void startGameLevelThree() {
		zombieSpawn = new Timer(1000, objects);
		zombieSpawn.start();
	}
		
	void startGameLevelFour() {
		zombieSpawn = new Timer(500, objects);
		zombieSpawn.start();
	}
	void startGameLevelFive() {
		zombieSpawn = new Timer(250, objects);
		zombieSpawn.start();
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState == MENU) {
		    	currentState++;
		    }
			else if(currentState == END) {
		        currentState = MENU;
		    } 
		    
		    else if(currentState == GAMEOVER) {
		    	currentState = MENU;
		    }
		   
		    if(currentState == 1)  {
				   startGameLevelOne();
				   objects.addBushLevelOne();
				    }
		}
		
		
		if(currentState == 1 || currentState == 2 || currentState == 3 || currentState == 4 || currentState == 5) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				player.up = true;
			}
			else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				player.down = true;
			}
			else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				player.right = true;
			}
			else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				player.left = true;
			}
			
		}
		
		if(currentState == END || currentState == GAMEOVER) {
			zombieSpawn.stop();
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			player.up = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.left = false;
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
