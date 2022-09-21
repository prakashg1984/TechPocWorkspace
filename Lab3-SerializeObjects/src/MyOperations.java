
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MyOperations {
    
    public static void writeMyObject(String fileName, Object[] object) throws IOException{
        ObjectOutputStream outStream = null; 
        
        try{
            outStream = new ObjectOutputStream(new FileOutputStream(fileName)); 
            for(int i=0; i< object.length; i++){
                outStream.writeObject(object[i]); 
            }
            outStream.close();
        }catch(IOException e){ 
            throw e;
        }
    }
    
    public static void readMyObject(String fileName, Object[] object) throws IOException, ClassNotFoundException{
        ObjectInputStream inStream = null; 
        
        try{
            inStream = new ObjectInputStream(new FileInputStream(fileName));  
            for(int i=0; i< object.length ; i++){
                object[i] = inStream.readObject();
            }
            System.err.println("The following Objects are read from the specified file");
            System.err.println("_______________________________________________________");
            for(int i=0; i< object.length ; i++){
                System.err.println(object[i]);
            }
        }catch(IOException e){ 
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
      }
    }
}
