package com.skilldistillery.cardgames.entities;

import java.util.List;
import java.util.Map;

public class Player extends PersonAtTable {
	private String name;
	

	public Player(String name) {
		this.name  = name;
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	@Override
	public Hand newHand(List<Card> cardsDealt) {
		Hand ph = new Hand(cardsDealt);
		System.out.println("The dealer deals you: ");
		for (Card card : cardsDealt) {
			System.out.println(card);
		}
		
		return ph;
		
	}

	@Override
	public Map<PersonAtTable, Hand> Hit(Map<PersonAtTable, Hand> cardsOnTable, Deck deck, PersonAtTable person) {
		cardsOnTable.get(person).getCardsInHand().addAll(deck.dealCard(1));
		if(person instanceof Player) {
			System.out.println("You now have the follwing hand: ");
			System.out.println(cardsOnTable.get(person));
		}
		return cardsOnTable;
	}


	@Override
	public String toString() {
		return   name ;
	}
	

}
