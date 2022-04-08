package com.skilldistillery.cardgames.entities;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> hand;
	
	public Hand(List<Card> dealtCards) {
		this.hand  = new ArrayList();
		this.hand.addAll(dealtCards);
	}
	
	
	
	
}
