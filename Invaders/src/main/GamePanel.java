package main;

import entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    KeyHandler key = new KeyHandler();
    int originalTileSize = 11;
    int scale = 4;
    int tileSize = originalTileSize*scale;
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth = tileSize*maxScreenCol;
    int screenHeight = tileSize*maxScreenRow;
    int FPS = 60;
    Thread gameThread;

    Player myShip = new Player(this, key);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(key);
    }

    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = 0;
        long timer = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime-lastTime) / drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                //update
                update();

                //draw
                repaint();

                delta--;
            }
        }
    }

    public void update(){
        myShip.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //actually draw
        myShip.draw(g2);
        g2.dispose();
    }
}
