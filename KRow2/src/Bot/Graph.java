package Bot;


import java.util.ArrayList;
import java.util.List;

/**
 * the data structure that represents
 * the State graph of the problem.
 * 
 */
public class Graph {

	// the descendants
	private List<Graph> descendants;
	// the value of the node
	private State value;
	// the parent
	private Graph parent;
	// the depth of the node in the graph
	private int depth;
	// id of creation
	private int id;
	// the graph int value
	private int graphValue;

	/**
	 * constructor
	 *
	 * @param value the value state of the node
	 */
	public Graph(State value) {
		this.parent = null;
		this.descendants = null;
		this.value = value;
		this.depth = 0;
		this.id = 0;
		this.graphValue = 0;
	}

	/**
	 * constructor
	 *
	 * @param value the value state of the node
	 * @param parent the parent node
	 */
	public Graph(State value, Graph parent, int id) {
		this.parent = parent;
		this.descendants = null;
		this.value = value;
		this.depth = parent.depth + 1;
		this.id = id;
		this.graphValue = 0;
	}

	/**
	 * getter of value
	 *
	 * @return the state of the node
	 */
	public State getValue() {
		return this.value;
	}
	
	/**
	 * getter of parent
	 *
	 * @return the parent node of the node
	 */
	public Graph getParent() {
		return this.parent;
	}

	/**
	 * set the descendants of the node
	 * if the node already have descendants
	 * the node will not set the new descendants
	 *
	 * @param descendantsList a list of the descendants
	 * @param developed the number of nodes that developed
	 */
	public void setDescendants(List<State> descendantsList, int developed) {
		// if the node already have descendants
		if (this.descendants != null) {
			return;
		}
		
		List<Graph> descList = new ArrayList<Graph>();
		int newID = developed;
		for (State child : descendantsList) {
			newID++;
			descList.add(new Graph(child, this, newID));
		}
		this.descendants = descList;
	}
	
	/**
	 * set the descendants of the node
	 * if the node already have descendants
	 * the node will not set the new descendants
	 *
	 * @param descendantsList a list of the descendants
	 */
	public void setDescendants(List<State> descendantsList) {
		setDescendants(descendantsList, 0);
	}

	/**
	 * getter of descendants
	 *
	 * @return the descendants nodes of the node
	 */
	public List<Graph> getDescendants() {
		return this.descendants;
	}
	
	/**
	 * the f function that defined
	 * as f(n) = g(n) + h(n)
	 *
	 * @return the value of f(n)
	 */
	public int f() {
		return this.depth + this.value.h();
	}

	/**
	 * set the h(n) value
	 */	
	public void setH() {
		this.value.setH();
	}
	
	/**
	 * set the g(n) value
	 */
	public void setG() {
		this.depth = parent.depth + 1;
	}
	
	/**
	 * get the depth of the node
	 */
	public int getDepth() {
		return this.depth;
	}
	
	/**
	 * get the id of the node
	 */
	public int getID() {
		return this.id;
	}
	
	public void setGraphValue(int value) {
		this.graphValue = value;
	}
	
	public int getGraphValue() {
		return this.graphValue;
	}
}

