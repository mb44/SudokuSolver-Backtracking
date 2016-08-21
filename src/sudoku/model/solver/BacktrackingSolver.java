/**
 * @author Morten Beuchert
 */

package sudoku.model.solver;

import sudoku.model.sudoku.SudokuGame;

public class BacktrackingSolver implements SudokuSolver {
	public BacktrackingSolver(SudokuGame sudoku) {
	}
	
	public void solve(int[][] puzzle) {
		solveSudoku(puzzle, 0, 0);
	}

	public boolean solveSudoku(int board[][], int x, int y) {
		int e[] = getNextEmptySquare(board, x, y);
		if (e == null) {
			return true;
		} else {
			int newX = e[0];
			int newY = e[1];

			for (int i = 1; i <= 9; i++) {
				board[newY][newX] = i;
				if (isSafe(board, newX, newY)) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException exc) {
						exc.printStackTrace();
					}
					boolean res = solveSudoku(board, newX, newY);
					// Return if success, yay!
					if (res)
						return true;
				}
				// Failure: unmake and try another number
				board[newY][newX] = 0;
			}
			// This triggers backtracking
			return false;
		}
	}

	public boolean isSolved(int[][] cells) {
		// Check all grids
		for (int x = 0; x < 9; x += 3) {
			for (int y = 0; y < 9; y += 3) {
				boolean res = checkGrid(cells, x, y);
				if (!res)
					return false;
			}
		}
		return true;
	}

	private boolean isSafe(int board[][], int x, int y) {
		if (!checkCol(board, x) || !checkRow(board, y) || !checkGrid(board, x, y)) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkCol(int board[][], int x) {
		// represent used numbers (1-9)
		boolean used[] = { false, false, false, false, false, false, false, false, false, false, false, false };

		// Test all cells in this column
		for (int i = 0; i < 9; i++) {
			if (board[i][x] == 0) {
				continue; // empty cell
			}
			if (!used[board[i][x] - 1]) {
				used[board[i][x] - 1] = true;
			} else {
				return false; // number is already represented elsewhere in this
								// column
			}
		}
		return true; // no duplicates found
	}

	private boolean checkRow(int res[][], int y) {
		// represent used numbers (1-9)
		boolean used[] = { false, false, false, false, false, false, false, false, false, false, false, false };

		for (int i = 0; i < 9; i++) {
			if (res[y][i] == 0) {
				continue; // empty cell
			}
			if (!used[res[y][i] - 1]) {
				used[res[y][i] - 1] = true;
			} else {
				return false; // number is already represented elsewhere in this
								// row
			}
		}
		return true; // no duplicates found
	}

	private boolean checkGrid(int res[][], int x, int y) {
		int gridX = x / 3; // 0-2
		int gridY = y / 3; // 0-2
		// represent used numbers (1-9)
		boolean used[] = { false, false, false, false, false, false, false, false, false };

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				// column offset: gridX * 3
				int colOffset = gridX * 3;
				// row offset: gridY * 3
				int rowOffset = gridY * 3;

				if (res[row + rowOffset][col + colOffset] == 0) {
					continue; // empty cell
				}
				if (!used[res[row + rowOffset][col + colOffset] - 1]) {
					// first time we encounter number in this grid
					used[res[row + rowOffset][col + colOffset] - 1] = true;
				} else {
					return false; // number is already represented elsewhere in
									// this grid
				}
			}
		}
		return true; // no duplicates found
	}

	private int[] getNextEmptySquare(int board[][], int x, int y) {
		int res[] = new int[2];

		// First check starting a column=x
		for (int col = x; col < 9; col++) {
			if (board[y][col] == 0) {
				res[0] = col;
				res[1] = y;
				return res;
			}
		}

		// Then check remaining columns and rows
		for (int row = y + 1; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == 0) {
					res[0] = col;
					res[1] = row;
					return res;
				}
			}
		}

		// Return null means we are done!
		return null;
	}
}
