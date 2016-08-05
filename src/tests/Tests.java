package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import cards.*;
import game.*;
import main.*;

public class Tests {
	@Test
	public void validCardFromString(){
		Game g = new Game(new TextClient(), 2);
		Card c = g.cardFromString("professor plum");
		assertEquals((CharacterCard) c, new CharacterCard("Professor Plum"));
		c = g.cardFromString("MRS WHITE");
		assertEquals((CharacterCard) c, new CharacterCard("Mrs White"));
		c = g.cardFromString("tHe ReVeReNd GrEeN");
		assertEquals((CharacterCard) c, new CharacterCard("The Reverend Green"));
	}

	@Test
	public void invalidCardFromString(){
		Game g = new Game(new TextClient(), 2);
		Card c = g.cardFromString("butts");
		assertTrue(c == null);
		c = g.cardFromString("mr plum");
		assertTrue(c == null);
	}

	@Test
	public void isRefuted(){
		Game g = new Game(new TextClient(), 2);
		List<Player> players = g.getPlayers();
		Card c = new CharacterCard("Mrs White");
		players.get(1).giveCard(c);
		List<Card> cards = new ArrayList<>();
		cards.add(c);
		cards.add(new WeaponCard("Rope"));
		cards.add(new RoomCard("Hall"));
		assertEquals(g.refute(cards), c);
		assertTrue(g.getChecklist().contains(c));
	}

	@Test
	public void notRefuted(){
		Game g = new Game(new TextClient(), 2);
		List<Player> players = g.getPlayers();
		players.get(1).giveCard(new CharacterCard("Mrs White"));
		List<Card> cards = new ArrayList<>();
		cards.add(new CharacterCard("The Reverend Green"));
		cards.add(new WeaponCard("Rope"));
		cards.add(new RoomCard("Hall"));
		assertTrue(g.refute(cards) == null);
	}

	@Test
	public void validDeal_4(){
		Game g= new Game(new TextClient(), 5);
	}

	@Test
	public void boardGood_5(){
		Board board=new Board();
		board.printBoard();
	}
}
