package model;

public abstract class AbstractPiece implements Pieces{

	private Couleur couleur;
	protected Coord coord;
	
	public static void main(String args[]){
		System.out.println("####TEST DES PIECES####");
		System.out.println("##ROOK##");
		Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
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
		//Out of bounds test
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
		//Can't go horizontally
		bP.move(6,7);
		System.out.println(bP);
		//Can go down!
		bP.move(7,6);
		System.out.println(bP);
		//Can't go up
		bP.move(7,7);
		System.out.println(bP);
		//First move not available, now :-/
		bP.move(7,4);
		System.out.println(bP);
		Pieces wP = new Pion(Couleur.BLANC, new Coord(0,0));
		System.out.println(wP);
		//Not horizontally
		wP.move(1,0);
		System.out.println(wP);
		//Moving two blocks
		wP.move(0,2);
		System.out.println(wP);
		//Not back though!
		wP.move(0,0);
		System.out.println(wP);
		//First move: done
		wP.move(0,4);
		System.out.println(wP);
		//Can still move normal tho
		wP.move(0,3);
		System.out.println(wP);
		System.out.println("##KING##");
		Pieces monRoi = new Roi(Couleur.BLANC, new Coord(0,0));
		System.out.println(monRoi);
		monRoi.move(1,1);
		System.out.println(monRoi);
		monRoi.move(2,0);
		System.out.println(monRoi);
		//Out of bounds
		monRoi.move(2,-1);
		System.out.println(monRoi);
		monRoi.move(2,1);
		System.out.println(monRoi);
		//Impossible move
		monRoi.move(4,1);
		System.out.println(monRoi);
		System.out.println("##QUEEN##");
		Pieces maReine = new Reine(Couleur.BLANC, new Coord(0,0));
		System.out.println(maReine);
		//Diagonal
		maReine.move(3,3);
		System.out.println(maReine);
		//Lateral
		maReine.move(3,7);
		System.out.println(maReine);
		//Invalid
		maReine.move(4,5);
		System.out.println(maReine);
		//Out of bounds
		maReine.move(3,8);
		System.out.println(maReine);


	}
	
	public AbstractPiece(Couleur couleur, Coord coord){
		this.coord = coord;
		this.couleur = couleur;
	}
	@Override
	public boolean capture(){
		this.coord = new Coord(-1,-1);
		return true;
	}

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
	
	public abstract boolean hasMoved();
	
	@Override
	public abstract boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk,
			boolean isCastlingPossible);

	@Override
	public boolean move(int x, int y){
		this.coord.x = x;
		this.coord.y = y;
		return true;
	}

	@Override
	public String toString() {
		return this.getName() + " : X=" + this.getX() + ";Y=" + this.getY();
	}

}
