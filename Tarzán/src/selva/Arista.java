package selva;

public class Arista {
	private int nDest;
	private int nOrig;
	private double cost;

	public Arista( int nOrig,int nDest, double cost) {
		this.nDest = nDest;
		this.nOrig = nOrig;
		this.cost = cost;
	}
	public Arista(){}
	public void setnDest(int nDest) {
		this.nDest = nDest;
	}
	public int getnOrig() {
		return nOrig;
	}
	public void setnOrig(int nOrig) {
		this.nOrig = nOrig;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getnDest() {
		return nDest;
	}
	

}
