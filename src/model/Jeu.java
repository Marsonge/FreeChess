package model;

import java.util.LinkedList;
import java.util.List;

import tools.ChessPiecesFactory;

public class Jeu implements Game {

	private List<Pieces> pieces;
	private Couleur couleur;
	private boolean castling;

	public Jeu(Couleur couleur) {
		this.castling = true;
		this.couleur = couleur;
		this.pieces = ChessPiecesFactory.newPieces(couleur);
	}

	public Couleur getCouleur() {
		return this.couleur;
	}

	public Couleur getPieceColor(int x, int y) {
		return this.findPiece(x, y).getCouleur();
	}

	public String getPieceName(int x, int y) {
		return this.findPiece(x, y).getName();
	}

	public void setCastling() {
		this.castling = false;
	}

	@Override
	public boolean isPieceHere(int x, int y) {
		Pieces piece = this.findPiece(x, y);
		return piece != null ? true : false;
	}

	@Override
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal,
			boolean isCatchOk, boolean isCastlingPossible) {
		Pieces piece = this.findPiece(xInit, yInit);
		try {
			return piece
					.isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible);
		} catch (NullPointerException e) {
			return false;
		}

	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		Pieces piece = this.findPiece(xInit, yInit);
		try {
			return piece.move(xFinal, yFinal);
		} catch (NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean capture(int xCatch, int yCatch) {
		Pieces piece = this.findPiece(xCatch, yCatch);
		try {
			return piece.capture();
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	public void setPossibleCapture(){
		// TODO Auto-generated method stub
	}

	public String toString() {
		return "Jeu "+ this.couleur +", pieces=" + pieces;
	}

	private Pieces findPiece(int x, int y) {
		for (Pieces piece : this.pieces) {
			if (piece.getX() == x && piece.getY() == y) {
				return piece;
			}
		}
		return null;

	}

	/**
	 *    * @return une version réduite de la liste des pièces en cours    * ne
	 * donnant que des accès en lecture sur des PieceIHMs (type piece + couleur
	 * + coordonnées)    
	 */
	public List<PieceIHMs> getPiecesIHM() {
		PieceIHMs newPieceIHM = null;
		List<PieceIHMs> list = new LinkedList<PieceIHMs>();
		for (Pieces piece : this.pieces) {
			// si la pièce est toujours en jeu
			if (piece.getX() != -1) {
				newPieceIHM = new PieceIHM(piece);
				list.add(newPieceIHM);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Jeu blancs = new Jeu(Couleur.BLANC);
		Jeu noirs = new Jeu(Couleur.NOIR);
		System.out.println(blancs);
		System.out.println(noirs);
		
		System.out.println(blancs.move(0, 7, 6, 7));
		System.out.println(blancs);
	}

}
