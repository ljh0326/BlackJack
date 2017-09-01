package PokerGame2;

public class Card {

	static final int KIND_MAX = 4;
	static final int NUM_MAX = 14;

	static final int SPADE = 4;
	static final int DIAMOND = 3;
	static final int HEART = 2;
	static final int CLOVER = 1;

	int kind; 
	int num; 

	Card() {
	}

	Card(int kind, int num) {
		this.kind = kind;
		this.num = num;
	}

	public String toString() {
		String[] kinds = { "¢À", "¢¾", "¡ß", "¢¼" };
		String numbers = "0A23456789XJQK";
		return kinds[this.kind] + " " + numbers.charAt(this.num);
	}
}