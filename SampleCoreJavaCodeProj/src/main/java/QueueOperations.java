import java.util.Scanner;

public class QueueOperations {

	public static void main(String[] args) {  
	    int choice=0;  
	    Scanner sc = new Scanner(System.in);  
	    SampleQueueArray s = new SampleQueueArray();  
	    System.out.println("*********Queue operations using array*********\n");  
	    System.out.println("\n------------------------------------------------\n");  
	    while(choice != 4)  
	    {  
	        System.out.println("\nChose one from the below options...\n");  
	        System.out.println("\n1.Insert\n2.Delete\n3.Show\n4.Exit");  
	        System.out.println("\n Enter your choice \n");        
	        choice = sc.nextInt();  
	        switch(choice)  
	        {  
	            case 1:  
	            {   
	                s.insert(sc);  
	                break;  
	            }  
	            case 2:  
	            {  
	                s.delete();  
	                break;  
	            }  
	            case 3:  
	            {  
	                s.display();  
	                break;  
	            }  
	            case 4:   
	            {  
	                System.out.println("Exiting....");  
	                System.exit(0);  
	                break;   
	            }  
	            default:  
	            {  
	                System.out.println("Please Enter valid choice ");  
	            }   
	        };  
	    }  
	}  
}
