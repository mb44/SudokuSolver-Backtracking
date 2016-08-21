/**
 * @author Morten Beuchert
 */

package sudoku;

import sudoku.controller.Controller;
import sudoku.model.SudokuManager;
import sudoku.model.SudokuModel;
import sudoku.model.solver.BacktrackingSolver;
import sudoku.model.sudoku.SudokuGame;
import sudoku.view.SudokuGUI;

public class Program {
	public static void main(String[] args) {
		SudokuModel model = new SudokuManager();
		SudokuGUI view = new SudokuGUI((SudokuManager)model);
		((SudokuManager)model).add(view);
		Controller controller = new Controller(model, view);
		view.startGUI(controller);
	}
}