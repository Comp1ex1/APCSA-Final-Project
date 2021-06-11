import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;


public class Tetris extends Canvas implements Runnable, KeyListener{
    private Board board;
    private Game tetris;

    private BufferedImage back;
    private boolean[] keys;

    private int width = 300;
    private int height = 50;
    private int BLOCK_SIZE = 20;

    private Keys keyboard = new Keys();
    private long lastIteration = System.currentTimeMillis();

    public Tetris() {
      tetris = new Game();
      //frame = new JFrame();
      keys = new boolean[5];

      board = new Board();
     
      new Thread(this).start();
      addKeyListener(keyboard);

      //this.addKeyListener(this);
      setVisible(true);
    }

    public void update(Graphics g) {
        // if starting new game
        //if(keys[4] == true){
        if(keyboard.newGame()){
            System.out.println("game started / enter key pressed");
            tetris = new Game();
            tetris.startGame();
        }

        // if program is running
        if (tetris.isRunning()) {
            if (tetris.isMovingDown()) {
                tetris.moveDown();
            } else if (System.currentTimeMillis() - lastIteration >= tetris.getIterations()) {
                tetris.moveDown();
                lastIteration = System.currentTimeMillis();
            }
    
            // keyboard inputs
            //left key
            // if(keys[0] == true)
            // {
            //     tetris.moveLeft();
            //     keys[0] = false;
            // }
            // // right key
            // if(keys[1] == true)
            // {
            //     tetris.moveRight();
            //     keys[1] = false;
            // }
            // // rotating key
            // if(keys[2] == true)
            // {
            //     tetris.rotate();
            //     keys[2] = false;
            // }
            // //drop 
            // if(keys[3] == true)
            // {
            //     tetris.down();
            //     keys[3] = false;
            // }
    
            if (keyboard.rotateKey()) {
                tetris.rotate();
            } else if (keyboard.leftKey()) {
                tetris.moveLeft();
            } else if (keyboard.rightKey()) {
                tetris.moveRight();
            } else if (keyboard.drop()) {
                tetris.down();
            }
        }
        try {
            Thread.sleep(20);
        } catch (Exception e) { }
        paint(g);
        
    }

    public void paint(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D)window;
        if(back == null) {
            back = (BufferedImage)(createImage(getWidth(),getHeight()));
        }
        Graphics g = back.createGraphics();

        drawFullBoard(g);

        if (tetris.isRunning()) {
            drawBlocks(g);
        }

        if (tetris.isGameOver()) {
            drawBlocks(g);
            drawGameOver(g);
        }

        // draws the player's score
        g.setColor(Color.BLACK);
        g.drawString(getPlayerScore(), 10, 40);

        drawInstructions(g);

        //g.dispose();
        //strategy.show();

        twoDGraph.drawImage(back, null, 0, 0);

    }

    // drawing the GUI methods
    private void drawFullBoard(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.BLACK);
        g.drawRect(width - 1, height - 1, 10 * BLOCK_SIZE + 2, 20 * BLOCK_SIZE + 2);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("GAME OVER", 350, 525);
    }

    private void drawInstructions(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Press enter to begin!", 350, 500);
        g.drawString("Use up arrow to rotate, and left and right to move the blocks!", 200, 550);
    }

    private void drawBlocks(Graphics g) {
        Block[][] blocks = tetris.getBlocks();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.setColor(getBlockColor(blocks[i][j]));
                g.fillRect(width + i * 20, height + (19 - j) * 20, BLOCK_SIZE, BLOCK_SIZE);
                g.drawRect(width + i * 20, height + (19 - j) * 20, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }



    private String getPlayerScore() {
        return String.format("Score: %1s", tetris.getLinesCleared());
    }


    private Color getBlockColor(Block b) {
        if (b.getType() == null) {
            return Color.BLACK;
        }

        String type = b.getType().getPieceType();

        if(type.equals("O")){
            return Color.RED;
        } 
        else if(type.equals("I")) {
            return Color.GRAY;
        }
        else if(type.equals("S")) {
            return Color.CYAN;
        }
        else if(type.equals("Z")) {
            return Color.BLUE;
        }
        else if(type.equals("L")) {
            return Color.GREEN;
        }
        else if(type.equals("T")) {
            return Color.ORANGE;
        }

        return Color.MAGENTA;
    }

    
    public void keyPressed(KeyEvent e)
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
        //start new game
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			keys[4] = true;
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
      //no code needed here
    }


    public void run()
    {
        try
        {
            while(true)
            {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }
        catch(Exception e)
        {
        }
    }
    


    public static void main(String[] args) {
        Tetris run = new Tetris();
    }

}
