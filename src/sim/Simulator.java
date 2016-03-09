package sim;

import carwash.CarWashEvent;

public class Simulator {
	SimState simState;
	EventQueue eventQueue;
	SimView simView;

	public Simulator(SimState simState, SimView simView, CarWashEvent startEvent){
		this.simState = simState;
		this.eventQueue = new EventQueue(simState, startEvent);
		this.simView = simView;
		setUp();
		}

	private void setUp() {
		this.simState.addObserver(this.simView);
		this.simView.printHeader();
	}

	public void start(){

		do{
			this.eventQueue.loop();
		}while(!eventQueue.carWashEventList.get(0).getStop());

	}
}
