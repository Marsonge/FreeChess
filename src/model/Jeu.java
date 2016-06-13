package model;

import java.util.LinkedList;
import java.util.List;

import tools.ChessPiecesFactory;

public class Jeu implements Game {

	private List<Pieces> pieces;
	private Couleur couleur;
	private boolean castling;

	public Jeu(Couleur couleur) {
		this.couleur = couleur;
		this.pieces = ChessPiecesFactory.newPieces(couleur);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Jeu [pieces=" + pieces + "]";
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
		Jeu jeu = new Jeu(Couleur.BLANC);
		System.out.println(jeu);
	}

}