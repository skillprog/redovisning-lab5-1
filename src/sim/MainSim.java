package sim;

import carwash.CarWashState;
import carwash.SimState;
import view.CarWashView;

public class MainSim {
	
	public static void main(String[] args){

		SimState carWashState = new CarWashState(2, 4, 7);
		SimView carWashView = new CarWashView(carWashState);
		Simulator carWashSim = new Simulator(carWashState, carWashView);

		carWashSim.start();

	}
}
