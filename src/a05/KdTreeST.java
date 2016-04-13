package a05;

import edu.princeton.cs.algs4.RedBlackBST;

public class KdTreeST<Value>
{
	private RedBlackBST bst = new RedBlackBST();
	public KdTreeST()
	{
		// construct an empty symbol table of points
	}

	public boolean isEmpty()
	{
		return false;
		// is the symbol table empty?
	}

	public int size()
	{
		return 0;
		// number of points
	}

	public void put(Point2D p, Value val)
	{
		// associate the value val with point p
	}

	public Value get(Point2D p)
	{
		return null;
		// value associated with point p
	}

	public boolean contains(Point2D p)
	{
		return false;
		// does the symbol table contain point p?
	}

	public Iterable<Point2D> points()
	{
		return null;
		// all points in the symbol table
	}

	public Iterable<Point2D> range(RectHV rect)
	{
		return null;
		// all points that are inside the rectangle
	}

	public Point2D nearest(Point2D p)
	{
		return p;
		// a nearest neighbor to point p; null if the symbol table is empty
	}

	public static void main(String[] args)
	{
		// unit testing of the methods (not graded)
	}
}