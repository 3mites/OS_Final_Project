package fcfss;

import java.util.ArrayList;
import java.util.Scanner;

class Process {
    private final String name;
    private final int arrivalTime;
    private final int burstTime;

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }
}

public class FCFSS {
    public static void main(String[] args) {
        ArrayList<Process> processes;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of processes: ");
            int numProcesses = scanner.nextInt();
            processes = new ArrayList<>();
            for (int i = 1; i <= numProcesses; i++) {
                System.out.print("Enter the arrival time of process P" + i + ": ");
                int arrivalTime = scanner.nextInt();
                
                System.out.print("Enter the burst time of process P" + i + ": ");
                int burstTime = scanner.nextInt();
                
                processes.add(new Process("P" + i, arrivalTime, burstTime));
            }
        }

        // Sort processes based on arrival time
        processes.sort((p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime());

        int currentTime = 0;
        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;

        System.out.println("Process\t\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");

        for (Process process : processes) {
            int waitingTime = currentTime - process.getArrivalTime();
            if (waitingTime < 0) {
                currentTime = process.getArrivalTime();
                waitingTime = 0;
            }

            int turnaroundTime = waitingTime + process.getBurstTime();

            totalTurnaroundTime += turnaroundTime;
            totalWaitingTime += waitingTime;

            System.out.println(process.getName() + "\t\t" + process.getArrivalTime() + "\t\t" + process.getBurstTime()
                    + "\t\t" + waitingTime + "\t\t" + turnaroundTime);

            currentTime += process.getBurstTime();
        }

        int totalProcesses = processes.size();
        double averageTurnaroundTime = totalTurnaroundTime / totalProcesses;
        double averageWaitingTime = totalWaitingTime / totalProcesses;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }
}
