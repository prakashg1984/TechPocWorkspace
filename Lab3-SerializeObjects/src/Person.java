
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author navpreetwaraich
 */
public class Person {
    private int age;
    private String firstName, lastName;
    
    public Person(String f, String l, int a){
        this.firstName = f;
        this.lastName = l;
        this.age = a;
    }
    public int getAge(){
        return age;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String toString(){
        return this.getFirstName() + " " + this.getLastName() + " aged " + this.getAge();
    }
}
