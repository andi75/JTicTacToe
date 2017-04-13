import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TicTacView extends JPanel implements MouseListener {
	Image circle = null, cross = null;
	
	final int STATE_EMPTY = 0;
	final int STATE_CROSS = 1;
	final int STATE_CIRCLE = 2;
	
	boolean isHighlighted = false;
	int state = STATE_EMPTY;
	
	int id;
	TicTacViewController viewController;
	
	public TicTacView(int id, TicTacViewController viewController)
	{
		this.id = id;
		this.viewController = viewController;
		
		try {
		    circle = ImageIO.read(getClass().getResource("circle.png"));
		    cross = ImageIO.read(getClass().getResource("cross.png"));
		} catch (IOException e) {
			System.out.println(e);
		}		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		switch(state)
		{
		case STATE_CROSS:
			g.drawImage(cross, 0, 0, this.getWidth(), this.getHeight(), this);
			break;
		case STATE_CIRCLE:
			g.drawImage(circle, 0, 0, this.getWidth(), this.getHeight(), this);
			break;
		case STATE_EMPTY:
			g.setColor(Color.white);
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 5, 5);
			break;
		}
		if(isHighlighted)
		{
			Graphics2D g2 = (Graphics2D) g;
			g.setColor( new Color(0, 127, 0) );
			g2.setStroke( new BasicStroke(3.0f) );
			g2.draw(new RoundRectangle2D.Double(10.0,  10.0,  this.getWidth() - 20.0, this.getHeight() - 20.0, 5.0, 5.0 ) );
		}
	}
	
	public void setState(int state)
	{
		this.state = state;
		repaint();
	}

	public void setHighlight(boolean isHighlighted) {
		this.isHighlighted = isHighlighted;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println(e);
		viewController.viewClicked(this.id);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println(e);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// System.out.println(e);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// System.out.println(e);
	}

}
