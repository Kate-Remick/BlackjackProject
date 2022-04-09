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
				this.cardsInDeck.add(new Card(suit, rank));
			}
		}
	}
	
	public int checkDeckSize() {
		return this.cardsInDeck.size();
	}
	public List<Card> dealCard(int numCards) {
		List <Card> cardsDealt = new ArrayList<>();
		for(int i =0; i < numCards; i++) {
			cardsDealt.add(this.cardsInDeck.remove(0));
			
		}
		return cardsDealt;
		
	}
	public Card dealSingleCard() {
		return this.cardsInDeck.remove(0);
	}
	public void shuffleDeck() {
		Collections.shuffle(this.cardsInDeck);
	}
	public void showDeck() {
		System.out.println(this.cardsInDeck);
	}
}
