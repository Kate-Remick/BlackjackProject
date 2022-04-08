package com.skilldistillery.cardgames.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cardsInDeck;

	public Deck() {
		cardsInDeck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
//				System.out.println("Check");
				this.cardsInDeck.add(new Card(suit, rank));
			}
		}
	}
	
	public int checkDeckSize() {
		return this.cardsInDeck.size();
	}
	public Card dealCard() {
		
		return this.cardsInDeck.remove(0);
	}
	public void shuffleDeck() {
		Collections.shuffle(this.cardsInDeck);
	}
}
