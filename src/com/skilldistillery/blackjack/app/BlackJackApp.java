package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.skilldistillery.cardgames.entities.Dealer;
import com.skilldistillery.cardgames.entities.Deck;
import com.skilldistillery.cardgames.entities.Hand;
import com.skilldistillery.cardgames.entities.NPCPlayer;
import com.skilldistillery.cardgames.entities.PersonAtTable;
import com.skilldistillery.cardgames.entities.Player;

public class BlackJackApp {
	Scanner kb;
	private static boolean gameWon;
	private static boolean gambling;

	public BlackJackApp() {
		this.kb = new Scanner(System.in);
		gameWon = false;

	}

	public static void main(String[] args) {
		BlackJackApp app = new BlackJackApp();
		app.launch();

	}

	private void launch() {
		boolean validInput = false;
		while (!validInput) {
			try {

				Dealer dealer = new Dealer();
				System.out.println("Enter your name");
				String name = kb.nextLine();
				Player you = new Player(name);
				System.out.println("Are you a gambling person(y/n)?");
				String gamble = kb.nextLine().toLowerCase();
				if (gamble.startsWith("y")) {
					System.out.println("I'll take that as a yes...");
					gambling = true;
				} else {
					System.out.println("... I'll take that as a no.");
					gambling = false;
				}

				List<PersonAtTable> players = playerNumber(you);
				Map<PersonAtTable, Hand> cardsOnTable = new HashMap<>();
				System.out.println("You approach the BlackJack table in the back corner.");
				if (players.size() == 1) {
					System.out.println(
							"There are no other players at the table right now, and the man behind it looks bored.");
					System.out.println(
							"The dealer looks up from the deck in his hands, which he has been shuffeling haphazardly");
				} else {
					System.out.println("The dealer looks up from the table. There are " + (players.size() - 1)
							+ " people at the table.");
				}

				System.out.println("Want me to deal you in? He asks gruffly.");
				System.out.println("(yes or no)");
				String userReply = this.kb.nextLine();
				userReply = userReply.toLowerCase();
				while (userReply.startsWith("y")) {
					if (gambling) {
						System.out.println("Place your initial bets: ");
						for (PersonAtTable player : players) {
							int bet = 0;
							if (player instanceof Player) {
								bet = kb.nextInt();
								kb.nextLine();
								while (!player.placeBet(bet)) {
									bet = kb.nextInt();
									kb.nextLine();
								}
							} else {
								bet = (int) Math.random() * player.getNumTokens() % 33;
								player.placeBet(bet);
							}
						}
						System.out.println("All players have placed their bets.");
						System.out.println("The pool has " + PersonAtTable.bettingPool + " Tokens.");
					}

					Deck deck = dealer.getNewDeck();
					cardsOnTable = dealer.dealCards(deck, dealer, players);
					cardsOnTable = playerTurn(cardsOnTable, you, deck);
					if (gameWon == false) {
						for (PersonAtTable player : players) {
							if (player instanceof NPCPlayer) {
								cardsOnTable = npcTurn(cardsOnTable, player, deck);
							}
						}
					}
					if (gameWon == false) {
						cardsOnTable = dealerTurn(cardsOnTable, dealer, deck);
					}
					if (gameWon == false) {
						gameWon = checkWin(cardsOnTable);
					}
					gameWon = false;
					System.out.println("Do you wish to play again?");
					userReply = kb.nextLine();
				}
				System.out.println("The dealer nods to you politely as you get up from the table and walk away");

				validInput = true;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void menu() {
		System.out.println("What would you like to do?");
		System.out.println("1) Look at the cards in my hand");
		System.out.println("2) Hit me.");
		System.out.println("3) I'll Stand.");
	}

	private Map<PersonAtTable, Hand> playerTurn(Map<PersonAtTable, Hand> cardsOnTable, PersonAtTable you, Deck deck) {
		boolean validInput = false;
		while(!validInput) {
			
			try {
				String opt = "5";
				while (Integer.parseInt(opt) != 3) {
					if (cardsOnTable.get(you).getValue() == 21) {
						System.out.println("You've got a BlackJack. You win!");
						System.out.println("Collect your winnings: ");
						you.collectWinnings();
						gameWon = true;
					}
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
							System.out.println("You win!");
							System.out.println("Collect your winnings: ");
							you.collectWinnings();
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
				validInput = true;
			} catch (Exception e) {
				System.out.println("Your entry was invalid. Enter a number.");
			}
		}
		

		return cardsOnTable;
	}

	private Map<PersonAtTable, Hand> npcTurn(Map<PersonAtTable, Hand> cardsOnTable, PersonAtTable npc, Deck deck) {
		int npcValue = cardsOnTable.get(npc).getValue();
		while (npcValue <= 21) {
			if (npcValue == 21) {
				System.out.println(npc + "shows their hand:");
				System.out.println(cardsOnTable.get(npc));
				System.out.println(npc + " wins!");
				System.out.println(npc + "Collects their winnings...");
				npc.collectWinnings();
				gameWon = true;
				break;
			}
			if (npcValue <= 10 + ((int) Math.random() * 9)) {
				cardsOnTable = npc.Hit(cardsOnTable, deck, npc);
				npcValue = cardsOnTable.get(npc).getValue();
			} else {
				System.out.println(npc + " stands.");
				System.out.println(npc + "'s cards: " + cardsOnTable.get(npc));
				break;
			}

		}
		if (npcValue > 21) {
			System.out.println(npc + " busts - they displays their hand: ");
			System.out.println(cardsOnTable.get(npc));
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
				System.out.println("Dealer wins!");
				System.out.println("The dealer keeps the pot");
				dealer.collectWinnings();
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
		System.out.println(winningPlayer + " collects the pot...");
		winningPlayer.collectWinnings();
		return false;
	}

	private List<PersonAtTable> playerNumber(PersonAtTable player1) {
		List<PersonAtTable> players = new ArrayList<>();
		boolean gotPlayers = false;
		while (!gotPlayers) {
			try {
				System.out.println("There are nine seats at a BlackJack table.");
				System.out.println("How many players would you like to play with?");
				int numPlayers = kb.nextInt();
				kb.nextLine();
				if (numPlayers > 8 || numPlayers < 0) {
					System.out.println("The table cannot support that number of players");
					System.out.println("Four people will be joining you.");
					numPlayers = 4;
				}
				for (int i = 0; i < numPlayers; i++) {
					players.add(new NPCPlayer());
				}
				players.add(player1);
				gotPlayers = true;
			} catch (Exception e) {
				System.out.println("Invalid input.");
				System.out.println("Please enter the number of players (1 - 8). ");
			}

		}

		return players;
	}
}
