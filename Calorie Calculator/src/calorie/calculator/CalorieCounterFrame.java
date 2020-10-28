/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.calculator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Nick McGuffin
 */
public class CalorieCounterFrame extends JFrame {
    
    
    
    
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    
    private JLabel titleLabel;
    private final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    
    static ArrayList<Person> personArray; //create array of people (clients)
    
    CalorieCounterFrame() {
    
        
        
        super("The Metabolic Movement");
        
        
        personArray  = new ArrayList<>(); //instantiate array
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
    
        tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane);
        
        //create and add header
        titleLabel = new JLabel("Calorie Calculator");      
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(TITLE_FONT);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        
        
        //adding tabbed panes
        tabbedPane.addTab("User Login", new UserLoginPanel());
        tabbedPane.addTab("Calculator", new CalculatorPanel());    

      
        this.add(mainPanel);
        
        
        addWindowListener( // override window closing method
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					exit();				// Attempt to exit application
				}
			}
		);
	}
    private void exit() //what happens upon system exit
	{
		
		
		int confirmed = JOptionPane.showConfirmDialog(null,
			    "Thank you for using the Metabolic Movement Calorie Counter.", "Metabolic Movement",
			    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE); //display exit message upon X button press.
		
		    if (confirmed == JOptionPane.YES_OPTION)
		   		{

		    		System.exit(0); //executes system exit when OK is pressed.
		   		}
	} // exit
        
        
}
    
    	

