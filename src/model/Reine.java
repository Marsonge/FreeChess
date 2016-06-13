package model;

public class Reine extends AbstractPiece {

	public Reine(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible) {
		// Diagonal move
		if(Math.abs(xFinal - this.coord.x) == Math.abs(yFinal - this.coord.y) && xFinal != this.coord.x){
			//Checking if we're still onto the board
			if(Coord.coordonnees_valides(xFinal, yFinal)){
				return true;
			}
		}
		// Lateral move
		else if(xFinal==this.coord.x ^ yFinal==this.coord.y){
			//Checking if we're still onto the board
			if(Coord.coordonnees_valides(xFinal, yFinal)){
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public String getName() {
		return "Queen";
	}


}
