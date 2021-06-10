import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;


public class Tetris extends Canvas {
    private JFrame frame;
    private JPanel panel;
    private Board board;
    private Game tetris;

    private final BufferStrategy strategy;

    private final int BOARD_CORNER_X = 300;
    private final int BOARD_CORNER_Y = 50;

    private final KeyboardInput keyboard = new KeyboardInput();
    private long lastIteration = System.currentTimeMillis();

    private static final int PIECE_WIDTH = 20;

    public Tetris() {
      tetris = new Game();
      frame = new JFrame();
      panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(800, 600));
      panel.setLayout(null);
      setBounds(0, 0, 800, 600);
      panel.add(this);
      setIgnoreRepaint(true);
      board = new Board();
      frame.setTitle("Tetris");
      //frame.add(panel);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
      frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
      //tetris.startGame();

      addKeyListener(keyboard);
      requestFocus();

      createBufferStrategy(2);
      strategy = getBufferStrategy();

    }

    void gameLoop() {

        while (true) {
            if(keyboard.newGame()){
              tetris = new Game();
              tetris.startGame();
            }
            if (tetris.isRunning()) {
                tetrisLoop();
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) { }
            draw();
        }
    }

    void tetrisLoop() {

      if (tetris.isMovingDown()) {
          tetris.moveDown();
      } else if (System.currentTimeMillis() - lastIteration >= tetris.getIterationDelay()) {
          tetris.moveDown();
          lastIteration = System.currentTimeMillis();
      }
      if (keyboard.rotate()) {
          tetris.rotate();
      } else if (keyboard.left()) {
          tetris.moveLeft();
      } else if (keyboard.right()) {
          tetris.moveRight();
      } else if (keyboard.down()) {
          tetris.down();
      }
    }

    public void draw() {
        Graphics2D g = getGameGraphics();
        drawEmptyBoard(g);
        drawHelpBox(g);
        drawPiecePreviewBox(g);

        if (tetris.isRunning()) {
            drawBlocks(g);

            /*if (game.isPaused()) {
                drawGamePaused(g);
            }*/
        }

        if (tetris.isGameOver()) {
            drawBlocks(g);
            drawGameOver(g);
        }

        drawStatus(g);
        drawPlayTetris(g);

        g.dispose();
        strategy.show();
    }

    private Graphics2D getGameGraphics() {
        return (Graphics2D) strategy.getDrawGraphics();
    }

    private void drawBlocks(Graphics2D g) {
        Block[][] blocks = tetris.getBlocks();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                drawBlock(g, BOARD_CORNER_X + i * 20, BOARD_CORNER_Y + (19 - j) * 20, getBlockColor(blocks[i][j]));
            }
        }
    }

    private void drawEmptyBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.GRAY);
        g.drawRect(BOARD_CORNER_X - 1, BOARD_CORNER_Y - 1, 10 * PIECE_WIDTH + 2, 20 * PIECE_WIDTH + 2);
    }

    private void drawStatus(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.BLUE);
        g.drawString(getLevel(), 10, 20);
        g.drawString(getLines(), 10, 40);
        g.drawString(getScore(), 20, 80);
    }

    private void drawGameOver(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString("GAME OVER", 350, 550);
    }

    /*private void drawGamePaused(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("GAME PAUSED", 350, 550);
    }*/


    private void drawPlayTetris(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString("Play TETRIS !", 350, 500);
    }

    private String getLevel() {
        return String.format("Your level: %1s", tetris.getLevel());
    }

    private String getLines() {
        return String.format("Full lines: %1s", tetris.getLinesCleared());
    }

    private String getScore() {
        return String.format("Score     %1s", tetris.getScore());
    }

    private void drawPiecePreviewBox(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.BLUE);
        g.drawString("Next:", 50, 420);
    }

    private void drawHelpBox(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.BLUE);
        g.drawString("H E L P", 50, 140);
        //g.drawString("F1: Pause Game", 10, 160);
        g.drawString("Enter: New Game", 10, 180);
        g.drawString("UP: Rotate", 10, 200);
        g.drawString("ARROWS: Move left/right", 10, 220);
        g.drawString("SPACE: Down", 10, 240);
    }



    private Color getBlockColor(Block block) {
        if (block.getType() == null) {
            return Color.BLACK;
        }
        return getPieceColor(block.getType());
    }

    private Color getPieceColor(PieceType pieceType) {
        if(pieceType.getPieceType().equals("O")){
          return Color.RED;
        } else if(pieceType.getPieceType().equals("I")) {
          return Color.GRAY;
        }
        else if(pieceType.getPieceType().equals("S")) {
            return Color.CYAN;
        }
        else if(pieceType.getPieceType().equals("Z")) {
            return Color.BLUE;
        }
        else if(pieceType.getPieceType().equals("L")) {
            return Color.GREEN;
        }
        else if(pieceType.getPieceType().equals("T")) {
            return Color.ORANGE;
        } else {
          return Color.MAGENTA;
        }

    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, PIECE_WIDTH, PIECE_WIDTH);
        g.drawRect(x, y, PIECE_WIDTH, PIECE_WIDTH);
    }

    public static void main(String[] args) {
        new Tetris().gameLoop();
    }
}
