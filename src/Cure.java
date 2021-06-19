import javax.swing.JFrame;

public class Cure {
	JFrame frame;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 1000;
	GamePanel gamepanel;
	
	public static void main(String[] args) {
		Cure cure = new Cure();
		cure.setup();
	}
	
	Cure(){
		frame = new JFrame();
		gamepanel = new GamePanel();
	}
	
	void setup() {
		frame.add(gamepanel);
		frame.addKeyListener(gamepanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);;
	}
	

}
