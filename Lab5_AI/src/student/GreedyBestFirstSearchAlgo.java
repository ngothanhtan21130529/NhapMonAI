package student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
		frontier.add(model.getInitialState());
		ArrayList<Node> exp = new ArrayList<Node>();
		Node current;
		while (!frontier.isEmpty()) {
			current = frontier.remove();
			if (current.getH() == 0) {
				return current;
			}
			if (!exp.contains(current)) {
				List<Node> children = model.getSuccessors(current);
				for (Node child : children) {
					child.setG(current.getG() + 1);
					frontier.add(child);
				}
				exp.add(current);
			}
		}

		return null;
	}

}
