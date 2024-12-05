import java.awt.*;
import java.util.ArrayList;

/**
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, updated Fall 2016
 * @author Lydia Jin
 * @author Sudi Zhao
 */
public class Rectangle implements Shape {
	// TODO: YOUR CODE HERE
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;

	/**
	 * Constructs rectangle given start coordinates and color
	 */
	public Rectangle(int x1, int y1, Color color) {
		this.x1 = x1; this.x2 = x1;
		this.y1 = y1; this.y2 = y1;
		this.color = color;
	}

	/**
	 * Constructs rectangle given start coordinates (top left) and end coordinates (bottom right) and color
	 */
	public Rectangle(int x1, int y1, int x2, int y2, Color color) {
		setCorners(x1, y1, x2, y2);
		this.color = color;
	}

	/**
	 * sets corners
	 */
	public void setCorners(int x1, int y1, int x2, int y2) {
		// Ensure correct upper left and lower right
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}

	/**
	 * moves by the given distance in x and y
	 */
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx; y1 += dy;
		x2 += dx; y2 += dy;
	}

	/**
	 * gets the color of the rectangle
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * sets the color to given color
	 * @param Color color
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return true if the given coordinates is inside the rectangle, false otherwise
	 * @param int x
	 * @param int y
	 */
	@Override
	public boolean contains(int x, int y) {
		if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
			return true;
		}
		else {
			return false;
		}
	}

	//draws shape
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x1, y1, x2-x1, y2-y1);
	}

	//toString method for the rectangle
	public String toString() {
		return "rectangle "+x1+" "+y1+" "+x2+" "+y2+" "+color.getRGB();
	}

	/**
	 * Gets the x coodinate of first point that the segment is connected by
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Gets the x coodinate of second point that the segment is connected by
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Gets the y coodinate of first point that the segment is connected by
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Gets the y coodinate of second point that the segment is connected by
	 */
	public int getY2() {
		return y2;
	}


}
