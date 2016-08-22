package board;

import java.util.*;

import cards.Card;
import main.Checklist;

public class Player implements Token {

	private List<Card> hand = new ArrayList<Card>();
	private int row, col;
	private String name;
	private String nick;
	private boolean stillIn;
	private int playerNumber;
	private Checklist checklist;

	public Player(String nam, int row, int col) {
		// TODO Auto-generated constructor stub
		this.row = row;
		this.col = col;
		this.name =nam;
		stillIn = true;
		checklist = new Checklist();
		}

	/**
	 * adds a card to this player's hand
	 *
	 * @param card
	 */
	public void giveCard(Card card) {
		if (card != null) {
			hand.add(card);
			checklist.addCard(card);
		}
	}

	/**
	 * prints out the cards in this player's hand
	 */
	public void printHand() {
		for (Card c : hand) {
			System.out.println(c.getName());
		}
	}

	public Checklist getChecklist(){
		return checklist;
	}

	/**
	 * returns true if player is still in the game, otherwise returns false
	 *
	 * @return
	 */
	public boolean isStillIn() {
		return stillIn;
	}

	/**
	 * set if this player is still in the game; true if still playing, otherwise false
	 *
	 * @param playStatus
	 */
	public void setStatus(boolean playStatus) {
		stillIn = playStatus;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setPlayerNumber(int pos){
		playerNumber=pos;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public String getName() {
		return name;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int r) {
		row = r;
	}

	public void setCol(int c) {
		col = c;
	}

	public void setNick(String s){
		nick = s;
	}

	public String getNick(){
		return nick;
	}

	@Override
	public String id() {
		return name;
	}

}
