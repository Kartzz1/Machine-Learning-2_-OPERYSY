import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS {

    public List<Process> schedule(
            List<Process> processes) {

        List<Process> schedule =
            new ArrayList<>(processes);

        schedule.sort(
            Comparator.comparingInt(
                Process::getArrivalTime
            ).thenComparingInt(
                Process::getInputOrder
            )
        );

        resetProcesses(schedule);

        int currentTime = 0;

        for (Process process : schedule) {

            if (
                currentTime
                <
                process.getArrivalTime()
            ) {
                currentTime =
                    process.getArrivalTime();
            }

            calculateTimes(
                process,
                currentTime
            );

            currentTime =
                process.getCompletionTime();
        }

        return schedule;
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


