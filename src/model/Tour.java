package model;

public class Tour extends AbstractPiece {

	public Tour(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}

	@Override
	public String getName() {
		return "Rook";
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible) {
		//For a rook, we need either X to change, or Y to change.
		if(xFinal==this.coord.x ^ yFinal==this.coord.y){
			//Checking if we're still onto the board
			if(Coord.coordonnees_valides(xFinal, yFinal)){
				return true;
			}
		}
		return false;
	}

	
}
