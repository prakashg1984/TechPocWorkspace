/**
 * Find HCF of N Numbers
 * @author PGPS
 *
 */
public class FindHCF {

	public static void main(String[] args) {
		int[] numbers = {35,140,70,10};
        System.out.println("*** Greatest Common Divisor ***");
        System.out.println("GCD(Greatest Common Divisor) of N numbers using Associative law and euclid's method");
        System.out.println(findHCFofNNumbers(4,numbers));

	}
	
	
	public static int findHCFofNNumbers(int num,int[] numbers) {
        int finalHCF=1;
        int index=2;
        if(num==1){
        	finalHCF=numbers[1];
        }
        if(num>1){
        	finalHCF=hcfOfTwoNumbers(numbers[0],numbers[1]); 
        }
        while(index<num){
        	finalHCF=hcfOfTwoNumbers(finalHCF,numbers[index]);
            index++;
        }
        return finalHCF;
    }
	
	
	public static int hcfOfTwoNumbers(int num1,int num2){
        int temp=0;
        while(num2!=0){
            temp=num2;
            num2=num1%num2;
            num1=temp;
        }
        num1=num1<0 ? num1 * (-1):num1;
        return num1;
    }

}
