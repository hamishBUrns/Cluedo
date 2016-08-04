package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.*;
import main.*;

public class Tests {
	@Test
	public void validCardFromString_1(){
		Game g = new Game(new TextClient(), 3);
		Card c = g.cardFromString("professor plum");
		assertEquals((CharacterCard) c, new CharacterCard("Professor Plum"));
		c = g.cardFromString("MRS WHITE");
		assertEquals((CharacterCard) c, new CharacterCard("Mrs White"));
		c = g.cardFromString("tHe ReVeReNd GrEeN");
		assertEquals((CharacterCard) c, new CharacterCard("The Reverend green"));//capitalise later
	}

	public void invalidCardFromString(){
		Game g = new Game(new TextClient(), 3);
		Card c = g.cardFromString("butts");
		assertTrue(c == null);
		c = g.cardFromString("mr plum");
		assertTrue(c == null);
	}

	public void validRefute(){
		Game g = new Game(new TextClient(), 3);
	}
}
