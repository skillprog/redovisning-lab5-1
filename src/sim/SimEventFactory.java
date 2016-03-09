package sim;

import carwash.SimState;

/**
 * Created by emilaasa on 09/03/16.
 */
public interface SimEventFactory {
    void getNextEvent(double time, int id, SimState state);
}
