package a05;

public class Point2D implements Comparable<Point2D>
{
	private final double x; // x coordinate
	private final double y; // y coordinate

	public Point2D(double x, double y)
	{
		if (Double.isInfinite(x) || Double.isInfinite(y))
			throw new IllegalArgumentException("Coordinates must be finite");
		if (Double.isNaN(x) || Double.isNaN(y))
			throw new IllegalArgumentException("Coordinates cannot be NaN");
		if (x == 0.0)
			this.x = 0.0; // convert -0.0 to +0.0
		else
			this.x = x;

		if (y == 0.0)
			this.y = 0.0; // convert -0.0 to +0.0
		else
			this.y = y;
	}

	public double x()
	{
		return x; // x-coordinate
	}

	public double y()
	{
		return y; // y-coordinate
	}

	public double distanceSquaredTo(Point2D that)
	{
		double dx = this.x - that.x;
        double dy = this.y - that.y;
        return dx*dx + dy*dy;
	}

	public int compareTo(Point2D that)
	{
		if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
	}

	public boolean equals(Object that)
	{
		if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass()) return false;
        Point2D here = (Point2D) that;
        return this.x == here.x && this.y == here.y;
	}

	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}