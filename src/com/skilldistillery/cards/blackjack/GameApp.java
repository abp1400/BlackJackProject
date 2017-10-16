package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

public class GameApp {

		public static void main(String[] args){
			
			System.out.println("Welcome to the Fabulous Sands Casino Blackjack Table! Please enter your name!");
			
			Deck workingDeck = new Deck();
			workingDeck.createFullDeck();
			workingDeck.shuffle();
			
			Player p1 = new Player();
			Player dealer = new Player();
			p1.bet = 0; 
			p1.chipcount = 100;
			p1.hand = new Hand();
			dealer.hand = new Hand();
					
			Scanner kb = new Scanner(System.in);
			
		
	while(p1.chipcount>0){
		
		
		System.out.println("You have $" + p1.chipcount + ", How much would you like to bet?");
		p1.bet = kb.nextDouble();
		
		boolean roundOver = false;
		
		while(p1.bet > p1.chipcount){
			System.out.println("You cannot bet more than your current chip value.");
			System.out.println("Please enter a lower bet: ");
			p1.bet = kb.nextDouble();}
		
		System.out.println("Dealer is dealing the cards\n\n");
		
		//Player draws two cards
		
		p1.hand.draw(workingDeck);
		p1.hand.draw(workingDeck);
		
		//Dealer draws two cards
		dealer.hand.draw(workingDeck);
		dealer.hand.draw(workingDeck);

				
	while(true)
				{
					//Display player cards
					System.out.println("Your Hand:" + p1.hand.toString());
					
					//Display Value
					System.out.println("Your hand is currently valued at: " + p1.hand.cardsValue());
					
					//Display dealer cards
					System.out.println("Dealer Hand: " + dealer.hand.getCard(0).toString() + " and [hidden]");
					
					//Hit or Stand
					System.out.println("Would you like to Hit (1) or Stand (2)");
					int hitOrStand = kb.nextInt();	
					
					//They hit
					if(hitOrStand == 1){
						p1.hand.draw(workingDeck);
						System.out.println("You draw a:" + p1.hand.getCard(p1.hand.deckSize()-1).toString());
						//Bust if they go over 21
						if(p1.hand.cardsValue() > 21){
							System.out.println("Bust. Currently valued at: " + p1.hand.cardsValue());
							p1.chipcount -= p1.bet;
							roundOver = true;
							break;
						}
					}
					//Stand
					if(hitOrStand == 2){
						break;
					}		
				}
					
				//Reveal Dealer Cards
				System.out.println("Dealer Cards:" + dealer.hand.toString());
				//See if dealer has more points than player
				if((dealer.hand.cardsValue() > p1.hand.cardsValue())&&roundOver == false){
					System.out.println("Dealer got " + dealer.hand.cardsValue() + " points.");
					System.out.println("You got " + p1.hand.cardsValue() + " points. You lose!");
					p1.chipcount -= p1.bet;
					roundOver = true;
				}
				//Dealer will hit if under 17
				
				while((dealer.hand.cardsValue() < 17) && roundOver == false){
					dealer.hand.draw(workingDeck);
					System.out.println("Dealer draws: " + dealer.hand.getCard(dealer.hand.deckSize()-1).toString());
				}
				

				System.out.println("Value of dealer's hand is: " + dealer.hand.cardsValue());
				
				//If Dealer Busts
				if((dealer.hand.cardsValue()>21)&& roundOver == false){
					System.out.println("Dealer Busts. You win!");
					p1.chipcount += p1.bet;
					roundOver = true;
				}
				//If it's a tie game
				if((dealer.hand.cardsValue() == p1.hand.cardsValue()) && roundOver == false){
					System.out.println("It's a tie, let's go again!");
					roundOver = true;
				}
				//If player wins
				if((p1.hand.cardsValue() > dealer.hand.cardsValue()) && roundOver == false){
					System.out.println("You win! Collect your chips!");
					p1.chipcount += p1.bet;
					roundOver = true;
				}
				else if(roundOver == false) //dealer wins
				{
					System.out.println("Dealer wins.");
					p1.chipcount -= p1.bet;
				}

				//deck recycling method
				p1.hand.moveAllToDeck(workingDeck);
				dealer.hand.moveAllToDeck(workingDeck);
				//playingDeck.shuffle();
				System.out.println("End of Hand.");
				
			}
			//Game is over
			System.out.println("Game over! You have no more chips! Go home!");
			return;		
		}
			
	}
