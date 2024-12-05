import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 * @author Lydia Jin, Sudi Zhao, updated Fall 2022
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE

	private Color color; //color of polyline
	private ArrayList<Point> points; //list of points that make up the polyline

	//constructor
	public Polyline (int x, int y, Color color) {
		points = new ArrayList<Point>();
		points.add(new Point(x, y)); //adds new point to the list of points that make up the polyline
		this.color = color;
	}

	//constructor
	public Polyline (ArrayList<Point> p, Color color) {
		points = p;
		this.color = color;
	}

	/**
	 * Returns the list of points that the polyline is made of
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * Grows the polyline by adding more points
	 */
	public void grow(int x, int y) {
		points.add(new Point(x, y));
	}

	/**
	 * Moves the polyline
	 */
	@Override
	public void moveBy(int dx, int dy) {
		for (Point p : points) {
			p.setLocation(p.getX() + dx, p.getY() + dy);
		}
	}

	/**
	 * Gets the color
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Checks if a point is a reasonable distance from the polyline (by checking each point)
	 */
	@Override
	public boolean contains(int x, int y) {
		for (Point elem : points) {
			if (Math.sqrt((elem.getY() - y) * (elem.getY() - y) + (elem.getX() - x) * (elem.getX() - x)) <= 5) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Draws the polyline
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < points.size()-1; i++) {
			g.drawLine((int)points.get(i).getX(), (int)points.get(i).getY(), (int)points.get(i+1).getX(), (int)points.get(i+1).getY());
		}
	}

	/**
	 * Polyline toString method
	 */
	@Override
	public String toString() {
		return "polyline " + points;
	}




}
