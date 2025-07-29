package core;

import java.util.PriorityQueue;

public class TaskManager {
    final PriorityQueue<Task> taskQueue = new PriorityQueue<>();

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public void removeTask(Task task) {
        taskQueue.remove(task);
    }

    public PriorityQueue<Task> getAllTasks() {
        return new PriorityQueue<>(taskQueue);
    }

    public Task getNextTask() {
        return taskQueue.peek();
    }

    public void clearTasks() {
        taskQueue.clear();
    }
}
