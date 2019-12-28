package goFish;

import java.util.*;

public class GoFishGame implements Game{
	private ArrayList<GoFishPlayer> players;
	private Deck deckUsed;
	public GoFishGame(){
		deckUsed = new Deck();
		players = new ArrayList<GoFishPlayer>();
		setUp();
		System.out.println(goal());
	}
	public String goal() {
		String x = "Go Fish is a card game. "
				+ "Each player gets five cards. If you are dealt a four of a kind, "
				+ "\nor get four of a kind during game play, "
				+ "\nthose cards are removed from your hand, and you get a point."
				+ "\nWhoever has the most when the pile runs out is the winner.";
		return x;
	}
	public void setUp() {
		System.out.println("It's GoFish. Play it.");
	}
	public void playGame() {
		Scanner kb = new Scanner(System.in);
		System.out.println("How many players? (3-5)");
		int times = kb.nextInt();
		while(times>6 || times<=2){
			if(times == 1)
				System.out.println("Wut. Forever alone? You have no friends?");
			else if(times>6)
				System.out.println("Can't be greater than 5!");
			else if(times<=2)
				System.out.println("Can't be less than 2!");
			times = kb.nextInt();
		}
		kb.nextLine();
		for(int k = 0; k<times; k++){
			String name = "";
			if(k==0){
				System.out.println("What is your name?");
				name = kb.nextLine();
				if(name.equalsIgnoreCase("Viraj"))
					throw new IllegalArgumentException("Viraj, you're not allowed to play.");
			}else{
				name = "Computer " + k;
			}
			ArrayList<Card> x = new ArrayList<Card>();
			for(int i = 0; i<5; i++)
				x.add(deckUsed.deal());
			players.add(new GoFishPlayer(name, x));
		}
		boolean human = true;
		int counter = 0;
		boolean firstTime = true;
		while(deckUsed.size()!=0){
			if(counter>=players.size())
				counter = 0;
			if(counter == 0)
				human = true;
			if(players.get(counter).isThereFour())
				players.get(counter).addFour();
			int pos = players.get(counter).play(human, players.size(), players);
			String chosencard = "";
			String[] rank = {"Ace","2","3","4","5","6","7","8","9","10","Jack","King","Queen"};
			if(counter == 0){
				System.out.println(players.get(pos) + ", do you have any _____(s) (Caps matter here!)");
				System.out.println("Card :");
				chosencard = kb.nextLine();
				while(!containscards(rank, chosencard) || !players.get(counter).hasCards(chosencard)){
					if(!players.get(counter).hasCards(chosencard))
						System.out.println("You don't have this card");
					else
						System.out.println("This is not a card.\nCard:");
					chosencard = kb.nextLine();
				}
			}else{
				chosencard = players.get(counter).pickRand();
				botSays(players.get(pos)+ ", do you have any " + chosencard + "s?");
			}
			ArrayList<Card> any = players.get(pos).giveCard(chosencard);
			if(pos == 0){
				String x = kb.nextLine();
				while((!x.equalsIgnoreCase("yes") && any.size()>=1)|| (x.equalsIgnoreCase("yes") && any.size() == 0)){
					System.out.println("You Filthy Liar.");
					x = kb.nextLine();
				}
				players.get(counter).addCard(any);
			}
			if(any.size()==0){
				if(pos != 0){
					System.out.print(players.get(pos).getName()); 
					botSays(": Sorry, I don't have any of those cards. Go Fish!");
				}
				Card temp = deckUsed.deal();
				if(human)
					System.out.println("Card Obtained from deck: " + temp);
				players.get(counter).addCard(temp);
				if(human)
					players.get(counter).showCards();	
			}
			if(any.size()>=1 && pos!=0){
					System.out.print(players.get(pos).getName() + ": ");
					botSays("Yes, i do.");
				players.get(counter).addCard(any);
			}
			if(players.get(counter).isThereFour())
				players.get(counter).addFour();
			counter++;
			if(firstTime == false && counter == players.size())
				showFourPairs();
			firstTime = false;
			human = false;
		}
		int x = getMax();
		if(x == 1000000)
			System.out.println("I don't know who wins, and i'm too lazy to actually find out who, since there is a tie.");
		else
			gameOver(players.get(x).getName());
	}
	private int getMax(){
		int max = players.get(0).howManyFours(), pos = 0;
		for(int i = 1; i<players.size(); i++){
			if(max<players.get(i).howManyFours()){
				pos = i;
				max = players.get(i).howManyFours();
			}
		}
		for(int i = 0; i<players.size(); i++)
			if(pos != i && max==players.get(i).howManyFours()){
				System.out.println("This is a rare Occurence.");
				return 1000000;
			}
		return pos;
	}
	private void showFourPairs(){
		System.out.println("\nThese are all the pairs that everyone has!");
		for(GoFishPlayer x: players)
			x.showFours();
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	public void gameOver(String winner) {
		int max = players.get(0).howManyFours(), pos = 0;
		for(int i = 1; i<players.size(); i++){
			if(max<players.get(i).howManyFours()){
				max = players.get(i).howManyFours();
				pos = i;
			}
		}
		System.out.println(players.get(pos).getName() + "is the winner!");
	}
	public void showCards(ArrayList<Card> cards){
		for(Card x: cards)
			System.out.println(x);
	}
	public int totalCards(ArrayList<Card> cards){
		int value = 0;
		return value;
	}
	private boolean containscards(String[] a, String x){
		for(int i = 0; i<a.length; i++)
			if(x.equals(a[i]))
				return true;
		return false;
	}
	private void botSays(String x){
		while(x.length() > 0){
			try {
				System.out.print(x.charAt(0));
				if(x.charAt(0)==',')
					Thread.sleep(600);
				else if(x.charAt(0)=='.')
					Thread.sleep(1200);
				else
					Thread.sleep(30);
				x = x.substring(1);
				if(x.length() == 0)
					Thread.sleep(2000);
			} catch (InterruptedException e) {}
		}
		System.out.println();
	}
	public static void main(String args[]){GoFishGame x = new GoFishGame();x.playGame();}
}