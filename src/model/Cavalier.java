package model;

public class Cavalier extends AbstractPiece{

	public Cavalier(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}


	@Override
	public String getName() {
		return "Knight";
	}
	
	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible) {
		int x = this.coord.x;
		int y = this.coord.y;
		if((Math.abs(xFinal - x) == 2 && Math.abs(yFinal - y) == 1) || (Math.abs(xFinal - x) == 1 && Math.abs(yFinal - y) == 2)){
			if(Coord.coordonnees_valides(xFinal, yFinal)){
				return true;
			}
		}
		return false;
	}

}
