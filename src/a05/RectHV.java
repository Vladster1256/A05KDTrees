package a05;

public class RectHV
{
	private final double xmin, ymin; // minimum x- and y-coordinates
	private final double xmax, ymax; // maximum x- and y-coordinates

	// construct the rectangle [xmin,
	// xmax] x [ymin, ymax]
	public RectHV(double xmin, double ymin, double xmax, double ymax)
	{
		if (Double.isNaN(xmin) || Double.isNaN(xmax))
			throw new IllegalArgumentException("x-coordinate cannot be NaN");
		if (Double.isNaN(ymin) || Double.isNaN(ymax))
			throw new IllegalArgumentException("y-coordinates cannot be NaN");
		if (xmax < xmin || ymax < ymin)
		{
			throw new IllegalArgumentException("Invalid rectangle");
		}
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}

	public double xmin()
	{
		return xmin;
		// minimum x-coordinate of rectangle
	}

	public double ymin()
	{
		return ymin;
		// minimum y-coordinate of rectangle
	}

	public double xmax()
	{
		return xmax;
		// maximum x-coordinate of rectangle
	}

	public double ymax()
	{
		return ymax;
		// maximum y-coordinate of rectangle
	}

	public boolean contains(Point2D p)
	{
		return (p.x() >= xmin) && (p.x() <= xmax) && (p.y() >= ymin) && (p.y() <= ymax);
		// does this rectangle contain the point p (either inside or on
		// boundary)?
	}

	public boolean intersects(RectHV that)
	{
		return this.xmax >= that.xmin && this.ymax >= that.ymin && that.xmax >= this.xmin && that.ymax >= this.ymin;
		// does this rectangle intersect that rectangle (at one or more points)?
	}

	public double distanceSquaredTo(Point2D p)
	{
		double dx = 0.0, dy = 0.0;
        if      (p.x() < xmin) dx = p.x() - xmin;
        else if (p.x() > xmax) dx = p.x() - xmax;
        if      (p.y() < ymin) dy = p.y() - ymin;
        else if (p.y() > ymax) dy = p.y() - ymax;
        return dx*dx + dy*dy;
		// square of Euclidean distance from point p to closest point in
		// rectangle
	}

	public boolean equals(Object that)
	{
		if (that == this)
			return true;
		if (that == null)
			return false;
		if (that.getClass() != this.getClass())
			return false;
		RectHV here = (RectHV) that;
		if (this.xmin != here.xmin)
			return false;
		if (this.ymin != here.ymin)
			return false;
		if (this.xmax != here.xmax)
			return false;
		if (this.ymax != here.ymax)
			return false;
		return true;
		// does this rectangle equal that object?
	}

	public String toString()
	{
		return "[" + xmin + ", " + xmax + "] x [" + ymin + ", " + ymax + "]";
		// string representation
	}
}