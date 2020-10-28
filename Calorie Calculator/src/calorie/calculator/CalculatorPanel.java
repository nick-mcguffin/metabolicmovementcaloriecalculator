/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.calculator;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Nick McGuffin
 */
public class CalculatorPanel extends JPanel implements ActionListener{
    
    private JPanel firstCalculatorPanel;
    private JPanel secondCalculatorPanel;
    private JPanel buttonPanel; 
    private JPanel outputPanel;
   
    private JComboBox gender;
    private JComboBox clientGoals;
    private JComboBox activeLevel;
    
    
    String[] genderString = { "Gender...", "Male", "Female"};
    String[] clientGoalsString = {"Fitness Goals...", "Weight Loss", "Weight Maintenance", "Muscle Gain"}; 
    String[] activeLevelString = { "Activity Levels... ", "Sedentary", "Average Activity", "Moderate Activity", "Extreme Activity"};
            
    
    private JLabel firstName;
    private JLabel lastName;
    private JLabel height;
    private JLabel weight;
    private JLabel age;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField heightField; 
    private JTextField weightField;
    private JTextField ageField;
    private JTextArea outputArea;
    
    private JButton calculateButton;
    private JButton clearButton;
    
    
    final int FAT_CALORIES = 9;
    final int CARBOHYDRATE_CALORIES = 4;
    final int PROTEIN_CALORIES = 4;
    
    
    
    public CalculatorPanel() {
        
        //first panel setup
        
        firstCalculatorPanel = new JPanel();
        firstCalculatorPanel.setLayout(new GridLayout(1, 2, 40, 20));
        firstCalculatorPanel.setBorder(BorderFactory.createTitledBorder("Select your Gender and Fitness Goals"));
        
        gender = new JComboBox<String>(genderString);
        gender.setSelectedIndex(0);
        gender.addActionListener(this);
        gender.setBackground(Color.WHITE);
        
        clientGoals = new JComboBox<String>(clientGoalsString);
        clientGoals.setSelectedIndex(0);
        clientGoals.addActionListener(this);
        clientGoals.setBackground(Color.WHITE);
        
        activeLevel = new JComboBox<String>(activeLevelString);
        activeLevel.setSelectedIndex(0);
        activeLevel.addActionListener(this);
        activeLevel.setBackground(Color.WHITE);
        
        firstCalculatorPanel.add(gender);
        firstCalculatorPanel.add(clientGoals);
        firstCalculatorPanel.add(activeLevel);
        
        //second panel setup
        secondCalculatorPanel = new JPanel();
        secondCalculatorPanel.setLayout(new GridLayout(5,5, -75, 15));
        secondCalculatorPanel.setBorder(BorderFactory.createTitledBorder(""));
        
      
        firstName = new JLabel("First Name: ");
        
        
        lastName = new JLabel("Last Name: ");
        
        
        weight = new JLabel("Enter Weight (kg): ");
        
        
        height = new JLabel("Enter Height (cm): ");
        
        
        age = new JLabel("Enter age: ");
        
        
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        heightField = new JTextField(3);
        weightField = new JTextField(3);
        ageField = new JTextField(3);
        
        secondCalculatorPanel.add(firstName);
        secondCalculatorPanel.add(firstNameField);
        secondCalculatorPanel.add(lastName);
        secondCalculatorPanel.add(lastNameField);
        secondCalculatorPanel.add(weight);
        secondCalculatorPanel.add(weightField);
        secondCalculatorPanel.add(height);
        secondCalculatorPanel.add(heightField);
        secondCalculatorPanel.add(age);
        secondCalculatorPanel.add(ageField);
        
        
        
        //button panel setup
        
        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear");
      
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 100, 40));
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        
        calculateButton.addActionListener(this);
        clearButton.addActionListener(this);
        

        //output panel setup
        outputArea = new JTextArea(16, 40);
        
        outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        
        outputPanel.add(outputArea);
        
        
        this.add(firstCalculatorPanel);
        this.add(secondCalculatorPanel);
        this.add(buttonPanel);
        this.add(outputPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
       
        if (source == clearButton){
            
            outputArea.append("Data cleared.");
            clear();
   
        }
        
        if (source == calculateButton){
            
            outputArea.append("-------------------------------------------------------------------------------------------------\n");
            
            setCustomerResults();
            calculate();
            
        }
    }
    
    public void clear(){
        //clear button functionality
        firstNameField.setText("");
        lastNameField.setText("");
        heightField.setText("");
        weightField.setText("");
        ageField.setText("");
        gender.setSelectedIndex(0);
        clientGoals.setSelectedIndex(0);
        outputArea.setText("");
        
        
    }
    
    public void calculate(){
        
        //implement calculate button functionality.
        
       
        checkGenderSelection();
        checkClientGoalsSelection();
        checkFirstNameField(firstNameField.getText());
        checkLastNameField(lastNameField.getText());
        checkAgeField(ageField.getText());
        checkWeightField(weightField.getText());
        checkHeightField(heightField.getText()); 
        
             
        DecimalFormat df = new DecimalFormat("####0.00");
        double baseCalories;
        double activityAdjustedCalories;
        double finalAdjustedCalories;
        baseCalories = calculateCalorieIntake((String)this.gender.getSelectedItem(),Integer.parseInt(this.weightField.getText()),
                                                Integer.parseInt(this.heightField.getText()), Integer.parseInt(this.ageField.getText()));
        
        activityAdjustedCalories = calculateActivityLevelAdjustment(baseCalories, (String)this.activeLevel.getSelectedItem());
        
        outputArea.append("Your base calorie intake should be " + df.format(activityAdjustedCalories) + " calories per day.\n\n");
        
        finalAdjustedCalories = dailyCalorieCalculation((String)this.clientGoals.getSelectedItem(), activityAdjustedCalories);
        
        outputArea.append("Your calorie intake adjusted to your fitness goals should be " + df.format(finalAdjustedCalories) + " calories per day.");
    }
    
    
   public void checkHeightField(String heightField){
    //check height field to ensure it remains in between values 1 - 250. 
       //TODO: throwing exceptions correctly.
       
       try{
           
           if(Integer.parseInt(heightField)<= 0 || Integer.parseInt(heightField) >= 251){
          outputArea.append("Please enter a number in the height field between 1 and 250.\n");
          
            }}catch (NumberFormatException e){ 
                    outputArea.append("ERROR: Please enter a number in the height field between 1 and 250.\n");
             }
       
       }
   
   
   
    public void checkWeightField(String weightField){
       //check weight field to ensure it remains in between values 1 - 400. 
       //TODO: throwing exceptions correctly.
       
        try{
         
        if(Integer.parseInt(weightField) <= 0 || Integer.parseInt(weightField) >= 401){
                outputArea.append("Please enter a number in the weight field between 1 and 400.\n");
            }} catch (NumberFormatException e){
                   outputArea.append("ERROR: Please enter a number in the weight field between 1 and 400.\n");
                    
             } }
                        
   
    
    public void checkAgeField(String ageField){
         //check age  field to ensure it remains in between values 1 - 120. 
       //TODO: throwing exceptions correctly.
       
        
        try {
           
         if(Integer.parseInt(ageField) <= 0 || Integer.parseInt(ageField) >= 121) {
                outputArea.append("Please enter a number in the age field between 1 and 120.\n");
            }} catch (NumberFormatException e){
                outputArea.append("ERROR: Please enter a number in the age field between 1 and 120.\n");
            } 
    }
    
    public void checkFirstNameField(String firstNameField){
        
        //check first name field. ensure no numeric values included using regex 
        
        
        
        Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(firstNameField);
        boolean matchFound = matcher.find();
        
        if (firstNameField.isEmpty()){
            outputArea.append("Please enter a first name.\n");
             
        }
        if(matchFound){
            outputArea.append("Please ensure you enter alphabetic characters into the first name field.\n");
        
    }
        
       
    }
    
    public void checkLastNameField(String lastNameField){
        
         //check last name field. ensure no numeric values included using regex 
        
        
        
        Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(lastNameField);
        boolean matchFound = matcher.find();
        
        if (lastNameField.isEmpty()){
            outputArea.append("Please enter a last name.\n");
             
        }
        if(matchFound){
            outputArea.append("Please ensure you enter alphabetic characters into the last name field.\n");
        
    }
       
    }
    
    public void setCustomerResults(){
        //get information from person.java class.
        
        Person newPerson = new Person((String)this.gender.getSelectedItem(), (String)this.clientGoals.getSelectedItem(), this.firstNameField.getText(), this.lastNameField.getText(), 
                                 Integer.parseInt(this.heightField.getText()), Integer.parseInt(this.weightField.getText()), Integer.parseInt(this.ageField.getText()));
        
         CalorieCounterFrame.personArray.add(newPerson);
         outputArea.setText("Your new calorie calculations have been added to the system!"); //confirmation message in Text Area
         outputArea.append(newPerson.toString());
    }

    public String checkGenderSelection(){
        
        String genderSelection;
        if (this.gender.getSelectedItem().equals("Male")){
            
            genderSelection = "Male";
            return genderSelection;
        }
        
        else if (this.gender.getSelectedItem().equals("Female")){
            genderSelection = "Feale";
            return genderSelection;
        }
        else {
            
            genderSelection = "Please make a gender selection.\n";
            return genderSelection;
    }
    }
    
    public String checkClientGoalsSelection(){
        
        String clientGoalsSelection;
        
        if (this.clientGoals.getSelectedItem().equals("Fat Loss")){
            
            clientGoalsSelection = "Fat Loss";
            return clientGoalsSelection;
        }
        
        else if (this.clientGoals.getSelectedItem().equals("Muscle Gain")){
            
            clientGoalsSelection = "Muscle Gain";
            return clientGoalsSelection;
        }
        
        else if (this.clientGoals.getSelectedItem().equals("Weight Maintenance")){
            
            clientGoalsSelection = "Weight Maintenance";
            return clientGoalsSelection;
        }
        
        else 
            clientGoalsSelection = "Please make a fitness goals selection.\n";
            return clientGoalsSelection;
        
    }
    public double calculateCalorieIntake(String gender, int weight, int height, int age) {
        //calculate base metabolic rate for female and male individuals.
        double femaleMetabolicRate = 655.1 + ((9.563 * weight) + (1.85 * height) - (4.676 * age));
	double maleMetabolicRate =  66.47 + ((13.75 * weight) + (5.003 * height) - (6.755 * age));
	double baseDailyMetabolicRate;
        double errorCode = 9999999;       
                
                if(this.gender.getSelectedItem().equals("Male")) {
                    
                    baseDailyMetabolicRate = maleMetabolicRate;
                    return baseDailyMetabolicRate;
                    
                }
                
                else if (this.gender.getSelectedItem().equals("Female")) {
                    
                    baseDailyMetabolicRate = femaleMetabolicRate;
                    return  baseDailyMetabolicRate;
                }
                else {
                    
                return errorCode;
                }
    }
    
    public double calculateActivityLevelAdjustment(double baseCalories, String activeLevel){
        //adjust calorie intake based on user's activity level
        double activityLevelAdjusted;
        double errorCode = 999;
        double sedentaryMultiplier = 1;
        double averageMultiplier = 1.2;
        double moderateMultiplier = 1.3;
        double extremeMultiplier = 1.5;
        
        if (this.activeLevel.getSelectedItem().equals("Sedentary")){
            activityLevelAdjusted = (baseCalories * sedentaryMultiplier);
            return activityLevelAdjusted;
        }
        
        else if (this.activeLevel.getSelectedItem().equals("Average Activity")){
            activityLevelAdjusted = (baseCalories * averageMultiplier);
            return activityLevelAdjusted;
        }
        
        else if (this.activeLevel.getSelectedItem().equals("Moderate Activity")){
            activityLevelAdjusted = (baseCalories * moderateMultiplier);
            return activityLevelAdjusted;
        }
        
        else if (this.activeLevel.getSelectedItem().equals("Extreme Activity")){
            activityLevelAdjusted = (baseCalories * extremeMultiplier);
            return activityLevelAdjusted;
        }
        else {
            return errorCode;
        }
    }
    
    public double dailyCalorieCalculation(String clientGoals, double activityAdjustedCalorieCalculation){
        
        //calculate daily calorie intake based on client goals and the base calorie calculation that is adjusted to males and females.
        double adjustedDailyCalories;
        int calorieModifier = 500;
        double errorCode = 999;
        
        if (this.clientGoals.getSelectedItem().equals("Weight Loss")){
            
            adjustedDailyCalories = (activityAdjustedCalorieCalculation - calorieModifier);
            return adjustedDailyCalories;
        }
        
        else if (this.clientGoals.getSelectedItem().equals("Muscle Gain")){
            
            adjustedDailyCalories = (activityAdjustedCalorieCalculation + calorieModifier);
            return adjustedDailyCalories;
        }
        
        else if (this.clientGoals.getSelectedItem().equals("Weight Maintenance")){
            
            adjustedDailyCalories = (activityAdjustedCalorieCalculation);
            return adjustedDailyCalories;
        }
        
        else {
        return errorCode;
        }
        
        
        
    }
}
