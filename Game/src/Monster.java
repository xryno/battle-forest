package Game.src;

import java.io.File;

public class Monster  {
    String name;
    int hp;
    String monsterHPTot;
    
    //to set max random of monster attack when player dead.
    static int max2 = 2;
    static int min2 = 1;

    static int playerToAttack;

    File attackSound = new File("game/src/dragonattack.wav");

    Monster(String name, int hp){

       this.name = name;
       this.hp = hp;
       monsterHPTot = String.valueOf(this.hp);

    }


    public int attack() {
    
    // for randomising which player to attack

        int range2 = max2 - min2 + 1;
        playerToAttack = (int) (Math.random() *  range2) + min2 ;
        
    //for random attack values
        int max = 1250;
        int min = 500;
        int range = max - min + 1;
        int atkVal = (int) (Math.random() *  range) + min ;
        return atkVal;

    }

}
