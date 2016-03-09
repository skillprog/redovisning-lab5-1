package event;

import java.util.ArrayList;
import state.CarWashState;
import state.SimState;

public class EventQueue {
	public ArrayList<Event> eventList = new ArrayList<Event>();
	private int counter = 0;
	SimState state;
	
	public EventQueue(SimState state){
		
		this.state = (CarWashState) state;
		Event e = new Event(0, 0, (CarWashState) state);
		eventList.add(e);
		
	}
	
	public void loop(){
		addInSequence();
		sort();
		// Förstog inte vad du menade med "den inte ska behöva veta vilken sorts event som executas"
		eventList.get(0).execute();
		pop(); 
	}
	
	private void addInSequence(){			
		double time = ((CarWashState) state).getLastTime()+((CarWashState) state).getExpoRandom();
		Event e = new Event(time, counter, (CarWashState) state);
		eventList.add(e);
		counter++;
	}
	
	private void sort(){
		eventList.sort((e1, e2) -> Double.compare(e1.getTime(), e2.getTime()));
	}
	
	private void pop(){
		if(eventList.get(0).getRemove()){
			eventList.remove(0);
		}
	}
}
