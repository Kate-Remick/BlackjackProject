package com.skilldistillery.cardgames.entities;

import java.util.List;

public abstract class PersonAtTable implements HitOrStand{
	Hand hand;
	
	public PersonAtTable() {}
	
	public abstract Hand newHand(List<Card> cardsDealt);

}
