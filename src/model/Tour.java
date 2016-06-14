package model;

public class Tour extends AbstractPiece {

	private boolean hasMoved;
	
	public Tour(Couleur couleur, Coord coord) {
		super(couleur, coord);
		hasMoved = false;
	}

	@Override
	public String getName() {
		return "Tour";
	}
	
	public boolean hasMoved() {
		return hasMoved;
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
