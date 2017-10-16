package com.skilldistillery.cards.blackjack;

public class Card {

	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank){
		this.suit = suit;
		this.rank = rank;
	}

	public String toString(){
		return this.suit.toString() + "-" + this.rank.toString();
	}

	public Rank getRank(){
		return this.rank;
	}



}


