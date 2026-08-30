public class Process {

    private final String processId;
    private final int arrivalTime;
    private final int burstTime;
    private final int inputOrder;

    private int startTime;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;

    public Process(
            String processId,
            int arrivalTime,
            int burstTime,
            int inputOrder) {

        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.inputOrder = inputOrder;
    }

    public String getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getInputOrder() {
        return inputOrder;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void resetTimes() {
        startTime = 0;
        completionTime = 0;
        turnaroundTime = 0;
        waitingTime = 0;
    }
}
