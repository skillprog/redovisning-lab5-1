package view;

import state.CarWashState;

public class CarWashView {
    CarWashState carwash;

    private static String dashedLine = String.format("%-32s %n", "-------------------------------------------");
    
    public CarWashView(CarWashState carwash){
        this.carwash = (CarWashState) carwash;
    }
    
    public void printState(){
        printHeader();
        printCurrentState();
        printFooter();
    }

    public void printCurrentState(){
        String currentTime = String.format("%2.2f \t %3s", this.carwash.getTime(), " ");
        String fastMachinesFree = String.format("%-12s", this.carwash.getFastWashers(), "");
        String slowMachinesFree = String.format("%d %-9s", this.carwash.getSlowWashers(), "");
        String carID = String.format("%-10s", this.carwash.getId(), "");
        String event = String.format("%-10s", this.carwash.getEvent(), "");
        String idleTime = String.format("%2.2f %-17s", this.carwash.getIdleTime(), ""); 
        String queueTime = String.format("%2.2f \t %-13s", this.carwash.getQueueTime(), "");
        String queueSize = String.format("%d %-22s", this.carwash.getQueueSize(), "");
        String rejected = String.format("%d %5s", this.carwash.getRejectedCars(), "");
        System.out.println(currentTime+ fastMachinesFree+ slowMachinesFree+ carID+ event+ idleTime+ queueTime+ queueSize+ rejected);
    }

    public void printHeader()
    {
        String seedString = String.format("%-8s %d%n", "Seed = ", this.carwash.getSeed());
        String fastMachinesString = String.format("%-12s %d %n", "Fast machines:", this.carwash.getFastWashers());
        String slowMachinesString = String.format("%-12s %d %n", "Slow machines:", this.carwash.getSlowWashers());
        String fastDistribution = String.format("%-16s %1.1f %s %1.1f %s %n", "Fast distribution: (",
                this.carwash.getLowerFast(), ",", this.carwash.getUpperFast(), ")");

        String slowDistribution = String.format("%-16s" +
                                                "%1.1f %s %1.1f %s %n",
                                                "Slow distribution: (",
                                                this.carwash.getLowerSlow(), ",", this.carwash.getUpperSlow(), ")");

        String lambdaString = String.format("%-5s%1.1f %n","Exponential distribution with lambda = ",this.carwash.getLambda());
        String maxQueueSize = String.format("%s%d %n", "Max Queue Size: ", this.carwash.getMaxQueueSize());

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
        String totalIdleTime = String.format("%-32s %2.2f %n", "Total idle machine time: ", carwash.getIdleTime() );
        String totalQueueingTime = String.format("%-32s %2.2f %n", "Total queueing time: ", carwash.getQueueTime());
        String meanQueueingTime = String.format("%-32s %2.2f %n", "Mean queueing time: ", carwash.getMeanQueue());
        String rejectedCars = String.format("%-32s %d %n", "Rejected cars: ", carwash.getRejectedCars());

        System.out.println(dashedLine
                            + totalIdleTime
                            + totalQueueingTime
                            + meanQueueingTime
                            + rejectedCars);
    }

}