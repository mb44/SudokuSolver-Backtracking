/**
 * @author Morten Beuchert
 */

package sudoku.model;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import sudoku.model.game.SudokuGame;
import sudoku.util.Observable;

public class SudokuManager extends Observable implements SudokuModel, Callable<Void> {
	private SudokuGame game;
	private SudokuState state;
	private String error;

	public SudokuManager() {
		game = new SudokuGame();
		state = new SudokuState();
		error = "";
	}
	
	@Override
	public Void call() {
		game.solve();
		return null;
	}

	@Override
	public void solveSingle(int[][] puzzle) {
		ExecutorService executor = Executors.newCachedThreadPool();
		// Set the puzzle
		game.setPuzzle(puzzle);
		// Solve the puzzle
		Future<Void> future = executor.submit(this);
		
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		state.setPuzzle(puzzle);
		notifyObservers();
		executor.shutdown();
	}

	@Override
	public void solveBatch(ArrayList<int[][]> puzzles) {
		//state.setPuzzles(puzzles);
	}

	public void setError(String msg) {
		this.error = msg;
	}
	
	public SudokuState getState() {
		return state;
	}
}