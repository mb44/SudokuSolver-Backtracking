/**
 * @author Morten Beuchert
 */

package sudoku.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import sudoku.controller.Controller;
import sudoku.model.SudokuManager;
import sudoku.model.SudokuState;
import sudoku.util.ObserverBase;

public class SudokuGUI extends JFrame implements ObserverBase {
	public static final int NEW_SINGLE_MODE = 0;
	public static final int NEW_BATCH_MODE = 1; 
	public static final int SINGLE_SOLVE_MODE = 2;
	public static final int BATCH_SOLVE_MODE = 3;
	
	private final static String START_PANEL = "Card choose mode";
	private final static String SINGLE_SOLVE_PANEL = "Card one Sudoku";
	private final static String BATCH_SOLVE_PANEL = "Card Sudoku batch";
	
	private Controller controller;
	private SudokuManager manager;
	private int mode;
	private JPanel cardPanel;
	private JPanel startPanel;
	private JPanel singlePanel;
	private JPanel topPanelSingle;
	private JPanel bottomPanelSingle;
	private JPanel batchPanel;
	// CardLayout
	private CardLayout cardLayout;
	// Status
	private JPanel statusPanel;
	private JLabel statusLabel;
	private JButton buttonSolve;
	private JButton buttonNewSingle;
	private JButton buttonNewBatch;
	private JTextField[] cells;
	
	// Menu
	private JMenuBar menuBar;
	private JMenu fileMenu, helpMenu;
	private JMenuItem newSudoku, newSudokuBatch, exit;
	private JMenuItem about;

	public SudokuGUI(SudokuManager manager) {
		super("Sudoku Solver");
		this.manager = manager;
		createComponents();
		initializeComponents();
		addComponentsToFrame();
		pack();
	}

	public int getMode() {
		return mode;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	private void newSingle() {
		statusLabel.setText("enter puzzle...");
		for (int i = 0; i < 81; i++) {
			cells[i].setText("");
		}
		cardLayout.show(cardPanel, SINGLE_SOLVE_PANEL);
	}
	
	private void newBatch() {
		cardLayout.show(cardPanel, BATCH_SOLVE_PANEL);
	}

	private void displayAboutDialog() {
		JOptionPane.showMessageDialog(this,
			    "Sudoku Solver\nFor more programs visit:  http://www.github.com/mb44",
			    "About",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	public void startGUI(Controller controller) {
		this.controller = controller;
		EventHandler eventHandler = new EventHandler();
		buttonNewSingle.addActionListener(eventHandler);
		buttonNewBatch.addActionListener(eventHandler);
		buttonSolve.addActionListener(eventHandler);
		newSudoku.addActionListener(eventHandler);
		newSudokuBatch.addActionListener(eventHandler);
		about.addActionListener(eventHandler);
		exit.addActionListener(eventHandler);
		mode = -1;
		setVisible(true);
	}

	private void createComponents() {
		// Initialize statusPanel
		statusPanel = new JPanel();
		statusPanel.setBorder((Border) new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		statusPanel.setLayout((LayoutManager) new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		// Initialize startPanel
		startPanel = new JPanel();
		startPanel.setPreferredSize(new Dimension(400, 350));
		startPanel.setBackground(Color.BLACK);
		buttonNewSingle = new JButton("New Sudoku");
		buttonNewBatch = new JButton("New Sudoku batch");
		// Initialize topPanel
		topPanelSingle = new JPanel();
		topPanelSingle.setPreferredSize(new Dimension(400, 50));
		topPanelSingle.setBackground(Color.BLACK);
		// Initialize bottomPanel
		bottomPanelSingle = new JPanel();
		bottomPanelSingle.setBackground(Color.BLACK);
		bottomPanelSingle.setPreferredSize(new Dimension(400, 300));
		buttonSolve = new JButton("Solve Sudoku");
		// Initialize singlePanel
		singlePanel = new JPanel(new BorderLayout());
		singlePanel.setPreferredSize(new Dimension(400, 350));
		singlePanel.add(topPanelSingle, BorderLayout.NORTH);
		singlePanel.add(bottomPanelSingle, BorderLayout.CENTER);
		singlePanel.add(statusPanel, BorderLayout.SOUTH);
		// Initialize batchPanel
		batchPanel = new JPanel();
		batchPanel.setPreferredSize(new Dimension(400, 350));
		batchPanel.setBackground(Color.BLACK);
		JLabel labelNoSupport = new JLabel("Batch solve not supported yet!");
		labelNoSupport.setForeground(Color.WHITE);
		batchPanel.add(labelNoSupport);
		
		
		Font cellFont = new Font("Times", Font.BOLD, 18);
		// Initialize input text fields
		cells = new JTextField[81];
		for (int i = 0; i < 81; i++) {
			cells[i] = new JTextField();
			cells[i].setHorizontalAlignment(JTextField.CENTER);
			cells[i].setFont(cellFont);
			cells[i].setBackground(Color.BLACK);
			cells[i].setForeground(Color.GREEN);
		}
		bottomPanelSingle.setLayout(new GridLayout(9, 9, 10, 10));
		
		// Menu bar
		menuBar = new JMenuBar();
		
		// File menu
		fileMenu = new JMenu("New");
		newSudoku = new JMenuItem("New Sudoku");
		newSudokuBatch = new JMenuItem("New Sudoku batch");
		exit = new JMenuItem("Exit");
		fileMenu.add(newSudoku);
		fileMenu.add(newSudokuBatch);
		fileMenu.add(exit);
		
		// Help menu
		helpMenu = new JMenu("Help");
		about = new JMenuItem("About");
		helpMenu.add(about);
		
		// Add menus to menubar
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
	}

	private void initializeComponents() {
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
	}
	
	private void addComponentsToFrame() {
		startPanel.add(buttonNewSingle);
		startPanel.add(buttonNewBatch);
		// Add components to topPanel
		topPanelSingle.add(buttonSolve);
		// Add components to bottomPanel
		for (int i = 0; i < 81; i++) {
			bottomPanelSingle.add(cells[i]);
		}

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setPreferredSize(new Dimension(400, 350));

		// Add panels to the main panel
		setJMenuBar(menuBar);

		cardPanel.add(startPanel, START_PANEL);
		cardPanel.add(singlePanel, SINGLE_SOLVE_PANEL);
		cardPanel.add(batchPanel, BATCH_SOLVE_PANEL);
		
		cardLayout.show(cardPanel, START_PANEL);
		getContentPane().add(cardPanel);
	}

	public String getInput() {
		if (mode == SINGLE_SOLVE_MODE) {
			StringBuilder res = new StringBuilder();

			for (int i = 0; i < 81; i++) {
				if (cells[i].getText().equals("")) {
					res.append("0");
				} else {
					res.append(Integer.parseInt(cells[i].getText()));
				}
			}
			
			return res.toString();
		} else if(mode == BATCH_SOLVE_MODE) {
			return null;
		}
		
		return null;
	}

	@Override
	public void update() {
		SudokuState state = manager.getState();
		
		if (state.isSinglePuzzleMode()) {
			displayResult(state.getPuzzle());
		} else {
			// Handle batchPuzzleMode
		}
	}
	
	private void displayResult(int[][] result) {
		statusLabel.setText("solved!");
		for (int row=0; row<9; row++) {
			for (int col=0; col<9; col++) {
				int index = row * 9 + col;
				cells[index].setText("" + result[row][col]);
			}
		}
	}
	
	public class EventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			
			if (source == buttonNewSingle || source == newSudoku) {
				setMode(NEW_SINGLE_MODE);
				newSingle();
			} else if (source == exit) {
				System.exit(1);
			} else if (source == buttonNewBatch || source == newSudokuBatch) {
				setMode(NEW_BATCH_MODE);
				newBatch();
			} else if (source == buttonSolve) {
				//statusLabel.setText("Solving. Please wait...");
				setMode(SINGLE_SOLVE_MODE);
				controller.execute(SINGLE_SOLVE_MODE);
			} else if (source == about) {
				displayAboutDialog();
			}
		}
	}
}