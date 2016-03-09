package sim;

import event.EventQueue;
import state.CarWashState;
import state.SimState;
import view.SimView;

public class Simulator {
	SimState state;
	EventQueue eventQueue;
	SimView simView;

	public Simulator(SimState state, SimView simView){
		this.state = state;
		this.eventQueue = new EventQueue(state);
		this.simView = simView;
		
		}


	public void start(){
	while(!eventQueue.eventList.get(0).getStop()){
		this.eventQueue.loop();
	}

	}
}
