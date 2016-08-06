package main;

import java.util.*;

import cards.*;
import game.*;

public class Game {

	private List<Player> players = new ArrayList<Player>();
	private List<Player> allCharas = new ArrayList<>();
	private List<Card> solution = new ArrayList<Card>();
	private Map<String, Weapon> weapons = new HashMap<>();

	private TextClient client;
	private Deck deck;
	private Checklist checklist;
	private Board board;

	private int turnIndex;
	private boolean noWinner;

	/**
	 * constructor for actually playing the game
	 *
	 * @param c
	 */
	public Game(TextClient c) {
		client = c;
		deck = new Deck();
		checklist = new Checklist();
		noWinner = true;
		board = new Board();

		assignCharacters();
		dealCards();
		placeWeapons();
		client.hashCode();
		runGame();
	}

	/**
	 * Runs through the turns for the players while someone hasn't won and all
	 * the players haven't lost
	 */
	public void runGame() {
		client.printBoardKeys();
		System.out.println("Starting game!");
		// noWinner && playersleft()
		play: while (true) {
			turnIndex = 0;
			for (Player p : players) {
				if (!noWinner) {
					System.out.println("GAME WON!!!");
					break play;
				}
				if (!playersleft()) {
					System.out.println("Wow, you all suck at this. Game over, I guess.");
					break play;
				}
				if (p.isStillIn()) {
					System.out.println();
					turn(p);
					turnIndex++;
				}
			}
		}
	}

	/**
	 * Creates the weapons of the game and puts them in rooms
	 */
	public void placeWeapons() {
		weapons.put("candlestick", new Weapon("candlestick"));
		weapons.put("rope", new Weapon("rope"));
		weapons.put("dagger", new Weapon("dagger"));
		weapons.put("leadpipe", new Weapon("leadpipe"));
		weapons.put("revolver", new Weapon("revolver"));
		weapons.put("spanner", new Weapon("spanner"));

		List<Room> rooms = new ArrayList<>();
		rooms.addAll(board.getRooms());
		Random rand = new Random();
		int index = rand.nextInt(rooms.size());
		//int i = 0;

		for (Weapon w : weapons.values()) {
			//System.out.println(w.symbol());
			Room r = rooms.get(index);
			//System.out.println(r.getName());
			r.putInRoom(w);
			//i++;
			rooms.remove(r);
			index = rand.nextInt(rooms.size());
		}
		// for (int i = 0; i < weapons.size(); i++) {
		// board.getRooms().get(i).putInRoom(weapons.get(i));
		// }
	}

	/**
	 * evenly deals cards to the players and add leftovers to checklist
	 */
	public void dealCards() {
		solution = deck.setSolution();

		while (deck.getDeck().size() >= players.size()) {
			for (Player p : players) {
				p.giveCard(deck.deal());
			}
		}
		for (Card c : deck.getDeck()) {
			checklist.addCard(c);
		}
		deck.setCards();// repopulate the deck to be used as a record of all the
						// card in the game
	}

	/**
	 * Assigns characters depending on how many players there are
	 */
	public void assignCharacters() {
		ArrayList<Player> defaults = new ArrayList<Player>();
		defaults.add(new Player("Miss Scarlet", 0, 9));
		defaults.add(new Player("Colonel Mustard", 0, 15));
		defaults.add(new Player("Mrs White", 6, 24));
		defaults.add(new Player("The Reverend Green", 17, 0));
		defaults.add(new Player("Mrs Peacock", 19, 24));
		defaults.add(new Player("Professor Plum", 24, 7));

		for (Player p : defaults) { // Putting players on tiles.
			board.getTile(p.getRow(), p.getCol()).setToken(p);
		}

		int numPlayers = client.readInt("Hi, How many players?");
		while (numPlayers > 6 || numPlayers < 2) {
			numPlayers = client.readInt("Must have between 2 and 6 players");
		}
		int player = 1;
		while (numPlayers > 0) {
			System.out.println("Characters left:");
			for (Player p : defaults) {
				System.out.println(p.getName());
			}

			String character = client.readString("Player" + player + " Type your character");
			while (playerFromString(character, defaults) == null) {
				character = client.readString("Type out character as shown:");
			}
			players.add(playerFromString(character, defaults));
			players.get(player - 1).setPlayerNumber(player);
			defaults.remove(playerFromString(character, defaults));
			numPlayers--;
			player++;
		}
		allCharas.addAll(defaults);
		allCharas.addAll(players);
	}

	/**
	 * Runs through actions taken on a players turn
	 *
	 * @param p
	 */
	public void turn(Player p) {
		System.out.print(p.getName() + "'s turn, cards in hand are:");
		p.printHand();

		Random rand = new Random();
		int diceRoll = rand.nextInt(11) + 2; // generate a random number between
												// 2 and 12
		System.out.println(p.getName() + " rolled a " + diceRoll);
		Room startRoom = board.currentRoom(p);
		if (startRoom != null) {
			leaveRoom(diceRoll, startRoom, p);
		} else {
			move(diceRoll, p);
		}
		boolean turnEnded = false;
		while (!turnEnded) {
			String command = client.readString("What would you like to do?").toLowerCase();
			switch (command) {
			case ("checklist"):
				checklist.printChecklist();
				break;
			case ("hand"):
				p.printHand();
				break;
			case ("suggest"):
				Room room = board.currentRoom(p);
				if (room != null) {
					Card c = refute(suggest(room, cardFromString(room.getName()), p));
					if (c == null) {
						System.out.println("Egads! " + p.getName() + "'s got it!");
						noWinner = false;
					} else {
						System.out.println(
								"But wait! There's irrefutable proof that '" + c.getName() + "' was not involved");
					}
					turnEnded = true;
				} else {
					System.out.println("Must be in room to suggest");
				}
				break;
			case ("accuse"):
				if (accusationCorrect(accuse(p))) {
					noWinner = false;
					System.out.println("By Jove! " + p.getName() + " has solved it!");
				} else {
					System.out.println("What poppycock! " + p.getName() + " is out of the game.");
					p.setStatus(false);
				}
				turnEnded = true;
				break;
			case ("end"):
				turnEnded = true;
				break;
			case ("help"):
				client.help();
				break;
			case ("keys"):
				client.printBoardKeys();
				break;
			default:
				System.out.println("Invalid command. Type 'help' to see command list and descriptions");
			}
		}
	}

	public void leaveRoom(int diceRoll, Room room, Player p) {
		Map<String, Tile> doors = room.getDoors();
		System.out.println("Exits from the " + room.getName() + " are:");
		for (String s : doors.keySet()) {
			System.out.println(s);
		}
		String exit = client.readString("How would you like to leave?");
		while (!doors.containsKey(exit)) {
			exit = client.readString("Please choose an exit from the list");
		}
		room.takeFromRoom(p);
		Tile destination = doors.get(exit);
		p.setRow(destination.getRow());
		p.setCol(destination.getCol());
		Room newRoom = board.currentRoom(p);
		if (newRoom != null) {
			newRoom.putInRoom(p);
		} else {
			move(diceRoll, p);
		}
	}

	/**
	 * gets user input to move them around the board
	 */
	public void move(int diceRoll, Player p) {
		while (diceRoll > 0) {
			board.printBoard();
			System.out.println("Steps left: " + diceRoll);
			String dir = client.readString("Choose a direction").toUpperCase();
			switch (dir) {
			case ("N"):
				// go north
				if (board.moveValid(p.getRow(), p.getCol(), p.getRow() - 1, p.getCol(), p)) {
					diceRoll--;
				}
				break;
			case ("S"):
				// go south
				if (board.moveValid(p.getRow(), p.getCol(), p.getRow() + 1, p.getCol(), p)) {
					diceRoll--;
				}
				break;
			case ("E"):
				// go east
				if (board.moveValid(p.getRow(), p.getCol(), p.getRow(), p.getCol() + 1, p)) {
					diceRoll--;
				}
				break;
			case ("W"):
				// go west
				if (board.moveValid(p.getRow(), p.getCol(), p.getRow(), p.getCol() - 1, p)) {
					diceRoll--;
				}
				break;
			default:
				System.out.println("Invalid input. Please use one of the following: N, S, E, W");
			}
			if (board.currentRoom(p) != null) {
				board.currentRoom(p).putInRoom(p);
				board.printBoard();
				return;
			}
		}
		board.printBoard();

	}

	/**
	 * get input for the weapon and character for this suggestion from the user
	 *
	 * @param room
	 * @param p
	 */
	public List<Card> suggest(Room room, Card r, Player p) {
		Card c = askCharacter(p);
		Card w = askWeapon(p);
		System.out.println(
				"Perhaps it was " + c.getName() + " in the " + r.getName() + " with the " + w.getName() + "?");

		Player suspect = playerFromString(c.getName(), allCharas);
		Room susRoom = board.currentRoom(suspect);
		if(susRoom != null){
			susRoom.takeFromRoom(suspect);
		}
		room.putInRoom(suspect);

		Weapon weap = weapons.get(w.getName());
		//board.currentRoom(weap).takeFromRoom(weap);
		//room.putInRoom(weap);

		board.printBoard();

		List<Card> cards = new ArrayList<>();
		cards.add(c);
		cards.add(r);
		cards.add(w);
		return cards;
	}

	/**
	 * if suggestion is refuted, returns that card, otherwise returns null
	 */
	public Card refute(List<Card> suggested) {
		// start at the index after the player suggesting
		int current = turnIndex + 1;
		// go until we are back at suggesting player
		while (current != turnIndex) {
			for (Card c : players.get(current).getHand()) {
				for (Card s : suggested) {
					if (c.equals(s)) {
						checklist.addCard(c);
						return c;
					}
				}
			}
			current++;
			// when we reach the end of the list, start at the beginning
			if (current == players.size()) {
				current = 0;
			}
		}
		return null;
	}

	/**
	 * get input for the room, weapon, and character for this accusation from
	 * the user
	 *
	 * @param p
	 */
	public List<Card> accuse(Player p) {
		Card c = askCharacter(p);
		Card r = askRoom(p);
		Card w = askWeapon(p);
		System.out.println("It was " + c.getName() + " in the " + r.getName() + " with the " + w.getName() + "!");

		List<Card> cards = new ArrayList<>();
		cards.add(c);
		cards.add(r);
		cards.add(w);
		return cards;
	}

	public Card askCharacter(Player p) {
		System.out.println("Characters on checklist: ");
		checklist.printCheckedCharas();
		Card c = cardFromString(client.readString("Who dunnit?"));
		while (c == null || !(c instanceof CharacterCard) || p.getHand().contains(c) || checklist.contains(c)) {
			c = cardFromString(
					client.readString("Character must not be in your checklist or hand and must be spelt correctly"));
		}
		return c;
	}

	public Card askRoom(Player p) {
		System.out.println("Rooms on checklist: ");
		checklist.printCheckedRooms();
		Card r = cardFromString(client.readString("Scene of the crime?"));
		while (r == null || !(r instanceof RoomCard) || p.getHand().contains(r) || checklist.contains(r)) {
			r = cardFromString(
					client.readString("Room must not be in your checklist or hand and must be spelt correctly"));
		}
		return r;
	}

	public Card askWeapon(Player p) {
		System.out.println("Weapons on checklist: ");
		checklist.printCheckedWeaps();
		Card w = cardFromString(client.readString("Murder weapon?"));
		while (w == null || !(w instanceof WeaponCard) || p.getHand().contains(w) || checklist.contains(w)) {
			w = cardFromString(
					client.readString("Weapon must not be in your checklist or hand and must be spelt correctly"));
		}
		return w;
	}

	/**
	 * Returns true if accusation matches the solution
	 *
	 * @param accusation
	 * @return
	 */
	public boolean accusationCorrect(List<Card> accusation) {
		for (Card c : accusation) {
			if (!solution.contains(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if there are still players that are left(Haven't been kicked
	 * out by wrong accusation)
	 *
	 * @return
	 */
	public boolean playersleft() {
		for (Player p : players) {
			if (p.isStillIn()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * return the card whose name matches the input string, null if no match
	 * found
	 *
	 * @param s
	 * @return card
	 */
	public Card cardFromString(String s) {
		for (Card c : deck.getDeck()) {
			if (c.getName().equalsIgnoreCase(s)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Return player from defaults whose name matches input string, null if no
	 * match found
	 *
	 * @param character
	 * @param characters
	 * @return
	 */
	public Player playerFromString(String character, List<Player> characters) {
		for (Player p : characters) {
			if (p.getName().equalsIgnoreCase(character)) {
				return p;
			}
		}
		return null;
	}

	public static void main(String args[]) {
		new Game(new TextClient());
	}

	// ====THE FOLLOWING METHODS ARE FOR TESTING PURPOSES ONLY==== //

	/**
	 * constructor that doesn't require user input for testing purposes
	 *
	 * @param c
	 * @param numPlayers
	 */
	public Game(TextClient c, int numPlayers) {
		client = c;
		deck = new Deck();
		board = new Board();
		checklist = new Checklist();
		noWinner = true;

		List<Player> defaults = new ArrayList<Player>();
		defaults.add(new Player("Miss Scarlet", 0, 9));
		defaults.add(new Player("Colonel Mustard", 0, 15));
		defaults.add(new Player("Mrs White", 6, 24));
		defaults.add(new Player("The Reverend Green", 17, 0));
		defaults.add(new Player("Mrs Peacock", 19, 24));
		defaults.add(new Player("Professor Plum", 24, 7));

		while (numPlayers >= 0) {
			players.add(defaults.get(numPlayers));
			numPlayers--;
		}
	}

	public void setSolution(List<Card> sol) {
		solution = sol;
	}

	/**
	 * returns list of players, for testing only
	 *
	 * @return
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * return the map of strings to weapons
	 * @return
	 */
	public Map<String,Weapon> getWeapons() {
		return weapons;
	}

	/**
	 * return checklist, for testing only
	 *
	 * @return
	 */
	public Checklist getChecklist() {
		return checklist;
	}

	public Board getBoard(){
		return board;
	}

}
