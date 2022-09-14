package Game.src;

import java.io.File;
import java.util.ArrayList;

public class Player {

        String name;

//attributes

        int hp;
        int mp;
        int speed = 3;
        int def = 30;
        int str = 50;
        int mag = 30;

//items
       static int potion = 2;
       static int ether = 2;
       static int redbull = 1;
       static int whiskey = 8;
       static int spinach = 3;
       static int moonstone = 2;
       static int armour = 3;

//misc  
        int lastAtk;
        static ArrayList<Player> active = new ArrayList<Player>(0);
        static int player1Time = 180;
        static int player2Time = 165;
        
//sounds

        File attackSound = new File("game/src/attack.wav");
        File fireSound = new File("game/src/fire.wav");
        File lightningSound = new File("game/src/lightning.wav");
        File heal = new File("game/src/heal.wav");
    
        Player(String name, int hp, int mp){
         this.name = name;
           this.hp = hp;
           this.mp = mp;
        }
    
        public int attack() {
            
            int max = 250;
            int min = 67;
            int range = max - min + 1;
            this.lastAtk = (int) (Math.random() *  range) + min + this.str;

            return this.lastAtk;
        
        }

        public int fire() {

             int max = 450;
             int min = 230;
             int range = max - min + 1;
             this.lastAtk = (int) (Math.random() *  range) + min + this.mag;
             this.mp -= 50;
             return this.lastAtk;
        }

        public int lightning() {
        
            int max = 440;
            int min = 220;
            int range = max - min + 1;
            this.lastAtk = (int) (Math.random() *  range) + min + this.mag;
            this.mp -= 40;
            return this.lastAtk;
}

        public String heal() {
    
            int max = 350;
            int min = 200;
            int range = max - min + 1;
            int healVal = (int) (Math.random() *  range) + min ;
            this.hp += healVal + this.mag;
            this.mp -= 30;
            return String.format("%s cast heal for %s HP!", this.name, healVal+this.mag);

        }

        public String useItem(String item) {

            switch(item) {

                case "Potion":

                if (Player.potion > 0){
                this.hp += 400;
                Player.potion -= 1;
                return String.format("%s used potion to heal 200HP!", this.name);
                }else{
                return String.format("No potions remaining!");
                }

                case "Ether":
                if (Player.ether > 0){
                this.mp += 100;
                Player.ether -= 1;
                return String.format("%s used ether for 100MP increase!", this.name);
                }else{
                    return String.format("No ethers remaining!");
                    }

                case "Whiskey":
                if (Player.whiskey > 0){
                this.hp -= 100;
                this.def -= 10;
                Player.whiskey -= 1;
                return String.format("%s drank whiskey, lost 100HP & 10 defence!", this.name);
                }else{
                    return String.format("The bottle is empty!");
                    }

                case "Red Bull":
                if (Player.redbull > 0){
                    Player.redbull -= 1;
                this.speed += 2;
                return String.format("%s drank Red Bull to increase speed!", this.name);
                }else{
                    return String.format("No Red Bulls left!");
                    }

                case "Spinach":
                if (Player.spinach > 0){
                this.str += 10;
                Player.spinach -= 1;
                return String.format("%s ate spinach, strength increased by 10!", this.name);
                }else{
                    return String.format("No spinach left!");
                    }

                case "Moonstone":
                if (Player.moonstone > 0){
                this.mag += 20;
                Player.moonstone -= 1;
                return String.format("%s used moonstone, magic power increased by 20!", this.name);
                }else{
                    return String.format("No moonstones left!");
                    }

                case "Armour":
                if (Player.armour > 0){
                this.def += 50;
                Player.armour -= 1;
                return String.format("%s used armour, defence increased by 50!", this.name);
                }else{
                    return String.format("No armour left!");
                    }

            }
            return item;
        }
    }
    

