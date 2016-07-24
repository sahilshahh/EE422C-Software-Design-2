package assignment5;

import java.awt.Polygon;
import java.awt.Rectangle; 
import java.awt.geom.Ellipse2D; 


public class Car {
	
    private int x;
    private int y;
	private Rectangle body;
	private Rectangle roof;
	private Ellipse2D.Double frontTire;
	private Ellipse2D.Double rearTire;
	private Polygon front;
	private Polygon back;
	
	public Car(int xcoord, int ycoord){
		setX(xcoord);
		setY(ycoord);
		setBody(new Rectangle(xcoord, ycoord, 60, 10));
		setRoof(new Rectangle(xcoord + 20, ycoord - 10, 20, 10));
		setFrontTire(new Ellipse2D.Double(xcoord + 10, ycoord + 10, 10, 10));
		setRearTire(new Ellipse2D.Double(xcoord + 40, ycoord + 10, 10, 10));
		setFront(new Polygon(new int[] {xcoord + 10, xcoord + 20, xcoord + 20}, new int[] {ycoord, ycoord, ycoord-10}, 3));
		setBack(new Polygon(new int[] {xcoord + 40, xcoord + 40, xcoord + 50}, new int[] {ycoord - 10, ycoord, ycoord}, 3));
	}

	public void shiftCar(int amount){
		setBody(new Rectangle(x + amount, y, 60, 10));
		setRoof(new Rectangle(x + amount + 20, y - 10, 20, 10));
		setFrontTire(new Ellipse2D.Double(x + amount + 10, y + 10, 10, 10));
		setRearTire(new Ellipse2D.Double(x + amount + 40, y + 10, 10, 10));
		setFront(new Polygon(new int[] {x + 10 + amount, x + 20 + amount, x + 20 + amount}, new int[] {y, y, y-10}, 3));
		setBack(new Polygon(new int[] {x + 40 + amount, x + 40 + amount, x + 50 + amount}, new int[] {y - 10, y, y}, 3));
		setX(x + amount);
	}
	
	public Rectangle getBody() {
		return body;
	}

	public void setBody(Rectangle body) {
		this.body = body;
	}

	public Rectangle getRoof() {
		return roof;
	}

	public void setRoof(Rectangle roof) {
		this.roof = roof;
	}

	public Ellipse2D.Double getFrontTire() {
		return frontTire;
	}

	public void setFrontTire(Ellipse2D.Double frontTire) {
		this.frontTire = frontTire;
	}

	public Ellipse2D.Double getRearTire() {
		return rearTire;
	}

	public void setRearTire(Ellipse2D.Double rearTire) {
		this.rearTire = rearTire;
	}

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

	public Polygon getFront() {
		return front;
	}

	public void setFront(Polygon front) {
		this.front = front;
	}

	public Polygon getBack() {
		return back;
	}

	public void setBack(Polygon back) {
		this.back = back;
	}

}
