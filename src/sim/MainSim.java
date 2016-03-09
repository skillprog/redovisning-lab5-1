package sim;

import carwash.CarWashEvent;
import carwash.CarWashState;
import view.CarWashView;

public class MainSim {
	
	public static void main(String[] args){

		SimState carWashState = new CarWashState(2, 4, 7);
		SimView carWashView = new CarWashView(carWashState);
		CarWashEvent startEvent = new CarWashEvent(0, 0, (CarWashState) carWashState);
		Simulator carWashSim = new Simulator(carWashState, carWashView, startEvent);
		carWashSim.start();

	}
}
