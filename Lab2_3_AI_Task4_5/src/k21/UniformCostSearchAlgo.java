package k21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			else {
				List<Edge> children = current.getChildren();
				for (Edge child : children) {
					Node n = child.getEnd();
					if (!frontier.isEmpty() && !explored.isEmpty()) {
						frontier.add(current);
						n.setParent(current);
						n.setPathCost(current.getPathCost() + child.getWeight());
					} else if (frontier.contains(n) && n.getPathCost() > current.getPathCost()) {
						n.setPathCost(current.getPathCost());
						n.setParent(current);
					}
				}
			}

		}
		return null;

	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		boolean seen = false;
		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			explored.add(current);
			if (current.getLabel().equals(start)) {
				seen = true;
				frontier.clear();
			}

			if (current.getLabel().equals(goal) && seen) {
				return current;
			} else if (current.getLabel().equals(goal) && seen == false) {
				continue;
			} else {
				List<Edge> edges = current.getChildren();
				for (Edge edge : edges) {
					Node child = (Node) edge.getEnd().clone();
					child.setParent(current);
					child.setPathCost(current.getPathCost() + edge.getWeight());
					frontier.add(child);
				}
			}

		}
		return null;
	}

	@Override
	public Node execute(Node root, String goal, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
