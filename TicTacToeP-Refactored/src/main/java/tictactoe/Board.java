package tictactoe;

/**
 * Object to Hold the Information of the Board
 * 
 *
 */
public class Board {

	private static char freshBoard[] = {'0','|','1','|','2','\n','-','+','-','+','-','\n','3','|','4','|','5','\n','-','+','-','+','-','\n','6','|','7','|','8','\n'};
	private char board[];
    private static int posToIndex[] = { 0, 2, 4, 12, 14, 16, 24, 26, 28 };
    
    
    public Board() {
    	board = new char[freshBoard.length];
    	for(int i=0;i<freshBoard.length;i++) {
    		board[i] = freshBoard[i];
    	}
    }
    
	public boolean checkBoard(int position) {
		if (board[posToIndex[position]] == 'X' || board[posToIndex[position]] == 'O') {
			return true;
		}
		return false;
	}
	
	public boolean winner() {
		
		boolean winnerFound = false;
		
		//If there is a Match Vertically
	    for (int i = 0; i < 3; i++){
	      if (board[posToIndex[i]] == board[posToIndex[i+3]] && board[posToIndex[i+3]]== board[posToIndex[i+6]]) {
	        winnerFound= true;
	        break;
	      }
	    }
	    
	    //If there is a Match Diagonally
	    if(winnerFound == false){
	      if (board[posToIndex[0]] == board[posToIndex[4]] && board[posToIndex[4]] == board[posToIndex[8]]) {
	    	  winnerFound= true;
	      }
	      if (board[posToIndex[2]] == board[posToIndex[4]] && board[posToIndex[4]] == board[posToIndex[6]]) {
	    	  winnerFound= true;
	      }
	      
	    }
	    
	  //If there is a Match Horizontally
	    if(winnerFound == false){
	      for (int i = 0; i <= 6; i+=3){
	        if (board[posToIndex[i]] == board[posToIndex[i+1]] && board[posToIndex[i+1]] == board[posToIndex[i+2]]) {
	          winnerFound= true;
	          break;
	        }
	      }
	    }

	  return winnerFound;
	}
	
	/**
	 * Print the Current Board Array
	 */
	public char[] printBoard() {
		return board;
		//System.out.println(board);
	}
	
	public int getBoardLength() {
		return board.length;
	}
	
	public int getPositionLength() {
		return posToIndex.length;
	}
	
	public void setPlayer(int position, Player p) {
		board[posToIndex[position]] = p.getPlayerName();
	}
	
}
