package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeCompare());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;

			} else {
				explored.add(current);
				List<Edge> children = current.getChildren();
				for (Edge e : children) {
					Node child = e.getEnd();
					if (!frontier.contains(child) && !explored.contains(child)) {
						child.setG(current.getG() + e.getWeight());
						child.setParent(current);
						frontier.add(child);

					} else if (frontier.contains(child) && child.getG() > current.getG() + e.getWeight()) {
						frontier.remove(child);
						child.setParent(current);
						child.setG(current.getG() + e.getWeight());
						frontier.add(child);
					}
				}
			}
		}
		return null;
	}

	public boolean isAdmissibleH(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			Node result = execute(current, goal, frontier, explored);
			System.out.println(current.getLabel());
			System.out.println("H " + current.getH());
			System.out.println("G " + result.getG());
			if (result.getG() < current.getH()) {
				return false;
			} else if (result == null)
				return false;
		}
		return true;

	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeCompare());

		List<Node> explored = new ArrayList<Node>();
		root.setParent(null);
		root.setG(0);
		frontier.add(root);
		boolean isStarted = false;
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				isStarted = true;
				current.setParent(null);
				current.setG(0);
				explored.clear();
				frontier.clear();
			}
			if (current.getLabel().equals(goal) && isStarted) {
				return current;
			}
			explored.add(current);
			List<Edge> ed = current.getChildren();
			for (Edge e : ed) {
				Node child = e.getEnd();
				double childCost = current.getG() + e.getWeight();
				if (!frontier.contains(child) || !explored.contains(child)) {
					child.setParent(current);
					child.setG(childCost);
					frontier.add(child);
				} else if (frontier.contains(child) && child.getG() > childCost) {
					child.setParent(current);
					child.setG(childCost);
				}

			}

		}
		return null;

	}

	public Node execute(Node root, String goal, Queue<Node> Pfrontier, List<Node> Pexplored) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeCompare());

		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		root.setParent(null);
		root.setG(0);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge e : children) {
				Node child = e.getEnd();
				double childPathCost = current.getG() + e.getWeight();
				if (!frontier.contains(child) || !explored.contains(child)) {
					child.setParent(current);
					child.setG(childPathCost);
					frontier.add(child);
				} else if (frontier.contains(child) && (child.getG() + child.getH()) > childPathCost + child.getH()) {
					child.setParent(current);
					child.setG(childPathCost);
				}
				if (!Pfrontier.contains(children) || !Pexplored.contains(child)) {
					frontier.add(child);
				}
			}
		}

		return null;
	}
}