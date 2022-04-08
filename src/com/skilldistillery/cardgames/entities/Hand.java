package com.skilldistillery.cardgames.entities;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> cardsInHand;
	private int value;
	
	public Hand(List<Card> dealtCards) {
		this.cardsInHand  = new ArrayList<>();
		this.cardsInHand.addAll(dealtCards);
		int value  = 0;
		for (Card card : dealtCards) {
			value += card.getValue();
		}
		this.value = value;
	}

	@Override
	public String toString() {
		return "Hand: " + cardsInHand + ", ";
	}

	public List<Card> getCardsInHand() {
		return cardsInHand;
	}

	public int getValue() {
		int value  = 0;
		for (Card card : this.cardsInHand) {
			value += card.getValue();
		}
		this.value = value;
		return this.value;
	}
	
	
	
	
}
