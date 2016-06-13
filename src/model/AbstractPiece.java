package model;

public abstract class AbstractPiece implements Pieces{

	private Couleur couleur;
	protected Coord coord;
	
	public static void main(String args[]){
		Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
		System.out.println(maTour.getX());
		System.out.println(maTour.getY());
		System.out.println(maTour);
		maTour.move(3, 0);
		System.out.println(maTour);
		maTour.move(4,7);
		System.out.println(maTour);
		maTour.move(3,8);
		System.out.println(maTour);
		maTour.move(3,7);
		System.out.println(maTour);
	}
	
	public AbstractPiece(Couleur couleur, Coord coord){
		this.coord = coord;
	}
	@Override
	public abstract boolean capture();

	@Override
	public Couleur getCouleur(){
		return couleur;
	}

	public String getName(){
		return "";
	}

	@Override
	public int getX(){
		return coord.x;
	}

	@Override
	public int getY(){
		return coord.y;
	}
	
	@Override
	public abstract boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible);

	@Override
	public boolean move(int x, int y){
		if(this.isMoveOk(x,y,true,true)){
			this.coord.x = x;
			this.coord.y = y;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getName() + " : X=" + this.getX() + ";Y=" + this.getY();
	}

}
