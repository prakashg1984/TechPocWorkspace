
public class SearchWordInGrid {

	public static void main(String[] args) {

		char grid[][] = {"GEKSFORGEEKS".toCharArray(), 
						 "GEEKSQUIZGEEK".toCharArray(), 
						 "IDEEAPRACTICE".toCharArray()
               }; 
		String s= "";

	    for(int i=0;i<grid.length;i++){
	        for(int j=0; j<grid[i].length;j++){
	            s+= grid[i][j];
	        }
	        s+="\n";
	    }
	    System.out.println(s);
	    //patternSearchVertical(grid,"EE");
	    String word = "EE";
	    
	    System.out.println(grid.length);
	    
	   boolean success =  (verticalContains(grid, word)
        || horizontalContains(grid, word)
        || diagonalContains(grid, word));
	   System.out.println(success);
	}
	
	private static void patternSearchVertical(char grid[][], String data){
		char[] letters = data.toCharArray();
		for (int i = 0; i < grid.length; i++){
		    for (int j = 0; j < grid[i].length; j++) {
		        boolean found = true;

		        for (int k = 0; k < letters.length; k++) {
		            if ((j+k >= grid[i].length) || (letters[k] != grid[i][j+k])	) {
		                found = false;
		                break;
		            }
		        }

		        if (found) {
		            System.out.println("String " + data + " found in row=" + i + " col=" +j);
		        }
		     }
		  } 
	}
	
	private static boolean verticalContains(char[][] grid, String word) {
	    for (char[] row : grid) {
	        if (new String(row).contains(word)) {
	            return true;
	        }
	    }
	    return false;
	}

	private static boolean horizontalContains(char[][] grid, String word) {
	    int wordLength = word.length();
	    int max = grid.length - wordLength;
	    char[] wordArray = word.toCharArray();
	    for (int i = 0, length = grid[0].length; i < length; i++) {
	        loop: for (int j = 0; j < max; j++) {
	            for (int k = j; k < wordArray.length; k++) {
	                if (wordArray[k - j] != grid[k][i]) {
	                    continue loop;
	                }
	            }
	            return true;
	        }
	    }
	    return false;
	}

	private static boolean diagonalContains(char[][] grid, String word) {
	    int wordLength = word.length();
	    char[] wordArray = word.toCharArray();
	    for (int i = 0, length = grid.length; i < length; i++) {
	        loop: for (int j = 0, k = i, subLength = grid[i].length;
	                j < subLength && k >= wordLength; j++, k--) {
	            for (int l = 0; l < wordLength; l++) {
	                if (grid[j + l][k - l] != wordArray[l]) {
	                    continue loop;
	                }
	                return true;
	            }
	        }
	    }
	    return false;
	}


}
