import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Process {
    int id, burstTime, waitingTime = 0, turnAroundTime = 0;

    public Process(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
    }
}

public class Scheduling {

    public static void calculatorFCFS(List<Process> processes) {
        int currentTime = 0;
        for (Process process : processes) {
            process.waitingTime = currentTime;
            process.turnAroundTime = process.waitingTime + process.burstTime;
            currentTime += process.burstTime;
        }
    }

    public static void calculateSJF(List<Process> processes){
        processes.sort(Comparator.comparingInt(p -> p.burstTime));
        int currentTime = 0;
        for (Process process : processes) {
            process.waitingTime = currentTime;
            process.turnAroundTime = process.waitingTime + process.burstTime;
            currentTime += process.burstTime;
        }
    }

    public static void displayResults( String title, List<Process> processes) {
        System.out.println("___________________ " + title + "___________________");
        System.out.println("Process ID | Waiting Time | Turn Around Time");
        int totalWaitingTime = 0, totalTurnAroundTime = 0;
        for (Process process : processes) {
            System.out.printf("%9d | %12d | %15d\n", process.id, process.waitingTime, process.turnAroundTime);
            totalWaitingTime += process.waitingTime;
            totalTurnAroundTime += process.turnAroundTime;
        }
        System.out.printf("Avg Waiting Time: %.2f\n", (double) totalWaitingTime / processes.size());
        System.out.printf("Avg Turn Around Time: %.2f\n", (double) totalTurnAroundTime / processes.size());

    }

    public static void main(String[] args) {
        List <Process> fcfsProcesses = Arrays.asList(
                new Process(1, 2),
                new Process(2, 1),
                new Process(3, 8),
                new Process(4, 4),
                new Process(5, 5)
        );

        calculatorFCFS(fcfsProcesses);
        displayResults("Scheduling FCFS", fcfsProcesses);

        /*processes.forEach(p -> {
            p.waitingTime = 0;
            p.turnAroundTime = 0;
        });*/

        List <Process> sjfProcesses = Arrays.asList(
                new Process(1, 2),
                new Process(2, 1),
                new Process(3, 8),
                new Process(4, 4),
                new Process(5, 5)
        );

        calculateSJF(sjfProcesses);
        displayResults("Scheduling SJF", sjfProcesses);
    }

}