package com.skilldistillery.cardgames.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NPCPlayer extends PersonAtTable {
	private static int namePicker = 0;	
	private String name;
	
	public NPCPlayer() {
		this.name = getNPCName();
		this.numTokens = (int)(Math.random()*200);
		
		
	}
	private String getNPCName() {
		String name;
		String[] names = {"Jim", "Brad", "Scar-Face", "Taylor", "Gretta", "Chayenne", "Stabby", "Karen",  "Geralt"};
		name = names[namePicker];
		namePicker ++;
		
		return name;
	}
	
	
	@Override
	public String toString() {
		return  name;
	}
	@Override
	public Map<PersonAtTable, Hand> Hit(Map<PersonAtTable, Hand> cardsOnTable, Deck deck, PersonAtTable person) {
		cardsOnTable.get(person).getCardsInHand().addAll(deck.dealCard(1));
		return cardsOnTable;
	}
	
	@Override
	public Hand newHand(List<Card> cardsDealt) {
		System.out.println("The dealer deals 2 cards face up to " + this.name +":");
		for (Card card : cardsDealt) {
			System.out.println(card);
		}
		System.out.println();
		Hand ph = new Hand(cardsDealt);
		return ph;
	}
}
