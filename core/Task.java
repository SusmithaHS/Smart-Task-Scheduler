package core;

import java.util.Date;

public class Task implements Comparable<Task> {
    private final String title;
    private final int priority;
    private final Date deadline;

    public Task(String title, int priority, Date deadline) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return title + " [Priority: " + priority + ", Deadline: " + deadline + "]";
    }
}