package com.skilldistillery.cards.blackjack;

public class Player {
	String name;
	Hand hand;
	double chipcount;
	double bet;
	
	public Player() {}

	public Player(String name, Hand hand, double chipcount, double bet) {
		super();
		this.name = name;
		this.hand = hand;
		this.chipcount = chipcount;
		this.bet = bet;
	}
	
	

}
