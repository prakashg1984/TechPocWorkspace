
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author navpreetwaraich
 */
public class SerializeTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Person[] peopleWriteList = new Person[10];
	Person[] peopleReadList = new Person[10];
	
        peopleWriteList[0] = new Person("Giya","Gulati",34);
        peopleWriteList[1] = new Person("Syed","Gosal",23);
        peopleWriteList[2] = new Person("Syed","Armani",56);
        peopleWriteList[3] = new Person("Yuvi","Birk",12);
        peopleWriteList[4] = new Person("Mark","Berry",44);
        peopleWriteList[5] = new Person("Joe","Fernandes",20);
        peopleWriteList[6] = new Person("Mark","Master",89);
        peopleWriteList[7] = new Person("Joe","Eules",32);
        peopleWriteList[8] = new Person("Alex","Wright",41);
        peopleWriteList[9] = new Person("Alex","Bill",62);
        
        try{
            MyOperations.writeMyObject("person.txt", peopleWriteList);
            System.out.println("The write operation is successful and the objects are written to the file\n");
        }catch(IOException e){
            System.out.println("The Objects are not written to the specified file");
        }
        
        try{
            MyOperations.readMyObject("person.txt", peopleReadList);
        }catch(Exception e){
            System.out.println("The Objects can't be read from the specified file");
        }
        
    }
    
}
