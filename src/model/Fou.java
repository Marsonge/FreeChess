package model;

public class Fou extends AbstractPiece {

	public Fou(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}


	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible) {
		//A Bishop moves diagonaly, meaning x changes as much as y
		//We also check if there actually IS a move
		if(Math.abs(xFinal - this.coord.x) == Math.abs(yFinal - this.coord.y) && xFinal != this.coord.x){
			//Checking if we're still onto the board
			if(Coord.coordonnees_valides(xFinal, yFinal)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getName() {
		return "Bishop";
	}

}
