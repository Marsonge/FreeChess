package model;

public class Pion extends AbstractPiece {

	private boolean firstMove;
	private boolean enPassant;
	
	public Pion(Couleur couleur, Coord coord) {
		super(couleur, coord);
		firstMove = true;
		enPassant = false;
	}

	
	@Override
	public boolean move(int x, int y){
		super.move(x,y);
		if(firstMove)
			firstMove = false;
		return true;
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible) {
		if(this.getCouleur().equals(Couleur.NOIR)){
			if(yFinal - this.coord.y == 1 && Math.abs(xFinal - this.coord.x) == 1 && isCatchOk){
				return true;
			}
			if(yFinal - this.coord.y == 1 && Math.abs(xFinal - this.coord.x) == 0){
				return true;
			}
			if(yFinal - this.coord.y == 2 && firstMove && Math.abs(xFinal - this.coord.x) == 0){
				return true;
			}
		}
		else{
			if(this.coord.y - yFinal == 1 && Math.abs(xFinal - this.coord.x) == 1 && isCatchOk){
				return true;
			}
			if(this.coord.y - yFinal == 1 && Math.abs(xFinal - this.coord.x) == 0){
				return true;
			}
			if(this.coord.y - yFinal == 2 && firstMove && Math.abs(xFinal - this.coord.x) == 0){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getName() {
		return "Pion";
	}


	@Override
	public boolean hasMoved() {
		return firstMove;
	}

}
