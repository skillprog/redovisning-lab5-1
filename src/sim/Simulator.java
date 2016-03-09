package sim;

import event.EventQueue;
import state.SimState;
import view.SimView;

public class Simulator {
	SimState simState;
	EventQueue eventQueue;
	SimView simView;

	public Simulator(SimState simState, SimView simView){
		this.simState = simState;
		this.eventQueue = new EventQueue(simState);
		this.simView = simView;
		setUp();
		}

	private void setUp() {
		this.simState.addObserver(this.simView);
		this.simView.printHeader();
	}

	public void start(){
	while(!eventQueue.eventList.get(0).getStop()){
		this.eventQueue.loop();
	}

	}
}
