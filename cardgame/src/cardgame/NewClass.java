/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author nikhil
 */
public class NewClass {
    public static class th extends Thread{
        int obj;
        public th(int a){
            obj=a;
        }
        public void run(){
    System.out.print(obj);
    }
    }
    
    public static void main(String args[]){
        new th(2).start();
    }
}
