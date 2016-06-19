/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;
import static cardgame.Cardgame.cur;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nikhil-hp
 */

public class Cardgame {

    public static int  c = 52;
    static Cardgame game;
    static curdet cur=new curdet();
    static char tr;
    static int[] va = new int[1000],cl2=new int[13],cl4=new int[13];
    static PrintStream p=null;
    public static JLabel[] p1 = new JLabel[13], p2 = new JLabel[13],
            p3 = new JLabel[13], p4 = new JLabel[13];
    public static JFrame f = new JFrame();
    public static String[] c1 = new String[13], c2 = new String[13], c3 = new String[13], c4 = new String[13];
    public static JLayeredPane pan = f.getLayeredPane();
    static class curdet{
        int starter;
        int wina,winb;
        char trump;
        int init;
        int cur,d1,d2,d3,d4;
        int totalchal;
        int[] cd1=new int[13],cd2=new int[13],cd3=new int[13],cd4=new int[13];
        curdet(){
            totalchal=0;
            cur=0;d1=0;d2=0;d3=0;d4=0;init=1;wina=0;winb=0;starter=1;
        }
    }
    public static int max(int a,int b,int c,int d){
       
        if(a>b&&a>c&&a>d)
            return 1;
        else if(b>a&&b>c&&b>d)
            return 2;
        else if(c>b&&c>a&&c>d)
            return 3;
        else
            return 4;
        
    }
    public static void flush(){
         cur.totalchal++;
        p.println("flush\n"+cur.cd1[cur.d1-1]+"\n"+cur.cd2[cur.d2-1]+"\n"
                    +cur.cd3[cur.d3-1]+"\n"+cur.cd4[cur.d4-1]);
        try{
        Thread.sleep(1000);}
        catch(Exception e){ ;}
        
            p1[cur.cd1[cur.d1-1]].setVisible(false);
            p2[cur.cd2[cur.d1-1]].setVisible(false);
            p3[cur.cd3[cur.d1-1]].setVisible(false);
            p4[cur.cd4[cur.d1-1]].setVisible(false);
        
        if(c1[cur.cd1[cur.d1-1]].charAt(0)==tr||c2[cur.cd2[cur.d2-1]].charAt(0)==tr||
           c3[cur.cd3[cur.d3-1]].charAt(0)==tr||c4[cur.cd4[cur.d4-1]].charAt(0)==tr)
        {   int one=0,two=0,three=0,four=0;
            if(c1[cur.cd1[cur.d1-1]].charAt(0)==tr)
                one=Integer.parseInt(c1[cur.cd1[cur.d1-1]].substring(1));
            if(c2[cur.cd2[cur.d2-1]].charAt(0)==tr)
                two=Integer.parseInt(c2[cur.cd2[cur.d2-1]].substring(1));
            if(c3[cur.cd3[cur.d3-1]].charAt(0)==tr)
                three=Integer.parseInt(c3[cur.cd3[cur.d3-1]].substring(1));
            if(c4[cur.cd4[cur.d4-1]].charAt(0)==tr)
                four=Integer.parseInt(c4[cur.cd4[cur.d4-1]].substring(1));
            cur.starter=max(one,two,three,four);
        }
        else{char e;
                if(cur.starter==1)
                    e=c1[cur.cd1[cur.d1-1]].charAt(0);
                else if(cur.starter==2)
                    e=c2[cur.cd2[cur.d2-1]].charAt(0);
                 else if(cur.starter==3)
                 e=c3[cur.cd3[cur.d3-1]].charAt(0);
                   else
                    e=c4[cur.cd4[cur.d4-1]].charAt(0);
                 int one=0,two=0,three=0,four=0;
            if(c1[cur.cd1[cur.d1-1]].charAt(0)==e)
                one=Integer.parseInt(c1[cur.cd1[cur.d1-1]].substring(1));
            if(c2[cur.cd2[cur.d2-1]].charAt(0)==e)
                two=Integer.parseInt(c2[cur.cd2[cur.d2-1]].substring(1));
            if(c3[cur.cd3[cur.d3-1]].charAt(0)==e)
                three=Integer.parseInt(c3[cur.cd3[cur.d3-1]].substring(1));
            if(c4[cur.cd4[cur.d4-1]].charAt(0)==e)
                four=Integer.parseInt(c4[cur.cd4[cur.d4-1]].substring(1));
            cur.starter=max(one,two,three,four);
        }
        if(cur.starter==2&&cur.totalchal!=13)
            player2();
        else if(cur.starter==4&&cur.totalchal!=13)
            player4();
        if(cur.starter==3)
            p.println("check\n0");
        if(cur.starter==2||cur.starter==4)
            cur.wina++;
        else cur.winb++;
        if(cur.totalchal==13||cur.wina==7||cur.winb==7)
        {   
            if(cur.wina>cur.winb){
                if(cur.winb==0){
                    if(cur.init==1||cur.init==2)
                        new nn(4);
                    else 
                        new nn(2);
                    JOptionPane.showMessageDialog(null,"coat on loosing team!!!");
                    p.println("coat");
                }
            else
            {if(cur.init==2||cur.init==4)
            new nn(cur.init);
            else 
                new nn(cur.init+1);
                }
            }
        else {
                if(cur.wina==0){
                     
                    if(cur.init==2||cur.init==3)
                        new nn(1);
                    else 
                        new nn(3);
                    JOptionPane.showMessageDialog(null,"coat on loosing team!!!");
                    p.println("coat");
                }
                else{
                if(cur.init==1||cur.init==3)
                    new nn(cur.init);
                else if(cur.init==2)
                { new nn(3);}
                else new nn(1);}
        }
                        
        }
    }
    void sorc(String[] t) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 12; j++) {
                if (t[j].charAt(0) > t[j + 1].charAt(0)) {
                    String te = t[j];
                    t[j] = t[j + 1];
                    t[j + 1] = te;
                }
            }
        }

    }

    void sorn(String[] t) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 12; j++) {
                if (Integer.parseInt(t[j].substring(1)) < Integer.parseInt(t[j + 1].substring(1))
                        && t[j].charAt(0) == t[j + 1].charAt(0)) {
                    String te = t[j];
                    t[j] = t[j + 1];
                    t[j + 1] = te;
                }
            }
        }

    }

    public int val(int t) {
        if (va[t] == 1) {
            return 0;
        } else {
            va[t] = 1;
        }
        return 1;
    }

    public String conv(int t) {
        if(t==1)
            return "c14";
        else if(t==14)
            return "d14";
        else if(t==27)
            return "h14";
        else if(t==40)
            return "s14";
        if (t < 14) {
            return "c" + t;
        } else if (t < 27) {
            return "d" + (t - 13);
        } else if (t < 40) {
            return "h" + (t - 26);
        } else {
            return "s" + (t - 39);
        }
    }
    //<editor-fold desc="ait" defaultstate="collapsed">
    static char ait(int p){
        int a[]=new int[5];
        for(int i=0;i<5;i++)
        {      a[i]=  Math.round((float)Math.random()*100/8);
                for(int j=0;j<i;j++)
                    if(a[i]==a[j])
                        i--;
        }
        int h=0,s=0,c=0,d=0;
        for(int i=0;i<5;i++)
        if(p==2){
            if(c2[a[i]].charAt(0)=='h')
                h++;
            else if(c2[a[i]].charAt(0)=='s')
                s++;
            else if(c2[a[i]].charAt(0)=='c')
                c++;
            else
                d++;
        }
        else{
            if(c4[a[i]].charAt(0)=='h')
                h++;
            else if(c4[a[i]].charAt(0)=='s')
                s++;
            else if(c4[a[i]].charAt(0)=='c')
                c++;
            else
                d++;
        }
        int m=max(h,s,c,d);
        if(m==1)
            return 'h';
        else if(m==2)
            return 's';
        else if(m==3)
            return 'c';
        else return 'd';
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="shuffle">

    public void shuff(String[] te) {
        int t = 13;
        while (t != 0&&c!=0) {
            int r = Math.round((float) Math.random() * 100)/2;
            if (r < 1) {
                continue;
            }
            if (val(r) == 1) {
                te[t - 1] = conv(r);
            } else if (val(c) == 0) {
                c--;
                continue;
            } else {
                val(c);
                
                te[t - 1] = conv(c);
                c--;
            }
            t--;

        }
    }

    //</editor-fold>                    

   static  class nn{
    
        nn(int st){
        
        c=52;
        for(int i=0;i<100;i++)
            va[i]=0;
        for(int i=0;i<13;i++)
        {
            cl2[i]=0;
            cl4[i]=0;
        }
        cur=new curdet();
        f.setVisible(false);
        cur.starter=st;
        cur.init=st;
        f=new JFrame();
        pan=f.getLayeredPane();
        game.comp();
        game.mou();
        sendcard();
        if(st==1){
            game.telltrump();
             p.println("trump\n"+cur.trump);}
        else if(st==2)
        {cur.trump=ait(2);
            tr=cur.trump;
            game.showtr();
            p.println("trump\n"+cur.trump);
            player2();
            f.setVisible(true);
        }
        else if(st==3)
        {
        p.println("telltrump");
        f.setVisible(true);
        }
        else
        {cur.trump=ait(4);
            tr=cur.trump;
            game.showtr();
            p.println("trump\n"+cur.trump);
            player4();
            f.setVisible(true);
        }
        f.setSize(1250, 680);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
static void sendcard(){
        p.println("cardsp3");
        for(int i=0;i<13;i++)
            p.println(c3[i]);
        p.println("cardsp1");
        for(int i=0;i<13;i++)
            p.println(c1[i]);
        p.println("cardsp2");
        for(int i=0;i<13;i++)
            p.println(c2[i]);
        p.println("cardsp4");
        for(int i=0;i<13;i++)
            p.println(c4[i]);
        
}
    public void play(JLabel[] t, int x, int y, int or, String[] ca) {
        int i;
        if (or == 0) {
            for (i = 0; i < 13; i++, x -= 20) {
                t[i] = new JLabel();
                t[i].setIcon(new ImageIcon(getClass().getResource("/img/"+ca[i]+".png")));
                pan.add(t[i], i);
                t[i].setBounds(x, y, 115, 167);
            }
        } else {
            for (i = 0; i <13; i++, y += 40) {
                t[i] = new JLabel();
                t[i].setIcon(new ImageIcon(getClass().getResource("/img/" + ca[i] + ".png")));
                pan.add(t[i], i);
                t[i].setVisible(false);
                t[i].setBounds(x, y, 115, 167);
            }
        }
    }

    public void comp() {
JButton ne=new JButton("new game");
pan.add(ne,0);
ne.setFocusable(false);

ne.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e) {
       new nn(1);
    }
});
ne.setBounds(20, 20, 120, 40);
        Color s = new Color(30, 137, 26);
        f.getContentPane().setBackground(s);

        shuff(c1);
        shuff(c2);
        shuff(c3);
        shuff(c4);
        sorc(c1);
        sorn(c1);
        sorc(c2);
        sorn(c2);
        sorc(c3);
        sorn(c3);
        sorc(c4);
        sorn(c4);
        play(p3, 690, 560, 1, c3);
    play(p2,900,20,1,c2);
   play(p1,690,20,0,c1);
    play(p4,200,20,1,c4);
    
    }

    public Cardgame() {


        comp();
        telltrump();
        mou();
        f.setSize(1250, 680);
        f.setLocationByPlatform(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //<editor-fold defaultstate="collapsed" desc="trump">
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
            n.setIcon(new ImageIcon(getClass().getResource("/img/"+c1[a[i]]+".png")));
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
        cur.trump=t.getText().charAt(0);
        tr=cur.trump;
       p.println("trump\n"+tr);
        showtr();
        f.setVisible(true);
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
    //</editor-fold>
    public String[] ask(int a){
        if(a==1)
            return c1;
        if(a==2)
            return c2;
        else if(a==3)
            return c3;
        else return c4;
    }
    int ass(int a){
        if(a==1)
            return cur.cd1[cur.d1-1];
        if(a==2)
            return cur.cd2[cur.d2-1];
        else if(a==3)
            return cur.cd3[cur.d3-1];
        else
            return cur.cd4[cur.d4-1];
    }
void showtr(){
    
    JLabel tr=new JLabel("Trump is:"+cur.trump);
    pan.add(tr,0);
    tr.setBounds(20, 70, 80, 20);
}
    public void j(int t) {
    if(cur.d1<=cur.d3)
    {if(cur.d1==cur.d3)
        if(cur.starter!=4&&cur.starter!=1)
            return;
        if(cur.starter==3)
            if(cur.d3==cur.totalchal)
                return;
        int f=0;
        if(cur.starter!=1)
        {
            for(int i=0;i<13;i++)
                if(p1[i].isVisible()&&c1[i].charAt(0)==ask(cur.starter)[ass(cur.starter)].charAt(0))
                    f=1;
            if(f==1&&c1[t].charAt(0)!=ask(cur.starter)[ass(cur.starter)].charAt(0))
                return;
        }
        p1[t].setLocation(550, 200);
        p.println("chal\n1"+t);
        cur.cur++;
        cur.cd1[cur.d1]=t;
        cur.d1++;
                if(cur.cur==4)
                {cur.cur=0;
                p.println("f");
                return;}
                p.println("ret");
    }
        
    }
//<editor-fold defaultstate="collapsed" desc="listener">

    public void mou() {

        p1[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(0);
            }
        });

        p1[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(1);
            }
        });
        p1[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(2);
            }
        });
        p1[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(3);
            }
        });

        p1[4].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(4);
            }
        });
        p1[5].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(5);
            }
        });
        p1[6].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(6);
            }
        });

        p1[7].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(7);
            }
        });
        p1[8].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(8);
            }
        });
        p1[9].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(9);
            }
        });

        p1[10].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(10);
            }
        });
        p1[11].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(11);
            }
        });
        p1[12].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j(12);
            }
        });

    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="artfr">
    static int artfr(int a,String f[],String s[],int cf[],int cs[]){
        int fl=0;
        for(int i=0;i<13;i++)
        {if(Integer.parseInt(f[i].substring(1))==14||f[i].substring(1).equals("13")&&cf[i]!=1)
                for(int j=0;j<13;j++)
                  if((s[j].substring(1).equals("13")||s[j].substring(1).equals("14"))&&cs[j]!=1&&s[j].charAt(0)==f[i].charAt(0))
                    {fl=1;break;}
        if(fl==1)
            for(int j=12;j>=0;j--)
                if(f[j].charAt(0)==f[i].charAt(0)&&cf[j]!=1)
                {System.out.println("first");return j;}
            
        }
        int ht=0,st=0,ct=0,dt=0;
        for(int i=0;i<13;i++)
        {if(s[i].charAt(0)=='h'&&cs[i]!=1)
            ht=1;
            else if(s[i].charAt(0)=='s'&&cs[i]!=1)
            st=1;
        else if(s[i].charAt(0)=='c'&&cs[i]!=1)
            ct=1;
        else if(s[i].charAt(0)=='d'&&cs[i]!=1)
            dt=1;}
        char tt='h';
        if(ht==0)
            tt='h';
        else if(st==0)
            tt='s';
        else if(ct==0)
            tt='c';
        else if(dt==0)
            tt='d';
                for(int i=12;i>=0;i--)
                    if(cf[i]!=1&&f[i].charAt(0)==tt&&tt!=tr)
                    {System.out.println("samne");return i;}
               int eh=0,es=0,ec=0,ed=0,hi=0,si=0,ci=0,di=0;
               for(int i=12;i>=0;i--)
               {    if(f[i].charAt(0)=='h'&&cf[i]!=1)
               {eh++;hi=i;}
               else if(f[i].charAt(0)=='s'&&cf[i]!=1)
               {es++;si=i;} 
               else if(f[i].charAt(0)=='c'&&cf[i]!=1)
               {ec++;ci=i;}
               else if(f[i].charAt(0)=='d'&&cf[i]!=1)
               {ed++;di=i;}}
               if(eh==1&&tr!='h')
                  return hi; 
               else if(es==1&&tr!='s')
                  return si; 
               else if(ec==1&&tr!='c')
                   return ci;
               else if(ed==1&&tr!='d')
                   return di;
               
                int t=0;
                fl=0;
       for(int i=12;i>=0;i--)
           if(cf[i]!=1)
               t=i;
       for(int i=0;i<13;i++)
           if(cf[i]!=1&&Integer.parseInt(f[i].substring(1))<=Integer.parseInt(f[t].substring(1))&&f[i].charAt(0)!=tr)
           {t=i;fl=1;System.out.println("lastsec");}
       if(fl==1)
       return t;
       else 
           for(int i=12;i>=0;i--)
               if(cf[i]!=1)
               {System.out.println("last");return i;}
       return -1;
            
    }
                
        
    
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="artch">
    
    static int artch(int a,String c[],String s[],int cd[],int d,int cl[],int cs[]){
        char meant;
       //for not first turn
        if(cur.starter==a)
           return artfr(a,c,s,cl,cs);
        if(cur.starter==1){
            meant=c1[cur.cd1[cur.d1-1]].charAt(0);
           
        }
       else if(cur.starter==2)
           meant=c2[cur.cd2[cur.d2-1]].charAt(0);
       else if(cur.starter==3)
            meant=c3[cur.cd3[cur.d3-1]].charAt(0);
       else 
            meant=c4[cur.cd4[cur.d4-1]].charAt(0);
        int ch=0,th=0;
        int cp=0,tp=0;
            if(cur.d1>cur.totalchal&&c1[cur.cd1[cur.d1-1]].charAt(0)==meant)
            {ch=Integer.parseInt(c1[cur.cd1[cur.d1-1]].substring(1));cp=1;}
            else if(cur.d1>cur.totalchal&&c1[cur.cd1[cur.d1-1]].charAt(0)==tr)
            {th=Integer.parseInt(c1[cur.cd1[cur.d1-1]].substring(1));tp=1;}
            
            if(cur.d2>cur.totalchal&&c2[cur.cd2[cur.d2-1]].charAt(0)==meant&&Integer.parseInt(c2[cur.cd2[cur.d2-1]].substring(1))>ch)
            {ch=Integer.parseInt(c2[cur.cd2[cur.d2-1]].substring(1));cp=2;}
            else if(cur.d2>cur.totalchal&&c2[cur.cd2[cur.d2-1]].charAt(0)==tr&&Integer.parseInt(c2[cur.cd2[cur.d2-1]].substring(1))>th)
                    {th=Integer.parseInt(c2[cur.cd2[cur.d2-1]].substring(1));tp=2;}
            if(cur.d3>cur.totalchal&&c3[cur.cd3[cur.d3-1]].charAt(0)==meant&&Integer.parseInt(c3[cur.cd3[cur.d3-1]].substring(1))>ch)
            {ch=Integer.parseInt(c3[cur.cd3[cur.d3-1]].substring(1));cp=3;}
            else if(cur.d3>cur.totalchal&&c3[cur.cd3[cur.d3-1]].charAt(0)==tr&&Integer.parseInt(c3[cur.cd3[cur.d3-1]].substring(1))>th)
                    {th=Integer.parseInt(c3[cur.cd3[cur.d3-1]].substring(1));tp=3;}
            if(cur.d4>cur.totalchal&&c4[cur.cd4[cur.d4-1]].charAt(0)==meant&&Integer.parseInt(c4[cur.cd4[cur.d4-1]].substring(1))>ch)
            {ch=Integer.parseInt(c4[cur.cd4[cur.d4-1]].substring(1));cp=4;}
            else if(cur.d4>cur.totalchal&&c4[cur.cd4[cur.d4-1]].charAt(0)==tr&&Integer.parseInt(c4[cur.cd4[cur.d4-1]].substring(1))>th)
                    {th=Integer.parseInt(c4[cur.cd4[cur.d4-1]].substring(1));tp=4;}
            
            System.out.println("\n"+ch+"\n"+th);
            System.out.println(cp+"\n"+tp);
            if(cp==1||cp==3||tp==1||tp==3){
       System.out.println(meant);
                for(int i=0 ;i<13;i++)
           if(cl[i]!=1&&c[i].charAt(0)==meant)
           {
               if(tp==1||tp==3)
               {for(int j=12;j>=0;j--)
                   if(cl[j]!=1&&c[j].charAt(0)==meant)
                   return j;   
               }
               else
               for(int j=12;j>=0;j--)
                   if(cl[j]!=1&&c[j].charAt(0)==meant&&Integer.parseInt(c[j].substring(1))>ch)
                       return j;
               for(int j=12;j>=0;j--)
                   if(cl[j]!=1&&c[j].charAt(0)==meant)
                   return j;
               break;
               
           }
       for(int i=12;i>=0;i--)
           if(cl[i]!=1&&c[i].charAt(0)==tr&&Integer.parseInt(c[i].substring(1))>th)
           return i;}
            else {
                for(int i=12;i>=0;i--)
                    if(cl[i]!=1&&c[i].charAt(0)==meant)
                        return i;
            }
       int t=0;
       for(int i=12;i>=0;i--)
           if(cl[i]!=1)
               t=i;
       for(int i=0;i<13;i++)
           if(cl[i]!=1&&Integer.parseInt(c[i].substring(1))<=Integer.parseInt(c[t].substring(1))&&c[i].charAt(0)!=tr)
           t=i;
       
       return t;
            
    
            
    }
    //</editor-fold>
    
    
    
    public static void player2(){
        int i=artch(2,c2,c4,cur.cd2,cur.d2,cl2,cl4);
        cl2[i]=1;
        p2[i].setLocation(600, 250);
            p2[i].setVisible(true);
                cur.cd2[cur.d2]=i;
                cur.d2++;
                p.println("chal\n2"+i);
                
                cur.cur++;
                if(cur.cur!=4)
                p.println("check\n"+game.ask(cur.starter)[game.ass(cur.starter)].charAt(0));
                if(cur.cur==4)
                {cur.cur=0;
                flush();}
                return;
    }
    public static void player4(){
                int i=artch(4,c4,c2,cur.cd4,cur.d4,cl4,cl2);
                cl4[i]=1;
                p4[i].setLocation(470, 250);
                 p4[i].setVisible(true);
                p.println("chal\n4"+i);
                cur.cd4[cur.d4]=i;
                cur.d4++;
                cur.cur++;
                if(cur.cur==4)
                {cur.cur=0;
                flush();}
               
                return;
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        game=new Cardgame();
        
        ServerSocket ser=new ServerSocket(5000);
        Socket s=ser.accept();
        p=new PrintStream(s.getOutputStream());
        sendcard();
                 

        
            InputStreamReader in=new InputStreamReader(s.getInputStream());
            BufferedReader buff=new BufferedReader(in);
            while(true){
                
                String re=buff.readLine();
                
                if(re.equals("chal")){
                    int l=Integer.parseInt(buff.readLine());
                   
                    p3[l].setLocation(550,370);
                     p3[l].setVisible(true);
                    cur.cd3[cur.d3]=l;
                    cur.d3++;
                    cur.cur++;
                    if(cur.cur==4)
                {cur.cur=0;
                flush();continue;}
                  
                    player4();
                }
                else if(re.charAt(0)=='h'||re.charAt(0)=='s'||
                        re.charAt(0)=='c'||re.charAt(0)=='d')
                {cur.trump=re.charAt(0);
                    tr=cur.trump;
                    game.showtr();
                
                    p.println("trump\n"+tr);
                }
                else if(re.equals("retd"))
                    player2();
                else if(re.equals("f"))
                    flush();
            }
    }

}
