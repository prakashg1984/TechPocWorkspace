package tictactoe;

import java.util.Scanner;

public class Runner {
 
  private static Scanner input = new Scanner(System.in);

   /**
   * Starting method
   * @param args
   */
	public static void main(String[] args) {
		//Create a new Game Object
		
		//boolean startGame = true;
		
		//while(startGame) {
			Game game = new Game();
			
			//Start the game
			game.beginGame(input);
			
			/*
			 * System.out.println("Do you want to continue - Enter YES or NO "); String
			 * userInput = input.next();
			 * 
			 * if(userInput.equalsIgnoreCase("YES")){ startGame = true; }else { startGame =
			 * false; }
			 */
		//}
		
		
		
	}
}
