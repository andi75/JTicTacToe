import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TicTacViewController implements ActionListener {
	static final String version = "1.0.1";
	
	TicTacView views[] = new TicTacView[9];
	TicTacModel model;
	
	JLabel winLabel;
	JFrame frame;
	JButton clearButton;

	int xOrig = 30; int yOrig = 30;
	int xOffset = 10; int yOffset = 10;
	int size = 135;
	
	int buttonSpacing = 60;
	int buttonWidth = 80;
	int buttonHeight = 20;
	
	public TicTacViewController()
	{
		model = new TicTacModel();
		
		JFrame frame = new JFrame("TicTacToe");
		frame.setLayout(null);
		frame.pack();
		// For some reason, Insets are not available
		// before frame.pack() is called
		Insets insets = frame.getInsets();
		System.out.println(insets);
		
		int contentWidth = 2*xOrig + 2*xOffset + 3 * size;
		int contentHeight = 2*yOrig + 3 * yOffset + 3 * size +
				buttonSpacing + buttonHeight;
		int width = contentWidth + insets.left + insets.right;
		int height = contentHeight + insets.top + insets.bottom;
		
		frame.setBounds(100, 100, width, height);
		
		this.frame = frame;
		
		JLabel winLabel = new JLabel("");
		winLabel.setVisible(false);
		frame.add(winLabel);
		this.winLabel = winLabel;
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(frame.getWidth() / 2 - buttonWidth / 2, yOrig + 3 * yOffset + 3 * size + buttonSpacing, buttonWidth, buttonHeight);
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
