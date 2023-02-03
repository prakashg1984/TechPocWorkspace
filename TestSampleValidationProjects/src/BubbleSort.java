import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int input[] ={3,60,35,2,45,320,5};  
        sortArray(input);
        for(int i=0; i < input.length; i++){  
            System.out.print(input[i] + " ");  
        } 
	}
	
	public static void sortArray(int[] input){
		
        int temp = 0;  
		for (int i = 0; i < input.length; i++) {
			for (int j = 1; j < input.length; j++) {
				if (input[j - 1] > input[j]) {
					temp = input[j - 1];
					input[j - 1] = input[j];
					input[j] = temp;
				}
			}
			for(int k=0; k < input.length; k++){  
	            System.out.print(input[k] + " ");  
	        } 
			System.out.println("\n");
		}
	}

}
