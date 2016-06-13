package model;

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
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return false;
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
	public void switchJoueur(){
		if(current == w){
			current = b;
		}
		else{
			current = w;
		}
	}

	private void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return w.toString() + "\n" + b.toString() + "\nLe joueur courant est " + current.getCouleur();
	}
	
	public static void main(String args[]){
		Echiquier e = new Echiquier();
		System.out.println(e);
		e.switchJoueur();
		System.out.println(e);
	}
	
	

}
