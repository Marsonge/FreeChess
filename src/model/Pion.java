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
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean move(int x, int y){
		return super.move(x,y);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible) {
		if(this.getCouleur().equals(Couleur.BLANC)){
			if(yFinal - this.coord.y == 1){
				if(firstMove)
					firstMove = false;
				return true;
			}
			if(yFinal - this.coord.y == 2 && firstMove){
				firstMove = false;
				enPassant = true;
				return true;
			}
		}
		else{
			if(this.coord.y - yFinal == 1){
				return true;
			}
			if(this.coord.y - yFinal == 2 && firstMove){
				firstMove = false;
				enPassant = true;
			}
		}
		return false;
	}
	
	@Override
	public String getName() {
		return "Pawn";
	}

}
