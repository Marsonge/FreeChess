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
		// TODO Auto-generated method stub
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
		System.out.println(e);
		System.out.println(e.move(0, 7, 6, 7));
		System.out.println(e.move(4, 3, 1, 2));
		System.out.println(e.move(7, 7, 4, 3));
		e.switchPlayer();
		System.out.println(e);
		e.move(1,0,2,2);
		System.out.println(e);
		e.move(0, 0, 7, 0);
		System.out.println(e);
	}

	public boolean isMoveOk(int xi, int yi, int xf, int yf) {
		if(current.isMoveOk(xi, yi, xf, yf, true, true)){
			if(w.isPieceHere(xf, yf)){
				if(current==w)
					return false;
				w.capture(xf,yf);
				return true;
			}
			else if(b.isPieceHere(xf, yf)){
				if(current==b)
					return false;
				b.capture(xf,yf);
				return true;
			}
			return true;
			
		}
		return false;
	}

	@Override
	public boolean move(int xi, int yi, int xf, int yf) {
		if(isMoveOk(xi, yi, xf, yf)){
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
