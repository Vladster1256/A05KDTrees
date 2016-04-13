package a05;

public class Point2D implements Comparable<Point2D>
{
	public Point2D(double x, double y)
	{ // construct the point (x, y)
	}

	public double x()
	{
		return 0; // x-coordinate
	}

	public double y()
	{
		return 0; // y-coordinate
	}

	public double distanceSquaredTo(Point2D that)
	{
		return 0; // square of Euclidean distance between two points
	}

	public int compareTo(Point2D that)
	{
		return 0; // for use in an ordered symbol table
	}

	public boolean equals(Object that)
	{
		return false; // does this point equal that object?
	}

	public String toString()
	{
		return null; // string representation
	}
}