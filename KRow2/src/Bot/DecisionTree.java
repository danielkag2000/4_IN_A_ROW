package Bot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.List;

public class DecisionTree {

	private static Graph createTree(State startState, int maxD) {

		Queue<Graph> queue = new LinkedList<Graph>();

		Graph returnVal = new Graph(startState);
		queue.add(returnVal);
		while (!queue.isEmpty()) {
			Graph current = queue.remove();

			if (current.getDepth() < maxD) {
				List<State> list = current.getValue().operator();
				if (list != null) {

					current.setDescendants(current.getValue().operator());

					for (Graph g : current.getDescendants()) {
						queue.add(g);
					}
				}else {
					current.setH(); // set the h(n) function
					current.setGraphValue(current.getValue().h());
				}
			} else { // current.getDepth() == maxD
				current.setH(); // set the h(n) function
				current.setGraphValue(current.getValue().h());
			}
		}

		return returnVal;
	}

	public static State getTheNextMove(State currentState, int maxD) {

		Graph start = DecisionTree.createTree(currentState, maxD);

		applyMax(start);

		int max = Integer.MIN_VALUE;
		State next = null;

		List<State> list = new ArrayList<State>();
		for (Graph g : start.getDescendants()) {
			if (g.getGraphValue() > max) {
				list.clear();
				max = g.getGraphValue();
				next = g.getValue();
				list.add(next);
			} else if(g.getGraphValue() == max){
				list.add(g.getValue());
			}
		}
		Random rnd = new Random();
		return list.get(rnd.nextInt(list.size()));
	}

	private static int applyMax(Graph current) {
		if (current.getDescendants() == null) {
			return current.getGraphValue();
		}
		int max = Integer.MIN_VALUE;
		for (Graph g : current.getDescendants()) {
			max = Math.max(max, applyMin(g));
		}
		current.setGraphValue(max);
		return max;
	}

	private static int applyMin(Graph current) {
		if (current.getDescendants() == null) {
			return current.getGraphValue();
		}
		int min = Integer.MAX_VALUE;
		for (Graph g : current.getDescendants()) {
			min = Math.min(min, applyMax(g));
		}
		current.setGraphValue(min);
		return min;
	}
}

