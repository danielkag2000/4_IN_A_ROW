package Bot;

import java.util.List;

/**
 * the state interface that represents
 * what needed to be in a state
 */
public interface State {
	
	/**
	 * do the operator that give
	 * the developed state.
	 * 
	 * @return list of the developed state
	 */
	public List<State> operator();
	
	/**
	 * check if 2 states are equals
	 * @param s the state to compare to
	 * 
	 * @return true, if they are the same state
	 *         otherwise, return false.
	 */
	public boolean equals(State s);
	
	/**
	 * set the heuristic function value.
	 */
	public void setH();
	
	public String toString();
	
	/**
	 * get the heuristic function value.
	 * 
	 * @return heuristic function value
	 */
	public int h();
	
	/**
	 * get the operation that made to
	 * get to this state.
	 *
	 * @return the operation value
	 */
	public int getOperation();
}

