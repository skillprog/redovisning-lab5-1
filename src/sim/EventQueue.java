package sim;

import java.util.ArrayList;
import carwash.CarWashState;
import carwash.SimState;
import carwash.Event;

public class EventQueue {
	public ArrayList<Event> eventList = new ArrayList<>();
	private int counter = 0;
	SimState state;
	
	public EventQueue(SimState state){
		
		this.state =  state;
		Event e = new Event(0, 0, (CarWashState) state);
		eventList.add(e);
		
	}
	
	public void loop(){
		addInSequence();
		sort();
		eventList.get(0).execute();
		shift();
	}
	
	private void addInSequence(){			
		double time = ((CarWashState) state).getPreviousEventTime()+((CarWashState) state).getExpoRandom();
		Event e = new Event(time, counter, (CarWashState) state);
		eventList.add(e);
		counter++;
	}
	
	private void sort(){
		eventList.sort((e1, e2) -> Double.compare(e1.getTime(), e2.getTime()));
	}
	
	private void shift(){
		if(eventList.get(0).getRemove()){
			eventList.remove(0);
		}
	}
}
