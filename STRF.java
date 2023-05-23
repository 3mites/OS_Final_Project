package strf;

import java.util.Scanner;

class Process {
    int pid;
    int at;
    int bt;
    int ct, wt, tat, rt, start_time;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
    }
}

public class STRF {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the total number of processes: ");
            int n = sc.nextInt();
            Process[] processes = new Process[n];
            float[] bt_remaining = new float[n];
            boolean[] is_completed = new boolean[n];
            int current_time = 0;
            int completed = 0;
            float sum_tat = 0, sum_wt = 0, sum_rt = 0, total_idle_time = 0, length_cycle, prev = 0;
            float cpu_utilization;
            int max_completion_time, min_arrival_time;
            for (int i = 0; i < n; i++) {
                System.out.print("Enter Process " + (i+1) + " Arrival Time: ");
                int at = sc.nextInt();
                System.out.print("Enter Process " + (i+1) + " Burst Time: ");
                int bt = sc.nextInt();
                processes[i] = new Process(i, at, bt);
                bt_remaining[i] = processes[i].bt;
            }   while (completed != n) {
                int min_index = -1;
                int minimum = Integer.MAX_VALUE;
                
                for (int i = 0; i < n; i++) {
                    if (processes[i].at <= current_time && !is_completed[i]) {
                        if (bt_remaining[i] < minimum) {
                            minimum = (int) bt_remaining[i];
                            min_index = i;
                        }
                        if (bt_remaining[i] == minimum) {
                            if (processes[i].at < processes[min_index].at) {
                                minimum = (int) bt_remaining[i];
                                min_index = i;
                            }
                        }
                    }
                }
                
                if (min_index == -1) {
                    current_time++;
                } else {
                    if (bt_remaining[min_index] == processes[min_index].bt) {
                        processes[min_index].start_time = current_time;
                        total_idle_time += (prev == 0) ? 0 : (processes[min_index].start_time - prev);
                    }
                    bt_remaining[min_index] -= 1;
                    current_time++;
                    prev = current_time;
                    
                    if (bt_remaining[min_index] == 0) {
                        processes[min_index].ct = current_time;
                        processes[min_index].tat = processes[min_index].ct - processes[min_index].at;
                        processes[min_index].wt = processes[min_index].tat - processes[min_index].bt;
                        processes[min_index].rt = processes[min_index].start_time - processes[min_index].at;
                        sum_tat += processes[min_index].tat;
                        sum_wt += processes[min_index].wt;
                        sum_rt += processes[min_index].rt;
                        completed++;
                        is_completed[min_index] = true;
                    }
                }
            }   max_completion_time = Integer.MIN_VALUE;
            min_arrival_time = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                max_completion_time = Math.max(max_completion_time, processes[i].ct);
                min_arrival_time = Math.min(min_arrival_time, processes[i].at);
            }   length_cycle = max_completion_time - min_arrival_time;
            System.out.println("\nProcess No.\tAT\tCPU Burst Time\tCT\tTAT\tWT\tRT");
            for (int i = 0; i < n; i++) {
                Process p = processes[i];
                System.out.println(p.pid + "\t\t" + p.at + "\t" + p.bt + "\t\t" + p.ct + "\t" + p.tat + "\t" + p.wt + "\t"
                        + p.rt);
            }   cpu_utilization = (length_cycle - total_idle_time) / length_cycle;
            System.out.println("\nAverage Turnaround time: " + (sum_tat / n));
            System.out.println("Average Waiting Time: " + (sum_wt / n));
            System.out.println("Average Response Time: " + (sum_rt / n));
            System.out.println("Throughput: " + (n / length_cycle));
            System.out.println("CPU Utilization (Percentage): " + (cpu_utilization * 100));
        }
    }
}
