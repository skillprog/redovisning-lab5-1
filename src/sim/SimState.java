package sim;

import java.util.ArrayList;
import java.util.Observable;

/**
 * SimState
 */
public abstract class SimState extends Observable {

    public abstract ArrayList<SimEvent> addInSequence(ArrayList<SimEvent> carWashEventList);

}
