package goFish;

import java.util.ArrayList;

public class Deck {
	private Card[] deck;
	private int size;
	public Deck(){
		deck = new Card[52];
		size = 52;
		String[] rank = {"Ace","2","3","4","5","6","7","8","9","10","Jack","King","Queen"};
		String[] suit = {"Clubs", "Spades", "Hearts", "Diamonds"};
		int[] value = {11,2,3,4,5,6,7,8,9,10,10,10,10};
		int p = 0;
		for(int s = 0; s<suit.length; s++)
			for(int r = 0; r<rank.length; r++){
				deck[p] = new Card(rank[r], suit[s], value[r]);
				p++;
		}
		shuffle();
	}
	public Deck(String[] rank, String[] suit, int[] val){
		deck = new Card[suit.length*rank.length];
		size = suit.length*rank.length;
		int p = 0;
		for(int s = 0; s<suit.length; s++)
			for(int r = 0; r<rank.length; r++){
			deck[p] = new Card(rank[r], suit[s], val[r]);
			p++;
		}
		shuffle();
	}
	public void shuffle(){
		for(int i = 0; i < 500; i++){
			int x = (int)(Math.random()*size);
			int y = (int)(Math.random()*size);
			Card temp = deck[x];
			deck[x] = deck[y];
			deck[y] = temp;
		}
	}
	public void shuffleFullDeck(){
		size = deck.length;
		shuffle();
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public String toString(){
		String ret = "";
		for(int i = 0; i<size; i++)
			ret = ret + deck[i];
		return ret;
	}
	public void showDiscardeddeck(){
		System.out.println("Discarded:");
		for(int i = size; i<deck.length; i++)
			System.out.print(deck[i]);
		if(size == deck.length)
			System.out.println("none");
	}
	public ArrayList<Card> deal(int n){
		if (n > size)
			throw new IllegalArgumentException("Deal too big!");
		if(deck.length == 0)
			return null;
		ArrayList<Card> DiscardPile = new ArrayList<Card>();
		for(int i = size-1; i > size-1-n; i--){
			DiscardPile.add(deck[i]);
		}
		size = size-n;
		return DiscardPile;
	}
	public Card deal(){
		if(deck.length == 0)
			return null;
		size = size-1;
		return deck[size];
	}
	public static void main(String[] args){
		Deck memes = new Deck();
		System.out.println(memes);
		System.out.println("---------------------------------------------");
		memes.shuffle();
		System.out.println(memes);
		System.out.println("-----------------------------------===----------");
		Card t = memes.deal();
			System.out.println(t);
		System.out.println(memes);
		//----------------------------------------------------------------------
		String[] ranks = {"jack", "queen", "king"}; 
		String[] suits = {"blue", "red"}; 
		int[] pointValues = {11, 12, 13}; 
		Deck d = new Deck(ranks, suits, pointValues);
		System.out.println(d);
		d.shuffle();
		System.out.println("\nSpace\n");
		System.out.println(d);
		System.out.println("\nSpace2\n");
		d.deal();
		System.out.println(d);
		System.out.println("\nSpace\n");
		d.deal(4);
		System.out.println(d);
		System.out.println("\nSpace\n");
		d.showDiscardeddeck();
	}
}
