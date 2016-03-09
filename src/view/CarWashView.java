package view;


import carwash.CarWashState;
import carwash.SimState;
import sim.SimView;

import java.util.Observable;

public class CarWashView extends SimView {
    CarWashState carWashState;

    private static String dashedLine = String.format("%-32s %n", "-------------------------------------------");

    public CarWashView(SimState carWashState){
        this.carWashState = (CarWashState) carWashState;
        }

    @Override
    public void update(Observable arg0, Object arg1) {
        if(this.carWashState.getTime() == this.carWashState.getMaxTime()){
            printCurrentState();
            printFooter();
        }
        else{
            printCurrentState();
        }
    }
    public void printCurrentState(){
        String currentTime = String.format("%2.2f \t %3s", this.carWashState.getTime(), " ");
        String fastMachinesFree = String.format("%-12s", this.carWashState.getFastWashers(), "");
        String slowMachinesFree = String.format("%d %-9s", this.carWashState.getSlowWashers(), "");
        String carID = String.format("%-10s", this.carWashState.getCarId(), "");
        String event = String.format("%-10s", this.carWashState.getEvent(), "");
        String idleTime = String.format("%2.2f %-17s", this.carWashState.getIdleTime(), "");
        String queueTime = String.format("%2.2f \t %-13s", this.carWashState.getQueueTime(), "");
        String queueSize = String.format("%d %-22s", this.carWashState.getQueueSize(), "");
        String rejected = String.format("%d %5s", this.carWashState.getRejectedCars(), "");
        System.out.println(currentTime+ fastMachinesFree+ slowMachinesFree+ carID+ event+ idleTime+ queueTime+ queueSize+ rejected);
    }

    public void printHeader()
    {
        String seedString = String.format("%-8s %d%n", "Seed = ", this.carWashState.getSeed());
        String fastMachinesString = String.format("%-12s %d %n", "Fast machines:", this.carWashState.getFastWashers());
        String slowMachinesString = String.format("%-12s %d %n", "Slow machines:", this.carWashState.getSlowWashers());
        String fastDistribution = String.format("%-16s %1.1f %s %1.1f %s %n", "Fast distribution: (",
                this.carWashState.getLowerFast(), ",", this.carWashState.getUpperFast(), ")");

        String slowDistribution = String.format("%-16s" +
                                                "%1.1f %s %1.1f %s %n",
                                                "Slow distribution: (",
                                                this.carWashState.getLowerSlow(), ",", this.carWashState.getUpperSlow(), ")");

        String lambdaString = String.format("%-5s%1.1f %n","Exponential distribution with lambda = ",this.carWashState.getLambda());
        String maxQueueSize = String.format("%s%d %n", "Max Queue Size: ", this.carWashState.getMaxQueueSize());

        String columnHeadersShort = String.format("%-8s   %-8s    %-8s    %-8s  %-8s",
                                                  "Time", "Fast", "Slow", "Id", "Event");

        String columnHeadersLong = String.format("%-16s      %-16s        %-16s        %-16s %n",
                                                 "IdleTime", "QueueTime", "QueueSize", "Rejected");
        System.out.println(seedString +
                fastMachinesString +
                slowMachinesString +
                fastDistribution +
                slowDistribution +
                lambdaString +
                maxQueueSize +
                dashedLine +
                columnHeadersShort +
                columnHeadersLong
        );

    }


    public void printFooter(){
        String totalIdleTime = String.format("%-32s %2.2f %n", "Total idle machine time: ", carWashState.getIdleTime() );
        String totalQueueingTime = String.format("%-32s %2.2f %n", "Total queueing time: ", carWashState.getQueueTime());
        String meanQueueingTime = String.format("%-32s %2.2f %n", "Mean queueing time: ", carWashState.getMeanQueue());
        String rejectedCars = String.format("%-32s %d %n", "Rejected cars: ", carWashState.getRejectedCars());

        System.out.println(dashedLine
                            + totalIdleTime
                            + totalQueueingTime
                            + meanQueueingTime
                            + rejectedCars);
    }

}