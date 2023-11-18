package lab_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 500;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		int iteration = 0;
		Node child = new Node();
		while (iteration++ < MAX_ITERATIONS) {
			List<Node> newPopulation = new ArrayList<Node>();
			for (int i = 0; i < population.size(); i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();
				child = preproduce(x, y);

				if (Math.random() * 1 < MUTATION_RATE) {
					child = mutate(child);
				}
				if (child.getH() == 0) {
					return child;
				}
				newPopulation.add(child);
			}
			population = newPopulation;
		}
		Collections.sort(population);
		return population.get(0);
	}

	// Mutate an individual by selecting a random Queen and
	// move it to a random row.
	public Node mutate(Node node) {
		Random rand = new Random();
		int index = rand.nextInt(Node.N);
		node.getState()[index].setRow(rand.nextInt(Node.N));
		return node;
	}

	// Crossover x and y to preproduce a child
	public Node preproduce(Node x, Node y) {
		Node child = new Node();
		Random random = new Random();
		int point = random.nextInt(x.getState().length);
		for (int i = 0; i < point; i++) {
			child.getState()[i] = x.getState()[i];
		}
		for (int i = point; i < y.getState().length; i++) {
			child.getState()[i] = y.getState()[i];
		}
		return child;
	}

	// Select K individuals from the population at random and
	// select the best out of these to become a parent.
	public Node getParentByTournamentSelection() {
		Random rand = new Random();
		int k = 50;

		Node parent = new Node();
		for (int i = 0; i < k; i++) {
			Node node = population.get(rand.nextInt(population.size()));
			if (node.getH() < parent.getH()) {
				parent = new Node(node);
			}
		}
		return parent;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		Random random = new Random();
		Node parent = population.get(random.nextInt(population.size()));
		return parent;
	}
}
