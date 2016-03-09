package sim;

/**
 * Simulator runs with the given parameters.
 */
public class Simulator {
	SimState simState;
	EventQueue eventQueue;
	SimView simView;

	/**
	 * the constructor initializes the simState, eventQueue and simView.
	 * @param simState
	 * @param simView
	 * @param startEvent
     */
	public Simulator(SimState simState, SimView simView, SimEvent startEvent){
		this.simState = simState;
		this.eventQueue = new EventQueue(simState, startEvent);
		this.simView = simView;
		setUp();
		}

	/**
	 * setUp adds an observer to the simView and then prints the header of view.
	 */
	private void setUp() {
		this.simState.addObserver(this.simView);
		this.simView.printHeader();
	}

	/**
	 * start enables the loop in which all the events are started.
	 */
	public void start(){

		do{
			this.eventQueue.loop();
		}while(!eventQueue.eventList.get(0).getSTOP());

	}
}
