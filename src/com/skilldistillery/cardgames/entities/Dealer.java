package com.skilldistillery.cardgames.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends PersonAtTable{
	private Hand dealerHand;
	
	
	public Dealer() {
		
		
	}
	
	private Map dealCards(Deck deck, Dealer dealer, Player player) {
		deck.shuffleDeck();
		Map<> cardsOnTable = new HashMap();
		//cards on table mapped with each play and their hand
		cardsOnTable.put(player, );
		cardsOnTable.put(dealer, );
		
		
		return null;
	}
	
	public Deck getNewDeck() {
		System.out.println("The dealer reaches below the table and pulls out a fresh deck of cards.");
		return new Deck();
	}

	@Override
	public Card Hit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Stand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hand newHand(List<Card> cardsDealt) {
		// TODO Auto-generated method stub
		return null;
	}

}
