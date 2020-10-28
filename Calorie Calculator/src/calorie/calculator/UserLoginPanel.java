/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.calculator;




import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;



/**
 *
 * @author Nick McGuffin
 */
public class UserLoginPanel extends JPanel implements ActionListener {

    private JPanel userLoginPanel;
    private JPanel userLoginButtonPanel; 
    private JPanel outputPanel;
   
    
    private JLabel username;
    private JLabel password;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea outputArea;
    
    private JButton loginButton;
    private JButton clearButton;
    private JButton newUserButton;
    private JButton recoverAccountButton;
    
    public UserLoginPanel() {
        
        
        //create main login panel
        userLoginPanel = new JPanel();
        userLoginPanel.setLayout(new GridLayout(2,2, 0, 5));
        userLoginPanel.setBorder(BorderFactory.createTitledBorder("Enter " +
                 "Login Information: "));
        
        username = new JLabel("Username: ");
        
        usernameField = new JTextField(20);
        password = new JLabel("Password: ");
        
        passwordField = new JPasswordField(20);
        
        userLoginPanel.add(username);
        userLoginPanel.add(usernameField);
        userLoginPanel.add(password);
        userLoginPanel.add(passwordField);
        
        
        
        //create button panel
        userLoginButtonPanel = new JPanel();
        userLoginButtonPanel.setLayout(new GridLayout(2, 2, 50, 10));
        
        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        newUserButton = new JButton("New User");
        recoverAccountButton = new JButton("Lost Password?");
        
        
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        newUserButton.addActionListener(this);
        recoverAccountButton.addActionListener(this);
        
        userLoginButtonPanel.add(loginButton);
        userLoginButtonPanel.add(clearButton);
        userLoginButtonPanel.add(newUserButton);
        userLoginButtonPanel.add(recoverAccountButton);
       
        outputArea = new JTextArea(23, 40);
        
        outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        
        outputPanel.add(outputArea);
        
        //add panels to frame.     
        this.add(userLoginPanel);
        this.add(userLoginButtonPanel);
        this.add(outputPanel);
      
        
        
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object source = e.getSource();
        
       if (source == loginButton){
           
           outputArea.setText("User Account Functionality not supported yet.\n");
       }
       
       if (source == clearButton){
           
           clear();
       }
       
       if (source == newUserButton){
           outputArea.setText("");
           outputArea.append("Functionality not yet supported.\n");
           
       }
       
       if (source == recoverAccountButton) {
           outputArea.setText("");
           outputArea.append("No accounts currently exist, please check back later.\n");
       }
    }
    
    public void clear() {
        
        usernameField.setText("");
        passwordField.setText("");
        outputArea.setText("Data cleared.");
    }
    
    
}
