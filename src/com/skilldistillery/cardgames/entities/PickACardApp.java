package com.skilldistillery.cardgames.entities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PickACardApp {

	public static void main(String[] args) {
		PickACardApp app = new PickACardApp();
		app.run();
	}

	private void run() {
		Scanner kb = new Scanner(System.in);
		try {
			System.out.println("How many cards do you want?");
			int numCards = kb.nextInt();
			dealAndDisplay(numCards);
			
		}catch(InputMismatchException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void dealAndDisplay(int numCards) {
		Deck deck = new Deck();
		deck.suffleDeck();
		int totalValue = 0;
		if(numCards > 52 || numCards < 0) {
			System.out.println("Pick a number between 0 and 52");
		}else {
			for(int i =0; i < numCards; i ++) {
				Card dealt = deck.dealCard();
				System.out.println(dealt);
				totalValue += dealt.getValue();
			}
		System.out.println(totalValue);	
		}
	}

}
