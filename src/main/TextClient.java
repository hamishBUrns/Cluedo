package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextClient {

	public String readString(String msg){
		System.out.println(msg);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {
				return input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("I/O Error! " + e.getMessage());
			}
		}
	}

	public int readInt(String msg){
		System.out.println(msg);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {
				String num = input.readLine();
				if(num == null){
					throw new IOException();
				}
				return Integer.parseInt(num);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("I/O Error! " + e.getMessage());
			}
		}
	}

	public void help(){
		System.out.println("Command list:");
		System.out.println("checklist: see cards that have been checked and cannot be part of the solution");
		System.out.println("hand: see what cards are in your hand");
		System.out.println("suggest: make a suggestion by stating a character and weapon (must be in a room)");
		System.out.println("accuse: make an accusation by stating a character, room, and weapon");
		System.out.println("end: ends your turn");
	}
}
