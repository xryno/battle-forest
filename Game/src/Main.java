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
import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Main {
  
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
            // Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (Exception e) {

            System.out.println(e);
        }
        
    }

    public static JFrame createGUI () {
        
        JButton attackButton = new JButton("Attack");
        JButton fireButton = new JButton("Fire");
        JButton lightningButton = new JButton("Lightning");
        JButton healButton = new JButton("Heal");
        JButton magicButton = new JButton("Magic");
        JButton backButton = new JButton("Back");
        JButton backButtonI = new JButton("Back");
        JButton useButton = new JButton("Use");
        JButton itemButton = new JButton("Items");
        JLabel combatMsgs = new JLabel();

        final Timer  timer;
        final Timer timerEnemey;
        final JProgressBar progressBar = new JProgressBar();
        final JProgressBar progressBarE = new JProgressBar();
        final JButton button = new JButton("Start");
        JLabel playerHPL = new JLabel();
        JLabel playerMPL = new JLabel();
        JLabel enemyHPL = new JLabel();

        //enemy char
        Monster monster = new Monster("Dragon", 2000);
        JPanel enemyc = new JPanel();
        enemyc.setBounds(500, 80, 300, 268);
        enemyc.setOpaque(false); 
        enemyc.setBackground( new Color(255, 0, 0, 20) );
        ImageIcon enemypic = new ImageIcon("enemy.png");
        enemyc.add(new JLabel(enemypic));

        //main char
        Player player = new Player("Tidus", 2500, 200);
        JLabel playerName = new JLabel(player.name);
        JPanel mainc = new JPanel();
        mainc.setBounds(30, 240, 122, 85);
        mainc.setOpaque(false); 
        mainc.setBackground( new Color(255, 0, 0, 20) );
        ImageIcon maincpic = new ImageIcon("main.png");
        mainc.add(new JLabel(maincpic));

        Dimension butDim = new Dimension(110, 23);
        Dimension butDimI = new Dimension(55, 23);
      
        attackButton.setPreferredSize(butDim);
        magicButton.setPreferredSize(butDim);
        backButton.setPreferredSize(butDim);
        backButtonI.setPreferredSize(butDimI);
        useButton.setPreferredSize(butDimI);
        fireButton.setPreferredSize(butDim);
        lightningButton.setPreferredSize(butDim);
        healButton.setPreferredSize(butDim);
        itemButton.setPreferredSize(butDim);
        playerName.setPreferredSize(new Dimension(60, 23));
        playerName.setForeground(Color.white);
        playerHPL.setForeground(Color.white);
        playerMPL.setForeground(Color.white);
        enemyHPL.setForeground(Color.white);
        playerHPL.setPreferredSize(new Dimension(60, 23));
        playerMPL.setPreferredSize(new Dimension(60, 23));
        enemyHPL.setPreferredSize(new Dimension(120, 23));
        combatMsgs.setForeground(Color.white);
        progressBar.setPreferredSize(new Dimension(190, 23));

      
//player bar
    ActionListener updateProBar = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            //update values each timer pass
            playerHPL.setText(String.valueOf("HP:" + player.hp));
            playerMPL.setText(String.valueOf("MP:" + player.mp));
            enemyHPL.setText(String.format("%s HP:" + monster.hp, monster.name));

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

            //check if player dead

            if (player.hp <= 0) {
                combatMsgs.setText(String.format("You died!"));
                mainc.setBounds(29, 240, 0, 0);
                attackButton.setEnabled(false);
                magicButton.setEnabled(false);
                itemButton.setEnabled(false);
                return;
            }

            if (monster.hp <= 0) {
                return;
            }

           //enable buttons when progress bar full
          int val = progressBar.getValue();
          if (val >= 100) {
            attackButton.setEnabled(true);
            fireButton.setEnabled(true);
            lightningButton.setEnabled(true);
            healButton.setEnabled(true);
            useButton.setEnabled(true);
          
     
          }
          
          progressBar.setValue(val + player.speed);
        }
       }
      };


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
                combatMsgs.setText(String.format("%s defeated %s!", player.name, monster.name));
                enemyc.setBounds(29, 240, 0, 0);
                attackButton.setEnabled(false);
                magicButton.setEnabled(false);
                itemButton.setEnabled(false);
                return;
            }
            

            if (player.hp <= 0) {
                return;
            }
          int val = progressBarE.getValue();

          if (val >= 100) {

            
            PlaySound(monster.attackSound);
            int atkVal = monster.attack();
            enemyc.setBounds(20, 100, 300, 268);
            player.hp -= atkVal;
            combatMsgs.setText(String.format("%s attacked %s for %d HP", monster.name, player.name, atkVal));
            progressBarE.setValue(0);
            return;
          }
          
          progressBarE.setValue(++val);
        }
      };

      timer = new Timer(180, updateProBar);
      timerEnemey = new Timer (180, updateEProBar);

   
    



      //start button
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            File music = new File("bgmusic.wav");
            PlaySound(music);
            
          if (timer.isRunning()) {
            timer.stop();
            timerEnemey.stop();
            button.setText("Start battle");
          } else if (button.getText() != "End") {
            timer.start();
            timerEnemey.start();
            button.setText("Stop");
          }
        }
      });



        attackButton.setEnabled(false);
        fireButton.setEnabled(false);
        lightningButton.setEnabled(false);
        healButton.setEnabled(false);
        useButton.setEnabled(false);

//Player attack
            attackButton.addActionListener(event -> {
            PlaySound(player.attackSound);
            mainc.setBounds(550, 240, 122, 85);
            monster.hp -= player.attack();
            combatMsgs.setText(String.format("%s attacked %s for %d HP!", player.name, monster.name, player.lastAtk));
            progressBar.setValue(0);
            attackButton.setEnabled(false);
            fireButton.setEnabled(false);
            lightningButton.setEnabled(false);
            healButton.setEnabled(false);
            useButton.setEnabled(false);

        });
//player fire magic

          fireButton.addActionListener(event -> {

            if (player.mp >= 50) {
                PlaySound(player.fireSound);
     

            monster.hp -= player.fire();
            combatMsgs.setText(String.format("%s cast fire spell for %d HP!", player.name, player.lastAtk));


            progressBar.setValue(0);
     
            attackButton.setEnabled(false);
            fireButton.setEnabled(false);
            lightningButton.setEnabled(false);
            healButton.setEnabled(false);
            useButton.setEnabled(false);

            } else {
                combatMsgs.setText(String.format("Not enough MP!"));
            }
          
        });
//player lightning magic

        lightningButton.addActionListener(event -> {

            if (player.mp >= 40) {

                PlaySound(player.lightningSound);
                monster.hp -= player.lightning();
                combatMsgs.setText(String.format("%s cast lightning spell for %d HP!", player.name, player.lastAtk));
                progressBar.setValue(0);
                attackButton.setEnabled(false);
                fireButton.setEnabled(false);
                lightningButton.setEnabled(false);
                healButton.setEnabled(false);
                useButton.setEnabled(false);
    
                } else {
                    combatMsgs.setText(String.format("Not enough MP!"));
                }
              
            });
//player heal magic

        healButton.addActionListener(event -> {
            if (player.mp >= 30) {
                combatMsgs.setText(player.heal());
                progressBar.setValue(0);
                attackButton.setEnabled(false);
                fireButton.setEnabled(false);
                lightningButton.setEnabled(false);
                healButton.setEnabled(false);
                useButton.setEnabled(false);
    
             } else {
                    combatMsgs.setText(String.format("Not enough MP!"));
            }
          
        });




        JFrame gui = new JFrame("Battle system");
        gui.setSize(new Dimension(800, 500));
        gui.getContentPane().setBackground(Color.black);
        gui.setLayout(null);
      

        

       

        JPanel controlPanel1;
        controlPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel1.setBounds(0, 350, 200, 150);
        controlPanel1.setBackground(Color.blue);
        controlPanel1.add(progressBar);
        controlPanel1.add(attackButton);

        JPanel controlPanel2;
        controlPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel2.setBounds(200, 350, 150, 150);
        controlPanel2.setBackground(Color.blue);
        controlPanel2.add(attackButton);
        controlPanel2.add(magicButton);
        controlPanel2.add(itemButton);
   
        JPanel controlPanel2MAGIC;
        
        controlPanel2MAGIC = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel2MAGIC.setBounds(200, 350, 150, 150);
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
          list.setPreferredSize(new Dimension(110, 70));



          JPanel controlPanel2ITEMS;
          
          controlPanel2ITEMS = new JPanel(new FlowLayout(FlowLayout.LEFT));
          controlPanel2ITEMS.setBounds(200, 350, 150, 150);
          controlPanel2ITEMS.setBackground(Color.blue);
          controlPanel2ITEMS.add(list);
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
          
          itemButton.addActionListener(event -> {
            l1.removeAllElements();
              l1.addElement("Potion x " + player.potion);  
              l1.addElement("Ether x " + player.ether);  
              l1.addElement("Sugar x " + player.sugar);  
              l1.addElement("Whiskey x " + player.whiskey); 
            controlPanel2ITEMS.setVisible(true);
            controlPanel2MAGIC.setVisible(false);
            controlPanel2.setVisible(false);
            
          });


        useButton.addActionListener(event -> {
            String usedItem = list.getSelectedValue().substring(0, list.getSelectedValue().length() - 4);
            combatMsgs.setText(player.useItem(usedItem));
            controlPanel2ITEMS.setVisible(false);
            controlPanel2.setVisible(true);
            progressBar.setValue(0);
            useButton.setEnabled(false);
     });
    
         

        JPanel controlPanel3;
        controlPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel3.setBounds(350, 350, 250, 150);
        controlPanel3.setBackground(Color.blue);
        controlPanel3.add(playerName);
        controlPanel3.add(playerHPL);
        controlPanel3.add(playerMPL);

        JPanel controlPanel4;
        controlPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel4.setBounds(600, 350, 200, 150);
        controlPanel4.setBackground(Color.blue);
        controlPanel4.add(progressBarE);
        controlPanel4.add(enemyHPL);
        controlPanel4.add(button);
        


        JPanel messagePanel;
        messagePanel = new JPanel();
        messagePanel.setBounds(200, 5, 400, 35);
        messagePanel.setBackground(Color.blue);
        messagePanel.add(combatMsgs);

        JPanel backg = new JPanel();
        backg.setBounds(0, 0, 800, 350);
        ImageIcon backgpic = new ImageIcon("bg.jpg");
        backg.add(new JLabel(backgpic));

        gui.getContentPane().add(controlPanel1);
        gui.getContentPane().add(controlPanel2);
        gui.getContentPane().add(controlPanel2MAGIC);
        gui.getContentPane().add(controlPanel2ITEMS);
        gui.getContentPane().add(controlPanel3);
        gui.getContentPane().add(controlPanel4);
        gui.getContentPane().add(messagePanel);
        gui.getContentPane().add(mainc);
        gui.getContentPane().add(enemyc);
        gui.getContentPane().add(backg);
        gui.setLocationRelativeTo(null);
      
 
        return gui;
    }

  
}
