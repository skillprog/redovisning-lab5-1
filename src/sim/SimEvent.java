/**
 * the package sim holds classes that are general.
 */

package sim;

/**
 * SimEvent calls execute(), getTime(), getRemove() and getSTOP.
 */

public interface SimEvent {

    void execute();

    double getTime();

    boolean getRemove();

    boolean getSTOP();
}
