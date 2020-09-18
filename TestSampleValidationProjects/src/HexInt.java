import java.math.BigInteger;


public class HexInt {

	enum Color 
	{ 
	    RED, GREEN, BLUE; 
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println(Integer.decode("0xAA0F245C")) ;   // output 1234
		//and vice versa 
		//System.out.println(Integer.toHexString(1234)); //  output is 4d2);
		
		System.out.println(Long.parseLong("110183a2", 16)) ; 
		System.out.println(Long.toHexString(285311906)) ;
		
		

	}

}
