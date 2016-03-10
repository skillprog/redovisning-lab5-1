/**
 * the package carwash holds all classes that are specific for the carwash machine.
 */

package carwash;

import java.util.ArrayList;
import java.util.Collections;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import sim.SimEvent;
import sim.SimState;

/**
 * CWState holds and keeps track of the state in the carwash.
 */
public class CWState extends SimState {
	
	private double lambda = 1.5;
	private double maxSimTime = 15; //Best�m n�r programmet ska avsluta
	
	private int fastWashers = 0;
	private int slowWashers = 0;
	private int maxQueue = 0;

	private int queueSize = 0;
	private int rejectedCars = 0;
	private double idleTime = 0;
	private double simulationTime = 0;
	private String event ="";
	private String carId = "-";
	private double previousEventTime = 0;
	
	private double queueTime = 0;
	private double lastQueueTime = 0;
	
	public ArrayList<double[]> carWashQueue = new ArrayList<double[]>();
	
	private long seed = 1234;
	private double lowerFast = 2.8;
	private double upperFast = 5.6;
	private double lowerSlow = 4.5;
	private double upperSlow = 6.7;
	
	private UniformRandomStream fastRandom;
	private UniformRandomStream slowRandom;
	private ExponentialRandomStream expoRandom;


	/**
	 * the constructor CWStat initalizes the fastWashers, slowWashers and maxQueueSize.
	 * @param fastWashers
	 * @param slowWashers
	 * @param maxQueueSize
     */
	public CWState(int fastWashers, int slowWashers , int maxQueueSize){
		this.fastWashers = fastWashers;
		this.slowWashers = slowWashers;
		this.maxQueue = maxQueueSize;
		this.fastRandom = new UniformRandomStream(lowerFast,upperFast,seed);
		this.slowRandom = new UniformRandomStream(lowerSlow,upperSlow,seed);
		this.expoRandom = new ExponentialRandomStream(lambda,seed);
	}

	//TODO can we refactor this?

	/**
	 * sort is sorting the carWashQueue.
	 */
	public void sort(){ //Kr�vs ifall en carId I k�n avslutas f�re ett carId tidigare i k�n. �ndrar positionen s� den blir korrekt
		carWashQueue.sort((e1, e2) -> Double.compare(e1[0], e2[0]));	
	}

	/**
	 * getMaxTime returns the maxSimTime.
	 * @return returns the maxSimTime.
	 */
	public double getMaxTime(){
		return maxSimTime;
	}

	/**
	 * getIdleTime returns idletime.
	 * @return returns idletime.
     */
	public double getIdleTime(){
		return idleTime;
	}

	/**
	 * getFastWashers returns the fastWashers.
	 * @return returns the fastWashers.
     */
	public int getFastWashers(){
		return fastWashers;
	}

	/**
	 * getSlowWashers returns the slowWashers.
	 * @return returns the slowWashers.
     */
	public int getSlowWashers(){
		return slowWashers;
	}

	/**
	 * getLowerFast returns the lowerFast.
	 * @return returns the lowerFast.
     */
	public double getLowerFast(){
		return lowerFast;
	}

	/**
	 * getUpperFast returns the upperFast.
	 * @return returns the upperFast.
     */
	public double getUpperFast(){
		return upperFast;
	}

	/**
	 * getLowerSlow returns the lowerSLow.
	 * @return returns the lowerSLow.
     */
	public double getLowerSlow(){
		return lowerSlow;
	}

	/**
	 * getUpperSlow returns the upperSlow.
	 * @return returns the upperSlow.
     */
	public double getUpperSlow(){
		return upperSlow;
	}

	/**
	 * getLambda returns the lambda.
	 * @return returns the lambda.
     */
	public double getLambda(){
		return lambda;		
	}

	/**
	 * getSeed returns the getSeed.
	 * @return returns the getSeed.
     */
	public long getSeed(){
		return seed;
	}

	/**
	 * getMaxQueueSize returns the maxQueue.
	 * @return returns the maxQueue.
     */
	public int getMaxQueueSize(){
		return maxQueue;
	}

	/**
	 * getQueueTime returns the queueTime.
	 * @return
     */
	public double getQueueTime(){
		return queueTime;
	}

	/**
	 * getRejectedCars returns the rejectedCars.
	 * @return returns the rejectedCars.
     */
	public int getRejectedCars(){
		return rejectedCars;
	}

	/**
	 * getFastRandom returns the getFastRandom.
	 * @return returns the getFastRandom.
     */
	public double getFastRandom(){
		return fastRandom.next();
	}

	/**
	 * getSLowRandom returns the slowRandom.
	 * @return returns the slowRandom.
     */
	public double getSlowRandom(){
		return  slowRandom.next();
	}

	/**
	 * getExpoRandom returns the expoRandom.next().
	 * @return returns the expoRandom.next().
     */
	public double getExpoRandom(){
		return expoRandom.next();
	}

	/**
	 * getEvent returns the event.
	 * @return returns the event.
     */
	public String getEvent(){
		return event;
	}

	/**
	 * getTime returns the simulationTime.
	 * @return returns the simulationTime.
     */
	public double getTime(){
		return simulationTime;
	}

	/**
	 * getCardId returns the carID.
	 * @return returns the carID.
     */
	public String getCarId(){
		return carId;
	}

	/**
	 * getMeanQueue returns the MeanQueueTime.
	 * @return returns the MeanQueueTime.
     */
	public double getMeanQueue(){
		//TODO
		return getQueueTime()/19; //Var i helvete kommer 19 ifr�n? Har h�kan gjort fel?
	}

	/**
	 * getQueueSize returns the queueSize.
	 * @return returns the queueSize.
     */
	public int getQueueSize(){
		return queueSize;
	}

	/**
	 * getPreviousEventTime returns the previousEventTime.
	 * @return returns the previousEventTime.
     */
	public double getPreviousEventTime(){
		return previousEventTime;
	}

	/**
	 * setPreviousEventTime sets the previousEventtime to a variable x.
	 * @param x
     */
	public void setPreviousEventTime(double x){
		previousEventTime = x;
	}

	/**
	 * setRejected has a varaiable x and an argument variable rejectedCars that increases whenever this class is called.
	 * @param x
     */
	public void setRejected(int x){
		rejectedCars += x;
	}

	/**
	 * setIdle sets the idleTime and increases it whenever this class is called.
	 * @param x
     */
	public void setIdle(double x){
		idleTime += x;
	}

	/**
	 * setQueueSize sets the queueSize and it increases it whenever this class is called.
	 * @param x
     */
	public void setQueueSize(int x){
		queueSize += x;
	}

	/**
	 * setQueueTime calculates the queueTime.
	 * @param time
     */
	public void setQueueTime(double time){
		if(lastQueueTime == 0){
			lastQueueTime = time;
		}
		else{
			queueTime += queueSize * (time - lastQueueTime);
			lastQueueTime = time;
		}
	}

	/**
	 * setCarId sets a unique carId.
	 * @param x
     */
	public void setCarId(int x){

		carId = "" + x;
	}

	/**
	 * setEvent checks what event is happening and then notifies the observers.
	 * @param x
     */
	public void setEvent(int x){

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
			carId = "-";
		}
		setChanged();
		notifyObservers();
	}

	/**
	 *setSimulationtime sets the simulation time to a variable x.
	 * @param x
     */
	public void setSimulationTime(double x){
		simulationTime = x;
	}

	/**
	 * changeFastWashers changes the number of fast washers when called.
	 * @param x
     */
	public void changeFastWashers(double x){

		fastWashers += x;
	}

	/**
	 * changeSlowWashers changes the number of slow washers when called.
	 * @param x
     */
	public void changeSlowWashers(double x){

		slowWashers += x;
	}
	private int counter = 0;

	/**
	 * addInSequence adds events to the SimEvent arraylist.
	 * @param carWashEventList
	 * @return returns carWashEventList.
     */
	@Override
	public ArrayList<SimEvent> addInSequence(ArrayList<SimEvent> carWashEventList){
		double time = this.getPreviousEventTime()+this.getExpoRandom();
		CWEvent e = new CWEvent(time, counter, this);
		carWashEventList.add(e);
		counter++;
		return carWashEventList;
	}

}
