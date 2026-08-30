import java.util.ArrayList;
import java.util.List;

public class SJF {

    public List<Process> schedule(
            List<Process> processes) {

        List<Process> remaining =
            new ArrayList<>(processes);

        List<Process> schedule =
            new ArrayList<>();

        resetProcesses(remaining);

        int currentTime = 0;

        while (!remaining.isEmpty()) {

            Process selected =
                findShortestAvailableProcess(
                    remaining,
                    currentTime
                );

            if (selected == null) {

                currentTime =
                    findNextArrival(remaining);

                continue;
            }

            calculateTimes(
                selected,
                currentTime
            );

            currentTime =
                selected.getCompletionTime();

            schedule.add(selected);
            remaining.remove(selected);
        }

        return schedule;
    }

    private Process findShortestAvailableProcess(
            List<Process> processes,
            int currentTime) {

        Process selected = null;

        for (Process process : processes) {

            if (
                process.getArrivalTime()
                >
                currentTime
            ) {
                continue;
            }

            if (selected == null) {

                selected = process;

            } else if (
                process.getBurstTime()
                <
                selected.getBurstTime()
            ) {

                selected = process;

            } else if (
                process.getBurstTime()
                ==
                selected.getBurstTime()
                &&
                process.getArrivalTime()
                <
                selected.getArrivalTime()
            ) {

                selected = process;

            } else if (
                process.getBurstTime()
                ==
                selected.getBurstTime()
                &&
                process.getArrivalTime()
                ==
                selected.getArrivalTime()
                &&
                process.getInputOrder()
                <
                selected.getInputOrder()
            ) {

                selected = process;
            }
        }

        return selected;
    }

    private int findNextArrival(
            List<Process> processes) {

        int nextArrival =
            Integer.MAX_VALUE;

        for (Process process : processes) {

            if (
                process.getArrivalTime()
                <
                nextArrival
            ) {
                nextArrival =
                    process.getArrivalTime();
            }
        }

        return nextArrival;
    }

    private void calculateTimes(
            Process process,
            int currentTime) {

        int startTime =
            Math.max(
                currentTime,
                process.getArrivalTime()
            );

        int completionTime =
            startTime
            + process.getBurstTime();

        int turnaroundTime =
            completionTime
            - process.getArrivalTime();

        int waitingTime =
            turnaroundTime
            - process.getBurstTime();

        process.setStartTime(startTime);
        process.setCompletionTime(
            completionTime
        );
        process.setTurnaroundTime(
            turnaroundTime
        );
        process.setWaitingTime(
            waitingTime
        );
    }

    private void resetProcesses(
            List<Process> processes) {

        for (Process process : processes) {
            process.resetTimes();
        }
    }
}