package sim;

import event.EventQueue;
import state.CarWashState;
import state.SimState;
import view.SimView;

public class MainSim {
	
	public static void main(String[] args){
		SimState state = new CarWashState(2, 4, 7); //fast,slow,maxQueueSize
		SimView simView = new SimView((CarWashState) state);
		Simulator hej = new Simulator(state, simView);
	}
}
