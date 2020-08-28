import java.util.Scanner;
import sudokuLib.*;
import fbLib.*;
import sha256Lib.*;

public class Main {

	public static class Gameslist {
		String[] game = {
			"FizzBuzz", 
			"Sudoku",
			"WordGuesser",
		};
	}

	public static void main(String[] args) throws Exception {
		Gameslist Games = new Gameslist();
		System.out.println("Select a game: ");
		for (int i = 0; i <= 2; i++) {
			System.out.println((i+1) + ": " + Games.game[i]);
		}
		System.out.println();

		System.out.printf("Select a game (number): "); 
		Scanner selGame = new Scanner(System.in);
		try {
			int gameNum = selGame.nextInt();
			
			switch(gameNum) {
				case 1:
					FizzBuzz.mainCode();
					break;

				case 2:
					sudokuSolver.mainCode();
					break;

				case 3:
					sha256.mainCode();
					break;
				default:
					System.out.println("Incorrect value ...exiting");
			}
		}
		catch (Exception msg) {
			System.out.println("Incorrect value ...exiting");
		}
		return;
	}
}
