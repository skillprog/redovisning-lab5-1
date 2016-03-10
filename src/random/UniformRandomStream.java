/**
 * the package random contains classes that calculates the arrive and machine times.
 */
package random;

import java.util.Random;

/**
 * UniformRandomStreams creates the simulated time used for the car washing machines.
 */
public class UniformRandomStream {

	private Random rand;
	private double lower, width;

	/**
	 * UniformRandomStream creates simulated time for washing machines.
	 * @param lower
	 * @param upper
	 * @param seed
     */
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper-lower;
	}

	/**
	 * is this ever used??
	 * @param lower
	 * @param upper
     */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
	    this.lower = lower;
	    this.width = upper-lower;
	}

	/**
	 *
	 * @return returns a value of type double to calculate machine times.
     */
	public double next() {
	    return lower+rand.nextDouble()*width;
	}
}