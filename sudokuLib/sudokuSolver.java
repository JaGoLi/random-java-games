package sudokuLib;
import java.util.*;

public class sudokuSolver {

	private static int side = 9;
	private static int square = 3;

	public static void mainCode() {
		getuInput();
		Algo Solution = new Algo(uInput);
		Solution.solve();
	}

	public static int[][] uInput = new int[9][9];

	private static void getuInput() {
		int x, y;

		System.out.println();
		System.out.println("For each row, enter the numbers of the sudoku grid");
		System.out.println("For empty squares, enter zero");
		System.out.println();

		for (y = 0; y < side; y++) {
			System.out.printf("Enter values for row " + (y+1) + ": ");
			Scanner lineInts = new Scanner(System.in);
			for (x = 0; x < side; x++) {
				uInput[y][x] = lineInts.nextInt();
			}
		}

	}

	public static class Algo {

		public static int[][] board;

		private static boolean[] posNums = new boolean[81];

		public Algo(int[][] board) {
			this.board = board;
		}

		private static boolean checkValid(int row, int col, int value) {
			int x, y;

			//check row
			for (x = 0; x < side; x++) {
				if(board[row][x] == value)
					return false;
			}

			//check column
			for (y = 0; y < side; y++) {
				if(board[y][col] == value)
					return false;
			}

			//check box
			int startx = col - col % square;
			int starty = row - row % square;
			for (x = startx; x < startx + square; x++) {
				for (y = starty; y < starty + square; y++) {
					if (board[y][x] == value)
						return false;
				}
			}

			//checks passed
			return true;

		}

		private static int numsToNum(int y, int x) {
			int num;
			num = (int) side * y + x;
			return num;
		}

		private static boolean initialize() {
			int x, y, i;

			for (y = 0; y < side; y++) {
				for(x = 0; x < side; x++) {
					if (board[y][x] > 0) {
						int curNum = numsToNum(y, x);
						posNums[curNum] = true;
					}
				}
			}

			for(y = 0; y < side; y++) {
				for (x = 0; x < side; x++) {
					int curNum = numsToNum(y, x);
					if (!posNums[curNum]) {
						boolean oneSol = false;
						int firstSol = 0;
						for (i = 1; i <= 9; i++) {
							if (checkValid(y, x, i)) {
								if (!oneSol) {
									oneSol = true;
									firstSol = i;
								} else {
									oneSol = false;
									break;
								}
							}
						}
						if (oneSol) 
							board[y][x] = firstSol;
					}
				}
			}
			return true;

		}

		private static boolean crunch() {

			int x, y, i;
			boolean isComplete = true;
			int curx = -1;
			int cury = -1;

			//get next null value
			for (y = 0; y < side; y++) {
				for (x = 0; x < side; x++) {
					if (board[y][x] == 0) {
						cury = y;
						curx = x;
						isComplete = false;
						break;
					}
				}
				if (!isComplete)
					break;
			}

			if (isComplete)
				return true;

			//check for available solutions
			for (i = 1; i <= 9; i++) {
				if (checkValid(cury, curx, i)) {
					board[cury][curx] = i;
					if (crunch())
						return true;
					else board[cury][curx] = 0;
				}
			}

			return false;

		}

		public static boolean solve() {
			initialize();
			if (crunch()) {
				printSol(board);
				return true;
			}
			else System.out.println("Unsolvable Sudoku!");
			return false;
		}

	}


	public static void printSol(int[][] solution) {
		int x, y;
		System.out.println();
		for (y = 0; y < side; y++) {
			for (x = 0; x < side; x++) {
				System.out.print(solution[y][x] + " ");
				if (((x+1) % square == 0) && (x != side - 1))
					System.out.print("| ");
			}
			System.out.println();
			if (((y+1) % square == 0) && (y != side - 1))
				System.out.println("------|-------|------");
		}
		System.out.println();
	}

}
