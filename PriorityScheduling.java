package priorityscheduling;

import java.util.Scanner;

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arrivalTime, burstTime, priority, waitingTime, turnaroundTime, completionTime;
        int[] x;
        int smallest, count = 0, time, n;
        double avgWaitingTime = 0, avgTurnaroundTime = 0, end;

        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        arrivalTime = new int[n];
        burstTime = new int[n];
        priority = new int[n];
        waitingTime = new int[n];
        turnaroundTime = new int[n];
        completionTime = new int[n];
        x = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time of process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Enter priority of process " + (i + 1) + ": ");
            priority[i] = sc.nextInt();
        }

        System.arraycopy(burstTime, 0, x, 0, n);

        priority[n - 1] = -1;

        for (time = 0; count != n; time++) {
            smallest = n - 1;
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= time && priority[i] > priority[smallest] && burstTime[i] > 0)
                    smallest = i;
            }
            time += burstTime[smallest] - 1;
            burstTime[smallest] = -1;
            count++;
            end = time + 1;
            completionTime[smallest] = (int) end;
            waitingTime[smallest] = (int) (end - arrivalTime[smallest] - x[smallest]);
            turnaroundTime[smallest] = (int) (end - arrivalTime[smallest]);
        }

        System.out.println("Process\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time\tCompletion Time\tPriority");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t\t" + x[i] + "\t\t" + arrivalTime[i] + "\t\t" + waitingTime[i] +
                    "\t\t" + turnaroundTime[i] + "\t\t" + completionTime[i] + "\t\t" + priority[i]);

            avgWaitingTime += waitingTime[i];
            avgTurnaroundTime += turnaroundTime[i];
        }

        System.out.println("\nAverage waiting time = " + (avgWaitingTime / n));
        System.out.println("Average turnaround time = " + (avgTurnaroundTime / n));
    }
}
