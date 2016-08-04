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

	public void getHand(){
		for(Card c : hand){
			System.out.println(c.getName());
		}
	}

	public String getName(){
		return name;
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
