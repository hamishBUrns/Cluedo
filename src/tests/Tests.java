package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import cards.*;
import game.*;
import main.*;

/**
 * Tests for Cluedo NOTE: due to how it was implemented, many of the functions
 * of this program cannot be tested without live user input, therefore these
 * tests are only of the parts that do not, while the rest were tested manually.
 * ((If there is an easier way do this, please advise.))
 *
 * @author kraemezoe
 *
 */
public class Tests {
	@Test
	public void validCardFromString() {
		Game g = new Game(new TextClient(), 2);
		Card c = g.cardFromString("professor plum");
		assertEquals((CharacterCard) c, new CharacterCard("Professor Plum"));
		c = g.cardFromString("MRS WHITE");
		assertEquals((CharacterCard) c, new CharacterCard("Mrs White"));
		c = g.cardFromString("tHe ReVeReNd GrEeN");
		assertEquals((CharacterCard) c, new CharacterCard("The Reverend Green"));
	}

	@Test
	public void invalidCardFromString() {
		Game g = new Game(new TextClient(), 2);
		Card c = g.cardFromString("butts");
		assertTrue(c == null);
		c = g.cardFromString("mr plum");
		assertTrue(c == null);
	}

	@Test
	public void isRefuted() {
		Game g = new Game(new TextClient(), 2);
		List<Player> players = g.getPlayers();
		Card c = new CharacterCard("Mrs White");
		players.get(1).giveCard(c);
		List<Card> cards = new ArrayList<>();
		cards.add(c);
		cards.add(new WeaponCard("rope"));
		cards.add(new RoomCard("Hall"));
		assertEquals(g.refute(cards), c);
		assertTrue(g.getChecklist().contains(c));
	}

	@Test
	public void notRefuted() {
		Game g = new Game(new TextClient(), 2);
		List<Player> players = g.getPlayers();
		players.get(1).giveCard(new CharacterCard("Mrs White"));
		List<Card> cards = new ArrayList<>();
		cards.add(new CharacterCard("The Reverend Green"));
		cards.add(new WeaponCard("rope"));
		cards.add(new RoomCard("Hall"));
		assertTrue(g.refute(cards) == null);
	}

	@Test
	public void rightAccusation() {
		Game g = mock2PlayerGame();
		List<Card> sol = solutionA();
		g.setSolution(sol);
		assertTrue(g.accusationCorrect(sol));
	}

	@Test
	public void wrongAccusation() {
		Game g = mock2PlayerGame();
		g.setSolution(solutionB());
		assertFalse(g.accusationCorrect(solutionA()));
	}

	@Test
	public void makeWeaponMap() {
		Game g = mock2PlayerGame();
		g.placeWeapons();
		for (Card c : g.getDeck().weapons) {
			assertTrue(g.getWeapons().get(c.getName()) != null);
		}
	}

	@Test
	public void validMove() {
		Game g = mock2PlayerGame();
		Player player1 = g.getPlayers().get(0);
		player1.setRow(7);
		player1.setCol(0);
		int prevRow = player1.getRow();
		int prevCol = player1.getCol();
		// Move east
		g.getBoard().moveValid(player1.getRow(), player1.getCol(), player1.getRow(), player1.getCol() + 1, player1);

		assertTrue(player1.getRow() == 7);
		assertTrue(player1.getCol() == 1);// player has registered as moved
		// New tile has registered player on it
		assertTrue(g.getBoard().getTile(player1.getRow(), player1.getCol()).getToken().equals(player1));
		// old tile has registered player has left
		assertNull(g.getBoard().getTile(prevRow, prevCol).getToken());
	}

	@Test
	public void invalidMove() {
		Game g = mock2PlayerGame();
		Player p1 = g.getPlayers().get(0);
		p1.setRow(7);
		p1.setCol(0);
		g.getBoard().getTile(7, 0).setToken(p1);
		Player p2 = g.getPlayers().get(1);
		p2.setRow(7);
		p2.setCol(1);
		g.getBoard().getTile(7, 1).setToken(p2);
		assertFalse(canGoEast(g, p1));
		assertFalse(canGoWest(g, p2));
		assertFalse(canGoSouth(g, p1));
		assertFalse(canGoNorth(g, p2));
	}

	@Test
	public void placeWeapons() {
		Game g = mock2PlayerGame();
		g.placeWeapons();
		Board b = g.getBoard();
		for (Weapon w : g.getWeapons().values()) {
			assertTrue(b.currentRoom(w) != null);
		}
	}

	@Test
	public void boardGood() {
		Game g = mock2PlayerGame();
		Board board = g.getBoard();
		assertEquals(9, board.getRooms().size());
		for (Room r : board.getRooms()) {
			assertTrue(g.cardFromString(r.getName()) != null);
		}
	}

	// ========== Helper methods ========== //

	public Game mock2PlayerGame() {
		Game g = new Game(new TextClient(), 2);
		List<Player> players = g.getPlayers();
		players.get(0).giveCard(new WeaponCard("revolver"));
		players.get(1).giveCard(new CharacterCard("Mrs White"));
		return g;
	}

	public List<Card> solutionA() {
		List<Card> sol = new ArrayList<>();
		sol.add(new CharacterCard("Professor Plum"));
		sol.add(new RoomCard("Study"));
		sol.add(new WeaponCard("candlestick"));
		return sol;
	}

	public List<Card> solutionB() {
		List<Card> sol = new ArrayList<>();
		sol.add(new CharacterCard("Mrs White"));
		sol.add(new RoomCard("Hall"));
		sol.add(new WeaponCard("revolver"));
		return sol;
	}

	public boolean canGoNorth(Game g, Player p) {
		return g.getBoard().moveValid(p.getRow(), p.getCol(), p.getRow() - 1, p.getCol(), p);
	}

	public boolean canGoSouth(Game g, Player p) {
		return g.getBoard().moveValid(p.getRow(), p.getCol(), p.getRow() + 1, p.getCol(), p);
	}

	public boolean canGoWest(Game g, Player p) {
		return g.getBoard().moveValid(p.getRow(), p.getCol(), p.getRow(), p.getCol() - 1, p);
	}

	public boolean canGoEast(Game g, Player p) {
		return g.getBoard().moveValid(p.getRow(), p.getCol(), p.getRow(), p.getCol() + 1, p);
	}
}
