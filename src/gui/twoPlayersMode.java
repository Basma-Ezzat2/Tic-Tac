/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.LocalDB.readLocalFile;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class twoPlayersMode extends javax.swing.JFrame {
    public static File localFile;
    public static  String dataLocl;
    private String startGame="X";
    private int xCount = 0;
    private int oCount = 0;
    public static int pWins,pLose,pGames;
    int draw =0;
    int drawCount=0;
    boolean cx = false;
    boolean co = false;
    boolean record = false;
    boolean checker;
    String playerx;
    String playery;
    String winner ="";
    JButton [] arr = new JButton[9];
    LinkedHashMap<Integer, String> moves = new LinkedHashMap<>();
    Profile pr;
    
    
    public twoPlayersMode() {
        initComponents();
        arr[0] = btn1;
        arr[1] = btn2;
        arr[2] = btn3;
        arr[3] = btn4;
        arr[4] = btn5;
        arr[5] = btn6;
        arr[6] = btn7;
        arr[7] = btn8;
        arr[8] = btn9;
        playerx = player1.getText();
        playery = player2.getText();
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
            dataLocl = LocalDB.readLocalFile(localFile);
            System.out.println("length: "+ localFile.length());
            } catch (IOException ex) {
            Logger.getLogger(LocalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void gameScore()
     {
         playerX.setText(String.valueOf(xCount));
         playerO.setText(String.valueOf(oCount));
         System.out.println("\n first "+playerx+"  "+playery);
     }
       private void choose_a_Player()
     {
          if(startGame.equalsIgnoreCase("X"))
          {
              startGame = "O";
              
          }
          else
          { 
              startGame = "X";
              
          }    
     }
           public void recordGame()
    {
     
            System.out.println("inside");
            LocalDB.fillMap(moves, arr);
            recbtn.setText("record game");
            recbtn.setBackground(Color.LIGHT_GRAY);
            record = false;
            dataLocl = readLocalFile(localFile);
            System.out.println(dataLocl+"the line inside recordGame");
        
        
    }
           public void isDraw()
    {
        if(drawCount<8)
        {
            drawCount++;
            System.out.print(""+drawCount);
        }else
        {
            System.out.println(""+cx+""+co);
            if(cx&&co)
            {
                closeButtons();
                JOptionPane.showMessageDialog(this, "the game is draw try again ");
               
            }           
        }       
    }
        public boolean  xWins()
     {
         for(int x =0, r1=0, r2=1 ,r3=2; x <3; x++, r1=r1+3, r2=r2+3, r3=r3+3)
        {
            String b1 = arr[r1].getText();
            String b2 = arr[r2].getText();
            String b3 = arr[r3].getText();
            if(b1.equalsIgnoreCase("X") && b2.equalsIgnoreCase("X") && b3.equalsIgnoreCase("X"))
            {
                arr[r1].setBackground(Color.red);
                arr[r2].setBackground(Color.red);
                arr[r3].setBackground(Color.red);
            
            JOptionPane.showMessageDialog (this,"Player X wins","Tic tac toe",JOptionPane.OK_OPTION);
              xCount++;
              winner =player1.getText();
              gameScore();
              closeButtons();
              Winner wins=new Winner();
               wins.setVisible(true);
               wins.setDefaultCloseOperation(2);
                cx = false;
              if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerx, xCount, playery, oCount, moves, winner);
                    
                }
             
              return true;
            }
         }
         
         for(int x =0, c1=0, c2=3 ,c3=6; x <3; x++, c1++, c2++, c3++)
        {
            String b1 = arr[c1].getText();
            String b2 = arr[c2].getText();
            String b3 = arr[c3].getText();
            if(b1.equalsIgnoreCase("X") && b2.equalsIgnoreCase("X") && b3.equalsIgnoreCase("X"))
            {
                arr[c1].setBackground(Color.red);
                arr[c2].setBackground(Color.red);
                arr[c3].setBackground(Color.red);
            
            JOptionPane.showMessageDialog(this,"Player X wins","Tic tac toe",JOptionPane.YES_OPTION);
              
              xCount++;
              winner =player1.getText();
              gameScore();
              closeButtons();
                cx = false;
                 Winner wins=new Winner();
               wins.setVisible(true);
               wins.setDefaultCloseOperation(2);
              if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerx, xCount, playery, oCount, moves, winner);
                    
                }
              return true;
            }
         }
           
          for(int x =0, d1=0, d2=4 , d3=8  ; x <2; x++ , d1=d1+2, d3=d3-2)
        {
            String b1 = arr[d1].getText();
            String b2 = arr[d2].getText();
            String b3 = arr[d3].getText();
            if(b1.equalsIgnoreCase("X") && b2.equalsIgnoreCase("X") && b3.equalsIgnoreCase("X"))
            {
                arr[d1].setBackground(Color.red);
                arr[d2].setBackground(Color.red);
                arr[d3].setBackground(Color.red);
           
            JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.OK_OPTION);
              
              xCount++;
              winner =player1.getText();
              gameScore();
              closeButtons();
                cx = false;
               Winner wins=new Winner();
               wins.setVisible(true);
               wins.setDefaultCloseOperation(2);
              if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerx, xCount, playery, oCount, moves, winner);
                    
                }
              
              return true;
            }
         }
          cx = true;
        return false;
     }   
         
           
        public boolean  oWin()
    {
        for (int x = 0, r1 = 0, r2 = 1, r3 = 2; x < 3; x++, r1 = r1 + 3, r2 = r2 + 3, r3 = r3 + 3) {
            String b1 = arr[r1].getText();
            String b2 = arr[r2].getText();
            String b3 = arr[r3].getText();
            if (b1.equalsIgnoreCase("O") && b2.equalsIgnoreCase("O") && b3.equalsIgnoreCase("O")) {
                arr[r1].setBackground(Color.red);
                arr[r2].setBackground(Color.red);
                arr[r3].setBackground(Color.red);
            
            JOptionPane.showMessageDialog(this,"Player O wins","Tic tac toe",JOptionPane.OK_OPTION);
              oCount++;
              gameScore();
              closeButtons();
                co = false;
                winner = player2.getText();
                Loser lose=new Loser();
                lose.setVisible(true);
               lose.setDefaultCloseOperation(2);
              if(record)
              {
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerx, xCount, playery, oCount, moves, winner);  
                } 
              
              return true;
            }
        }
         
          for (int x = 0, c1 = 0, c2 = 3, c3 = 6; x < 3; x++, c1++, c2++, c3++) 
          {
            String b1 = arr[c1].getText();
            String b2 = arr[c2].getText();
            String b3 = arr[c3].getText();
            if (b1.equalsIgnoreCase("O") && b2.equalsIgnoreCase("O") && b3.equalsIgnoreCase("O"))
            {
                arr[c1].setBackground(Color.red);
                arr[c2].setBackground(Color.red);
                arr[c3].setBackground(Color.red);
           
            JOptionPane.showMessageDialog(this,"Player O wins","Tic tac toe",JOptionPane.OK_OPTION);
              
              oCount++;
              gameScore();
              closeButtons();
                co = false;
                winner = player2.getText();
               Loser lose=new Loser();
                lose.setVisible(true);
               lose.setDefaultCloseOperation(2);
              
            if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerx, xCount, playery, oCount, moves, winner);
                    
                }
            return true;
            }
          }
           
          for (int x = 0, d1 = 0, d2 = 4, d3 = 8; x < 2; x++, d1 = d1 + 2, d3 = d3 - 2)
          {
             
          
            String b1 = arr[d1].getText();
            String b2 = arr[d2].getText();
            String b3 = arr[d3].getText();
            if (b1.equalsIgnoreCase("O") && b2.equalsIgnoreCase("O") && b3.equalsIgnoreCase("O")) 
            {
                arr[d1].setBackground(Color.red);
                arr[d2].setBackground(Color.red);
                arr[d3].setBackground(Color.red);
            
            JOptionPane.showMessageDialog(this,"Player O wins","Tic tac toe",JOptionPane.OK_OPTION);
              
              oCount++;
              gameScore();
              closeButtons();
                co = false;
                winner = player2.getText();
                Loser lose=new Loser();
                lose.setVisible(true);
               lose.setDefaultCloseOperation(2);
              
            if(record)
                {
                    
                    recordGame();
                    LocalDB.writeLocalGameSteps(localFile, dataLocl ,playerx, xCount, playery, oCount, moves, winner);
                    
                }
            
            return true;
           }
      
        }
          co = true;
     return false;
    }
     
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
        player1 = new javax.swing.JLabel();
        player2 = new javax.swing.JLabel();
        playerX = new javax.swing.JLabel();
        playerO = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        histbtn = new javax.swing.JButton();
        recbtn = new javax.swing.JButton();
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
        btnback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        twoplayer_mode.setBackground(new java.awt.Color(48, 57, 82));
        twoplayer_mode.setPreferredSize(new java.awt.Dimension(762, 496));

        jPanel2.setBackground(new java.awt.Color(230, 76, 60));

        player1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        player1.setForeground(new java.awt.Color(48, 57, 82));
        player1.setText("Player  X");

        player2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        player2.setForeground(new java.awt.Color(48, 57, 82));
        player2.setText("Player O");

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

        recbtn.setBackground(new java.awt.Color(0, 0, 0));
        recbtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        recbtn.setForeground(new java.awt.Color(255, 255, 255));
        recbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-record-button-for-computer-application-program-layout-24.png"))); // NOI18N
        recbtn.setText("Rec..");
        recbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 276, Short.MAX_VALUE)
                        .addComponent(recbtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playerO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(player1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerX, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(histbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recbtn)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(player1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(playerX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(player2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(playerO, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(histbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnback.setBackground(new java.awt.Color(255, 255, 255));
        btnback.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnback.setForeground(new java.awt.Color(255, 255, 255));
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-back-arrow-64.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
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
            .addComponent(twoplayer_mode, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(twoplayer_mode, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(JOptionPane.showConfirmDialog(this,"confirm if you want  Exit","Tic Tak Toe" , JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }        
    }//GEN-LAST:event_btnExitActionPerformed

    private void histbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histbtnActionPerformed
        GameRecorded s = new GameRecorded();
        dataLocl = LocalDB.readLocalFile(localFile);
        System.out.println("\n inside the history btn"+dataLocl+"\n");
        s.method(localFile, dataLocl);
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        s.setDefaultCloseOperation(2);
    }//GEN-LAST:event_histbtnActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        
        String s = btn9.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn9.setText(startGame);
            moves.put(9, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        String s = btn1.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn1.setText(startGame);
            moves.put(1, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        String s = btn5.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn5.setText(startGame);
            moves.put(5, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        String s = btn2.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn2.setText(startGame);
            moves.put(2, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        String s = btn6.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn6.setText(startGame);
            moves.put(6, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        String s = btn3.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn3.setText(startGame);
            moves.put(3, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
       String s = btn8.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn8.setText(startGame);
            moves.put(8, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        String s = btn7.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn7.setText(startGame);
            moves.put(7, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        String s = btn4.getText();
        if(s.equalsIgnoreCase(""))
        {
            btn4.setText(startGame);
            moves.put(4, startGame);
            choose_a_Player();
            xWins();
            oWin();
            isDraw();
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        pWins=Profile.win;
       System.out.println(Profile.win);
       pWins+=xCount;
      // pr.winGames.setText(""+pWins);
       pLose=Profile.lose;
       pLose+=oCount;
       //Profile.loseGames.setText(""+pLose);
       pGames=pWins+pLose;
       
       
//Profile.playerGames.setText(""+pGames);  
       //String name=Profile.profileName.getText();
        Modes m =new Modes();
        m.setVisible(true);
        m.setDefaultCloseOperation(2);
        //m.setVisible(false);
    }//GEN-LAST:event_btnbackActionPerformed

    private void recbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recbtnActionPerformed
       record = true;
       recbtn.setText("recording");
       recbtn.setBackground(Color.GREEN);
    }//GEN-LAST:event_recbtnActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        startGame = "X";    
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
            java.util.logging.Logger.getLogger(twoPlayersMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(twoPlayersMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(twoPlayersMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(twoPlayersMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new twoPlayersMode().setVisible(true);
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
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnback;
    private javax.swing.JButton histbtn;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel player1;
    private javax.swing.JLabel player2;
    public static javax.swing.JLabel playerO;
    public static javax.swing.JLabel playerX;
    private javax.swing.JButton recbtn;
    private javax.swing.JPanel twoplayer_mode;
    // End of variables declaration//GEN-END:variables
}
