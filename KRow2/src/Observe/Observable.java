package Observe;
import java.util.LinkedList;
import java.util.List;

public class Observable {

	private List<Observer> obsList;
	
	public Observable() {
		obsList = new LinkedList<Observer>();
	}
	
	/**
	 * notify the observers that are registered
	 * @param args the data to notify about
	 */
	public final void notifyObservers(Object args) {
		for (Observer obs : obsList) {
			obs.update(this, args);
		}
	}

	/**
	 * add observer
	 * @param obs the observer
	 */
	public final void addObserver(Observer obs) {
		obsList.add(obs);
	}

	/**
	 * remove observer
	 * @param obs the observer
	 */
	public final void removeObserver(Observer obs) {
		obsList.remove(obs);
	}
}
