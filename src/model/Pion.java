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
		if(firstMove)
			firstMove = false;
		return super.move(x,y);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible) {
		System.out.println("hello");
		if(this.getCouleur().equals(Couleur.NOIR)){
			if(yFinal - this.coord.y == 1){
				return true;
			}
			if(yFinal - this.coord.y == 2 && firstMove){
				return true;
			}
		}
		else{
			if(this.coord.y - yFinal == 1){
				return true;
			}
			if(this.coord.y - yFinal == 2 && firstMove){
				return true;
			}
		}
		System.out.println(":(");
		return false;
	}
	
	@Override
	public String getName() {
		return "Pawn";
	}

}
