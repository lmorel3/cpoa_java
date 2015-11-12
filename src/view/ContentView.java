/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author laurent
 */
public class ContentView extends JPanel{
    
    public static final int NUM_COURTA = 0;
    public static final int NUM_COURTB = 1;
    public static final int NUM_TRAIN1 = 2;
    public static final int NUM_TRAIN2 = 3;
    public static final int NUM_TRAIN3 = 4;
    public static final int NUM_TRAIN4 = 5;
    
    public ContentView(){
        
        initComponents();
        generateLayout();

        System.out.println("hello");
        
    }
    
    private void initComponents(){
           
        img_courtA = new javax.swing.JLabel();
        img_courtB = new javax.swing.JLabel();
        name_courtA = new javax.swing.JLabel();
        name_courtB = new javax.swing.JLabel();
        img_train1 = new javax.swing.JLabel();
        name_train1 = new javax.swing.JLabel();
        img_train2 = new javax.swing.JLabel();
        name_train2 = new javax.swing.JLabel();
        name_train3 = new javax.swing.JLabel();
        img_train3 = new javax.swing.JLabel();
        vs_courtA = new javax.swing.JLabel();
        vs_courtB = new javax.swing.JLabel();
        vs_train1 = new javax.swing.JLabel();
        vs_train2 = new javax.swing.JLabel();
        vs_train3 = new javax.swing.JLabel();
        img_train4 = new javax.swing.JLabel();
        vs_train4 = new javax.swing.JLabel();
        name_train4 = new javax.swing.JLabel();
        
        java.awt.Font font_img = new java.awt.Font("Arial", 1, 48);
        java.awt.Font font_court = new java.awt.Font("Arial", 0, 18);
        java.awt.Font font_train = new java.awt.Font("Arial", 0, 14);
        
        color_green = new java.awt.Color(91, 135, 125);
        color_red = new java.awt.Color(149, 44, 40);
        color_orange = new java.awt.Color(206, 117, 103);
        
        image_courtLarge = new javax.swing.ImageIcon(getClass().getResource("/images/court_large.jpg"));
        image_courtSmall = new javax.swing.ImageIcon(getClass().getResource("/images/court_small.jpg"));
        
        img_courtA.setFont(font_img);
        img_courtA.setForeground(color_green);
        img_courtA.setIcon(image_courtLarge); // NOI18N
        img_courtA.setText("■");
        img_courtA.setToolTipText("");

        img_courtB.setFont(font_img);
        img_courtB.setForeground(color_red);
        img_courtB.setIcon(image_courtLarge); // NOI18N
        img_courtB.setText("■");
        img_courtB.setToolTipText("");

        name_courtA.setFont(font_court); // NOI18N
        name_courtA.setText("Court central");

        name_courtB.setFont(font_court); // NOI18N
        name_courtB.setText("Court secondaire");

        img_train1.setFont(font_img); // NOI18N
        img_train1.setForeground(color_green);
        img_train1.setIcon(image_courtSmall); // NOI18N
        img_train1.setText("■");
        img_train1.setToolTipText("");

        img_train2.setFont(font_img); // NOI18N
        img_train2.setForeground(color_orange);
        img_train2.setIcon(image_courtSmall); // NOI18N
        img_train2.setText("■");
        img_train2.setToolTipText("");
                
        img_train3.setFont(font_img); // NOI18N
        img_train3.setForeground(color_green);
        img_train3.setIcon(image_courtSmall); // NOI18N
        img_train3.setText("■");
        img_train3.setToolTipText("");
        
        img_train4.setFont(font_img); // NOI18N
        img_train4.setForeground(color_green);
        img_train4.setIcon(image_courtSmall); // NOI18N
        img_train4.setText("■");
        img_train4.setToolTipText("");
        
        name_train1.setFont(font_train); // NOI18N
        name_train1.setText("Entraînement A");
        
        name_train2.setFont(font_train); // NOI18N
        name_train2.setText("Entraînement B");

        name_train3.setFont(font_train); // NOI18N
        name_train3.setText("Entraînement C");

        name_train4.setFont(font_train); // NOI18N
        name_train4.setText("Entraînement C");

        vs_courtA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vs_courtA.setText("Libre");

        vs_courtB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vs_courtB.setText("Laurent MOREL vs Loïc ROUQUETTE");

        vs_train1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vs_train1.setText("Libre");

        vs_train2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vs_train2.setText("Libre");

        vs_train3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vs_train3.setText("Libre");

        vs_train4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vs_train4.setText("Libre");
        
    }
    
    private void generateLayout(){
                javax.swing.GroupLayout contentViewLayout = new javax.swing.GroupLayout(this);
        this.setLayout(contentViewLayout);
        contentViewLayout.setHorizontalGroup(
            contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentViewLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vs_courtB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentViewLayout.createSequentialGroup()
                        .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img_courtB)
                            .addGroup(contentViewLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(name_courtB))
                            .addGroup(contentViewLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(name_courtA))
                            .addComponent(img_courtA)
                            .addComponent(vs_courtA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentViewLayout.createSequentialGroup()
                                .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(img_train2)
                                        .addGroup(contentViewLayout.createSequentialGroup()
                                            .addComponent(name_train2)
                                            .addGap(59, 59, 59)))
                                    .addComponent(vs_train2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vs_train4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(img_train4)
                                        .addGroup(contentViewLayout.createSequentialGroup()
                                            .addComponent(name_train4)
                                            .addGap(59, 59, 59)))))
                            .addGroup(contentViewLayout.createSequentialGroup()
                                .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentViewLayout.createSequentialGroup()
                                            .addGap(31, 31, 31)
                                            .addComponent(name_train1)
                                            .addGap(55, 55, 55))
                                        .addComponent(img_train1))
                                    .addComponent(vs_train1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vs_train3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(img_train3)
                                        .addGroup(contentViewLayout.createSequentialGroup()
                                            .addComponent(name_train3)
                                            .addGap(59, 59, 59))))))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        contentViewLayout.setVerticalGroup(
            contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentViewLayout.createSequentialGroup()
                .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentViewLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(name_courtA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(img_courtA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vs_courtA)
                        .addGap(18, 18, 18)
                        .addComponent(name_courtB))
                    .addGroup(contentViewLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentViewLayout.createSequentialGroup()
                                .addComponent(name_train1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(img_train1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vs_train1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentViewLayout.createSequentialGroup()
                                .addComponent(name_train3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(img_train3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vs_train3)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentViewLayout.createSequentialGroup()
                        .addComponent(name_train2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(img_train2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vs_train2))
                    .addGroup(contentViewLayout.createSequentialGroup()
                        .addComponent(name_train4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(img_train4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vs_train4))
                    .addGroup(contentViewLayout.createSequentialGroup()
                        .addComponent(img_courtB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vs_courtB)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }
    
    public void setState(int courtNumber, int state){
        
        JLabel tmpLabel = getCourtByNumber(courtNumber);
        Color color = getStateColor(state);
        
        tmpLabel.setForeground(color);

    }
    
    private Color getStateColor(int state){
        
        Color color = null;
        switch(state){
            case 0:
                color = color_green;
                break;
            case 1:
                color = color_orange;
                break;
            case 2:
                color = color_red;
                break;
        }
        
        return color;
        
    }
    
    private JLabel getCourtByNumber(int courtNumber){
        
        JLabel tmpLabel = null;

        switch(courtNumber){
            case NUM_COURTA:
                tmpLabel = img_courtA;
                break;
            case NUM_COURTB:
                tmpLabel = img_courtB;
                break;
            case NUM_TRAIN1:
                tmpLabel = img_train1;
                break;
            case NUM_TRAIN2:
                tmpLabel = img_train2;
                break;
            case NUM_TRAIN3:
                tmpLabel = img_train3;
                break;
            case NUM_TRAIN4:
                tmpLabel = img_train4;
                break;
        }       
        
        return tmpLabel;
        
    }
 
    private javax.swing.JLabel img_courtA;
    private javax.swing.JLabel img_courtB;
    private javax.swing.JLabel img_train1;
    private javax.swing.JLabel img_train2;
    private javax.swing.JLabel img_train3;
    private javax.swing.JLabel img_train4;
    private javax.swing.JLabel name_courtA;
    private javax.swing.JLabel name_courtB;
    private javax.swing.JLabel name_train1;
    private javax.swing.JLabel name_train2;
    private javax.swing.JLabel name_train3;
    private javax.swing.JLabel name_train4;
    private javax.swing.JLabel vs_courtA;
    private javax.swing.JLabel vs_courtB;
    private javax.swing.JLabel vs_train1;
    private javax.swing.JLabel vs_train2;
    private javax.swing.JLabel vs_train3;
    private javax.swing.JLabel vs_train4;
    
    private java.awt.Color color_red;
    private java.awt.Color color_orange;
    private java.awt.Color color_green;
    
    private javax.swing.ImageIcon image_courtLarge;
    private javax.swing.ImageIcon image_courtSmall;
}
