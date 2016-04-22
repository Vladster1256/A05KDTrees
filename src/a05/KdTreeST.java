package a05;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

public class KdTreeST<Value>
{
	private RedBlackBST<Point2D, Value> bst = new RedBlackBST<Point2D, Value>();

	public KdTreeST()
	{
		// construct an empty symbol table of points
	}

	// is the symbol table empty?
	public boolean isEmpty()
	{
		if (bst.size() == 0)
			return true;
		else
			return false;
	}

	public int size()
	{
		return bst.size();
	}

	public void put(Point2D p, Value val)
	{
		bst.put(p, val);
	}

	// value associated with point p
	public Value get(Point2D p)
	{
		return bst.get(p);
	}

	// does the symbol table contain point p? 
	public boolean contains(Point2D p)
	{
		if (bst.contains(p))
			return true;
		else
			return false;
	}

	// all points in the symbol table
	public Iterable<Point2D> points()
	{
		return bst.keys();
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect)
	{
		Stack<Point2D> stack = new Stack<Point2D>();
		for(Point2D point: bst.keys())
		{
			if(rect.contains(point))
			{
				stack.push(point);
			}
		}
		
		return stack;
		
	}

	// a nearest neighbor to point p; null if the symbol table is empty
	public Point2D nearest(Point2D p)
	{
		Point2D returnable = new Point2D(0,0);
		double distance = Double.MAX_VALUE;
		if(isEmpty())
		{
			return null;
		}
		else
		{
			for(Point2D point: bst.keys())
			{
				if(p.distanceSquaredTo(point)<distance)
				{
					distance = p.distanceSquaredTo(point);
					returnable = point;
				}
			}
		}
		return returnable;	
	}
	
	public static void main(String[] args)
	{
		// unit testing of the methods (not graded)
	}
}