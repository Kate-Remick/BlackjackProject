package com.skilldistillery.cardgames.entities;

import java.util.List;

public abstract class PersonAtTable implements Hit {
	protected Hand hand;
	protected int numTokens;
	public static int bettingPool;

	public PersonAtTable() {
	}

	public abstract Hand newHand(List<Card> cardsDealt);

	public int getNumTokens() {
		return numTokens;
	}

	public void setNumTokens(int numTokens) {

		this.numTokens = numTokens;
	}

	public boolean placeBet(int bet) {
		boolean betPlaced = false;
		if (bet > this.numTokens) {
			System.out.println("You do not have that many tokens. Place a different bet.");
		}else {
			numTokens -= bet;
			bettingPool+= bet;
			betPlaced = true;
		}
		
		return betPlaced;
	}

	public void collectWinnings(int numTiedPlayers) {
		
		this.numTokens += bettingPool/numTiedPlayers;
		bettingPool -= bettingPool/numTiedPlayers;
	}
}
