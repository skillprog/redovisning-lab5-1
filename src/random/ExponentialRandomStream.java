/**
 * the package random contains classes that calculates the arrive and machine times.
 */

package random;

import java.util.Random;

/**
 * ExponentialRandomStream creates the simulated time used for car arrivals.
 */
public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;

	/**
	 * the constructor ExponentialRandomStream initializes lambda and random seed.
	 * @param lambda
	 * @param seed
     */
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}

	/**
	 * @param lambda is initialized
     */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}

	/**
	 *
	 * @return returns a value in type double needed for event times.
     */
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
}
