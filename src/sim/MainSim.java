package sim;

import event.EventQueue;
import state.CarWashState;
import state.SimState;
import view.SimView;

public class MainSim {
	
	public static void main(String[] args){
		SimState carWashState = new CarWashState(2, 4, 7); //fast,slow,maxQueueSize
		SimView carWashView = new SimView((CarWashState) carWashState);
		Simulator carWashSim = new Simulator(carWashState, carWashView);

		carWashSim.start();

	}
}
