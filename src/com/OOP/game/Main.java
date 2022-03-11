package com.OOP.game;


import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;


// This class extends JPanel. It overrides
// the paintComponent() method so that random
// lines are plotted in the panel.
class PaintPanel extends JPanel {



    Insets ins; // holds the panel's insets
    // Construct a panel.
    PaintPanel() {/* w   ww    .d  e    m  o2   s  .  c  o m  */
    }
    // Override the paintComponent() method.
    protected void paintComponent(Graphics g) {
        // Always call the superclass method first.
        super.paintComponent(g);

        int x1,y1;
        if(Main.x==0&&Main.y==0){
            x1=8;
            y1=120;
        }
        else{
            x1=Main.x;
            y1=Main.y;
        }


        g.drawRect(0,100,120,10);
        g.drawRect(110,100,10,100);
        g.drawRect(0,140,90,10);
        g.drawRect(80,140,10,100);
        g.drawRect(80,240,250,10);
        g.drawRect(110,200,180,10);
        g.drawRect(290,160,10,50);
        g.drawRect(290,160,120,10);
        g.drawRect(320,190,10,50);
        g.drawRect(320,190,50,10);
        g.drawRect(360,200,10,100);
        g.drawRect(400,160,10,170);
        g.drawRect(220,300,150,10);
        g.drawRect(220,310,10,50);
        g.drawRect(250,330,300,10);
        g.drawRect(220,360,330,10);

        g.setColor(Color.blue);
        g.drawString("Drag the red ball through the path",200,50);
        g.fillRect(0,100,120,10);
        g.fillRect(110,100,10,100);
        g.fillRect(0,140,90,10);
        g.fillRect(80,140,10,100);
        g.fillRect(80,240,250,10);
        g.fillRect(110,200,180,10);
        g.fillRect(290,160,10,50);
        g.fillRect(290,160,120,10);
        g.fillRect(320,190,10,50);
        g.fillRect(320,190,50,10);
        g.fillRect(360,200,10,100);
        g.fillRect(400,160,10,170);
        g.fillRect(220,300,150,10);
        g.fillRect(220,310,10,50);
        g.fillRect(250,330,300,10);
        g.fillRect(220,360,330,10);
        g.setColor(Color.red);
        g.drawOval(x1,y1,10,10);
        g.fillOval(x1,y1,10,10);


        repaint();
    }
}

// Demonstrate painting directly onto a panel.
public class Main extends JFrame implements MouseMotionListener{

    // Create the panel that will be painted.
    static int x, y;
    int xx = 5,yy = 120;
    PaintPanel pp = new PaintPanel();
    Rectangle r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16;

    Area a1 = new Area();
    Point p1 = new Point();
    boolean isstarted = false;




    Main() {

        r1 = new Rectangle(0,100,120,10);
        r2 = new Rectangle(110,100,10,100);
        r3 = new Rectangle(0,140,90,10);
        r4 = new Rectangle(80,140,10,100);
        r5 = new Rectangle(80,240,250,10);
        r6 = new Rectangle(110,200,180,10);
        r7 = new Rectangle(290,160,10,50);
        r8 = new Rectangle(290,160,120,10);
        r9 = new Rectangle(320,190,10,50);
        r10 = new Rectangle(320,190,50,10);
        r11 = new Rectangle(360,200,10,100);
        r12 = new Rectangle(400,160,10,170);
        r13 = new Rectangle(220,300,150,10);
        r14 = new Rectangle(220,310,10,50);
        r15 = new Rectangle(250,330,300,10);
        r16 = new Rectangle(220,360,330,10);

        JFrame jfrm = new JFrame("Paint Demo");
        jfrm.setSize(600, 500);
        // Terminate the program when the user closes the application.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // JLabel l1= new JLabel("Drag the red ball through the path");

        // l1.setBounds(200,80,200,50);
        //jfrm.add(l1);
        jfrm.add(pp);
        jfrm.addMouseMotionListener(this);
        // Display the frame.
        jfrm.setVisible(true);
    }

    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {


        x = e.getX()-10;
        y = e.getY()-40;
        isstarted = true;
        if(x>xx+20||(y>yy+10||y<yy-10)){
            gameover();


        }
        xx=x;
        yy=y;


        p1.setLocation(x,y);
       if(isin(r1)||isin(r2)||isin(r3)||isin(r4)||isin(r5)||isin(r6)||isin(r7)||isin(r8)||isin(r9)||isin(r10)||isin(r11)||isin(r12)||isin(r13)||isin(r14)||isin(r15)||isin(r16)||x<0){
           gameover();

       }
       if(x>=550){
           isstarted = false;
           AudioInputStream audioIn = null;
           try {
               File file = new File("src/com/OOP/game/Passed.wav");
               AudioInputStream stream = AudioSystem.getAudioInputStream(file);
               Clip clip = AudioSystem.getClip();
               clip.open(stream);
               clip.start();

               // sleep to allow enough time for the clip to play
               Thread.sleep(500);

               stream.close();

           } catch (Exception ex) {
               System.out.println(ex.getMessage());
           }
           showMessageDialog(null, "Mission Passed Respect++");
           x=5;
           y=120;
       }


        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(isstarted){
            gameover();

        }
    }
    boolean isin(Rectangle r){
        if(r.contains(p1)){
            return true;
        }
        else
            return false;

    }
    void gameover(){
        AudioInputStream audioIn = null;
        try {
            File file = new File("src/com/OOP/game/wasted.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

            // sleep to allow enough time for the clip to play
            Thread.sleep(500);

            stream.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        showMessageDialog(null, "Wasted");
        x=5;
        y=120;
        repaint();
        isstarted = false;
        xx=5;
        yy=120;

    }




}