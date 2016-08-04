package game;

import java.util.*;

import cards.Card;

public class Player {

	private List<Card> hand= new ArrayList<Card>();
	private int row,col;
	private String name;

	public Player(String nam,int row, int col) {
		// TODO Auto-generated constructor stub
		this.row = row;
		this.col = col;
		this.name =nam;
	}

	public void setHand(List<Card> cards){
		hand = cards;
	}

	public void giveCard(Card card){
		if(card!=null){
			hand.add(card);
		}
	}

	public void printHand(){
		for(Card c : hand){
			System.out.println(c.getName());
		}
	}

	public String getName(){
		return name;
	}

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
