package model;

public abstract class AbstractPiece implements Pieces{

	private Couleur couleur;
	private int x;
	private int y;
	
	private AbstractPiece(Couleur couleur, Coord coord){
		int x = coord.x;
		int y = coord.y;
	}
	@Override
	public abstract boolean capture();

	@Override
	public Couleur getCouleur(){
		return couleur;
	}

	@Override
	public abstract String getName();

	@Override
	public int getX(){
		return x;
	}

	@Override
	public int getY(){
		return y;
	}
	
	@Override
	public abstract boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible);

	@Override
	public abstract boolean move(int xFinal, int yFinal);

	@Override
	public String toString() {
		return this.getName() + " : X=" + this.getX() + ";Y=" + this.getY();
	}

}
