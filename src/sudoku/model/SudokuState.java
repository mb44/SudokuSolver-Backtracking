/**
 * @author Morten Beuchert
 */

package sudoku.model;

import java.util.ArrayList;

public class SudokuState {
	private ArrayList<int[][]> puzzles;
	private boolean singlePuzzleMode;
	
	public SudokuState() {
		singlePuzzleMode = true;
		puzzles = new ArrayList<int[][]>();
	}
	
	public SudokuState(int[][] puzzle) {
		this();
		puzzles = new ArrayList<int[][]>();
		puzzles.add(puzzle);
	}

	public void setPuzzle(int[][] puzzle) {
		puzzles.clear();
		puzzles.add(puzzle);
	}
	
	public int[][] getPuzzle() {
		return puzzles.get(0);
	}
	
	public void setPuzzles(ArrayList<int[][]> puzzles) {
		puzzles.clear();

		for (int[][] p : puzzles)
			this.puzzles.add(p);
	}
	
	public ArrayList<int[][]> getPuzzles() {
		return puzzles;
	}
	
	public void setSinglePuzzleMode() {
		singlePuzzleMode = true;
	}
	
	public void setBatchPuzzleMode() {
		singlePuzzleMode = false;
	}
	
	public boolean isSinglePuzzleMode() {
		return singlePuzzleMode;
	}
	
	public boolean isBatchPuzzleMode() {
		return !singlePuzzleMode;
	}
}