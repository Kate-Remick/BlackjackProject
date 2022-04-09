package com.skilldistillery.cardgames.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends PersonAtTable{
	
	@Override
	public String toString() {
		return "Dealer";
	}

	public Dealer() {
		this.numTokens = 1000000;
		
	}
	
	public Map<PersonAtTable, Hand> dealCards(Deck deck, PersonAtTable dealer, List <PersonAtTable> players) {
		deck.shuffleDeck();
		HashMap<PersonAtTable, Hand> cardsOnTable = new HashMap<>();
		//cards on table mapped with each play and their hand
		for (PersonAtTable player : players) {
			cardsOnTable.put(player, player.newHand(deck.dealCard(2)));
		}
		cardsOnTable.put(dealer, dealer.newHand(deck.dealCard(2)));
//		System.out.println(cardsOnTable); //Shows each hand 
		
		return cardsOnTable;
	}
	
	public Deck getNewDeck() {
		System.out.println("The dealer reaches below the table and pulls out a fresh deck of cards.");
		return new Deck();
	}

	@Override
	public Map<PersonAtTable, Hand> Hit(Map<PersonAtTable, Hand> cardsOnTable, Deck deck, PersonAtTable person) {
		cardsOnTable.get(person).getCardsInHand().addAll(deck.dealCard(1));
		System.out.println("The dealer deals himself a card");
		
		return cardsOnTable;
	}


	@Override
	public Hand newHand(List<Card> cardsDealt) {
		Hand dh = new Hand(cardsDealt);
		System.out.println("The dealer deals himself in and shows you the second card: ");
		System.out.println("It is a " + cardsDealt.get(0));
		
		return dh;
	}

}
