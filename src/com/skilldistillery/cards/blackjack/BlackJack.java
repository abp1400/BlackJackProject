package com.skilldistillery.cards.blackjack;

import java.util.*;

public class BlackJack {
	public void startGame() {
		
			
			Scanner kb = new Scanner(System.in);
			
			System.out.println("Welcome to the Fabulous Sands Casino Blackjack Table! \nPlease enter your name:");
			
			Player p1 = new Player();
			p1.name = kb.nextLine();
			p1.bet = 0; 
			p1.chipcount = 100;
			p1.hand = new Hand();
			Deck workingDeck = new Deck();
			workingDeck.createFullDeck();
			workingDeck.shuffle();
			
			Player dealer = new Player();
			
			dealer.hand = new Hand();
					
			
			
		
	while(p1.chipcount>0){
		
		
		System.out.println(p1.name + " has $ " + p1.chipcount + ", How much would you like to bet, "+p1.name + "?");
		p1.bet = kb.nextDouble();
		
		boolean roundOver = false;
		
		while(p1.bet > p1.chipcount){
			System.out.println("You cannot bet more than your current chip value, " +p1.name);
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
					
					//If there is an immediate player blackjack win
					if((p1.hand.cardsValue() ==21 && roundOver ==false)) {
						System.out.println("You hit blackjack! You win! Collect your chips, "+p1.name);
						p1.chipcount += p1.bet;
						roundOver = true;
						break;
					}
					
					//If there is an immediate dealer blackjack win
					else if((p1.hand.cardsValue() ==21 && roundOver ==false)) {
						
						System.out.println("Dealer Hand: " + dealer.hand.toString());
						System.out.println("Dealer hit blackjack! You lose!");
						p1.chipcount -= p1.bet;
						roundOver = true;
						break;
						//If there is a blackjack tie
				
				} else if((dealer.hand.cardsValue() == 21 && p1.hand.cardsValue() ==21 && roundOver == false)) {
					System.out.println("Dealer Hand: " + dealer.hand.toString());
					System.out.println("You both got 21! It's a tie round! Let's go again!");
							roundOver = true;
						}
					
					
					//Hit or Stand
					System.out.println("Would you like to Hit (1) or Stand (2)");
					int hitOrStand = kb.nextInt();	
					
					//They hit
					if(hitOrStand == 1){
						p1.hand.draw(workingDeck);
						System.out.println("You drew a: " + p1.hand.getCard(p1.hand.deckSize()-1).toString());
						//Bust if they go over 21
						if(p1.hand.cardsValue() > 21){
							System.out.println("You got " + p1.hand.cardsValue() + " points, you busted," + p1.name +"!");
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
				
				//If there is a blackjack tie
				if((dealer.hand.cardsValue() == 21 && p1.hand.cardsValue() ==21 && roundOver == false)) {
					System.out.println("You both got 21! It's a tie round! Let's go again!");
					roundOver = true;
				}
				
				//If there is a player blackjack win
				if((p1.hand.cardsValue() ==21 && roundOver ==false)) {
					System.out.println("You hit blackjack! You win! Collect your chips, "+p1.name);
					p1.chipcount += p1.bet;
					roundOver = true;
				}
				
				//If dealer wins by blackjack
				if((dealer.hand.cardsValue() ==21 && roundOver ==false)) {
					System.out.println("Dealer hit blackjack! You lose!");
					p1.chipcount -= p1.bet;
					roundOver = true;
					}
				
				
				//If dealer has more points than player
				if((dealer.hand.cardsValue() > p1.hand.cardsValue())&&dealer.hand.cardsValue() >=17 && roundOver == false){
					System.out.println("\nDealer got " + dealer.hand.cardsValue() + " points.");
					System.out.println("You got " + p1.hand.cardsValue() + " points. You lose!");
					p1.chipcount -= p1.bet;
					roundOver = true;
				}
				//Dealer will hit if under 17
				
				while((dealer.hand.cardsValue() < 17) && roundOver == false){
					dealer.hand.draw(workingDeck);
					System.out.println("Dealer draws: " + dealer.hand.getCard(dealer.hand.deckSize()-1).toString());
				}
				
	//			System.out.println("Value of dealer's hand is: " + dealer.hand.cardsValue());
				
				//If Dealer Busts
				if((dealer.hand.cardsValue()>21)&& roundOver == false){
					System.out.println("Dealer got " + dealer.hand.cardsValue() + " points.  He Busted! You win! \nCollect your chips, "+p1.name);
					p1.chipcount += p1.bet;
					roundOver = true;
				}
				//If it's a tie game
				if((dealer.hand.cardsValue() == p1.hand.cardsValue()) && roundOver == false){
					System.out.println("\nDealer got " + dealer.hand.cardsValue() + " points.");
					System.out.println("You got " + p1.hand.cardsValue() + " points.");
					System.out.println("It's a tie round, let's go again, "+ p1.name);
					roundOver = true;
				}
				//If player wins
				if((p1.hand.cardsValue() > dealer.hand.cardsValue()) && roundOver == false){
					System.out.println("\nDealer got " + dealer.hand.cardsValue() + " points.");
					System.out.println("You got " + p1.hand.cardsValue() + " points.");
					System.out.println("You win! Collect your chips, " +p1.name);
					p1.chipcount += p1.bet;
					roundOver = true;
				}
				else if(roundOver == false) //dealer wins
				{
					
					System.out.println("\nDealer got " + dealer.hand.cardsValue() + " points.");
					System.out.println("You got " + p1.hand.cardsValue() + " points. You lose!");
				//	System.out.println("Dealer wins.");
					p1.chipcount -= p1.bet;
				}

				//deck recycling method
				p1.hand.movetoDeck(workingDeck);
				dealer.hand.movetoDeck(workingDeck);
				workingDeck.shuffle();
				System.out.println("End of Round.");
				
			}
			//Game is over
			System.out.println("Game over! You have no more chips! See you next time, "+ p1.name);
			return;		
		}
			
	}
