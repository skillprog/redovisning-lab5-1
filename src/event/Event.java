package event;

import carwash.CarWashState;

public class Event {
	
	CarWashState state;
	private double time = 0;
	private int id = 0;
	private int action = 1; //Default arrive
	
	//�r olika actions
	private int start = 0;
	private int arrive = 1;
	private int leave = 2;
	private int stop = 3;
	
	private boolean stopping = false;
	private boolean removing = false;
	
	//Anv�nds i leave() kollar om bilen �kte fr�n snabb eller l�ngsam tv�tt.
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
		idle(); //R�knar samman maskinernas idle time
		state.setQueueTime(time); //R�knar samman k�tiderna enligt pdf exempel... fast den r�knar �ven rejected cars..
		if(state.getFastWashers() > 0){
			state.setTime(time);
			state.setId(id);
			state.setEvent(action);
			state.setFastWashers(-1);
			time += state.getFastRandom();
			state.carWashQueue.add(time); //Tiden f�r leave
			state.carWashQueue.add(1.0);
			action = leave;
			fast = true;
			
		}
		else if(state.getSlowWashers() > 0){
			state.setTime(time);
			state.setId(id);
			state.setEvent(action);
			state.setSlowWashers(-1);
			time += state.getSlowRandom();
			state.carWashQueue.add(time);
			state.carWashQueue.add(2.0);
			action = leave;
			slow = true;
			
		}
		else if(state.getQueueSize() < state.getMaxQueueSize()){
			double t = time; //Spara arrive tiden
			double wash = state.carWashQueue.get(1); //spara tv�tten
			state.setTime(time);
			state.setId(id);
			state.setEvent(action); //S�tter event arrival (Updaterar observer i view)
			
			if(state.carWashQueue.get(1) == 1){
				time += state.getFastRandom();	//tiden f�r att tv�ttas l�ggs till
				time += (state.carWashQueue.get(0) - t);
		
				state.carWashQueue.remove(0);
				state.carWashQueue.remove(0);
				state.carWashQueue.add(time);
				state.carWashQueue.add(1.0);
			}
			else if(state.carWashQueue.get(1) == 2){
				time += state.getSlowRandom();
				time += (state.carWashQueue.get(0) - t); // v�ntetiden f�r n�sta maskin l�ggs till
				state.carWashQueue.remove(0);
				state.carWashQueue.remove(0);
				
				state.carWashQueue.add(time);
				state.carWashQueue.add(2.0);
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
		
		if(state.getQueueSize() == 0){ //Tar bort den senaste k�andes tid och tv�tt om k�n �r tom
			while(state.carWashQueue.size() > 0){
				state.carWashQueue.remove(0);
			}
		}
		
		if(fast){
			if(state.getQueueSize() == 0){ 	//Om k�n till tv�tten �r tom s� blir tv�ttmaskinen ledig
				state.setFastWashers(1);
			}
			else{						//Annars s� minskas k�n med 1;
				state.setQueueSize(-1);
			}
		}
		else if (slow){
			if(state.getQueueSize() == 0){	//Om k�n till tv�tten �r tom s� blir tv�ttmaskinen ledig
				state.setSlowWashers(1);
			}
			else{						//Annars s� minskas k�n med 1;
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
