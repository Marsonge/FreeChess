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
		this.setMessage(current.getCouleur()+" to play");
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
		e.move(3,7,2,7);
		System.out.println(e);
	}

	public boolean isMoveOk(int xi, int yi, int xf, int yf) {
		boolean isCatchOk = false;
		if(w.isPieceHere(xf, yf)){
			if(current==w){
				this.setMessage("There's an allied piece on the way. " + current.getCouleur() + " to play.");
				return false;
			}
			isCatchOk = true; //There's something to catch at the end
		}
		else if(b.isPieceHere(xf, yf)){
			if(current==b){
				this.setMessage("There's an allied piece on the way. " + current.getCouleur() + " to play.");
				return false;
			}
			isCatchOk = true; //There's something to catch at the end
		}
		if(current.isMoveOk(xi, yi, xf, yf, isCatchOk, current.isCastlingPossible(xi,xf,yi,yf))){
				if(isPieceBlocked(xi,yi,xf,yf)){
					this.setMessage("The way isn't clear to move. " + current.getCouleur() + " to play.");
					return false;
				}
			return true;
		}
		this.setMessage("This move is illegal. " + current.getCouleur() + " to play.");
		return false;
	}

	private boolean isPieceBlocked(int xi, int xf, int yi, int yf) {
		if(Math.abs(xi-yi) == Math.abs(xf-yf)){ //If the piece is moving diagonally
			return isDiagonalBlocked(xi,xf,yi,yf);
		}
		else if(xi==xf ^ yi==yf){ //If the piece is moving laterally
			return isLateralBlocked(xi,xf,yi,yf);
		}
		return false; //If it's not moving laterally or diagonally, it's a knight : can't be blocked
	}

	private boolean isLateralBlocked(int xi, int xf, int yi, int yf) {
		if(xi-xf == 0){
			int incr = (yf - yi) / Math.abs(yf-yi); //Figures out which way to go : up or down
			int i = yi + incr;
			while(i != yf){
				if(w.isPieceHere(xi, i) || b.isPieceHere(xi, i))
					return true;
				i += incr;
			}
		}
		else{
			int incr = (xf - xi) / Math.abs(xf-xi); //Figures out which way to go : left or right
			int i = xi + incr;
			while(i != xf){
				if(w.isPieceHere(i, yi) || b.isPieceHere(i, yi))
					return true;
				i += incr;
			}
		}
		return false;
		
	}

	private boolean isDiagonalBlocked(int xi, int yi, int xf, int yf) {
		int incrx = (xf - xi)/Math.abs(xf-xi); //Figures out which way to go : left or right
		int incry = (yf - yi)/Math.abs(yf-yi); //Figures out which way to go : up or down
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
