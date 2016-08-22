package main;

import java.util.*;

import board.*;
import cards.*;

public class Game {

	private List<Player> players = new ArrayList<Player>();
	private List<Player> allCharas = new ArrayList<>();
	private List<Card> solution = new ArrayList<Card>();
	private Map<String, Weapon> weapons = new HashMap<>();

	//private TextClient client;
	private Deck deck;
	//private Checklist checklist;
	private Board board;

	private int turnIndex;
	private int diceRoll;
	private boolean noWinner;
	private Player currentPlayer;

	/**
	 * constructor for actually playing the game
	 *
	 * @param c
	 */


	public Game() {
		deck = new Deck();
		noWinner = true;
		board = new Board();
		diceRoll=100;
		turnIndex=0;

		//populate the array that holds all characters in the game

		placeCharacters();
		placeWeapons();
	}

	public void placeCharacters(){
		allCharas.add(new Player("Miss Scarlett", 0, 9));
		allCharas.add(new Player("Colonel Mustard", 0, 15));
		allCharas.add(new Player("Mrs White", 6, 24));
		allCharas.add(new Player("The Reverend Green", 17, 0));
		allCharas.add(new Player("Mrs Peacock", 19, 24));
		allCharas.add(new Player("Professor Plum", 24, 7));

		for(Player p: allCharas){
			board.getTile(p.getRow(), p.getCol()).setToken(p);
		}
	}

	/**
	 * Creates the weapons of the game and puts them in rooms
	 */
	public void placeWeapons() {
		for (Card c : deck.weapons) {
			weapons.put(c.getName(), new Weapon(c.getName()));
		}

		List<Room> rooms = new ArrayList<>();
		rooms.addAll(board.getRooms());
		Random rand = new Random();
		int index = rand.nextInt(rooms.size());

		for (Weapon w : weapons.values()) {
			Room r = rooms.get(index);
			r.putInRoom(w, board);
			rooms.remove(r);
			index = rand.nextInt(rooms.size());
		}
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
			for(Player p : players){
				p.getChecklist().addCard(c);
			}
		}
		deck.setCards();// repopulate the deck to be used as a record of all the
						// card in the game
	}

	public void addPlayer(Player p, String nick){
		p.setNick(nick);
		players.add(p);
		p.setPlayerNumber(players.size());
	}


	// ===== turn logic starts here ==== //
	public void rollDice(){
		Random rand = new Random();
		// generate a random number between 2 and 12
		diceRoll = rand.nextInt(11) + 2;
	}

	/**
	 * increments the turnIndex and sets the current player
	 */
	public void endTurn(){
		if(turnIndex==players.size()-1){
			turnIndex=0;
		}
		else{
			turnIndex++;
		}
		setCurrentPlayer();
	}

	/**
	 * Sets the current player using the turnIndex and rolls the dice for their turn.
	 * If that player is out of the game, calls endTurn
	 */
	public void setCurrentPlayer(){
		currentPlayer = players.get(turnIndex);
		rollDice();
		if(!currentPlayer.isStillIn() && playersLeft()){
			endTurn();
		}
	}

	public boolean canMove(){
		if(!currentPlayer.isStillIn()){
			return false;
		}
		if(diceRoll==0){
			return false;
		}
		if(!noWinner){
			return false;
		}
		if(board.currentRoom(currentPlayer)!=null){
			return false;
		}
		return true;
	}

	public void tryMove(String dir){
		if(!canMove()){
			System.out.println("cant move");
			return;
		}
		move(dir);

	}

	public boolean canLeaveRoom(Tile door){
		Room room = board.currentRoom(currentPlayer);
		if(room==null){//if player isn't in a room
			//System.out.println("player not in room");
			return false;
		}
		if(!room.getDoors().containsValue(door)){ //if the door selected isn't a door of the players current room
			for(Tile t: room.getDoors().values()){
				//System.out.println("Door:"+t.getRow()+","+t.getCol());
			}
			//System.out.println("actual door is:"+door.getRow()+","+door.getCol());
			//System.out.println("door is not of this room");
			return false;
		}
		return true;
	}


	public void tryLeaveRoom(int row, int col){
		Tile door = board.getTile(row, col);
		if(!canLeaveRoom(door)){
			System.out.println("can't leave room");
			return;
		}
		leaveRoom(door);
	}

	/**
	 * current player leaves room by specified door
	 * @param diceRoll
	 * @param room
	 * @param p
	 */
	public void leaveRoom(Tile door) {
		Player p=currentPlayer;
		Room room =getBoard().currentRoom(p);
		room.takeFromRoom(p, board);
		Tile destination = door;
		p.setRow(destination.getRow());
		p.setCol(destination.getCol());
		board.getTile(p.getRow(), p.getCol()).setToken(p);
		Room newRoom = board.currentRoom(p);
		move("null");
	}

	/**
	 * gets user input to move them around the board
	 */
	public void move(String dir) {
		dir=dir.toUpperCase();
		Player p=currentPlayer;
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
			board.currentRoom(p).putInRoom(p, board);
			diceRoll=0;
			return;
			}
		//board.printBoard();
	}

	public boolean canSuggest() {
		Room room = board.currentRoom(currentPlayer);
		if(room == null){
			return false;
		}
		Card r = cardFromString(room.getName());
		if(currentPlayer.getChecklist().contains(r)){
			return false;
		}
		return true;
	}

	public List<Card> suggest(String s, String w){
		Room room = board.currentRoom(currentPlayer);

		List<Card> sug = new ArrayList<>();
		sug.add(cardFromString(s));
		sug.add(cardFromString(room.getName()));
		sug.add(cardFromString(w));

		Player suspect = playerFromString(s);
		Room susRoom = board.currentRoom(suspect);
		if (susRoom != null) {
			susRoom.takeFromRoom(suspect, board);
		}
		room.putInRoom(suspect, board);

		return sug;
	}

	/**
	 * if suggestion is refuted, returns that card, otherwise returns null
	 */
	public String refute(List<Card> suggested) {
		// start at the index after the player suggesting
		int current = turnIndex + 1;
		if (current == players.size()) {
			current = 0;
		}
		// go until we are back at suggesting player
		while (current != turnIndex) {
			for (Card c : players.get(current).getHand()) {
				for (Card s : suggested) {
					if (c.equals(s)) {
						// if someone has a card that matches:
						// add it to everyone's checklists
						for (Player p : players) {
							p.getChecklist().addCard(c);
						}
						// and return the name of the card
						return c.getName();
					}
				}
			}
			current++;
			// when we reach the end of the list, start at the beginning
			if (current == players.size()) {
				current = 0;
			}
		}
		// if no one has a match, return null
		return null;
	}

	/**
	 * Returns true if accusation matches the solution
	 * If they differ, removes the accusing character from the game
	 * @param accusation
	 * @return
	 */
	public boolean accusationCorrect(String s, String r, String w) {
		List<Card> acc = new ArrayList<>();
		acc.add(cardFromString(s));
		acc.add(cardFromString(r));
		acc.add(cardFromString(w));
		for (Card c : acc) {
			if (!solution.contains(c)) {
				currentPlayer.setStatus(false);
				return false;
			}
		}
		return true;
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

	/**
	 * returns the player object whose name matches the given string
	 * null if no match found
	 * @param character
	 * @return
	 */
	public Player playerFromString(String character) {
		for (Player p : allCharas) {
			if (p.getName().equalsIgnoreCase(character)) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Returns true if there are still players that are left (Haven't been kicked
	 * out by wrong accusation)
	 *
	 * @return
	 */
	public boolean playersLeft() {
		for (Player p : players) {
			if (p.isStillIn()) {
				return true;
			}
		}
		return false;
	}

	// ====THE FOLLOWING METHODS ARE FOR TESTING PURPOSES ONLY==== //

	/**
	 * constructor that doesn't require user input for testing purposes
	 *
	 * @param c
	 * @param numPlayers
	 */
	public Game(TextClient c, int numPlayers) {
		//client = c;
		deck = new Deck();
		board = new Board();
		//checklist = new Checklist();
		noWinner = true;

		List<Player> defaults = new ArrayList<Player>();
		defaults.add(new Player("Miss Scarlett", 0, 9));
		defaults.add(new Player("Colonel Mustard", 0, 15));
		defaults.add(new Player("Mrs White", 6, 24));
		defaults.add(new Player("The Reverend Green", 17, 0));
		defaults.add(new Player("Mrs Peacock", 19, 24));
		defaults.add(new Player("Professor Plum", 24, 7));

		allCharas.add(new Player("Miss Scarlett", 0, 9));
		allCharas.add(new Player("Colonel Mustard", 0, 15));
		allCharas.add(new Player("Mrs White", 6, 24));
		allCharas.add(new Player("The Reverend Green", 17, 0));
		allCharas.add(new Player("Mrs Peacock", 19, 24));
		allCharas.add(new Player("Professor Plum", 24, 7));

		for (Player p : defaults) { // Putting players on tiles.
			board.getTile(p.getRow(), p.getCol()).setToken(p);
		}

		while (numPlayers > 0) {
			players.add(defaults.get(numPlayers));
			numPlayers--;
		}
		placeWeapons();

	}

	/**
	 * returns list of players, for testing only
	 *
	 * @return
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/////these ones are being used now//////

	public List<Player> getAllCharas() {
		return allCharas;
	}

	public List<String> allCharaNames(){
		List<String> charaNames = new ArrayList<>();
		for(Card c : deck.characters){
			charaNames.add(c.getName());
		}
		return charaNames;
	}

	public List<String> allValidRoomNames(){
		List<String> roomNames = new ArrayList<>();
		for(Card r : deck.rooms){
			if(!currentPlayer.getChecklist().contains(r)){
				roomNames.add(r.getName());
			}
		}
		return roomNames;
	}

	public List<String> allValidCharaNames(){
		List<String> charaNames = new ArrayList<>();
		for(Card c : deck.characters){
			if(!currentPlayer.getChecklist().contains(c)){
				charaNames.add(c.getName());
			}
		}
		return charaNames;
	}

	public List<String> allValidWeapNames(){
		List<String> weapNames = new ArrayList<>();
		for(Card w : deck.weapons){
			if(!currentPlayer.getChecklist().contains(w)){
				weapNames.add(w.getName());
			}
		}
		return weapNames;
	}

	/**
	 * returns the board
	 * @return
	 */
	public Board getBoard() {
		return board;
	}

	//not used?
	public Deck getDeck() {
		return deck;
	}

	public String getCurrentChara(){
		return currentPlayer.getName();
	}

	public String getCurrentNick(){
		return currentPlayer.getNick();
	}

	public String currentRoomName(){
		return board.currentRoom(currentPlayer).getName();
	}

	public String currentPlayerHand(){
		String s = currentPlayer.getNick() + "'s Hand: \n";
		for(Card c : currentPlayer.getHand()){
			s += "\n" + c.getName();
		}
		return s;
	}

	public String currentPlayerChecklist() {
		String s = currentPlayer.getNick() + "'s Checklist \n";
		s += "\nCharacters: \n";
		for(Card c : deck.characters){
			if(currentPlayer.getChecklist().contains(c)){
				s += c.getName() + "[X]\n";
			}else{
				s += c.getName() + "[ ]\n";
			}
		}
		s += "\nRooms: \n";
		for(Card r : deck.rooms){
			if(currentPlayer.getChecklist().contains(r)){
				s += r.getName() + "[X]\n";
			}else{
				s += r.getName() + "[ ]\n";
			}
		}
		s += "\nWeapons: \n";
		for(Card w : deck.weapons){
			if(currentPlayer.getChecklist().contains(w)){
				s += w.getName() + "[X]\n";
			}else{
				s += w.getName() + "[ ]\n";
			}
		}
		return s;
	}

	public int getDiceRoll(){
		return diceRoll;
	}

}
