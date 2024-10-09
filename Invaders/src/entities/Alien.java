package entities;

import main.GamePanel;

public class Alien extends Entity{
    GamePanel gp;

    private int difficulty = 1;
    private int direction = 1;
    private int moveDown;

    public Alien(GamePanel gp, int x, int y, int difficulty){
        this.gp = gp;
        this.moveDown = this.gp.getTileSize()/2;
        this.difficulty = 60/difficulty;
    }


}
