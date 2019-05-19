package Observe;

public interface Observer {
    
	/**
	 * 
	 * @param o the Observable who called
	 * @param args the data that notified
	 */
	void update(Observable o, Object args);
}
