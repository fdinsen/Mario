/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

/**
 *
 * @author simon
 */
public class SingleMenu {
    
    public static int amount = 0;
    
    private SingleMenu() {
        amount++;
    }
    
    public static SingleMenu getInstance() {
        return SingleMenuHolder.INSTANCE;
    }
    
    private static class SingleMenuHolder {

        private static final SingleMenu INSTANCE = new SingleMenu(); 
    }
    public int getMenu(){
        
        return amount;
        
    }
}
