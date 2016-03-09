package sim;
import carwash.CarWashEvent;

import java.util.ArrayList;
import java.util.Observable;

public abstract class SimState extends Observable {

    public abstract ArrayList<CarWashEvent> addInSequence(ArrayList<CarWashEvent> carWashEventList);

}
