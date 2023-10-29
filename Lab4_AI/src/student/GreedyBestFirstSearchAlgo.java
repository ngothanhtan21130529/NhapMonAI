package student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

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
						child.setParent(current);
						frontier.add(current);

					}
					if (!frontier.contains(child) && child.getH() > current.getH()) {

					}

				}
			}

		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
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
			return null;
		}

	}
}
