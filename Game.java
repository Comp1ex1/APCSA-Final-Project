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

    private boolean gameRunning;
    private boolean pause;
    private boolean gameOver = false;
    private boolean movingDown = false;

    private int amountFallen;
  	//private Timer timer;


    public Game() {
        board = new Board();	
	}

	public void startGame() {
		if(pause)
		{
			return;
		}

		pause = false;
		gameRunning = true;
		board.setCurrentBlock(Block.getRandomBlock());
    	nextBlock = Block.getRandomBlock();
	}

	public boolean isRunning(){
		return gameRunning;
	}

	public boolean gameEnd()
	{
		for(int i = 0; i < board.getWidth(); i++)
		{
			if(board.isFilled(i, 20))
				gameOver = true;
		}
		gameOver = false;

		return gameOver;
	}


	public void down(){
		movingDown = true;
	}

	public Block[][] getBlocks(){
		return board.getBoardWithPiece();
	}

    public void update(Graphics window)
    {

    }

    /*public void paint(Graphics window)
	{

		//more code

		Graphics2D twoDGraph = (Graphics2D)window;

		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		Graphics graphToBack = back.createGraphics();
		graphToBack.drawString("Tetris", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);

		if(!gameOver)
		{
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

	}*/

	public long getIterations() {
        return (long) (((11 - getLevel()) * 0.05) * 1000);
    }

	public int getLevel()
	{
		return 1;
	}

	public void moveLeft() {
		board.moveLeft();
	}

	public void moveRight() {
		board.moveRight();
	}
	
	public void rotate() {
		board.rotate();
	}

  	public void moveDown() {
        if (!board.canMoveDown()) {
            if (amountFallen == 0) {
                gameRunning = false;
                gameOver = true;
			} 
			else {
                movingDown = false;
                board.setCurrentBlock(nextBlock);
                nextBlock = Block.getRandomBlock();
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
		// acts as score so multiply by 10
		return board.getLinesFull() * 10;
	}

	public boolean isGameOver(){
		return gameOver;
	}

  /*  public void keyPressed(KeyEvent e)
	{
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

	/*public void actionPerformed(ActionEvent e)
	{
		if(board.canMoveDown()){
			board.moveDown();
		}
		else{
			board.clearRows();
			board.setCurrentPiece(Block.getRandomBlock());
		}
	}*/


    /*public void run()
    {
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
