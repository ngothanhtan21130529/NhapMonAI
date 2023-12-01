package game_nim_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();
	private Node bestmove;

	public Node getBestmove() {
		return bestmove;
	}

	public void setBestmove(Node bestmove) {
		this.bestmove = bestmove;
	}

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		// Enter your code here
		List<Node> list = new ArrayList<Node>();
		List<Integer> datas = new ArrayList<Integer>();

		Collections.sort(this.data, DESCOMPARATOR);
		datas.addAll(data);

		Integer val = datas.remove(0);
		Integer des = val - 1;
		Integer count = 1;

		while (count != des) {
			datas.add(count);
			datas.add(des);

			Node node = new Node();
			node.addAll(datas);
			list.add(node);

			datas.remove(count);
			datas.remove(des);

			count++;
			des--;
			if (des == (val / 2)) {
				break;
			}
		}

		return list;
	
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		// Enter your code here
		if (getSuccessors().isEmpty()) {
			return true;
		}
		return false;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

}
