package tictactoe;

/**
 * Object to hold Player information
 * 
 *
 */
public class Player {

	private char playerName;

	public Player(char player) {
		setPlayerName(player);
	}
	
	public char getPlayerName() {
		return playerName;
	}

	public void setPlayerName(char playerName) {
		this.playerName = playerName;
	}
}
