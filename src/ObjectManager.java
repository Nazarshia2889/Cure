import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Player player;
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	ArrayList<Bush> bushes = new ArrayList<Bush>();
	Random random = new Random();
	Hospital hospital;
	
	
	ObjectManager(Player player){
		this.player = player;
	}
	
	void addBushLevelOne() {
		bushes.add(new Bush(100, 100, 250, 150));
		bushes.add(new Bush(100, 400, 250, 150));
		bushes.add(new Bush(800, 100, 250, 150));
		bushes.add(new Bush(800, 400, 250, 150));
	}
	void addBushLevelTwo() {
		bushes.add(new Bush(200, 250, 250, 150));
		bushes.add(new Bush(600, 250, 250, 150));
		bushes.add(new Bush(400, 600, 250, 150));
			
	}
		
	void addBushLevelThree() {
		bushes.add(new Bush(200, 600, 250, 150));
		bushes.add(new Bush(600, 200, 250, 150));
	}
		
	void addBushLevelFour() {
		bushes.add(new Bush(200, 500, 250, 150));
		bushes.add(new Bush(600, 500, 250, 150));
	}
	
	void addBushLevelFive() {
		bushes.add(new Bush(200, 600, 250, 150));
	}
	
	
	void addZombie() {
		int plusMinus = random.nextInt(2);
		int pixel = random.nextInt((350 - 100) + 1) + 100;
		if(GamePanel.currentState == 1 | GamePanel.currentState == 2 | GamePanel.currentState == 3 | GamePanel.currentState == 4) {
			if(plusMinus == 0) {
				zombies.add(new Zombie(player.px+pixel, 0, 100, 100));
			}
			else if(plusMinus == 1) {
				zombies.add(new Zombie(player.px-pixel, 0, 100, 100));
			}
		}
		if(GamePanel.currentState == 5) {
			zombies.add(new Zombie(0, random.nextInt(Cure.HEIGHT), 100, 100));
			zombies.add(new Zombie(Cure.WIDTH, random.nextInt(Cure.HEIGHT), 100, 100));
		}
	}
	
	void update() {
		checkCollision();
		for(int i = 0;i<zombies.size();i++){
			Zombie s = zombies.get(i);
			s.update();
			if(s.y > Cure.HEIGHT) {
				s.isActive = false;
			}
		}
		for(int i = 0;i<bushes.size();i++){
			Bush s = bushes.get(i);
			s.update();
			if(s.y > Cure.HEIGHT) {
				s.isActive = false;
			}
		}
	}
	
	void draw(Graphics g) {
		player.draw(g);
		
		for(int i = 0;i<zombies.size();i++) {
			Zombie s = zombies.get(i);
			s.draw(g);
		}
		
		for(int i = 0;i<bushes.size();i++) {
			Bush s = bushes.get(i);
			s.draw(g);
		}
	}
	
	void drawHospital(Graphics g) {
		hospital = new Hospital(475, -100, 500, 400);
		hospital.draw(g);
	}
	
	
	
	
	void checkCollision() {
		for(int i = 0;i<zombies.size();i++) {
			Zombie s = zombies.get(i);
			if(s.collisionBox.intersects(player.collisionBox)) {
				player.isActive = false;
				s.isActive = false;
				GamePanel.currentState = GamePanel.GAMEOVER;
			}		
		}
		boolean isInBush = false;
		
		for(int i = 0;i<bushes.size();i++) {
			Bush s = bushes.get(i);
			if(player.collisionBox.intersects(s.collisionBox)) {
				isInBush = true;
				break;
			}
		}
		player.inBush = isInBush;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addZombie();
	}
	
}
