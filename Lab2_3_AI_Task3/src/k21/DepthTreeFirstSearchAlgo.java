package k21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthTreeFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();
		frontier.push(root);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			if (currentNode.getLabel().equals(goal))
				return currentNode;

			List<Edge> edges = currentNode.getChildren();
			Collections.sort(edges);
			for (Edge edge : edges) {
				Node childNode = (Node) edge.getEnd().clone();
				childNode.setParent(currentNode);
				childNode.setPathCost(currentNode.getPathCost() + edge.getWeight());
				frontier.push(childNode);
			}
		}
		return null;
	}
//task 2
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();
		frontier.push(root);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			if (currentNode.getLabel().equals(goal))
				return currentNode;
			if (currentNode.getLabel().equals(start)) {
				frontier.clear();
				currentNode.setParent(null);
				currentNode.setPathCost(0);
			}
			List<Edge> edges = currentNode.getChildren();
			Collections.sort(edges);
			for (Edge edge : edges) {
				Node childNode = (Node) edge.getEnd().clone();
				childNode.setParent(currentNode);
				childNode.setPathCost(currentNode.getPathCost() + edge.getWeight());
				frontier.push(childNode);
			}
		}
		return null;
	}
}
