package com.skilldistillery.blackjack.app;

import java.util.Scanner;

import com.skilldistillery.cardgames.entities.*;

public class BlackJackApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private void launch() {
		Scanner kb = new Scanner(System.in);
		Dealer dealer = new Dealer(); 
		System.out.println("You approach the BlackJack table in the back corner.");
		System.out.println("There are no other players at the table right now, and the man behind the table looks bored.");
		System.out.println("The dealer looks up from the deck in his hands, which he has been shuffeling haphazardly");
		System.out.println("Want me to deal you in? He asks gruffly.");
		System.out.println("(yes or no)");
		try {
			String userReply = kb.nextLine();
			userReply = userReply.toLowerCase();
			while(userReply.startsWith("y")) {
				Deck deck = dealer.getNewDeck();
				
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
