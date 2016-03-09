package sim;

import carwash.CWEvent;
import carwash.CWState;
import carwash.CWView;

public class MainSim {
	
	public static void main(String[] args){

		SimState carWashState = new CWState(2, 4, 7);
		SimView carWashView = new CWView(carWashState);
		CWEvent startEvent = new CWEvent(0, 0, (CWState) carWashState);
		Simulator carWashSim = new Simulator(carWashState, carWashView,  startEvent);
		carWashSim.start();

	}
}
