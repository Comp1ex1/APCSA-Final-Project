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

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;


public class Tetris extends JFrame
{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public Tetris()
    {
        super("TETRIS");
        Game tetrisGame = new Game();
        ((Component)tetrisGame).setFocusable(true);

        getContentPane().add(tetrisGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main( String args[] )
    {
        Tetris run = new Tetris();
    }
}
