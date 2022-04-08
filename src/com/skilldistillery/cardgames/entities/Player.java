package com.skilldistillery.cardgames.entities;

import java.util.List;

public class Player extends PersonAtTable {
	private Hand playerHand;
	
	public Player() {
		
	}
	
	public Hand getHand() {
		
		return this.playerHand;
	}
	
	public Hand newHand(List<Card> dealtCards) {
		Hand ph = new Hand(dealtCards);
		
		return this.playerHand;
		
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

	

}
