package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

public class KdTreeST<Value>
{

	private Node root;
	private static boolean level = true;
	private int size;

	private class Node
	{
		private Point2D p;
		private Value value;
		private RectHV rect;
		private Node leftBot;
		private Node rightTop;

		public Node(Point2D p, RectHV rect, Value val)
		{
			this.p = p;
			this.rect = rect;
			this.value = val;
		}
	}

	public KdTreeST()
	{

	}

	public boolean isEmpty()
	{
		if (size() == 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public int size()
	{
		return helpSize(root);
	}

	public void put(Point2D p, Value val)
	{
		root = helpPut(root, p, new RectHV(0, 0, 1, 1), 0, val);
		size++;
	}

	public Value get(Point2D p)
	{
		return helpGet(root, p, level);
	}

	public boolean contains(Point2D p)
	{
		if (helpContains(root, p) == true)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public Iterable<Point2D> points()
	{
		Queue<Point2D> q = new Queue<Point2D>();
		helpRange(new RectHV(0, 0, 1, 1), q, root);
		return q;
	}

	public Iterable<Point2D> range(RectHV rect)
	{
		Queue<Point2D> queue = new Queue<Point2D>();
		helpRange(rect, queue, root);
		return queue;
	}

	public Point2D nearest(Point2D p)
	{
		if (p == null)
		{
			return null;
		}
		Point2D returnable = nearestHelper(root, p, Double.MAX_VALUE, 0).p;
		if (returnable != null)
			return returnable;
		else
			return null;
	}

	private Node nearestHelper(Node x, Point2D p, double shortestDist, int level)
	{
		if (x == null)
		{
			return null;
		}
		Node minNode = x;
		Point2D curPoint = x.p;
		int next = level + 1;
		double shortestDistance = p.distanceSquaredTo(curPoint);

		if (shortestDistance < shortestDist)
		{
			shortestDist = p.distanceSquaredTo(curPoint);
			minNode = x;
		}

		Node leftBranch = null;
		Node rightBranch = null;
		if (level % 2 == 0)
		{
			if (p.x() < curPoint.x())
			{
				leftBranch = nearestHelper(x.leftBot, p, shortestDistance, next);
				if (leftBranch != null && leftBranch.p.distanceSquaredTo(p) < shortestDistance)
				{
					shortestDistance = p.distanceSquaredTo(leftBranch.p);
					minNode = leftBranch;
				}
				if (x.rightTop != null && x.rightTop.rect.distanceSquaredTo(p) < shortestDist)
				{
					rightBranch = nearestHelper(x.rightTop, p, shortestDist, next);
					if (rightBranch != null && rightBranch.p.distanceSquaredTo(p) < shortestDist)
					{
						shortestDist = p.distanceSquaredTo(rightBranch.p);
						minNode = rightBranch;
					}
				}
			} else
			{
				rightBranch = nearestHelper(x.rightTop, p, shortestDist, next);
				if (rightBranch != null && rightBranch.p.distanceSquaredTo(p) < shortestDist)
				{
					shortestDist = p.distanceSquaredTo(rightBranch.p);
					minNode = rightBranch;
				}
				if (x.leftBot != null && x.leftBot.rect.distanceSquaredTo(p) < shortestDist)
				{
					leftBranch = nearestHelper(x.leftBot, p, shortestDist, next);
					if (leftBranch != null && leftBranch.p.distanceSquaredTo(p) < shortestDist)
					{
						shortestDist = p.distanceSquaredTo(leftBranch.p);
						minNode = leftBranch;
					}
				}
			}
		} else
		{
			if (p.y() < curPoint.y())
			{
				leftBranch = nearestHelper(x.leftBot, p, shortestDist, next);
				if (leftBranch != null && leftBranch.p.distanceSquaredTo(p) < shortestDist)
				{
					shortestDist = p.distanceSquaredTo(leftBranch.p);
					minNode = leftBranch;
				}
				if (x.rightTop != null && x.rightTop.rect.distanceSquaredTo(p) < shortestDist)
				{
					rightBranch = nearestHelper(x.rightTop, p, shortestDist, next);
					if (rightBranch != null && rightBranch.p.distanceSquaredTo(p) < shortestDist)
					{
						shortestDist = p.distanceSquaredTo(rightBranch.p);
						minNode = rightBranch;
					}
				}
			} else
			{
				rightBranch = nearestHelper(x.rightTop, p, shortestDist, next);
				if (rightBranch != null && rightBranch.p.distanceSquaredTo(p) < shortestDist)
				{
					shortestDist = p.distanceSquaredTo(rightBranch.p);
					minNode = rightBranch;
				}
				if (x.leftBot != null && x.leftBot.rect.distanceSquaredTo(p) < shortestDist)
				{
					leftBranch = nearestHelper(x.leftBot, p, shortestDist, next);
					if (leftBranch != null && leftBranch.p.distanceSquaredTo(p) < shortestDist)
					{
						shortestDist = p.distanceSquaredTo(leftBranch.p);
						minNode = leftBranch;
					}
				}
			}
		}
		return minNode;

	}

	private int helpSize(Node node)
	{
		if (node == null)
		{
			return 0;
		}
		int size = 0;
		if (node.rightTop != null)
		{
			size = size + helpSize(node.rightTop);
		}
		if (node.leftBot != null)
		{
			size = size + helpSize(node.leftBot);
		}
		return ++size;
	}

	private Node helpPut(Node n, Point2D p, RectHV r, int level, Value val)
	{
		if (n == null)
		{
			return new Node(p, r, val);
		}
		int next = level + 1;
		if (level % 2 == 0)
		{
			if (p.x() < n.p.x())
			{
				n.leftBot = helpPut(n.leftBot, p, new RectHV(n.rect.xmin(), n.rect.ymin(), n.p.x(), n.rect.ymax()), next, val);
			} else
			{
				n.rightTop = helpPut(n.rightTop, p, new RectHV(n.p.x(), n.rect.ymin(), n.rect.xmax(), n.rect.ymax()), next, val);
			}
		} else
		{
			if (p.y() < n.p.y())
			{
				n.leftBot = helpPut(n.leftBot, p, new RectHV(n.rect.xmin(), n.rect.ymin(), n.rect.xmax(), n.p.y()), next, val);
			} else
			{
				n.rightTop = helpPut(n.rightTop, p, new RectHV(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.rect.ymax()), next, val);
			}
		}
		return n;
	}

	private Value helpGet(Node x, Point2D p, boolean dim)
	{
		if (x == null)
		{
			return null;
		}
		int cmp = helpComp(p, x.p, dim);
		if (cmp < 0)
		{
			return helpGet(x.leftBot, p, !dim);
		} else if (cmp > 0)
		{
			return helpGet(x.rightTop, p, !dim);
		} else
			return x.value;
	}

	private int helpComp(Point2D a, Point2D b, boolean dim)
	{
		if (dim == level)
		{
			if (a.x() < b.x())
			{
				return -1;
			} else if (a.x() > b.x())
			{
				return 1;
			} else
			{
				return 0;
			}
		} else
		{
			if (a.y() < b.y())
			{
				return -1;
			} else if (a.y() > b.y())
			{
				return 1;
			} else
			{
				return 0;
			}
		}
	}

	private boolean helpContains(Node n, Point2D p)
	{
		if (n == null)
		{
			return false;
		}
		if (n.rect.contains(p))
		{
			if (n.leftBot != null)
			{
				if (helpContains(n.leftBot, p))
				{
					return true;
				}
			}
			if (n.rightTop != null)
			{
				if (helpContains(n.rightTop, p))
				{
					return true;
				}
			}
		}
		if (n.p.equals(p))
		{
			return true;
		} else
		{
			return false;
		}
	}

	private void helpRange(RectHV rect, Queue<Point2D> q, Node n)
	{
		if (n == null)
		{
			return;
		}
		if (n.rect.intersects(rect) == false)
		{
			return;
		}
		if (rect.contains(n.p))
		{
			q.enqueue(n.p);
		}
		helpRange(rect, q, n.leftBot);
		helpRange(rect, q, n.rightTop);
	}

	public static void main(String[] args)
	{
		// unit testing of the methods (not graded)
		KdTreeST<Integer> tree = new KdTreeST<Integer>();
		Point2D p2 = new Point2D(.5, .5);
		Point2D p1 = new Point2D(.25, .25);
		Point2D p3 = new Point2D(.75, .75);
		Point2D p4 = new Point2D(.8, .2);
		Point2D p5 = new Point2D(.2, .5);
		tree.put(p1, 3);
		tree.put(p2, 1);
		tree.put(p3, 5);
		tree.put(p4, 2);
		Iterable<Point2D> list = tree.range(new RectHV(.26, .26, .76, .76));
		for (Point2D key : list)
		{
			System.out.println(key.toString());
		}
		StdOut.println("Here is the nearest point to" + p5.toString());
		StdOut.println(tree.nearest(p5));

		StdOut.println("done");
	}
}