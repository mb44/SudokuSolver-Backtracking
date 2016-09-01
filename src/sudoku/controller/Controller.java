/**
 * @author Morten Beuchert
 */

package sudoku.controller;

import sudoku.model.SudokuModel;
import sudoku.view.SudokuGUI;

public class Controller {
	private SudokuModel model;
	private SudokuGUI view;
	
	public Controller(SudokuModel model, SudokuGUI view) {
		this.model = model;
		this.view = view;
	}
	
	public void execute(int mode) {
		if (mode == -1) {
			return;
		} else if (mode == view.SINGLE_SOLVE_MODE) {
			executeSolveSingle();
		} else if (mode == view.BATCH_SOLVE_MODE) {
			executeSolveBatch();
		}
	}
	
	public void executeSolveSingle() {
		String sudoku = view.getInput();
		
		int p[][] = new int[9][9];
		
		for (int i=0; i<sudoku.length(); i++) {
			/*
			if (su.charAt(i) < '0' || su.charAt(i) > '9') {
				model.setError("Error reading puzzle");
				System.out.println("break");
				return;
			}
			*/
			
			int row = i / 9;
			int col = i % 9;
			p[row][col] = Character.getNumericValue(sudoku.charAt(i));
		}
		
		model.solveSingle(p);
	}
	
	public void executeSolveBatch() {
		//String suduko = view.getInput();
	}
}