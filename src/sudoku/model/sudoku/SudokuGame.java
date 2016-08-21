/**
 * @author Morten Beuchert
 */

package sudoku.model.sudoku;

import sudoku.model.solver.BacktrackingSolver;
import sudoku.model.solver.SudokuSolver;

public class SudokuGame {
	private int[][] puzzle;
	private SudokuSolver solver;
	
	public SudokuGame() {
		puzzle = new int[9][9];
		solver = new BacktrackingSolver(this);
	}
	
	public void solve() {
		solver.solve(puzzle);
		
		for (int row=0; row<9; row++) {
			for (int col=0; col<9; col++) {
				System.out.print(puzzle[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	public void setPuzzle(int[][] puzzle) {
		this.puzzle = puzzle;
	}
	
	public int[][] getPuzzle() {
		return puzzle;
	}
}