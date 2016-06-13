package model;

public abstract class AbstractPiece implements Pieces{

	private Couleur couleur;
	protected Coord coord;
	
	public static void main(String args[]){
		System.out.println("####TEST DES PIECES####");
		System.out.println("##ROOK##");
		Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
		System.out.println(maTour.getX());
		System.out.println(maTour.getY());
		System.out.println(maTour);
		maTour.move(3, 0);
		System.out.println(maTour);
		//Incorrect move
		maTour.move(4,7);
		System.out.println(maTour);
		//Out of move test
		maTour.move(3,8);
		System.out.println(maTour);
		maTour.move(3,7);
		System.out.println(maTour);
		System.out.println("##KNIGHT##");
		Pieces monCav = new Cavalier(Couleur.NOIR, new Coord(3, 3));
		System.out.println(monCav.getX());
		System.out.println(monCav.getY());
		System.out.println(monCav);
		monCav.move(4,1);
		System.out.println(monCav);
		monCav.move(3,3);
		System.out.println(monCav);
		//Incorrect move test
		monCav.move(4,2);
		System.out.println(monCav);
		monCav.move(2,5);
		monCav.move(1,7);
		System.out.println(monCav);
		//Out of bonds test
		monCav.move(2,9);
		System.out.println(monCav);
		System.out.println("##BISHOP##");
		Pieces monFou = new Fou(Couleur.NOIR, new Coord(0,0));
		System.out.println(monFou);
		monFou.move(2,2);
		System.out.println(monFou);
		//Incorrect move
		monFou.move(3,2);
		System.out.println(monFou);
		//Out of bounds
		monFou.move(8, 8);
		System.out.println(monFou);
		System.out.println("###PAWN###");
		Pieces bP = new Pion(Couleur.NOIR, new Coord(7,7));
		System.out.println(bP);
		bP.move(6,7);
		System.out.println(bP);
		bP.move(7,6);
		System.out.println(bP);
		bP.move(7,7);
		System.out.println(bP);
		Pieces wP = new Pion(Couleur.BLANC, new Coord(0,0));
		System.out.println(wP);
		wP.move(1,0);
		System.out.println(wP);
		wP.move(0,1);
		System.out.println(wP);
		wP.move(0,0);
		System.out.println(wP);

	}
	
	public AbstractPiece(Couleur couleur, Coord coord){
		this.coord = coord;
		this.couleur = couleur;
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
