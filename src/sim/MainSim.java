package sim;

import state.CarWashState;
import state.SimState;
import view.CarWashView;
import view.SimView;

public class MainSim {
	
	public static void main(String[] args){

		SimState carWashState = new CarWashState(2, 4, 7);
		SimView carWashView = new CarWashView(carWashState);
		Simulator carWashSim = new Simulator(carWashState, carWashView);

		carWashSim.start();

	}
}
