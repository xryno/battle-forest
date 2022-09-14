package Game.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Main {
    static int timerElapsed = 0;
    public static void main(String... args) {

        JFrame application = createGUI();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setVisible(true);

    }

     private static void PlaySound(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    public static JFrame createGUI () {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
           
        }
        
        JButton attackButton = new JButton("Attack");
        JButton fireButton = new JButton("Fire");
        JButton lightningButton = new JButton("Lightning");
        JButton healButton = new JButton("Heal");
        JButton magicButton = new JButton("Magic");
        JButton backButton = new JButton("Back");
        JButton backButtonI = new JButton("Back");
        JButton useButton = new JButton("Use");
        JButton itemButton = new JButton("Items");
        JButton restartButton = new JButton("Restart");
        restartButton.setVisible(false);
        JLabel combatMsgs = new JLabel();
        JPanel startScreen = new JPanel();
        Timer timer;
        Timer timer2;
        Timer timerEnemy;
        Timer gameTimer;
        final JProgressBar progressBar = new JProgressBar();
        final JProgressBar progressBar2 = new JProgressBar();
        final JProgressBar progressBarE = new JProgressBar();
        final JButton button = new JButton("Start game");
        JLabel nameTitle = new JLabel("<html><B>Name</B></html>");
        JLabel timeTitle = new JLabel("<html><B>Time</B></html>");
        JLabel hpTitle = new JLabel("<html><B>HP</B></html>");
        JLabel mpTitle = new JLabel("<html><B>MP</B></html>");
        JLabel monsterTitle = new JLabel("<html><B>Monster</B></html>");
        JLabel mtimeTitle = new JLabel("<html><B>Time</B></html>");
        JLabel mhpTitle = new JLabel("<html><B>HP</B></html>");
        JLabel playerHPL = new JLabel();
        JLabel playerMPL = new JLabel();
        JLabel player2HPL = new JLabel();
        JLabel player2MPL = new JLabel();
        JLabel enemyHPL = new JLabel();
        JLabel arrayinfo = new JLabel();
        File music = new File("game/src/bgmusic.wav");
        

        //enemy char
        Monster monster = new Monster("Pedro", 8000);
        JLabel monsterName = new JLabel(monster.name);
        JPanel enemyc = new JPanel();
        enemyc.setBounds(500, 80, 300, 268);
        enemyc.setOpaque(false); 
        ImageIcon enemypic = new ImageIcon("game/src/enemy.png");
        enemyc.add(new JLabel(enemypic));

        //player 1
        Player player = new Player("Tidus", 3000, 250);
        JLabel playerName = new JLabel(player.name);
        JPanel mainc = new JPanel();
        mainc.setBounds(30, 240, 122, 85);
        mainc.setOpaque(false); 
        ImageIcon maincpic = new ImageIcon("game/src/main.png");
        mainc.add(new JLabel(maincpic));
        
        //player 2 
        Player player2 = new Player("Terra", 2500, 300);
        JLabel playerName2 = new JLabel(player2.name);
        JPanel mainc2 = new JPanel();
        mainc2.setBounds(120, 220, 122, 85);
        mainc2.setOpaque(false); 
        ImageIcon maincpic2 = new ImageIcon("game/src/terra.png");
        mainc2.add(new JLabel(maincpic2));

        //fire 
        JPanel fire = new JPanel();
        fire.setBounds(490, 60, 300, 300);
        fire.setOpaque(false); 
        fire.setVisible(false);
        ImageIcon firepic = new ImageIcon("game/src/fire.gif");
        fire.add(new JLabel(firepic));

        //lightning
        JPanel lightning = new JPanel();
        lightning.setBounds(490, 60, 300, 300);
        lightning.setOpaque(false); 
        lightning.setVisible(false);
        ImageIcon lightningpic = new ImageIcon("game/src/lightning.gif");
        lightning.add(new JLabel(lightningpic));

         //bulk set sizes
        Dimension butDim = new Dimension(110, 23);
        Dimension butDimI = new Dimension(55, 23);
        Dimension labelDim = new Dimension(80, 18);

        backButtonI.setPreferredSize(butDimI);
        useButton.setPreferredSize(butDimI);

        JButton[] buttonsArr = {attackButton, magicButton, backButton,fireButton,lightningButton,healButton,itemButton};
        for(JButton each : buttonsArr) {
            each.setPreferredSize(butDim);
        }
       
        JLabel[] labelsArr = {playerName, playerName2, monsterName, playerHPL, playerMPL, player2HPL, player2MPL, enemyHPL, combatMsgs, nameTitle, timeTitle, hpTitle, mpTitle, mhpTitle, monsterTitle, mtimeTitle};
        for(JLabel each : labelsArr) {
            each.setForeground(Color.white);
        }

        JLabel[] StatlabelsArr = {playerName, playerName2, monsterName, playerHPL, playerMPL, player2HPL, player2MPL,  nameTitle, timeTitle};
        for(JLabel each : StatlabelsArr) {
            each.setPreferredSize(labelDim);
        }

        enemyHPL.setPreferredSize(new Dimension(118, 18));
        progressBar.setPreferredSize(new Dimension(118, 18));
        progressBar2.setPreferredSize(new Dimension(118, 18));
        progressBarE.setPreferredSize(new Dimension(118, 18));

        String playerHPLTot = String.valueOf(player.hp);
        String playerMPLTot = String.valueOf(player.mp);
        String player2HPLTot = String.valueOf(player2.hp);
        String player2MPLTot = String.valueOf(player2.mp);
      
//player1 bar
     ActionListener updateProBar = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {

            //player animations
            int currentX = mainc.getBounds().x;
            if (currentX > 30) {
                mainc.setBounds(currentX -275, 240, 122, 85);
            } else {
            if (currentX != 30) {
                mainc.setBounds(30, 240, 122, 85);
            } else {
                mainc.setBounds(29, 240, 122, 85);
            }

            // if (monster.hp <= 0) {
            //     return;
            // }

//enable buttons when progress bar full
          int val = progressBar.getValue();

          if (val >= 100) {
            for(JButton each : buttonsArr) {
                each.setEnabled(true);
                playerName.setForeground(Color.YELLOW);
                playerName.setText(String.valueOf("<html><b>" + player.name + "</b></html>"));
            }
       
            if (!Player.active.contains(player)) {
                Player.active.add(player);
            }

            }
          progressBar.setValue(val + player.speed);
        }
       }
      };

      timer = new Timer(Player.player1Time, updateProBar);

 //player2 bar
 ActionListener updateProBar2 = new ActionListener() {
    public void actionPerformed(ActionEvent actionEvent) {
  
        //player animations
        int currentX = mainc2.getBounds().x;
        if (currentX > 120) {
            mainc2.setBounds(currentX -200, 220, 122, 85);
        } else {
        if (currentX != 120) {
            mainc2.setBounds(120, 220, 122, 85);
        } else {
            mainc2.setBounds(119, 220, 122, 85);
        }

//enable buttons when progress bar full
      int val = progressBar2.getValue();

      if (val >= 100) {
        for(JButton each : buttonsArr) {
            each.setEnabled(true);
        }

        if (!Player.active.contains(player2)) {
            Player.active.add(player2);
        }

        playerName2.setForeground(Color.YELLOW);
        playerName2.setText(String.valueOf("<html><b>" + player2.name + "</b></html>"));

        }
        
      
      progressBar2.setValue(val + player2.speed);
    }
   }
  };     

  timer2 = new Timer(Player.player2Time, updateProBar2);

//enemy bar
      ActionListener updateEProBar = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {

 //  monster animations
            int currentX = enemyc.getBounds().x;
            int maxX = -9, maxY = -9, minX = 9, minY = 9;
            int rangeX = maxX - minX + 1;
            int rangeY = maxY - minY + 1;
            int moveValX = (int) (Math.random() *  rangeX) + minX ;
            int moveValY = (int) (Math.random() *  rangeY) + minY ;

            if (currentX != 500) {
                enemyc.setBounds(500, 80, 300, 268);
            } else {
                enemyc.setBounds(500 + moveValX, 80 + moveValY, 300, 268);
            }

          //  check if monster dead
          
            if (monster.hp <= 0) {
                combatMsgs.setText(String.format("%s defeated %s! New Monster!", player.name, monster.name));
                enemyc.setBounds(29, 240, 0, 0);

        // next monster
                monster.hp = 25000;
                monster.name = "Bahamut";
                enemyc.setBounds(500, 80, 300, 268);
                enemyc.setOpaque(false); 
                enemyc.setBackground( new Color(255, 0, 0, 20) );
                ImageIcon enemypic2 = new ImageIcon("game/src/bahamut.png");
                enemyc.remove(0);
                enemyc.add(new JLabel(enemypic2));
                monster.monsterHPTot = (String.valueOf(monster.hp));
                return;
            }
            
            if (player.hp <= 0 && player2.hp <= 0) {
                return;
            }

         
//monster attack
          int val = progressBarE.getValue();
          if (val >= 100) {

            PlaySound(monster.attackSound);
            int atkVal = monster.attack();
           

            if (Monster.playerToAttack == 1) {
                player.hp -= atkVal;
                enemyc.setBounds(20, 100, 300, 268);
                combatMsgs.setText(String.format("%s attacked %s for %d HP", monster.name, player.name, atkVal));
            } else {

                player2.hp -= atkVal;
                enemyc.setBounds(25, 80, 300, 268);
                combatMsgs.setText(String.format("%s attacked %s for %d HP", monster.name, player2.name, atkVal));
            }
            progressBarE.setValue(0);
            return;
          }
          
          progressBarE.setValue(++val);
        }
      };

      
//game timer

      ActionListener updateGameTimer = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
        timerElapsed += 150;

        if (timerElapsed >= 139000) {
            PlaySound(music);
            timerElapsed = 0;
        }
            

        playerHPL.setText(String.valueOf(player.hp + "/" + playerHPLTot ));
        playerMPL.setText(String.valueOf(player.mp + "/" + playerMPLTot));
        player2HPL.setText(String.valueOf(player2.hp + "/" + player2HPLTot));
        player2MPL.setText(String.valueOf(player2.mp + "/" + player2MPLTot));
        enemyHPL.setText(String.format(monster.hp + "/" + monster.monsterHPTot));
        monsterName.setText(String.format(monster.name));
        
// check player 1 dead

        if (player.hp <= 0 && timer.isRunning()) {
            progressBar.setValue(0);
            timer.stop();
            combatMsgs.setText(String.format("%s died!", player.name));
            mainc.setBounds(29, 240, 0, 0);
            if (Player.active.contains(player)){
                Player.active.remove(Player.active.indexOf(player));
             }
             Monster.max2 = 2;
             Monster.min2 = 2;

            playerName.setForeground(Color.WHITE);
            playerName.setText(String.valueOf(player.name));
            Player.player1Time = 0;
            return;
        }
// check player 2 dead

        if (player2.hp <= 0 && timer2.isRunning()) {
             progressBar2.setValue(0);
            timer2.stop();
            combatMsgs.setText(String.format("%s died!", player2.name));
            mainc2.setBounds(29, 240, 0, 0);
            if (Player.active.contains(player2)){
                Player.active.remove(Player.active.indexOf(player2));
             }
           
             Monster.max2 = 1;
             Monster.min2 = 1;

             playerName2.setForeground(Color.WHITE);
             playerName2.setText(String.valueOf(player2.name));
             Player.player2Time = 0;
            return;
        }
// check both players dead

        if (player.hp <= 0 && player2.hp <= 0) {

            combatMsgs.setText(String.format("Game over!"));
            mainc.setBounds(29, 240, 0, 0);
            mainc2.setBounds(29, 240, 0, 0);
            attackButton.setEnabled(false);
            magicButton.setEnabled(false);
            itemButton.setEnabled(false);
            restartButton.setVisible(true);
            return;
        }
        
        if (monster.hp <= 0) {
            return;
        }
        }
      };

      timerEnemy = new Timer (180, updateEProBar); 
      gameTimer = new Timer(150, updateGameTimer);


//start button
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            startScreen.setVisible(false);
            button.setVisible(false);

            PlaySound(music);
            
          if (timer.isRunning()) {
            timer.stop();
            timer2.stop();
            timerEnemy.stop();
            gameTimer.stop();
            button.setText("Start game");
          } else if (button.getText() != "End") {
            timer.start();
            timer2.start();
            timerEnemy.start();
            gameTimer.start();
            button.setText("Stop");
          }
        }
      });


        for(JButton each : buttonsArr) {
            each.setEnabled(false);
        }


        JFrame gui = new JFrame("Battle Forest");
        gui.setSize(new Dimension(800, 500));
        gui.getContentPane().setBackground(Color.black);
        gui.setLayout(null);
      
        JPanel controlPanel2;
        controlPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel2.setBounds(380, 350, 120, 150);
        controlPanel2.setBackground(Color.blue);
        controlPanel2.add(attackButton);
        controlPanel2.add(magicButton);
        controlPanel2.add(itemButton);
   
        JPanel controlPanel2MAGIC;
        controlPanel2MAGIC = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel2MAGIC.setBounds(380, 350, 120, 150);
        controlPanel2MAGIC.setBackground(Color.blue);
        controlPanel2MAGIC.add(fireButton);
        controlPanel2MAGIC.add(lightningButton);
        controlPanel2MAGIC.add(healButton);
        controlPanel2MAGIC.add(backButton);
        controlPanel2MAGIC.setVisible(false);
        

        magicButton.addActionListener(event -> {
            controlPanel2MAGIC.setVisible(true);
            controlPanel2.setVisible(false);
            
          });

          DefaultListModel<String> l1 = new DefaultListModel<>();  
          JList<String> list = new JList<>(l1);  
          list.setBackground(Color.BLUE);
          list.setForeground(Color.WHITE);
      
          JPanel controlPanel2ITEMS;
          
          controlPanel2ITEMS = new JPanel(new FlowLayout(FlowLayout.LEFT));
          controlPanel2ITEMS.setBounds(380, 350, 120, 150);
          controlPanel2ITEMS.setBackground(Color.blue);
          JScrollPane scrollpane = new JScrollPane(list);
          scrollpane.setPreferredSize(new Dimension(120, 70));
       
           controlPanel2ITEMS.add(scrollpane);
          controlPanel2ITEMS.add(useButton);
          controlPanel2ITEMS.add(backButtonI);
          controlPanel2ITEMS.setVisible(false);

          backButton.addActionListener(event -> {
            controlPanel2MAGIC.setVisible(false);
            controlPanel2ITEMS.setVisible(false);
            controlPanel2.setVisible(true);
            
          });

          backButtonI.addActionListener(event -> {
            controlPanel2MAGIC.setVisible(false);
            controlPanel2ITEMS.setVisible(false);
            controlPanel2.setVisible(true);
            
          });


//Player attack
attackButton.addActionListener(event -> {
    PlaySound(Player.active.get(0).attackSound);
    monster.hp -= Player.active.get(0).attack();
    combatMsgs.setText(String.format("%s attacked %s for %d HP!", Player.active.get(0).name, monster.name, Player.active.get(0).lastAtk));

    if (Player.active.get(0) == player){
        mainc.setBounds(550, 240, 122, 85);
        progressBar.setValue(0);
        playerName.setForeground(Color.WHITE);
        playerName.setText(String.valueOf(player.name));
    } else if (Player.active.get(0) == player2){
        mainc2.setBounds(510, 220, 122, 85);
        progressBar2.setValue(0);
        playerName2.setForeground(Color.WHITE);
        playerName2.setText(String.valueOf(player2.name));
    }

        Player.active.remove(0);

    for(JButton each : buttonsArr) {
        each.setEnabled(false);
    }

});
//player fire magic

  fireButton.addActionListener(event -> {

    if (Player.active.get(0).mp >= 50) {
        PlaySound(Player.active.get(0).fireSound);
        fire.setVisible(true);
        setTimeout(() -> fire.setVisible(false), 1000);


    monster.hp -= Player.active.get(0).fire();
    combatMsgs.setText(String.format("%s cast fire spell for %d HP!", Player.active.get(0).name, Player.active.get(0).lastAtk));
  
    if (Player.active.get(0) == player){
        progressBar.setValue(0);
        playerName.setForeground(Color.WHITE);
        playerName.setText(String.valueOf(player.name));
    } else if (Player.active.get(0) == player2){
        progressBar2.setValue(0);
        playerName2.setForeground(Color.WHITE);
        playerName2.setText(String.valueOf(player2.name));
    }

//switch queued player to pos 0
    Player.active.remove(0);

    for(JButton each : buttonsArr) {
        each.setEnabled(false);
    }
    controlPanel2MAGIC.setVisible(false);
    controlPanel2.setVisible(true);

    } else {
        combatMsgs.setText(String.format("Not enough MP!"));
    }
  
});
//player lightning magic

lightningButton.addActionListener(event -> {

    if (Player.active.get(0).mp >= 40) {

        PlaySound(Player.active.get(0).lightningSound);
        lightning.setVisible(true);
        setTimeout(() -> lightning.setVisible(false), 1000);
        monster.hp -= Player.active.get(0).lightning();
        combatMsgs.setText(String.format("%s cast lightning spell for %d HP!", Player.active.get(0).name, Player.active.get(0).lastAtk));

        if (Player.active.get(0) == player){
            progressBar.setValue(0);
            playerName.setForeground(Color.WHITE);
            playerName.setText(String.valueOf(player.name));
        } else if (Player.active.get(0) == player2){
            progressBar2.setValue(0);
            playerName2.setForeground(Color.WHITE);
            playerName2.setText(String.valueOf(player2.name));
        }
    
    //switch queued player to pos 0
    
       Player.active.remove(0);
    
        for(JButton each : buttonsArr) {
            each.setEnabled(false);
        }
        controlPanel2MAGIC.setVisible(false);
        controlPanel2.setVisible(true);

        } else {
            combatMsgs.setText(String.format("Not enough MP!"));
        }
      
    });
//player heal magic


          healButton.addActionListener(event -> {
            if (Player.active.get(0).mp >= 30) {
                PlaySound(Player.active.get(0).heal);
                combatMsgs.setText(Player.active.get(0).heal());
            
                if (Player.active.get(0) == player){
                    progressBar.setValue(0);
                    playerName.setForeground(Color.WHITE);
                    playerName.setText(String.valueOf(player.name));
                } else if (Player.active.get(0) == player2){
                    progressBar2.setValue(0);
                    playerName2.setForeground(Color.WHITE);
                    playerName2.setText(String.valueOf(player2.name));
                }
            
            //switch queued player to pos 0
            
                Player.active.remove(0);

                for(JButton each : buttonsArr) {
                    each.setEnabled(false);
                }

                controlPanel2MAGIC.setVisible(false);
                controlPanel2.setVisible(true);
    
             } else {
                    combatMsgs.setText(String.format("Not enough MP!"));
            }
          
        });
          
          itemButton.addActionListener(event -> {
            l1.removeAllElements();
              l1.addElement("Potion x " + Player.potion);  
              l1.addElement("Ether x " + Player.ether);  
              l1.addElement("Red Bull x " + Player.redbull);  
              l1.addElement("Whiskey x " + Player.whiskey); 
              l1.addElement("Spinach x " + Player.spinach); 
              l1.addElement("Moonstone x " + Player.moonstone); 
              l1.addElement("Armour x " + Player.armour); 
            controlPanel2ITEMS.setVisible(true);
            controlPanel2MAGIC.setVisible(false);
            controlPanel2.setVisible(false);
            
          });


        useButton.addActionListener(event -> {
            String usedItem = list.getSelectedValue().substring(0, list.getSelectedValue().length() - 4);
            combatMsgs.setText(Player.active.get(0).useItem(usedItem));
            controlPanel2ITEMS.setVisible(false);
            controlPanel2.setVisible(true);

            if (Player.active.get(0) == player){
                progressBar.setValue(0);
                playerName.setForeground(Color.WHITE);
                playerName.setText(String.valueOf(player.name));
            } else if (Player.active.get(0) == player2){
                progressBar2.setValue(0);
                playerName2.setForeground(Color.WHITE);
                playerName2.setText(String.valueOf(player2.name));
            }
        
        //switch queued player to pos 0
        Player.active.remove(0);


            for(JButton each : buttonsArr) {
                each.setEnabled(false);
            }
     });
//reset game

     restartButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
    
            timer.start();
            timer2.start();
            timerEnemy.start();
            gameTimer.start();
            player.hp = 3000;
            player2.hp = 2500;
            player.mp = 250;
            player2.mp = 300;
            monster.hp = 8000;
            monster.name = "Pedro";
            player.speed = 3;
            player2.speed = 3;
            player.def = 30;
            player2.def = 30;
            player.str = 50;
            player2.str = 50;
            player.mag = 30;
            player2.mag = 30;
            Player.player1Time = 180;
            Player.player2Time = 165;
            Player.potion = 2;
            Player.ether = 2;
            Player.redbull = 1;
            Player.whiskey = 8;
            Player.spinach = 3;
            Player.moonstone = 2;
            Player.armour = 3;
            mainc.setBounds(30, 240, 122, 85);
            mainc2.setBounds(120, 220, 122, 85);
            enemyc.remove(0);
            enemyc.add(new JLabel(enemypic));
            monster.monsterHPTot = (String.valueOf(monster.hp));
            combatMsgs.setText(String.format(""));
            // Player.active.remove(0);
            // Player.active.remove(1);
            restartButton.setVisible(false);
        }
      });


//start screen

     startScreen.setSize(800, 500);
     button.setBounds(350, 350, 100, 25);
     ImageIcon startPic = new ImageIcon("game/src/start.png");
     startScreen.add(new JLabel(startPic));
   
//UI controls
     
     JPanel controlPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
     controlPanel1.setBounds(0, 350, 80, 130);
     controlPanel1.setBackground(Color.blue);
     controlPanel1.add(nameTitle);
     controlPanel1.add(playerName);
     controlPanel1.add(playerName2);

     JPanel controlPanelTime = new JPanel(new FlowLayout(FlowLayout.LEFT));
     controlPanelTime.setBounds(80, 350, 120, 130);
     controlPanelTime.setBackground(Color.blue);
     controlPanelTime.add(timeTitle);
     controlPanelTime.add(progressBar);
     controlPanelTime.add(progressBar2);
         
        JPanel controlPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel3.setBounds(200, 350, 100, 130);
        controlPanel3.setBackground(Color.blue);
        controlPanel3.add(hpTitle);
        controlPanel3.add(playerHPL);
        controlPanel3.add(player2HPL);

        JPanel  controlPanelMP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanelMP.setBounds(300, 350, 100, 130);
        controlPanelMP.setBackground(Color.blue);
        controlPanelMP.add(mpTitle);
        controlPanelMP.add(playerMPL);
        controlPanelMP.add(player2MPL);

        JPanel  controlPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel4.setBounds(500, 350, 80, 130);
        controlPanel4.setBackground(Color.blue);
        controlPanel4.add(monsterTitle);
        controlPanel4.add(monsterName);

        JPanel controlPanelMTime = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanelMTime.setBounds(580, 350, 120, 130);
        controlPanelMTime.setBackground(Color.blue);
        controlPanelMTime.add(mtimeTitle);
        controlPanelMTime.add(progressBarE);

        JPanel controlPanelMHP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanelMHP.setBounds(700, 350, 120, 130);
        controlPanelMHP.setBackground(Color.blue);
        controlPanelMHP.add(mhpTitle);
        controlPanelMHP.add(enemyHPL);
        controlPanelMHP.add(arrayinfo);
        controlPanelMHP.add(restartButton);

        JPanel  messagePanel = new JPanel();
        messagePanel.setBounds(200, 5, 400, 35);
        messagePanel.setBackground(Color.blue);
        messagePanel.add(combatMsgs);
      
        JPanel backg = new JPanel();
        backg.setBounds(0, 0, 800, 350);
        ImageIcon backgpic = new ImageIcon("game/src/bg.jpg");
        backg.add(new JLabel(backgpic));

        gui.setResizable(false);
        gui.getContentPane().add(button);
        gui.getContentPane().add(startScreen);
        gui.getContentPane().add(controlPanel1);
        gui.getContentPane().add(controlPanelTime);
        gui.getContentPane().add(controlPanel2);
        gui.getContentPane().add(controlPanel2MAGIC);
        gui.getContentPane().add(controlPanel2ITEMS);
        gui.getContentPane().add(controlPanel3);
        gui.getContentPane().add(controlPanelMP);
        gui.getContentPane().add(controlPanel4);
        gui.getContentPane().add(controlPanelMTime);
        gui.getContentPane().add(controlPanelMHP);
        gui.getContentPane().add(messagePanel);
        gui.getContentPane().add(mainc);
        gui.getContentPane().add(mainc2);
        gui.getContentPane().add(fire);
        gui.getContentPane().add(lightning);
        gui.getContentPane().add(enemyc);
        gui.getContentPane().add(backg);
        gui.setLocationRelativeTo(null);
        return gui;
    }

  
}
