import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Game extends Canvas implements ActionListener, KeyListener, Runnable{

    private Board board;
    private Block nextBLock;
    
    private boolean run;
    private boolean pause;
    private boolean gameOver = false;

    private boolean[] keys;
    private BufferedImage back;

	private int score;
	private Timer timer;


    public Game() {
        board = new Board();
 
        setBackground(Color.black);
        keys = new boolean[4];

        this.addKeyListener(this);
        new Thread(this).start();
        
		setVisible(true);

		timer = new Timer(400, this);
		timer.start();
		
	}

	public void startGame() {
		if(pause)
		{
			return;
		}
		pause = false;
		run = true;
		board.setCurrentPiece(Block.getRandomBlock());
		timer.start();
	}
		
	public boolean gameEnd() {
		for(int i = 0; i < board.getWidth(); i++) {
			if(board.isFilled(i, 20))
				gameOver = true;
		}
		gameOver = false;

		timer.stop();
		return gameOver;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void updateScore() {
		score = board.getLinesFull() * 10;
	}

	public int getScore() {
		return score;
	}

    public void update(Graphics window) {
		if(!gameOver) {
			paint(window);
		}
    }

    public void paint(Graphics window) {

		//more code

		Graphics2D twoDGraph = (Graphics2D)window;

		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));
		  
		Graphics graphToBack = back.createGraphics();
		graphToBack.drawString("Tetris", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);
		
		if(!gameOver) {
			if(keys[0] == true)
			{
				board.moveLeft();
			}
			if(keys[1] == true)
      		{
				board.moveRight();
      		}
      		if(keys[2] == true)
      		{
        		board.getCurrentPiece().rotate();
      		}
      		if(keys[3] == true)
      		{	
				board.moveDown();
			}	
		}

		if (!gameOver){
			graphToBack.setColor(Color.white);
			graphToBack.drawString("SCORE: " +  score, 700, 30);
		}

	}
	
	public int getLevel() {
		if ((board.getLinesFull() >= 1) && (board.getLinesFull() <= 90)) {
            return 1 + ((board.getLinesFull() - 1) / 10);
        } else if (board.getLinesFull() >= 91) {
            return 10;
        } else {
            return 1;
        }
	}


    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		repaint();
	}


    public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
      //no code needed here
	}

	public void actionPerformed(ActionEvent e) {
		if(board.canMoveDown()){
			board.moveDown();
		}
		else{
			board.clearRows();
			board.setCurrentPiece(Block.getRandomBlock());	
		}
	}


    public void run() {
   	    try
   	    {
   		    while(true)
   		    {
   		    Thread.currentThread().sleep(5);
            repaint();
            }
        }catch(Exception e)
        {
        }
  	}

}