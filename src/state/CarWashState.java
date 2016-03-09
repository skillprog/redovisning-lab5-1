package state;

import java.util.ArrayList;
import java.util.Collections;

import random.ExponentialRandomStream;
import random.UniformRandomStream;

public class CarWashState extends SimState{
	
	private double lambda = 1.5;	//Skrivlambda här
	private double maxSimTime = 15; //Bestäm när programmet ska avsluta
	
	private int fastWashers = 0;
	private int slowWashers = 0;
	private int maxQueue = 0;
	private int queueSize = 0;
	private int rejectedCars = 0;
	private double idleTime = 0;
	private double simulationTime = 0;
	private String event ="";			//Printas som event namn
	private String id = "-";			//Printas som id nummer
	private double lastTime = 0; //variabel med det föregåend arrive-tiden, används för att skapa nästa arrive.
	
	private double queueTime = 0;
	private double lastQueueTime = 0;
	
	public ArrayList<Double> array = new ArrayList<Double>(); //ArrayList för kön till tvätten
	
	//För UniformRandomStream fast/slow
	private long seed = 1234;
	private double lowerFast = 2.8;
	private double upperFast = 5.6;
	private double lowerSlow = 4.5;
	private double upperSlow = 6.7;
	
	private UniformRandomStream fastRandom;
	private UniformRandomStream slowRandom;
	private ExponentialRandomStream expoRandom;

	public CarWashState(int fast,int slow,int q){
		fastWashers = fast;
		slowWashers = slow;
		maxQueue = q;
		fastRandom = new UniformRandomStream(lowerFast,upperFast,seed);
		slowRandom = new UniformRandomStream(lowerSlow,upperSlow,seed);
		expoRandom = new ExponentialRandomStream(lambda,seed);
	}
	
	public void sort(){ //Krävs ifall en id I kön avslutas före ett id tidigare i kön. Ändrar positionen så den blir korrekt		
		if(array.size() > 2){
			for(int i=0;i<array.size()-2;i += 2){ //byter plats så den största tiden hamnar längst bak
				if(array.get(i) > array.get(i+2)) {
					Collections.swap(array, i, i+2);
					Collections.swap(array, i+1, i+3); //ojämna index håller koll på snabb/långsam tvätt
				}
			}
		}	
	}
	public double getMaxTime(){
		return maxSimTime;
	}
	public double getIdleTime(){
		return idleTime;
	}
	
	public int getFastWashers(){
		return fastWashers;
	}
	
	public int getSlowWashers(){
		return slowWashers;
	}
	
	public double getLowerFast(){
		return lowerFast;
	}
	
	public double getUpperFast(){
		return upperFast;
	}
	
	public double getLowerSlow(){
		return lowerSlow;
	}
	
	public double getUpperSlow(){
		return upperSlow;
	}
	
	public double getLambda(){
		return lambda;		
	}
	
	public long getSeed(){
		return seed;
	}
	
	public int getMaxQueueSize(){
		return maxQueue;
	}
	
	public double getQueueTime(){
		return queueTime;
	}
	
	public int getRejectedCars(){
		return rejectedCars;
	}
	
	public double getFastRandom(){
		double f = fastRandom.next();
		return f;
	}
	
	public double getSlowRandom(){
		double f = slowRandom.next();
		return f;
	}
	
	public double getExpoRandom(){
		return expoRandom.next();
	}
	
	public String getEvent(){
		return event;
	}
	
	public double getTime(){
		return simulationTime;
	}
	
	public String getId(){
		return id;
	}
	
	public double getMeanQueue(){
		return getQueueTime()/19; //Var i helvete kommer 19 ifrån? Har håkan gjort fel?
	}
	
	public int getQueueSize(){
		return queueSize;
	}
	
	public double getLastTime(){
		return lastTime;
	}
	
	public void setLastTime(double x){
		lastTime = x;
	}
	
	public void setRejected(int x){
		rejectedCars += x;
	}
	
	public void setIdle(double x){
		idleTime += x;
	}
	
	public void setQueueSize(int x){
		queueSize += x;
	}
	
	public void setQueueTime(double time){ //Räknar ut queueTime
		if(lastQueueTime == 0){
			lastQueueTime = time;
		}
		else{
			queueTime += queueSize * (time - lastQueueTime);
			lastQueueTime = time;
		}
	}
	
	public void setId(int x){
		id = ""+x;
	}
	
	public void setEvent(int x){
//		System.out.println("vad i helvete " + x);
		if(x == 1){
			event = "Arrive";
		}
		else if (x == 2){
			event = "Leave";
		}
		else if(x == 0){
			event = "Start";
		}
		else{
			event = "Stop";
			id = "-";
		}
		setChanged();
		notifyObservers();
	}
	
	public void setTime(double x){
		simulationTime = x;
	}
	
	public void setFastWashers(double x){
		fastWashers += x;
	}
	
	public void setSlowWashers(double x){
		slowWashers += x;
	}
}
