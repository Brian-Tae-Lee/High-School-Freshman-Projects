package mancala;

import java.util.Scanner;

public class Mancala {
	private static final int BOARD_SIZE = 14;
	private static final int START_SEEDS=4;
	private int[] board;
	public Mancala(){
		board = new int[BOARD_SIZE];
		for(int i = 0; i<board.length; i++)
			board[i] = START_SEEDS;
		board[0] = 0;
		board[7] = 0;
		drawBoard();
	}
	public boolean move(int k)	{
		int pos = k;
		int numinhole = board[k];
		board[k] = 0;
		for(int i = 1; i<=numinhole; i++){
			if(pos>=13){
				if(whichside(k)==7 && pos==13){
					pos=1;
					board[1]++;
				}else{
					pos = 0;
				if(numinhole==i){
					board[pos]++;
					return true;
				}
				board[pos]++;
				}
			}else{
				if(whichside(k)==0 && pos==6)
					pos++;
				pos++;
				board[pos]++;
			}
			if(pos == 7 && numinhole==i)
				return true;
			if(numinhole==i && board[pos]==1 && board[acrossfrom(pos)]!=0){
				int x = 1 + board[acrossfrom(pos)];
				if(whichside(k)==7 && (pos>=1 && pos<=7)){
					board[acrossfrom(pos)] = 0;
					board[pos] = 0;
					board[7] += x;
				}else if(whichside(k) == 0 && (pos>=8 && pos<=13)){
					board[acrossfrom(pos)] = 0;
					board[pos] = 0;
					board[0] += x;
					}
				}
			}
		return false;
	}
	public int acrossfrom(int i){
		return (14 - i);
	}
	public int whichside(int n){
		if(n>=0 && n<=6)
			return 7;
		return 0;
	}
	public void drawBoard()	{
		System.out.println(" _______________________________");
		System.out.println("|\t\t" + board[0] + "\t\t|");
		System.out.println("|_______________________________|");
		int o = 1;
		int t = 13;
		for(int i = 0; i<6;i++){
			System.out.println("|\t" + board[t] + "\t|\t" + board[o] + "\t|");
			o++;
			t--;
			}
		System.out.println("|_______________________________|");
		System.out.println("|\t\t"+ board[7] + "\t\t|");
		System.out.println("|_______________________________|");
		}
	
	public void runGame()	{
		Scanner kb = new Scanner(System.in);
		boolean turn = true; //if true, it is player 1's turn. If false, it's player 2's.
		System.out.println("Note: The right side (from top to bottom) is 1-6.\nThe left side (from bottom to top) is 8-13."); 
		System.out.println("Enable Bot? Y or N?");
		String bot = kb.nextLine();
		boolean dbot = false;
		if(bot.toUpperCase().equals("Y")){
			dbot = true;
			System.out.println("Bot has been enabled.");
		}
		while((board[1]!=0 || board[2]!=0 || board[3]!=0 || board[4]!=0 || board[5]!=0 || board[6]!=0) && (board[13]!=0 || board[12]!=0 || board[11]!=0 || board[10]!=0 || board[9]!=0 || board[8]!=0)){
		if(turn)
			System.out.println("Player 1's turn!");
		else{
			System.out.println("Player 2's turn!");
		}
		int pos = 0;
		if(!dbot && !turn){
			pos = kb.nextInt();
			while(pos>=14 || pos<=7){
				System.out.println("Wat.Choose a number 8-13 please.");
				pos = kb.nextInt();
			}
			kb.nextLine();
		}
		if(turn){
		pos = kb.nextInt();
		while(pos>=7 || pos<=0){
			System.out.println("Wat.Chose a number 1-6 please.");
			pos = kb.nextInt();
		}
		kb.nextLine();
		}
		if(dbot && !turn){
			System.out.println("(Please do not interupt the Bot. He is thinking.)");
			System.out.print("Bot is Thinking");
			try {
				int x = (int)(Math.random()*4000)+1000;
				Thread.sleep(x);
				System.out.print(" . ");
				x = (int)(Math.random()*2000)+1000;
				Thread.sleep(x);
				System.out.print(". ");
				x = (int)(Math.random()*2000)+1000;
				Thread.sleep(x);
				System.out.println(". ");
				x = (int)(Math.random()*2000)+1000;
				Thread.sleep(x);
			}
			catch (InterruptedException ex){}
			pos = dumbBot();
			while(board[pos]==0)
				pos = dumbBot();
			boolean goagain = move(pos);
			while(goagain){
				drawBoard();
				goagain = move(pos);
				}
			int SayWhat = (int)(Math.random()*5)+1;
			if(SayWhat==2)
				System.out.println("Bot: I am an AI. I will take over the world.");
			else if(SayWhat==3)
				System.out.println("Bot: You will lose.");
			else
				System.out.println("Bot: I will win.");
			if(SayWhat==1 && board[0]+10<=board[7])
				System.out.println("Bot: Could i lose?");
			drawBoard();
			turn = !turn;
		}else{
			boolean goagain = move(pos);
		while(goagain){
			drawBoard();
			System.out.println("It's still your turn. Go again.");
			pos = kb.nextInt();
			kb.nextLine();
			goagain = move(pos);
			}
		turn = !turn;
		drawBoard();
		}
		}
		if(board[0] > board[7])
			System.out.println("Player 2 wins!");
		else if(board[0]==board[7])
			System.out.println("Tie.");
		else
			System.out.println("Player 1 wins!");
	}
	public int dumbBot(){
		int x = ((int)(Math.random()*6)+8);
		return x;
	}
	public static void main(String[] args){
		Mancala x = new Mancala();
		x.runGame();
	}
}