package Game.src;

import java.io.File;

public class Monster  {
    String name;
    int hp;

    File attackSound = new File("dragonattack.wav");

    Monster(String name, int hp){
     this.name = name;
       this.hp = hp;
    }


    public int attack() {

        int max = 500;
        int min = 120;
        int range = max - min + 1;
        int atkVal = (int) (Math.random() *  range) + min ;
        return atkVal;

    }

}
