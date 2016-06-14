package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.Couleur;
import tools.ChessImageProvider;
import tools.ChessPiecePos;

public class ChessGameGUI extends JFrame implements MouseListener,
		MouseMotionListener, Observer {

	ChessGameControlers chessGameControler;
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	Dimension boardSize;
	Point parentLocation;

	public ChessGameGUI(String title,ChessGameControlers chessGameControler,Dimension dim) {
		boardSize = dim;
		this.setTitle(title);
		this.chessGameControler = chessGameControler;
		// Use a Layered Pane for this application
		// With it, we can have objects on top of each other : the pieces
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		// Add a chess board to the Layered Pane

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		this.pack();
		chessBoard.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
			else
				square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
		}
		
		for(ChessPiecePos c : ChessPiecePos.values()){
			for(Coord co : c.coords){
				JLabel piece = new JLabel(new ImageIcon(
						ChessImageProvider.getImageFile(c.nom, c.couleur)));
				JPanel panel = (JPanel) chessBoard.getComponent(co.x + (co.y*8));
				panel.add(piece);
			}
		}
		
	}

	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	// Move the chess piece around

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null)
			return;
		chessPiece
				.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	}

	// Drop the chess piece back onto the chess board

	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null)
			return;		
		chessPiece.setVisible(false);
		if(chessGameControler.move(translateCoord(parentLocation.x, parentLocation.y) , translateCoord(e.getX(),e.getY())))
		{
			Component c = chessBoard.findComponentAt(e.getX(), e.getY());
			if (c instanceof JLabel) {
				Container parent = c.getParent();
				parent.remove(0);
				parent.add(chessPiece);
			} else {
				Container parent = (Container) c;
				parent.add(chessPiece);
			}
		}
		else{
			Component c = chessBoard.findComponentAt(parentLocation.x, parentLocation.y);
			if (c instanceof JLabel) {
				Container parent = c.getParent();
				parent.remove(0);
				parent.add(chessPiece);
			} else {
				Container parent = (Container) c;
				parent.add(chessPiece);
			}
		}
		chessPiece.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public static void main(String[] args) {
		JFrame frame = new ChessGameGUI();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(chessGameControler.getMessage() + "\n");	

		
	}
	
	private Coord translateCoord(int x, int y){
		Coord coord = null;
		int xC = x / chessPiece.getWidth();
		int yC = y / chessPiece.getHeight();
		coord = new Coord(xC,yC);
		return coord;
	}

}