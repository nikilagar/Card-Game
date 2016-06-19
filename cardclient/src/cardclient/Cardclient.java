/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardclient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nikhil
 */
public class Cardclient {
    static Socket so=null;
    static Cardclient game;
    static int tot=0,cur=0;
    static char ch='n';
    static PrintStream p=null;
    public static JLabel[] p3 = new JLabel[13],p1=new JLabel[13],p2=new JLabel[13],p4=new JLabel[13];
    public static JFrame f=new JFrame();
    public static JLayeredPane pan = f.getLayeredPane();
    public static String[] c1 = new String[13], c2 = new String[13], c3 = new String[13], c4 = new String[13];

    public void play(JLabel[] t, int x, int y, int or, String[] ca) {
        int i;
        int x1=x,y1=y;
            for (i = 0; i < 13; i++, x1 -= 20) {
                t[i] = new JLabel();
                t[i].setIcon(new ImageIcon(getClass().getResource("/img/"+ca[i]+".png")));
                
                pan.add(t[i], i);
                t[i].setBounds(x1, y1, 115, 167);
                if(or==1){
                    t[i].setVisible(false);
                    t[i].setLocation(x, y);
                }
            }
        
    }

    public void comp() {
        Color s = new Color(30, 137, 26);
        f.getContentPane().setBackground(s);
        play(p3, 690, 560, 0, c3);
        play(p1, 550, 200, 1, c1);
        play(p2, 600, 250, 1, c2);
        play(p4, 470, 250, 1, c4);
        
        
    }

    public Cardclient() {


        comp();
        mou();
        f.setSize(1250, 680);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void j(int t) {
        if(ch=='n')
            return;
        if(cur<=tot){
        
        if(ch=='0'||c3[t].charAt(0)==ch){
        p3[t].setLocation(550, 370);
        p.println("chal\n"+t);
        cur++;
        ch='n';
        }
        }
    }
//<editor-fold defaultstate="collapsed" desc="listener">

    public void mou() {

        p3[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(0);
            }
        });

        p3[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(1);
            }
        });
        p3[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(2);
            }
        });
        p3[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(3);
            }
        });

        p3[4].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(4);
            }
        });
        p3[5].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(5);
            }
        });
        p3[6].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(6);
            }
        });

        p3[7].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(7);
            }
        });
        p3[8].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(8);
            }
        });
        p3[9].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(9);
            }
        });

        p3[10].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(10);
            }
        });
        p3[11].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(11);
            }
        });
        p3[12].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(12);
            }
        });

    }
//</editor-fold>
    
    public void telltrump(){
        f.setVisible(false);
     int a[]=new int[5];
        for(int i=0;i<5;i++)
        {      a[i]=  Math.round((float)Math.random()*100/8);
                for(int j=0;j<i;j++)
                    if(a[i]==a[j])
                        i--;
        }
       final JDialog w=new JDialog();
       w.setSize(300,300);
       w.setVisible(true);
       JLayeredPane s=w.getLayeredPane();
       JLabel n;int x=120;
        for(int i=4;i>=0;i--,x-=20){
            n=new JLabel();
            n.setIcon(new ImageIcon(getClass().getResource("/img/"+c3[a[i]]+".png")));
            s.add(n,4-i);
            n.setBounds(x,20,115,167);
        }
        n=new JLabel("Enter trump:(h,s,c,d)");
        n.setBounds(40,195,180,20);
        final JTextField t=new JTextField();
        t.setBounds(220, 195, 20, 20);
        s.add(n,0);
        s.add(t,1);
        JButton trum=new JButton("Enter trump");
        trum.setBounds(100,220,110,30);
        trum.setFocusable(false);
        s.add(trum,0);
        
        trum.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
        p.println(t.getText());
        ch='0';
        w.setVisible(false);
         }
     });
        w.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        JButton cl=new JButton("Exit");
        cl.setBounds(220,220,60,30);
        cl.setFocusable(false);
        s.add(cl,0);
        cl.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
        System.exit(0);
         }
     });
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        so=new Socket("127.0.0.1",5000);
        p=new PrintStream(so.getOutputStream());
        InputStreamReader i=new InputStreamReader(so.getInputStream());
        BufferedReader buff=new BufferedReader(i);
       while(true)
       {String re=buff.readLine();
       
       if(re.equals("cardsp3"))
        {for(int ii=0;ii<13;ii++)
                c3[ii]=buff.readLine();
        }
        else if(re.equals("cardsp2"))
        {for(int ii=0;ii<13;ii++)
                c2[ii]=buff.readLine();
        }
        else if(re.equals("cardsp1"))
        {for(int ii=0;ii<13;ii++)
                c1[ii]=buff.readLine();
        }
        else if(re.equals("cardsp4"))
        {for(int ii=0;ii<13;ii++)
                c4[ii]=buff.readLine();
        f.setVisible(false);
        f= new JFrame();
        pan=f.getLayeredPane();
        game=new Cardclient();
        tot=0;
        cur=0;
        }
        else if(re.equals("trump")){
              JLabel tr=new JLabel("Trump is:"+buff.readLine());
    pan.add(tr,0);
    tr.setBounds(20, 70, 80, 20);
    f.setVisible(true);
    
        }
           else if(re.equals("chal"))
           {   String t=buff.readLine();
                if(t.charAt(0)=='1')
                    p1[Integer.parseInt(t.substring(1))].setVisible(true);
                else if(t.charAt(0)=='2')
                    p2[Integer.parseInt(t.substring(1))].setVisible(true);
               else if(t.charAt(0)=='4')
                    p4[Integer.parseInt(t.substring(1))].setVisible(true);
               
           }
           else if(re.equals("flush")){
               try{
                   Thread.sleep(1000);
               }catch(Exception e){
                   ;
               }
               int t=Integer.parseInt(buff.readLine());
            p1[t].setVisible(false);
            t=Integer.parseInt(buff.readLine());
            p2[t].setVisible(false);
            t=Integer.parseInt(buff.readLine());
            p3[t].setVisible(false);
            t=Integer.parseInt(buff.readLine());
            p4[t].setVisible(false);
            tot++;
           }
           else if(re.equals("telltrump")){
               game.telltrump();
           }
           else if(re.equals("ret"))
               p.println("retd");
           else if(re.equals("f"))
               p.println("f");
           else if(re.equals("check")){
               ch=buff.readLine().charAt(0);
               int f=0;
               for(int k=0;k<13;k++)
                   if(p3[k].isVisible())
                       if(c3[k].charAt(0)==ch)
                       {f=1;break;}
               if(f==0)
                   ch='0';
           }
           else if(re.equals("coat"))
               JOptionPane.showMessageDialog(null,"Coat on loosing team!!!");
       }
    }
    
}
