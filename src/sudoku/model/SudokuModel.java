/**
 * @author Morten Beuchert
 */

package sudoku.model;

import java.util.ArrayList;

public interface SudokuModel {
	public void solveSingle(int[][] puzzle);
	public void solveBatch(ArrayList<int[][]>puzzles);
	public void setError(String msg);
}