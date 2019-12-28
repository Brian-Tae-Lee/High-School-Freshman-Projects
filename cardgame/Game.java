package cardgame;

public interface Game {
	String goal();
	void setUp();
	void playGame();
	void gameOver(String winner);
}
