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
public class Person {
    
    String gender;
    String firstName;
    String lastName;
    String goals;
    int age;
    int height;
    int weight;

    
    
    public Person(String gender,String goals, String firstName, String lastName, int height, int weight, int age) {
        this.gender = gender;
        this.goals = goals;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }
    public Person()
        { // Start of the default constructor for the Person class, which initialises the paramatised constructor
                this("Male", "Weight Loss", "Sam", "Sample",  21, 179, 95);
        } 

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

   

    @Override
    public String toString() {
        return  "\nGender: \t" + gender + "\nGoals: \t" + goals + "\nFirst Name: \t" 
                 + firstName + "\nLast Name: \t" + lastName + "\nHeight (cm): \t" 
                + height + "\nWeight (kg): \t" + weight + "\nAge: \t" + age + "\n\n";
                
    }
    
    
    
   
}
