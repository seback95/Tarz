package selva;

public class Punto {
private int x,y;
private double distancia;
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public Punto(int x,int y)
{
	this.x=x;
	this.y=y;
}
public Punto(Punto obj,double dist)
{
	x=obj.x;
	y=obj.y;
	distancia=dist;
}
public Punto(Punto obj)
{
	x=obj.x;
	y=obj.y;
	distancia=obj.distancia;
}
public double getDistancia() {
	return distancia;
}
public void setDistancia(double distancia) {
	this.distancia = distancia;
}

}
