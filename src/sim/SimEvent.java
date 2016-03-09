package sim;

/**
 * Created by emilaasa on 09/03/16.
 */
public interface SimEvent {

    void execute();

    double getTime();

    boolean getRemove();

    boolean getSTOP();
}
