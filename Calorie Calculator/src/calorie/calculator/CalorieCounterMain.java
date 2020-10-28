/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.calculator;

/**
 *
 * @author Nick McGuffin
 */
public class CalorieCounterMain {
    
    public static void main(String[] args) {
        
        
        CalorieCounterFrame newGUI = new CalorieCounterFrame();
        newGUI.setVisible(true);
        newGUI.setSize(560, 650);
        newGUI.setDefaultCloseOperation(CalorieCounterFrame.EXIT_ON_CLOSE);
        newGUI.setLocationRelativeTo(null);
        newGUI.setResizable(false);
    }
    
}
