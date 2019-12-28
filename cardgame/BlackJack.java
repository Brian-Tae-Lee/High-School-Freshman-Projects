package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackJack implements Game{
	private ArrayList<Card> player_cards;
	private ArrayList<Card> dealer_cards;
	private Deck deckUsed;
	public BlackJack(){
		deckUsed = new Deck();
		player_cards = new ArrayList<Card>();
		dealer_cards = new ArrayList<Card>();
		setUp();
		System.out.println(goal());
		System.out.println("\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/*\\/");

	}
	public String goal() {
		String x =  "    In Blackjack, the goal of the game is to reach either 21,\n";
		x += "    or to get a card(hit) until you get close to the number.";
		x += "\n  If you do, then you can Stand, and see if your cards value is"
				+ "\n higher than the Dealer's. Be careful though, because if you go"
				+ "\n       over 21, you will go BUST! and immediately lose.";
		return x;
	}
	public void setUp() {
		System.out.println("/*\\/*\\/*\\/*\\/*\\/*\\/*\\/ - BEGIN THE GAME. - \\/*\\/*\\/*\\/*\\/*\\/*\\/*\\");
	}
	public void playGame() {
		Scanner kb = new Scanner(System.in);
		System.out.println("\t\t       WHAT IS YOUR NAME?");
		String pName = kb.nextLine();
		plays(pName);
	}
	private void plays(String pName){
		boolean playerwinner = false;
		boolean skip = false;
		Scanner kb = new Scanner(System.in);
		if(deckUsed.size()<8){
			System.out.println("The deck's getting pretty small. Do you want to reshuffle the cards? (Y/N?)");
			while(kb.hasNextLine()){
				String gA = kb.nextLine();
			if(gA.equalsIgnoreCase("Y") || gA.equalsIgnoreCase("yes")){
				deckUsed.shuffleFullDeck();
				plays(pName);
				break;
			}
			if(gA.equalsIgnoreCase("N") || gA.equalsIgnoreCase("no")){
				System.out.println("k. Bye.");
				return;
			}
			System.out.println("What are you saying? Do you want to reshuffle the cards?");
			}
		}
		player_cards.clear();
		dealer_cards.clear();
		player_cards.add(deckUsed.deal());
		player_cards.add(deckUsed.deal());
		dealer_cards.add(deckUsed.deal());
		dealer_cards.add(deckUsed.deal());
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("Dealers top Card:");
		System.out.println(dealer_cards.get(dealer_cards.size()-1));
		playerTurn();
		if(totalCards(player_cards) == 21 && player_cards.size()==2){
			playerwinner = true;
			System.out.println("BLACKJACK");
			gameOver(pName);
			skip = true;
		}
		if(totalCards(player_cards) == 21 && player_cards.size()>2){
			System.out.println("Your Cards:");
			showCards(player_cards);
			System.out.println("Point value total: " + totalCards(player_cards));
			playerwinner = true;
			skip = true;
			gameOver(pName);
		}
		if(totalCards(player_cards)>21){
			showCards(player_cards);
			System.out.println("Point value total: " + totalCards(player_cards));
			playerwinner = false;
			System.out.println("BUST!");
			System.out.println("You lose by default");
			skip = true;
		}
		if(!skip){
			dealerTurn();
			if(totalCards(dealer_cards) == 21){
				playerwinner = false;
			}
			if(playerwinner)
				gameOver(pName);
			System.out.println("Dealers cards:");
			showCards(dealer_cards);
			System.out.println("Value of Dealers cards: " + totalCards(dealer_cards));
			if(totalCards(player_cards)>totalCards(dealer_cards) && playerwinner)
				gameOver(pName);
			else if(totalCards(dealer_cards)>21 && totalCards(player_cards)<=21){
				System.out.println("Dealer BUST!");
				gameOver(pName);
			}else if(totalCards(player_cards)>totalCards(dealer_cards) && totalCards(player_cards)<=21){
				gameOver(pName);
			}else{
				gameOver("Dealer");
			}
		}
			System.out.println("Play again? (Y/N?)");
		while(kb.hasNextLine()){
			String gA = kb.nextLine();
				if(gA.equalsIgnoreCase("Y") || gA.equalsIgnoreCase("yes")){
					plays(pName);
					break;
				}
				if(gA.equalsIgnoreCase("N") || gA.equalsIgnoreCase("no")){
					System.out.println("k. Bye.");
					break;
				}
			System.out.println("What are you saying? Do you want to play again?");
			}
	}
	public void gameOver(String winner) {
		System.out.println(winner + " is the winner!");
	}
	public void showCards(ArrayList<Card> cards){
		for(Card x: cards)
			System.out.println(x);
	}
	public void sortCards(ArrayList<Card> cards){
		Collections.sort(cards);
		Collections.reverse(cards);
	}
	public int totalCards(ArrayList<Card> cards){
		int value = 0;
		int ace = 0;
		for(Card x: cards){
			if(x.getRank().equals("Ace"))
			ace++;
			value = value + x.getValue();
		}
		for(int i =0; i<ace; i++)
			if(value>21)
				value = value-10;
		return value;
	}
	public void playerTurn(){
		Scanner kb = new Scanner(System.in);
		System.out.println("Your Cards:");
		showCards(player_cards);
		System.out.println("Point value total: " + totalCards(player_cards));
		System.out.println("Hit or Stand?");
		while(kb.hasNextLine()){
			String yee = kb.nextLine();
		if(yee.toUpperCase().equals("HIT")){
			player_cards.add(deckUsed.deal());
			if(totalCards(player_cards)==21){
				return;
			}
			if(totalCards(player_cards)>21)
				return;
			playerTurn();
			return;
			}
		if(yee.toUpperCase().equals("STAND"))
			return;
		System.out.println("What?\nDo you want to hit, or nah?");
		}
	}
	public void dealerTurn(){
		while(totalCards(dealer_cards) < 17)
			dealer_cards.add(deckUsed.deal());
	}
	public static void main(String args[]){BlackJack x = new BlackJack();x.playGame();}
}