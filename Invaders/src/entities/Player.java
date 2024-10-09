package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.security.Key;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler key;

    public Player(GamePanel gp, KeyHandler key){
        this.gp = gp;
        this.key = key;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = this.gp.getScreenWidth()/2;
        y = this.gp.getScreenHeight()-this.gp.getTileSize();
        speed = 4;
    }

    public void getPlayerImage(){
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/player/ship.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(key.leftPressed){
            x -= speed;
        }
        if(key.rightPressed){
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(img, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }
}
