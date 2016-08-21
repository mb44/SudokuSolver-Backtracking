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
		if (mode == -1 ) {
			return;
		} else if (mode == view.SINGLE_SOLVE_MODE) {
			executeSolveSingle();
		} else if (mode == view.BATCH_SOLVE_MODE) {
			executeSolveBatch();
		}
	}
	
	public void executeSolveSingle() {
		String su = view.getInput();
		
		int p[][] = new int[9][9];
		
		for (int i=0; i<su.length(); i++) {
			/*
			if (su.charAt(i) < '0' || su.charAt(i) > '9') {
				model.setError("Error reading puzzle");
				System.out.println("break");
				break;
			}
			*/
			
			int row = i / 9;
			int col = i % 9;
			p[row][col] = Character.getNumericValue(su.charAt(i));
		}
		
		/*
		for (int row=0; row<9; row++) {
			for (int col=0; col<9; col++) {
				System.out.print(p[row][col] + " ");
			}
			System.out.println();
		}
		*/
		
		model.solveSingle(p);
	}
	
	public void executeSolveBatch() {
		//String su = view.getInput();
	}
}