package Game.src;

import java.io.File;

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
        int potion = 2;
        int ether = 2;
        int redbull = 1;
        int whiskey = 8;
        int spinach = 3;
        int moonstone = 2;
        int armour = 3;

//misc  
        int lastAtk;
//sounds

        File attackSound = new File("attack.wav");
        File fireSound = new File("fire.wav");
        File lightningSound = new File("lightning.wav");
    
        Player(String name, int hp, int mp){
         this.name = name;
           this.hp = hp;
           this.mp = mp;
        }
    
        public int attack() {
            
            int max = 1500;
            int min = 67;
            int range = max - min + 1;
            this.lastAtk = (int) (Math.random() *  range) + min + this.str;
            return this.lastAtk;
        
        }

        public int fire() {

             int max = 250;
             int min = 120;
             int range = max - min + 1;
             this.lastAtk = (int) (Math.random() *  range) + min + this.mag;
             this.mp -= 50;
             return this.lastAtk;
        }

        public int lightning() {
        
            int max = 190;
            int min = 100;
            int range = max - min + 1;
            this.lastAtk = (int) (Math.random() *  range) + min + this.mag;
            this.mp -= 40;
            return this.lastAtk;
}

        public String heal() {
    
            int max = 280;
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

                if (this.potion > 0){
                this.hp += 200;
                this.potion -= 1;
                return String.format("%s used potion to heal 200HP!", this.name);
                }else{
                return String.format("No potions remaining!");
                }

                case "Ether":
                if (this.potion > 0){
                this.mp += 100;
                this.ether -= 1;
                return String.format("%s used ether for 100MP increase!", this.name);
                }else{
                    return String.format("No ethers remaining!");
                    }

                case "Whiskey":
                if (this.whiskey > 0){
                this.hp -= 100;
                this.def -= 10;
                this.whiskey -= 1;
                return String.format("%s drank whiskey, lost 100HP & 10 defence!", this.name);
                }else{
                    return String.format("The bottle is empty!");
                    }

                case "Red Bull":
                if (this.redbull > 0){
                this.redbull -= 1;
                this.speed += 2;
                return String.format("%s drank Red Bull to increase speed!", this.name);
                }else{
                    return String.format("No Red Bulls left!");
                    }

                case "Spinach":
                if (this.spinach > 0){
                this.str += 10;
                this.spinach -= 1;
                return String.format("%s ate spinach, strength increased by 10!", this.name);
                }else{
                    return String.format("No spinach left!");
                    }

                case "Moonstone":
                if (this.moonstone > 0){
                this.mag += 20;
                this.moonstone -= 1;
                return String.format("%s used moonstone, magic power increased by 20!", this.name);
                }else{
                    return String.format("No moonstones left!");
                    }

                case "Armour":
                if (this.armour > 0){
                this.def += 50;
                this.armour -= 1;
                return String.format("%s used armour, defence increased by 50!", this.name);
                }else{
                    return String.format("No armour left!");
                    }

            }
            return item;
        }
    }
    

