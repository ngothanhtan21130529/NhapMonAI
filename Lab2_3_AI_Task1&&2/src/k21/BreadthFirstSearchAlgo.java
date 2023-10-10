package k21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			else
				explored.add(current);
			List<Node> children = current.getChildrenNodes();
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}
			}
		}
		return null;
	}
//task 2
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		Queue<Node> frontier = new LinkedList<>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			else
				explored.add(current);
			List<Node> children = current.getChildrenNodes();
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}

			}
			if (current.getLabel().equals(start) && started)
				return current;
			else {
				frontier.clear();
				explored.clear();
				current.setParent(null);
			}
		}
		return null;
	}
}
