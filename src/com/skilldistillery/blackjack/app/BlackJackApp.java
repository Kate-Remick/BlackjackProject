package com.skilldistillery.blackjack.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.skilldistillery.cardgames.entities.Dealer;
import com.skilldistillery.cardgames.entities.Deck;
import com.skilldistillery.cardgames.entities.Hand;
import com.skilldistillery.cardgames.entities.PersonAtTable;
import com.skilldistillery.cardgames.entities.Player;

public class BlackJackApp {
	Scanner kb;
	public static boolean gameWon;

	public BlackJackApp() {
		this.kb = new Scanner(System.in);
		gameWon = false;

	}

	public static void main(String[] args) {
		BlackJackApp app = new BlackJackApp();
		app.launch();

	}

	private void launch() {
		try {
			Dealer dealer = new Dealer();
			System.out.println("Enter your name");
			String name = kb.nextLine();
			Player you = new Player(name);
			Map<PersonAtTable, Hand> cardsOnTable = new HashMap<>();
			System.out.println("You approach the BlackJack table in the back corner.");
			System.out.println(
					"There are no other players at the table right now, and the man behind the table looks bored.");
			System.out.println(
					"The dealer looks up from the deck in his hands, which he has been shuffeling haphazardly");
			System.out.println("Want me to deal you in? He asks gruffly.");
			System.out.println("(yes or no)");
			String userReply = this.kb.nextLine();
			userReply = userReply.toLowerCase();
			while (userReply.startsWith("y")) {
				Deck deck = dealer.getNewDeck();
				cardsOnTable = dealer.dealCards(deck, dealer, you);
				cardsOnTable = playerTurn(cardsOnTable, you, deck);
				if (gameWon == false) {
					cardsOnTable = dealerTurn(cardsOnTable, dealer, deck);
				}
				if (gameWon == false) {
					gameWon = checkWin(cardsOnTable);
				}
				System.out.println("Do you wish to play again?");
				userReply = kb.nextLine();
			}
			System.out.println("\"I understand\", He states.");
			System.out.println("The dealer nods to you politely as you get up from the table and walk away");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void menu() {
		System.out.println("What would you like to do?");
		System.out.println("1) Look at the cards in my hand");
		System.out.println("2) Hit me.");
		System.out.println("3) I'll Stand.");
	}

	private Map<PersonAtTable, Hand> playerTurn(Map<PersonAtTable, Hand> cardsOnTable, PersonAtTable you, Deck deck) {
		try {
			String opt = "5";
			while (Integer.parseInt(opt) != 3) {
				menu();
				opt = this.kb.nextLine();
				switch (Integer.parseInt(opt)) {
				case 1:
					System.out.println("You look down at your hand and see: ");
					System.out.println(cardsOnTable.get(you));
					break;
				case 2:
					System.out.println("The dealer deals you a card.");
					cardsOnTable = ((Player) you).Hit(cardsOnTable, deck, you);
					if (cardsOnTable.get(you).getValue() > 21) {
						System.out.println("You bust!");
						opt = "3";
					} else if (cardsOnTable.get(you).getValue() == 21) {
						System.out.println("You hit BlackJack!");
						System.out.println("You win!");
						gameWon = true;
						opt = "3";
					}
					break;
				case 3:
					System.out.println("You chose to stand.");
					System.out.println("Your hand value is " + cardsOnTable.get(you).getValue());
					break;
				default:
					System.out.println("Please enter a number, 1 - 3.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Your entry was invalid. Your turn is forfit.");
			return cardsOnTable;
		}

		return cardsOnTable;
	}

	private Map<PersonAtTable, Hand> dealerTurn(Map<PersonAtTable, Hand> cardsOnTable, PersonAtTable dealer,
			Deck deck) {
		int dealerValue = cardsOnTable.get(dealer).getValue();
		while (dealerValue <= 21) {
			if (dealerValue == 21) {
				System.out.println("The dealer shows his hand:");
				System.out.println(cardsOnTable.get(dealer));
				System.out.println("Dealer wins with blackjack.");
				gameWon = true;
				break;
			}
			if (dealerValue <= 17) {
				cardsOnTable = dealer.Hit(cardsOnTable, deck, dealer);
				dealerValue = cardsOnTable.get(dealer).getValue();
			} else {
				System.out.println("The dealer stands.");
				System.out.println("Dealer's cards: " + cardsOnTable.get(dealer));
				break;
			}

		}
		if (dealerValue > 21) {
			System.out.println("The dealer busts - he displays his hand: ");
			System.out.println(cardsOnTable.get(dealer));
		}

		return cardsOnTable;
	}

	private boolean checkWin(Map<PersonAtTable, Hand> cardsOnTable) {
		Set<PersonAtTable> players = cardsOnTable.keySet();
			int winningValue = 0;
			PersonAtTable winningPlayer = new Player("No one");
			for (PersonAtTable player : players) {
				int handValue;
				handValue = cardsOnTable.get(player).getValue();
				if (handValue < 21 && handValue > winningValue) {
					winningValue = handValue;
					winningPlayer = player;
				}
				System.out.println(player + " has a hand value of " + handValue);
			}
			System.out.println(winningPlayer + " wins!");
		return false;
	}
}
