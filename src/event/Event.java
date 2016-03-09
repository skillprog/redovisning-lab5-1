package event;

import state.CarWashState;

public class Event {
	
	CarWashState state;
	private double time = 0;
	private int id = 0;
	private int action = 1; //Default arrive
	
	//Är olika actions
	private int start = 0;
	private int arrive = 1;
	private int leave = 2;
	private int stop = 3;
	
	private boolean stopping = false;
	private boolean removing = false;
	
	//Används i leave() kollar om bilen åkte från snabb eller långsam tvätt.
	private boolean fast = false;
	private boolean slow = false;

	public Event(double t, int id, CarWashState state){
		time = t;
		state.setLastTime(t);
		this.state = state;
		this.id = id;
	}
	
	public void execute(){
		if(time == 0){
			start();
		}
		else if(time >= state.getMaxTime()){
			stop();
		}
		else if(action == arrive){
			Arrival();
		}else if(action == leave){
			Leave();
		}
	}
	
	private void start(){
		idle();
		action = start;
		state.setEvent(action);//Uppdaterar i carwashview
		removing = true;
	}
	
	private void stop(){
		idle();
		state.setQueueTime(state.getMaxTime());
		action = stop;
		state.setTime(state.getMaxTime());
		state.setEvent(action);
		stopping = true;
	}
	
	private void Arrival(){
		idle(); //Räknar samman maskinernas idle time
		state.setQueueTime(time); //Räknar samman kötiderna enligt pdf exempel... fast den räknar även rejected cars..
		if(state.getFastWashers() > 0){
			state.setTime(time);
			state.setId(id);
			state.setEvent(action);
			state.setFastWashers(-1);
			time += state.getFastRandom();
			state.array.add(time); //Tiden för leave
			state.array.add(1.0);
			action = leave;
			fast = true;
			
		}
		else if(state.getSlowWashers() > 0){
			state.setTime(time);
			state.setId(id);
			state.setEvent(action);
			state.setSlowWashers(-1);
			time += state.getSlowRandom();
			state.array.add(time);
			state.array.add(2.0);
			action = leave;
			slow = true;
			
		}
		else if(state.getQueueSize() < state.getMaxQueueSize()){
			double t = time; //Spara arrive tiden
			double wash = state.array.get(1); //spara tvätten
			state.setTime(time);
			state.setId(id);
			state.setEvent(action); //Sätter event arrival (Updaterar observer i view)
			
			if(state.array.get(1) == 1){
				time += state.getFastRandom();	//tiden för att tvättas läggs till
				time += (state.array.get(0) - t);							
		
				state.array.remove(0);
				state.array.remove(0);			
				state.array.add(time);
				state.array.add(1.0);
			}
			else if(state.array.get(1) == 2){
				time += state.getSlowRandom();
				time += (state.array.get(0) - t); // väntetiden för nästa maskin läggs till
				state.array.remove(0);
				state.array.remove(0);
				
				state.array.add(time);
				state.array.add(2.0);
			}			
			state.setQueueSize(1);
			action = leave;
			
			if(wash == 1){
				fast = true;
			}
			else{
				slow = true;
			}
		}
		else{
			state.setTime(time);
			state.setId(id);
			state.setEvent(action);
			
			state.setRejected(1);
			removing = true;
		}
		state.sort();
	}
	
	private void idle(){
		double diff = state.getTime();
		diff = time - diff;
		state.setIdle(diff * (state.getFastWashers() + state.getSlowWashers()));
	}
	
	private void Leave(){		
		idle();
		state.setQueueTime(time);
		state.setId(id);
		state.setTime(time);
		state.setEvent(action);
		
		if(state.getQueueSize() == 0){ //Tar bort den senaste köandes tid och tvätt om kön är tom
			while(state.array.size() > 0){
				state.array.remove(0);
			}
		}
		
		if(fast){
			if(state.getQueueSize() == 0){ 	//Om kön till tvätten är tom så blir tvättmaskinen ledig
				state.setFastWashers(1);
			}
			else{						//Annars så minskas kön med 1;
				state.setQueueSize(-1);
			}
		}
		else if (slow){
			if(state.getQueueSize() == 0){	//Om kön till tvätten är tom så blir tvättmaskinen ledig
				state.setSlowWashers(1);
			}
			else{						//Annars så minskas kön med 1;
				state.setQueueSize(-1);
			}
		}
		removing = true;
	}
	
	public double getTime(){
		return time;
	}
	
	public int getAction(){
		return action;
	}
	
	public boolean getStop(){
		return stopping;
	}
	
	public boolean getRemove(){
		return removing;
	}
}
