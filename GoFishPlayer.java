package goFish;
import java.util.*;

public class GoFishPlayer{
	private String name;
	private ArrayList<Card> cards;
	private ArrayList<fourRow> fR;
	private static int inc = -1;
	private int pos;
	
	public void addFour(){
		ArrayList<Card> fours = new ArrayList<Card>();
		Card comp = null;
		for(int i = 0; i<cards.size(); i++){
			comp = cards.get(i);
			int count = 0;
			for(int j = cards.size()-1; j>=0; j--){
				if(comp.match(cards.get(j))){
					count++;
				}
			}
			if(count == 4){
				for(int j = cards.size()-1; j>=0; j--)
					if(comp.match(cards.get(j))){
						fours.add(cards.get(j));
						cards.remove(j);
					}
			}
		}
		System.out.println(fours);
		fourRow x = new fourRow(fours);
		fR.add(x);
	}
	public boolean isThereFour(){
		Card comp = null;
		for(int i = 0; i<cards.size(); i++){
			comp = cards.get(i);
			int count = 0;
			for(int j = 0; j<cards.size(); j++){
				if(comp.match(cards.get(j))){
					count++;
				}
			}
			if(count == 4){
				return true;
			}
		}
		return false;
	}
	public int play(boolean human, int numPlayers,ArrayList<GoFishPlayer> a){
		Scanner kb = new Scanner(System.in);
		if(human){
			showCards();
			System.out.println("\nWho do you want to ask cards from?");
			for(int i = 1; i<a.size(); i++)
				System.out.println(a.get(i));
			String x = kb.nextLine();
			char trans = x.charAt(x.indexOf(" ")+1);
			int y = Character.getNumericValue(trans);
			while(y>=numPlayers || y<=0){
				System.out.println("you're not picking an actual player...");
				x = kb.nextLine();
				trans =  x.charAt(x.indexOf(" ")+1);
				y = Character.getNumericValue(trans);
			}
			return y;
		}
		int ret = (int)(Math.random()*numPlayers);
		while(ret == pos)
			ret = (int)(Math.random()*numPlayers);
			try {
				System.out.print("\n" + name + " is Thinking");
				Thread.sleep((long)(Math.random()*3000)+1000);
				System.out.print(" .");
				Thread.sleep((long)(Math.random()*3000)+1000);
				System.out.print(" .");
				Thread.sleep((long)(Math.random()*3000)+1000);
				System.out.println(" .");
				Thread.sleep((long)(Math.random()*3000)+1000);
			} catch (InterruptedException e) {}
		return ret;
	}
	public GoFishPlayer(String x, ArrayList<Card> a){
		cards = new ArrayList<Card>();
		fR = new ArrayList<fourRow>();
		name = x;
		cards = a;
		inc++;
		pos = inc;
	}
	public int howManyFours(){
		return fR.size();
	}
	public void showFours(){
		if(howManyFours() >=1){
			System.out.println(name + " has " + howManyFours() + " pair(s) of 4 cards. They are:");
			for(fourRow z: fR)
				System.out.print(z.getPName() + "\t");
		}
		else
			System.out.println(name + " has no pairs of 4!");
	}
	public void addCard(Card x){
		cards.add(x);
	}
	public void addCard(ArrayList<Card> x){
		for(Card y: x){
			System.out.println("\nCards Received: " + y);
			cards.add(y);
		}
	}
	public String getName(){
		return name;
	}
	public void showCards(){
		System.out.println("Your Cards: " );
		System.out.print(cards);
	}
	public ArrayList<Card> giveCard(String x){
		ArrayList<Card> ret = new ArrayList<Card>();
		for(int i = cards.size()-1; i>=0; i--)
			if(x.equals(cards.get(i).getRank())){ 
				ret.add(cards.get(i));
				cards.remove(i);
			}
		return ret;
	}
	public String toString(){
		return name;
	}
	public boolean hasCards(String a){
		for(Card x: cards)
			if(x.getRank().equalsIgnoreCase(a))
				return true;
		return false;
	}
	public String pickRand(){
		int ran = (int)(Math.random()*cards.size());
		return cards.get(ran).getRank();
	}
}
class fourRow{
	private Card[] card;
	public fourRow(ArrayList<Card> c){
		card = new Card[4];
		for(int i = 0; i<4; i++)
			card[i] = c.get(i);
	}
	public String getPName(){
		return card[0].getRank();
	}
	public String toString(){
		String ret = "";
		for(Card x: card)
			ret += x+ "\n";
		return ret;
	}
}