package com.skilldistillery.cardgames.entities;

public enum Suit {
	HEARTS("Hearts"), 
	SPADES("Spades"), 
	CLUBS("Clubs"), 
	DIAMONDS("Diamonds");
	
	private String name;
	
	Suit(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	@Override
	  public String toString() {
	    return name;
	  }
	

}
