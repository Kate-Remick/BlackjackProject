package com.skilldistillery.cardgames.entities;

import java.util.Objects;

public class Card {
	Suit suit;
	Rank rank;
	
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public int getValue() {
		return this.rank.getValue();
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(rank);
		builder.append(" of ");
		builder.append(suit.getName());
		return builder.toString();
	}


	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return rank == other.rank && suit == other.suit;
	}

}
