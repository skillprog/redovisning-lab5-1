/**
 * the package sim holds classes that are general.
 */

package sim;

import java.util.*;

/**
 * SimView reacts to updates in the state and prints the view.
 */
public abstract class SimView implements Observer{

	protected SimView() {

	}

	public void update(Observable arg0, Object arg1) {
	}

	public void printHeader() {
	}
}
