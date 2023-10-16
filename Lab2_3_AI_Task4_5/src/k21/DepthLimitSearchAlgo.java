package k21;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthLimitSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		return null;
	}

	@Override
	public Node execute(Node root, String goal, int limit) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal))
				return current;
			else if(current.getDepth()<limit){
				List<Node> children=current.getChildrenNodes();
				for(Node child:children) {
					if(!frontier.contains(child)&& !explored.contains(child)) {
						frontier.add(child);
						child.setDepth(current.getDepth()+1);
						
					}
				}
			}
		}
		return null;
	}

}
