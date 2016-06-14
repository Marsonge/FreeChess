package model;

public class Roi extends AbstractPiece {

	private boolean hasMoved;
	
	public boolean hasMoved() {
		return hasMoved;
	}

	public Roi(Couleur couleur, Coord coord) {
		super(couleur, coord);
		hasMoved = false;
	}

	@Override
	public boolean move(int x,int y){
		super.move(x,y);
		if(!hasMoved)
			hasMoved = true;
		return true;
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
		if(isCastlingPossible && Math.abs(xFinal - this.coord.x) == 2 && yFinal - this.coord.y == 0){
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "Roi";
	}

}
