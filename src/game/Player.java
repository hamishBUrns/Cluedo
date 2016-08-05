package game;

import java.util.*;

import cards.Card;

public class Player {

	private List<Card> hand= new ArrayList<Card>();
	private int row,col;
	private String name;
	private boolean stillIn;

	public Player(String nam,int row, int col) {
		// TODO Auto-generated constructor stub
		this.row = row;
		this.col = col;
		this.name =nam;
	}

	/**
	 * adds a card to this player's hand
	 * @param card
	 */
	public void giveCard(Card card){
		if(card!=null){
			hand.add(card);
		}
	}

	/**
	 * prints out the cards in this player's hand
	 */
	public void printHand(){
		for(Card c : hand){
			System.out.println(c.getName());
		}
	}

	public String getName(){
		return name;
	}

	/**
	 * returns true if player is still in the game, otherwise returns false
	 * @return
	 */
	public boolean isStillIn(){
		return stillIn;
	}

	/**
	 * set whether or not this player is still in the game
	 * @param playStatus
	 */
	public void setStatus(boolean playStatus){
		stillIn = playStatus;
	}

	/**
	 * returns this player's hand
	 * @return
	 */
	public List<Card> getHand(){
		return hand;
	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}

	public void setRow(int r){
		row = r;
	}

	public void setCol(int c){
		col = c;
	}

}
