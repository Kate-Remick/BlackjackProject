package com.skilldistillery.cardgames.entities;

import java.util.Map;

public interface Hit{
	
	public Map<PersonAtTable, Hand> Hit(Map<PersonAtTable, Hand> CardsOnTable, Deck deck, PersonAtTable person);
	

}
