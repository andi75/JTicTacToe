import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TicTacViewController implements ActionListener {
	
	TicTacView views[] = new TicTacView[9];
	TicTacModel model;
	
	JLabel winLabel;
	JFrame frame;
	JButton clearButton;

	int xOrig = 30; int yOrig = 30;
	int xOffset = 10; int yOffset = 10;
	int size = 135;
	
	public TicTacViewController()
	{
		model = new TicTacModel();
		
		JFrame frame = new JFrame("TicTacToe");
		frame.setBounds(100, 100, 2*xOrig + 2*xOffset + 3 * size, 600);
		this.frame = frame;
		
		JLabel winLabel = new JLabel("");
		winLabel.setVisible(false);
		frame.add(winLabel);
		this.winLabel = winLabel;
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(frame.getWidth() / 2 - 40, yOrig + 3 * yOffset + 3 * size + 60, 80, 20);
		clearButton.addActionListener(this);
		frame.add(clearButton);
		this.clearButton = clearButton;
		
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				int id = 3 * y + x;
				TicTacView view = new TicTacView(id, this);
				view.setBounds(xOrig + (xOffset + size) * x, yOrig + (yOffset + size) * y, size, size);
				view.addMouseListener(view);
				frame.add(view);
				views[id] = view;
			}
		}
		frame.setLayout(null);
		frame.setVisible(true);		
	}
	
	public void update()
	{
		for(int i = 0; i < 9; i++)
		{
			views[i].setHighlight(model.winSquare[i]);
			views[i].setState(model.board[i]);
			
		}
	}

	public void viewClicked(int id) {
		if(model.canMove && model.board[id] == TicTacModel.EMPTY)
		{
			model.board[id] = model.isCrossTurn ? TicTacModel.CROSS : TicTacModel.CIRCLE;
			model.isCrossTurn = !model.isCrossTurn;
			
			if(model.checkForWin())
			{
				model.canMove = false;
				winLabel.setText( (model.winner == TicTacModel.CIRCLE ? "Circle " : "Cross") + " wins!");
				winLabel.setSize( winLabel.getPreferredSize() );
				winLabel.setBounds( frame.getWidth() / 2 - winLabel.getWidth() / 2, yOrig + 3 * yOffset + 3 * size,
						winLabel.getWidth(), winLabel.getHeight());
				winLabel.setVisible(true);
				
			}
		}
		update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		winLabel.setVisible(false);
		model.clear();
		update();
	}
}
