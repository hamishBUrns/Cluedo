package game;

import java.util.*;

import cards.Card;

public class Player {

	private List<Card> hand;
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

	public void printHand(){
		for(Card c : hand){
			System.out.println(c.getName());
		}
	}

<<<<<<< HEAD
	public String getName(){
		return name;
=======
	public List<Card> getHand(){
		return hand;
>>>>>>> aaace10f87764c98505dd6c68baf5e64548c3091
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
