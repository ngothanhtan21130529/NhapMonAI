package student;

public class HillClimbing {
	public Node execute(Node Start ){
		Node current = new Node(Start);
		Node next = new Node();
		while (true) {
			
			next= current.getBestCandidate();
				if(next.getH() < current.getH()) {
					current=next;
			}else {
				return  current;
			}
		}
	}

	public Node executeHillClimbingWithRandomRestart(Node Start) {
		// Enter your code here.
		Node state = execute(Start);
		while (state.getH()!=0) {
			state = new Node();
			state.generateBoard();
		
			state = execute(state);
		}
		return state;
	}
}
