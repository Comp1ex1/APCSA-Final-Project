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


//public class Game extends Canvas implements ActionListener, KeyListener, Runnable{
public class Game{
    private Board board;
    private Block nextBlock;

    private boolean run;
    private boolean pause;
    private boolean gameOver = false;
    private boolean movingDown = false;

    private boolean[] keys;
    private BufferedImage back;

    private int amountFallen;
  	private int score;
  	//private Timer timer;


    public Game() {
        board = new Board();

        //setBackground(Color.black);
        //keys = new boolean[4];

        //this.addKeyListener(this);
        //new Thread(this).start();

  		//setVisible(true);

  		//timer = new Timer(400, this);
  		//timer.start();

	}

	public void startGame() {
		if(pause)
		{
			return;
		}
		pause = false;
		run = true;
		board.setCurrentPiece(Block.getRandomBlock());
		//timer.start();
    nextBlock = Block.getRandomBlock();
	}
<<<<<<< Updated upstream
		
	public boolean gameEnd() {
		for(int i = 0; i < board.getWidth(); i++) {
=======

  public boolean isRunning(){
    return run;
  }

	public boolean gameEnd()
	{
		for(int i = 0; i < board.getWidth(); i++)
		{
>>>>>>> Stashed changes
			if(board.isFilled(i, 20))
				gameOver = true;
		}
		gameOver = false;

		//timer.stop();
		return gameOver;
	}

<<<<<<< Updated upstream
	public Timer getTimer() {
=======
	/*public Timer getTimer()
	{
>>>>>>> Stashed changes
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}*/

	public void updateScore() {
		score = board.getLinesFull() * 10;
	}

<<<<<<< Updated upstream
	public int getScore() {
		return score;
	}

    public void update(Graphics window) {
		if(!gameOver) {
			paint(window);
		}
    }

    public void paint(Graphics window) {
=======
  public void down(){
    movingDown = true;
  }
  public Block[][] getBlocks(){
    return board.getBoardWithPiece();
  }
	public int getScore()
	{
		return score;
	}

    public void update(Graphics window)
    {

    }

    /*public void paint(Graphics window)
	{
>>>>>>> Stashed changes

		//more code

		Graphics2D twoDGraph = (Graphics2D)window;

		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		Graphics graphToBack = back.createGraphics();
		graphToBack.drawString("Tetris", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);
<<<<<<< Updated upstream
		
		if(!gameOver) {
=======

		if(!gameOver)
		{
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
	}
	
	public int getLevel() {
=======
	}*/

	public long getIterationDelay() {
        return (long) (((11 - getLevel()) * 0.05) * 1000);
    }

	public int getLevel()
	{
>>>>>>> Stashed changes
		if ((board.getLinesFull() >= 1) && (board.getLinesFull() <= 90)) {
            return 1 + ((board.getLinesFull() - 1) / 10);
        } else if (board.getLinesFull() >= 91) {
            return 10;
        } else {
            return 1;
        }
	}

  public void moveLeft() {
        board.moveLeft();
    }

<<<<<<< Updated upstream
    public void keyPressed(KeyEvent e) {
=======
  public void moveRight() {
      board.moveRight();
  }
  public void rotate() {
      board.rotate();
  }

  public void moveDown() {
        if (!board.canMoveDown()) {

            if (amountFallen == 0) {
                run = false;
                gameOver = true;
            } else {
                movingDown = false;
                board.setCurrentPiece(nextBlock);
                nextBlock = Block.getRandomBlock();
                score += getScore();
                amountFallen = 0;
            }
        } else {
            board.moveDown();
            amountFallen++;
        }
    }

  public boolean isMovingDown(){
    return movingDown;
  }
  public int getLinesCleared(){
    return board.getLinesFull();
  }
  public boolean isGameOver(){
    return gameOver;
  }
  /*  public void keyPressed(KeyEvent e)
	{
>>>>>>> Stashed changes
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
	}*/

<<<<<<< Updated upstream
	public void actionPerformed(ActionEvent e) {
=======
	/*public void actionPerformed(ActionEvent e)
	{
>>>>>>> Stashed changes
		if(board.canMoveDown()){
			board.moveDown();
		}
		else{
			board.clearRows();
			board.setCurrentPiece(Block.getRandomBlock());
		}
	}*/


<<<<<<< Updated upstream
    public void run() {
=======
    /*public void run()
    {
>>>>>>> Stashed changes
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
*/
}
