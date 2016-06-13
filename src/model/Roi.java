package model;

public class Roi extends AbstractPiece {

	public Roi(Couleur couleur, Coord coord) {
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
		if(Math.abs(xFinal - this.coord.x) == 1 || Math.abs(xFinal - this.coord.x) == 0){
			if(Math.abs(yFinal - this.coord.y) == 1 || Math.abs(yFinal - this.coord.y) == 0){
				if(Coord.coordonnees_valides(xFinal, yFinal)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return "King";
	}

}
