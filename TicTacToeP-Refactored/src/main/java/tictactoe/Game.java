package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * Object to hold the Information about the Game
 * 
 *
 */
public class Game {

	private Player currentPlayer;
	
	
	/**
	 * 
	 * @param player1
	 * @param player2
	 */
	private void setNextTurn(Player player1, Player player2) {
		//If Current player is X, then next player is O
		if (null == currentPlayer || currentPlayer == player2) {
			currentPlayer = player1;
		} else {
			currentPlayer = player2;
		}
	}
	
	/**
	 * Method to start the Game
	 * @param input
	 */
	public void beginGame(Scanner input) {

		boolean startGame = true;
		
		while(startGame) {
			Board board = new Board();
			Player playerX = new Player('X');
			Player playerO = new Player('O');

			// Print the initial board
			System.out.println(board.printBoard());
			int position;
			int depth = 0;

			// Set the Intial Player
			setNextTurn(playerX, playerO);
			
			System.out.println("Turn = " + currentPlayer.getPlayerName());

			// Loop through till a winner is found or all 9 turns over
			while (board.winner() == false && depth < 9) {
				System.out.print("Enter Any Position between 0 and 8:\n");
				position = input.nextInt();

				if (position > board.getPositionLength() - 1) {
					System.err.println("Error - You should only enter between 0 and 8");
					System.out.println("\n");
				} else {
					//To check if a Position is already taken
					if (board.checkBoard(position)) {
						System.err.println("Illegal Move! This position is already taken by another Player");
						System.out.println("\n");
					} else {
						depth++;
						board.setPlayer(position, currentPlayer);
						board.printBoard();
						if (board.winner() == false) {
							//If wineer is not found, set the Next Player Turn
							setNextTurn(playerX, playerO);
							if (depth != 9) {
								System.out.println("Turn = " + currentPlayer.getPlayerName());
							}
						}

					}
				}
			}

			if (board.winner() == true) {
				System.out.println("CONGRATZ! The winner is " + currentPlayer.getPlayerName());
			} else if (depth == 9) {
				System.out.println("The Game was a Tie!");
			}
			
			System.out.println("Do you want to continue - Enter YES or NO ");
			String userInput = input.next();
			
			if(userInput.equalsIgnoreCase("YES")){
				startGame = true;
			}else {
				startGame = false;
			}
		}
		

	}
}
