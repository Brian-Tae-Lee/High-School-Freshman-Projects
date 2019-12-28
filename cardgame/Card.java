package cardgame;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class Card implements Comparable{
	private String rank, suit;
	private int value;
	public Card(String r, String s, int v){
		rank = r;
		suit = s;
		value = v;
	}
	public String getRank(){
		return rank;
	}
	public String getSuit(){
		return suit;
	}
	public int getValue(){
		return value;
	}
	public boolean equals(Object x){
		Card c = (Card)x;
		return this.getValue() == c.getValue();
	}
	public boolean match(Object x){
		Card c = (Card)x;
		return this.getRank().equals(c.getRank());
	}
	public int compareTo(Object x) {
		Card c = (Card)x;
		return this.getValue()-c.getValue();
	}
	public String toString(){
		return rank + " of " + suit; 
	}
	public static void main(String[] args){
		String[] rank = {"2","3","4","5","6","7","8","9","10","Jack","King","Queen","Ace"};
		String[] suit = {"Clubs", "Spades", "Hearts", "Diamonds"};
		ArrayList<Card> a = new ArrayList<Card>();
		for(int i = 0; i<10; i++){
			int x = (int)(Math.random()*rank.length);
			int y = (int)(Math.random()*suit.length);
			a.add(new Card(rank[x], suit[y], x));
		}
		System.out.println(a);
	}
}
