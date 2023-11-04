package student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarAlgo implements IPuzzleAlgo{

	@Override
	public Node execute(Puzzle model) {
		PriorityQueue<Node> fron= new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
		List<Node> exp= new ArrayList<Node>();
		fron.add(model.getInitialState());
		Node cr;
		while (!fron.isEmpty()) {
			cr= fron.poll();
			if (cr.getH()==0) return cr;
			if (!exp.contains(cr)) {
				List<Node> list=model.getSuccessors(cr);
				for (Node child: list) {
					child.setG(cr.getG()+1);
					fron.add(child);
				}
				exp.add(cr);
			}
		}
		
		return null;
	}

}
