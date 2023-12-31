package student;

public class SAAlgorithm {
	public Node execute(Node initialState) {
		int T = 500000;
		Node current = initialState;
		while (current.getH() != 0) {
			if (T == 0) {
				System.out.println("T: " + T);
				return current;
			}
			Node next = current.selectNextRandomCandidate();
			int deltaE = next.getH() - current.getH();
			if (deltaE > 0) {
				current = next;
			} else if (Math.pow(Math.E, deltaE / T) > Math.random()) {
				current = next;
			}
			T--;
		}
		System.out.println("T: " + T);
		return current;
	}
}
