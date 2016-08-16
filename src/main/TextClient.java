package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextClient {

	/**
	 * reads and returns a string from the user
	 * @param msg
	 * @return
	 */
	public String readString(String msg) {
		System.out.println(msg);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				return input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("I/O Error! " + e.getMessage());
			}
		}
	}

	/**
	 * reads and returns an int from the user
	 * @param msg
	 * @return
	 */
	public int readInt(String msg) {
		System.out.println(msg);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				String num = input.readLine();
				if (num == null) {
					throw new IOException();
				}
				return Integer.parseInt(num);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("I/O Error! " + e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("Please input a number");
			}
		}
	}

	/**
	 * print the list of keys used to represent the board
	 */
	public void printBoardKeys() {
		System.out.println("//Board Kyes//");
		System.out.println("_ is a floor, W is a wall, D is a door, lowercase letters are rooms");
		System.out.println(
				"k is kitchen, b is ballroom, c is conservatory, d is dining room, r is billiard room, l is library, n is lounge, h is hall, s is study");
		System.out.println("Rope is ~, dagger is ^, candlestick is $, Leadpipe is (, revolver is @, Spanner is *");
		System.out.println("Players are represented by a number in the order in which they joined.");
		System.out.println("0 for NPCs.");
	}

	/**
	 * print out the help list of commands
	 */
	public void help() {
		System.out.println("Command list:");
		System.out.println("checklist: see cards that have been checked and cannot be part of the solution");
		System.out.println("hand: see what cards are in your hand");
		System.out.println("suggest: make a suggestion by stating a character and weapon (must be in a room)");
		System.out.println("accuse: make an accusation by stating a character, room, and weapon");
		System.out.println("end: ends your turn");
		System.out.println("keys: show board keys");
		System.out.println("help: show command list");
	}

	/**
	 * print a bunch of blank lines to pseudo-clear the console
	 */
	public void printLines(){
		for(int i=0; i<50; i++){
			System.out.println();
		}
	}
}
