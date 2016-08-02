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
}
