import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FCFS fcfs = new FCFS();
        SJF sjf = new SJF();

        printHeader();

        int numberOfProcesses = readIntInRange(
            scanner,
            "Enter number of processes (3-10): ",
            3,
            10
        );

        List<Process> processes = readProcesses(
            scanner,
            numberOfProcesses
        );

        boolean continueRunning = true;

        while (continueRunning) {
            int algorithm = readAlgorithmChoice(scanner);
            List<Process> schedule;

            if (algorithm == 1) {
                System.out.println();
                System.out.println(
                    "Selected Scheduling Algorithm: FCFS"
                );

                schedule = fcfs.schedule(processes);

            } else {
                System.out.println();
                System.out.println(
                    "Selected Scheduling Algorithm: SJF"
                );

                schedule = sjf.schedule(processes);
            }

            printGanttChart(schedule);
            printResults(processes);

            int runAgain = readYesNoChoice(scanner);

            if (runAgain == 2) {
                continueRunning = false;
            }
        }

        scanner.close();

        System.out.println();
        System.out.println("Program terminated.");
    }

    private static void printHeader() {
        System.out.println(
            "============================================================"
        );
        System.out.println(
            "           CPU SCHEDULING MACHINE PROBLEM #2"
        );
        System.out.println(
            "============================================================"
        );
        System.out.println(
            "Non-Preemptive CPU Scheduling"
        );
        System.out.println();
    }

    private static List<Process> readProcesses(
            Scanner scanner,
            int numberOfProcesses) {

        List<Process> processes = new ArrayList<>();

        for (int i = 1; i <= numberOfProcesses; i++) {

            System.out.println();
            System.out.println("Process " + i);

            String processId;

            while (true) {
                processId = readProcessId(
                    scanner,
                    "Enter process ID for Process" + i + ": "
                );

                if (isDuplicateProcessId(
                        processes,
                        processId
                )) {
                    System.out.println(
                        "Error: Process ID already exists."
                    );
                } else {
                    break;
                }
            }

            int arrivalTime;

            while (true) {
                arrivalTime = readNonNegativeInt(
                    scanner,
                    "Enter waiting time for "
                    + processId
                    + ": "
                );

                if (isDuplicateArrivalTime(
                        processes,
                        arrivalTime
                )) {
                    System.out.println(
                        "Error: Waiting time already exists."
                    );
                } else {
                    break;
                }
            }

            int burstTime = readPositiveInt(
                scanner,
                "Enter burst time for "
                + processId
                + ": "
            );

            processes.add(
                new Process(
                    processId,
                    arrivalTime,
                    burstTime,
                    i
                )
            );
        }

        return processes;
    }

    private static int readAlgorithmChoice(
            Scanner scanner) {

        System.out.println();
        System.out.println(
            "Choose Scheduling Algorithm:"
        );
        System.out.println(
            "1. FCFS - First Come First Serve"
        );
        System.out.println(
            "2. SJF  - Shortest Job First"
        );

        return readIntInRange(
            scanner,
            "Enter your choice: ",
            1,
            2
        );
    }

    private static int readYesNoChoice(
            Scanner scanner) {

        System.out.println();
        System.out.println(
            "Would you like to run another scheduling algorithm?"
        );
        System.out.println("1. Yes");
        System.out.println("2. No");

        return readIntInRange(
            scanner,
            "Enter your choice: ",
            1,
            2
        );
    }

    private static String readProcessId(
            Scanner scanner,
            String prompt) {

        while (true) {
            System.out.print(prompt);

            String input =
                scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(
                    "Error: Process ID cannot be empty."
                );
                continue;
            }

            if (!input.matches("[A-Za-z0-9_]+")) {
                System.out.println(
                    "Error: Process ID may contain only "
                    + "letters, numbers, and underscores."
                );
                continue;
            }

            return input.toUpperCase();
        }
    }

    private static int readNonNegativeInt(
            Scanner scanner,
            String prompt) {

        while (true) {
            System.out.print(prompt);

            String input =
                scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(
                    "Error: Input cannot be empty."
                );
                continue;
            }

            try {
                int value =
                    Integer.parseInt(input);

                if (value < 0) {
                    System.out.println(
                        "Error: waiting time cannot be negative."
                    );
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println(
                    "Error: Please enter a valid integer."
                );
            }
        }
    }

    private static int readPositiveInt(
            Scanner scanner,
            String prompt) {

        while (true) {
            System.out.print(prompt);

            String input =
                scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(
                    "Error: Input cannot be empty."
                );
                continue;
            }

            try {
                int value =
                    Integer.parseInt(input);

                if (value <= 0) {
                    System.out.println(
                        "Error: Burst time must be greater than 0."
                    );
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println(
                    "Error: Please enter a valid positive integer."
                );
            }
        }
    }

    private static int readIntInRange(
            Scanner scanner,
            String prompt,
            int min,
            int max) {

        while (true) {
            System.out.print(prompt);

            String input =
                scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(
                    "Error: Input cannot be empty."
                );
                continue;
            }

            try {
                int value =
                    Integer.parseInt(input);

                if (value < min || value > max) {
                    System.out.println(
                        "Error: Please enter a value from "
                        + min
                        + " to "
                        + max
                        + "."
                    );
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println(
                    "Error: Please enter a valid integer."
                );
            }
        }
    }

    private static boolean isDuplicateProcessId(
            List<Process> processes,
            String processId) {

        for (Process process : processes) {
            if (process.getProcessId().equals(processId)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isDuplicateArrivalTime(
            List<Process> processes,
            int arrivalTime) {

        for (Process process : processes) {
            if (process.getArrivalTime() == arrivalTime) {
                return true;
            }
        }

        return false;
    }

    private static void printGanttChart(
            List<Process> schedule) {

        System.out.println();
        System.out.println(
            "                         GANTT CHART"
        );
        System.out.println();

        if (schedule.isEmpty()) {
            System.out.println(
                "        No processes to display."
            );
            return;
        }

        int blockWidth = 10;

        for (Process process : schedule) {
            blockWidth = Math.max(
                blockWidth,
                process.getProcessId().length() + 4
            );
        }

        System.out.print("        +");

        for (int i = 0; i < schedule.size(); i++) {
            System.out.print(
                "-".repeat(blockWidth)
            );
            System.out.print("+");
        }
        System.out.println();

        System.out.print("        |");

        for (Process process : schedule) {

            String processId =
                process.getProcessId();

            int padding =
                blockWidth - processId.length();

            int leftPadding =
                padding / 2;

            int rightPadding =
                padding - leftPadding;

            System.out.print(
                " ".repeat(leftPadding)
            );

            System.out.print(processId);

            System.out.print(
                " ".repeat(rightPadding)
            );

            System.out.print("|");
        }

        System.out.println();

        System.out.print("        +");

        for (int i = 0; i < schedule.size(); i++) {
            System.out.print(
                "-".repeat(blockWidth)
            );
            System.out.print("+");
        }

        System.out.println();

        printTimeLine(schedule, blockWidth);
    }

    private static void printTimeLine(
            List<Process> schedule,
            int blockWidth) {

        List<Integer> timePoints =
            new ArrayList<>();

        timePoints.add(0);

        for (int i = 0; i < schedule.size(); i++) {

            Process current =
                schedule.get(i);

            if (
                i == 0
                &&
                current.getStartTime() > 0
            ) {
                timePoints.add(
                    current.getStartTime()
                );
            }

            timePoints.add(
                current.getCompletionTime()
            );

            if (i < schedule.size() - 1) {

                Process next =
                    schedule.get(i + 1);

                if (
                    next.getStartTime()
                    >
                    current.getCompletionTime()
                ) {
                    timePoints.add(
                        next.getStartTime()
                    );
                }
            }
        }

        System.out.print("        ");

        for (int time : timePoints) {

            System.out.printf(
                "%-" + (blockWidth + 2) + "d",
                time
            );
        }

        System.out.println();
    }

    private static void printResults(
            List<Process> originalProcesses) {

        List<Process> results =
            new ArrayList<>(originalProcesses);

        results.sort(
            Comparator.comparingInt(
                Process::getInputOrder
            )
        );

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println();
        System.out.println(
            "================================================================"
        );
        System.out.println(
            "                       SCHEDULING RESULTS"
        );
        System.out.println(
            "================================================================"
        );

        System.out.printf(
            "%-8s %-8s %-8s %-8s %-10s %-8s%n",
            "PID",
            "AT",
            "BT",
            "CT",
            "TAT",
            "WT"
        );

        System.out.println(
            "----------------------------------------------------------------"
        );

        for (Process process : results) {

            System.out.printf(
                "%-8s %-8d %-8d %-8d %-10d %-8d%n",
                process.getProcessId(),
                process.getArrivalTime(),
                process.getBurstTime(),
                process.getCompletionTime(),
                process.getTurnaroundTime(),
                process.getWaitingTime()
            );

            totalWaitingTime +=
                process.getWaitingTime();

            totalTurnaroundTime +=
                process.getTurnaroundTime();
        }

        System.out.println(
            "----------------------------------------------------------------"
        );

        double averageWaitingTime =
            (double) totalWaitingTime
            / results.size();

        double averageTurnaroundTime =
            (double) totalTurnaroundTime
            / results.size();

        System.out.printf(
            "Average Waiting Time: %.1f%n",
            averageWaitingTime
        );

        System.out.printf(
            "Average Turnaround Time: %.1f%n",
            averageTurnaroundTime
        );
    }
}