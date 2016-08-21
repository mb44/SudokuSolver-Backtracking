/**
 * Author: Morten Beuchert
 */

package sudoku.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Observable {
    private Set<ObserverBase> observers = new HashSet<ObserverBase>();

    public void add(ObserverBase o) {
    	observers.add(o);
    }

    public void notifyObservers() {
        Iterator<ObserverBase> it = observers.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }
}