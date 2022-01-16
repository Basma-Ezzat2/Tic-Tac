/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.LocalDB.readLocalFile;
import static gui.twoPlayersMode.dataLocl;
import static gui.twoPlayersMode.localFile;
import static gui.twoPlayersMode.pGames;
import static gui.twoPlayersMode.pLose;
import static gui.twoPlayersMode.pWins;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class singleMode extends javax.swing.JFrame {
   public static File localFile;
    public static  String dataLocl;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    
    String flag = "X"; //string to swap between x , o
    int xCounter = 0;  // int value to count the number of winning game to player x
    int oCounter =0;   //int value to count the number of winning game to player o
    int draw =0;
    static int drawCount=0;
    boolean cx = false;
    boolean co = false;
    String playerX00;
    String playerY00;
    String winner ="";
    boolean record = false;
    Boolean success=false;
    boolean end=false;
    String playerName;
    String playerEmail;
    String playingMode="";
    int computerco=0;
    int playerGames ;
    int playerWins;
    int playerLoses;
    String secPlayer;
    LinkedHashMap<Integer, String> moves = new LinkedHashMap<>();
    JButton [] arr = new JButton[9];
    public singleMode() {
        initComponents();
        playerX00=firstPlayer.getText();
        playerY00=secondPlayer.getText();
        setResizable(true);
        //setSize(1200, 800);
        arr[0] = btn1;
        arr[1] = btn2;
        arr[2] = btn3;
        arr[3] = btn4;
        arr[4] = btn5;
        arr[5] = btn6;
        arr[6] = btn7;
        arr[7] = btn8;
        arr[8] = btn9;
        
        
        
        for(int x = 0; x <9; x++ )
        {
            arr[x].setText("");
        }
              try {
            
            localFile = new File("local.txt");
            if(localFile.createNewFile())
            {
                System.out.println("file created "+ localFile.getName()+ localFile.getPath());
            }
            else
            {
                System.out.println("the file is already existed");
            }
            
            System.out.println("length: "+ localFile.length());
            } catch (IOException ex) {
            Logger.getLogger(LocalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
              secondPlayer.setText("Computer");
    }
    // method to reset the score of two players
    public void gameScore()
    {
        playerX.setText(""+xCounter);
        playerO.setText(""+oCounter);
        
    }// end method gameScore 
    public void recordGame()
    {
           
            System.out.println("inside");
            LocalDB.fillMap(moves, arr);
           //recbtn.setText("record game");
           //recbtn.setBackground(Color.LIGHT_GRAY);
            record = false;
            dataLocl = LocalDB.readLocalFile(localFile);
            System.out.println(dataLocl+"the line inside recordGame");
        
        
    }
    public boolean isDraw()
    {
        if(drawCount<8)
        {
            drawCount++;
            System.out.print(""+drawCount);
            return false;
        }else
        {
            System.out.println(""+cx+""+co);
            if(cx&&co)
            {
                closeButtons();
                JOptionPane.showMessageDialog(this, "The game is draw try again ");
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
                return true;
            }
        }
        return false;
        
    }
    public  boolean isDrawComputer()
    {
        if(drawCount<4)
        {
            drawCount++;
            System.out.print(""+drawCount);
            return false;
        } if(cx&&co)
            {
                closeButtons();
                JOptionPane.showMessageDialog(this, "The game is draw try again ");
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
                return true;
            }
        return false;
        
    }
  
    
    
     public void choose_player()
    {
        
        playerX00 = firstPlayer.getText();
        playerY00 = secondPlayer.getText();
        System.out.println("\n first "+playerX00+"  "+playerY00);
        if(flag.equalsIgnoreCase("X"))
        {
            flag = "O";
           // jLabel46.setText(" O turn");
        }else
        {
            flag = "X";
            //jLabel46.setText(" X turn");
        }
            
        
    }// end choose_player
      public boolean xWin()
    {
        for(int x =0, r1=0, r2=1 ,r3=2; x <3; x++, r1=r1+3, r2=r2+3, r3=r3+3)
        {
            String b1 = arr[r1].getText();
            String b2 = arr[r2].getText();
            String b3 = arr[r3].getText();
            if(b1.equalsIgnoreCase("X") && b2.equalsIgnoreCase("X") && b3.equalsIgnoreCase("X"))
            {
                arr[r1].setBackground(Color.green);
                arr[r2].setBackground(Color.green);
                arr[r3].setBackground(Color.green);
                JOptionPane.showMessageDialog(this, "You Win the Game ");
                xCounter++;
                winner =firstPlayer.getText();
                gameScore();
                closeButtons();
                cx = false;
                Winner wins=new Winner();
               wins.setVisible(true);
                wins.setDefaultCloseOperation(2);
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
                return true;
                
            }// end if
            
        }//end for loop to check win in rows
        for(int x =0, c1=0, c2=3 ,c3=6; x <3; x++, c1++, c2++, c3++)
        {
            String b1 = arr[c1].getText();
            String b2 = arr[c2].getText();
            String b3 = arr[c3].getText();
            if(b1.equalsIgnoreCase("X") && b2.equalsIgnoreCase("X") && b3.equalsIgnoreCase("X"))
            {
                arr[c1].setBackground(Color.green);
                arr[c2].setBackground(Color.green);
                arr[c3].setBackground(Color.green);
                JOptionPane.showMessageDialog(this, "You Win the Game ");
                xCounter++;
                gameScore();
                closeButtons();
                cx = false;
                winner =firstPlayer.getText();
                Winner wins=new Winner();
               wins.setVisible(true);
                wins.setDefaultCloseOperation(2);
                
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
                return true;
            }// end if
            
        }//end for loop to check win in columes
        for(int x =0, d1=0, d2=4 , d3=8  ; x <2; x++ , d1=d1+2, d3=d3-2)
        {
            String b1 = arr[d1].getText();
            String b2 = arr[d2].getText();
            String b3 = arr[d3].getText();
            if(b1.equalsIgnoreCase("X") && b2.equalsIgnoreCase("X") && b3.equalsIgnoreCase("X"))
            {
                arr[d1].setBackground(Color.green);
                arr[d2].setBackground(Color.green);
                arr[d3].setBackground(Color.green);
                JOptionPane.showMessageDialog(this, "You Win the Game ");
                xCounter++;
                gameScore();
                closeButtons();
                winner =firstPlayer.getText();
                cx = false;
                Winner wins=new Winner();
               wins.setVisible(true);
                wins.setDefaultCloseOperation(2);
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
              return true;
            }// end if
            
        }//end for loop to check win in diagonal
        cx = true;
        return false;
    }// end xwin method
    
    public boolean  oWin()
    {
        for (int x = 0, r1 = 0, r2 = 1, r3 = 2; x < 3; x++, r1 = r1 + 3, r2 = r2 + 3, r3 = r3 + 3) {
            String b1 = arr[r1].getText();
            String b2 = arr[r2].getText();
            String b3 = arr[r3].getText();
            if (b1.equalsIgnoreCase("O") && b2.equalsIgnoreCase("O") && b3.equalsIgnoreCase("O")) {
                arr[r1].setBackground(Color.green);
                arr[r2].setBackground(Color.green);
                arr[r3].setBackground(Color.green);
                JOptionPane.showMessageDialog(this, "You Lost the Game ");
                oCounter++;
                gameScore();
                closeButtons();
                co = false;
                winner =secondPlayer.getText();
                Loser lose=new Loser();
                lose.setVisible(true);
               lose.setDefaultCloseOperation(2);
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
                return true;
                 
            }// end if
            
        }//end for loop to check win in rows
        for (int x = 0, c1 = 0, c2 = 3, c3 = 6; x < 3; x++, c1++, c2++, c3++) {
            String b1 = arr[c1].getText();
            String b2 = arr[c2].getText();
            String b3 = arr[c3].getText();
            if (b1.equalsIgnoreCase("O") && b2.equalsIgnoreCase("O") && b3.equalsIgnoreCase("O")) {
                arr[c1].setBackground(Color.green);
                arr[c2].setBackground(Color.green);
                arr[c3].setBackground(Color.green);
                JOptionPane.showMessageDialog(this, "You Lost the Game ");
                oCounter++;
                gameScore();
                closeButtons();
                winner =secondPlayer.getText();
                co = false;
                Loser lose=new Loser();
                lose.setVisible(true);
               lose.setDefaultCloseOperation(2);
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
                return true;
            }// end if
            
        }//end for loop to check win in columes
        for (int x = 0, d1 = 0, d2 = 4, d3 = 8; x < 2; x++, d1 = d1 + 2, d3 = d3 - 2) {
            String b1 = arr[d1].getText();
            String b2 = arr[d2].getText();
            String b3 = arr[d3].getText();
            if (b1.equalsIgnoreCase("O") && b2.equalsIgnoreCase("O") && b3.equalsIgnoreCase("O")) {
                arr[d1].setBackground(Color.green);
                arr[d2].setBackground(Color.green);
                arr[d3].setBackground(Color.green);
                JOptionPane.showMessageDialog(this, "You Lost the Game ");
                oCounter++;
                gameScore();
                closeButtons();
                winner =secondPlayer.getText();
                co = false;
                Loser lose=new Loser();
                lose.setVisible(true);
               lose.setDefaultCloseOperation(2);
                if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerX00, xCounter, playerY00, oCounter, moves, winner);
                    
                }
                return true;
                
            }// end if
           System.out.println();
        }//end for loop to check win in diagonal
        co = true;
     return false;
    }// end xwin method
    
    public void closeButtons()
    {
        for(int x = 0; x <9; x++ )
        {
            arr[x].setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        twoplayer_mode = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        firstPlayer = new javax.swing.JLabel();
        secondPlayer = new javax.swing.JLabel();
        playerX = new javax.swing.JLabel();
        playerO = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        histbtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btn9 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        twoplayer_mode.setBackground(new java.awt.Color(48, 57, 82));
        twoplayer_mode.setPreferredSize(new java.awt.Dimension(762, 496));

        jPanel2.setBackground(new java.awt.Color(230, 76, 60));

        firstPlayer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        firstPlayer.setForeground(new java.awt.Color(48, 57, 82));
        firstPlayer.setText("You");

        secondPlayer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        secondPlayer.setForeground(new java.awt.Color(48, 57, 82));
        secondPlayer.setText("Computer");

        playerX.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        playerX.setOpaque(true);

        playerO.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        playerO.setOpaque(true);

        btnExit.setBackground(new java.awt.Color(48, 57, 82));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(48, 57, 82));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        histbtn.setBackground(new java.awt.Color(48, 57, 82));
        histbtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        histbtn.setForeground(new java.awt.Color(255, 255, 255));
        histbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-activity-history-24.png"))); // NOI18N
        histbtn.setText("History");
        histbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(histbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playerO, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondPlayer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playerX, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(firstPlayer)
                        .addGap(18, 18, 18)
                        .addComponent(playerX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(secondPlayer)
                        .addGap(18, 18, 18)
                        .addComponent(playerO, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(histbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(48, 57, 82));

        btn9.setBackground(new java.awt.Color(255, 255, 255));
        btn9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn9.setForeground(new java.awt.Color(255, 102, 102));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(255, 255, 255));
        btn1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn1.setForeground(new java.awt.Color(255, 102, 102));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(255, 255, 255));
        btn5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn5.setForeground(new java.awt.Color(255, 102, 102));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(255, 255, 255));
        btn2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn2.setForeground(new java.awt.Color(255, 102, 102));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn6.setBackground(new java.awt.Color(255, 255, 255));
        btn6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn6.setForeground(new java.awt.Color(255, 102, 102));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(255, 255, 255));
        btn3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn3.setForeground(new java.awt.Color(255, 102, 102));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn8.setBackground(new java.awt.Color(255, 255, 255));
        btn8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn8.setForeground(new java.awt.Color(255, 102, 102));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn7.setBackground(new java.awt.Color(255, 255, 255));
        btn7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn7.setForeground(new java.awt.Color(255, 102, 102));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(255, 255, 255));
        btn4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn4.setForeground(new java.awt.Color(255, 102, 102));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(230, 76, 60));
        jLabel21.setText("XO Game");

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-back-arrow-64.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 58, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout twoplayer_modeLayout = new javax.swing.GroupLayout(twoplayer_mode);
        twoplayer_mode.setLayout(twoplayer_modeLayout);
        twoplayer_modeLayout.setHorizontalGroup(
            twoplayer_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, twoplayer_modeLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        twoplayer_modeLayout.setVerticalGroup(
            twoplayer_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(twoplayer_mode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(twoplayer_mode, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if(JOptionPane.showConfirmDialog(this,"confirm if you want  Exit","Tic Tak Toe" , JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
       flag = "X";    
        if(cx && co)
        { 
            
            draw++;
            cx = false;
            co = false;       
        }
         drawCount =0;
        for(int x = 0; x <9; x++ )
        {
            arr[x].setText("");
            arr[x].setEnabled(true);
            arr[x].setBackground(Color.LIGHT_GRAY);
        }
        moves.clear();
        playerX.setText("");
        playerO.setText("");
      
       
    
    }//GEN-LAST:event_btnResetActionPerformed

    private void histbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histbtnActionPerformed
       GameRecorded s = new GameRecorded();
        dataLocl = LocalDB.readLocalFile(localFile);
        System.out.println("\n inside the history btn"+dataLocl+"\n");
        s.method(localFile, dataLocl);
        s.setVisible(true);
        s.setDefaultCloseOperation(2);
        
    }//GEN-LAST:event_histbtnActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        String s = btn9.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn9.setText(flag);
            moves.put(9, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
        
       
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        String s = btn1.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn1.setText(flag);
            moves.put(1, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        String s = btn5.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn5.setText(flag);
            moves.put(5, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        String s = btn2.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn2.setText(flag);
            moves.put(2, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }

    }//GEN-LAST:event_btn2ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        String s = btn6.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn6.setText(flag);
            moves.put(6, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        String s = btn3.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn3.setText(flag);
            moves.put(3, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
       String s = btn8.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn8.setText(flag);
            moves.put(8, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        String s = btn7.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn7.setText(flag);
            moves.put(7, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        String s = btn4.getText();
            
        if(s.equalsIgnoreCase(""))
        {
            
            btn4.setText(flag);
            moves.put(4, flag);
             end  =xWin();
            if((computerco<4)&&!end)
            {
                computerco++;
                ComputerMode.generateRand(arr, flag, moves);
            }
            
            
            end =oWin();
            isDrawComputer();
           
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
       pWins=Profile.win;
       System.out.println(Profile.win);
       pWins+=xCounter;
       pLose=Profile.lose;
       pLose+=oCounter;
      pGames=pWins+pLose;
        Modes m =new Modes();
        m.setVisible(true);
        m.setDefaultCloseOperation(2);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(singleMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(singleMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(singleMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(singleMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new singleMode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel firstPlayer;
    private javax.swing.JButton histbtn;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel playerO;
    private javax.swing.JLabel playerX;
    private javax.swing.JLabel secondPlayer;
    private javax.swing.JPanel twoplayer_mode;
    // End of variables declaration//GEN-END:variables
}
