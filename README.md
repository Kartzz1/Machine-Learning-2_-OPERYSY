# CPU Scheduling Machine Problem #2

A Java program that simulates two non-preemptive CPU scheduling algorithms:

- **FCFS (First Come First Serve)**
- **SJF (Shortest Job First)**

The program calculates Completion Time, Turnaround Time, Waiting Time, Average Waiting Time, and Average Turnaround Time for each process.

## Features

- Supports **3 to 10 processes**
- Validates process input
- Requires unique Process IDs
- Requires unique Arrival Times
- Validates non-negative Arrival Time
- Validates positive Burst Time
- Handles invalid and non-numeric input without crashing
- Implements **non-preemptive FCFS**
- Implements **non-preemptive SJF**
- SJF only considers processes that have already arrived
- Uses arrival order as the tie-breaker when SJF processes have the same Burst Time
- Handles CPU idle time correctly
- Displays a simple ASCII Gantt Chart
- Displays Completion Time, Turnaround Time, and Waiting Time
- Displays Average Waiting Time and Average Turnaround Time with one decimal place
- Allows the user to run another scheduling algorithm without re-entering the processes

## Project Structure

```text
MP2 OPERSYS/
├── Main.java
├── Process.java
├── FCFS.java
└── SJF.java
```

### Main.java

Handles:

- Program flow
- User input
- Input validation
- Scheduling algorithm selection
- Gantt Chart display
- Scheduling results display
- Option to run another scheduling algorithm

### Process.java

Represents a CPU process and stores:

- Process ID
- Arrival Time
- Burst Time
- Input Order
- Start Time
- Completion Time
- Turnaround Time
- Waiting Time

### FCFS.java

Implements the **First Come First Serve** scheduling algorithm.

Processes are executed according to their Arrival Time. FCFS is non-preemptive, so once a process starts, it continues until completion.

### SJF.java

Implements **Non-Preemptive Shortest Job First** scheduling.

At every scheduling decision, only processes that have already arrived are considered. Among those processes, the one with the shortest Burst Time is selected.

If two available processes have the same Burst Time, the process with the earlier Arrival Time is selected.

## Scheduling Formulas

### Completion Time

```text
CT = Start Time + Burst Time
```

### Turnaround Time

```text
TAT = Completion Time - Arrival Time
```

### Waiting Time

```text
WT = Turnaround Time - Burst Time
```

### Average Waiting Time

```text
Average Waiting Time = Total Waiting Time / Number of Processes
```

### Average Turnaround Time

```text
Average Turnaround Time = Total Turnaround Time / Number of Processes
```

## How the Algorithms Work

### FCFS

FCFS executes processes in the order they arrive.

Example:

```text
P1 → P2 → P3 → P4
```

If no process has arrived yet, the CPU waits until the next process arrives.

### SJF

SJF selects the shortest Burst Time from the processes that have already arrived.

Example:

```text
Current Time = 8

Ready Queue:
P2 → BT = 4
P3 → BT = 2

Selected:
P3
```

A process that has not arrived yet cannot be selected, even if it has a shorter Burst Time.

## Gantt Chart

The program displays the execution order using an ASCII Gantt Chart.

Example:

```text
                         GANTT CHART

        +----------+----------+----------+----------+----------+
        |    C     |    B     |    E     |    D     |    A     |
        +----------+----------+----------+----------+----------+
        0          34         58         81         86         91
```

CPU gaps are represented by the timeline values without creating an `IDLE` block.

## Sample Result

```text
================================================================
                       SCHEDULING RESULTS
================================================================
PID      AT       BT       CT       TAT        WT
----------------------------------------------------------------
1        2        8        26       24         16
2        0        4        4        4          0
3        3        2        6        3          1
4        9        5        18       9          4
6        6        7        13       7          0
----------------------------------------------------------------
Average Waiting Time: 4.2
Average Turnaround Time: 9.4
```

## Input Validation

The program checks the following:

### Number of Processes

Allowed:

```text
3-10
```

### Process ID

- Cannot be empty
- Must be unique
- Allows letters, numbers, and underscores

### Arrival Time

- Must be numeric
- Must be non-negative
- Must be unique

### Burst Time

- Must be numeric
- Must be greater than zero

### Algorithm Choice

Only the following choices are accepted:

```text
1. FCFS
2. SJF
```

### Run Another Algorithm

After the results are displayed:

```text
Would you like to run another scheduling algorithm?
1. Yes
2. No
```

Selecting **Yes** returns to the scheduling algorithm menu. Selecting **No** terminates the program.

## Requirements

- Java Development Kit (JDK)
- Java-compatible terminal or IDE such as VS Code, IntelliJ IDEA, or Eclipse

## How to Compile

Make sure all four `.java` files are in the same folder.

```bash
javac Main.java Process.java FCFS.java SJF.java
```

## How to Run

```bash
java Main
```

## Concepts Demonstrated

This project demonstrates:

- Object-Oriented Programming
- Classes and Objects
- Encapsulation
- ArrayList
- Methods
- Loops
- Conditional Statements
- Exception Handling
- Input Validation
- CPU Scheduling
- FCFS Scheduling
- Non-Preemptive SJF Scheduling
- Ready Queue Management
- Completion Time
- Turnaround Time
- Waiting Time
- Average Scheduling Metrics

## Purpose

This project was created as a Machine Problem for studying operating systems and CPU scheduling. It provides a practical implementation of FCFS and non-preemptive SJF scheduling using Java while demonstrating basic Object-Oriented Programming concepts.
