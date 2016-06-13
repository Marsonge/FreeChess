package model;

import java.util.ArrayList;
import java.util.List;

public class Echiquier implements BoardGames {

	private Jeu w;
	private Jeu b;
	private Jeu current;
	private String message;

	public Echiquier() {
		w = new Jeu(Couleur.BLANC);
		b = new Jeu(Couleur.NOIR);
		current = w;
		this.setMessage("Game start : White to play");
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		return current.getCouleur();
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		if(w.getPieceColor(x, y) != null)
			return w.getPieceColor(x,y);
		if(b.getPieceColor(x,y) != null)
			return b.getPieceColor(x, y);
		return null;
	}

	public void switchPlayer() {
		if (current == w) {
			current = b;
		} else {
			current = w;
		}
	}

	private void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return w.toString() + "\n" + b.toString() + "\nThe current player is "
				+ current.getCouleur();
	}

	public static void main(String args[]) {
		Echiquier e = new Echiquier();
		System.out.println(e);
		e.move(1,6,1,4);
		System.out.println(e);
	}

	public boolean isMoveOk(int xi, int yi, int xf, int yf) {
		if(current.isMoveOk(xi, yi, xf, yf, true, true)){
			String name = current.getPieceName(xi, yi);
			if(name.equals("Bishop")){
				if(isBishopBlocked(xi,yi,xf,yf))
					return false;
			}
			if(w.isPieceHere(xf, yf)){
				if(current==w)
					return false;
			}
			else if(b.isPieceHere(xf, yf)){
				if(current==b)
					return false;
			}
			return true;
		}
		return false;
	}

	private boolean isBishopBlocked(int xi, int yi, int xf, int yf) {
		int incrx = xi - xf;
		if(incrx > 0)
			incrx = -1;
		else
			incrx = 1;
		int incry = yi - yf;
		if(incry > 0)
			incry = -1;
		else
			incry = 1;
		int i = xi + incrx;
		int j = yi + incry;
		while(i != xf && j != yf){
			if(w.isPieceHere(i, j) || b.isPieceHere(i, j))
				return true;
			i += incrx;
			j += incry;
		}
		return false;
	}

	@Override
	public boolean move(int xi, int yi, int xf, int yf) {
		if(isMoveOk(xi, yi, xf, yf)){
			if(w.isPieceHere(xf, yf)){
					w.capture(xf,yf);
			}
			else if(b.isPieceHere(xf, yf)){
					b.capture(xf,yf);
			}
			return current.move(xi, yi, xf, yf);
		}
		return false;
	}

	public List<PieceIHMs> getPiecesIHM(){
		List<PieceIHMs> list = new ArrayList<PieceIHMs>();
		list.addAll(w.getPiecesIHM());
		list.addAll(b.getPiecesIHM());
		return list;
		
	}
}
