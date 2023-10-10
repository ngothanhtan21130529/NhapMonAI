package k21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BreadthTreeFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal))
				return currentNode;

			List<Edge> edges = currentNode.getChildren();
			for (Edge edge : edges) {
				Node childNode = (Node) edge.getEnd().clone();
				childNode.setParent(currentNode);
				childNode.setPathCost(currentNode.getPathCost() + edge.getWeight());
				frontier.add(childNode);
			}
		}
		return null;
	}
//task 2
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal))
				return currentNode;

			if (currentNode.getLabel().equals(start)) {
				frontier.clear();
				currentNode.setParent(null);
				currentNode.setPathCost(0);
			}

			List<Edge> edges = currentNode.getChildren();
			for (Edge edge : edges) {
				Node childNode = (Node) edge.getEnd().clone();
				childNode.setParent(currentNode);
				childNode.setPathCost(currentNode.getPathCost() + edge.getWeight());
				frontier.add(childNode);
			}
	}
		return null;
}
}