package com.skilldistillery.cards.blackjack;

import java.util.*;

public class Deck {

	private ArrayList<Card> cards;
	
	public Deck(){
		//Create a new deck of playing cards
		this.cards = new ArrayList<Card>();
	
	}
	
	//Adding 52 cards to the deck
	public void createFullDeck(){
	
		//Loop through Suits
		for(Suit cardSuit : Suit.values()){
			//Loop through Ranks
			for(Rank r : Rank.values()){
				
				this.cards.add(new Card(cardSuit,r));
			}
		}
	}
	
	
//Shuffle the deck
public void shuffle(){

	Collections.shuffle(this.cards);
}
	
	
	//Remove a card from the deck
	public void removeCard(int i){
		this.cards.remove(i);
	}
	
	//Get card from deck
	public Card getCard(int i){
		return this.cards.get(i);
	}
	
	
	//Add card to deck
	public void addCard(Card addCard){
		this.cards.add(addCard);
	}
	
	//Draw the top card from the deck
	public void draw(Deck comingFrom){
		
		//Add card to this deck from whatever deck its coming from
		this.cards.add(comingFrom.getCard(0));
		
		//Remove the card in the deck its coming from
		comingFrom.removeCard(0);
	}
	
	public String toString(){
		String Output = "";
		for(Card c : this.cards){
			Output += "\n" + c.toString();
		}
		return Output;
	}
	
	//method below will print out full deck
/*	public String toString(){
		String Output = "";
		int = 0;
		for(Card c : this.cards){
			Output += "\n" + c.toString();
			i++;
		}
		return Output;
	}*/
	
	public void movetoDeck(Deck moveTo){
		int thisDeckSize = this.cards.size();
		//put cards in moveTo deck
		for(int i = 0; i < thisDeckSize; i++){
			moveTo.addCard(this.getCard(i));
		}
		//empty out the deck
		for(int i = 0; i < thisDeckSize; i++){
			this.removeCard(0);
		}
	}
	
	public int deckSize(){
		return this.cards.size();
	}
	
	//Calculate the value of a hand
	public int cardsValue(){
		int totalValue = 0;
		int acecount = 0;
		//For every card in the deck
		for(Card c : this.cards){
			
			//Switch of possible values
			switch(c.getRank()){
			case TWO: totalValue += 2; 
			break;
			case THREE: totalValue += 3; 
			break;
			case FOUR: totalValue += 4; 
			break;
			case FIVE: totalValue += 5; 
			break;
			case SIX: totalValue += 6; 
			break;
			case SEVEN: totalValue += 7; 
			break;
			case EIGHT: totalValue += 8; 
			break;
			case NINE: totalValue += 9; 
			break;
			case TEN: totalValue += 10; 
			break;
			case JACK: totalValue += 10; 
			break;
			case QUEEN: totalValue += 10; 
			break;
			case KING: totalValue += 10; 
			break;
			case ACE: acecount += 1; break;
			}			
		}
		
		for(int i = 0; i < acecount; i++){
			if (totalValue > 10){
				totalValue += 1;
			}
			else{
				totalValue += 11;
			}
		}
		
		//Return
		return totalValue;
	
	}
	
	
}
