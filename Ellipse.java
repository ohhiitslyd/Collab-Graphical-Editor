import java.awt.*;

/**
 * An ellipse-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, Fall 2016, constructors and corner setter
 * @author Lydia Jin, Sudi Zhao, updated Fall 2022, added getters
 */
public class Ellipse implements Shape {
	private int x1, y1, x2, y2;		// upper left and lower right
	private Color color;

	/**
	 * An "empty" ellipse, with only one point set so far
	 */
	public Ellipse(int x1, int y1, Color color) {
		this.x1 = x1; this.x2 = x1;
		this.y1 = y1; this.y2 = y1;
		this.color = color;
	}

	/**
	 * An ellipse defined by two corners
	 */
	public Ellipse(int x1, int y1, int x2, int y2, Color color) {
		setCorners(x1, y1, x2, y2);
		this.color = color;
	}

	/**
	 * Redefines the ellipse based on new corners
	 */

	public void setCorners(int x1, int y1, int x2, int y2) {
		// Ensure correct upper left and lower right
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}

	/**
	 * Checks if a point is in the ellipse
	 */
	@Override
	public boolean contains(int x, int y) {
		double a = (x2-x1)/2.0, b = (y2-y1)/2.0;
		double dx = x - (x1 + a); // horizontal distance from center
		double dy = y - (y1 + b); // vertical distance from center

		// Apply the standard geometry formula. (See CRC, 29th edition, p. 178.)
		return Math.pow(dx / a, 2) + Math.pow(dy / b, 2) <= 1;
	}

	/**
	 * Moves the ellipse
	 */
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx; y1 += dy;
		x2 += dx; y2 += dy;
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
	 * Draws the ellipse
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x1, y1, x2-x1, y2-y1);
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

	/**
	 * To string method of the ellipse
	 */
	@Override
	public String toString() {
		return "ellipse "+x1+" "+y1+" "+x2+" "+y2+" "+color.getRGB();
	}


}
